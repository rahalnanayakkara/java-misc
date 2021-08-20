import io.Screen;

import java.awt.Font;

import javax.swing.JTextField;

import gui.*;
import gui.adapters.FocusRefuser;
import gui.ints.*;

public class SquareAndCubeForm extends GridForm implements IntListener
{
	IntField intNum, intSquare, intCube;
	Font formFont = new Font("Sans Serif",Font.BOLD,20);
	
	public SquareAndCubeForm()
	{
		super("Square and Cube",10,10,30,40,7,7);
		
		addLabel("Number",1,1,2,1);
		addLabel("Square",1,3,2,1);
		addLabel("Cube",1,5,2,1);
		
		add(intNum = new IntField(),4,1,2,1);
		add(intSquare = new IntField(),4,3,2,1);
		add(intCube = new IntField(),4,5,2,1);
		
		Appearance.setFont(formFont, components);
		intNum.setIntListener(this);
		
		intSquare.setHorizontalAlignment(JTextField.RIGHT);
		intCube.setHorizontalAlignment(JTextField.RIGHT);
		
		FocusRefuser.plugTo(intSquare, intCube);
	}
	
	public static void main(String[] args)
	{
		new SquareAndCubeForm();
	}

	public void intValueChanged(IntEvent e) 
	{
		int number = e.getNewValue();
		intSquare.setText(""+(number*number));
		intCube.setText((""+number*number*number));
	}
}
