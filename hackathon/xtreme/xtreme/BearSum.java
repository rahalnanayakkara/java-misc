package xtreme;

import java.util.*;

public class BearSum 
{

	public static void main(String[] args) 
	{
		long startTime = System.nanoTime();
		
		int sum, length;
		ArrayList list, compList;
		boolean[] adds;
		Scanner in = new Scanner("13 8\n" + 
				"2 7 6 5 3 9 8 0");
		sum = in.nextInt();
		length = in.nextInt();
		list = new ArrayList<Integer>();
		adds = new boolean[length];
		compList = new ArrayList<Integer>();
		for(int i=0; i<length; i++) 
		{
			list.add(in.nextInt());
			compList.add(sum-(int)list.get(i));
		}
		for(int i=0; i<length; i++) 
		{
			//if ()
		}
		
		
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println(totalTime/1000000);


	}
	
}
