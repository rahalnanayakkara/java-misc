import io.Keyboard;

import java.net.*;

public class SendByte 
{

	public static void main(String[] args) throws Exception
	{
		byte[] data = new byte[1];
		data[0] = (byte)Keyboard.readInt("Number");
		
		InetAddress address = InetAddress.getByName("192.168.8.200");
		DatagramPacket packet = new DatagramPacket(data,1,address,1000);
		DatagramSocket socket = new DatagramSocket();
		
		socket.send(packet);
		socket.close();
	}

}
