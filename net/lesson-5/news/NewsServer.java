package news;
import java.util.LinkedList;
import java.io.*;
import java.net.*;

import network.DataSocket;
import io.*;

public class NewsServer 
{
	static LinkedList<DataSocket> sockets;
	static ServerSocket serverSocket;
	
	public static void main(String[] args) throws Exception
	{
		sockets = new LinkedList<DataSocket>();
		serverSocket = new ServerSocket(2000);
		serverThread.start();
		String message ="";
		while (!message.equals("bye")) try
		{
			message = Keyboard.readString("Message (\"bye\" to finish)");
			for(DataSocket socket : sockets) socket.out.writeUTF(message);
		}
		catch(Exception e){}
		for(DataSocket socket : sockets) try
		{
			socket.close();
		}
		catch(Exception e){}
		serverThread.stop();
		serverSocket.close();
	}
	
	static Thread serverThread = new Thread()
	{
		public void run()
		{
			while(true) try
			{
				sockets.add(new DataSocket(serverSocket.accept()));
			}
			catch(Exception e){}
		}
	};

}
