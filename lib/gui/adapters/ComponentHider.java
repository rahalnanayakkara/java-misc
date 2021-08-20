package gui.adapters;

import javax.swing.*;
import java.awt.event.*;

public class ComponentHider implements ActionListener
{
	JComponent[] components;
	JCheckBox chk;
	
	public ComponentHider(JCheckBox chk, JComponent...components)
	{
		this.components=components;
		this.chk=chk;
		chk.addActionListener(this);
		for (JComponent c: components)
			c.setVisible(false);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		for(JComponent c: components) 
			if (chk.isSelected()) c.setVisible(true); 
			else c.setVisible(false);
	}
	
	public static void plugTo(JCheckBox chk, JComponent...components)
	{
		new ComponentHider(chk,components);
	}
}
