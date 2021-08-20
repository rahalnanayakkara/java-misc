package gui.adapters;

import java.awt.event.*;

import javax.swing.JTextField;

public class LimiterToTime extends KeyAdapter
{
	public static LimiterToTime ONE = new LimiterToTime();
	
	public void keyTyped(KeyEvent e)
	{
		if (!(Character.isDigit(e.getKeyChar())||e.getKeyChar()==':')) e.consume();
	}
	
	public static void plugTo(JTextField... txts)
	{
		for(JTextField txt: txts) txt.addKeyListener(ONE);
	}
}
