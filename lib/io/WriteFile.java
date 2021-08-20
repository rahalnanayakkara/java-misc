package io;
import java.io.*;
public class WriteFile
{
	static FileOutputStream fos;
	static ObjectOutputStream oos;
	static String path;

	public static void open(String path)
	{
		WriteFile.path = path;
    	try
      	{	
	    	fos = new FileOutputStream(path);
	    	oos = new ObjectOutputStream(fos);
	    }
      	catch ( IOException e )
      	{
			System.out.println("Error Creating "+path);
       	}
    }

    public static void writeInt(int i)
    {
       	try 
		{ 
			oos.writeInt(i); 
		}
        catch ( IOException e) 
		{ 
			System.out.println("Error Writing to "+ path);
        }
    }
    
    public static void writeBoolean(boolean b)
    {
       	try 
		{ 
			oos.writeBoolean(b); 
		}
        catch ( IOException e) 
		{ 
			System.out.println("Error Writing to "+ path);
        }
    }

   public static void writeString(String s)
   {
   		try 
   		{ 
   			oos.writeUTF(s); 
   		}
   		catch ( IOException e) 
   		{ 
   			System.out.println("Error Writing to "+ path);
   		}
   }	
   
   public static void writeDouble(double d)
   {
   		try 
   		{ 
   			oos.writeDouble(d); 
   		}
   		catch ( IOException e) 
   		{ 
   			System.out.println("Error Writing to "+ path);
   		}
   }	
   
   public static void writeObject(Object o)
   {
   		try 
   		{ 
   			oos.writeObject(o); 
   		}
   		catch ( Exception e) 
   		{ 
   			System.out.println("Error Writing to " + path);
   		}
   }	
   
   public static void close()
   {
   		try
   		{
			oos.close();
			fos.close();
   		}
		catch ( IOException e)
		{
			System.out.println("Error Closing "+path);
		}
   }
   
   public static void saveObject(String filePath, Object object)
   {
	   open(filePath);
	   writeObject(object);
	   close();
   }
   
}

