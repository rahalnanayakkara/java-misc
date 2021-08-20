package test;

import io.ReadFile;

public class ReadMaze 
{
	static final int NORTH=0, SOUTH=1, EAST=2, WEST=3;
	static int[][] rawData = new int[196][4];
	static int[] data = new int[98];
	static void loadMaze()
	{
		ReadFile.open("Data2.dat");
		for(int x=0; x<14; x++) for(int y=0; y<14; y++)
		{
			rawData[x+14*y][NORTH] = ReadFile.readInt();
			rawData[x+14*y][SOUTH] = ReadFile.readInt();
			rawData[x+14*y][EAST] = ReadFile.readInt();
			rawData[x+14*y][WEST] = ReadFile.readInt();
			int val = rawData[x+14*y][WEST]*8 + rawData[x+14*y][EAST]*4 + rawData[x+14*y][SOUTH]*2 + rawData[x+14*y][NORTH];
			if (x%2==0) val*=16;
			data[(x+14*y)/2] = data[(x+14*y)/2] | val;
		}
		ReadFile.close();
	}

	public static void main(String[] args) 
	{
		loadMaze();
		
		System.out.print("{");
		for(int a=0; a<98; a++)
		{
			System.out.print(data[a]);
			if(a!=97)System.out.print(",");
		}
		System.out.print("}");
	}

}
