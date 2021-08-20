package gui.validation;

import javax.swing.*;

public class TimeValidator extends Validator
{
	public void validate(JComponent C)
	{
		try
		{
			String[] time = ((JTextField)C).getText().split(":");
			
			int hours = Integer.parseInt(time[0]);
			if (hours<0||hours>=24) throw new Exception();
			
			int minutes = Integer.parseInt(time[1]);
			if (minutes<0||minutes>=60) throw new Exception();
			
			valid = true;
		}
		catch(Exception e)
		{
			valid=false;
		}
	}
	
	public static TimeValidator ONE = new TimeValidator();
	
	public static void plugTo(JTextField field)
	{
		field.setInputVerifier(ONE);
	}
}
