package savings;

import io.ReadFile;
import io.Screen;
import io.WriteFile;

import java.util.*;

public class Store<DataType> implements java.io.Serializable 
{
	protected Vector<DataType> data;
	protected int n;
	public String name;
	
	public Store(String name)
	{
		this.name = name;
	}
	
	public void load()
	{
		data = (Vector<DataType>)(ReadFile.loadObject("data\\"+(name)+".dat"));
		if (data==null) data = new Vector<DataType>();
		else n =data.size();
	}
	
	public void save()
	{
		WriteFile.saveObject("data\\"+name+".dat", data);
	}
	
	public int append(DataType datum)
	{
		data.add(datum);
		return ++n;
	}
	
	public DataType get(int no)
	{
		return data.get(no-1);
	}
	
	public int getN()
	{
		return n;
	}
	
	public DataType[] getAll()
	{
		return (DataType[])data.toArray();
	}
}
