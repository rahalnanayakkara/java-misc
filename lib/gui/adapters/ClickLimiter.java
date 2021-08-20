package gui.adapters;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ClickLimiter implements ActionListener
{
	int clickNo, count;
	JButton button;
	
	public ClickLimiter (int clickNo, JButton button)
	{
		this.clickNo=clickNo;
		this.button=button;
		button.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		count++;
		if (count>=clickNo) button.setEnabled(false);
	}
}
