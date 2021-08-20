package io;
import java.io.*;

import number.ZeroValueException;

public class Keyboard
{
  private static DataInputStream k =new DataInputStream(System.in);
  
  public static String readString(String message)
  { 
  	String s = "";
  	try
  	{
  			System.out.print(message+" ");
  			s = k.readLine();
 		
  	} catch ( IOException e) {}
  	return s;
  	
  }
	public static int readInt(String message)
	{
		int i;
		loop : while(true)
		{
			try 
			{
				i=Integer.parseInt(readString(message));
				break loop;
			}
			catch(NumberFormatException e) 
			{
				Screen.show("Re-enter an Integer","");
			}
		}
		return i;
	}
	
	public static int readInt(String message, int min, int max)
	{
		int i;
		loop : while(true)
		{
			try 
			{
				i=Integer.parseInt(readString(message));
				if (i>=min&&i<=max)break loop;
				else throw new ZeroValueException();
			}
			catch(NumberFormatException e) 
			{
				Screen.show("Re-enter an Integer between", min+" and "+max);
			}
			catch(ZeroValueException e)
			{
				Screen.show("value must be between", min+" and "+max);
			}
		}
		return i;
	}
	
	public static long readLong(String message)
	{
		return Long.parseLong(readString(message));
	}
	
	
	public static float readFloat(String message)
	{
		 return Float.parseFloat(readString(message));
	}
	
	public static double readDouble(String message)
	{
		double d;
		loop : while(true)
		{
			try 
			{
				d=Double.parseDouble(readString(message));
				break loop;
			}
			catch(NumberFormatException e) 
			{
				Screen.show("Re-enter a Number","");
			}
		}
		return d;
	}
	
	public static boolean confirm(String message)
	{
		while(true)
		{
			String s = Keyboard.readString(message);
			s = s.toUpperCase();
			if (s.equals("Y")) return true;
			if (s.equals("N")) return false;
			System.out.println("You must respond Y or N");
		}
	}
}
  	
  