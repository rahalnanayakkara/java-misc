package test;

import java.awt.Color;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.JOptionPane;

import graphics.*;
import gui.*;
import io.Screen;

public class TestForm extends GridForm 
{
	DrawingCanvas dc;
	BufferedImage img;
	File file = new File("D:\\java\\ui\\maze\\test\\sample_maze.jpg");
	int imgWidth, imgHeight;
	
	public TestForm()
	{
		super("Test",10,10,80,80,20,20);
		
		add(dc=new DrawingCanvas(),1,1,500/colWidth+1,500/rowHeight+1);
		dc.setBackground(Color.WHITE);
		
		try
		{
			img = ImageIO.read(file);
		}
		catch(IOException e) 
		{
			Screen.show("", "ERROR");
			Screen.show("", ""+file.exists());
		}
		
		imgWidth = img.getWidth();
		imgHeight = img.getHeight();
		
		dc.g.drawImage(img, 1, 1, null);
	}
	
	public static void main(String[] args) 
	{
		new TestForm();
	}

}
