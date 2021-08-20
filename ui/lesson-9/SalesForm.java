
import java.awt.Color;

import io.Screen;
import graphics.*;
import gui.*;
import gui.buttons.*;
import gui.ints.*;

public class SalesForm extends GridForm implements ButtonListener
{
	IntField intMon, intTue, intWed, intThu, intFri;
	SalesGraph g;
	
	public SalesForm()
	{
		super("Sales Graph",10,10,50,50,16,22);
		
		add(new IndexedButton(1,"New",this,false),1,1,4,2);
		add(new IndexedButton(2,"Draw",this,false),1,19,4,2);
		
		addLabel("Mon",1,4,2,2);
		addLabel("Tue",1,7,2,2);
		addLabel("Wed",1,10,2,2);
		addLabel("Thu",1,13,2,2);
		addLabel("Fri",1,16,2,2);
		
		add(intMon = new IntField(),3,4,2,2);
		add(intTue = new IntField(),3,7,2,2);
		add(intWed = new IntField(),3,10,2,2);
		add(intThu = new IntField(),3,13,2,2);
		add(intFri = new IntField(),3,16,2,2);
		
		add(g = new SalesGraph(),6,1,9,20);
		g.setBackground(Color.white);
		g.plot();
	}
	
	public class SalesGraph extends Graph
	{
		public void plot()
		{
			setupCoordinateSystem(-20,120,35,-5);
			draw();
		}
		
		public void draw()
		{
			axisColor = Color.BLACK;
			dataColor = new Color(0,200,0);
			
			text();
			text("Weekly Sales",50,30,CENTER,CENTER);
			
			grid();
			xGrid(0,100,20,0,25);
			
			data();
			bar(2,3,intFri.getInt());
			bar(7,3,intThu.getInt());
			bar(12,3,intWed.getInt());
			bar(17,3,intTue.getInt());
			bar(22,3,intMon.getInt());
			
			axis();
			xAxis(0,100);
			yAxis(0,25);
			xAxisNumbers(0,100,20,-1);
			yAxisLabels(2,22,-6,"Fri,Thu,Wed,Tue,Mon");
		}
	}
	
	public void buttonClicked(int index)
	{
		if (index==1)
		{
			intMon.setText("");
			intTue.setText("");
			intWed.setText("");
			intThu.setText("");
			intFri.setText("");
			g.repaint();
		}
		if (index==2) g.repaint();
	}
	
	public static void main(String[] arhs)
	{
		new SalesForm();
	}
}
