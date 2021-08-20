package savings;

public class SavingsControl 
{
	public static Store<Account> accounts = new Store<Account>("accounts");
	public static Transactions transactions = new Transactions();
	
	public static void save()
	{
		accounts.save();
		transactions.save();
	}
	
	public static void load()
	{
		accounts.load();
		transactions.load();
	}
	
	public static int newAccount(String name)
	{
		return accounts.append(new Account(name));
	}
	
	public static void deposit(int accountNo, int amount)
	{
		accounts.get(accountNo).deposit(amount);
		transactions.append(accountNo, "Deposit", amount);
	}
	
	public static boolean withdraw(int accountNo, int amount)
	{
		boolean success = accounts.get(accountNo).withdraw(amount);
		if (success) transactions.append(accountNo, "Withdraw", amount);
		return success;
	}
	
	public static boolean transfer(int fromAccount, int toAccount, int amount)
	{
		boolean success = withdraw(fromAccount, amount);
		if(success) deposit(toAccount,amount);
		return success;
	}
	
	public static void addInterest(int rate)
	{
		for( int x=0; x<=accounts.getN(); x++)
		{
			int interest = accounts.get(x).addInterest(rate);
			transactions.append(x, "Interest", interest);
		}
	}
	
	public static String getName(int accountNo)
	{
		if (accountNo<1||accountNo>accounts.getN()) return "Error";
		return accounts.get(accountNo).name;
	}
	
	public static Account getAccount(int accountNo)
	{
		if (accountNo<1||accountNo>accounts.getN()) return null;
		return accounts.get(accountNo);
	}
	
	public static Transaction[] getTransactions(int accountNo)
	{
		if (accountNo<1||accountNo>accounts.getN()) return null;
		return transactions.getForAccount(accountNo);
	}
}
