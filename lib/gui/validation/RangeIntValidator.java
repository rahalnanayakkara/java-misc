package gui.validation;

import gui.ints.*;

import javax.swing.JComponent;

public class RangeIntValidator extends IntValidator 
{
	static RangeIntValidator ONE = new RangeIntValidator();
	
	public void validate(JComponent c)
	{
		super.validate(c);
		RangeIntField field = (RangeIntField)c;
		valid = (field.getInt()>=0)&&(field.getInt()<=field.getMax()); 
	}
	
	public static void plugTo(RangeIntField field)
	{
		field.setInputVerifier(ONE);
	}
}
