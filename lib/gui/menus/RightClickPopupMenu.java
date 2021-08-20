package gui.menus;

import java.awt.event.*;

import javax.swing.*;

public class RightClickPopupMenu extends JPopupMenu implements MouseListener, ActionListener 
{
	public RightClickPopupMenu(String...options)
	{
		super();
		for(String option : options)
		if(option.equals("|")) addSeparator();
		else add(option).addActionListener(this);
	}
	
	public RightClickPopupMenu(String options)
	{
		this(options.split(","));
	}
	
	public void doMenu(String caption){}
	
	public void actionPerformed(ActionEvent e) 
	{
		doMenu(((JMenuItem)e.getSource()).getText());
	}
	
	public void mouseClicked(MouseEvent e) 
	{
		if (e.getButton()==MouseEvent.BUTTON3)
			show(e.getComponent(),e.getX(),e.getY());
	}
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

}
