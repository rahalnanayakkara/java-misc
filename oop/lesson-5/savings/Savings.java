package savings;

import io.*;

public class Savings 
{
	public static int max=100, n=0;
	public static Account[] a = new Account[max];
	
	public static void save()
	{
		WriteFile.open("savings.dat");
		WriteFile.writeInt(n);
		for(int x=1; x<=n; x++) WriteFile.writeObject(a[x]);
		WriteFile.close();
	}
	
	public static void load()
	{
		ReadFile.open("savings.dat");
		n = ReadFile.readInt();
		for(int x=1; x<=n; x++) a[x]=(Account)(ReadFile.readObject());
		ReadFile.close();
	}
	
	public static int inputAccountNo(String message)
	{
		int no = Keyboard.readInt(message,1,n);
		Screen.show("Name :", a[no].name);
		Screen.nextLine();
		return no;
	}
	
	public static void open()
	{
		Screen.nextLine();
		n++;
		a[n] = new Account();
		Screen.show("Acc. No. :", n);
		Screen.nextLine();
		save();
	}
	
	public static void deposit()
	{
		Screen.nextLine();
		int no = inputAccountNo("Account to deposit to :");
		a[no].deposit(Keyboard.readInt("Enter amount to deposit :"));
		Screen.show("Deposit successful to Acc. no.",no);
		Screen.show("Account balance", a[no].balance);
		Screen.nextLine();
		save();
	}
	
	public static int withdraw()
	{
		Screen.nextLine();
		int no = inputAccountNo("Account to withdraw from :");
		int amount = Keyboard.readInt("Amount to withdraw :");
		if (a[no].canWithdraw(amount)) 
		{
			a[no].withdraw(amount);
			Screen.show("Withdraw succesful from Acc. no.",no);
			Screen.show("Remaining balance is :",a[no].balance);
		}
		else
		{
			Screen.show("Insufficient account balance", "");
			Screen.show("Account balance is", a[no].balance);
		}
		Screen.nextLine();
		save();
		return amount;
	}
	
	public static void transfer()
	{
		Screen.nextLine();
		int amount = withdraw();
		int no = inputAccountNo("Account to transfer to");
		a[no].deposit(amount);
		Screen.show("Transferred an amount of ",amount+" to Acc. no. "+no);
		Screen.nextLine();
		save();
	}
	
	public static void see()
	{
		Screen.nextLine();
		int x = inputAccountNo("Enter Acc. No.");
		Screen.show(a[x].toString(),"");
		Screen.nextLine();
	}
	
	public static void list()
	{
		Screen.nextLine();
		for(int x=1; x<=n; x++) Screen.show(x+".",a[x].toString());
		Screen.nextLine();
	}
	
	public static void addInterest()
	{
		Screen.nextLine();
		int rate = Keyboard.readInt("Rate of interest");
		for(int x=1; x<=n; x++) a[x].addInterest(rate);
		save();
	}
	
	public static void main(String[] args) 
	{
		load();
		menu : while (true)
		{
			int n = Menu.select("New Account","Deposit","Withdraw","Transfer","See","List","Add Interest");
			switch(n)
			{
				case 0: break menu;
				case 1: open(); break;
				case 2: deposit(); break;
				case 3: withdraw(); break;
				case 4: transfer(); break;
				case 5: see(); break;
				case 6: list(); break;
				case 7: addInterest(); break;
			}
			Screen.nextLine();
		}
		save();
	}

}
