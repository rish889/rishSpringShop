package com.myorg;

import software.amazon.awscdk.App;
import software.amazon.awscdk.StackProps;

public class CdkApp {
    public static void main(final String[] args) {
        App app = new App();

        new HelloEcsStack(app, "HelloEcsStack", StackProps.builder()
                .build());

        app.synth();
    }
}

