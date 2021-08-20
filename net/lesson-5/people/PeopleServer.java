package people;

import network.*;

import java.net.*;
import java.util.*;

public class PeopleServer 
{
	public static HashMap<String,String> people = new HashMap<String,String>();
	
	static ServerSocket registerServerSocket, queryServerSocket;
	
	public static class RegisterService extends ServiceSocket
	{
		RegisterService(Socket socket) throws Exception
		{
			super(socket);
		}
		
		public void service()
		{ 
			try
			{
				String nic = in.readUTF();
				String name = in.readUTF();
				people.put(nic, name);
			}
			catch(Exception e){}
		}
	}
	
	public static class QueryService extends ServiceSocket
	{
		QueryService(Socket socket) throws Exception
		{
			super(socket);
		}
		
		public void service()
		{
			try
			{
				String nic = in.readUTF();
				String name = people.get(nic);
				if (name == null) out.writeUTF("Name not Found");
				else out.writeUTF(name);
			}
			catch(Exception e){}
		}
	}
	
	public static Thread registerServiceThread = new Thread()
	{
		public void run()
		{
			while (true) try
			{
				new RegisterService(registerServerSocket.accept());
			}
			catch(Exception e){}
		}
	};
	
	public static Thread queryServiceThread = new Thread()
	{
		public void run()
		{
			while (true) try
			{
				new QueryService(queryServerSocket.accept());
			}
			catch(Exception e){}
		}
	};
	
	public static void main(String[] args) throws Exception
	{
		registerServerSocket = new ServerSocket(2000);
		queryServerSocket = new ServerSocket(2001);
		registerServiceThread.start();
		queryServiceThread.start();
	}
}
