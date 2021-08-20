package project_euler;

import java.util.ArrayList;

import number.Operations;

public class Problem24 
{
	static int dig = 10;
	static int n=999999;
	
	public static void main(String[] args)
	{
		ArrayList<Integer> num = new ArrayList<Integer>();
		for(int x=0; x<dig; x++) num.add(x);
		
		int[] order = new int[dig];
		int[] answer = new int[dig];
		
		for (int i=dig-1; i>=0; i--)
		{
			int ifact=Operations.factorial(i);
			order[dig-1-i] = n/ifact;
			n=n%ifact;
		}
		
		for(int x=0; x<dig; x++)
		{
			answer[x]=num.get(order[x]);
			num.remove(order[x]);
		}
		
		for(int y: answer) System.out.print(y);
	}

}
