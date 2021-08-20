package adding;

import javax.swing.*;

import gui.*;
import gui.buttons.*;
import gui.ints.*;

import number.*;

public class AddDialog extends GridDialog implements ButtonListener 
{
	IntField txtAnswer;
	IndexedButton btnSubmit, btnNext;
	int num1, num2;
	JFrame frame;
	JLabel lblAnswer;
	
	AddDialog(JFrame frame)
	{
		super(frame,"Add",33,30,30,40,7,5);
		this.frame = frame;
		num1 = Random.random(0, Data.max);
		num2 = Random.random(0, Data.max);
		addLabel(num1+" + "+num2,1,1,2,1);
		lblAnswer = addLabel("",3,2,1,1);
		add(txtAnswer = new IntField(),4,1,2,1);
		add(btnSubmit = new IndexedButton(1,"OK",this),1,3,2,1);
		add(btnNext = new IndexedButton(2,"Next",this),4,3,2,1);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		txtAnswer.setInputVerifier(null);
		
	}
	
	public void buttonClicked(int index)
	{
		if (index==1)
		{
			btnSubmit.setEnabled(false);
			if(num1+num2==txtAnswer.getInt()) 
			{
				lblAnswer.setText("CORRECT");
				Data.correct++;
			}
			else lblAnswer.setText("INCORRECT");
		}
		if(txtAnswer.getText().equals("")&&index==2)
		{
			int option = JOptionPane.showConfirmDialog(this,"Are you sure you want to skip the question?","Skip",JOptionPane.YES_NO_OPTION);
			if (option==JOptionPane.YES_OPTION) dispose();
		}
		else if (index==2) dispose();
	}
}
