package savings;

import io.*;

import java.text.*;

public class SavingsUI 
{
	public static void open() throws Exception
	{
		String name = Keyboard.readString("Name :");
		int accountNo = SavingsClient.open(name);
		Screen.show("Account No :", accountNo);
	}
	
	public static int inputAccountNo(String message) throws Exception
	{
		int accountNo = Keyboard.readInt("Account No :");
		String name = SavingsClient.getName(accountNo);
		if (name.equals("Error")) return inputAccountNo(message);
		Screen.show("Name :", name);
		return accountNo;
	}
	
	public static void deposit() throws Exception
	{
		int accountNo = inputAccountNo("Account to deposit to :");
		int amount = Keyboard.readInt("Amount");
		SavingsClient.deposit(accountNo, amount);
	}
	
	public static void withdraw() throws Exception
	{
		int accountNo = inputAccountNo("Account to withdraw from :");
		int amount = Keyboard.readInt("Amount :");
		boolean success = SavingsClient.withdraw(accountNo, amount);
		Screen.show("Withdraw", success?"Allowed":"Disallowed");
	}
	
	public static void transfer() throws Exception
	{
		int fromAccount = inputAccountNo("Withdraw from :");
		int toAccount = inputAccountNo("Transfer to :");
		int amount = Keyboard.readInt("Amount :");
		boolean success = SavingsClient.transfer(fromAccount, toAccount, amount);
		Screen.show("Transfer", success?"Successful":"Disallowed");
	}
	
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public static void getTransactions() throws Exception
	{
		int accountNo = inputAccountNo("Account to see :");
		Account account = SavingsClient.getAccount(accountNo);
		Transaction[] transactions = SavingsClient.getTransactions(accountNo);
		Screen.show("Balance :", account.balance);
		for(Transaction transaction: transactions)
			Screen.show(dateFormat.format(transaction.date)+" : "+transaction.description, transaction.amount);
	}
	
	public static void getName() throws Exception
	{
		int accountNo = inputAccountNo("Account No :");
		String name = SavingsClient.getName(accountNo);
		Screen.show("Name", name);
	}
	
	public static void getAccount() throws Exception
	{
		int accountNo = inputAccountNo("Account No :");
		Account account = SavingsClient.getAccount(accountNo);
		Screen.show(account.toString(), "");
	}
	
	public static void addInterest() throws Exception
	{
		int rate = Keyboard.readInt("Rate :");
		SavingsClient.addInterest(rate);
	}
	
	public static void main(String[] args) throws Exception
	{
		SavingsClient.connect();
		while (true)
		{
			int option = Menu.select("Open,Deposit,Withdraw,Transfer,Get Account Name,Get Account Details,"
					+ "See Transactions,Add Interest");
			if (option==0) break;
			switch(option)
			{
				case 1: open(); break;
				case 2: deposit(); break;
				case 3: withdraw(); break;
				case 4: transfer(); break;
				case 5: getName(); break;
				case 6: getAccount(); break;
				case 7: getTransactions(); break;
				case 8: addInterest(); break;
			}
		}
		SavingsClient.disconnect();
	}
}
