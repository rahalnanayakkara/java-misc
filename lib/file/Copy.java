package file;
import io.*;

import java.io.*;

public class Copy 
{
	public static void copyFile(File original, File copy) throws Exception
	{
		FileInputStream fis = new FileInputStream(original.getAbsoluteFile());
		FileOutputStream fos = new FileOutputStream(copy.getAbsoluteFile());
		while (true)
		{
			byte[] b = new byte[1024];
			int i = fis.read(b);
			fos.write(b);
			if(i==-1) break;
		}
		fis.close();
		fos.close();
	}
	
	public static void copyDir(File source, File dest) throws Exception
	{
		dest.mkdir();
		String[] list = source.list();
		for(String filename : list)
		{
			File file = new File(source,filename);
			if (file.isFile()) copyFile(file , new File(dest, filename));
			else if(file.isDirectory()) copyDir(file,new File(dest,filename));
		}
	}
	
	public static void copy(String sourcePath, String destination) throws Exception
	{
		File sourceDir = new File(sourcePath);
		String parentDir = destination+"\\";
		copyDir(new File(parentDir+sourceDir.getName()), sourceDir);
	}
	
	public static void main(String[] args) throws Exception
	{
		copy(Keyboard.readString("Path of Source:"),Keyboard.readString("Destination path"));
	}
}
