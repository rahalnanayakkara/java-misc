package materials;

import io.*;

public class Material implements java.io.Serializable
{
	private static final long serialVersionUID = 2L;
	
	public String name, units;
	public int stock, value, dailyReq;
	
	public Material()
	{
		name = Keyboard.readString("Name :");
		units = Keyboard.readString("Units in which it is measured :");
		value = Keyboard.readInt("Value of 1"+units);
		dailyReq = Keyboard.readInt("Daily requirement in "+units);
	}
	
	public String toString()
	{
		return name +" : " +stock+units;
	}
	
	public void recieve(int quantity)
	{
		stock+=quantity;
	}
	
	public boolean issue(int quantity)
	{
		if (canIssue(quantity))
		{
			stock-=quantity;
			return true;
		}
		else return false;
	}
	
	public boolean canIssue(int quantity)
	{
		return (stock>=quantity);
	}
	
	public boolean issueDailyReq()
	{
		return issue(dailyReq);
	}
}