package gui.fields;

import gui.adapters.FocusRefuser;
import gui.adapters.LimiterToDouble;
import gui.validation.MoneyValidator;

import javax.swing.JTextField;

public class MoneyField extends JTextField 
{
	public MoneyField()
	{
		setHorizontalAlignment(CENTER);
		LimiterToDouble.plugTo(this);
		MoneyValidator.plugTo(this);
	}
	
	public void setValue(int amount)
	{
		String cents=(amount%100>9)?amount%100+"":"0"+amount%100;
		setText(amount/100+"."+cents.substring(0, 2));
	}
	
	public int getValue()
	{
		String text = getText();
		int big = Integer.parseInt(text.substring(0,text.indexOf('.')));
		int small = Integer.parseInt(text.substring(text.indexOf('.')+1));
		return small+100*big;
	}
	
	public void displayOnly()
	{
		setInputVerifier(null);
		FocusRefuser.plugTo(this);
	}
}
