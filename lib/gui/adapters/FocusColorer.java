package gui.adapters;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class FocusColorer extends FocusAdapter 
{
	Color normForecolor, normBackcolor, focusForecolor, focusBackcolor;
	
	public static void plugTo(Color normForecolor, Color normBackcolor,
			Color focusForecolor, Color focusBackcolor, JComponent...components)
	{
		new FocusColorer(normForecolor, normBackcolor, focusForecolor, focusBackcolor, components);
	}
	
	public FocusColorer(Color normForecolor, Color normBackcolor,
			Color focusForecolor, Color focusBackcolor, JComponent...components)
	{
		this.focusBackcolor=focusBackcolor;
		this.focusForecolor=focusForecolor;
		this.normBackcolor=normBackcolor;
		this.normForecolor=normForecolor;
		for(JComponent component : components ) component.addFocusListener(this);
	}
	
	public void focusGained(FocusEvent e)
	{
		JComponent component = (JComponent)e.getSource();
		component.setForeground(focusForecolor);
		component.setBackground(focusBackcolor);
	}
	
	public void focusLost(FocusEvent e)
	{
		JComponent component = (JComponent)e.getSource();
		component.setForeground(normForecolor);
		component.setBackground(normBackcolor);
	}
}
