package savings;

import java.net.*;

public class SavingsServer 
{
	public static void main(String[] args) throws Exception
	{
		SavingsControl.load();
		ServerSocket serverSocket = new ServerSocket(1000);
		while (true) try
		{
			new SavingsServerThread(serverSocket.accept());
		}
		catch (Exception e){}
	}
}
