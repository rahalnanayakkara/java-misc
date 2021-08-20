package messaging;

import gui.*;
import gui.lists.*;
import gui.buttons.*;

import javax.swing.*;

import network.*;

import java.awt.event.*;
import java.net.*;

public class Messaging extends GridForm implements ButtonListener, KeyListener
{
	JTextField txtSend;
	DataList history;
	DatagramSocket receiveSocket;
	String hostName;
	
	public Messaging() throws Exception
	{
		super("Chat",10,10,30,60,13,12);
		add(history = new DataList(7),1,1,11,8);
		add(txtSend = new JTextField(),1,10,9,1);
		add(new IndexedButton(1,"SEND",this,false),10,10,2,1);
		txtSend.addKeyListener(this);
		history.scrollWith(true);
		
		receiveSocket = new DatagramSocket(4000);
		
		hostName = JOptionPane.showInputDialog(this, "Partner's Address");
		setTitle("Chat - "+hostName);
		
		while (true) try
		{
			String message = new String(Datagram.receive(receiveSocket, 1000));
			addMessage("Them",message);
		}
		catch (Exception e){}
	}
	
	public void send()
	{
		String message = txtSend.getText();
		if (message.equals("")) return;
		addMessage("You",message);
		try
		{
			Datagram.send(message.getBytes(), hostName, 4000);
			txtSend.setText("");
		}
		catch (Exception e){}
	}
	
	public void buttonClicked(int index)
	{
		send();
	}	
	
	public synchronized void addMessage(String sender, String message)
	{
		history.add(sender+" : "+message);
	}
	

	public void keyPressed(KeyEvent arg0) {}
	public void keyReleased(KeyEvent arg0) {}
	
	public void keyTyped(KeyEvent e) 
	{
		if (e.getKeyChar()==KeyEvent.VK_ENTER) send();
	}
	
	public static void main(String[] ars) throws Exception
	{
		new Messaging();
	}
}
