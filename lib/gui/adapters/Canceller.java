package gui.adapters;

import java.awt.event.*;

import javax.swing.JTextField;

public class Canceller extends KeyAdapter implements FocusListener
{
	static String oldText;
	static JTextField txt;
	
	public void focusGained(FocusEvent e)
	{
		txt = (JTextField)(e.getSource());
		oldText = txt.getText();
	}
	
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode()==KeyEvent.VK_ESCAPE) txt.setText(oldText);
	}
	
	public void focusLost(FocusEvent e){}
	
	static Canceller ONE = new Canceller();
	
	public static void plugTo(JTextField...txts)
	{
		for(JTextField field : txts)
		{
			field.addFocusListener(ONE);
			field.addKeyListener(ONE);
		}
	}
}
