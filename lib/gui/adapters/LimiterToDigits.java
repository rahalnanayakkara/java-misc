package gui.adapters;

import java.awt.Component;
import java.awt.event.*;

public class LimiterToDigits extends KeyAdapter 
{
	public void keyTyped(KeyEvent e)
	{
		char c = e.getKeyChar();
		if (!(Character.isDigit(c))&&c!='-') e.consume();
	}
	
	static LimiterToDigits ONE = new LimiterToDigits();
	
	public static void plugTo(Component... cs)
	{
		for (Component c: cs)
		c.addKeyListener(ONE);
	}
}
