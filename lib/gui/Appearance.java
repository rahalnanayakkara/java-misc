package gui;

import java.awt.*;

import javax.swing.*;

public class Appearance 
{
	public static Font formFont = new Font("Sans Serif",Font.BOLD,20);
	
	public static void setFont(Font font, Component ...components)
	{
		try {for(Component component : components) component.setFont(font);}
		catch (NullPointerException e){}
	}
	
	public static void setFont(Font font, GridForm g)
	{
		setFont(font,g.components);
	}
	
	public static void setBackground(Color bg, Component ...components)
	{
		try{for(Component component : components) component.setBackground(bg);}
		catch (NullPointerException e){}
	}
	
	public static void setBackground(Color bg, GridForm g)
	{
		setBackground(bg, g.components);
	}
	
	public static void setForeground(Color fg, Component ...components)
	{
		try{for(Component component : components) component.setForeground(fg);}
		catch (NullPointerException e){}
	}
	
	public static void setForeground(Color fg, GridForm g)
	{
		setForeground(fg, g.components);
	}
	
	public static void setColor(Color fg, Color bg, Component ...components)
	{
		try
		{
			for(Component component : components)
			{
				component.setBackground(bg);
				component.setForeground(fg);
			}
		}
		catch (NullPointerException e){}
	}
	
	public static void setAppearance(Font font, Color fg, Color bg, Component ...components)
	{
		try
		{
			for(Component component : components)
			{
				component.setFont(font);
				component.setBackground(bg);
				component.setForeground(fg);
			}
		}
		catch (NullPointerException e){}
	}
}
