package edu.studio.sample;

import java.util.Locale;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import jfortune.Cookie;
import jfortune.Fortune;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * The function that AWS Lambda will invoke.
 *
 * @author @JoeSondow
 */
public class LambdaRequestHandler implements RequestHandler<Object, Object>
{

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amazonaws.services.lambda.runtime.RequestHandler#handleRequest(java. lang.Object,
	 * com.amazonaws.services.lambda.runtime.Context)
	 */
	@Override
	public Object handleRequest(Object input, Context context)
	{
		Configuration config = configure();
		Tweeter tweeter = createTweeter(config);

		LambdaLogger logger = context.getLogger();

		Fortune fortune = new Fortune(new Locale("en"));
		fortune.setShortLength(200);
		Cookie cookie = fortune.getShortCookie();

		Catsay catPrint = new Catsay();
		String tweetBody = catPrint.makeTweet(cookie);

		int retryMakeTweetBodyCount = 0;
		while (tweetBody.length() > 280)
		{
			retryMakeTweetBodyCount++;
			catPrint.clearStream();
			cookie = fortune.getShortCookie();
			tweetBody = catPrint.makeTweet(cookie);
		}
		logger.log("retryCount: " + retryMakeTweetBodyCount + '\n');
		logger.log("bodyLength: " + tweetBody.length() + '\n');
		return tweeter.tweet(tweetBody);
	}

	Configuration configure()
	{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		String consumerKeyEnvVar = getEnvVar("twitter_consumer_key");
		String consumerSecretEnvVar = getEnvVar("twitter_consumer_secret");
		String accessTokenEnvVar = getEnvVar("twitter_access_token_key");
		String accessTokenSecretEnvVar = getEnvVar("twitter_access_token_secret");
		if (consumerKeyEnvVar != null)
		{
			cb.setOAuthConsumerKey(consumerKeyEnvVar);
		}
		if (consumerSecretEnvVar != null)
		{
			cb.setOAuthConsumerSecret(consumerSecretEnvVar);
		}
		if (accessTokenEnvVar != null)
		{
			cb.setOAuthAccessToken(accessTokenEnvVar);
		}
		if (accessTokenSecretEnvVar != null)
		{
			cb.setOAuthAccessTokenSecret(accessTokenSecretEnvVar);
		}
		Configuration config = cb.setTrimUserEnabled(true).build();
		return config;
	}

	Tweeter createTweeter(Configuration config)
	{
		return new Tweeter(config);
	}

	String getEnvVar(String key)
	{
		return System.getenv(key);
	}

}
