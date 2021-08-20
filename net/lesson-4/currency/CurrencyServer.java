package currency;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

import gui.*;
import gui.adapters.FocusRefuser;
import gui.buttons.*;

import javax.swing.*;

public class CurrencyServer extends GridForm implements ButtonListener, ActionListener
{
	Vector<Currency> currency;
	int n;
	
	JComboBox cmbCurrency;
	JTextField txtBuy, txtSell;
	
	ExchangeRateThread t1;
	CurrencyListThread t2;
	
	AddDialog addDialog;
	ChangeDialog changeDialog;
	
	public CurrencyServer() throws Exception
	{
		super("Currency Server",10,10,30,40,7,9);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addLabel("Currency",1,1,2,1);
		addLabel("Buying Rate(Rs.)",1,3,2,1);
		addLabel("Selling Rate(Rs.)",1,5,2,1);
		add(new IndexedButton(1,"Add",this,false),1,7,2,1);
		add(new IndexedButton(2,"Change",this,false),4,7,2,1);
		add(cmbCurrency = new JComboBox(),4,1,2,1);
		add(txtBuy = new JTextField(),4,3,2,1);
		add(txtSell = new JTextField(),4,5,2,1);
		FocusRefuser.plugTo(txtBuy,txtSell);
		
		currency = new Vector<Currency>();
		addDialog = new AddDialog(this);
		changeDialog = new ChangeDialog(this);
		
		cmbCurrency.addActionListener(this);
		
		t1 = new ExchangeRateThread();
		t1.start();
		
		t2 = new CurrencyListThread();
		t2.start();
		
		WindowAdapter window = new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				t2.terminate();
				t1.terminate();
				dispose();
			}
		};
		
		addWindowListener(window);
	}
	
	public void buttonClicked(int index)
	{
		switch(index)
		{
			case 1: addDialog.setVisible(true); break;
			case 2: 
				changeDialog.cmbCurrency.setSelectedIndex(cmbCurrency.getSelectedIndex());
				changeDialog.setVisible(true); break;
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		refresh();
	}
	
	public void addCurrency(Currency currency)
	{
		this.currency.add(currency);
		cmbCurrency.addItem(currency);
		changeDialog.cmbCurrency.addItem(currency);
		n++;
	}
	
	public void refresh()
	{
		Currency selected = (Currency)cmbCurrency.getSelectedItem();
		txtBuy.setText(selected.getBuyingRate()+"");
		txtSell.setText(selected.getSellingRate()+"");
	}
	
	public class ExchangeRateThread extends Thread
	{
		DatagramSocket socket;
		DatagramPacket packet;
		ByteArrayOutputStream outBytes;
		DataOutputStream out;
		
		ExchangeRateThread() throws Exception
		{
			socket = new DatagramSocket(2001);
			packet = new DatagramPacket(new byte[1000],1000);
			outBytes = new ByteArrayOutputStream();
			out = new DataOutputStream(outBytes);
		}
		
		public void run()
		{
			while (true) try
			{
				outBytes.reset();
				socket.receive(packet);
				
				ByteArrayInputStream inBytes = new ByteArrayInputStream(packet.getData());
				DataInputStream in = new DataInputStream(inBytes);
				
				String name = in.readUTF();
				
				for(Currency c : currency) if(c.getName().equals(name))
				{
					out.writeUTF(name);
					out.writeUTF(c.getBuyingRate()+"");
					out.writeUTF(c.getSellingRate()+"");
					packet.setData(outBytes.toByteArray());
					socket.send(packet);
					break;
				}

				
				in.close();
				inBytes.close();
			}
			catch (Exception e){}
		}
		
		public void terminate()
		{
			try 
			{
				out.close();
				outBytes.close();
			} catch (IOException e) {}
			socket.close();
			stop();
		}
	}
	
	public class CurrencyListThread extends Thread
	{
		DatagramSocket socket;
		DatagramPacket packet;
		ByteArrayOutputStream outBytes;
		DataOutputStream out;
		
		public CurrencyListThread() throws Exception
		{
			socket = new DatagramSocket(2000);
			packet = new DatagramPacket(new byte[1000],1000);
			outBytes = new ByteArrayOutputStream();
			out = new DataOutputStream(outBytes);
		}
		
		public void run()
		{
			while (true) try
			{
				outBytes.reset();
				socket.receive(packet);
				out.writeInt(currency.size());
				for(Currency c: currency) out.writeUTF(c.getName());
				packet.setData(outBytes.toByteArray());
				socket.send(packet);
			}
			catch (Exception e) {}
		}
		
		public void terminate()
		{
			try
			{
				out.close();
				outBytes.close();
			}catch(IOException e){}
			socket.close();
			stop();
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		new CurrencyServer();
	}
}
