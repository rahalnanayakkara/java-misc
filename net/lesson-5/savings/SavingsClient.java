package savings;

import network.DataSocket;

public class SavingsClient implements Options
{
	static DataSocket socket;
	
	public static void connect() throws Exception
	{
		socket = new DataSocket("localHost",1000);
	}
	
	public static int open(String name) throws Exception
	{
		socket.out.writeInt(OPEN);
		socket.out.writeUTF(name);
		return socket.in.readInt();
	}
	
	public static void deposit(int accountNo, int amount) throws Exception
	{
		socket.out.writeInt(DEPOSIT);
		socket.out.writeInt(accountNo);
		socket.out.writeInt(amount);
	}
	
	public static boolean withdraw (int accountNo, int amount) throws Exception
	{
		socket.out.writeInt(WITHDRAW);
		socket.out.writeInt(accountNo);
		socket.out.writeInt(amount);
		return socket.in.readBoolean();
	}
	
	public static boolean transfer (int fromAccount, int toAccount, int amount) throws Exception
	{
		socket.out.writeInt(TRANSFER);
		socket.out.writeInt(fromAccount);
		socket.out.writeInt(toAccount);
		socket.out.writeInt(amount);
		return socket.in.readBoolean();
	}
	
	public static void addInterest (int rate) throws Exception
	{
		socket.out.writeInt(ADD_INTEREST);
		socket.out.writeInt(rate);
	}
	
	public static String getName (int accountNo) throws Exception
	{
		socket.out.writeInt(GET_NAME);
		socket.out.writeInt(accountNo);
		return socket.in.readUTF();
	}
	
	public static Account getAccount(int accountNo) throws Exception
	{
		socket.out.writeInt(GET_ACCOUNT);
		socket.out.writeInt(accountNo);
		return (Account)socket.readObject();
	}
	
	public static Transaction[] getTransactions(int accountNo) throws Exception
	{
		socket.out.writeInt(GET_TRANSACTIONS);
		socket.out.writeInt(accountNo);
		return (Transaction[])socket.readObject();
	}
	
	public static void disconnect() throws Exception
	{
		socket.close();
	}
}
