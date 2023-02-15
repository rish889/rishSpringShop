
package com.myorg;

import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.*;
import software.amazon.awscdk.services.rds.*;
import software.constructs.Construct;

import java.util.Arrays;

public class RssRdsStack extends Stack {
    public RssRdsStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public RssRdsStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        final Vpc vpc = createVpc();
        final Instance bastionInstance = createBastion(vpc);
        final DatabaseInstance databaseInstance = createRds(vpc, bastionInstance);
    }

    private Vpc createVpc() {
        return Vpc.Builder.create(this, "vpc")
                .subnetConfiguration(Arrays.asList(
                                SubnetConfiguration.builder().name("public").subnetType(SubnetType.PUBLIC).build(),
                                SubnetConfiguration.builder().name("isolated").subnetType(SubnetType.PRIVATE_ISOLATED).build()
                        )
                )
                .build();
    }

    private Instance createBastion(final Vpc vpc) {
        final SecurityGroup bastionSecurityGroup = SecurityGroup.Builder.create(this, "bastion-sg").vpc(vpc).build();
        bastionSecurityGroup.addIngressRule(Peer.anyIpv4(), Port.tcp(22), "allow SSH connections from anywhere");

        return Instance.Builder
                .create(this, "bastion")
                .vpc(vpc)
                .vpcSubnets(SubnetSelection.builder().subnetType(SubnetType.PUBLIC).build())
                .securityGroup(bastionSecurityGroup)
                .instanceType(InstanceType.of(InstanceClass.BURSTABLE3, InstanceSize.MICRO))
                .machineImage(AmazonLinuxImage.Builder.create().generation(AmazonLinuxGeneration.AMAZON_LINUX_2).build())
                .keyName("rss-ec2-key-pair")
                .build();

    }

    private DatabaseInstance createRds(Vpc vpc, Instance bastionInstance) {
        final IInstanceEngine instanceEngine = DatabaseInstanceEngine.postgres(
                PostgresInstanceEngineProps.builder()
                        .version(PostgresEngineVersion.VER_13_6)
                        .build()
        );

        final DatabaseInstance databaseInstance = DatabaseInstance.Builder.create(this, "rss-rds")
                .vpc(vpc)
                .vpcSubnets(SubnetSelection.builder().subnetType(SubnetType.PRIVATE_ISOLATED).build())
                .instanceType(InstanceType.of(InstanceClass.BURSTABLE3, InstanceSize.MICRO))
                .engine(instanceEngine)
                .instanceIdentifier("rss-rds")
                .removalPolicy(RemovalPolicy.DESTROY)
                .credentials(Credentials.fromGeneratedSecret("postgres"))
                .databaseName("todosdb")
                .build();

        databaseInstance.getConnections().allowFrom(bastionInstance, Port.tcp(5432));
        return databaseInstance;
    }

}



