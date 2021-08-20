package project_euler;

import io.*;
import java.util.*;

public class Problem23 
{
	public static void main(String[] args)
	{
		boolean[] isPrime = new boolean[28123];
		
		for (int i=2; i<28123; i++) isPrime[i]=true;
		
		for (int i=2; i<28123; i++)
			for (int j=2*i; j<28123; j+=i)
				isPrime[j]=false;
		List<Integer> primes = new List<Integer>();
		
		
		
		for (int x=0; x<28123; x++) Screen.show("",x+""+isPrime[x]);
	}
}
