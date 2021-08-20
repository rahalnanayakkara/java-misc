package gui.adapters;

import java.awt.event.*;
import javax.swing.*;

public class Resetter implements ActionListener 
{
	JButton button;
	JTextField[] txtfields;
	
	public static void plugTo (JButton button, JTextField... txtfields)
	{
		new Resetter(button, txtfields);
	}
	
	public Resetter(JButton button, JTextField... txtfields)
	{
		this.button=button;
		this.txtfields=txtfields;
		button.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		for (JTextField txt: txtfields) txt.setText("");
	}

}
