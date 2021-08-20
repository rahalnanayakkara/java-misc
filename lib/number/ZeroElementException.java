package number;

public class ZeroElementException extends RuntimeException 
{
	public int index;
	
	public ZeroElementException(int index)
	{
		this.index = index;
	}
	
	public String toString()
	{
		return "ZeroElementException at index : "+index;
	}
}
