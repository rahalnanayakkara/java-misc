import io.Screen;

import java.awt.*;
import java.awt.event.*;

import gui.*;
import gui.adapters.ComponentHider;
import gui.adapters.LimiterToDigits;
import gui.adapters.TextFieldHider;
import gui.ints.*;

import javax.swing.*;

public class FontForm extends GridForm implements ActionListener
{
	String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	JCheckBox chkBold, chkItalic;
	JButton btnSelect;
	JComboBox cmbFont;
	JTextField txtSize;
	
	Font font;
	
	Font formFont = new Font("Sans Serif",Font.BOLD,20);
	
	public FontForm()
	{
		super("Select Font",10,10,30,40,5,10);
		
		addLabel("Font Style :",1,1,2,1);
		addLabel("Font Size:",1,4,2,1);
		
		add(cmbFont = new JComboBox(fonts),1,2,3,1);
		add(txtSize=new JTextField(),3,4,1,1);
		
		add(chkBold = new JCheckBox("Bold"),1,6,2,1);
		add(chkItalic = new JCheckBox("Italic"),3,6,2,1);
		
		btnSelect = addButton("Select",1,8,3,1);
		
		LimiterToDigits.plugTo(txtSize);
		txtSize.setHorizontalAlignment(JTextField.CENTER);
		
		btnSelect.addActionListener(this);
		
		Appearance.setFont(formFont, components);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String fontName =(String) cmbFont.getSelectedItem();
		int fontSize = Integer.parseInt(txtSize.getText());
		int fontStyle = Font.PLAIN;
		if (chkBold.isSelected()) fontStyle+=Font.BOLD;
		if (chkItalic.isSelected()) fontStyle+=Font.ITALIC;
		
		font = new Font(fontName, fontSize, fontStyle);
	}
	
	public Font getFont()
	{
		return font;
	}

	public static void main(String[] args)
	{
		new FontForm();
	}

}