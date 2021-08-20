import gui.*;
import gui.adapters.*;
import gui.validation.IntValidator;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class RectangleForm extends GridForm implements ActionListener
{
	JTextField txtLength, txtWidth, txtArea;
	JButton btnCalculate, btnReset;
	JLabel lblLength, lblWidth, lblArea;
	
	
	Font formFont = new Font("Sans Serif",Font.BOLD,20);
	
	public RectangleForm()
	{
		super("Area of a rectangle",10,10,30,40,7,9);
		
		lblLength = addLabel("Length",1,1,2,1);
		lblWidth = addLabel("Width",1,3,2,1);
		lblArea = addLabel("Area",1,7,2,1);
		
		txtLength = addTextField(JTextField.CENTER,3,1,3,1);
		txtWidth = addTextField(JTextField.CENTER,3,3,3,1);
		txtArea = addTextField(JTextField.CENTER,3,7,3,1);
		
		btnCalculate = addButton("Calculate",1,5,2,1);
		btnReset = addButton("Reset",4,5,2,1);
		
		btnCalculate.addActionListener(this);
		Resetter.plugTo(btnReset, txtLength, txtWidth, txtArea);
		
		LimiterToDouble.plugTo(txtLength, txtWidth);
		Appearance.setFont(formFont, txtLength, txtWidth, txtArea, btnCalculate, btnReset,lblLength, lblWidth, lblArea);
	}

	public void actionPerformed(ActionEvent e) 
	{
		double length = Double.parseDouble(txtLength.getText());
		double width = Double.parseDouble(txtWidth.getText());
		txtArea.setText(""+(width*length));
	}
	
	
	public static void main(String[] args)
	{
		new RectangleForm();
	}



}
