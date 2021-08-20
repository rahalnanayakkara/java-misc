package gui.fields;

import gui.validation.Validator;

import javax.swing.*;
import javax.swing.event.*;

public abstract class ChangingTextField extends JTextField implements DocumentListener
{
	public ChangingTextField(String text)
	{
		super(text);
		getDocument().addDocumentListener(this);
		Validator.plugTo(this);
	}
	
	public ChangingTextField()
	{
		this("");
	}

	public void changedUpdate(DocumentEvent e) 
	{
		change(e);
	}
	
	public void insertUpdate(DocumentEvent e) 
	{
		change(e);
	}
	
	public void removeUpdate(DocumentEvent e) 
	{
		change(e);
	}
	
	public abstract void change(DocumentEvent e);
}
