package gui.menus;

import javax.swing.JOptionPane;

import gui.ints.*;

public class IntPopupMenu extends RightClickPopupMenu 
{
	IntField field;
	
	public IntPopupMenu()
	{
		super("Add","Subtract","Multiply","Divide");
	}
	
	public void doMenu(String caption)
	{
		if(caption.equals("Add"))
			field.setInt(field.getInt()+
			Integer.parseInt(JOptionPane.showInputDialog("Number to add")));
		if(caption.equals("Subtract"))
			field.setInt(field.getInt()-
			Integer.parseInt(JOptionPane.showInputDialog("Number to subtract")));
		if(caption.equals("Multiply"))
			field.setInt(field.getInt()*
			Integer.parseInt(JOptionPane.showInputDialog("Number to multiply")));
		if(caption.equals("Divide"))
			field.setInt(field.getInt()/
			Integer.parseInt(JOptionPane.showInputDialog("Number to divide by")));
	}
	
	public static void plugTo(IntField field)
	{
		IntPopupMenu menu;
		field.addMouseListener(menu = new IntPopupMenu());
		menu.field = field;
	}
}
