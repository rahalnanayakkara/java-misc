package gui.ints;

import java.util.LinkedList;

import javax.swing.*;
import javax.swing.event.*;

import gui.ContainerPanel;

public class IntSlider extends ContainerPanel implements ChangeListener, IntListener 
{
	RangeIntField intField;
	JSlider slider;
	int max, value, newValue;
	boolean fieldChanged;
	
	LinkedList<IntListener> listeners;
	
	public IntSlider(int val, int max)
	{
		super(2);
		setLayout(null);
		add(slider = new JSlider(0,max,val));
		add(intField = new RangeIntField(val,max));
		this.value= value;
		this.max = max;
		slider.addChangeListener(this);
		intField.setIntListener(this);
		listeners = new LinkedList<IntListener>();
	}
	
	public void setBounds(int x, int y, int width, int height)
	{
		super.setBounds(x, y, width, height);
		intField.setBounds(0,0,height*2,height);
		slider.setBounds(height*2, 0, width-height*2, height);
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue(int value)
	{
		if ((value>=0)&&value<=max)
		{
			this.value = value;
			slider.setValue(value);
			intField.setInt(value);
		}
	}
	
	public void addIntListener(IntListener listener)
	{
		listeners.add(listener);
	}
	
	public void intValueChanged(IntEvent e)
	{
		fieldChanged = true;
		slider.setValue(newValue = e.getNewValue());
	}
	
	public void stateChanged(ChangeEvent e)
	{
		if(fieldChanged) fieldChanged = false;
		else intField.setInt(newValue = slider.getValue());
		IntEvent intEvent = new IntEvent(this,value,newValue);
		value = newValue;
		for(int x=0; x<listeners.size(); x++) listeners.get(x).intValueChanged(intEvent);
	}
}
