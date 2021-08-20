package thread;
import javax.swing.*;

public class SecondsCounter extends StoppableThread 
{
	JTextField field;
	int seconds;
	
	public SecondsCounter(JTextField field)
	{
		super();
		this.field = field;
	}
	
	public void repeat()
	{
		field.setText(""+seconds);
		seconds++;
		try
		{
			sleep(1000);
		}
		catch(InterruptedException e){}
	}
}
