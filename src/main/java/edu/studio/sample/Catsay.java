package edu.studio.sample;

import jfortune.Cookie;

public class Catsay
{

	private String tweetStream;

	public void clearStream()
	{
		tweetStream = "";
	}

	public Catsay()
	{
		tweetStream = "";
	}

	private void printCat()
	{
		// tweetStream += " \\ ^ /\\\n ) ( ')\n ( / )\njgs \\(__)|";
		tweetStream += "      \\  ^    /\\\r\n" + "        )    (   ' )\r\n" + "       (   /     )\r\n"
				+ "jgs   \\(___) |";
	}

	private void printLoop(char c, int cnt)
	{
		if (cnt > 0)
		{
			tweetStream += c;
			cnt--;
			printLoop(c, cnt);
		}
		else
			return;
	}

	private void generateBox(Cookie cookie)
	{
		char[] buf = new char[1000];
		int col = 0, max_col = 0, i = 0, tabcnt = 0;
		char oldSpaceC = 'a';
		for (char c : cookie.getText().toCharArray())
		{
			if (c == ' ' && oldSpaceC == ' ' || c == '\t')
			{
				oldSpaceC = c;
				if (c == '\t')
				{
					buf[i] = '\n';
					col = 0;
				}
				continue;
			}
			if (c == '\n')
			{
				buf[i] = ' ';
				col++;
				i++;
				oldSpaceC = c;
				continue;
			}
			buf[i] = c;
			if (col == 29)
			{
				max_col = col > max_col ? col : max_col;
				col = 0;
				if (c == ' ' || c == '\t')
				{
					buf[i] = '\n';
					col = 0;
				}
				else
				{
					int j = i;
					String overhangingWord = "";
					while (j > 0 && (buf[j] != ' ' || buf[j] != '\t'))
					{
						overhangingWord = buf[j] + overhangingWord;
						j--;
						if (buf[j] == ' ' || buf[j] == '\t')
							break;
					}
					buf[j] = '\n';
					col = 0;
					for (int k = 0; k < overhangingWord.length(); k++, j++)
					{
						buf[i + j] = overhangingWord.charAt(k);
					}
				}
			}
			if (c == cookie.getText().charAt(cookie.length() - 1))
			{
				max_col = col > max_col ? col : max_col;
			}

			if (i > 4 && buf[i] == '-' && buf[i - 1] == '-')
			{
				if (buf[i - 2] == ' ')
				{
					buf[i - 2] = '\n';
					col = 0;
				}
			}

			col++;
			i++;
			oldSpaceC = c;
		}
		buf[i + 1] = '\0';
		max_col += 3;

		tweetStream += "~~~~~~~~~~~~~~~~~~~~~~~~~~~";
		tweetStream += "\n ";

		i = 0;
		col = 0;
		int lineno = 0, tabline = -1;
		while (true)
		{
			if (buf[i] == '\t')
			{
				tabcnt++;
				tabline = lineno;
				col = ((col + 8) / 8 * 8);
			}
			if ((buf[i] == '\n' && (max_col > col)) || (buf[i + 1] == '\0'))
			{
				if (buf[i] == '\n')
				{
					if (i > 0 && buf[i - 1] == '\n' && buf[i] != ' ')
						tweetStream += ' ';
					tweetStream += buf[i];
				}
				else if (buf[i + 1] == '\0')
				{
					tweetStream += buf[i];
					tweetStream += '\n';
					break;
				}
				col = 0;
				lineno++;
			}
			else
			{
				if (i > 0 && buf[i - 1] == '\n' && buf[i] != ' ')
					tweetStream += ' ';
				tweetStream += buf[i];
			}
			i++;
			col++;
		}
		tweetStream += "~~~~~~~~~~~~~~~~~~~~~~~~~~~";
		tweetStream += '\n';

	}

	public String makePlainTextBody(Cookie cookie)
	{
		generateBox(cookie);
		return tweetStream;
	}

	public boolean isTweetErrorFree(String cookiePlainText)
	{
		String tmpTweetStream = tweetStream;
		tmpTweetStream = tmpTweetStream.replaceAll("~+|[\n\r\t]+", "");
		tmpTweetStream = tmpTweetStream.replaceFirst(" ", "");
		tmpTweetStream = tmpTweetStream.replaceAll("(  )+", " ");

		cookiePlainText = cookiePlainText.replaceAll("[\n\r\t]+", " ");
		cookiePlainText = cookiePlainText.replaceAll("~+", "");
		cookiePlainText = cookiePlainText.replaceAll("(  )+", " ");

		System.out.println(tmpTweetStream);
		System.out.println(cookiePlainText);
		return tmpTweetStream.equals(cookiePlainText);
	}

	public void deleteLastChar()
	{
		String tmpTweetStream = tweetStream;
		tmpTweetStream = tmpTweetStream.replaceAll("~+|[\n\r\t]+", "");
		tmpTweetStream = tmpTweetStream.replaceAll("(  )+", " ");

		tmpTweetStream = tmpTweetStream.substring(0, tmpTweetStream.length() - 1);
		tweetStream = tmpTweetStream;
	}

	public void outputStream()
	{
		System.out.println(tweetStream);
	}

	public String makeTweet(Cookie cookie)
	{
		generateBox(cookie);
		printCat();
		return tweetStream;
	}

}
