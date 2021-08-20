import io.Keyboard;
import io.Screen;

import java.io.*;

public class RenameExe2 
{
	public static void renameExe(File dir)
	{
		String[] list = dir.list();
		for(String filename : list)
		{
			File file = new File(dir,filename);
			if (file.isDirectory()) renameExe(file);
			else if (filename.endsWith(".exe"))file.renameTo(new File(dir,filename+"1"));
		}
	}
	
	public static void main(String[] args) 
	{
		String path = Keyboard.readString("Path of directory:");
		File dir = new File(path);
		renameExe(dir);
		Screen.show("COMPLETED", "");
	}
}
