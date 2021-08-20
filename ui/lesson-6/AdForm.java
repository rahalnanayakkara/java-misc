import java.awt.Color;

import io.Screen;
import gui.Appearance;
import gui.GridForm;
import gui.adapters.FocusRefuser;
import gui.buttons.*;
import gui.fields.*;
import gui.ints.*;
import gui.validation.Validator;

import javax.swing.*;

public class AdForm extends GridForm implements ButtonListener
{
	IntSlider sldCol, sldCM;
	MoneyField txtCost;
	RadioButtonPanel rdbPaper;
	IndexedButton btnCalculate;
	JCheckBox chkColour, chkFirstLast;
	int[] rates = {100,120,130};
	
	public AdForm()
	{
		super("Cost of Ad",20,20,40,50,7,14);
		addLabel("Columns",1,1,2,1);
		addLabel("CM",1,2,2,1);
		addLabel("Amount",1,12,2,1);
		
		add(sldCol = new IntSlider(0,12),4,1,2,1);
		add(sldCM = new IntSlider(0,70),4,2,2,1);
		
		add(rdbPaper = new RadioButtonPanel(3,"Paper","Daily Paper,Sunday Paper,Magazine",0),1,4,2,5);
		
		add(chkColour = new JCheckBox("Colour"),4,4,2,1);
		add(chkFirstLast = new JCheckBox("First/Last Page"),4,5,2,1);
		
		add(btnCalculate = new IndexedButton(1,"Calculate",this),2,10,3,1);
		
		add(txtCost = new MoneyField(),4,12,2,1);
		txtCost.setInputVerifier(null);
		FocusRefuser.plugTo(txtCost);
		Validator.valid = true;
		Appearance.setFont(Appearance.formFont, components);
	}
	
	public void buttonClicked(int index) 
	{
		double cost = rates[rdbPaper.getSelectedIndex()]*100;
		int area = sldCol.getValue()*sldCM.getValue();
		cost*=area;
		if (chkColour.isSelected()) cost*=1.25;
		if (chkFirstLast.isSelected()) cost*=1.15;
		txtCost.setValue((int)Math.round(cost));
	}
	
	public static void main(String[] args)
	{
		new AdForm();
	}

	
}
