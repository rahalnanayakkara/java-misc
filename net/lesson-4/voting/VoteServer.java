package voting;
import gui.*;
import gui.ints.*;
import java.net.*;

public class VoteServer extends GridForm 
{
	String[] names = {"Lal","Kumari","Raj"};
	IntField[] fields = new IntField[3];
	int[] count = new int[3];
	
	public VoteServer() throws Exception
	{
		super("Vote Server",10,10,30,20,10,4);
		for(int i=1; i<=3; i++)
		{
			addLabel(names[i-1],3*i-2,1,2,1);
			add(fields[i-1]=new IntField(),3*i-2,2,2,1);
		}
		
		DatagramSocket socket = new DatagramSocket(1000);
		DatagramPacket packet = new DatagramPacket(new byte[1],1);
		
		while(true)
		{
			try
			{
				socket.receive(packet);
				int data = packet.getData()[0];
				fields[data-1].setInt(++count[data-1]);
			}
			catch(Exception e){}
		}
	}

	public static void main(String[] args) throws Exception
	{
		new VoteServer();
	}

}
