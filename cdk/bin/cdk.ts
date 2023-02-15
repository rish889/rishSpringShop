#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from 'aws-cdk-lib';
import { RishSpringShopStack } from '../lib/rish-spring-shop-stack';

const app = new cdk.App();
new RishSpringShopStack(app, 'RishSpringShopStack', {});