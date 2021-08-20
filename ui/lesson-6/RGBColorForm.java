import java.awt.*;

import javax.swing.*;

import gui.*;
import gui.ints.*;


public class RGBColorForm extends GridForm implements IntListener 
{
	IntSlider sldRed, sldGreen, sldBlue;
	JLabel lblColor;
	
	RGBColorForm()
	{
		super("RGB Color",10,10,50,40,11,7);
		
		add(sldRed = new IntSlider(0,255),1,1,5,1);
		add(sldGreen = new IntSlider(0,255),1,3,5,1);
		add(sldBlue = new IntSlider(0,255),1,5,5,1);
		
		add(lblColor = new JLabel(),7,1,3,5);
		lblColor.setOpaque(true);
		lblColor.setBackground(Color.BLACK);
		
		sldRed.setBackground(Color.RED);
		sldGreen.setBackground(Color.GREEN);
		sldBlue.setBackground(Color.BLUE);
		
		sldRed.addIntListener(this);
		sldGreen.addIntListener(this);
		sldBlue.addIntListener(this);
		
		Appearance.setFont(Appearance.formFont, components);
	}
	
	public void intValueChanged(IntEvent e)
	{
		lblColor.setBackground(new Color(sldRed.getValue(),sldGreen.getValue(),sldBlue.getValue()));
	}
	
	public static void main (String[] args)
	{
		new RGBColorForm();
	}
}
