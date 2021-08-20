package municipality;

import io.*;

public abstract class Building implements java.io.Serializable
{

	private static final long serialVersionUID = 22L;
	
	int assessmentNo;
	double area;
	String address, ownerName;
	
	Building(int assessmentNo)
	{
		Screen.nextLine();
		this.assessmentNo = assessmentNo;
		address = Keyboard.readString("Address :");
		ownerName = Keyboard.readString("Name of Owner :");
		area = Keyboard.readDouble("Area of building in sq. ft. :");
		Screen.nextLine();
		Screen.show("Assessment No :", assessmentNo);
		Screen.nextLine();
	}
	
	abstract void inputDetails();
	
	void changeDetails()
	{
		Screen.nextLine();
		area = Keyboard.readDouble("Area of building in sq. ft. :");
	}
	
	void showDetails()
	{
		Screen.nextLine();
		Screen.show("Assessment No :",assessmentNo);
		Screen.show("Address :", address);
		Screen.show("Owner's Name :", ownerName);
		Screen.show("Area :", area);
	}
	
	void changeOwner()
	{
		Screen.nextLine();
		ownerName = Keyboard.readString("New Owner's Name :");
		Screen.nextLine();
	}
	
	abstract double calculateTax();
}
