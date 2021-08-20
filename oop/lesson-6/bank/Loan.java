package bank;

import io.*;

public abstract class Loan implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	
	int loanNo;
	double amount, rate;
	String name, nic, address;
	
	Loan(int loanNo)
	{
		this.loanNo = loanNo;
	}
	
	void inputDetails()
	{
		Screen.nextLine();
		name = Keyboard.readString("Name :");
		nic = Keyboard.readString("NIC no. :");
		address = Keyboard.readString("Address :");
		amount = Keyboard.readDouble("Amount Loaned :");
		rate = Keyboard.readDouble("Rate of interest :");
	}
	
	void displayDetails()
	{
		Screen.nextLine();
		Screen.show("Name :", name);
		Screen.show("NIC no, :", nic);
		Screen.show("Address :",address);
		Screen.show("Original Amount :",amount);
		Screen.show("Rate of Interest :", rate);
	}
	
	abstract void pay(double amount);
	
	abstract void nextMonth();
	
	abstract boolean isFinished();
}
