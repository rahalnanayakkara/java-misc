package gui.validation;

import javax.swing.*;

public class Validator extends InputVerifier 
{
	public static boolean valid;
	
	static Validator ONE = new Validator();
	
	public boolean verify(JComponent c) 
	{
		validate(c);
		return valid;
	}
	
	public void validate(JComponent c){}
	
	public static void plugTo(JTextField textField)
	{
		textField.setInputVerifier(ONE);
	}
}
