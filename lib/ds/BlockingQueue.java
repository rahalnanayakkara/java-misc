package ds;

public class BlockingQueue<D> 
{
	private D[] data;
	private int n;
	
	public BlockingQueue(int maxItems)
	{
		data = (D[])(new Object[maxItems]);
	}
	
	public synchronized void in(D datum)
	{
		if (n==data.length)
	try
		{
			wait();
		}
		catch(Exception e){}
		data[n] = datum;
		n++;
		notify();
	}
	
	public synchronized D out()
	{
		if(n==0) try
		{
			wait();
		}
		catch(Exception e){}
		D datum = data[0];
		n--;
		for(int x=0; x<n; x++) data[x] = data[x+1];
		notify();
		return datum;
	}
	
	public synchronized void show()
	{
		for (int x=0; x<n; x++) io.Screen.show("", data[x].toString());
	}
	
	public synchronized int count()
	{
		return n;
	}
	
	public synchronized D get(int index)
	{
		return data[index];
	}
}
