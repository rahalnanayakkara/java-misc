package gui.adapters;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class TextFieldHider implements DocumentListener, ActionListener
{
	JCheckBox chk;
	JTextField txt;
	Document doc;
	
	public TextFieldHider(JCheckBox chk, JTextField txt)
	{
		this.txt = txt;
		this.chk = chk;
		
		doc = txt.getDocument();
		doc.addDocumentListener(this);
		chk.addActionListener(this);
		
		txt.setVisible(false);
	}

	public void changedUpdate(DocumentEvent e) {change(e);}
	public void insertUpdate(DocumentEvent e) {change(e);}
	public void removeUpdate(DocumentEvent e) {change(e);}
	
	public void change(DocumentEvent e)
	{
		if (txt.getText().equals("")) chk.setEnabled(true);
		else chk.setEnabled(false);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (chk.isSelected()) txt.setVisible(true); 
		else txt.setVisible(false);
	}
	
	public static void plugTo(JCheckBox chk, JTextField txt)
	{
		new TextFieldHider(chk,txt);
	}
}
