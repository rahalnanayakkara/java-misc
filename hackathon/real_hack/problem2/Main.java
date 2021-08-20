package problem2;

import java.util.Arrays;

public class Main {

	static boolean[] isPrime = new boolean[1000001];
	
	static void main(String[] args) 
	{
		System.out.println("HI");
		Arrays.fill(isPrime, true);
		isPrime[0]=false;
		isPrime[1]=false;
		for(int x=2; x<1000001; x++) if (isPrime[x]) for(int y=2; y<1000001/x; y++) isPrime[y]=false;
		//for(int x=0; x<100; x++) System.out.println(x+" "+isPrime[x]);
	}

}
