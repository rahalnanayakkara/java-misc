package savings;

import io.*;

public class Account implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	
	public String name;
	public int balance;
	
	public Account()
	{
		name = Keyboard.readString("Name : ");
	}
	
	public String toString()
	{
		return name+" : "+balance;
	}
	
	public void deposit(int amount)
	{
		balance+=amount;
	}
	
	public boolean canWithdraw(int amount)
	{
		return (amount<=balance);
	}
	
	public void withdraw(int amount)
	{
		balance-=amount;
	}
	
	public void addInterest(int rate)
	{
		balance+=balance*rate/100;
	}
}
