package gui.validation;

import io.Screen;

import javax.swing.*;

public class MoneyValidator extends Validator 
{
	public void validate (JComponent c)
	{
		try
		{
			String text = ((JTextField)c).getText();
			int big = Integer.parseInt(text.substring(0,text.indexOf('.')));
			int small = Integer.parseInt(text.substring(text.indexOf('.')+1));
			if (big<0) throw new Exception();
			if (small>99) throw new Exception();
			Validator.valid = true;
		}
		catch(Exception e)
		{
			valid = false;
		}
	}
	
	public static MoneyValidator ONE = new MoneyValidator();
	
	public static void plugTo(JTextField field)
	{
		field.setInputVerifier(ONE);
	}
}
