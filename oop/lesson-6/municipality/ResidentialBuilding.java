package municipality;

import io.*;

public class ResidentialBuilding extends Building 
{

	int occupantNo;
	String occupantName;

	ResidentialBuilding(int assessmentNo) 
	{
		super(assessmentNo);
	}
	
	void inputDetails()
	{
		Screen.nextLine();
		occupantName = Keyboard.readString("Name of Occupant :");
		occupantNo = Keyboard.readInt("No of Occupants :");
		Screen.nextLine();
	}
	
	void changeDetails()
	{
		super.changeDetails();
		occupantName = Keyboard.readString("Name of New Occupant :");
		occupantNo = Keyboard.readInt("New No of Occupants :");
		Screen.nextLine();
	}
	
	void showDetails()
	{
		super.showDetails();
		Screen.show("Name of Occupant :", occupantName);
		Screen.show("No of Occupants :", occupantNo);
		Screen.nextLine();
	}
	
	double calculateTax()
	{
		return (area<2000)?area*0.5:area*0.75;
	}
}
