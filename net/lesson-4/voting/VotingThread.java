package voting;

import gui.ints.*;

import java.io.IOException;
import java.net.*;

public class VotingThread extends Thread 
{
	IntField field;
	int count;
	DatagramSocket socket;
	
	public VotingThread(int port)throws Exception
	{
		field = new IntField();
		socket = new DatagramSocket(port);
	}
	
	public void run()
	{
		DatagramPacket packet = new DatagramPacket(new byte[0],0);
		while (true)
		{
			try 
			{
				socket.receive(packet);
				field.setInt(++count);
			} 
			catch (Exception e){}
		}
	}
	
	public void finish()
	{
		socket.close();
		stop();
	}
}
