import java.awt.*;
import java.awt.event.*;

import io.Screen;

import javax.swing.*;

import gui.*;
import gui.adapters.FocusRefuser;
import number.*;

public class Minesweeper extends GridForm implements MouseListener
{
	String[][] fieldText = new String[10][10];
	JTextField[][] field = new JTextField[10][10]; 
	boolean[][] fieldMined = new boolean[10][10];
	boolean[][] fieldCleared = new boolean[10][10];
	Color[][] fieldColor = new Color[10][10];
	
	Color bgColor;
	int count=0;
	
	Font formFont = new Font("Sans Serif",Font.BOLD,30);
	
	public Minesweeper()
	{
		super("Minesweeper",10,10,40,70,10,10);
		
		for (int x=1; x<=8; x++) for(int y=1; y<=8; y++)
			{
				field[x][y] = addTextField(JTextField.CENTER,x,y,1,1);
				FocusRefuser.plugTo(field[x][y]);
				field[x][y].addMouseListener(this);
				field[x][y].setBackground(Color.LIGHT_GRAY);
			}
		
		int[] minePlaces = Random.random(1, 63,10);
		for (int x=0; x<10; x++) 
		{
			fieldText[minePlaces[x]/8+1][minePlaces[x]%8+1]="#";
			fieldMined[minePlaces[x]/8+1][minePlaces[x]%8+1]=true;
		}
		
		for (int x=1; x<=8; x++) for(int y=1; y<=8; y++)
		{
			int count = 0;
			for (int a=1; a>=-1; a--) for (int b=1; b>=-1; b--)if(fieldMined[x+a][y+b])count++;
			if (!fieldMined[x][y]&&count!=0)
			{	
				fieldText[x][y]=""+count;
				if (count==1) fieldColor[x][y]=(Color.BLUE);
				if (count==2) fieldColor[x][y]=(Color.GREEN);
				if (count==3) fieldColor[x][y]=(Color.ORANGE);
				if (count>=4) fieldColor[x][y]=(Color.RED);
			}
		}
		
		Appearance.setFont(formFont, components);
	}
	

	public void mouseClicked(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e)
	{
		bgColor = ((JTextField)e.getSource()).getBackground();
		if (bgColor!=Color.WHITE) ((JTextField)e.getSource()).setBackground(Color.DARK_GRAY);
	}
	
	public void mouseExited(MouseEvent e)
	{
		((JTextField)e.getSource()).setBackground(bgColor);
	}
	
	public void mousePressed(MouseEvent e)
	{
		JTextField selField = (JTextField)e.getSource();
		if (e.getButton()==MouseEvent.BUTTON1)
		{
			for (int x=1; x<=8; x++) for(int y=1; y<=8; y++) if (field[x][y]==selField)
			{
				selField.setBackground(Color.WHITE);
				bgColor = Color.WHITE;
				selField.setForeground(fieldColor[x][y]);
				selField.setText(fieldText[x][y]);
				if (!fieldCleared[x][y])
				{
					count++;
					fieldCleared[x][y]=true;
				}
				if(fieldMined[x][y])lose();
				else if (count==54) win();
			}
		}
		else if ((e.getButton()==MouseEvent.BUTTON3)&&(selField.getBackground()!=Color.WHITE))
		{
			selField.setText("!");
		}
	}
	
	public void lose()
	{
		for (int a=1; a<=8; a++) for(int b=1; b<=8; b++)
		{
			field[a][b].setBackground(Color.WHITE);
			bgColor = Color.WHITE;
			field[a][b].setForeground(fieldColor[a][b]);
			field[a][b].setText(fieldText[a][b]);
		}
	}
	
	public void win()
	{
		for (int a=1; a<=8; a++) for(int b=1; b<=8; b++)
		{
			field[a][b].setBackground(Color.WHITE);
			bgColor = Color.WHITE;
			field[a][b].setForeground(Color.GREEN);
			field[a][b].setText("");
		}
		field[2][4].setText("C");
		field[3][4].setText("L");
		field[4][4].setText("E");
		field[5][4].setText("A");
		field[6][4].setText("R");
		field[7][4].setText("!");
	}
	
	public void mouseReleased(MouseEvent e) {}
	
	public static void main(String[] args)
	{
		new Minesweeper();
	}
}
