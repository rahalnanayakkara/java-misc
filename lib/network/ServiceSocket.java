package network;

import java.net.Socket;

public abstract class ServiceSocket extends DataSocket implements Runnable 
{
	
	public ServiceSocket(Socket socket) throws Exception 
	{
		super(socket);
		new Thread(this).start();
	}
	
	public void run()
	{
		try
		{
			service();
			close();
		}
		catch(Exception e){}
	}
	
	public abstract void service() throws Exception;
}