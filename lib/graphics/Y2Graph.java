package graphics;

import graphics.shapes.Line;
import io.Screen;

import java.awt.*;

public class Y2Graph extends Graph 
{
	public double originX, originY, xScale2, yScale2, lastX2, lastY2;
	public Graphics2D g2;
	
	public void setupCoordinateSystem(double left, double right, double top, double bottom, 
			double originX, double originY, double xScale2, double yScale2) 
	{
		this.left=left;
		this.right=right;
		this.top=top;
		this.bottom=bottom;
		this.originX = originX;
		this.originY = originY;
		this.xScale2 = xScale2;
		this.yScale2 = yScale2;
		setupCoordinateSystem(getGraphics());
	}
	
	public void setupCoordinateSystem(Graphics graphics)
	{
		g2 = (Graphics2D)graphics.create();
		super.setupCoordinateSystem(graphics);
		g2.setStroke(new BasicStroke(0));
		g2.scale(xScale, yScale);
		g2.translate(-left, -top);
		g2.translate(originX, originY);
		g2.scale(1/xScale2, 1/yScale2);
	}
	
	public void yAxis2(double from, double to, double x)
	{
		new Line(x,from,x,to).draw(g);
	}
	
	public void yAxis2Numbers(double from, double to, double step, double x)
	{
		for(double y=from; y<=to; y+=step/yScale2)
			text(""+y,xScale(x),yScale(y),RIGHT,CENTER);
	}
	
	public void yAxis2Numbers(int from, int to, int step, double x)
	{
		for(int y=from; y<=to; y+=step)
			text(""+y,xScale(x),yScale(y),RIGHT,CENTER);
	}
	
	public double xScale(double x)
	{
		return x/xScale2+originX;
	}
	
	public double yScale(double y)
	{
		return y/yScale2+originY;
	}
	
	public void point2(double x, double y)
	{
		new Line(x,y,x,y).draw(g2);
		lastX2 = x;
		lastY2 = y;
	}
	
	public void join2(double x, double y)
	{
		new Line(x,y,lastX2,lastY2).draw(g2);
		lastX2 = x;
		lastY2 = y;
	}
	
	public void column2(double x, double width, double height)
	{
		new graphics.shapes.Rectangle(x-width/2,0,x+width/2,height,true).draw(g2);
	}
}
