# testing a twitter bot

Build this project with `$ mvn clean verify` and upload the jar to AWS Lambda. Ensure that your Twitter API consumer/access token/keys/secrets are stored correctly as environment variables in AWS, and then set the entry method to be edu.studio.sample.LambdaRequestHander::handleRequest

In order to use this program with the Twitter API, you must have "Elevated" access to the API v1.1. `twitter4j` is not yet updated (or will ever I believe) to use API v2 protocols, so this is essential. More info here: https://developer.twitter.com/en/docs/twitter-api/getting-started/about-twitter-api
