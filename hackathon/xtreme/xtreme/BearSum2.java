package xtreme;

import java.util.*;

public class BearSum2 
{

	public static void main(String[] args) 
	{
		long startTime = System.nanoTime();
		
		Scanner in = new Scanner("10\n" + 
				"12 4\n" + 
				"5 0 8 9\n" + 
				"13 4\n" + 
				"10 0 6 1\n" + 
				"17 3\n" + 
				"2 9 4\n" + 
				"0 8\n" + 
				"2 7 6 5 3 9 8 0\n" + 
				"4 0\n" + 
				"\n" + 
				"4 0\n" + 
				"\n" + 
				"7 7\n" + 
				"8 10 9 7 4 6 2\n" + 
				"13 3\n" + 
				"6 7 1\n" + 
				"2 7\n" + 
				"10 7 5 1 0 3 9\n" + 
				"0 1\n" + 
				"-1");
		int t = in.nextInt();
		for(int x=0; x<t; x++) 
		{
			
			int length, sum, n1=0, n2=0;
			int[] list;
			boolean found=false;
			
			sum = in.nextInt();
			length = in.nextInt();
			
			if (length!=0)
			{
				list = new int[length];
				
				for(int i=0; i<length; i++)
				list[i]=in.nextInt();
				
				here : for(int i=0; i<length; i++) 
				{
					for(int j=0; j<i; j++) if(list[j]+list[i]==sum)
					{
						if (list[i]>list[j]) 
						{
							n1=list[j];
							n2=list[i];
						}
						else
						{
							n1=list[i];
							n2=list[j];
						}
						found = true;
						break here;
					}
				}
			}
			
			if (found) System.out.println(n1+" "+n2);
			else System.out.println("!OK");
		}
		
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println(totalTime/1000000);
	}

}
