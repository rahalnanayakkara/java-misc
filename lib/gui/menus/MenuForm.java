package gui.menus;

import gui.Form;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MenuForm extends Form implements ActionListener 
{
	static Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
	
	public JMenuBar menuBar;
	JMenuItem menuItem;
	
	public MenuForm(String title, int x, int y, int width, int height)
	{
		super(title,x*display.width/100,y*display.height/100,width*display.width/100,height*display.height/100);
		setJMenuBar(menuBar = new JMenuBar());
	}
	
	public void addMenu(String caption, String options)
	{
		JMenu menu = new JMenu(caption);
		String[] option = options.split(",");
		for(String opt : option) menu.add(opt);
		menuBar.add(menu);
		validate();
	}
	
	public void addMenu(String caption, String... options)
	{
		JMenu menu = new JMenu(caption);
		menu.addActionListener(this);
		for(String opt : options) menu.add(opt).addActionListener(this);
		menuBar.add(menu);
		validate();
	}
	
	public void addOption(String option)
	{
		menuBar.add(menuItem = new JMenu(option));
		menuItem.addActionListener(this);
		validate();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		menuItem = (JMenuItem)e.getSource();
		doMenu(menuItem.getText());
	}
	
	public void doMenu(String menuText){}
}
