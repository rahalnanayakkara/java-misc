package io;
public class Menu 
{
	public static int select(String...options)
	{
		int n = options.length;
		Screen.show("0.", "Exit");
		for(int x=1; x<=n; x++) Screen.show(x+".", options[x-1]);
		return Keyboard.readInt("Selection :",0,n);
	}
	
	public static int select(String options)
	{
		return select(options.split(","));
	}
}
