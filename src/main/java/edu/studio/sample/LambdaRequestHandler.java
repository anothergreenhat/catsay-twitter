package edu.studio.sample;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * The function that AWS Lambda will invoke.
 *
 * @author @JoeSondow
 */
public class LambdaRequestHandler implements RequestHandler<Object, Object> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.amazonaws.services.lambda.runtime.RequestHandler#handleRequest(java.
     * lang.Object, com.amazonaws.services.lambda.runtime.Context)
     */
    @Override
    public Object handleRequest(Object input, Context context) {
        Configuration config = configure();
        Tweeter tweeter = createTweeter(config);
        // tweeter.authorize(args[0], args[1]);
        String message = "hello from aws!";
        return tweeter.tweet(message);

        // Tweeter tw = new Tweeter();
        // tw.authorize(args[0], args[1]);
        // tw.tweet("hello from java!");

    }

    Configuration configure() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        String consumerKeyEnvVar = getEnvVar("twitter_consumer_key");
        String consumerSecretEnvVar = getEnvVar("twitter_consumer_secret");
        String accessTokenEnvVar = getEnvVar("twitter_access_token_key");
        String accessTokenSecretEnvVar = getEnvVar("twitter_access_token_secret");
        if (consumerKeyEnvVar != null) {
            cb.setOAuthConsumerKey(consumerKeyEnvVar);
        }
        if (consumerSecretEnvVar != null) {
            cb.setOAuthConsumerSecret(consumerSecretEnvVar);
        }
        if (accessTokenEnvVar != null) {
            cb.setOAuthAccessToken(accessTokenEnvVar);
        }
        if (accessTokenSecretEnvVar != null) {
            cb.setOAuthAccessTokenSecret(accessTokenSecretEnvVar);
        }
        Configuration config = cb.setTrimUserEnabled(true).build();
        return config;
    }

    Tweeter createTweeter(Configuration config) {
        return new Tweeter(config);
    }

    String getEnvVar(String key) {
        return System.getenv(key);
    }

}
