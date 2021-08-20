 package gui.ints;

import io.Screen;
import gui.adapters.LimiterToDigits;
import gui.fields.ChangingTextField;
import gui.validation.IntValidator;
import gui.validation.Validator;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;

public class IntField extends ChangingTextField implements IntListener
{
	int value;
	IntListener listener;
	
	public IntField()
	{
		super();
		setHorizontalAlignment(LEFT);
		LimiterToDigits.plugTo(this);
		IntValidator.plugTo(this);
		setIntListener(this);
		value=0;
	}
	
	public IntField(int value)
	{
		this();
		setInt(value);
	}
	
	public void setInt(int value)
	{
		this.value = value;
		setText(""+value);
	}
	
	public int getInt()
	{
		return (getText().equals(""))?0:Integer.parseInt(getText());
	}
	
	public void setIntListener(IntListener listener)
	{
		this.listener=listener;
	}

	public void change(DocumentEvent e) 
	{
		try
		{
			int oldValue = value;
			value = getInt();
			listener.intValueChanged(new IntEvent(this,oldValue,value));
		}
		catch (NumberFormatException ex)
		{}
	}

	public void intValueChanged(IntEvent e) {}
}
