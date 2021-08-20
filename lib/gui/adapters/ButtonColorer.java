package gui.adapters;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonColorer extends MouseAdapter
{
	public Color foreColor, oldForeColor;
	JButton sourceBtn;
	
	public ButtonColorer(Color foreColor)
	{
		this.foreColor = foreColor;
	}
	
	public void plugTo(JButton... btns)
	{
		for(JButton btn : btns) btn.addMouseListener(this);
	}
	
	public void mousePressed(MouseEvent e)
	{
		sourceBtn = (JButton) e.getSource();
		oldForeColor = sourceBtn.getForeground();
		sourceBtn.setForeground(foreColor);
	}
	
	public void mouseReleased(MouseEvent e)
	{
		sourceBtn.setForeground(oldForeColor);
	}
	
	public void mouseExited(MouseEvent e)
	{
		sourceBtn.setForeground(oldForeColor);
	}
}
