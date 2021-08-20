package gui.fields;

import gui.adapters.LimiterToDate;
import gui.validation.DateValidator;

import java.util.*;

import javax.swing.*;

public class DateField extends JTextField 
{
	public DateField()
	{
		setHorizontalAlignment(CENTER);
		LimiterToDate.plugTo(this);
		DateValidator.plugTo(this);
	}
	
	public Date getDate()
	{
		String[] date = getText().split("-");
		int year = Integer.parseInt(date[0])-1900;
		int month = Integer.parseInt(date[1])-1;
		int day = Integer.parseInt(date[2]);
		return new Date(year,month,day);
	}
	
	public void setDate(Date d)
	{
		setText((d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate());
	}
}
