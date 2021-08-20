package bank;

import io.*;

import java.util.Vector;

public class Bank 
{
	static Vector<Loan> l = new Vector<Loan>();
	static int n=0;
	
	static void save()
	{
		WriteFile.open("Loans.dat");
		WriteFile.writeInt(n);
		WriteFile.writeObject(l);
		WriteFile.close();
	}
	
	static void load()
	{
		ReadFile.open("Loans.dat");
		n = ReadFile.readInt();
		l = (Vector<Loan>)ReadFile.readObject();
		ReadFile.close();
	}
	
	static int inputLoanNo(String message)
	{
		int a=0;
		here : while (true)
		{
			int x = Keyboard.readInt("Enter Loan No :",1,n);
			Screen.nextLine();
			for(int y=1; y<l.size(); y++)
			if (x==l.get(y).loanNo) 
			{
				a = y;
				break here;
			}
			Screen.show("Loan has been completely paid", "");
		}
		return a;
	}
	
	static void newFixed()
	{
		n++;
		Loan loan = new FixedLoan(n);
		loan.inputDetails();
		l.add(loan);
		Screen.show("Loan No :", n);
		Screen.nextLine();
		save();
	}
	
	static void newVariable()
	{
		n++;
		Loan loan = new VariableLoan(n);
		loan.inputDetails();
		l.add(loan);
		Screen.show("Loan No :", n);
		Screen.nextLine();
		save();
	}
	
	static void endMonth()
	{
		Vector<Loan> newL = new Vector<Loan>();
		newL.add(null);
		for(int x=1; x<l.size(); x++)
		{
			Loan loan = l.get(x);
			if (!loan.isFinished())
			{
				loan.nextMonth();
				newL.add(loan);
			}
		}
		l = newL;
		Screen.nextLine();
		save();
	}
	
	public static void main(String[] args)
	{
		l.add(null);
		//load();
		menu : while(true)
		{
			switch (Menu.select("New Fixed Term Loan","New Variable Loan","See Details","Accept Payment","End of Month"))
			{
				case 0 : break menu;
				case 1 : newFixed(); break;
				case 2 : newVariable(); break;
				case 3 : Screen.show("", l.size());l.get(inputLoanNo("Input Loan No :")).displayDetails(); break;
				case 4 : l.get(inputLoanNo("Input Loan No :")).pay(Keyboard.readDouble("Amount to be paid :")); break;
				case 5 : endMonth(); break;
			}
		}
		save();
	}
}
