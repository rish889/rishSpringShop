package com.myorg;

import software.amazon.awscdk.App;
import software.amazon.awscdk.StackProps;

public class CdkApp {
    public static void main(final String[] args) {
        App app = new App();

        new EcsStack(app,
                "RishSpringShopStack",
                StackProps.builder().build());

        new RssStack(app,
                "rss",
                StackProps.builder().build());

        app.synth();
    }
}

