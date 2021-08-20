package news;
import io.*;

import java.net.*;

import network.*;

public class NewsClient 
{
	public static void main(String[] args) throws Exception
	{
		DataSocket socket = new DataSocket("localhost",2000);
		Screen.show("Connected", "");
		String message="";
		while (!message.equals("bye")) try
		{
			message = socket.in.readUTF();
			Screen.show(message, "");
		}
		catch(Exception e){}
		socket.close();
	}
}
