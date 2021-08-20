import gui.*;
import gui.buttons.*;
import network.*;
import ds.*;

import javax.swing.*;
import java.net.*;

public class Messaging extends GridForm implements ButtonListener
{
	JTextField txtTo;
	JTextArea txtSend, txtReceive;
	DatagramSocket sendSocket, receiveSocket;
	BlockingQueue<String> queue;
	
	public Messaging() throws Exception
	{
		super("Messaging Program",10,10,50,50,13,10);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addLabel("To",1,1,1,1);
		add(txtTo = new JTextField(),2,1,4,1);
		add(txtSend = new JTextArea(),1,3,5,4);
		add(new IndexedButton(1,"Send",this,false),1,8,2,1);
		addLabel("Received",7,1,2,1);
		add(txtReceive = new JTextArea(),7,2,5,5);
		add(new IndexedButton(2,"Next",this,false),7,8,2,1);
		add(new IndexedButton(3,"Close",this,false),10,8,2,1);
		
		sendSocket = new DatagramSocket();
		receiveSocket = new DatagramSocket(1500);
		queue = new BlockingQueue<String>(10);
		
		while (true) try
		{
			queue.in(new String(Datagram.receive(receiveSocket,1000)));
		}
		catch(Exception e){}
	}
	
	public void buttonClicked(int index)
	{
		switch(index)
		{
			case 1: sendMessage(); break;
			case 2: nextMessage(); break;
			case 3: close(); break;
		}
	}
	
	public void nextMessage()
	{
		txtReceive.setText("");
		txtReceive.setText(queue.out());
	}
	
	public void sendMessage()
	{
		try
		{
			String message = txtSend.getText();
			Datagram.send(sendSocket, message.getBytes(), txtTo.getText(), 1500);
			JOptionPane.showMessageDialog(this, "Message Sent");
			txtTo.setText("");
			txtSend.setText("");
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, "Transmission Failed", "Failure Notice", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void close()
	{
		sendSocket.close();
		receiveSocket.close();
		System.exit(0);
	}
	
	public static void main(String[] args) throws Exception
	{
		new Messaging();
	}
}