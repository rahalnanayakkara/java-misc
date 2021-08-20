package sendFile;

import io.Screen;

import java.awt.*;
import java.io.*;

import gui.*;
import gui.buttons.*;

import javax.swing.*;

import network.DataSocket;

public class FileClient extends GridForm implements ButtonListener
{
	JTextField txtSource;
	JProgressBar progress;
	IndexedButton btnSend, btnCancel, btnChoose;
	JFileChooser fileChooser;
	String hostName;
	DataSocket socket;
	
	public FileClient() throws Exception
	{
		super("Send File",10,10,30,40,18,7);
		addLabel("Source File",2,1,4,1);
		txtSource = addTextField(6,1,9,1);
		add(btnChoose = new IndexedButton(3,"?",this,false),15,1,1,1);
		add(btnSend = new IndexedButton(1,"Send",this,false),10,3,6,1);
		add(btnCancel = new IndexedButton(2,"Cancel",this,false),2,3,6,1);
		add(progress = new JProgressBar(),2,5,14,1);
		progress.setForeground(new Color(100,200,100));
		fileChooser = new JFileChooser();
		
		hostName = JOptionPane.showInputDialog(this, "Sender's Address");
		
		socket = new DataSocket(hostName,2000);
		
		btnSend.setEnabled(false);
		btnCancel.setEnabled(false);
	}
	
	public void send() throws Exception
	{
		socket.out.writeUTF(txtSource.getText());
		
		progress.setIndeterminate(true);
		socket.in.readInt();
		progress.setIndeterminate(false);
		
		File file = fileChooser.getSelectedFile();
		
		FileInputStream fis = new FileInputStream(file);
		long size = file.length();
		int total = (int)(size/1024);
		socket.out.writeInt(total);
		progress.setMaximum(total);
		int count=1;
		while (true)
		{
			byte[] b = new byte[1024];
			int i = fis.read(b);
			socket.out.write(b);
			if(i==-1)
			{
				socket.out.writeBoolean(true);
				break;
			}
			socket.out.writeBoolean(false);
			progress.setValue(count);
			count++;
		}
		fis.close();
	}
	
	public void buttonClicked(int index)
	{
		if(index==1)
		{
			progress.setIndeterminate(true);
			btnSend.setEnabled(false);
			btnChoose.setEnabled(false);
			btnCancel.setEnabled(true);
			txtSource.setEnabled(false);
			try 
			{
				send();
			}
			catch (Exception e){}
			btnSend.setEnabled(true);
			btnChoose.setEnabled(true);
			btnCancel.setEnabled(false);
			txtSource.setEnabled(true);
			progress.setValue(0);
		}
		if(index==2)
		{
			btnSend.terminate();
			btnSend.setEnabled(true);
			btnChoose.setEnabled(true);
			btnCancel.setEnabled(false);
			txtSource.setEnabled(true);
			progress.setValue(0);
		}
		if (index==3&&fileChooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
		{
			File sourceFile = fileChooser.getSelectedFile();
			txtSource.setText(sourceFile.getAbsolutePath());
			btnSend.setEnabled(true);
		}
	}
	
	public static void main(String[] srgs) throws Exception
	{
		new FileClient();
	}
}
