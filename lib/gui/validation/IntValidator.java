package gui.validation;

import javax.swing.*;

public class IntValidator extends Validator 
{
	static IntValidator ONE = new IntValidator();
	
	public void validate(JComponent c)
	{
		try
		{
			String text = ((JTextField)c).getText();
			Integer.parseInt(text);
			valid = true;
		}
		catch(NumberFormatException e)
		{
			valid = false;
		}
	}
	
	public static void plugTo(JTextField field)
	{
		field.setInputVerifier(ONE);
	}
}
