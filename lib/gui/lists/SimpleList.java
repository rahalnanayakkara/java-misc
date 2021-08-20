package gui.lists;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.event.*;

import gui.*;

public class SimpleList extends ContainerPanel implements MouseListener, KeyListener
{
	String[] data;
	JLabel[] lblData;
	Color fg, bg;
	int selected=0, n, maxItems;
	LinkedList<ListSelectionListener> listeners;
	
	public SimpleList(int maxItems, String[] data)
	{
		super(maxItems);
		setLayout(null);
		lblData = new JLabel[maxItems];
		this.maxItems = maxItems;
		this.data = new String[maxItems];
		n=data.length;
		for(int x=0; x<maxItems; x++) 
		{
			add(lblData[x] = new JLabel());
			lblData[x].addMouseListener(this);
			lblData[x].setOpaque(true);
		}
		for(int x=0; x<n; x++) 
		{
			this.data[x] = data[x];
			lblData[x].setText(data[x]);
		}
		fg = lblData[0].getForeground();
		bg = lblData[0].getBackground();
		listeners = new LinkedList<ListSelectionListener>();
		setFocusable(true);
		addKeyListener(this);
	}
	
	public SimpleList(int maxItems, String itemList)
	{
		this(maxItems, itemList.split(","));
	}
	
	public void add(String datum)
	{
		while (true) if(!data[n].equals("")) n++; else break;
		data[n] = datum;
		lblData[n].setText(datum);
		n++;
	}
	
	public void insert(int index, String datum)
	{
		data[index] = datum;
		lblData[index].setText(datum);
		if(index>=n) n++;
	}
	
	public void remove(int index)
	{
		data[index]="";
		lblData[index].setText("");
		if (index==n-1) n--;
	}
	
	public void remove(String datum)
	{
		for(int x=0; x<data.length; x++) if(data[x].equals(datum))
		{
			remove(x);
			if(x==n-1) n--; 
		}
	}
	
	public void removeAll()
	{
		for(int x=0; x<maxItems; x++)remove(x);
		n=0;
	}
	
	public void setHorizontalAlignment(int a)
	{
		for(int x=0; x<maxItems; x++) lblData[x].setHorizontalAlignment(a);
	}
	
	public void setBounds(int x, int y, int width, int height)
	{
		super.setBounds(x,y,width,height);
		int rowHeight = height/maxItems;
		for(int a=0; a<maxItems; a++) 
		{
			lblData[a].setBounds(0,rowHeight*a,width,rowHeight);
			lblData[a].setText(data[a]);
		}
	}
	
	public int getSelectedIndex()
	{
		return selected;
	}
	
	public String getText()
	{
		return lblData[selected].getText();
	}
	
	public void setSelectedIndex(int index)
	{
		lblData[selected].setForeground(fg);
		lblData[selected].setBackground(bg);
		int oldVal = selected;
		selected = index;
		lblData[selected].setForeground(bg);
		lblData[selected].setBackground(fg);
		listValueChanged(oldVal);
	}
	
	public void setSelectedText(String text)
	{
		for(int x=0; x<maxItems; x++) if(data[x].equals(text)) setSelectedIndex(x);
	}
	
	public void addListSelectionListener(ListSelectionListener listener)
	{
		listeners.add(listener);
	}

	public void mouseClicked(MouseEvent e) 
	{
		lblData[selected].setForeground(fg);
		lblData[selected].setBackground(bg);
		int oldVal = selected;
		JLabel lblSelected = (JLabel)e.getSource();
		for(int x=0; x<maxItems; x++) if (lblSelected==lblData[x]) this.selected = x;
		lblSelected.setForeground(bg);
		lblSelected.setBackground(fg);
		listValueChanged(oldVal);
		requestFocus();
	}
	
	public void listValueChanged(int oldVal)
	{
		for(int x=0; x<listeners.size(); x++) listeners.get(x).valueChanged(new ListSelectionEvent(lblData[selected],oldVal,selected,false));
	}
	
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode()==KeyEvent.VK_UP&&selected>0) setSelectedIndex(selected-1);
		if (e.getKeyCode()==KeyEvent.VK_DOWN&&selected<maxItems-1) setSelectedIndex(selected+1);
	}
	
	public void keyReleased(KeyEvent arg0){}
	public void mousePressed(MouseEvent arg0) {}	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {setSelectedIndex(selected-1);}
}
