package test;

import io.Keyboard;
import network.Datagram;
import number.Random;

public class Test 
{
	public static void main(String[] args) throws Exception
	{
		while (true)
		{
			String s1 = ""+Random.random(0, 50);
			String s2 = ""+Random.random(0, 50);
			String s3 = ""+Random.random(0, 50);
		
			String message = "a"+s1+"ab"+s2+"bc"+s3+"c";
			Datagram.send(message.getBytes(), "localhost", 2000);
		}
	}
}
