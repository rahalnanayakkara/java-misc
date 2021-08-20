import java.awt.*;

import gui.*;
import gui.adapters.FocusRefuser;
import gui.ints.*;
import gui.lists.SimpleList;

import javax.swing.*;

public class MarkForm extends GridForm implements IntListener
{
	JTextField txtTheory, txtPractical;
	IntField intTheory, intPractical, intTotal;
	
	Font formFont = new Font("Sans Serif",Font.BOLD,20);
	
	public MarkForm()
	{
		super("Student Marks",10,10,30,40,10,9);
		
		addLabel("Theory",1,3,2,1);
		addLabel("Practical",1,5,2,1);
		addLabel("Total",1,7,2,1);
		addLabel("Marks",4,1,2,1);
		addLabel("Grade",7,1,2,1);
		
		txtTheory = addTextField(JTextField.CENTER,7,3,2,1);
		txtPractical = addTextField(JTextField.CENTER,7,5,2,1);
		
		add(intTheory = new RangeIntField(100),4,3,2,1);
		add(intPractical = new RangeIntField(100),4,5,2,1);
		add(intTotal = new RangeIntField(200),4,7,2,1);
		
		intTheory.setIntListener(this);
		intPractical.setIntListener(this);
		
		Appearance.setFont(formFont, components);
		FocusRefuser.plugTo(txtTheory, txtPractical, intTotal);
	}
	
	
	public String grade(int marks)
	{
		if (marks<40) return "F";
		if (marks<60) return "C";
		if (marks<80) return "B";
		return "A";
	}
	
	public void intValueChanged(IntEvent e)
	{
		if (e.getSource()==intTheory) txtTheory.setText(grade(intTheory.getInt()));
		if (e.getSource()==intPractical) txtPractical.setText(grade(intPractical.getInt()));
		intTotal.setInt(intTheory.getInt()+intPractical.getInt());
	}
	
	public static void main(String[] args)
	{
		new MarkForm();
	}
}
