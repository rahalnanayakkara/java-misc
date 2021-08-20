package people;

import network.DataSocket;
import io.Keyboard;
import io.Menu;
import io.Screen;

public class PeopleClient 
{
	static String remoteHost = "localHost";
	
	public static void register() throws Exception
	{
		String name = Keyboard.readString("Name :");
		String nic = Keyboard.readString("NIC :");
		DataSocket socket = new DataSocket(remoteHost,2000);
		socket.out.writeUTF(nic);
		socket.out.writeUTF(name);
		socket.close();
	}
	
	public static void query() throws Exception
	{
		String nic = Keyboard.readString("NIC :");
		DataSocket socket = new DataSocket(remoteHost,2001);
		socket.out.writeUTF(nic);
		String name = socket.in.readUTF();
		socket.close();
		Screen.show("Name :", name);
	}
	
	public static void main(String[] args)
	{
		loop : while (true) try
		{
			switch(Menu.select("Register","Query"))
			{
			case 0: break loop;
			case 1: register(); break;
			case 2: query(); break;
			}
		}
		catch(Exception e)
		{
			Screen.show("Operation", "Failed");
		}
	}
}
