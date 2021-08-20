package file;

import io.Keyboard;

import java.io.*;

public class Delete 
{
	public static void delete(File dir)
	{
		String[] list = dir.list();
		for(String filename : list)
		{
			File file = new File(dir,filename);
			if (file.isFile()) file.delete();
			if (file.isDirectory()) delete(file);
		}
		dir.delete();
	}
	
	
	public static void main(String[] args) 
	{
		delete(new File(Keyboard.readString("Enter address")));
	}

}
