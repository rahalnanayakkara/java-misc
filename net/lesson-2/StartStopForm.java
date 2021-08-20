import javax.swing.*;

import thread.SecondsCounter;
import gui.*;
import gui.buttons.*;
import gui.validation.Validator;

public class StartStopForm extends GridForm implements ButtonListener
{
	SecondsCounter sc;
	JTextField txtTime;
	
	StartStopForm()
	{
		super("Tester Form",10,10,30,40,7,7);
		addLabel("Time",1,1,2,1);
		add(new IndexedButton(1,"Start",this),1,3,2,1);
		add(new IndexedButton(2,"Stop",this),4,3,2,1);
		add(new IndexedButton(3,"Suspend",this),1,5,2,1);
		add(new IndexedButton(4,"Resume",this),4,5,2,1);
		txtTime = addTextField(JTextField.CENTER,4,1,2,1);
		Validator.valid = true;
	}
	
	public void buttonClicked(int index)
	{
		if (index==1)
		{
			sc = new SecondsCounter(txtTime);
			sc.start();
		}
		if (index==2) sc.terminate();
		if (index==3) sc.suspend();
		if (index==4) sc.resume();
	}
	
	public static void main(String[] args) 
	{
		new StartStopForm();
	}
}
