package currency;

import java.net.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import network.Datagram;
import gui.*;
import gui.adapters.FocusRefuser;
import gui.buttons.*;

public class CurrencyClient extends GridForm implements ButtonListener, ActionListener
{
	String hostName = "localhost";
	
	DatagramSocket socket;
	DatagramPacket getPacket, refreshPacket;
	
	JComboBox cmbCurrency;
	JTextField txtBuy, txtSell;
	
	public CurrencyClient() throws Exception
	{
		super("Currency Client",10,10,30,40,7,9);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addLabel("Currency",1,1,2,1);
		addLabel("Buying Rate(Rs.)",1,3,2,1);
		addLabel("Selling Rate(Rs.)",1,5,2,1);
		add(new IndexedButton(1,"Get",this,false),1,7,2,1);
		add(new IndexedButton(2,"Refresh",this,false),4,7,2,1);
		add(cmbCurrency = new JComboBox(),4,1,2,1);
		add(txtBuy = new JTextField(),4,3,2,1);
		add(txtSell = new JTextField(),4,5,2,1);
		FocusRefuser.plugTo(txtBuy,txtSell);
		
		cmbCurrency.addActionListener(this);
		
		WindowAdapter window = new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				socket.close();
				dispose();
			}
		};
		
		addWindowListener(window);
		
		socket = new DatagramSocket();
		getPacket = new DatagramPacket(new byte[1000],1000,InetAddress.getByName(hostName),2001);
		refreshPacket = new DatagramPacket(new byte[1000],1000,InetAddress.getByName(hostName),2000);
		
		refresh();
	}
	
	public void buttonClicked(int index)
	{
		try
		{
			switch(index)
			{
				 case 1 : get(); break;
				 case 2 : refresh(); break;
			}
		}
		catch(Exception e){}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		txtBuy.setText("");
		txtSell.setText("");
	}
	
	public void get() throws IOException
	{	
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(outBytes);
		
		out.writeUTF((String)cmbCurrency.getSelectedItem());
		getPacket.setData(outBytes.toByteArray());
		socket.send(getPacket);
		getPacket.setData(new byte[1000]);
		socket.receive(getPacket);

		
		ByteArrayInputStream inBytes = new ByteArrayInputStream(getPacket.getData());
		DataInputStream in = new DataInputStream(inBytes);

		in.readUTF();
		txtBuy.setText(in.readUTF());
		txtSell.setText(in.readUTF());


		in.close();
		inBytes.close();
		out.close();
		outBytes.close();
	}
	
	public void refresh() throws Exception
	{
		socket.send(refreshPacket);
		socket.receive(refreshPacket);
		
		ByteArrayInputStream inBytes = new ByteArrayInputStream(refreshPacket.getData());
		DataInputStream in = new DataInputStream(inBytes);
		
		int n = in.readInt();
		cmbCurrency.removeAllItems();
		for(int a=0; a<n; a++) cmbCurrency.addItem(in.readUTF());
		
		in.close();
		inBytes.close();
	}
	
	public static void main(String[] args) throws Exception
	{
		new CurrencyClient();
	}
}
