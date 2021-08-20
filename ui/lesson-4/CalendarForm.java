import io.Screen;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.event.*;

import gui.*;
import gui.adapters.FocusRefuser;

public class CalendarForm extends GridForm implements ListSelectionListener, ItemListener 
{
	String[] days = {"MON","TUE","WED","THU","FRI","SAT","SUN"};
	String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
	
	int[] monthVal = {0,3,3,6,1,4,6,2,5,0,3,5};
	int[] yearVal = {0,5,3,1,6,4,2};
	
	JTextField[][] calendar = new JTextField[8][7];
	JList lstMonth;
	JComboBox cmbYear;
	
	int month, year;
	
	Font formFont = new Font("Sans Serif",Font.BOLD,20);
	
	public CalendarForm()
	{
		super("Calendar",10,10,70,70,12,9);
		for(int x=1; x<8; x++)
			for(int y=0; y<7; y++)
				{
					add(calendar[x][y]=new JTextField(),x,y+1,1,1);
					calendar[x][y].setHorizontalAlignment(JTextField.CENTER);
					FocusRefuser.plugTo(calendar[x][y]);
				}
		
		add(lstMonth = new JList(months),9,3,2,5);
		addLabel("Select Month",9,2,2,1);
		addLabel("Year",9,1,2,1);
		
		add(cmbYear = new JComboBox(),10,1,1,1);
		for(int y = 2017; y>=1753; y--)
			cmbYear.addItem(""+y);
		
		for(int x=1; x<8; x++) 
		{
			calendar[x][0].setBackground(Color.BLACK);
			calendar[x][0].setForeground(Color.WHITE);
			calendar[x][0].setText(days[x-1]);
		}
		calendar[7][0].setBackground(Color.RED);
		calendar[6][0].setBackground(Color.ORANGE);
		
		lstMonth.setSelectedIndex(0);
		
		Appearance.setFont(formFont, components);
		lstMonth.addListSelectionListener(this);
		cmbYear.addItemListener(this);
		
		loadCalendar();
	}
	
	public int calculate()
	{
		month = lstMonth.getSelectedIndex();
		int monthFig = monthVal[month];
		year = Integer.parseInt(""+cmbYear.getSelectedItem());
		int yearFig = yearVal[(((year%100)/4)%7)]+((year%100)%4);
		int centFig=0;
		switch (year/100+1)
		{
			case 21: centFig = -1; break;
			case 19: centFig = 2; break;
			case 18: centFig = 4; break;
			case 17: centFig = 6; break;
		}
		if (((month==0)||(month==1))&&(year%4==0)&&(year>1900)) yearFig--;
		return (monthFig+yearFig+centFig+1)%7;
	}
	
	public void valueChanged(ListSelectionEvent e)
	{
		clear();
		loadCalendar();
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		clear();
		loadCalendar();
	}
	
	public void loadCalendar()
	{
		int day = calculate();
		if (day==0) day=7;
		int days=0;
		int row=1;
		if ((month%2==0&&month<=6)||(month%2==1&&month>6)) days=31;
		else if (month!=1) days=30;
		else if (month==1&&(year%4==0)) days=29;
		else days=28;
		for (int x=1; x<=days; x++)
		{
			calendar[day][row].setText(""+x);
			day++;
			if (day==8)
			{
				day=1;
				row++;
			}
		}
	}
	
	public void clear()
	{
		for(int x=1; x<=7; x++)
			for(int y=1; y<=6; y++)
				calendar[x][y].setText("");
	}
	
	public static void main(String[] args)
	{
		new CalendarForm();
	}
}
