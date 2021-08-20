package municipality;

import io.*;
import java.util.Vector;

public class Municipality 
{
	static int n;
	static Vector<Building> b = new Vector<Building>();
	
	static void save()
	{
		WriteFile.open("Municipality.dat");
		WriteFile.writeObject(b);
		WriteFile.close();
	}
	
	static void load()
	{
		ReadFile.open("Municipality.dat");
		n = ReadFile.readInt();
		b = (Vector<Building>)ReadFile.readObject();
		ReadFile.close();
	}
	
	static int inputAssessmentNo(String message)
	{
		Screen.nextLine();
		int x = Keyboard.readInt(message,1,n);
		Screen.show("Address :", b.get(x).address);
		Screen.nextLine();
		return x;
	}
	
	static void list()
	{
		Screen.nextLine();
		for(int x=1; x<=n; x++)
		{
			Building a = b.get(x);
			Screen.display(a.assessmentNo+"",a.ownerName,a.calculateTax()+"");
		}
		Screen.nextLine();
	}
	
	public static void main(String[] args)
	{
		b.add(null);
		//load();
		loop : while (true)
		{
			int x = Menu.select("New House", "New Commercial Building", 
					"New Religious Building", "Input Details","Change owner of a building", "Display details",
					"Change details","List");
			switch(x)
			{
				case 0: break loop;
				case 1: n++; b.add(new ResidentialBuilding(n)); save();break;
				case 2: n++; b.add(new CommercialBuilding(n)); save(); break;
				case 3: n++; b.add(new ReligiousBuilding(n)); save(); break;
				case 4: b.get(inputAssessmentNo("Enter Assessment No. :")).inputDetails(); save(); break;
				case 5: b.get(inputAssessmentNo("Enter Assessment No. :")).changeOwner(); save(); break;
				case 6: b.get(inputAssessmentNo("Enter Assessment No. :")).showDetails(); save(); break;
				case 7: b.get(inputAssessmentNo("Enter Assessment No. :")).changeDetails(); save(); break;
				case 8: list(); break;
			}
		}
		save();
	}
}
