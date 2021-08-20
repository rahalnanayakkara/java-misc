package currency;

public class Currency 
{
	private String name;
	private double buyRate, sellRate;
	
	public Currency(String name)
	{
		this.name = name;
	}
	
	public void setBuyingRate(double rate)
	{
		buyRate = rate;
	}
	
	public void setSellingRate(double rate)
	{
		sellRate = rate;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getBuyingRate()
	{
		return buyRate;
	}
	
	public double getSellingRate()
	{
		return sellRate;
	}
	
	public String toString()
	{
		return name;
	}
}
