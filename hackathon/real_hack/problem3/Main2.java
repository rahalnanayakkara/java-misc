package problem3;

import java.util.Arrays;

public class Main2 {

	static int n=17;
	static boolean[] isPrime = new boolean[1000001];
	static int primes;
	
	public static void main(String[] args) 
	{
		//System.out.println("HI");
		Arrays.fill(isPrime, true);
		isPrime[0]=false;
		isPrime[1]=false;
		for(int x=2; x<1000001; x++) if (isPrime[x]) for(int y=2; y<1000001/x; y++) isPrime[x*y]=false;
		
		if (n>=7) 
		{
			for(int x=0; x<=n; x++) if (isPrime[x]) primes++;
			switch(primes%3) 
			{
			case 0: System.out.println("Amal");
					break;
			case 1:System.out.println("Bimal");
					break;
			case 2:System.out.println("Chamal");
					break;
			}	
		}
		else if (n>=5) System.out.println("Amal Bimal");
		else System.out.println("Amal Chamal");
	}

}
