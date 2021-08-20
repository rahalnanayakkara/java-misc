package ventilator;

import gui.*;
import gui.buttons.ButtonListener;
import network.Datagram;

import javax.swing.*;

public class Controller extends GridForm implements ButtonListener
{
	JLabel lblImage, lblTxt;
	ImageIcon icon;
	String hostName;

	public Controller()
	{
		super("Ventilator", 10, 10, 80, 90, 100, 100);
		icon = new ImageIcon("E:\\java\\net\\lesson-5\\ventilator\\vent.PNG");
		lblImage = new JLabel(icon);
		addItem(lblImage,1,1,98,98);
		lblTxt = addLabel("Connecting...",10,95,50,5);
		hostName = JOptionPane.showInputDialog(this, "Partner's Address");
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
	
	public static void main(String[] args)
	{
		new Controller();
	}

	@Override
	public void buttonClicked(int index)
	{
		send();
	}
}
