# testing a twitter bot

Build this project with `$ mvn clean verify` and upload the jar to AWS Lambda. Ensure that your Twitter API consumer/access token/keys/secrets are stored correctly as environment variables in AWS, and then set the entry method to be edu.studio.sample.LambdaRequestHander::handleRequest
