package xtreme;

// Don't place your source in a package
import java.util.*;
import java.lang.*;
import java.io.*;

// Please name your class Main
class BinaryTree {
	public static void main (String[] args) throws java.lang.Exception 
	{
	    Scanner in = new Scanner("abdce"+" "+"abcde");
		String infix = in.next();
		String prefix = in.next();
		int length = infix.length();
		int row=0;
		String[][] array = new String[26][26];
		for(int x=0; x<26; x++) for(int y=0; y<26; y++) array[x][y] = " ";
		array[0][infix.indexOf('a')]="a";
		for(char c='b'; c<='z'; c++)
		{
		    int ind = infix.indexOf(c);
		    if (ind==-1) break;
		    if (ind<infix.indexOf(c-1)) row++;
		    System.out.println(c+" "+ind);
		    array[row][ind] = c+"";
		}
		for(int x=0; x<26; x++) 
		{
			for(int y=0; y<26; y++) System.out.print(array[x][y]);
			System.out.println("");
		}
	}
	
}