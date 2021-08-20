package rainfall;
import network.*;

import java.io.*;
import java.net.*;

public class RainfallService extends ServiceSocket 
{
	RainfallService(Socket socket) throws Exception
	{
		super(socket);
	}
	
	public void service() throws Exception
	{
		String town = in.readUTF();
		File file = new File("rainfall\\"+town+".dat");
		if(file.exists())
		{
			out.writeInt(0);
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			for(int x=0; x<12; x++) out.writeInt(in.readInt());
			in.close();
			fileIn.close();
		}
		else out.writeInt(-1);
	}
}
