package network;

import java.net.*;
import java.io.*;

public class DataSocket 
{
	public Socket socket;
	public DataOutputStream out;
	public DataInputStream in;
	ObjectInputStream objectIn;
	ObjectOutputStream objectOut;
	
	public DataSocket(Socket socket) throws Exception
	{
		this.socket=socket;
		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
		//objectOut = new ObjectOutputStream(socket.getOutputStream());
		//objectIn = new ObjectInputStream(socket.getInputStream());
	}
	
	public DataSocket(String hostName, int portNo) throws Exception
	{
		this(new Socket(hostName,portNo));
	}
	
	public void writeObject(Object object) throws IOException
	{
		objectOut.writeObject(object);
	}
	
	public Object readObject() throws Exception
	{
		return objectIn.readObject();
	}
	
	public void close() throws Exception
	{
		out.close();
		in.close();
		//objectIn.close();
		//objectOut.close();
		socket.close();
	}
}
