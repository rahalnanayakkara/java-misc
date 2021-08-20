import gui.*;
import gui.adapters.*;
import number.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class FactorForm extends GridForm implements ActionListener, FocusListener
{
	JTextField txtNum1, txtNum2, txtGCD, txtLCM, txtNum1Fac, txtNum2Fac, txtGCDFac, txtLCMFac;
	JLabel lblNum1, lblNum2, lblGCD, lblLCM;
	JButton btnReset, btnCalculate;
	
	Font formFont = new Font("Sans Serif",Font.BOLD,20);
	
	public FactorForm()
	{
		super("Prime Factor",10,10,45,60,11,11);
		
		lblNum1 = addLabel("Number 1 :",1,1,2,1);
		lblNum2 = addLabel("Number 2 :",1,3,2,1);
		lblGCD = addLabel("GCD :",1,5,2,1);
		lblLCM = addLabel("LCM :",1,7,2,1);
		
		btnCalculate = addButton("Calculate",1,9,4,1);
		btnReset = addButton("Reset",6,9,4,1);
		
		txtNum1 = addTextField(JTextField.CENTER,3,1,2,1);
		txtNum2 = addTextField(JTextField.CENTER,3,3,2,1);
		txtGCD = addTextField(JTextField.CENTER,3,5,2,1);
		txtLCM = addTextField(JTextField.CENTER,3,7,2,1);
		
		txtNum1Fac = addTextField(JTextField.LEFT,6,1,4,1);
		txtNum2Fac = addTextField(JTextField.LEFT,6,3,4,1);
		txtGCDFac = addTextField(JTextField.LEFT,6,5,4,1);
		txtLCMFac = addTextField(JTextField.LEFT,6,7,4,1);
		
		btnCalculate.addActionListener(this);
		
		Appearance.setFont(formFont, txtNum1, txtNum2, txtGCD, txtLCM, txtNum1Fac, 
				txtNum2Fac, txtGCDFac, txtLCMFac, lblNum1, lblNum2, lblGCD, lblLCM, btnReset, btnCalculate);
		
		txtNum1Fac.addFocusListener(this);
		txtNum2Fac.addFocusListener(this);
		
		FocusRefuser.plugTo(txtGCD, txtLCM, txtGCDFac, txtLCMFac);
		Resetter.plugTo(btnReset, txtNum1, txtNum2, txtGCD, txtLCM, txtNum1Fac, 
				txtNum2Fac, txtGCDFac, txtLCMFac);
	}

	public void actionPerformed(ActionEvent e)
	{
		int num1 = Integer.parseInt(txtNum1.getText());
		int num2 = Integer.parseInt(txtNum2.getText());
		int gcd = Factors.gcd(num1,num2);
		int lcm = Factors.lcm(num1, num2);
		
		txtGCD.setText(""+gcd);
		txtLCM.setText(""+lcm);
		
		txtNum1Fac.setText(" = "+Factors.primeFactors(num1));
		txtNum2Fac.setText(" = "+Factors.primeFactors(num2));
		txtGCDFac.setText(" = "+Factors.primeFactors(gcd));
		txtLCMFac.setText(" = "+Factors.primeFactors(lcm));
	}

	public void focusGained(FocusEvent e) 
	{
		if (e.getSource()==txtNum1Fac) txtNum2.requestFocus();
		if (e.getSource()==txtNum2Fac) btnCalculate.requestFocus();
	}
	
	public void focusLost(FocusEvent arg0) {}
	
	public static void main(String[] args)
	{
		new FactorForm();
	}


}
