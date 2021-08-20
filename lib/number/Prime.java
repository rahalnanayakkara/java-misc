package number;

public class Prime 
{
	public static boolean isPrime(int a)
	{
		boolean isPrime=true;
		for(int x=1; x<=a; x++) if (a%x==0)
		{
			isPrime = false;
			break;
		}
		if (a==1) isPrime = false;
		return isPrime;
	}
}
