package gui.fields;

import java.util.LinkedList;

import gui.*;
import gui.buttons.*;
import gui.ints.*;
import gui.validation.Validator;

public class UpDownField extends ContainerPanel implements ButtonListener, IntListener
{
	RangeIntField field;
	IndexedButton btnUp, btnDown;
	LinkedList<IntListener> listeners;
	
	int value;
	
	public UpDownField(int max)
	{
		super(3);
		add(field = new RangeIntField(max));
		add(btnUp = new IndexedButton(1,">",this));
		add(btnDown = new IndexedButton(2,"<",this));
		field.setIntListener(this);
		listeners = new LinkedList<IntListener>();
	}
	
	public UpDownField(int val, int max)
	{
		this(max);
		this.setInt(val);
	}
	
	public void setBounds(int x, int y, int width, int height)
	{
		super.setBounds(x,y,width,height);
		btnDown.setBounds(0, 0, 2*height, height);
		field.setBounds(2*height,0,width-4*height, height);
		btnUp.setBounds(width-2*height,0,2*height,height);
	}
	
	public void setInt(int val)
	{
		field.setInt(val);
		Validator.valid = true;
	}
	
	public int getInt()
	{
		return value;
	}
	
	public void addIntListener(IntListener listener)
	{
		listeners.add(listener);
	}
	
	public void intValueChanged(IntEvent e)
	{
		value = e.getNewValue();
		for(int x=0; x<listeners.size(); x++) listeners.get(x).intValueChanged(new IntEvent(this,e.getOldValue(),e.getNewValue()));
	}
	
	public void buttonClicked(int index)
	{
		switch(index)
		{
			case 1: field.setInt(field.getInt()+1); break;
			case 2: field.setInt(field.getInt()-1); break;
		}
	}
}
