import javax.swing.*;

import thread.SecondsCounter;
import gui.*;

public class TesterForm extends GridForm 
{
	TesterForm()
	{
		super("Tester Form",10,10,30,40,7,5);
		addLabel("Time",1,1,2,1);
		addLabel("A",1,3,2,1);
		addTextField(JTextField.CENTER,4,3,2,1);
		new SecondsCounter(addTextField(JTextField.CENTER,4,1,2,1)).start();
	}
	
	public static void main(String[] args)
	{
		new TesterForm();
	}
}
