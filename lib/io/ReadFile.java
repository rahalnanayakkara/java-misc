package io;
import java.io.*;
public class ReadFile
{
	static FileInputStream fis;
	static ObjectInputStream ois;
	static String path;

	public static void open(String path)
	{
		ReadFile.path = path;
    	try
      	{	
	    	fis = new FileInputStream(path);
	    	ois = new ObjectInputStream(fis);
	    }
      	catch ( IOException e )
      	{
			System.out.println("Error opening "+path);
       	}
    }

    public static int readInt()
    {
       	try 
		{ 
			return ois.readInt(); 
		}
        catch ( IOException e) 
		{ 
			System.out.println("Error Reading From "+ path);
        }
       	return 0;
    }
    
    public static int readBoolAsInt()
    {
       	try 
		{ 
			return ois.readBoolean()?0:1; 
		}
        catch ( IOException e) 
		{ 
			System.out.println("Error Reading From "+ path);
        }
       	
       	return 0;
    }
    
    public static long readLong()
    {
       	try 
		{ 
			return ois.readLong(); 
		}
        catch ( IOException e) 
		{ 
			System.out.println("Error Reading From "+ path);
        }
       	return 0;
    }

   public static String readString()
   {
   		try 
   		{ 
   			return ois.readUTF(); 
   		}
   		catch ( IOException e) 
   		{ 
   			System.out.println("Error Reading From "+ path);
   		}
   		return "";
   }	
   
   public static double readDouble()
   {
   		try 
   		{ 
   			return ois.readDouble(); 
   		}
   		catch ( IOException e) 
   		{ 
   			System.out.println("Error Reading From "+ path);
   		}
   		return 0.0;
   }	
   
   public static Object readObject()
   {
   		try 
   		{ 
   			return ois.readObject(); 
   		}
   		catch ( Exception e) 
   		{ 
   			System.out.println("Error Reading From " + path);
   		}
   		return null;
   }	
   
   public static void close()
   {
   		try
   		{
			ois.close();
			fis.close();
   		}
		catch ( IOException e)
		{
			System.out.println("Error Closing "+path);
		}
   }
   
   public static Object loadObject(String path)
   {
	   open(path);
	   Object obj = ReadFile.readObject();
	   close();
	   return obj;
   }
}

