import gui.*;
import gui.adapters.FocusRefuser;
import gui.adapters.SliderValueDisplayer;


import javax.swing.*;
import javax.swing.event.*;

import number.Round;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

public class BMI extends GridForm implements ChangeListener, ActionListener
{
	JSlider sldCMHeight, sldInHeight, sldWeight, sldBMI;
	JTextField txtCMHeight, txtInHeight, txtWeight, txtBMI, txtStatus;
	JRadioButton rdbCM,rdbInch;
	
	Font formFont = new Font("Sans Serif",Font.BOLD,20);
	
	public BMI()
	{
		super("Body Mass Index",10,10,40,50,9,9);
		
		add(new JLabel("Height"),1,1,2,1);
		add(new JLabel("Weight (kg)"),1,3,2,1);
		add(new JLabel("BMI"),1,5,2,1);
		add(new JLabel("Status"),1,7,2,1);
		
		add(sldCMHeight = new JSlider(0,200,0),3,1,3,1);
		add(sldInHeight = new JSlider(0,80,0),3,1,3,1);
		add(sldWeight = new JSlider(0,100,0),3,3,3,1);
		add(sldBMI = new JSlider(50,400,50),3,5,3,1);
		
		txtCMHeight = addTextField(JTextField.CENTER,7,1,1,1);
		txtInHeight = addTextField(JTextField.CENTER,7,1,1,1);
		txtWeight = addTextField(JTextField.CENTER,7,3,1,1);
		txtBMI = addTextField(JTextField.CENTER,7,5,1,1);
		txtStatus = addTextField(JTextField.CENTER,4,7,3,1);
		
		txtInHeight.setVisible(false);
		sldInHeight.setVisible(false);
		
		add( rdbCM = new JRadioButton("cm",true),1,2,1,1);
		add( rdbInch = new JRadioButton("inches"),2,2,2,1);
		
		ButtonGroup height = new ButtonGroup();
		height.add(rdbCM);
		height.add(rdbInch);
		
		txtCMHeight.setText("0"); txtWeight.setText("0"); txtInHeight.setText("0");
		
		SliderValueDisplayer.plugTo(sldWeight, txtWeight);
		SliderValueDisplayer.plugTo(sldCMHeight, txtCMHeight);
		SliderValueDisplayer.plugTo(sldInHeight, txtInHeight);
		
		FocusRefuser.plugTo(txtBMI, txtStatus);
		sldBMI.setEnabled(false);
		sldWeight.addChangeListener(this);
		sldCMHeight.addChangeListener(this);
		sldInHeight.addChangeListener(this);
		
		rdbInch.addActionListener(this);
		rdbCM.addActionListener(this);
		
		Appearance.setFont(formFont, components);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==rdbInch)
		{
			sldCMHeight.setVisible(false);
			txtCMHeight.setVisible(false);
			sldInHeight.setVisible(true);
			txtInHeight.setVisible(true);
			stateChanged(new ChangeEvent(rdbInch));
		}
		else if (e.getSource()==rdbCM)
		{
			sldCMHeight.setVisible(true);
			txtCMHeight.setVisible(true);
			sldInHeight.setVisible(false);
			txtInHeight.setVisible(false);
			stateChanged(new ChangeEvent(rdbCM));
		}
	}
	
	public void stateChanged(ChangeEvent e)
	{
		double weight = Double.parseDouble(txtWeight.getText());
		double height=0;
		if (rdbCM.isSelected()) height = Double.parseDouble(txtCMHeight.getText())/100;
		if (rdbInch.isSelected()) height = Double.parseDouble(txtInHeight.getText())/100*2.54;
		double BMI = weight/(height*height);
		sldBMI.setValue((int)(BMI*10));
		txtBMI.setText(""+Round.near(BMI, 2));
		
		if (BMI>25)txtStatus.setBackground(Color.RED); else txtStatus.setBackground(Color.WHITE);
		if (BMI>30) txtStatus.setText("Obese");
		else if (BMI>25) txtStatus.setText("Over Weight");
		else if (BMI>=20) txtStatus.setText("Normal Range");
		else txtStatus.setText("Under Weight");
	}
	
	public static void main(String[] args)
	{
		new BMI();
	}
}
