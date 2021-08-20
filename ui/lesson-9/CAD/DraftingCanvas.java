package CAD;

import graphics.*;
import graphics.shapes.*;
import graphics.shapes.Rectangle;
import gui.*;
import gui.buttons.*;
import io.Screen;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import thread.StoppableThread;

public class DraftingCanvas extends DrawingCanvas implements MouseListener, ButtonListener
{
	double x, y, mouseX, mouseY;
	int clickCount, shape; //1=line, 2=rectangle, 3=circle
	Vector<Drawable> shapes = new Vector<Drawable>();
	Line drawingLine = new Line(0,0,0,0);
	Rectangle drawingRectangle = new Rectangle(0,0,0,0,false);
	Ellipse drawingCircle = new Ellipse(0,0,0,0,false);
	JTextField txtX = new JTextField(), txtY = new JTextField();
	
	public MouseMotionAdapter mouseMovement = new MouseMotionAdapter()
	{
		public void mouseMoved(MouseEvent e)
		{
			mouseX = 1.0*e.getX()/xScale-xTranslate;
			mouseY = 1.0*e.getY()/yScale-yTranslate;
			if(clickCount%2==1)
			{
				repaint();
				if(shape==1)
				{
					drawingLine.setLine(x, y, mouseX, mouseY);
				}
				if(shape==2)
				{
					drawingRectangle.setRect(x, y, mouseX, mouseY);
				}
				if(shape==3)
				{
					double d = distance(x,y,mouseX, mouseY);
					drawingCircle.setEllipse(x, y, d, d);
				}
			}
			try
			{
				txtX.setText((mouseX+"").substring(0,7));
				txtY.setText((mouseY+"").substring(0,6));
			}
			catch(Exception ex){}
		}
	};
	
	public void setCoordinateListeners(JTextField x, JTextField y)
	{
		txtX = x;
		txtY = y;
	}
	
	public void draw()
	{
		for(Drawable d : shapes)d.draw(g);
		drawingLine.draw(g);
		drawingRectangle.draw(g);
		drawingCircle.draw(g);
	}
	

	public void mouseClicked(MouseEvent e) 
	{
		double newX = 1.0*e.getX()/xScale-xTranslate;
		double newY = 1.0*e.getY()/yScale-yTranslate;
		switch(shape)
		{
			case 1 : clickCount++; 
					 if (clickCount%2==0)
						 shapes.addElement(new Line(x,y,newX,newY));
					 break;
			case 2 : clickCount++;
					 if (clickCount%2==0) 
					 	shapes.addElement(new Rectangle(x,y,newX,newY,false));
					 break;
			case 3 : clickCount++;
					 double d = distance(x,y,newX,newY);
			 		 if (clickCount%2==0) 
			 			shapes.addElement(new Ellipse(x,y,d,d,false));
			 		 break;
		}
		x=newX;
		y=newY;
		draw();
	}
	
	public void buttonClicked(int index)
	{
		switch(index)
		{
			case 0 : repaint() ; break;
			case 1 : clickCount=0; shape=1; break;
			case 2 : clickCount=0; shape=2; break;
			case 3 : clickCount=0; shape=3; break;
		}
	}
	
	public static void main(String[] args)
	{
		GridForm form = new GridForm("Drafting",0,0,80,70,100,100);
		DraftingCanvas dc = new DraftingCanvas();
		dc.setBackground(Color.WHITE);
		form.add(dc,1,1,98,90);
		form.add(new IndexedButton(0,"Regenerate",dc,false),89,91,10,10);
		form.add(new IndexedButton(1,"Line",dc,false),1,91,10,10);
		form.add(new IndexedButton(2,"Rectangle",dc,false),11,91,10,10);
		form.add(new IndexedButton(3,"Circle",dc,false),21,91,10,10);
		JTextField txtX = form.addTextField(JTextField.CENTER, 46, 93, 10,6);
		JTextField txtY = form.addTextField(JTextField.CENTER, 61, 93, 10,6);
		form.addLabel("X :", 44, 93, 5, 6);
		form.addLabel("Y :", 59, 93, 5, 6);
		Appearance.setFont(Appearance.formFont, form);
		dc.setCoordinateListeners(txtX, txtY);
		dc.setupCoordinateSystem(0,2100,1000,0);
		dc.addMouseListener(dc);
		dc.addMouseMotionListener(dc.mouseMovement);
	}
	
	public double distance(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
	
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
}
