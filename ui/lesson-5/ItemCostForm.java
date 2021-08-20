import java.awt.event.*;

import javax.swing.*;

import number.Round;
import gui.*;
import gui.adapters.LimiterToDigits;
import gui.adapters.LimiterToDouble;
import gui.adapters.Resetter;
import gui.buttons.*;
import gui.fields.MoneyField;

public class ItemCostForm extends GridForm implements ButtonListener, FocusListener
{
	JTextField txtDiscount, txtQty;
	MoneyField txtPrice, txtCost;
	JButton btnReset;
	IndexedButton btnCalculate;
	
	public ItemCostForm()
	{
		super("Cost of an Item",10,10,40,50,7,11);
		
		addLabel("Quantity",1,1,2,1);
		addLabel("Unit Price",1,3,2,1);
		addLabel("Discount %",1,5,2,1);
		addLabel("Total Cost",1,9,2,1);
		
		txtQty = addTextField(JTextField.CENTER,4,1,2,1);
		add(txtPrice = new MoneyField(),4,3,2,1);
		txtDiscount = addTextField(JTextField.CENTER,4,5,2,1);
		add(txtCost = new MoneyField(),4,9,2,1);
		
		btnReset = addButton("Reset",4,7,2,1);
		add(btnCalculate = new IndexedButton(1,"Calculate",this),1,7,2,1);
		
		txtCost.setInputVerifier(null);
		txtCost.addFocusListener(this);
		
		LimiterToDigits.plugTo(txtQty);
		LimiterToDouble.plugTo(txtDiscount);
		Resetter.plugTo(btnReset, txtQty, txtDiscount, txtPrice, txtCost);
		
		Appearance.setFont(Appearance.formFont, components);
	}
	
	public void focusGained(FocusEvent e)
	{
		txtQty.requestFocus();
	}
	
	public void buttonClicked(int index)
	{
		double discount = Double.parseDouble(txtDiscount.getText())/100;
		int qty = Integer.parseInt(txtQty.getText());
		double cost = (1-discount)*txtPrice.getValue()*qty;
		txtCost.setValue((int)Math.round(cost));
	}

	public void focusLost(FocusEvent e){}
	
	public static void main(String[] args) 
	{
		new ItemCostForm();
	}

}
