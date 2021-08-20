package adding;

import javax.swing.*;

import gui.*;
import gui.buttons.*;
import gui.ints.*;

public class StartDialog extends GridDialog implements ButtonListener
{
	IntField txtProblems, txtMax;
	IndexedButton btnOK;
	
	StartDialog(JFrame frame)
	{
		super(frame,"Select Difficulty",33,30,30,40,7,7);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addLabel("No. of problems",1,1,2,1);
		addLabel("Max Value",1,3,2,1);
		add(txtProblems = new IntField(),4,1,2,1);
		add(txtMax = new IntField(),4,3,2,1);
		add(btnOK = new IndexedButton(1,"OK",this),3,5,1,1);
	}
	
	public void buttonClicked(int i)
	{
		Data.n = txtProblems.getInt();
		Data.max = txtMax.getInt();
		dispose();
	}
}