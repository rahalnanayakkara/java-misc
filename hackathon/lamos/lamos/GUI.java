package lamos;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.imageio.*;
import javax.swing.*;

import graphics.*;
import gui.*;
import network.Datagram;

public class GUI extends GridForm 
{
	JTextField txtStrain1, txtStrain2, txtStrain3;
	DatagramSocket socket;
	String rawData;
	int strain1, strain2, strain3;
	int Dstrain1, Dstrain2, Dstrain3;
	int nstrain1, nstrain2, nstrain3;
	MapWindow mapWindow;
	DataGraph dataGraph;
	DataWindow dataWindow;
	GridForm graphForm;
	LinkedList<Integer> strainData1, strainData2, strainData3;
	int threshold=10;
	Color defaultColor;
	boolean initial=true, second = false;
	long count;
	
	public GUI() throws Exception
	{
		super("LAMOS",0,0,100,100,1,1);
		
		strainData1 = new LinkedList<Integer>();
		strainData2 = new LinkedList<Integer>();
		strainData3 = new LinkedList<Integer>();
		strainData1.add(0);
		strainData2.add(0);
		strainData3.add(0);
		
		socket = new DatagramSocket(2000);
		
		mapWindow = new MapWindow();
		dataWindow = new DataWindow();
		dataGraph = new DataGraph();
		graphForm = new GridForm("Graph",5,40,50,45,20,20);
		dataGraph = new DataGraph();
		graphForm.add(dataGraph, 1, 1, 18, 18);
		dataGraph.setBackground(Color.WHITE);
		dataGraph.setupCoordinateSystem(0, 60, 200, -2);
		

	}
	
	public class DataWindow extends GridForm
	{
		public DataWindow() 
		{
			super("Data",15,5,30,30,6,7);
			
			addLabel("Strain 1",1,1,1,1);
			addLabel("Strain 2",1,3,1,1);
			addLabel("Strain 3",1,5,1,1);
			
			txtStrain1 = addTextField(JTextField.CENTER,3,1,2,1);
			txtStrain2 = addTextField(JTextField.CENTER,3,3,2,1);
			txtStrain3 = addTextField(JTextField.CENTER,3,5,2,1);
			
			defaultColor = container.getBackground();
			
			Appearance.setFont(Appearance.formFont, this);
		}
	}
	
	public class MapWindow extends GridForm
	{
		public MapWindow() throws IOException
		{
			super("Map",60,10,35,75,10,10);
			
			BufferedImage image = ImageIO.read(new File("/mnt/4A86C22586C2117D/java/hackathon/lamos/lamos/map3.png"));
		    JLabel label = new JLabel(new ImageIcon(image));
		    add(label,1,1,8,8);
		}
	}
	
	public class DataGraph extends Graph
	{
		
		public void draw()
		{
		
			data();
			xAxis(0, 60);
			yAxis(2, 200);
			xAxisNumbers(0, 60, 5, 0);
			
			axis();
			point(0,strainData1.get(0));
			for( int x= 1; x<(strainData1.size()<60?strainData1.size():60); x++)
				join(x, strainData1.get(x));
			
			grid();
			point(0,strainData2.get(0));
			for( int x= 1; x<(strainData2.size()<60?strainData2.size():60); x++)
				join(x, strainData2.get(x));
			
			title();
			point(0,strainData3.get(0));
			for( int x= 1; x<(strainData3.size()<60?strainData3.size():60); x++)
				join(x, strainData3.get(x));
		} 
	}
	
	public void getData() throws Exception
	{
		if (count==5)
		{
			nstrain1 = strain1;
			nstrain2 = strain2;
			nstrain3 = strain3;
		}
		
		rawData= new String(Datagram.receive(socket, 1024));


		strain1 = Integer.parseInt(rawData.split("a")[1]);
		strain2 = Integer.parseInt(rawData.split("b")[1]);
		strain3 = Integer.parseInt(rawData.split("c")[1]);
		
		txtStrain1.setText(""+strain1);
		txtStrain2.setText(""+strain2);
		txtStrain3.setText(""+strain3);
		
		if (strainData1.size()==60)	strainData1.remove();
		if (strainData2.size()==60)	strainData2.remove();
		if (strainData3.size()==60)	strainData3.remove();
		
		strainData1.add(strain1);
		strainData2.add(strain2);
		strainData3.add(strain3);
		
		dataGraph.repaint();
		
		Dstrain1=Math.abs(nstrain1 - strain1);
		Dstrain2=Math.abs(nstrain2 - strain2);
		Dstrain3=Math.abs(nstrain3 - strain3);
		
		if ((Dstrain1>threshold||Dstrain2>threshold||Dstrain3>threshold)&&(count>5)) dataWindow.container.setBackground(Color.RED);
		
		count++;
	}
	
	public static void main(String[] args) throws Exception
	{
		GUI gui = new GUI();
		while (true)
			gui.getData();
		
	}
}
