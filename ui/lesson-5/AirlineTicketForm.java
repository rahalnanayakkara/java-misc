import java.awt.event.*;

import gui.Appearance;
import gui.GridForm;
import gui.fields.MoneyField;
import gui.ints.*;

import javax.swing.*;
import javax.swing.event.*;

public class AirlineTicketForm extends GridForm implements ListSelectionListener, ActionListener
{
	JList lstDestination; 
	JRadioButton rdbFirst, rdbBusiness, rdbEconomy;
	JCheckBox chkReturn;
	IntComboBox cmbTickets;
	MoneyField txtOne, txtAll;
	
	String[] destinations = {"Dubai", "Maldives","Madras","Delhi","Singapore"};
	int[] destinationPrices = {1000000,450000,500000,800000,1200000};
	
	public AirlineTicketForm()
	{
		super("Airline Ticket Prices",10,10,50,60,8,9);
		
		addLabel("Destination",1,1,2,1);
		addLabel("Class :",4,1,2,1);
		addLabel("No of Tickets :",4,5,2,1);
		addLabel("One Ticket",1,7,1,1);
		addLabel("All Tickets",4,7,1,1);
		
		add(lstDestination = new JList(destinations),1,2,2,4);
		
		add(rdbFirst = new JRadioButton("First Class"),5,1,2,1);
		add(rdbBusiness = new JRadioButton("Business Class"),5,2,2,1);
		add(rdbEconomy = new JRadioButton("Economy Class"),5,3,2,1);
		
		add(chkReturn = new JCheckBox("Return Ticket"),4,4,2,1);
		
		add(cmbTickets = new IntComboBox(1,9),6,5,1,1);
		
		add(txtOne = new MoneyField(), 2,7,1,1);
		add(txtAll = new MoneyField(), 5,7,2,1);
		
		txtOne.setInputVerifier(null);
		txtAll.setInputVerifier(null);
		
		ButtonGroup ticketClass = new ButtonGroup();
		ticketClass.add(rdbBusiness);
		ticketClass.add(rdbEconomy);
		ticketClass.add(rdbFirst);
		rdbEconomy.setSelected(true);
		
		lstDestination.setSelectedIndex(0);
		
		rdbBusiness.addActionListener(this);
		rdbEconomy.addActionListener(this);
		rdbFirst.addActionListener(this);
		lstDestination.addListSelectionListener(this);
		chkReturn.addActionListener(this);
		cmbTickets.addActionListener(this);
		
		Appearance.setFont(Appearance.formFont, components);
		calculate();
	}
	
	public void valueChanged(ListSelectionEvent e)
	{
		calculate();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		calculate();
	}
	
	public void calculate()
	{
		double cost = destinationPrices[lstDestination.getSelectedIndex()];
		if (rdbBusiness.isSelected()) cost*=1.5;
		if (rdbFirst.isSelected()) cost*=2;
		if (chkReturn.isSelected()) cost*=1.7;
		txtOne.setValue((int)Math.round(cost));
		txtAll.setValue((int)Math.round(cost*cmbTickets.getSelectedInt()));
	}
	
	public static void main(String[] args) 
	{
		new AirlineTicketForm();
	}

}
