
package com.myorg;

import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.*;
import software.amazon.awscdk.services.rds.*;
import software.constructs.Construct;

public class RssRdsStack extends Stack {
    public RssRdsStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public RssRdsStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        final Vpc vpc = Vpc.Builder.create(this, id + "-vpc")
                .natGateways(0) // Do not create any gateways
                .build();

        final IInstanceEngine instanceEngine = DatabaseInstanceEngine.postgres(
                PostgresInstanceEngineProps.builder()
                        .version(PostgresEngineVersion.VER_13_6)
                        .build()
        );

        final DatabaseInstance databaseInstance = DatabaseInstance.Builder.create(this, id + "-rds")
                .vpc(vpc)
                .vpcSubnets(SubnetSelection.builder().subnetType(SubnetType.PRIVATE_ISOLATED).build())
                .instanceType(InstanceType.of(InstanceClass.BURSTABLE3, InstanceSize.MICRO))
                .engine(instanceEngine)
                .instanceIdentifier(id + "-rds")
                .removalPolicy(RemovalPolicy.DESTROY) // If you want the destroy command to not take the final snapshot
                .build();
    }
}

