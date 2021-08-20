package municipality;

import io.*;

public class CommercialBuilding extends Building 
{
	String businessName, businessNature, businessRegNo;
	
	CommercialBuilding(int assessmentNo)
	{
		super(assessmentNo);
	}
	
	void inputDetails()
	{
		Screen.nextLine();
		businessName = Keyboard.readString("Name of Business :");
		businessRegNo = Keyboard.readString("Business Registration No :");
		businessNature = Keyboard.readString("Nature of Business :");
		Screen.nextLine();
	}
	
	void changeDetails()
	{
		super.changeDetails();
		businessName = Keyboard.readString("New Name of Business :");
		businessRegNo = Keyboard.readString("New Business Registration No :");
		businessNature = Keyboard.readString("Nature of Business :");
		Screen.nextLine();
	}
	
	void showDetails()
	{
		super.showDetails();
		Screen.show("Business Name :", businessName);
		Screen.show("Business Registration No. :", businessRegNo);
		Screen.show("Nature of Business :", businessNature);
		Screen.nextLine();
	}
	
	double calculateTax()
	{
		return area*1.5;
	}
}
