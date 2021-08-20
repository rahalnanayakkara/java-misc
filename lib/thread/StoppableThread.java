package thread;

public class StoppableThread extends Thread 
{
	boolean shouldStop;
	
	public void terminate()
	{
		shouldStop = true;
	}
	
	public void run()
	{
		while(!shouldStop) repeat();
	}
	
	public void repeat(){}
}
