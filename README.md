# AWS Lambda Layer Demo

A demo serverless application to demonstrate how AWS Lambda Layer can be used with lambdas written in Kotlin.

## Architecture diagram

![Architecture diagram of the demo application](resources/lambda-layer-demo-arch.png?raw=true "Architecture diagram of the demo application")

## Notes

* Both the lambda functions, and the lambda layer are in separate modules. The layer module (`kotlin-lambda-commons`) contains some common dependencies required by all the lambdas and has `provided` scope.
* On packaging, the built fat-jar is zipped, and is ready for use as an AWS Lambda Layer (the resulting jar artifact is on the required path of [`java\lib`](https://docs.aws.amazon.com/lambda/latest/dg/configuration-layers.html#configuration-layers-path)).
