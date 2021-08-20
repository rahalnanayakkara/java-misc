package gui.tables;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;

import gui.*;
import gui.fields.*;


public class ScrollableTable extends ContainerPanel implements FocusListener, KeyListener, FieldListener, AdjustmentListener
{
	JScrollBar scrollBar;
	JLabel[] headings;
	String[][] data;
	StringField[][] visibleData;
	int[] colWidthInChars;
	int rows, cols, totalWidthInChars, width, height, selectedRow, selectedCol, visibleRows, visibleFrom;
	String oldValue;
	LinkedList<TableListener> tableListeners;
	Color bg;
	boolean fieldChanged=true;
	
	public ScrollableTable(int rows, int cols, int totalWidthInChars, int visibleRows)
	{
		super((visibleRows+1)*cols+1);
		this.rows = rows;
		this.cols = cols;
		this.totalWidthInChars = totalWidthInChars;
		this.visibleRows = visibleRows;
		visibleFrom=0;
		headings = new JLabel[cols];
		visibleData = new StringField[cols][visibleRows];
		colWidthInChars = new int[cols];
		data = new String[cols][rows];
		for(int x=0; x<cols; x++)
		{
			add(headings[x] = new JLabel());
			headings[x].setHorizontalAlignment(JLabel.CENTER);
		}
		for (int x=0; x<cols; x++) for (int y=0; y<visibleRows; y++)
		{
			add(visibleData[x][y] = new StringField());
			visibleData[x][y].addFocusListener(this);
			visibleData[x][y].addKeyListener(this);
			visibleData[x][y].addFieldListener(this);
		}
		add(scrollBar = new JScrollBar(JScrollBar.VERTICAL,0, visibleRows, 0, rows));
		tableListeners = new LinkedList<TableListener>();
		scrollBar.addAdjustmentListener(this);
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
		int offset=0, rowHeight=height/(visibleRows+1),colWidth;
		for(int a=0; a<cols; a++)
		{
			colWidth = colWidthInChars[a]*width*9/(totalWidthInChars*10);
			headings[a].setBounds(offset, 0, colWidth, rowHeight);
			for (int b=0; b<visibleRows; b++) visibleData[a][b].setBounds(offset, rowHeight*(b+1), colWidth, rowHeight);
			offset+=colWidth;
		}
		scrollBar.setBounds(offset, rowHeight, width/10, height-rowHeight);
	}
	
	public void refreshText()
	{
		fieldChanged=false;
		for(int x=0; x<cols; x++) for(int y=0; y<visibleRows; y++) visibleData[x][y].setText(data[x][y+visibleFrom]);
		fieldChanged=true;
	}
	
	public void setCol(int colNo, String caption, int alignment, int widthInChars, boolean editable)
	{
		headings[colNo].setText(caption);
		colWidthInChars[colNo] = widthInChars;
		for(int x=0; x<visibleRows; x++) 
		{
			visibleData[colNo][x].setHorizontalAlignment(alignment);
			visibleData[colNo][x].setEditable(editable);
		}
		refresh();
	}
	
	public void selectCell(int row, int col)
	{
		visibleData[col][row].requestFocus();
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
		data[col][row]=datum;
	}
	
	public void setText(String datum)
	{
		setText(selectedRow+visibleFrom, selectedCol, datum);
	}
	
	public String getText(int row, int col)
	{
		return data[col][row];
	}
	
	public String getText()
	{
		return getText(selectedRow+visibleFrom, selectedCol);
	}
	
	public void addTableListener(TableListener listener)
	{
		tableListeners.add(listener);
	}
	
	public void addKeyListener(int col, KeyListener listener)
	{
		for(int x=0; x<visibleRows; x++) visibleData[col][x].addKeyListener(listener);
	}
	
	public void addFocusListener(int col, FocusListener listener)
	{
		for(int x=0; x<visibleRows; x++) visibleData[col][x].addFocusListener(listener);
	}
	
	public void addActionListener(int col, ActionListener listener)
	{
		for(int x=0; x<visibleRows; x++) visibleData[col][x].addActionListener(listener);
	}
	
	public void addMouseListener(int col, MouseListener listener)
	{
		for(int x=0; x<visibleRows; x++) visibleData[col][x].addMouseListener(listener);
	}
	
	public void addMouseMotionListener(int col, MouseMotionListener listener)
	{
		for(int x=0; x<visibleRows; x++) visibleData[col][x].addMouseMotionListener(listener);
	}
	
	public void setInputVerifier(int col, InputVerifier verifier)
	{
		for(int x=0; x<visibleRows; x++) visibleData[col][x].setInputVerifier(verifier);
	}

	public void focusGained(FocusEvent e) 
	{
		JTextField selected = (JTextField)e.getSource();
		for(int x=0; x<cols; x++) for(int y=0; y<visibleRows; y++) if (selected==visibleData[x][y])
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
		visibleData[selectedCol][selectedRow].setBackground(bg);
		for(int x=0; x<tableListeners.size(); x++) 
			tableListeners.get(x).leftCell(selectedCol, selectedRow, visibleData[selectedCol][selectedRow].getText());
	}
	
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode()==KeyEvent.VK_UP && selectedRow>0) visibleData[selectedCol][selectedRow-1].requestFocus();
		else if (e.getKeyCode()==KeyEvent.VK_UP && selectedRow==0 && visibleFrom>0) 
			{
				visibleFrom--; 
				refreshText();
				scrollBar.setValue(visibleFrom);
			}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN && selectedRow<visibleRows-1) visibleData[selectedCol][selectedRow+1].requestFocus();
		else if (e.getKeyCode()==KeyEvent.VK_DOWN && selectedRow==visibleRows-1 && visibleFrom<rows-visibleRows) 
			{
				visibleFrom++; 
				refreshText();
				scrollBar.setValue(visibleFrom);
			}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT && selectedCol>0) visibleData[selectedCol-1][selectedRow].requestFocus();
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT && selectedCol<cols-1) visibleData[selectedCol+1][selectedRow].requestFocus();
	}
	
	public void fieldChanged(ChangingTextField source, String oldText,
			String newText) 
	{
		if (fieldChanged)
		{
			data[selectedCol][selectedRow+visibleFrom] = newText;
			for(int x=0; x<tableListeners.size(); x++) 
				tableListeners.get(x).changedCell(selectedCol, selectedRow, oldText, newText);
		}
	}
	
	public void adjustmentValueChanged(AdjustmentEvent e) 
	{
		visibleFrom = e.getValue();
		refreshText();
	}
	
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent arg0) {}

}