package ticket;

import io.Screen;

import java.net.*;

import network.*;

public class TicketServer 
{
	static int total;
	
	public static class TicketService extends ServiceSocket
	{
		public TicketService(Socket socket) throws Exception
		{
			super(socket);
		}
		
		public void service()
		{
			while (true) try
			{
				int tickets = in.readInt();
				if (tickets==0) break;
				total+=tickets;
				Screen.show("Total", total);
			}
			catch(Exception e){}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		ServerSocket serverSocket = new ServerSocket(2000);
		while (true) try
		{
			new TicketService(serverSocket.accept());
		}
		catch(Exception e){}
	}
}
