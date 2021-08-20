import java.awt.*;
import java.awt.event.*;

import gui.*;
import gui.adapters.*;

import javax.swing.*;

public class DiscountForm extends GridForm implements ActionListener 
{
	JTextField txtQty, txtPrice, txtAmount, txtDiscount, txtPayable;
	JButton btnCalculate;
	JCheckBox chkDiscount;
	JLabel lblPayable, lblDiscount;
	
	Font formFont = new Font("Sans Serif",Font.BOLD,20);
	
	public DiscountForm()
	{
		super("Item Cost Form",10,10,50,70,6,15);
		
		addLabel("Quantity :",1,1,2,1);
		addLabel("Price    :",1,3,2,1);
		addLabel("Amount   :",1,7,2,1);
		lblDiscount = addLabel("Discount :",1,9,2,1);
		lblPayable = addLabel("Payable  :", 1,11,2,1);
		
		txtQty = addTextField(JTextField.CENTER,3,1,2,1);
		txtPrice = addTextField(JTextField.CENTER,3,3,2,1);
		txtAmount = addTextField(JTextField.CENTER,3,7,2,1);
		txtDiscount = addTextField(JTextField.CENTER,3,9,2,1);
		txtPayable = addTextField(JTextField.CENTER,3,11,2,1);
		
		btnCalculate = addButton("Calculate",1,5,2,1);
		add(chkDiscount = new JCheckBox("Discount"),4,5,2,1);
		
		FocusRefuser.plugTo(txtAmount, txtDiscount, txtPayable);
		TextFieldHider.plugTo(chkDiscount, txtDiscount);
		TextFieldHider.plugTo(chkDiscount, txtPayable);
		ComponentHider.plugTo(chkDiscount, lblDiscount, lblPayable);
		LimiterToDigits.plugTo(txtQty, txtPrice);
		
		btnCalculate.addActionListener(this);
		
		Appearance.setFont(formFont, components);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		int qty = Integer.parseInt(txtQty.getText());
		int price = Integer.parseInt(txtPrice.getText());
		double discount = qty*price*0.05;
		txtAmount.setText(""+price*qty);
		txtDiscount.setText(""+discount);
		txtPayable.setText(""+(qty*price-discount));
		chkDiscount.setEnabled(true);
	}
	
	public static void main(String[] args)
	{
		new DiscountForm();
	}
}
