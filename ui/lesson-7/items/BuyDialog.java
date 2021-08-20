package items;

import javax.swing.*;

import gui.*;
import gui.buttons.*;
import gui.ints.*;

public class BuyDialog extends GridDialog implements ButtonListener
{
	IntField intQty, intCost, intAmount;
	
	BuyDialog(JFrame frame)
	{
		super(frame,"Buy Item",20,20,30,40,7,9);
		
		add(new JLabel("Qty"),1,1,2,1);
		add(new JLabel("Cost"),1,3,2,1);
		add(new JLabel("Amount"),1,7,2,1);
		
		add(intQty = new IntField(),4,1,2,1);
		add(intCost = new IntField(),4,3,2,1);
		add(intAmount = new IntField(),4,7,2,1);
		
		intCost.setEnabled(false);
		intAmount.setEnabled(false);
		
		add(new IndexedButton(1,"Calculate",this),2,5,3,1);
	}
	
	public void display()
	{
		intCost.setInt(Data.cost);
		setVisible(true);
	}
	
	public void buttonClicked(int index)
	{
		int qty = intQty.getInt();
		int amount = qty*Data.cost;
		intAmount.setInt(amount);
		Data.stock+=qty;
	}
}
