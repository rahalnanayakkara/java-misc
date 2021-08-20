import java.awt.*;
import java.awt.geom.*;

import javax.swing.JOptionPane;

import graphics.*;
import gui.*;

public class DrawingCanvasDemo extends GridForm 
{
	DrawingCanvas dc;
	
	DrawingCanvasDemo()
	{
		super("Demo",10,10,50,50,10,10);
		add(dc = new DrawingCanvas(),1,1,8,8);
		JOptionPane.showMessageDialog(this, "Start");
		dc.setupCoordinateSystem(0, 16, 10, 0);
		dc.setColor(Color.BLUE);
		dc.line(1,1,15,9);
		dc.setColor(Color.RED);
		dc.box(1, 1, 15, 9, false);
		dc.setColor(200,100,150);
		dc.ellipse(8, 5, 3, 2, true);
		dc.setColor(Color.BLACK);
		dc.text("DEMO", 7.5, 9);
	}

	public static void main(String[] args) 
	{
		
		new DrawingCanvasDemo();
	}
}
