import java.awt.Font;
import java.awt.event.*;

import gui.*;
import gui.adapters.Resetter;
import gui.fields.*;
import gui.validation.*;

import java.util.*;

import javax.swing.*;

public class VaccineForm extends GridForm implements ActionListener
{
	DateField txtDOB, txtA, txtB, txtC;
	JButton btnCalculate, btnReset;
	
	Font formFont = new Font("Sans Serif",Font.BOLD,20);
	
	public VaccineForm()
	{
		super("Vaccines",10,10,55,60,7,11);
		
		addLabel("Date of birth    (yyyy-mm-dd)",1,1,2,1);
		addLabel("Date for Vaccine A",1,3,2,1);
		addLabel("Date for Vaccine B",1,5,2,1);
		addLabel("Date for Vaccine C",1,7,2,1);
		
		add(txtDOB = new DateField(),4,1,2,1);
		add(txtA = new DateField(),4,3,2,1);
		add(txtB = new DateField(),4,5,2,1);
		add(txtC = new DateField(),4,7,2,1);
		
		btnCalculate = addButton("Calculate",1,9,2,1);
		btnReset = addButton("Reset",4,9,2,1);
		
		btnCalculate.addActionListener(this);
		
		Resetter.plugTo(btnReset,txtDOB,txtA, txtB, txtC);
		DateValidator.plugTo(txtDOB,txtA, txtB, txtC);
		
		Appearance.setFont(formFont, components);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Date DOB = txtDOB.getDate();
		Date A = new Date(DOB.getYear(),DOB.getMonth(),DOB.getDate()+7);
		Date B = new Date(DOB.getYear(),DOB.getMonth(),DOB.getDate()+30);
		Date C = new Date(DOB.getYear(),DOB.getMonth(),DOB.getDate()+900);
		txtA.setDate(A);
		txtB.setDate(B);
		txtC.setDate(C);
	}
	
	public static void main(String[] args)
	{
		new VaccineForm();
	}
}
