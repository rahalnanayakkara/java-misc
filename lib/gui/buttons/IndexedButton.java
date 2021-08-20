package gui.buttons;

import gui.validation.Validator;
import javax.swing.JButton;
import java.awt.event.*;

public class IndexedButton extends JButton implements ActionListener
{
	ButtonListener listener;
	int index;
	boolean validate;
	ClickerThread t;
	
	public IndexedButton(int index, String caption, ButtonListener listener, boolean validate)
	{
		super(caption);
		this.index = index;
		this.listener = listener;
		this.validate = validate;
		addActionListener(this);
	}
	
	public IndexedButton(int index, String caption, ButtonListener listener)
	{
		this(index,caption,listener,true);
	}
	
	public void addButtonListener(ButtonListener listener)
	{
		this.listener=listener;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (Validator.valid||!validate) (t = new ClickerThread()).start();
	}
	
	public class ClickerThread extends Thread
	{
		public void run()
		{
			listener.buttonClicked(index);
		}
	}
	
	public void terminate()
	{
		t.stop();
	}
}
