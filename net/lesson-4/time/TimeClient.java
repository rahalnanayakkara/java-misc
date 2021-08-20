package time;

import network.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class TimeClient
{
	public TimeClient() throws Exception
	{
		String hostAddress = "localhost";
		DatagramSocket socket = new DatagramSocket();
		Datagram.send(socket, new byte[1000], hostAddress, 2000);
		byte[] data = Datagram.receive(socket, 1000);
		
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream objIn = new ObjectInputStream(in);
		
		Date d = (Date)objIn.readObject();
		io.Screen.show("", d.toGMTString());
	}
	
	public static void main(String[] args) throws Exception
	{
		new TimeClient();
	}
}
