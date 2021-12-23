package edu.studio.sample;

import org.junit.jupiter.api.Test;

import jfortune.Cookie;
import jfortune.Fortune;

class SampleTest {

    @Test
    void testGetFortuneWithLength() {
        Fortune fortune = new Fortune();
        fortune.setShortLength(140);
        Cookie cookie = fortune.getShortCookie();

        System.out.println(cookie.getText());
        System.out.println("Num chars: " + cookie.getText().length());
    }

    @Test
    void testPutCatAndFortuneInString() {
        Fortune fortune = new Fortune();
        fortune.setShortLength(140);
        Cookie cookie = fortune.getShortCookie();

        Catsay catPrint = new Catsay();
        String tweetBody = catPrint.makeTweet(cookie);
        System.out.println(tweetBody + "\nNum chars: " + tweetBody.length());
    }

}
