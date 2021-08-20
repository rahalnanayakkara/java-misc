package gui.ints;

import java.awt.AWTEvent;

public class IntEvent extends AWTEvent 
{
	int oldValue, newValue;
	
	public IntEvent(Object source, int oldValue, int newValue)
	{
		super(source, 9999);
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
	public int getOldValue()
	{
		return oldValue;
	}
	
	public int getNewValue()
	{
		return newValue;
	}
}
