package graphics;

import java.awt.*;

public class Graph extends DrawingCanvas 
{
	public static Color defaultAxisColor = Color.GREEN;
	public static Color defaultGridColor = Color.RED;
	public static Color defaultDataColor = Color.BLACK;
	public static Color defaultTitleColor = Color.BLUE;
	public static Color defaultTextColor = Color.BLUE;
	
	public static Font defaultAxisFont = new Font("Tahoma",Font.PLAIN,14);
	public static Font defaultTitleFont = new Font("Courier",Font.BOLD,20);
	public static Font defaultTextFont = new Font("Sans Serif",Font.BOLD,16);
	
	public Color axisColor = defaultAxisColor;
	public Color gridColor = defaultGridColor;
	public Color dataColor = defaultDataColor;
	public Color titleColor = defaultTitleColor;
	public Color textColor = defaultTextColor;
	
	public Font axisFont = defaultAxisFont;
	public Font titleFont = defaultTitleFont;
	public Font textFont = defaultTextFont;
	
	public void axis()
	{
		setColor(axisColor);
		setFont(axisFont);
	}
	
	public void data()
	{
		setColor(dataColor);
	}
	
	public void grid()
	{
		setColor(gridColor);
	}
	
	public void title()
	{
		setColor(titleColor);
		setFont(titleFont);
	}
	
	public void text()
	{
		setColor(textColor);
		setFont(textFont);
	}
	
	public void xAxis(double from, double to)
	{
		line(from,0,to,0);
	}
	
	public void yAxis(double from, double to)
	{
		line(0,from,0,to);
	}
	
	public void xAxisNumbers(double from, double to, double step, double y)
	{
		for (double x=from; x<=to; x+=step)
			text(""+x,x,y,CENTER,TOP);
	}
	
	public void xAxisNumbers(int from, int to, int step, double y)
	{
		for (int x=from; x<=to; x+=step)
			text(""+x,x,y,CENTER,TOP);
	}
	
	public void yAxisNumbers(double from, double to, double step, double x)
	{
		for(double y=from; y<=to; y+=step)
			text(""+y,x,y,RIGHT,CENTER);
	}
	
	public void yAxisNumbers(int from, int to, int step, double x)
	{
		for(int y=from; y<=to; y+=step)
			text(""+y,x,y,RIGHT,CENTER);
	}
	
	public void xAxisLabels(double from, double to, double y, String...labels)
	{
		double step = (to-from)/(labels.length-1);
		for(int a=0; a<labels.length; a++)
			text(labels[a],from+a*step,y,CENTER,TOP);
	}
	
	public void xAxisLabels(double from, double to, double y, String labels)
	{
		xAxisLabels(from,to,y,labels.split(","));
	}
	
	public void yAxisLabels(double from, double to, double x, String...labels)
	{
		double step = (to-from)/(labels.length-1);
		for(int a=0; a<labels.length; a++)
			text(labels[a],x,from+step*a,RIGHT,CENTER);
	}
	
	public void yAxisLabels(double from, double to, double x, String labels)
	{
		yAxisLabels(from,to,x,labels.split(","));
	}
	
	public void xGrid(double from, double to, double step, double yMin, double yMax)
	{
		for(double x=from; x<=to; x+=step)
			line(x,yMin,x,yMax);
	}
	
	public void yGrid(double from, double to, double step, double xMin, double xMax)
	{
		for(double y=from; y<=to; y+=step)
			line(xMin,y,xMax,y);
	}
	
	public double lastX, lastY;
	
	public void point(double x, double y)
	{
		line(x,y,x,y);
		lastX=x;
		lastY=y;
	}
	
	public void join(double x, double y)
	{
		line(x,y,lastX,lastY);
		lastX=x;
		lastY=y;
	}
	
	public void column (double x, double width, double height)
	{
		box(x-width/2,0,x+width/2,height,true);
	}
	
	public void bar (double y, double height, double length)
	{
		box(0,y-height/2,length,y+height/2,true);
	}
}