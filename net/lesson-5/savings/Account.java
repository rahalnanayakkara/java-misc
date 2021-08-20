package savings;

public class Account implements java.io.Serializable 
{
	public static final long serialVersionUID = 201706091750L;
	
	public String name;
	public int balance;
	
	public Account(String name)
	{
		this.name = name;
	}
	
	public Account(){}
	
	public String toString()
	{
		return name+" : "+balance;
	}
	
	public void deposit(int amount)
	{
		balance+=amount;
	}
	
	public boolean withdraw(int amount)
	{
		if(amount>balance) return false;
		balance-=amount;
		return true;
	}
	
	public int addInterest(int rate)
	{
		int interest = balance*rate/100;
		balance+=interest;
		return interest;
	}
}
