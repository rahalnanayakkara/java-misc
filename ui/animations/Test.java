
import gui.*;
import graphics.*;
import io.Screen;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Test 
{
	private static class Tester extends DrawingCanvas implements ActionListener
	{
		Timer tm = new Timer(5,this);
		boolean left, right, up, down;
		int velX=5, velY=5, x=1000, y=500;
		
		KeyAdapter m = new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_LEFT : left = true; break;
					case KeyEvent.VK_RIGHT : right = true; break;
					case KeyEvent.VK_UP : up = true; break;
					case KeyEvent.VK_DOWN : down = true; break;
				}
			}
			
			public void keyReleased(KeyEvent e)
			{
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_LEFT : left = false; break;
					case KeyEvent.VK_RIGHT : right = false; break;
					case KeyEvent.VK_UP : up = false; break;
					case KeyEvent.VK_DOWN : down = false; break;
				}
			}
		};
		
		public void initialize()
		{
			setBackground(Color.WHITE);
			setupCoordinateSystem(0, 2000, 1000, 0);
			addKeyListener(m);
			tm.start();
		}
		
		public void draw()
		{
			circle(x,y,20,true);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if (left && x>20) x-=velX;
			if (right && x<1980) x+=velX;
			if (up && y<980) y+=velY;
			if (down && y>20) y-=velY;
			repaint();
		}
	}
	
	public static void main(String[] args) 
	{
		GridForm g = new GridForm("Animation",10,10,80,80,100,100);
		Tester t = new Tester();
		g.add(t, 1, 1, 99, 99);
		t.initialize();
	}
}
