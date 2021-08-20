package sendFile;

import io.*;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.net.*;

import gui.*;
import gui.buttons.*;

import javax.swing.*;

import network.*;

public class FileServer extends GridForm implements ButtonListener 
{
	JFileChooser fileChooser;
	JTextField txtSave;
	JProgressBar progress;
	IndexedButton btnReceive, btnCancel, btnChoose;
	DataSocket socket;
	ServerSocket serverSocket;
	String fileName;
	
	public FileServer() throws Exception
	{
		super("Receive File",10,10,30,40,18,7);
		addLabel("Save As",2,1,4,1);
		txtSave = addTextField(7,1,8,1);
		add(btnChoose = new IndexedButton(3,"?",this,false),15,1,1,1);
		add(btnCancel = new IndexedButton(2,"Cancel",this,false),2,3,6,1);
		add(btnReceive = new IndexedButton(1,"Receive",this,false),10,3,6,1);
		add(progress = new JProgressBar(),2,5,14,1);
		progress.setForeground(new Color(100,200,100));
		fileChooser = new JFileChooser();
		
		btnReceive.setEnabled(false);
		btnChoose.setEnabled(false);
		btnCancel.setEnabled(false);
		txtSave.setEnabled(false);
		progress.setIndeterminate(true);
		
		serverSocket = new ServerSocket(2000);
		socket = new DataSocket(serverSocket.accept());
		
		String inFilePath = socket.in.readUTF();
		File inFile = new File(inFilePath);
		fileName = inFile.getName();
		
		progress.setIndeterminate(false);
		btnChoose.setEnabled(true);
		txtSave.setEnabled(true);
	}
	
	public void receive() throws Exception
	{
		socket.out.writeInt(0);
		File file = new File(txtSave.getText());
		FileOutputStream fos = new FileOutputStream(file);
		int total = socket.in.readInt();
		progress.setMaximum(total);
		int count=1;
		while (true)
		{
			byte[] b = new byte[1024];
			socket.in.read(b);
			fos.write(b);
			if(socket.in.readBoolean()) break;
			progress.setValue(count);
			count++;
		}
		fos.close();
	}
	
	public void buttonClicked(int index)
	{
		if(index==1)
		{
			btnReceive.setEnabled(false);
			btnChoose.setEnabled(false);
			btnCancel.setEnabled(true);
			txtSave.setEnabled(false);
			try
			{
				receive();
			}
			catch(Exception e)
			{
				Screen.show("Transmission", "Error");
			}
			btnReceive.setEnabled(true);
			btnChoose.setEnabled(true);
			btnCancel.setEnabled(false);
			txtSave.setEnabled(true);
			progress.setValue(0);
		}
		if(index==2)
		{
			btnReceive.terminate();
			btnReceive.setEnabled(true);
			btnChoose.setEnabled(true);
			btnCancel.setEnabled(false);
			txtSave.setEnabled(true);
			progress.setValue(0);
		}
		if(index==3&&fileChooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
		{
			txtSave.setText(fileChooser.getSelectedFile().getParent()+"\\"+fileName);
			btnReceive.setEnabled(true);
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		new FileServer();
	}
}
