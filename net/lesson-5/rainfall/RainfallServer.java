package rainfall;

import java.net.*;

public class RainfallServer 
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket serverSocket = new ServerSocket(1500);
		while (true) try
		{
			new RainfallService(serverSocket.accept());
		}
		catch(Exception e){}
	}
}
