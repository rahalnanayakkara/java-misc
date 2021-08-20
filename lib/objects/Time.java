package objects;

public class Time 
{
	int hours, minutes;
	
	public Time(int hours, int minutes)
	{
		this.hours=hours;
		this.minutes=minutes;
	}
	
	public Time(int minutes)
	{
		this(minutes/60,minutes%60);
	}
	
	public int getHours()
	{
		return hours;
	}
	
	public int getMinutes()
	{
		return minutes;
	}
	
	public String getTime()
	{
		String h = hours<=9? "0"+hours : hours+"";
		String m = minutes<=9? "0"+minutes : minutes+"";
		return h+":"+m;
	}
	
	public void setHours(int hours)
	{
		this.hours = hours;
	}
	
	public void setMinutes(int minutes)
	{
		this.minutes = minutes;
	}
	
	public int toMinutes()
	{
		return hours*60+minutes;
	}
	
	public static Time duration(Time startTime, Time endTime)
	{
		int start = startTime.toMinutes(), end = endTime.toMinutes();
		return new Time(end>start?end-start:end-start+24*60);
	}
}
