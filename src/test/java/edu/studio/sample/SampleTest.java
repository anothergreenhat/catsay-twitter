package edu.studio.sample;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jfortune.Cookie;
import jfortune.Fortune;

class SampleTest
{

	@BeforeEach
	void setUp()
	{
		System.setProperty("line.separator", "\n");
	}

	@Test
	void testCreateShortTweetInLengthLimit()
	{
		Fortune fortune = new Fortune(new Locale("en"));
		fortune.setShortLength(280 - 132 - 25);
		Cookie cookie = fortune.getShortCookie();

		Catsay catPrint = new Catsay();
		catPrint.clearStream();
		String tweetBody = catPrint.makeTweet(cookie);
		System.out.println(tweetBody + "\nNum chars: " + tweetBody.length());
		assert (tweetBody.length() <= 280);

		cookie = fortune.getShortCookie();
		catPrint.clearStream();
		tweetBody = catPrint.makeTweet(cookie);
		System.out.println(tweetBody + "\nNum chars: " + tweetBody.length());
		assert (tweetBody.length() <= 280);

		cookie = fortune.getShortCookie();
		catPrint.clearStream();
		tweetBody = catPrint.makeTweet(cookie);
		System.out.println(tweetBody + "\nNum chars: " + tweetBody.length());
		assert (tweetBody.length() <= 280);

		cookie = fortune.getShortCookie();
		catPrint.clearStream();
		tweetBody = catPrint.makeTweet(cookie);
		System.out.println(tweetBody + "\nNum chars: " + tweetBody.length());
		assert (tweetBody.length() <= 280);

		cookie = fortune.getShortCookie();
		catPrint.clearStream();
		tweetBody = catPrint.makeTweet(cookie);
		System.out.println(tweetBody + "\nNum chars: " + tweetBody.length());
		assert (tweetBody.length() <= 280);

		cookie = fortune.getShortCookie();
		catPrint.clearStream();
		tweetBody = catPrint.makeTweet(cookie);
		System.out.println(tweetBody + "\nNum chars: " + tweetBody.length());
		assert (tweetBody.length() <= 280);
	}

	@Test
	void testCreateLongTweet()
	{
		Fortune fortune = new Fortune(new Locale("en"));
		Cookie cookie = fortune.getLongCookie();
		if (cookie.getText().length() > 700)
		{
			System.out.println(cookie.getText() + "\nNum chars: " + cookie.getText().length());
		}
		else
		{
			assert (cookie.getText().length() < 700);
			Catsay catPrint = new Catsay();
			catPrint.clearStream();
			String tweetBody = catPrint.makeTweet(cookie);
			System.out.println(tweetBody + "\nNum chars: " + tweetBody.length());
		}
	}

	@Test
	void testRetryForNewerTweets()
	{
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
		System.out.print("retryCount: " + retryMakeTweetBodyCount + '\n');
		System.out.print("bodyLength: " + tweetBody.length() + '\n');
		System.out.print(tweetBody);
	}
}
