import gui.*;
import gui.buttons.*;
import gui.ints.*;
import gui.adapters.*;

import javax.swing.*;

import java.awt.*;

public class RectangleForm extends GridForm implements ButtonListener 
{
	IntField intLength, intWidth, intArea;
	IndexedButton btnReset;
	JLabel lblLength;
	
	Font formFont = new Font("Sans Serif",Font.BOLD,20);
	
	public RectangleForm()
	{
		super("Rectangle",10,10,30,40,7,9);
		
		add(new JLabel("Length"),1,1,2,1);
		add(new JLabel("Width"),1,3,2,1);
		add(new JLabel("Area"),1,7,2,1);
		
		add(intLength = new IntField(),4,1,2,1);
		add(intWidth = new IntField(),4,3,2,1);
		add(intArea = new IntField(),4,7,2,1);
		
		add(new IndexedButton(1,"Calculate",this),1,5,2,1);
		add(btnReset = new IndexedButton(2,"Reset",this),4,5,2,1);
		
		Appearance.setFont(formFont, components);
		Resetter.plugTo(btnReset, intLength, intWidth, intArea);
	}
	
	public static void main(String[] args)
	{
		new RectangleForm();
	}

	public void buttonClicked(int index) 
	{
		if (index==1) intArea.setInt(intLength.getInt()*intWidth.getInt());
	}
	
}