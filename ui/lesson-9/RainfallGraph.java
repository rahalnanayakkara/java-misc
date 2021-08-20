import javax.swing.JOptionPane;

import graphics.*;
import gui.GridForm;


public class RainfallGraph extends Graph 
{
	String town;
	int[] rainfall;
	
	public void draw(String town, int[] rainfall)
	{
		this.town = town;
		this.rainfall = rainfall; 
		setupCoordinateSystem(-2,14,550,-100);
		draw();
	}
	
	public void draw()
	{
		axis();
		xAxis(0,12);
		setLineWidth(0.1);
		yAxis(0,430);
		yAxisNumbers(0,400,100,-0.3);
		xAxisLabels(0.5,11.5,-25,"J,F,M,A,M,J,J,A,S,O,N,D");
		
		setDashedLine(0.2,0);
		grid();
		yGrid(50,400,50,0,12);
		
		setLineWidth(0);
		data();
		for( int x=0; x<12; x++)
			column(x+0.5,0.8,rainfall[x]);
		
		text();
		text("mm",0,460,CENTER,BOTTOM);
		
		title();
		text("Rainfall "+town,6,474,CENTER,BOTTOM);
	}
	
	public static void main(String[] args)
	{
		GridForm test = new GridForm("Rainfall Graph",10,10,50,50,20,20);
		RainfallGraph graph = new RainfallGraph();
		test.add(graph,1,1,18,18);
		graph.draw("Kandy", new int[]{50,20,100,150,280,210,110,90,140,170,190,175});
	}
}
