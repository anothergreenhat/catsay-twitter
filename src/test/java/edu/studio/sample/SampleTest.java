package edu.studio.sample;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jfortune.Cookie;
import jfortune.Fortune;

class SampleTest {

    @BeforeEach
    void setUp() {
        System.setProperty("line.separator", "\n");
    }

    @Test
    void testGetFortuneWithLength() {
        Fortune fortune = new Fortune();
        fortune.setShortLength(140);
        Cookie cookie = fortune.getShortCookie();

        // System.out.println(cookie.getText());
        // System.out.println("Num chars: " + cookie.getText().length());
    }

    @Test
    void testPutCatAndFortuneInString() {
        Fortune fortune = new Fortune(new Locale("en"));
        fortune.setShortLength(40);
        Cookie cookie = fortune.getShortCookie();

        Catsay catPrint = new Catsay();
        String tweetBody = catPrint.makeTweet(cookie);
        System.out.println(tweetBody + "\nNum chars: " + tweetBody.length());
        assert (tweetBody.length() <= 240);

        cookie = fortune.getShortCookie();
        catPrint.clearStream();
        tweetBody = catPrint.makeTweet(cookie);
        System.out.println(tweetBody + "\nNum chars: " + tweetBody.length());
        assert (tweetBody.length() <= 240);

        cookie = fortune.getShortCookie();
        catPrint.clearStream();
        tweetBody = catPrint.makeTweet(cookie);
        System.out.println(tweetBody + "\nNum chars: " + tweetBody.length());
        assert (tweetBody.length() <= 240);

        cookie = fortune.getShortCookie();
        catPrint.clearStream();
        tweetBody = catPrint.makeTweet(cookie);
        System.out.println(tweetBody + "\nNum chars: " + tweetBody.length());
        assert (tweetBody.length() <= 240);

        cookie = fortune.getShortCookie();
        catPrint.clearStream();
        tweetBody = catPrint.makeTweet(cookie);
        System.out.println(tweetBody + "\nNum chars: " + tweetBody.length());
        assert (tweetBody.length() <= 240);

        cookie = fortune.getShortCookie();
        catPrint.clearStream();
        tweetBody = catPrint.makeTweet(cookie);
        System.out.println(tweetBody + "\nNum chars: " + tweetBody.length());
        assert (tweetBody.length() <= 240);
    }

}
