package gui.adapters;

import gui.validation.Validator;

import java.awt.event.*;

import javax.swing.*;

public class FocusRefuser extends FocusAdapter 
{
	public static FocusRefuser ONE = new FocusRefuser();
	
	public static void plugTo(JComponent...c)
	{
		for (JComponent component : c) 
		{
			component.setInputVerifier(null);
			component.addFocusListener(ONE);
		}
	}
	
	public void focusGained(FocusEvent e)
	{
		e.getOppositeComponent().requestFocus();
	}
	
}
