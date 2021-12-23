package edu.studio.sample;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Interacts with the twitter API to post tweets or do other twitter actions for
 * a given Twitter account.
 *
 * @author @JoeSondow
 */
public class Tweeter {

    private Twitter twitter;

    public Tweeter() {
        this(new ConfigurationBuilder().setTrimUserEnabled(true).build());
    }

    public Tweeter(Configuration config) {
        twitter = new TwitterFactory(config).getInstance();
    }

    public Status tweet(String message) {
        try {
            Status status = twitter.updateStatus(message);
            String msg = "Successfully tweeted message: " + message + " with status " + status;
            System.out.println(msg);
            return status;
        }
        catch (TwitterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void authorize(String consumerKey, String consumerSecret) {
        AccessToken accessToken = loadCatsayBotAccessToken();
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(accessToken);
    }

    private AccessToken loadCatsayBotAccessToken() {
        String token = "";
        String tokenSecret = "";
        return new AccessToken(token, tokenSecret);
    }
}
