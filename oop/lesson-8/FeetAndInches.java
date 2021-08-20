import io.*;
public class FeetAndInches 
{

	public static void main(String[] args) 
	{
		
		while (true)
		{
			try
			{
				String input = Keyboard.readString("feet:inches");
				int colonPlace = input.indexOf(':');
				int feet = Integer.parseInt(input.substring(0, colonPlace));
				int inches = Integer.parseInt(input.substring(colonPlace+1));
				inches+=feet*12;
				Screen.show("Inches", inches);
				break;
			}
			catch(NumberFormatException e)
			{
				Screen.show("Not in the form", "feet:inches");
			}
			catch(StringIndexOutOfBoundsException e)
			{
				Screen.show("Not in the form", "feet:inches");
			}
		}
	}
}
