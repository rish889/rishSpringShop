
package com.myorg;

import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.*;
import software.amazon.awscdk.services.rds.*;
import software.constructs.Construct;

import java.util.Arrays;

public class RssRdsStack2 extends Stack {
    public RssRdsStack2(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public RssRdsStack2(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        final Vpc vpc = Vpc.Builder.create(this, "vpc")
                .subnetConfiguration(Arrays.asList(SubnetConfiguration.builder().name("public-subnet-1").subnetType(SubnetType.PUBLIC).build()))
                .subnetConfiguration(Arrays.asList(SubnetConfiguration.builder().name("isolated-subnet-1").subnetType(SubnetType.PRIVATE_ISOLATED).build()))
                .build();

        final SecurityGroup bastionSecurityGroup = SecurityGroup.Builder.create(this, "bastion-sg").vpc(vpc).build();
        bastionSecurityGroup.addIngressRule(Peer.anyIpv4(), Port.tcp(22), "allow SSH connections from anywhere");

        final Instance ec2Instance = Instance.Builder
                .create(this, "bastion")
                .vpc(vpc)
                .vpcSubnets(SubnetSelection.builder().subnetType(SubnetType.PUBLIC).build())
                .securityGroup(bastionSecurityGroup)
                .instanceType(InstanceType.of(InstanceClass.BURSTABLE3, InstanceSize.MICRO))
                .machineImage(AmazonLinuxImage.Builder.create().generation(AmazonLinuxGeneration.AMAZON_LINUX_2).build())
                .keyName("rss-ec2-key-pair")
                .build();


        final IInstanceEngine instanceEngine = DatabaseInstanceEngine.postgres(
                PostgresInstanceEngineProps.builder()
                        .version(PostgresEngineVersion.VER_13_6)
                        .build()
        );

        final DatabaseInstance databaseInstance = DatabaseInstance.Builder.create(this, "rds")
                .vpc(vpc)
                .vpcSubnets(SubnetSelection.builder().subnetType(SubnetType.PRIVATE_WITH_NAT).build())
                .instanceType(InstanceType.of(InstanceClass.BURSTABLE3, InstanceSize.MICRO))
                .engine(instanceEngine)
                .instanceIdentifier("rds")
                .removalPolicy(RemovalPolicy.DESTROY)
                .credentials(Credentials.fromGeneratedSecret("postgres"))
                .databaseName("todosdb")
                .build();

        databaseInstance.getConnections().allowFrom(ec2Instance, Port.tcp(5432));
    }
}

