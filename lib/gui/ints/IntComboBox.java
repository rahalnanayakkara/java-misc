package gui.ints;

import java.awt.event.*;

import javax.swing.JComboBox;

public class IntComboBox extends JComboBox implements ItemListener, IntListener
{
	int value;
	IntListener listener;
	
	public IntComboBox(int start, int end)
	{
		super();
		for(int x=0; x<end-start+1; x++) addItem(start+x);
		value = -1;
		addItemListener(this);
		setIntListener(this);
	}
	
	public IntComboBox(int[] a)
	{
		super();
		for(int val : a) addItem(val);
		value = -1;
		addItemListener(this);
		setIntListener(this);
	}
	
	public void setIntListener(IntListener listener)
	{
		this.listener = listener;
	}
	
	public int getSelectedInt()
	{
		return (Integer)getSelectedItem();
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		int oldValue = value;
		value = getSelectedInt();
		listener.intValueChanged(new IntEvent(this,oldValue,value));
	}
	
	public void intValueChanged(IntEvent e){}
}
