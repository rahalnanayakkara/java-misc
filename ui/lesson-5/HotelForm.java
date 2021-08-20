import java.awt.event.*;

import gui.*;
import gui.fields.*;
import gui.ints.IntComboBox;

import javax.swing.*;
import javax.swing.event.*;

public class HotelForm extends GridForm implements ActionListener, ListSelectionListener 
{
	JList lstRoom;
	IntComboBox cmbDays;
	MoneyField txtRate, txtAmount;
	
	String[] room = {"Araliya","Nelum","Manel","Olu"};
	int[] roomCost = { 1000,1250,1500,1800};
	
	public HotelForm()
	{
		super("Hotel Booking",10,10,30,40,9,7);
		
		addLabel("Rate",4,1,2,1);
		addLabel("Days",4,3,2,1);
		addLabel("Amount",4,5,2,1);
		
		add(lstRoom = new JList(room),1,1,2,5);
		add(txtRate = new MoneyField(),6,1,2,1);
		add(txtAmount = new MoneyField(),6,5,2,1);
		add(cmbDays = new IntComboBox(1,9),6,3,2,1);
		
		txtRate.displayOnly();
		txtAmount.displayOnly();
		
		lstRoom.setSelectedIndex(0);
		cmbDays.addActionListener(this);
		lstRoom.addListSelectionListener(this);
		Appearance.setFont(Appearance.formFont, components);
		compute();
	}
	
	public void valueChanged(ListSelectionEvent e)
	{
		compute();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		compute();
	}
	
	public void compute()
	{
		txtRate.setValue(roomCost[lstRoom.getSelectedIndex()]*100);
		txtAmount.setValue(txtRate.getValue()*cmbDays.getSelectedInt());
	}
	
	public static void main(String[] args)
	{
		new HotelForm();
		
	}
}
