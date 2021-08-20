package bank;

import io.*;

public class VariableLoan extends Loan 
{
	private static final long serialVersionUID = 3L;
	double currentBalance=0, minDue=0;
	
	VariableLoan(int loanNo)
	{
		super(loanNo);
	}
	
	void inputDetails()
	{
		super.inputDetails();
		currentBalance=amount;
	}
	
	void displayDetails()
	{
		super.displayDetails();
		Screen.show("Current Balance :", currentBalance);
		Screen.show("Minimum amount due :", minDue);
		Screen.nextLine();
	}
	
	void pay(double amount)
	{
		currentBalance-=amount;
		minDue = minDue-amount;
		Screen.nextLine();
	}
	
	void nextMonth()
	{
		currentBalance*=(100+rate)/100;
		minDue*=(minDue<0)?1:(100+rate)/100;
		minDue+=currentBalance*0.05;
	}
	
	boolean isFinished()
	{
		return (currentBalance<=0);
	}
}
