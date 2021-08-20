package gui;

import java.awt.*;

import javax.swing.*;

public class ContainerPanel extends JPanel 
{
	int nComponents;
	Component[] components;
	
	public ContainerPanel(int maxComponents)
	{
		components = new Component[maxComponents];
		setLayout(null);
	}
	
	public Component add(Component component)
	{
		super.add(component);
		return components[nComponents++] = component;
	}
	
	public void setFont(Font font)
	{
		for(int x=0; x<nComponents; x++) components[x].setFont(font);
	}
	
	public void setForeground(Color forecolor)
	{
		for(int x=0; x<nComponents; x++) components[x].setForeground(forecolor);
	}
	
	public void setBackground(Color backcolor)
	{
		for(int x=0; x<nComponents; x++) components[x].setBackground(backcolor);
	}
}
