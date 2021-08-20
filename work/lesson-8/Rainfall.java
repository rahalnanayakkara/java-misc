import io.*;

public class Rainfall
{
	public static String town;
	public static int[] rainfall = new int[12];
	public static String[] month = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	
	public static void input()
	{
		town = Keyboard.readString("Enter Town name");
		for(int x=0; x<12; x++) rainfall[x] = Keyboard.readInt("Rainfall of "+month[x]);
		save();
	}
	
	public static void save()
	{
		WriteFile.open("rainfall\\"+town+".dat");
		WriteFile.writeString(town);
		for(int x=0; x<12; x++) WriteFile.writeInt(rainfall[x]);
		WriteFile.close();
	}
	
	public static void load()
	{
		ReadFile.open("rainfall\\"+town+".dat");
		town = ReadFile.readString();
		for(int x=0; x<12; x++) rainfall[x]=ReadFile.readInt();
		ReadFile.close();
	}
	
	public static void show()
	{
		town = Keyboard.readString("Enter City Name");
		load();
		for(int x=0; x<12; x++) Screen.show("Rainfall of "+month[x]+" :",rainfall[x]);
	}
	
	public static void main(String[] args)
	{
		loop: while(true)
		{
			int n = Menu.select("New City","Show city");
			switch (n)
			{
				case 0 : break loop;
				case 1 : input(); break;
				case 2 : show(); break;
			}
		}
	}
}
