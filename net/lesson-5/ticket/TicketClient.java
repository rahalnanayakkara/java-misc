package ticket;

import io.Keyboard;
import io.Screen;
import network.DataSocket;

public class TicketClient 
{
	public static void main(String[] args) throws Exception
	{
		DataSocket socket = new DataSocket("localHost",2000);
		while (true) try
		{
			int tickets = Keyboard.readInt("Tickets :");
			if (tickets==0) break;
			socket.out.writeInt(tickets);
			Screen.show("Amount :",tickets*100);
		}
		catch(Exception e){}
		socket.close();
	}
}
