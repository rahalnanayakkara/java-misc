import io.Screen;

import java.net.*;

public class RecieveByte 
{
	public static void main (String[] args) throws Exception
	{
		DatagramSocket socket = new DatagramSocket(1000);
		DatagramPacket packet = new DatagramPacket(new byte[1],1);
		socket.receive(packet);
		Screen.show("Recieved", packet.getData()[0]);
		socket.close();
	}
}
