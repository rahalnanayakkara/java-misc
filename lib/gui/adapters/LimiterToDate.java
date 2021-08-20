package gui.adapters;

import java.awt.event.*;

import javax.swing.JTextField;

public class LimiterToDate extends KeyAdapter
{
	public static LimiterToDate ONE = new LimiterToDate();
	
	public void keyTyped(KeyEvent e)
	{
		if (!(Character.isDigit(e.getKeyChar())||e.getKeyChar()=='-')) e.consume();
	}
	
	public static void plugTo(JTextField... txts)
	{
		for(JTextField txt: txts) txt.addKeyListener(ONE);
	}
}
