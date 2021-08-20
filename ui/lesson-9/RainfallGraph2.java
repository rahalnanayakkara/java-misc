import java.awt.Color;

import javax.swing.JOptionPane;

import graphics.*;
import gui.GridForm;


public class RainfallGraph2 extends Y2Graph 
{
	String town;
	int[] rainfall, temp;
	
	public void draw(String town, int[] rainfall, int[] temp)
	{
		this.town = town;
		this.rainfall = rainfall; 
		this.temp = temp;
		setupCoordinateSystem(-2,14,550,-100,0,0,1,0.1);
		draw();
	}
	
	public void draw()
	{
		setBackground(Color.WHITE);
		
		axisColor = Color.BLACK;
		dataColor = new Color(0,150,0);
		
		axis();
		xAxis(0,12);
		setLineWidth(0.05);
		yAxis2(0,430,12);
		yAxis(0,430);
		yAxisNumbers(0,400,100,-0.3);
		yAxis2Numbers(0,40,10,12.5);
		xAxisLabels(0.5,11.5,-25,"J,F,M,A,M,J,J,A,S,O,N,D");
		
		setDashedLine(0.2,0);
		grid();
		yGrid(50,400,50,0,12);
		
		setLineWidth(0);
		data();
		for( int x=0; x<12; x++)
			column(x+0.5,0.8,rainfall[x]);
		
		axis();
		point2(0.5,temp[0]);
		for( int x=0; x<12; x++)
			join2(x+0.5,temp[x]);
		
		text();
		text("mm",0,460,CENTER,BOTTOM);
		text("Temp C",12,460,CENTER,BOTTOM);
		
		title();
		text("Rainfall "+town,6,474,CENTER,BOTTOM);
	}
	
	public static void main(String[] args)
	{
		GridForm test = new GridForm("Rainfall Graph",10,10,50,50,20,20);
		RainfallGraph2 graph = new RainfallGraph2();
		test.add(graph,1,1,18,18);
		//graph.draw("Kandy", new int[]{50,20,100,150,280,210,110,90,140,170,190,175}, new int[]{14,16,22,25,22,21,20,24,21,19,15,20});
		//graph.repaint();
	}
}
