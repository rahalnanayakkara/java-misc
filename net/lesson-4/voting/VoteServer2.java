package voting;
import gui.*;
import gui.buttons.*;
import gui.ints.*;
import java.net.*;

public class VoteServer2 extends GridForm implements ButtonListener 
{
	String[] names = {"Lal","Kumari","Raj"};
	VotingThread[] thread = new VotingThread[3];
	
	public VoteServer2() throws Exception
	{
		super("Vote Server",10,10,30,30,10,6);
		for(int i=1; i<=3; i++)
		{
			addLabel(names[i-1],3*i-2,1,2,1);
			thread[i-1] = new VotingThread(1000+i);
			add(thread[i-1].field,3*i-2,2,2,1);
		}
		
		add(new IndexedButton(1,"Stop Voting",this,false),1,4,8,1);
		
		for(VotingThread t : thread) t.start();
		
		DatagramSocket resultSocket = new DatagramSocket(1000);
		DatagramPacket resultPacket = new DatagramPacket(new byte[3],3);
		byte[] results = new byte[3];
		while(true)
		{
			resultSocket.receive(resultPacket);
			for(int x=0;x<3;x++) results[x] = (byte)thread[x].count;
			resultPacket.setData(results);
			resultSocket.send(resultPacket);
		}
	}
	
	public void buttonClicked(int index)
	{
		setTitle("Voting Stopped");
		for(VotingThread t : thread) t.finish();
	}

	public static void main(String[] args) throws Exception
	{
		new VoteServer2();
	}


}
