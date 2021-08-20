import java.io.File;

import io.ReadFile;
import io.WriteFile;
import file.ExtensionFilter;
import gui.*;
import gui.fields.*;
import gui.ints.*;
import gui.validation.Validator;

import javax.swing.*;

import gui.buttons.*;


public class CountryForm extends GridForm implements ButtonListener, IntListener, FieldListener
{
	StringField txtCountry, txtCapital;
	IntField txtPopulation, txtArea;
	IndexedButton btnSave, btnLoad, btnClear;
	JFileChooser fileChooser;
	boolean saved=true;
	
	CountryForm()
	{
		super("Country Data",10,10,40,40,22,7);
		
		addLabel("Country",2,1,4,1);
		add(txtCountry = new StringField(),6,1,4,1);
		
		addLabel("Capital",12,1,4,1);
		add(txtCapital = new StringField(),16,1,4,1);
		
		addLabel("Population(millions)",2,3,6,1);
		add(txtPopulation = new IntField(),8,3,2,1);
		
		addLabel("Area sq.km.",12,3,4,1);
		add(txtArea = new IntField(),16,3,4,1);
		
		add(btnLoad = new IndexedButton(1,"Load",this),2,5,4,1);
		add(btnSave = new IndexedButton(2,"Save",this),9,5,4,1);
		add(btnClear = new IndexedButton(3,"Clear",this),16,5,4,1);
		
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new ExtensionFilter("cdt",""));
		Validator.valid = true;
		
		txtCountry.addFieldListener(this);
		txtCapital.addFieldListener(this);
		txtPopulation.setIntListener(this);
		txtArea.setIntListener(this);
	}
	
	public void load()
	{
		int option = fileChooser.showDialog(this,"Open");
		if(option == fileChooser.APPROVE_OPTION) load(fileChooser.getSelectedFile().getPath());
	}
	
	public void save()
	{
		fileChooser.setSelectedFile(new File(txtCountry.getText()));
		int option = fileChooser.showDialog(this,"Save");
		if(option == fileChooser.APPROVE_OPTION) save(fileChooser.getSelectedFile().getPath()+".cdt");
	}
	
	public void clear()
	{
		int option;
		if (!saved) 
		{
			option = JOptionPane.showConfirmDialog(this, "Are you sure you don't want to save?",
					"Confirm Clearing",JOptionPane.YES_NO_CANCEL_OPTION); 	
			if(option==JOptionPane.NO_OPTION) 
			{
				save();
				clearText();
			}
			else if (option==JOptionPane.YES_OPTION) clearText();
		}
		else clearText();
	}
	
	public void clearText()
	{
		txtCountry.setText("");
		txtCapital.setText("");
		txtPopulation.setInt(0);
		txtArea.setInt(0);
	}
	
	public void load(String path)
	{
		ReadFile.open(path);
		txtCountry.setText(ReadFile.readString());
		txtCapital.setText(ReadFile.readString());
		txtPopulation.setInt(ReadFile.readInt());
		txtArea.setInt(ReadFile.readInt());
		ReadFile.close();
	}
	
	public void save(String path)
	{
		WriteFile.open(path);
		WriteFile.writeString(txtCountry.getText());
		WriteFile.writeString(txtCapital.getText());
		WriteFile.writeInt(txtPopulation.getInt());
		WriteFile.writeInt(txtArea.getInt());
		WriteFile.close();
		saved = true;
	}
	
	public void buttonClicked(int index)
	{
		switch (index)
		{
			case 1: load(); break;
			case 2: save(); break;
			case 3: clear(); break;
		}
	}
	
	public void fieldChanged(ChangingTextField source, String oldText, String newText) 
	{
		saved = false;
	}

	public void intValueChanged(IntEvent e)
	{
		saved = false;
	}
	
	public static void main(String[] args)
	{
		new CountryForm();
		
	}


}
