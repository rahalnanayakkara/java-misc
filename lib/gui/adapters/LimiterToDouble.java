package gui.adapters;

import java.awt.event.*;

import javax.swing.JTextField;

public class LimiterToDouble extends KeyAdapter
{
	public static LimiterToDouble ONE = new LimiterToDouble();
	
	public void keyTyped(KeyEvent e)
	{
		if (!(Character.isDigit(e.getKeyChar())||e.getKeyChar()=='.')) e.consume();
	}
	
	public static void plugTo(JTextField... txts)
	{
		for(JTextField txt: txts) txt.addKeyListener(ONE);
	}
}
