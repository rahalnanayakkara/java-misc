package bank;

import io.*;

public class FixedLoan extends Loan 
{

	private static final long serialVersionUID = 2L;
	int ninst, paid=0;
	double arrears=0;
	double installment;
	
	FixedLoan(int loanNo)
	{
		super(loanNo);
	}
	
	void inputDetails()
	{
		super.inputDetails();
		ninst = Keyboard.readInt("Number of installments :");
		installment = amount/ninst;
		Screen.nextLine();
	}
	
	void displayDetails()
	{
		super.displayDetails();
		Screen.show("Number of installments", ninst);
		Screen.show("Installment size :", installment);
		Screen.show("No. of Paid installments :", paid);
		Screen.show("Arrears :", arrears);
		Screen.nextLine();
	}
	
	void pay(double amount)
	{
		arrears-=amount;
		Screen.nextLine();
	}
	
	void nextMonth()
	{
		paid++;
		arrears *= (arrears>0)?(100+rate)/100:1;
		arrears+=installment*(100+rate)/100;
	}
	
	boolean isFinished()
	{
		return (paid>=ninst)&&(arrears==0);
	}
}
