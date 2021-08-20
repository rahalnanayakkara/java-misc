import gui.*;
import gui.adapters.*;

import javax.swing.*;

import number.Operations;

import java.awt.*;
import java.awt.event.*;

public class ElectricityBill extends GridForm implements ActionListener
{
	JTextField txtUnits, txtCost;
	JComboBox cmbType;
	JButton btnCalculate, btnReset;
	
	String[] list = {"None", "Domestic", "Charities", "Institutions"};
	
	Font formFont = new Font("Sans Serif",Font.BOLD,20);
	
	public ElectricityBill()
	{
		super("Electricity Bill",10,10,30,40,7,9);
		
		add(new JLabel("No. of Units"),1,1,2,1);
		add(new JLabel("Customer Type"),1,3,2,1);
		add(new JLabel("Total Cost"),1,5,2,1);
		
		btnCalculate = addButton("Calculate",1,7,2,1);
		btnReset = addButton("Reset",4,7,2,1);
		
		txtUnits = addTextField(JTextField.CENTER,4,1,2,1);
		txtCost = addTextField(JTextField.CENTER,4,5,2,1);
		add(cmbType = new JComboBox(list),4,3,2,1);

		btnCalculate.addActionListener(this);
		
		LimiterToDouble.plugTo(txtUnits);
		FocusRefuser.plugTo(txtCost);
		Resetter.plugTo(btnReset, txtUnits, txtCost);
		
		Appearance.setFont(formFont, components);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		calculate();
	}
	
	public void calculate()
	{
		int units, amount=0;
		units = Integer.parseInt(txtUnits.getText());
		switch (cmbType.getSelectedIndex())
		{
			case 1 : amount = units*5+Operations.over(units, 30)*3+Operations.over(units, 90)*4; break;
			case 2 : amount = units*5; break;
			case 3 : amount = units*8+Operations.over(units, 90)*4; break;
			case 0 : amount = 0; break; 
		}
		txtCost.setText(""+amount);
	}

	
	public static void main(String[] args)
	{
		new ElectricityBill();
	}
}
