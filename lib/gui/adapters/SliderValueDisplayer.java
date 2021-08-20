package gui.adapters;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;

public class SliderValueDisplayer implements ChangeListener
{
	JSlider sld;
	JTextField txt;
	
	public static void plugTo(JSlider slider, JTextField textField)
	{
		new SliderValueDisplayer(slider, textField);
	}
	
	public SliderValueDisplayer(JSlider slider, JTextField textField)
	{
		sld = slider;
		txt = textField;
		sld.addChangeListener(this);
	}

	public void stateChanged(ChangeEvent e) 
	{
		txt.setText(""+sld.getValue());
	}
}
