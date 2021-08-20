package savings;

import io.*;
import java.text.*;

public class SavingsUI 
{
	public static void open()
	{
		String name = Keyboard.readString("Name :");
		int accountNo = SavingsControl.newAccount(name);
		Screen.show("Account No :", accountNo);
	}
	
	public static int inputAccountNo(String message)
	{
		int accountNo = Keyboard.readInt("Account No :");
		String name = SavingsControl.getName(accountNo);
		if (name.equals("Error")) return inputAccountNo(message);
		Screen.show("Name :", name);
		return accountNo;
	}
	
	public static void deposit()
	{
		int accountNo = inputAccountNo("Account to deposit to :");
		int amount = Keyboard.readInt("Amount");
		SavingsControl.deposit(accountNo, amount);
	}
	
	public static void withdraw()
	{
		int accountNo = inputAccountNo("Account to withdraw from :");
		int amount = Keyboard.readInt("Amount :");
		boolean success = SavingsControl.withdraw(accountNo, amount);
		Screen.show("Withdraw", success?"Allowed":"Disallowed");
	}
	
	public static void transfer()
	{
		int fromAccount = inputAccountNo("Withdraw from :");
		int toAccount = inputAccountNo("Transfer to :");
		int amount = Keyboard.readInt("Amount :");
		boolean success = SavingsControl.transfer(fromAccount, toAccount, amount);
		Screen.show("Transfer", success?"Successful":"Disallowed");
	}
	
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public static void see()
	{
		int accountNo = inputAccountNo("Account to see :");
		Account account = SavingsControl.getAccount(accountNo);
		Transaction[] transactions = SavingsControl.getTransactions(accountNo);
		Screen.show("Balance",account.balance);
		for(Transaction transaction: transactions)
			Screen.show(dateFormat.format(transaction.date)+" : "+transaction.description, transaction.amount);
	}
	
	public static void addInterest()
	{
		int rate = Keyboard.readInt("Rate :");
		SavingsControl.addInterest(rate);
	}
	
	public static void main(String[] args)
	{
		SavingsControl.load();
		while (true)
		{
			int option = Menu.select("Open,Deposit,Withdraw,Transfer,See,Add Interest");
			if (option==0) break;
			switch(option)
			{
				case 1: open(); break;
				case 2: deposit(); break;
				case 3: withdraw(); break;
				case 4: transfer(); break;
				case 5: see(); break;
				case 6: addInterest(); break;
			}
		}
		SavingsControl.save();
	}
}
