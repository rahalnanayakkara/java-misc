import objects.Time;

import javax.swing.*;

import gui.*;
import gui.adapters.FocusRefuser;
import gui.adapters.Resetter;
import gui.buttons.ButtonListener;
import gui.buttons.IndexedButton;
import gui.fields.TimeField;

public class DurationForm extends GridForm implements ButtonListener
{
	IndexedButton btnCalculate; 
	JButton btnReset;
	TimeField txtStart, txtEnd, txtDuration;
	
	public DurationForm()
	{
		super("Duration of a Task",10,10,30,40,7,9);
		
		addLabel("Start Time",1,1,2,1);
		addLabel("End Time",1,3,2,1);
		addLabel("Duration",1,5,2,1);
		
		add(txtStart = new TimeField(),4,1,2,1);
		add(txtEnd = new TimeField(),4,3,2,1);
		add(txtDuration = new TimeField(),4,5,2,1);
		
		add(btnCalculate = new IndexedButton(1,"Calculate",this),1,7,2,1);
		btnReset = addButton("Reset",4,7,2,1);
		
		Resetter.plugTo(btnReset, txtStart, txtEnd, txtDuration);
		FocusRefuser.plugTo(txtDuration);
		Appearance.setFont(Appearance.formFont, components);
	}
	
	public void buttonClicked(int index)
	{
		txtDuration.setTime(Time.duration(txtStart.getTime(), txtEnd.getTime()));
	}

	public static void main(String[] args) 
	{
		new DurationForm();
	}
}
