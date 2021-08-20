package gui.fields;

import java.util.LinkedList;

import javax.swing.event.DocumentEvent;

public class StringField extends ChangingTextField 
{
	String text;
	LinkedList<FieldListener> listeners;
	
	public StringField(String text)
	{
		super();
		setText(text);
		listeners = new LinkedList<FieldListener>();
		setInputVerifier(null);
	}
	
	public StringField()
	{
		this("");
	}
	
	public void change(DocumentEvent e) 
	{
		for(int x=0; x<listeners.size(); x++) listeners.get(x).fieldChanged(this, text, getText());
		text = getText();
	}
	
	public void setText(String text)
	{
		super.setText(text);
		this.text = text;
	}
	
	public void addFieldListener(FieldListener listener)
	{
		listeners.add(listener);
	}
}
