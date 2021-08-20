package savings;

import java.util.*;

public class Transactions extends Store<Transaction>
{
	public Transactions()
	{
		super("transactions");
	}
	
	public Transaction[] getForAccount(int accountNo)
	{
		Vector<Transaction> results = new Vector<Transaction>();
		for(Transaction transaction : data) 
			if(transaction.accountNo == accountNo) results.add(transaction);
		return results.toArray(new Transaction[0]);
	}
	
	public int append(int accountNo, String description, int amount)
	{
		return append(new Transaction(getN(),accountNo,description,amount));
	}
}
