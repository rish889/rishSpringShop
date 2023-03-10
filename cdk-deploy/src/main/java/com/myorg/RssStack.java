
package com.myorg;

import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.SecretValue;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.*;
import software.amazon.awscdk.services.ecr.Repository;
import software.amazon.awscdk.services.ecs.ContainerImage;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateService;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedTaskImageOptions;
import software.amazon.awscdk.services.rds.*;
import software.constructs.Construct;

import java.util.HashMap;
import java.util.Map;

public class RssStack extends Stack {
    public RssStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public RssStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        final IVpc vpc = createVpc();
        final Instance bastionInstance = createBastion(vpc, id);
        final DatabaseInstance databaseInstance = createRds(vpc, bastionInstance, id);
        final ApplicationLoadBalancedFargateService productService = createProductService(id, vpc, databaseInstance);

        databaseInstance.getConnections().allowFrom(productService.getService(), Port.tcp(5432));
    }

    private IVpc createVpc() {
        return Vpc.Builder.create(this, "vpc").build();
    }

    private Instance createBastion(final IVpc vpc, final String id) {
        final SecurityGroup bastionSecurityGroup = SecurityGroup.Builder.create(this, "bastion-sg").vpc(vpc).build();
        bastionSecurityGroup.addIngressRule(Peer.anyIpv4(), Port.tcp(22), "allow SSH connections from anywhere");

        return Instance.Builder
                .create(this, "bastion")
                .vpc(vpc)
                .vpcSubnets(SubnetSelection.builder().subnetType(SubnetType.PUBLIC).build())
                .securityGroup(bastionSecurityGroup)
                .instanceType(InstanceType.of(InstanceClass.BURSTABLE3, InstanceSize.MICRO))
                .machineImage(AmazonLinuxImage.Builder.create().generation(AmazonLinuxGeneration.AMAZON_LINUX_2).build())
                .keyName(id + "-ec2-key-pair")
                .build();

    }

    private DatabaseInstance createRds(IVpc vpc,
                                       Instance bastionInstance,
                                       final String id) {
        final IInstanceEngine instanceEngine = DatabaseInstanceEngine.postgres(
                PostgresInstanceEngineProps.builder()
                        .version(PostgresEngineVersion.VER_13_6)
                        .build()
        );

        final DatabaseInstance databaseInstance = DatabaseInstance.Builder.create(this, id + "-rds")
                .vpc(vpc)
                .instanceType(InstanceType.of(InstanceClass.BURSTABLE3, InstanceSize.MICRO))
                .engine(instanceEngine)
                .instanceIdentifier(id + "-rds")
                .removalPolicy(RemovalPolicy.DESTROY)
//                .credentials(Credentials.fromGeneratedSecret("postgres"))
                .credentials(Credentials.fromPassword("postgres", SecretValue.Builder.create("postgres").build()))
                .databaseName("product_service")
                .build();

        databaseInstance.getConnections().allowFrom(bastionInstance, Port.tcp(5432));
        return databaseInstance;
    }

    private ApplicationLoadBalancedFargateService createProductService(String id, IVpc vpc, DatabaseInstance databaseInstance) {
        final Map<String, String> environment = new HashMap<>();
        environment.put("spring.profiles.active", "dev");
        environment.put("rss.postgres.host", databaseInstance.getDbInstanceEndpointAddress());

        ApplicationLoadBalancedFargateService productService = ApplicationLoadBalancedFargateService
                .Builder
                .create(this, "product-service")
                .vpc(vpc)
                .taskImageOptions(ApplicationLoadBalancedTaskImageOptions.builder()
                        .image(ContainerImage.fromEcrRepository(Repository.fromRepositoryName(
                                this,
                                "rss-product-service-repository",
                                "rish-spring-shop-product-service")))
                        .containerPort(80)
                        .environment(environment)
                        .build())
                .publicLoadBalancer(true)
                .cpu(256)
                .memoryLimitMiB(512)
                .build();

        return productService;
    }


}



