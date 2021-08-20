package gui.tables;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;

import gui.*;
import gui.fields.*;

//TEST RUN IN ADFORM3

public class DataTable extends ContainerPanel implements FocusListener, KeyListener, FieldListener
{
	JLabel[] headings;
	StringField[][] data;
	int[] colWidthInChars;
	int rows, cols, totalWidthInChars, width, height, selectedRow, selectedCol;
	String oldValue;
	LinkedList<TableListener> tableListeners;
	Color bg;
	
	public DataTable(int rows, int cols, int totalWidthInChars)
	{
		super((rows+1)*cols);
		this.rows = rows;
		this.cols = cols;
		this.totalWidthInChars = totalWidthInChars;
		headings = new JLabel[cols];
		data = new StringField[cols][rows];
		colWidthInChars = new int[cols];
		for(int x=0; x<cols; x++)
		{
			add(headings[x] = new JLabel());
			headings[x].setHorizontalAlignment(JLabel.CENTER);
		}
		for (int x=0; x<cols; x++) for (int y=0; y<rows; y++)
		{
			add(data[x][y] = new StringField());
			data[x][y].addFocusListener(this);
			data[x][y].addKeyListener(this);
			data[x][y].addFieldListener(this);
		}
		tableListeners = new LinkedList<TableListener>();
	}
	
	public void setBounds(int x, int y, int width, int height)
	{
		super.setBounds(x, y, width, height);
		this.width = width;
		this.height = height;
		refresh();
	}
	
	public void refresh()
	{
		int offset=0, rowHeight=height/(rows+1),colWidth;
		for(int a=0; a<cols; a++)
		{
			colWidth = colWidthInChars[a]*width/totalWidthInChars;
			headings[a].setBounds(offset, 0, colWidth, rowHeight);
			for (int b=0; b<rows; b++) data[a][b].setBounds(offset, rowHeight*(b+1), colWidth, rowHeight);
			offset+=colWidth;
		}
	}
	
	public void setCol(int colNo, String caption, int alignment, int widthInChars, boolean editable)
	{
		headings[colNo].setText(caption);
		colWidthInChars[colNo] = widthInChars;
		for(int x=0; x<rows; x++) 
		{
			data[colNo][x].setHorizontalAlignment(alignment);
			data[colNo][x].setEditable(editable);
		}
		refresh();
	}
	
	public void selectCell(int row, int col)
	{
		data[col][row].requestFocus();
	}
	
	public int getRow()
	{
		return selectedRow;
	}
	
	public int getCol()
	{
		return selectedCol;
	}
	
	public void setText(int row, int col, String datum)
	{
		data[col][row].setText(datum);
	}
	
	public void setText(String datum)
	{
		setText(selectedRow, selectedCol, datum);
	}
	
	public String getText(int row, int col)
	{
		return data[col][row].getText();
	}
	
	public String getText()
	{
		return getText(selectedRow, selectedCol);
	}
	
	public void addTableListener(TableListener listener)
	{
		tableListeners.add(listener);
	}
	
	public void addKeyListener(int col, KeyListener listener)
	{
		for(int x=0; x<rows; x++) data[col][x].addKeyListener(listener);
	}
	
	public void addFocusListener(int col, FocusListener listener)
	{
		for(int x=0; x<rows; x++) data[col][x].addFocusListener(listener);
	}
	
	public void addActionListener(int col, ActionListener listener)
	{
		for(int x=0; x<rows; x++) data[col][x].addActionListener(listener);
	}
	
	public void addMouseListener(int col, MouseListener listener)
	{
		for(int x=0; x<rows; x++) data[col][x].addMouseListener(listener);
	}
	
	public void addMouseMotionListener(int col, MouseMotionListener listener)
	{
		for(int x=0; x<rows; x++) data[col][x].addMouseMotionListener(listener);
	}
	
	public void setInputVerifier(int col, InputVerifier verifier)
	{
		for(int x=0; x<rows; x++) data[col][x].setInputVerifier(verifier);
	}

	public void focusGained(FocusEvent e) 
	{
		JTextField selected = (JTextField)e.getSource();
		for(int x=0; x<cols; x++) for(int y=0; y<rows; y++) if (selected==data[x][y])
		{
			selectedCol = x;
			selectedRow = y;
			break;
		}
		bg = selected.getBackground();
		selected.setBackground(Color.YELLOW);
		oldValue = selected.getText();
		for(int x=0; x<tableListeners.size(); x++) tableListeners.get(x).enteredCell(selectedCol, selectedRow, selected.getText());
	}
	
	public void focusLost(FocusEvent arg0) 
	{
		data[selectedCol][selectedRow].setBackground(bg);
		for(int x=0; x<tableListeners.size(); x++) 
			tableListeners.get(x).leftCell(selectedCol, selectedRow, data[selectedCol][selectedRow].getText());
	}
	
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode()==KeyEvent.VK_UP && selectedRow>0) data[selectedCol][selectedRow-1].requestFocus();
		if (e.getKeyCode()==KeyEvent.VK_DOWN && selectedRow<rows-1) data[selectedCol][selectedRow+1].requestFocus();
		if (e.getKeyCode()==KeyEvent.VK_LEFT && selectedCol>0) data[selectedCol-1][selectedRow].requestFocus();
		if (e.getKeyCode()==KeyEvent.VK_RIGHT && selectedCol<cols-1) data[selectedCol+1][selectedRow].requestFocus();
	}
	
	public void fieldChanged(ChangingTextField source, String oldText,
			String newText) 
	{
		for(int x=0; x<tableListeners.size(); x++) 
			tableListeners.get(x).changedCell(selectedCol, selectedRow, oldText, newText);
	}
	
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent arg0) {}
}