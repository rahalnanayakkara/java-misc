import io.*;

import javax.swing.*;

import file.ExtensionFilter;
import gui.*;
import gui.buttons.*;
import gui.fields.*;
import gui.tables.*;

public class RainfallForm extends GridForm implements ButtonListener, TableListener, FieldListener
{
	RainfallGraph2 g;
	StringField txtName;
	DataTable t;
	String[] months = {"J","F","M","A","M","J","J","A","S","O","N","D"};
	int[] rainfall = new int[12], temp = new int[12];
	JFileChooser fileChooser;
	boolean changed=true;
	
	public RainfallForm()
	{
		super("Rainfall and Temperature",10,10,50,70,19,32);
		add(g = new RainfallGraph2(),1,1,17,18);
		g.draw("", new int[12], new int[12]);
		
		add(t = new DataTable(2,13,40),1,20,17,6);
		t.setCol(0, "", JTextField.CENTER, 4, false);
		for(int x=1; x<=12; x++)
			t.setCol(x, months[x-1], JTextField.CENTER, 3, true);
		t.setText(0, 0, "Rainfall(mm)");
		t.setText(1,0,"Temp C");
		t.addTableListener(this);
		
		add(new IndexedButton(1,"Load",this,false),7,28,3,2);
		add(new IndexedButton(2,"Save",this,false),15,28,3,2);
		add(new IndexedButton(3,"Clear",this,false),11,28,3,2);
		
		addLabel("Name of City",1,28,2,2);
		add(txtName = new StringField(),3,28,3,2);
		txtName.addFieldListener(this);
		
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new ExtensionFilter("rnf","Rainfall Files"));
	}
	
	public void save()
	{
		WriteFile.open("C:\\Users\\Rahal\\Documents\\"+txtName.getText()+".rnf");
		WriteFile.writeString(txtName.getText());
		for(int i=0; i<12; i++)
			WriteFile.writeInt(rainfall[i]);
		for(int i=0; i<12; i++)
			WriteFile.writeInt(temp[i]);
		WriteFile.close();
		JOptionPane.showMessageDialog(this,"Save Successfull");
	}
	
	public void load()
	{
		if (fileChooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
		{
			changed = false;
			int[] rainfall = new int[12], temp = new int[12];
			ReadFile.open(fileChooser.getSelectedFile().getAbsolutePath());
			txtName.setText(ReadFile.readString());
			for(int x=0; x<12; x++) rainfall[x] = ReadFile.readInt();
			for(int x=0; x<12; x++) temp[x] = ReadFile.readInt();
			for(int x=0; x<12; x++) t.setText(0, x+1, rainfall[x]+"");
			for(int x=0; x<12; x++) t.setText(1, x+1, temp[x]+"");
			ReadFile.close();
			this.rainfall = rainfall;
			this.temp = temp;
			changed = true;
			changedCell(0,0,"","");
		}
	}
	
	public void clear()
	{
		changed = false;
		for(int x=0; x<12; x++) t.setText(0, x+1, "");
		for(int x=0; x<12; x++) t.setText(1, x+1, "");
		changed = true;
		txtName.setText("");
	}
	
	public void changedCell(int col, int row, String oldValue, String newValue)
	{
		if (changed)
		{
			g.clear();
			for(int x=0; x<12; x++) try
			{
				rainfall[x] = Integer.parseInt(t.getText(0, x+1));
			}
			catch(Exception e)
			{
				rainfall[x]=0;
			}
			for(int x=0; x<12; x++) try
			{
				temp[x] = Integer.parseInt(t.getText(1, x+1));
			}
			catch(Exception e)
			{
				temp[x]=0;
			}
			g.draw(txtName.getText(), rainfall, temp);
		}
	}
	
	public void fieldChanged(ChangingTextField source, String oldText, String newText)
	{
		changedCell(0,0,"","");
	}
	
	public void buttonClicked(int index)
	{
		if (index==1) load();
		if (index==2&&!txtName.getText().equals("")) save();
		if (index==3) clear();
	}
	
	public void enteredCell(int col, int row, String datum){}
	public void leftCell(int col, int row, String datum){}
	
	public static void main(String[] args)
	{
		new RainfallForm();
	}
}
