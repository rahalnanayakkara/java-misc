import gui.*;
import gui.adapters.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class CircleForm extends GridForm implements ActionListener
{
	JTextField txtRadius, txtArea, txtCircumference;
	JButton btnReset, btnCalculate;
	JLabel lblRadius, lblArea, lblCircumference;
	
	Font formFont = new Font("Sans Serif",Font.BOLD,20);
	
	public CircleForm()
	{
		super("Area of a circle", 10, 10, 30, 40, 7, 9);
		
		lblRadius = addLabel("Radius",1,1,2,1);
		lblCircumference = addLabel("Circumference",1,5,2,1);
		lblArea = addLabel("Area",1,7,2,1);
		
		txtRadius = addTextField(JTextField.CENTER,3,1,3,1);
		txtCircumference = addTextField(JTextField.CENTER,3,5,3,1);
		txtArea = addTextField(JTextField.CENTER,3,7,3,1);
		
		btnCalculate = addButton("Calculate",1,3,2,1);
		btnReset = addButton("Reset",4,3,2,1);
		
		Appearance.setFont(formFont, lblRadius, lblArea, txtRadius, txtArea,
				btnReset, btnCalculate, lblCircumference, txtCircumference);
		
		LimiterToDouble.plugTo(txtRadius);
		
		btnCalculate.addActionListener(this);
		Resetter.plugTo(btnReset, txtRadius, txtArea, txtCircumference);
	}
	
	public void actionPerformed (ActionEvent e)
	{
		double radius = Double.parseDouble(txtRadius.getText());
		double circumference = radius*2*Math.PI;
		double area = circumference*Math.PI/2;
		txtArea.setText(area+"");
		txtCircumference.setText(""+circumference);
	}
	
	public static void main(String[] args) 
	{
		new CircleForm();
	}


}
