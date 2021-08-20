import gui.*;
import graphics.*;

import javax.swing.*;

import java.awt.*;

public class ExampleGraph extends GridForm 
{
	Graph g;
	
	public ExampleGraph()
	{
		super("Example Graph",10,10,50,60,20,20);
		add( g= new Graph(),1,1,18,18);
		JOptionPane.showMessageDialog(this, "Start");
		g.setBackground(Color.WHITE);
		g.setupCoordinateSystem(-4, 4, 35, -15);
		
		g.grid();
		g.xGrid(-3, 3, 1, -12, 27);
		g.yGrid(-10, 25, 5, -3.2, 3.2);
		
		g.axis();
		g.xAxis(-3.2, 3.2);
		g.yAxis(-12, 27);
		g.xAxisNumbers(-3, 3, 1, -1);
		g.yAxisNumbers(-10, 25, 5, -0.1);
		
		g.data();
		g.point(-3, 20);
		for( double x= -2.98; x<3.02; x+=0.02)
			g.join(x, 2*x*x-3*x-7);
		
		g.text();
		g.text("x", 3.4, 1, Graph.LEFT, Graph.RIGHT);
		g.text("y", 0, 28, Graph.CENTER, Graph.BOTTOM);
		
		g.title();
		g.text(" y= 2x - 3x -7", 0, 31, Graph.CENTER, Graph.BOTTOM);
		g.setFont("Courier", Font.BOLD, 12);
		g.text("2",-0.1,32);
	}
	
	public static void main(String[] args)
	{
		new ExampleGraph();
	}
}
