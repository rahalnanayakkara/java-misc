package gui.lists;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.event.*;

import gui.*;

public class DataList extends ContainerPanel implements MouseListener, KeyListener, AdjustmentListener 
{
	int visibleItems, selected, n, displayIndex;
	boolean scrollWith;
	LinkedList<String> data;
	Color fg, bg;
	JLabel[] lblVisible;
	JScrollBar scrollBar;
	LinkedList<ListSelectionListener> listeners;
	
	public DataList(int visibleItems)
	{
		super(visibleItems+1);
		this.visibleItems = visibleItems;
		lblVisible = new JLabel[visibleItems];
		for(int x=0; x<visibleItems; x++) 
		{
			add(lblVisible[x] = new JLabel());
			lblVisible[x].setOpaque(true);
			lblVisible[x].addMouseListener(this);
		}
		fg = lblVisible[0].getForeground();
		setBackground(Color.WHITE);
		data = new LinkedList<String>();
		listeners = new LinkedList<ListSelectionListener>();
		add(scrollBar = new JScrollBar(JScrollBar.VERTICAL,0,visibleItems,0,visibleItems));
		scrollBar.addAdjustmentListener(this);
		setFocusable(true);
		addKeyListener(this);
	}
	
	public DataList(int visibleItems, String[] data)
	{
		this(visibleItems);
		setData(data);
	}
	
	public DataList(int visibleItems, String data)
	{
		this(visibleItems, data.split(","));
	}
	
	public void setHorizontalAlignment(int a)
	{
		for(int x=0; x<visibleItems; x++) lblVisible[x].setHorizontalAlignment(a);
	}
	
	public void setForeground(Color fg)
	{
		super.setForeground(fg);
		this.fg=fg;
	}
	
	public void setBackground(Color bg)
	{
		super.setBackground(bg);
		this.bg=bg;
	}
	
	public void setData(String[] data)
	{
		n=data.length;
		for(int x=0; x<n; x++) this.data.add(data[x]);
		for(int x=0; x<visibleItems&&x<n; x++)lblVisible[x].setText(data[x]);
		scrollBar.setMaximum(n);
		displayIndex=0;
		refresh();
	}
	
	public void refresh()
	{
		for(int x=0; x<visibleItems&&x<n; x++) 
			lblVisible[x].setText(data.get(displayIndex+x));
		scrollBar.setMaximum(data.size());
		scrollBar.setValue(displayIndex);
		scrollBar.setVisibleAmount(visibleItems);
		if (scrollWith&&(data.size()-visibleItems>0)) scrollBar.setValue(data.size()-visibleItems);
		
		//BUG : when one property of the JScrollBar is changed all the others are automatically made 0
	}
	
	public void add(String datum)
	{
		if(n==0)setData(new String[]{datum});
		else 
		{
			data.add(datum);
			n++;
			refresh();
		}
	}
	
	public void insert(int index, String datum)
	{
		data.set(index,datum);
		refresh();
	}
	
	public void remove(int index)
	{
		data.set(index, "");
		if (index==n-1) n--;
		refresh();
	}
	
	public void remove(String datum)
	{
		for(int x=data.size()-1; x>=0 ; x--)
			if (datum.equals(data.get(x)))
			{
				remove(x);
				if(x==n-1) n--;
			}
	}
	
	public void removeAll()
	{
		for(int x=data.size()-1; x>=0 ; x--) remove(x);
		n=0;
	}
	
	public void addListSelectionListener(ListSelectionListener listener)
	{
		listeners.add(listener);
	}
	
	public void listValueChanged(int oldVal)
	{
		for(int x=0; x<listeners.size(); x++) 
			listeners.get(x).valueChanged(new ListSelectionEvent(lblVisible[selected-displayIndex],oldVal,selected,false));
	}
	
	public void setBounds(int x, int y, int width, int height)
	{
		super.setBounds(x, y, width, height);
		int rowHeight = height/visibleItems;
		for(int a=0; a<visibleItems; a++)
			lblVisible[a].setBounds(0,rowHeight*a,width*9/10,rowHeight);
		scrollBar.setBounds(width*9/10,0,width/10,height);
		refresh();
	}
	
	public int getSelectedIndex()
	{
		return selected;
	}
	
	public String getText()
	{
		return data.get(selected);
	}
	
	public void setSelectedIndex(int index)
	{
		if(index>=displayIndex&&index<displayIndex+visibleItems)
		{	
			lblVisible[selected-displayIndex].setForeground(fg);
			lblVisible[selected-displayIndex].setBackground(bg);
			int oldVal = selected;
			selected = index;
			lblVisible[selected-displayIndex].setForeground(bg);
			lblVisible[selected-displayIndex].setBackground(fg);
			listValueChanged(oldVal);
		}
		else if(index<displayIndex)
		{
			displayIndex=index;
			refresh();
			setSelectedIndex(index);
		}
		else if (index>=displayIndex+visibleItems)
		{
			displayIndex = index-visibleItems+1;
			refresh();
			setSelectedIndex(index);
		}
	}
	
	public void setSelectedText(String text)
	{
		for(int x=0; x<data.size(); x++) if (data.get(x).equals(text))
		{
			setSelectedIndex(x);
			break;
		}
	}
	
	public void scrollWith(boolean b)
	{
		scrollWith=b;
	}

	public void mouseClicked(MouseEvent e) 
	{
		for(int x=0; x<visibleItems; x++)
		{
			lblVisible[x].setForeground(fg);
			lblVisible[x].setBackground(bg);
		}
		int oldVal = selected;
		JLabel lblSelected = (JLabel)e.getSource();
		for(int x=0; x<visibleItems; x++) if(lblSelected==lblVisible[x]) selected=x+displayIndex;
		lblVisible[selected-displayIndex].setForeground(bg);
		lblVisible[selected-displayIndex].setBackground(fg);
		listValueChanged(oldVal);
		requestFocus();
	}
	
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode()==KeyEvent.VK_UP&&selected>0) setSelectedIndex(selected-1);
		if (e.getKeyCode()==KeyEvent.VK_DOWN&&selected<data.size()-1) setSelectedIndex(selected+1);
	}
	
	public void adjustmentValueChanged(AdjustmentEvent e)
	{
		selected-=displayIndex;
		displayIndex=e.getValue();
		selected+=displayIndex;
		refresh();
	}
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}

	
}
