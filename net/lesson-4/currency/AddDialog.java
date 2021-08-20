package currency;

import javax.swing.*;

import gui.*;
import gui.adapters.LimiterToDouble;
import gui.buttons.*;

public class AddDialog extends GridDialog implements ButtonListener
{
	CurrencyServer owner;
	JTextField txtName, txtBuy, txtSell;
	
	public AddDialog(JFrame owner) 
	{
		super(owner, "Add Currency", 30,30,30,40,7,9);
		addLabel("Currency",1,1,2,1);
		addLabel("Buying Rate(Rs.)",1,3,2,1);
		addLabel("Selling Rate(Rs.)",1,5,2,1);
		add(new IndexedButton(1,"Add",this,false),1,7,2,1);
		add(new IndexedButton(2,"Close",this,false),4,7,2,1);
		add(txtName = new JTextField(),4,1,2,1);
		add(txtBuy = new JTextField(),4,3,2,1);
		add(txtSell = new JTextField(),4,5,2,1);
		LimiterToDouble.plugTo(txtBuy, txtSell);
		this.owner = (CurrencyServer)owner;
	}
	
	public void buttonClicked(int i)
	{
		switch(i)
		{
			case 1: add(); break;
			case 2: setVisible(false); break;
		}
	}
	
	public void add()
	{
		Currency newCurrency = new Currency(txtName.getText());
		newCurrency.setBuyingRate(Double.parseDouble(txtBuy.getText()));
		newCurrency.setSellingRate(Double.parseDouble(txtSell.getText()));
		owner.addCurrency(newCurrency);
		setVisible(false);
		txtName.setText("");
		txtBuy.setText("");
		txtSell.setText("");
	}

}
