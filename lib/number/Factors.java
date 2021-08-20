package number;

public class Factors
{
	public static int gcd(int a, int b) throws ZeroValueException
	{
		if (a==0||b==0) throw new ZeroValueException();
		else
		{
			a = Math.abs(a);
			b = Math.abs(b);
			while (a!=b) if (a>b) a-=b; else b-=a;
		}
		return a;
	}
	
	public static int gcd(int... a)
	{
		int b = a[0];
		int x=0;
		try 
		{
			for (x=0; x<a.length; x++) b=gcd(b,a[x]);
		}
		catch(ZeroValueException e)
		{
			throw new ZeroElementException(x);
		}
		return b;
	}
	
	public static int lcm(int a, int b) throws ZeroValueException
	{
		return a*b/gcd(a,b);
	}
	
	public static String primeFactors(int a)
	{
		String factors = "";
		int x=2;
		while (a>1)
		{
			if(a%x==0)
			{
				a/=x;
				factors+=x;
				if (a!=1) factors+=" * ";
			}
			else x++;
		}
		return factors;
	}
	
}
