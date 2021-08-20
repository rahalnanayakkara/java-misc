package items;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import io.ReadFile;
import io.WriteFile;
import gui.*;
import gui.buttons.*;
import gui.ints.*;
import gui.validation.Validator;

public class ItemForm extends GridForm implements ButtonListener, IntListener, WindowListener
{
	IntField intStock, intCost, intPrice;
	IndexedButton btnBuy, btnSell;
	BuyDialog buyDialog;
	SellDialog sellDialog;
	
	ItemForm()
	{
		super("ItemTrading",10,10,30,40,7,9);
		
		addLabel("Stock",1,1,2,1);
		addLabel("Cost",1,3,2,1);
		addLabel("Price",1,5,2,1);
		
		add(intStock = new IntField(),4,1,2,1);
		add(intCost = new IntField(),4,3,2,1);
		add(intPrice = new IntField(),4,5,2,1);
		
		intStock.setEnabled(false);
		intCost.setIntListener(this);
		intPrice.setIntListener(this);
		
		add(btnBuy = new IndexedButton(1,"Buy",this),1,7,2,1);
		add(btnSell = new IndexedButton(2,"Sell",this),4,7,2,1);
		
		buyDialog = new BuyDialog(this);
		sellDialog = new SellDialog(this);
		
		intStock.setInt(Data.stock);
		intCost.setInt(Data.cost);
		intPrice.setInt(Data.price);
		
		addWindowListener(this);
		Validator.valid=true;
	}
	
	public void intValueChanged(IntEvent e)
	{
		if (e.getSource()==intPrice) Data.price = intPrice.getInt();
		if (e.getSource()==intCost) Data.cost = intCost.getInt();
	}
	
	public void buttonClicked(int index)
	{
		switch(index)
		{
			case 1: buyDialog.display(); break;
			case 2: sellDialog.display(); break;
		}
		intStock.setInt(Data.stock);
	}
	
	public void windowClosing(WindowEvent e) 
	{
		WriteFile.open("Item.dat");
		WriteFile.writeInt(Data.stock);
		WriteFile.writeInt(Data.cost);
		WriteFile.writeInt(Data.price);
		WriteFile.close();
	}

	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
	
	public static void main(String[] args)
	{
		ReadFile.open("Item.dat");
		Data.stock = ReadFile.readInt();
		Data.cost = ReadFile.readInt();
		Data.price = ReadFile.readInt();
		ReadFile.close();
		new ItemForm();
	}
}
