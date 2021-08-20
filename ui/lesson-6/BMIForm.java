import java.awt.*;

import javax.swing.*;

import gui.*;
import gui.adapters.ClosingConfirmer;
import gui.adapters.FocusRefuser;
import gui.buttons.*;
import gui.fields.*;
import gui.ints.*;

public class BMIForm extends GridForm implements IntListener
{
	IntSlider sldCM, sldInches, sldWeight, visible;
	RadioButtonPanel radHeight;
	JSlider sldBMI;
	MoneyField txtBMI;
	JTextField txtStatus;
	
	public BMIForm()
	{
		super("BMI",10,10,30,50,9,9);
		
		add(radHeight = new RadioButtonPanel(2,"Height","CM,Inches",0),1,1,2,1);
		addLabel("Weight",1,3,2,1);
		addLabel("BMI",1,5,2,1);
		addLabel("Status",1,7,2,1);
		
		add(sldCM = new IntSlider(0, 200),3,1,5,1);
		add(sldInches = new IntSlider(0, 80),3,1,5,1);
		add(sldWeight = new IntSlider(0,100),3,3,5,1);
		add(sldBMI = new JSlider(0,4000,0),5,5,3,1);
		add(txtBMI = new MoneyField(),3,5,2,1);
		add(txtStatus = new JTextField(),4,7,3,1);
		
		sldInches.setVisible(false);
		sldBMI.setEnabled(false);
		visible = sldCM;
		
		sldCM.addIntListener(this);
		sldWeight.addIntListener(this);
		sldInches.addIntListener(this);
		
		txtStatus.setHorizontalAlignment(JTextField.CENTER);
		txtBMI.setInputVerifier(null);
		FocusRefuser.plugTo(txtBMI, txtStatus);
		
		radHeight.setIntListener(this);
		Appearance.setFont(new Font("Sans Serif",Font.BOLD,16), components);
		ClosingConfirmer.plugTo(this);
	}
	
	public void intValueChanged(IntEvent e) 
	{
		if(e.getSource()==radHeight)
		{
			switch(e.getNewValue())
			{
			case 0: sldCM.setVisible(true);
					sldInches.setVisible(false);
					visible = sldCM;
					break;
			case 1: sldCM.setVisible(false);
					sldInches.setVisible(true);
					visible = sldInches;
					break;
			}
		}
		else
		{
			int height = visible.getValue();
			if (height != 0)
			{
				int weight = sldWeight.getValue();
				int BMI = (weight*1000000)/(height*height);
				if (visible==sldInches)BMI *= 0.1545;
				txtBMI.setValue(BMI);
				sldBMI.setValue(BMI);
				if(BMI<2000) 
				{
					txtStatus.setBackground(Color.WHITE);
					txtStatus.setText("Under Weight");
				}
				else if (BMI<2500)
				{
					txtStatus.setBackground(Color.WHITE);
					txtStatus.setText("Normal");
				}
				else if (BMI<3000)
				{
					txtStatus.setBackground(Color.YELLOW);
					txtStatus.setText("Over Weight");
				}
				else
				{
					txtStatus.setBackground(Color.RED);
					txtStatus.setText("Obese");
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		new BMIForm();
	}

}
