package voting;
import gui.*;
import java.io.*;
import gui.buttons.*;
import gui.ints.*;
import java.net.*;

public class ResultsServer extends GridForm implements ButtonListener 
{
	String[] names = {"Lal","Kumari","Raj"};
	VotingThread[] thread = new VotingThread[3];
	
	public ResultsServer() throws Exception
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
		
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		DataOutputStream dataOut = new DataOutputStream(outBytes);
		DatagramSocket resultSocket = new DatagramSocket(1000);
		DatagramPacket resultPacket = new DatagramPacket(new byte[1000],1000);
		
		while(true)
		{
			resultSocket.receive(resultPacket);
			outBytes.reset();
			dataOut.writeInt(3);
			for(int x=0; x<3; x++)
			{	
				dataOut.writeUTF(names[x]);
				dataOut.writeInt(thread[x].count);
			}
			resultPacket.setData(outBytes.toByteArray());
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
		new ResultsServer();
	}


}
