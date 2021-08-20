import io.Screen;

import java.awt.Font;
import java.awt.event.*;

import gui.*;
import gui.adapters.FocusRefuser;
import gui.ints.*;
import gui.validation.Validator;

import javax.swing.*;

public class AdForm extends GridForm  implements IntListener, ActionListener
{
	IntField intCol, intCM;
	JRadioButton rdbDaily, rdbSunday, rdbMag;
	JCheckBox chkColour, chkFirstLast;
	JTextField txtAmount;
	
	Font formFont = new Font("Sans Serif",Font.BOLD,20);
	
	public AdForm()
	{
		super("Cost of Ad",10,10,40,50,7,10);
		
		addLabel("Columns",1,1,2,1);
		addLabel("CM",1,2,2,1);
		addLabel("Amount",1,8,2,1);
		
		add(intCol = new RangeIntField(11),4,1,2,1);
		add(intCM = new RangeIntField(68),4,2,2,1);
		
		add(rdbDaily = new JRadioButton("Daily"),1,4,2,1);
		add(rdbSunday = new JRadioButton("Sunday"),1,5,2,1);
		add(rdbMag = new JRadioButton("Magazine"),1,6,2,1);
		
		add(chkColour = new JCheckBox("Colour"),4,4,2,1);
		add(chkFirstLast = new JCheckBox("First/Last Page"),4,5,2,1);
		
		txtAmount = addTextField(JTextField.CENTER,4,8,2,1);
		
		ButtonGroup paper = new ButtonGroup();
		paper.add(rdbDaily);
		paper.add(rdbMag);
		paper.add(rdbSunday);
		
		FocusRefuser.plugTo(txtAmount);
		Appearance.setFont(formFont, components);
		
		rdbDaily.setSelected(true);
		
		intCol.setIntListener(this);
		intCM.setIntListener(this);
		
		rdbDaily.addActionListener(this);
		rdbSunday.addActionListener(this);
		rdbMag.addActionListener(this);
		chkColour.addActionListener(this);
		chkFirstLast.addActionListener(this);

	}
	
	public void intValueChanged(IntEvent e)
	{
		calculate();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		calculate();
	}
	
	public void calculate()
	{
		double cost = intCol.getInt()*intCM.getInt();
		if (rdbDaily.isSelected()) cost*=100;
		if (rdbSunday.isSelected()) cost*=120;
		if (rdbMag.isSelected()) cost*=130;
		if (chkColour.isSelected()) cost*=1.15;
		if (chkFirstLast.isSelected()) cost*=1.25;
		txtAmount.setText(""+cost);
	}
	
	public static void main(String[] args)
	{
		new AdForm();
	}
}
