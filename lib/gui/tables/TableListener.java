package gui.tables;

public interface TableListener 
{
	public void enteredCell(int col, int row, String datum);
	
	public void leftCell(int col, int row, String datum);
	
	public void changedCell(int col, int row, String oldValue, String newValue);
}
