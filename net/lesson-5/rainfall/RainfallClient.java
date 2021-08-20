package rainfall;

import io.Keyboard;
import io.Screen;
import network.*;

public class RainfallClient 
{
	static String[] month = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"}; 
	
	public static void main(String[] args) throws Exception
	{
		DataSocket socket = new DataSocket("localHost",1500);
		String town = Keyboard.readString("Town Name");
		socket.out.writeUTF(town);
		int result = socket.in.readInt();
		if(result==-1) Screen.show("Unavailable", "");
		else for(int x=0; x<12; x++) Screen.show(month[x], socket.in.readInt());
		socket.close();
	}

}
