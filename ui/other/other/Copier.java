package other;

import gui.*;
import gui.buttons.*;
import io.Screen;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class Copier extends GridForm implements ButtonListener
{
	JTextField txtSource, txtDestination;
	JProgressBar progress; 
	IndexedButton btnStart, btnCancel, btnChoose;
	JFileChooser fileChooser;
	Copier copier;
	
	public Copier()
	{
		super("Copy File",10,10,30,40,18,18);
		copier = this;
		addLabel("Source",2,2,4,2);
		addLabel("Destination",2,6,4,2);
		add(btnStart = new IndexedButton(1,"Start",this,false),10,10,6,2);
		add(btnCancel = new IndexedButton(2,"Cancel",this,false),2,10,6,2);
		add(btnChoose = new IndexedButton(3,"?",this,false),15,2,1,2);
		txtSource = addTextField(8,2,7,2);
		txtDestination = addTextField(8,6,8,2);
		add(progress = new JProgressBar(),2,14,14,2);
		btnStart.setEnabled(false);
		btnCancel.setEnabled(false);
		fileChooser = new JFileChooser();
	}
	
	public void copyFile(File original, File copy) throws Exception
	{
		FileInputStream fis = new FileInputStream(original.getAbsoluteFile());
		FileOutputStream fos = new FileOutputStream(copy.getAbsoluteFile());
		long size = original.length();
		int total = (int)(size/1024);
		progress.setMaximum(total);
		int count=1;
		while (true)
		{
			byte[] b = new byte[1024];
			int i = fis.read(b);
			fos.write(b);
			if(i==-1) break;
			progress.setValue(count);
			count++;
		}
		fis.close();
		fos.close();
	}
	
	public void buttonClicked(int index)
	{
		if(index==1)
		{
			btnStart.setEnabled(false);
			btnChoose.setEnabled(false);
			btnCancel.setEnabled(true);
			txtSource.setEnabled(false);
			txtDestination.setEnabled(false);
			try 
			{
				copyFile(new File(txtSource.getText()),new File(txtDestination.getText()));
			}
			catch (Exception e){}
			btnStart.setEnabled(true);
			btnChoose.setEnabled(true);
			btnCancel.setEnabled(false);
			txtSource.setEnabled(true);
			txtDestination.setEnabled(true);
			progress.setValue(0);
		}
		if(index==2)
		{
			btnStart.terminate();
			btnStart.setEnabled(true);
			btnChoose.setEnabled(true);
			btnCancel.setEnabled(false);
			txtSource.setEnabled(true);
			txtDestination.setEnabled(true);
			progress.setValue(0);
		}
		if (index==3&&fileChooser.showOpenDialog(copier)==JFileChooser.APPROVE_OPTION)
		{
			File sourceFile = fileChooser.getSelectedFile();
			txtSource.setText(sourceFile.getAbsolutePath());
			txtDestination.setText(sourceFile.getParent()+"\\Copy Of "+sourceFile.getName());
			btnStart.setEnabled(true);
		}
	}
	
	public static void main(String[] args)
	{
		new Copier();
	}
}
