package number;

public class Random 
{
	public static int random(int min, int max)
	{
		int range = max-min+1;
		double random = min+(range)*Math.random()-1;
		return (int)Math.ceil(random);
	}
	
	public static int[] random(int min, int max, int n)
	{
		int[] numbers = new int[n];
		loop : for(int x=1; x<=n; x++)
		{
			int val = random(min,max);
			for(int y=1; y<=x; y++)	if(numbers[y-1]==val)
			{
				x--;
				continue loop;
			}
			numbers[x-1]=val;
		}
		return numbers;
	}

}
