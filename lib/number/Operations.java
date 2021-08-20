package number;

public class Operations 
{
	public static int dif(int a, int b)
	{
		return (a>b)?a-b:b-a;
	}
	
	public static int over(int a, int b)
	{
		return (a>b) ? a-b : 0;
	}
	
	public static int min(int... a)
	{
		int min=a[0];
		for(int b : a) if (b<min) min = b;
		return min;
	}
	
	public static int max(int... a)
	{
		int max=a[0];
		for(int b : a) if (b>max) max = b;
		return max;
	}
	
	public static int factorial(int n)
	{
		int fact=1;
		for (int i=2; i<=n; i++)
			fact*=i;
		return fact;
	}
}
