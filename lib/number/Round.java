package number;

public class Round 
{
	public static double near(double number, double accuracy)
	{
		int factor = (int)Math.pow(10, accuracy);
		return Math.round(number*factor)*1.0/factor;
	}
	
	public static double up(double number, double accuracy)
	{
		int factor = (int)Math.pow(10, accuracy);
		return Math.ceil(factor*number)*1.0/factor;
	}
	
	public static double down(double number, double accuracy)
	{
		int factor = (int)Math.pow(10, accuracy);
		return Math.floor(factor*number)*1.0/factor;
	}
}
