import io.*;

import java.io.*;

public class RenameExe 
{
	public static void main(String[] args) throws IOException 
	{
		String path = Keyboard.readString("Path of directory:");
		File dir = new File(path);
		String[] list = dir.list();
		for(String filename : list) if (filename.endsWith(".exe"))
		{
			File file = new File(dir,filename);
			file.renameTo(new File(dir,filename+"1"));
		}
	}
}
