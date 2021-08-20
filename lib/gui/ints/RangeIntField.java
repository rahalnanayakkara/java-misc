package gui.ints;

import io.Screen;
import gui.adapters.LengthLimiter;
import gui.validation.*;

import javax.swing.event.DocumentEvent;

public class RangeIntField extends IntField 
{
	int max;
	
	public RangeIntField(int max)
	{
		super();
		this.max=max;
		RangeIntValidator.plugTo(this);
		LengthLimiter.plugTo((max+"").length(), this);
		value=0;
		setHorizontalAlignment(CENTER);
	}
	
	public RangeIntField(int val, int max)
	{
		this(max);
		setInt(val);
	}
	
	public void setInt(int val)
	{
		if (val>=0&&val<=max)
			super.setInt(val);
	}
	
	public int getMax()
	{
		return max;
	}
	
	public void change(DocumentEvent e) 
	{
		try
		{
			int oldValue = value;
			value = getInt();
			if (value<=max&&value>0) listener.intValueChanged(new IntEvent(this,oldValue,value));
		}
		catch (NumberFormatException ex)
		{}
	}
}
