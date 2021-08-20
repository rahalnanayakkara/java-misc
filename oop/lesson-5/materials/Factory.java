package materials;

import io.*;

public class Factory 
{
	public static int max =100, n=0;
	public static Material[] m = new Material[max];
	
	public static void save()
	{
		WriteFile.open("Factory.dat");
		WriteFile.writeInt(n);
		for(int x=1; x<=n; x++) WriteFile.writeObject(m[x]);
		WriteFile.close();
	}
	
	public static void load()
	{
		ReadFile.open("Factory.dat");
		n = ReadFile.readInt();
		for(int x=1; x<=n; x++) m[x]=(Material)ReadFile.readObject();
		ReadFile.close();
	}
	
	public static int inputMaterialNo(String message)
	{
		int x = Keyboard.readInt(message,1,n);
		Screen.show("Material :",m[x].name);
		return x;
	}
	
	public static void newMaterial()
	{
		Screen.nextLine();
		n++;
		m[n] = new Material();
		Screen.show("Material code :", n);
		Screen.nextLine();
		save();
	}
	
	public static void receiveMaterial()
	{
		Screen.nextLine();
		int x = inputMaterialNo("Code of material to be recieved :");
		m[x].recieve(Keyboard.readInt("Amount to be received in "+m[x].units));
		Screen.show(m[x].name+" : ",m[x].stock+" "+m[x].units);
		Screen.nextLine();
		save();
	}
	
	public static void issueMaterial()
	{
		Screen.nextLine();
		int x = inputMaterialNo("Code of material to be issued");
		int quantity = Keyboard.readInt("Amount to be issued in "+m[x].units);
		if (m[x].issue(quantity)) {}
		else 
		{
			Screen.show("Cannot issue ",quantity+" "+m[x].units);
			Screen.show("Stock only", m[x].stock+" "+m[x].units);
		}
		Screen.nextLine();
		save();
	}
	
	public static void issueDailyReq()
	{
		Screen.nextLine();
		for(int x=1; x<=n; x++) 
			if ( m[x].issueDailyReq() ) {}
			else 
			{
				Screen.show("Cannot issue Daily Requirement of ",m[x].dailyReq+" "+m[x].units+" for "+m[x].name);
				Screen.show("Stock only", m[x].stock+" "+m[x].units);
			}
		Screen.nextLine();
		save();
	}
	
	public static void list()
	{
		Screen.nextLine();
		for( int x=1; x<=n; x++)
			Screen.show(m[x].name+" :", m[x].stock+" "+m[x].units);
		Screen.nextLine();
	}
	
	
	public static void changeDailyReq()
	{
		Screen.nextLine();
		int x = inputMaterialNo("Code of material");
		Screen.show("Current dail requirement",m[x].dailyReq+" "+m[x].units);
		m[x].dailyReq = Keyboard.readInt("New Daily requirement in "+m[x].units+" :");
		Screen.nextLine();
		save();
	}	
	
	public static void main(String[] args)
	{
		load();
		loop : while(true)
		{
			int x = Menu.select("New Material","Receive Material","Issue Daily Requirement",
					"Show list","Issue a Material","Change Daily Requirement");
			switch(x)
			{
				case 0: break loop;
				case 1: newMaterial(); break;
				case 2: receiveMaterial(); break;
				case 3: issueDailyReq(); break;
				case 4: list(); break;
				case 5: issueMaterial(); break;
				case 6: changeDailyReq(); break;
			}
		}
		save();
	}
}