package time;

import java.net.*;
import java.util.*;
import java.io.*;

public class TimeServer 
{
	public TimeServer() throws Exception
	{
		DatagramSocket socket = new DatagramSocket(2000);
		DatagramPacket packet = new DatagramPacket(new byte[1000],1000);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream objOut = new ObjectOutputStream(out);
		
		while (true) try
		{
			out.reset();
			socket.receive(packet);
			Date d = new Date();
			objOut.writeObject(d);
			packet.setData(out.toByteArray());
			socket.send(packet);
		}
		catch(Exception e){}
	}
	
	public static void main(String[] args) throws Exception
	{
		new TimeServer();
	}
}
