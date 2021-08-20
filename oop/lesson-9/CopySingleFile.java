import io.*;

import java.io.*;

public class CopySingleFile 
{
	static String path="", destination="";
	static FileInputStream fis;
	static FileOutputStream fos;
	
	public static void enterSourceFileName()
	{
		loop : while (true)
		{
			try
			{
				path = Keyboard.readString("Path of file :");
				fis = new FileInputStream(path);
				break loop;
			}
			catch(FileNotFoundException e)
			{
				Screen.show(path, "Does not exist");
			}
		}
	}
	
	public static void enterDestinationFileName() 
	{
		destination = Keyboard.readString("Enter destination file");
		try 
		{
			fos = new FileOutputStream(destination);
		}
		catch (FileNotFoundException e) {}
	}
	
	public static void copyFile(File file) throws IOException
	{
		while (true)
		{
			byte[] b = new byte[1048576];
			int i = fis.read(b);
			fos.write(b);
			if(i==-1) break;
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		enterSourceFileName();
		enterDestinationFileName();
		File file = new File(path);
		copyFile(file);
		Screen.show("COMPLETED", "");
		fis.close();
		fos.close();
	}
}
