
package com.myorg;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ecr.Repository;
import software.amazon.awscdk.services.ecs.ContainerImage;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateService;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedTaskImageOptions;
import software.constructs.Construct;

public class HelloEcsStack extends Stack {
    public HelloEcsStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public HelloEcsStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        ApplicationLoadBalancedFargateService.Builder.create(this, "HelloService")
                .taskImageOptions(ApplicationLoadBalancedTaskImageOptions.builder()
                        .image(ContainerImage.fromEcrRepository(Repository.fromRepositoryName(
                                this,
                                "RishSpringShopRepository",
                                "rish-spring-shop")))
                        .containerPort(80)
                        .build())
                .publicLoadBalancer(true)
                .cpu(256)
                .memoryLimitMiB(512)
                .build();
    }
}
