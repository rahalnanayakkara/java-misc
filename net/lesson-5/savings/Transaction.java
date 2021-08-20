package savings;

import java.util.Date;

public class Transaction implements java.io.Serializable
{
	public static final long serialVersionUID = 201706091841L;
	
	public Date date;
	public int id, accountNo, amount;
	public String description;
	
	public Transaction(int id, int accountNo, String description, int amount)
	{
		date = new Date();
		this.id = id;
		this.accountNo = accountNo;
		this.description = description;
		this.amount = amount;
	}
}
