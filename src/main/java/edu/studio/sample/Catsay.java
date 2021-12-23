package edu.studio.sample;

import jfortune.Cookie;

public class Catsay {

    private String tweetStream;

    public Catsay() {
        tweetStream = "";
    }

    private void printCat() {
        tweetStream += "      \\ ^  /\\\n       )  ( ')\n      (  /  )\njgs    \\(__)|";
    }

    private void printLoop(char c, int cnt) {
        if (cnt > 0) {
            tweetStream += c;
            cnt--;
            printLoop(c, cnt);
        }
        else
            return;
    }

    private void generateBox(Cookie cookie) {
        String buf = cookie.getText();
        int c, col = 0, max_col = 0, i, tabcnt = 0;

        for (i = 0; i < buf.length();) {
            c = buf.charAt(i);
            if (c == '\t') {
                col = ((col + 8) / 8 * 8);
                tabcnt++;
            }
            if (c == '\n') {
                max_col = col > max_col ? col : max_col;
                col = 0;
            }
            col++;
            i++;
        }
        max_col += 2;

        tweetStream += ' ';
        printLoop('_', max_col);
        tweetStream += ' ';
        tweetStream += "\n/";
        printLoop(' ', max_col);
        tweetStream += "\\\n| ";
        i = 0;
        col = 0;
        tabcnt = 0;
        int lineno = 0, tabline = -1;
        while (buf.charAt(i) != '\0') {
            if (buf.charAt(i) == '\t') {
                tabcnt++;
                tabline = lineno;
                col = ((col + 8) / 8 * 8);
            }
            if ((buf.charAt(i) == '\n' && (max_col > col))) {// || (buf.charAt(i
                                                             // + 1) == '\0')) {
                if (tabline == lineno)
                    printLoop(' ', max_col - col + 2);
                else if (lineno == 0)
                    printLoop(' ', max_col - col - 1);
                else
                    printLoop(' ', max_col - col);
                tweetStream += '|';
                tweetStream += buf.charAt(i);
                if (buf.charAt(i + 1) == '\0')
                    break;
                tweetStream += "| ";
                col = 0;
                lineno++;
            }
            else
                tweetStream += buf.charAt(i);
            i++;
            col++;
        }
        tweetStream += '\\';
        printLoop('_', max_col);
        tweetStream += "/\n";
    }

    public String makeTweet(Cookie cookie) {
        generateBox(cookie);
        printCat();
        return tweetStream;
    }

}
