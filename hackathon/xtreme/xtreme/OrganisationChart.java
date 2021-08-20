package xtreme;

//Don't place your source in a package
import java.util.*;

import java.lang.*;
import java.io.*;

//Please name your class Main
class OrganisationChart 
{
 static int n,q;
 static Division root;
 static Division[] divisions;
 
 	
 	
	public static void main (String[] args) throws java.lang.Exception
	{
		
		Scanner in = new Scanner("10 3\n" + 
				"company NONE 10 100\n" + 
				"engineering company 5 30\n" + 
				"team1 engineering 10 10\n" + 
				"team2 engineering 0 0\n" + 
				"team2part1 team2 5 5\n" + 
				"team2part2 team2 3 3\n" + 
				"manufacturing company 40 40\n" + 
				"finance company 0 0\n" + 
				"accounting finance 0 0\n" + 
				"sales finance 0 5\n" + 
				"company 1\n" + 
				"team2 2\n" + 
				"accounting 1");
		
	    n = in.nextInt();
	    q = in.nextInt();
	    
	    divisions = new Division[n];
	    
	    String rootName = in.next();
	    in.next();
	    root = new Division(rootName,in.nextLong(), in.nextLong());
	    divisions[0]=root;
	    
	    
	    for (int i=1; i<n; i++)
	    {
	        String divisionName = in.next();
	        String parentName = in.next();
	        Division parent = null;
	        for (int x=0; x<i; x++) if(divisions[x].name.equals(parentName))
	        {
	            parent = divisions[x];
	            break;
	        }
	        divisions[i] = new Division(divisionName,in.nextLong(),in.nextLong(),parent);
	        divisions[i].parent.addChild(divisions[i]);
	    }
	    
	    
	    for (int x=0; x<n; x++)
	    {
	    	Division d=divisions[x];
	    	if (x==0) System.out.println(d.name+" "+d.size1+" "+d.size2);
	    	else
	        System.out.println(d.name+" "+d.parent.name+" "+d.size1+" "+d.size2);
	    }
	    
	}
	
	
	public static class Division
	{
	    String name;
	    long size1, size2;
	    Division parent;
	    ArrayList<Division> children = new ArrayList<Division>();
	    
	    public Division(String name, long size1, long size2)
	    {
	        this.name = name;
	        this.size1 = size1;
	        this.size2 = size2;
	        parent = null;
	    }
	    
	    public Division(String name, long size1, long size2, Division parent)
	    {
	        this(name, size1, size2);
	        this.parent = parent;
	    }
	    
	    public void addChild(Division div)
	    {
	        children.add(div);
	    }
	}
	
}