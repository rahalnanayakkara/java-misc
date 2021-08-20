package voting;
import java.net.*;

import network.Datagram;
import gui.*;
import gui.buttons.*;
import gui.validation.Validator;

public class VoteClient extends GridForm implements ButtonListener
{
	String[] names = {"Lal","Kumari","Raj"};
	
	public VoteClient()
	{
		super("Voting",10,10,30,15,10,3);
		for(int index=1; index<=3; index++)
			add(new IndexedButton(index,names[index-1],this),index*3-2,1,2,1);
		Validator.valid =true;
	}
	
	public void buttonClicked(int index)
	{
		try
		{
			Datagram.send(new byte[0], "192.168.1.10", 1000+index);
		}
		catch (Exception e){}
	}
	
	public static void main (String[] args)
	{
		new VoteClient();
	}
}
