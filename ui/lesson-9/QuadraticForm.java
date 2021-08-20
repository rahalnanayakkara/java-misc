
import graphics.*;
import gui.*;
import gui.buttons.*;
import gui.ints.*;
import io.Screen;

import java.awt.*;

import javax.swing.JLabel;

public class QuadraticForm extends GridForm implements ButtonListener
{
	IntField txtXMin, txtXMax, txtYMin, txtYMax, txtXStep, txtYStep, txtA, txtB, txtC;
	QuadraticGraph g;
	JLabel lblTitle;
	
	int xMin, xMax, yMin, yMax, xStep, yStep, a, b, c;
	
	public QuadraticForm()
	{
		super("Quadratic Graph",10,10,50,70,14,28);
		
		add(g = new QuadraticGraph(),0,3,14,16);
		g.setBackground(Color.WHITE);
		
		addLabel("X :-",1,20,1,1);
		addLabel("Y :-",1,22,1,1);
		addLabel("Min =",2,20,1,1);
		addLabel("Min =",2,22,1,1);
		addLabel("Max =",6,20,1,1);
		addLabel("Max =",6,22,1,1);
		addLabel("Step =",10,20,1,1);
		addLabel("Step =",10,22,1,1);
		addLabel("y =",2,24,1,1);
		addLabel("   x^2",5,24,1,1);
		addLabel("   +",6,24,1,1);
		addLabel("   x",9,24,1,1);
		addLabel("   +",10,24,1,1);
		
		lblTitle = addLabel("Graph",4,1,6,1);
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		
		add(txtXMin = new IntField(),3,20,2,1);
		add(txtYMin = new IntField(),3,22,2,1);
		add(txtXMax = new IntField(),7,20,2,1);
		add(txtYMax = new IntField(),7,22,2,1);
		add(txtXStep = new IntField(),11,20,2,1);
		add(txtYStep = new IntField(),11,22,2,1);
		add(txtA = new IntField(),3,24,2,1);
		add(txtB = new IntField(),7,24,2,1);
		add(txtC = new IntField(),11,24,2,1);
		
		add(new IndexedButton(1,"Show",this,false),5,26,4,2);
		
		txtXMin.setInt(-5);
		txtXMax.setInt(5);
		txtYMin.setInt(-10);
		txtYMax.setInt(20);
		txtXStep.setInt(1);
		txtYStep.setInt(2);
		txtA.setInt(1);
		txtB.setInt(0);
		txtC.setInt(0);
		
		xMin = txtXMin.getInt();
		xMax = txtXMax.getInt();
		yMin = txtYMin.getInt();
		yMax = txtYMax.getInt();
		xStep = txtXStep.getInt();
		yStep = txtYStep.getInt();
		a = txtA.getInt();
		b = txtB.getInt();
		c = txtC.getInt();
		g.setupCoordinateSystem(xMin-xStep, xMax+xStep, yMax+yStep, yMin-yStep);
		g.draw();
		g.repaint();
		
		lblTitle.setText(toString());
		
		Appearance.setFont(Appearance.formFont, this);
	}
	
	public class QuadraticGraph extends Graph
	{
		public void draw()
		{
			axisColor = Color.BLACK;
			
			setColor(defaultGridColor);
			for(double x=xMin; x<=xMax; x+=xStep)
				line(x,yMin,x,yMax);
			for(double y=yMin; y<=yMax; y+=yStep)
				line(xMin,y,xMax,y);
			
			axis();
			xAxis(xMin,xMax);
			yAxis(yMin,yMax);
			xAxisNumbers(xMin,xMax,xStep,-yStep/3);
			yAxisNumbers(yMin,yMax,yStep,-xStep/2);
			
			data();
			for (double x=xMin; x<xMax+0.01; x+=0.02) if(f(x)<yMax&&f(x)>yMin) 
				{
					point(x,f(x));
					break;
				}
			for (double x=xMin; x<xMax+0.01; x+=0.02) if(f(x)<yMax&&f(x)>yMin) 
					join(x,f(x));
			
		}
	}
	
	public String toString()
	{
		String s = "Graph of y = ";
		if(a!=0)
		{
			s+=(a==1)?"":(a==-1)?"-":a+"";
			s+="x^2";
		}
		if(b!=0)
		{
			s+=(b==1)?" + ":(b==-1)?" - ":(b<0)?" - "+b*-1:" + "+b;
			s+="x";
		}
		if(c!=0)
		{
			s+=(c>0)?" + "+c:" - "+c*-1;
		}
		return s;
	}
	
	public double f(double x)
	{
		return a*x*x+b*x+c;
	}
	
	public void buttonClicked(int index)
	{
		xMin = txtXMin.getInt();
		xMax = txtXMax.getInt();
		yMin = txtYMin.getInt();
		yMax = txtYMax.getInt();
		xStep = txtXStep.getInt();
		yStep = txtYStep.getInt();
		a = txtA.getInt();
		b = txtB.getInt();
		c = txtC.getInt();
		lblTitle.setText(toString());
		g.setupCoordinateSystem(xMin-xStep, xMax+xStep, yMax+yStep, yMin-yStep);
		g.repaint();
		
	}
	
	public static void main(String[] args)
	{
		new QuadraticForm();
	}
}
