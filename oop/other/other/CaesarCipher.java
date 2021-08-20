package other;

import io.Screen;

public class CaesarCipher 
{
	static char[] a = //{'Y','O','I','W','L','Y','W','Q','Y','P','U','O','I','K'};
			{'B','L','R','D','O','B','D','J','B','K','F','L','R','P'};
	
	public static void main(String[] args)
	{
		for(int y=1; y<=25; y++)
		{
			for(int x=0; x<a.length; x++)
				a[x]=(a[x]=='Z')?a[x]='A':++a[x];
			Screen.display((a));
		}
	}
	
	public static char[] reverse(char[] c)
	{
		char[] b = new char[c.length];
		for(int x=0; x<b.length; x++) b[x] = c[c.length-1-x];
		return b;
	}
	
}