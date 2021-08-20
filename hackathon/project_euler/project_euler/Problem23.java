package project_euler;

import io.*;
import java.util.*;

public class Problem23 
{
	static boolean[] sumAbundant = new boolean[28123];
	static boolean[] isPrime = new boolean[28123];
	static boolean[] isAbundant = new boolean[28123];
	static ArrayList<Integer> primes = new ArrayList<Integer>();
	static ArrayList<Integer> abundant = new ArrayList<Integer>();
	static int[] sumDivisors = new int[28123];
	
	public static void main(String[] args)
	{
		
		getPrimes();
		Screen.show("Obtained Primes", "...");
		getSumDivisors();
		Screen.show("Obtained Sum of Divisors", "...");
		
		getAbundant();
		Screen.show("Obtained Abundant Numbers", "...");
		getSumAbundant();
		
		long sum=0L;
		
		for(int x=1; x<28123; x++) if (!sumAbundant[x]) sum+=x;
		
		Screen.show("",sum);
	}
	
	public static void getPrimes()
	{

		
		for (int i=2; i<28123; i++) isPrime[i]=true;
		
		for (int i=2; i<28123; i++)
			for (int j=2*i; j<28123; j+=i)
				isPrime[j]=false;
		
		
		for (int i=2; i<28123; i++) if (isPrime[i])
			primes.add(i);
	}
	
	public static void getSumDivisors()
	{
		for (int x=1; x<28123; x++) sumDivisors[x] = sumDivisors(x);
	}
	
	public static void getAbundant()
	{
		for (int x=1; x<28123; x++) if (sumDivisors[x]>x) abundant.add(x);
		
		for (int x: abundant) isAbundant[x]=true;
	}
	
	public static void getSumAbundant()
	{
		for (int x=1; x<28123; x++) for(int ab : abundant) if(x>ab)
		{
			if (isAbundant[x-ab]) 
			{
				sumAbundant[x] = true;
				break;
			}
		}
		else break;
	}
	
	public static int sumDivisors(int n)
	{
		int N=n;
		int sum=1;
		for(int prime : primes) 
		{
			int count=0;
			while (n%prime==0)
			{
				n=n/prime;
				count++;
			}
			sum*=(int)(Math.pow(prime, count+1)-1)/(prime-1);
		}
		return sum-N;
	}

}
