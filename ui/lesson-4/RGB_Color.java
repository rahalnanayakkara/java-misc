import gui.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;

public class RGB_Color extends GridForm implements ChangeListener 
{
	JSlider sldRed, sldGreen, sldBlue;
	JLabel	lblColor, lblRed, lblGreen, lblBlue;

	public RGB_Color()
	{
		super("RGB Color", 10, 10, 50, 50, 10, 7);
		
		add(lblColor = new JLabel(), 6,1,3,5);
		lblColor.setOpaque(true);
		lblColor.setBackground(Color.BLACK);
		
		add(sldRed = new JSlider(0,255,0),2,1,3,1);
		add(sldGreen = new JSlider(0,255,0),2,3,3,1);
		add(sldBlue = new JSlider(0,255,0),2,5,3,1);
		
		lblRed = addLabel("",1,1,1,1);
		lblGreen = addLabel("",1,3,1,1);
		lblBlue = addLabel("",1,5,1,1);
		lblRed.setOpaque(true);
		lblGreen.setOpaque(true);
		lblBlue.setOpaque(true);
		lblRed.setBackground(Color.RED);
		lblGreen.setBackground(Color.GREEN);
		lblBlue.setBackground(Color.BLUE);
		
		sldRed.addChangeListener(this);
		sldGreen.addChangeListener(this);
		sldBlue.addChangeListener(this);
	}
	
	public static void main(String[] args)
	{
		new RGB_Color();
	}

	public void stateChanged(ChangeEvent e)
	{
		lblColor.setBackground(new Color(sldRed.getValue(),sldGreen.getValue(),sldBlue.getValue()));
	}
}
