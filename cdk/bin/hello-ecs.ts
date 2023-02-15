#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from 'aws-cdk-lib';
import { HelloEcsStack } from '../lib/hello-ecs-stack';

const app = new cdk.App();
new HelloEcsStack(app, 'RishSpringShopStack', {});