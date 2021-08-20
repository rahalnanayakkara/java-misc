import java.io.*;

import io.*;

public class ComparePaths 
{
	public static File inputDir(String prompt) throws IOException
	{
		String path = Keyboard.readString(prompt);
		File dir = new File(path);
		if (!dir.exists()) throw new IOException("Directory "+path+" not found");
		if (dir.isFile()) throw new IOException(path+" is a file, nt a directory");
		return dir;
	}

	public static void main(String[] args) 
	{
		try
		{
			File dir1 = inputDir("Enter first Directory");
			File dir2 = inputDir("Enter second Directory");
			String[] list = dir1.list();
			for (String filename: list)
			{
				File file1 = new File(dir1,filename);
				File file2 = new File(dir2,filename);
				if (!file2.exists()) Screen.show(filename, "missing");
				else if (file2.length()!=file1.length()) Screen.show(filename, "Size mismatch");
			}
		}
		catch(IOException e)
		{
			System.out.println(e.toString());
		}
		
	}

}
