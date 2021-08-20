package voting;
import io.Screen;

import java.io.*;
import java.net.*;

public class ResultClient 
{
	static String[] names = {"Rahal","Madhavee","Ravindu"};
	
	public static void main (String[] args) throws Exception
	{
		DatagramSocket socket = new DatagramSocket();
		InetAddress address = InetAddress.getByName("192.168.1.10");
		DatagramPacket packet = new DatagramPacket(new byte[1000],1000,address,1000);
		socket.send(packet);
		Screen.show("SENT", "");
		socket.receive(packet);
		Screen.show("RECIEVED", "");
		socket.close();
		
		byte[] results = packet.getData();
		
		ByteArrayInputStream inBytes = new ByteArrayInputStream(results);
		DataInputStream dataIn = new DataInputStream(inBytes);
		
		int n = dataIn.readInt();
		for(int x=0; x<n; x++) Screen.show(dataIn.readUTF(), dataIn.readInt());
			
	}
}
