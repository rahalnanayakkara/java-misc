package savings;

import io.Keyboard;

import java.net.*;

import network.*;

public class SavingsServerThread extends ServiceSocket implements Options
{
	public SavingsServerThread (Socket socket) throws Exception
	{
		super(socket);
	}
	
	public void newAccount() throws Exception
	{
		String name = in.readUTF();
		int accountNo = SavingsControl.newAccount(name);
		out.writeInt(accountNo);
	}
	
	public void deposit() throws Exception
	{
		int accountNo = in.readInt();
		int amount = in.readInt();
		SavingsControl.deposit(accountNo, amount);
	}
	
	public void withdraw() throws Exception
	{
		int accountNo = in.readInt();
		int amount = in.readInt();
		boolean success = SavingsControl.withdraw(accountNo, amount);
		out.writeBoolean(success);
	}
	
	public void transfer() throws Exception
	{
		int fromAccount = in.readInt();
		int toAccount = in.readInt();
		int amount = in.readInt();
		boolean success = SavingsControl.transfer(fromAccount, toAccount, amount);
		out.writeBoolean(success);
	}
	
	public void addInterest() throws Exception
	{
		int rate = in.readInt();
		SavingsControl.addInterest(rate);
	}
	
	public void getName() throws Exception
	{
		int accountNo = in.readInt();
		String name = SavingsControl.getName(accountNo);
		out.writeUTF(name);
	}
	
	public void getAccount() throws Exception
	{
		int accountNo = in.readInt();
		Account account = SavingsControl.getAccount(accountNo);
		writeObject(account);
	}
	
	public void getTransactions() throws Exception
	{
		int accountNo = in.readInt();
		Transaction[] transactions = SavingsControl.getTransactions(accountNo);
		writeObject(transactions);
	}
	
	public void service() throws Exception
	{
		while (true)
		{
			int service = in.readInt();
			if (service==QUIT) break;
			switch(service)
			{
				case OPEN : newAccount(); break;
				case DEPOSIT : deposit(); break;
				case WITHDRAW : withdraw(); break;
				case TRANSFER : transfer(); break;
				case ADD_INTEREST : addInterest(); break;
				case GET_NAME : getName(); break;
				case GET_ACCOUNT : getAccount(); break;
				case GET_TRANSACTIONS : getTransactions(); break;
			}
		}
		SavingsControl.save();
	}
}
