package gui.buttons;

import java.awt.event.*;

import javax.swing.*;

import gui.*;
import gui.ints.*;

public class RadioButtonPanel extends ContainerPanel implements ActionListener 
{
	JRadioButton[] buttons;
	JLabel lblTitle;
	int nButtons, selected, rowHeight;
	ButtonGroup buttonGroup;
	String[] names;
	IntListener listener;
	
	public RadioButtonPanel(int nButtons, String title, String buttonNames, int selected)
	{
		super(nButtons+1);
		setLayout(null);
		add(lblTitle = new JLabel(title));
		buttonGroup = new ButtonGroup();
		this.nButtons = nButtons;
		buttons = new JRadioButton[nButtons];
		names = buttonNames.split(",");
		this.selected = selected;
		for(int x=0; x<nButtons; x++)
		{
			add(buttons[x] = new JRadioButton(names[x]));
			buttonGroup.add(buttons[x]);
			buttons[x].addActionListener(this);
			buttons[x].setHorizontalAlignment(JRadioButton.LEFT);
		}
		buttons[selected].setSelected(true);
	}
	
	public void setBounds(int x, int y, int width, int height)
	{
		super.setBounds(x, y, width, height);
		rowHeight = height/(nButtons+1);
		lblTitle.setBounds(0, 0, width, rowHeight);
		for(int a=0; a<nButtons; a++)
			buttons[a].setBounds(0, rowHeight*(a+1), width, rowHeight);
	}
	
	public void setIntListener(IntListener listener)
	{
		this.listener = listener;
	}

	public void actionPerformed(ActionEvent e) 
	{
		int oldValue = selected;
		selected = ((JRadioButton)e.getSource()).getY()/rowHeight-1;
		try
		{
			listener.intValueChanged(new IntEvent(this,oldValue, selected));
		}
		catch(NullPointerException n)
		{}
	}
	
	public int getSelectedIndex()
	{
		return selected;
	}
	
	public String getSelectedText()
	{
		return names[selected];
	}
	
	public void setSelectedIndex(int index)
	{
		buttons[index].setSelected(true);
	}
	
	public void setSelectedText(String text)
	{
		for(int x=0; x<nButtons; x++)
			if (names[x].equals(text))
			buttons[x].setSelected(true); 
	}
}
