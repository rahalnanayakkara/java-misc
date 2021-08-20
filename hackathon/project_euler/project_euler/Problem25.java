package project_euler;

public class Problem25 
{
	public static void main(String[] args)
	{
		int x1=1, x2=1, t=0, count=2;
		while (t/1000==0)
		{
			count++;
			t=x1+x2;
			x1=x2;
			x2=t;
		}
		System.out.println(t);
		System.out.println(count);
	}
}
