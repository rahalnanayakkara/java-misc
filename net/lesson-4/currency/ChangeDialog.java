package currency;

import javax.swing.*;

import gui.*;
import gui.adapters.LimiterToDouble;
import gui.buttons.*;

public class ChangeDialog extends GridDialog implements ButtonListener 
{
	CurrencyServer owner;
	JComboBox cmbCurrency;
	JTextField txtBuy, txtSell;
	
	public ChangeDialog(JFrame owner)
	{
		super(owner,"Change Rates",30,30,30,40,7,9);
		this.owner = (CurrencyServer)owner;
		addLabel("Currency",1,1,2,1);
		addLabel("Buying Rate(Rs.)",1,3,2,1);
		addLabel("Selling Rate(Rs.)",1,5,2,1);
		add(new IndexedButton(1,"Change",this,false),1,7,2,1);
		add(new IndexedButton(2,"Close",this,false),4,7,2,1);
		add(cmbCurrency = new JComboBox(),4,1,2,1);
		add(txtBuy = new JTextField(),4,3,2,1);
		add(txtSell = new JTextField(),4,5,2,1);
		LimiterToDouble.plugTo(txtBuy, txtSell);
	}

	public void buttonClicked(int index) 
	{
		switch(index)
		{
			case 1: change(); break;
			case 2: setVisible(false); break;
		}
	}
	
	public void change()
	{
		Currency selected = (Currency)cmbCurrency.getSelectedItem();
		selected.setBuyingRate(Double.parseDouble(txtBuy.getText()));
		selected.setSellingRate(Double.parseDouble(txtSell.getText()));
		owner.refresh();
		setVisible(false);
		txtBuy.setText("");
		txtSell.setText("");
	}
}
