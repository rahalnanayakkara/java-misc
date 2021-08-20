package gui.fields;

import gui.adapters.LimiterToTime;
import gui.validation.TimeValidator;

import objects.Time;

import javax.swing.*;

public class TimeField extends JTextField 
{
	public TimeField()
	{
		setHorizontalAlignment(CENTER);
		TimeValidator.plugTo(this);
		LimiterToTime.plugTo(this);
	}
	
	public Time getTime()
	{
		String[] time = getText().split(":");
		return new Time(Integer.parseInt(time[0]),Integer.parseInt(time[1]));
	}
	
	public void setTime(Time time)
	{
		setText(time.getTime());
	}
}
