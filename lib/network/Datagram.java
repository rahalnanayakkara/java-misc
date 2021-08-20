package network;

import java.net.*;
import java.util.*;

public class Datagram 
{
	public static void send(byte[] data, String hostName, int portNo) throws Exception
	{
		DatagramSocket socket = new DatagramSocket();
		send(socket,data,hostName,portNo);
		socket.close();
	}
	
	public static void send(DatagramSocket socket, byte[] data, String hostName, int portNo)throws Exception
	{
		InetAddress hostAddress =InetAddress.getByName(hostName);
		DatagramPacket packet = new DatagramPacket(data,data.length,hostAddress,portNo);
		socket.send(packet);
	}
	
	public static byte[] receive(DatagramSocket socket, int maxSize) throws Exception
	{
		DatagramPacket packet = new DatagramPacket(new byte[maxSize],maxSize);
		socket.receive(packet);
		return Arrays.copyOf(packet.getData(), packet.getLength());
	}
	
	public static byte[] receive(int portNo, int maxSize)throws Exception
	{
		DatagramSocket socket = new DatagramSocket(portNo);
		byte[] data = receive(socket,maxSize);
		socket.close();
		return data;
	}
}
