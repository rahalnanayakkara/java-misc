package file;

import io.Screen;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ExtensionFilter extends FileFilter 
{
	String description, extension;
	
	public ExtensionFilter(String extension, String description)
	{
		this.extension = "."+extension;
		this.description = description;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public boolean accept(File file)
	{
		return (file.isDirectory()||file.getName().endsWith(extension));
	}
}
