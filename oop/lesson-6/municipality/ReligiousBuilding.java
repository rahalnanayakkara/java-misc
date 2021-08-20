package municipality;

import io.*;

public class ReligiousBuilding extends Building 
{
	String religion, placeName;
	
	ReligiousBuilding(int assessmentNo)
	{
		super(assessmentNo);
	}
	
	void inputDetails()
	{
		Screen.nextLine();
		religion = Keyboard.readString("Religion :");
		placeName = Keyboard.readString("Place Name :");
		Screen.nextLine();
	}
	
	void changeDetails()
	{
		super.changeDetails();
		placeName = Keyboard.readString("New Place Name :");
		Screen.nextLine();
	}
	
	void showDetails()
	{
		super.showDetails();
		Screen.show("Religion :", religion);
		Screen.show("Name of place :", placeName);
		Screen.nextLine();
	}
	
	double calculateTax()
	{
		return 0;
	}
}
