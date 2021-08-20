import java.awt.event.*;

import io.Screen;
import gui.Appearance;
import gui.GridForm;
import gui.adapters.FocusRefuser;
import gui.buttons.*;
import gui.fields.*;
import gui.ints.*;
import gui.validation.Validator;

import javax.swing.*;
import javax.swing.event.*;

public class AdForm3 extends GridForm implements ActionListener, IntListener
{
	UpDownField fldCM, fldCol;
	MoneyField txtCost, txtRate;
	RadioButtonPanel rdbPaper;
	JTextField txtArea;
	IndexedButton btnCalculate;
	JCheckBox chkColour, chkFirstLast;
	int[] rates = {100,120,130};
	
	public AdForm3()
	{
		super("Cost of Ad",20,20,40,50,7,16);
		addLabel("Columns",1,1,2,1);
		addLabel("CM",1,2,2,1);
		addLabel("Amount",1,14,2,1);
		addLabel("Rate",1,10,2,1);
		addLabel("Area",1,12,2,1);
		
		add(fldCol = new UpDownField(0,12),3,1,3,1);
		add(fldCM = new UpDownField(0,70),3,2,3,1);
		
		add(rdbPaper = new RadioButtonPanel(3,"Paper","Daily Paper,Sunday Paper,Magazine",0),1,4,2,5);
		
		add(chkColour = new JCheckBox("Colour"),4,5,2,1);
		add(chkFirstLast = new JCheckBox("First/Last Page"),4,7,2,1);
		
		add(txtRate = new MoneyField(),3,10,3,1);
		add(txtArea = new JTextField(),3,12,3,1);
		add(txtCost = new MoneyField(),3,14,3,1);
		txtCost.setInputVerifier(null);
		txtRate.setInputVerifier(null);
		txtArea.setHorizontalAlignment(JTextField.CENTER);
		FocusRefuser.plugTo(txtCost, txtRate, txtArea);
		
		rdbPaper.setIntListener(this);
		chkColour.addActionListener(this);
		chkFirstLast.addActionListener(this);
		fldCM.addIntListener(this);
		fldCol.addIntListener(this);
		
		Appearance.setFont(Appearance.formFont, components);
		
		compute();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		compute();
	}
	
	public void intValueChanged(IntEvent e)
	{
		compute();
	}
	
	public void compute() 
	{
		double cost = rates[rdbPaper.getSelectedIndex()]*100;
		int area = fldCol.getInt()*fldCM.getInt();
		if (chkColour.isSelected()) cost*=1.25;
		if (chkFirstLast.isSelected()) cost*=1.15;
		txtRate.setValue((int)Math.round(cost));
		cost*=area;
		txtArea.setText(""+area);
		txtCost.setValue((int)Math.round(cost));
	}
	
	public static void main(String[] args)
	{
		new AdForm3();
	}

	
}
