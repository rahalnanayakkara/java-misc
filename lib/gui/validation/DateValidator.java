package gui.validation;

import javax.swing.*;

public class DateValidator extends Validator
{
	public void validate(JComponent c)
	{
		try
		{
			String[] date = ((JTextField)c).getText().split("-");
			
			if (date[0].length()>4) throw new Exception();
			Integer.parseInt(date[0]);
			
			int month = Integer.parseInt(date[1]);
			if (month>12||month==0) throw new Exception();
			
			int day = Integer.parseInt(date[2]);
			if (day>31||day==0) throw new Exception();
			
			Validator.valid = true;
		}
		catch (Exception e)
		{
			Validator.valid=false;
		}
	}
	
	public static DateValidator ONE = new DateValidator();
	
	public static void plugTo(JTextField field)
	{
		field.setInputVerifier(ONE);
	}
	
	public static void plugTo(JTextField... fields)
	{
		for(JTextField field : fields) field.setInputVerifier(ONE);
	}
}
