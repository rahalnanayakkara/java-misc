package gui.adapters;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClosingConfirmer extends WindowAdapter
{
	public static ClosingConfirmer ONE = new ClosingConfirmer();
	
	public void windowClosing(WindowEvent e)
	{
		JFrame source = (JFrame)e.getSource();
		int option = JOptionPane.showConfirmDialog
				(source, "Are you sure you want to close ths window?", "Confirm Closing", JOptionPane.YES_NO_OPTION);
		if(option==JOptionPane.YES_OPTION)
			source.dispose();
	}
	
	public static void plugTo(JFrame frame)
	{
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(ONE);
	}
}
