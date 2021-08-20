package gui.menus;

import javax.swing.*;

public class CaseConverterPopupMenu extends RightClickPopupMenu 
{
	JTextField textField;
	
	public CaseConverterPopupMenu()
	{
		super("Upper Case", "Lower Case");
	}
	
	public void doMenu(String caption)
	{
		if(caption.equals("Upper Case")) textField.setText(textField.getText().toUpperCase());
		if(caption.equals("Lower Case")) textField.setText(textField.getText().toLowerCase());
	}
	
	public static void plugTo(JTextField textField)
	{
		CaseConverterPopupMenu menu;
		textField.addMouseListener(menu = new CaseConverterPopupMenu());
		menu.textField = textField;
	}
}
