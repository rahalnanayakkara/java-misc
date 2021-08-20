package gui.adapters;

import java.awt.event.*;
import javax.swing.*;

public class LengthLimiter extends KeyAdapter 
{
	int length;
	
	public void keyTyped(KeyEvent e)
	{
		JTextField field = (JTextField)(e.getSource());
		if (field.getText().length()>=length) e.consume();
	}
	
	public static void plugTo(int length, JTextField... txts)
	{
		LengthLimiter limiter = new LengthLimiter();
		limiter.length=length;
		for(JTextField txt: txts) txt.addKeyListener(limiter);
	}
}
