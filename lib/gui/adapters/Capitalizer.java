package gui.adapters;

import java.awt.event.*;
import java.awt.*;

public class Capitalizer extends KeyAdapter 
{
	public static Capitalizer ONE = new Capitalizer();
	
	public void keyTyped(KeyEvent e)
	{
		char c = e.getKeyChar();
		if (c>='a'&&c<='z') c-=32;
		e.setKeyChar(c);
	}
	
	public static void plugTo(Component...components)
	{
		for(Component c: components) c.addKeyListener(ONE);
	}
}
