package adding;

import javax.swing.*;

import java.awt.event.*;

import gui.*;
import gui.ints.IntField;

public class AddingForm extends GridForm implements ActionListener 
{
	IntField txtAnswered, txtCorrect;
	JButton btnOK;
	StartDialog start;
	
	AddingForm()
	{
		super("Addition Test",28,25,40,50,5,7);
		
		addLabel("No. answered",1,1,1,1);
		addLabel("No. Correct",1,3,1,1);
		
		add(txtAnswered = new IntField(),3,1,1,1);
		add(txtCorrect = new IntField(),3,3,1,1);
		
		txtAnswered.setEnabled(false);
		txtCorrect.setEnabled(false);
		txtAnswered.setHorizontalAlignment(JTextField.CENTER);
		txtCorrect.setHorizontalAlignment(JTextField.CENTER);
		
		btnOK = addButton("OK",2,5,1,1);
		btnOK.addActionListener(this);
		start = new StartDialog(this);
		
		Appearance.setFont(Appearance.formFont, components);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		start.setVisible(true);
		for(int x=1; x<=Data.n; x++)
		{
			new AddDialog(this).setVisible(true);
			txtAnswered.setInt(x);
			txtCorrect.setInt(Data.correct);
		}
	}

	public static void main(String[] args) 
	{
		new AddingForm();
	}

}
