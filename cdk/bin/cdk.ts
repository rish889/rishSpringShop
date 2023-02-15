#!/usr/bin/env node
import * as cdk from "aws-cdk-lib";
import { CdkStarterStack } from "../lib/cdk-starter-stack";

const app = new cdk.App();
// new RishSpringShopStack(app, 'RishSpringShopStack', {});

new CdkStarterStack(app, "cdk-stack", {});
