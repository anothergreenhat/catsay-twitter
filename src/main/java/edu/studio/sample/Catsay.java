package edu.studio.sample;

import jfortune.Cookie;

public class Catsay {

    private String tweetStream;

    public void clearStream() {
        tweetStream = "";
    }

    public Catsay() {
        tweetStream = "";
    }

    private void printCat() {
        // tweetStream += " \\ ^ /\\\n ) ( ')\n ( / )\njgs \\(__)|";
        tweetStream += "      \\  ^    /\\\r\n" + "        )    (   ' )\r\n" + "       (   /     )\r\n"
                + "jgs   \\(___) |";
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
        char[] buf = new char[1000];
        int col = 0, max_col = 0, i = 0, tabcnt = 0;

        for (char c : cookie.getText().toCharArray()) {
            buf[i] = c;
            if (c == '\t') {
                col = ((col + 8) / 8 * 8);
                tabcnt++;
            }
            if (c == '\n') {
                max_col = col > max_col ? col : max_col;
                col = 0;
            }
            if (c == cookie.getText().charAt(cookie.length() - 1)) {
                max_col = col > max_col ? col : max_col;
            }
            col++;
            i++;
        }
        buf[i + 1] = '\0';
        max_col += 3;

        // tweetStream += ' ';
        // printLoop('_', max_col);
        // tweetStream += ' ';
        tweetStream += "\n/";
        printLoop('ˉ', max_col + (max_col / 3) - 2);
        // printLoop(' ', max_col);
        tweetStream += "\\\n| ";
        i = 0;
        col = 0;
        int lineno = 0, tabline = -1;

        while (true) {
            if (buf[i] == '\t') {
                tabcnt++;
                tabline = lineno;
                col = ((col + 8) / 8 * 8);
            }
            if ((buf[i] == '\n' && (max_col > col)) || (buf[i + 1] == '\0')) {
                if (buf[i] == '\n') {
                    if (tabline == lineno)
                        printLoop(' ', max_col - col + 2);
                    else if (lineno == 0)
                        printLoop(' ', max_col - col - 1);
                    else
                        printLoop(' ', max_col - col);
                    tweetStream += '|';
                    tweetStream += buf[i];
                }
                else if (buf[i + 1] == '\0') {
                    tweetStream += buf[i];
                    if (tabline == lineno)
                        printLoop(' ', max_col - col + 1);
                    else if (lineno == 0)
                        printLoop(' ', max_col - col - 2);
                    else
                        printLoop(' ', max_col - col - 1);
                    tweetStream += "|\n";
                    break;
                }
                tweetStream += "| ";
                col = 0;
                lineno++;
            }
            else {
                tweetStream += buf[i];
            }
            i++;
            col++;
        }
        tweetStream += '\\';
        printLoop('_', max_col - (max_col / 14) - 1);
        tweetStream += "/\n";

    }

    public String makeTweet(Cookie cookie) {
        generateBox(cookie);
        printCat();
        return tweetStream;
    }

}
