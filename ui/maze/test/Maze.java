package test;

public class Maze 
{
	static final int NORTH=0, SOUTH=1, EAST=2, WEST=3;
	static final int startX=13, startY=0;
	static final int DEST=0, HOME=1;
	
	//known data
	static int[][] data = {{1,1,0,1},{1,0,0,0},{1,1,0,0},{1,1,1,0},{1,1,0,1},{1,0,0,0},{1,1,0,0},{1,0,1,0},{1,0,1,1},{1,1,0,1},{1,0,1,0},{1,0,0,1},{1,1,0,0},{1,1,1,0},{1,1,0,1},{0,1,0,0},{1,0,0,0},{1,1,0,0},{1,0,1,0},{0,1,0,1},{1,0,1,0},{0,0,1,1},{0,0,1,1},{1,1,0,1},{0,0,1,0},{0,1,0,1},{1,1,0,0},{1,0,1,0},{1,0,1,1},{1,0,0,1},{0,1,0,0},{1,0,1,0},{0,1,0,1},{1,1,0,0},{0,0,1,0},{0,0,0,1},{0,1,1,0},{1,1,0,1},{0,0,0,0},{1,1,0,0},{1,1,0,0},{0,1,1,0},{0,0,1,1},{0,1,0,1},{1,1,1,0},{0,0,1,1},{1,1,0,1},{1,0,0,0},{0,0,1,0},{0,1,0,1},{1,1,0,0},{1,1,0,0},{0,1,0,0},{1,0,1,0},{1,0,0,1},{1,1,1,0},{0,0,1,1},{1,0,0,1},{1,0,0,0},{0,1,1,0},{1,0,1,1},{0,0,1,1},{0,1,0,1},{1,1,0,0},{1,1,1,0},{1,0,0,1},{1,1,1,0},{0,0,1,1},{0,1,0,1},{1,0,1,0},{0,1,0,1},{0,1,1,0},{0,0,1,1},{1,1,0,1},{0,0,1,0},{0,1,1,1},{1,1,0,1},{1,1,0,0},{1,1,0,0},{0,0,0,0},{1,1,0,0},{0,1,0,0},{1,1,0,0},{0,1,1,0},{1,0,0,1},{1,0,0,0},{0,0,1,0},{1,1,0,1},{0,0,0,0},{1,0,0,0},{1,0,0,0},{1,0,1,0},{1,0,1,1},{0,0,1,1},{1,1,0,1},{1,1,0,0},{1,0,0,0},{1,1,1,0},{0,0,1,1},{0,0,1,1},{0,1,0,1},{1,1,1,0},{0,0,1,1},{0,0,1,1},{0,1,0,1},{0,1,1,0},{0,0,1,1},{0,0,0,1},{1,1,0,0},{1,0,0,0},{0,1,0,0},{1,1,1,0},{0,1,1,1},{0,0,0,1},{1,1,1,0},{1,0,0,1},{0,0,1,0},{0,1,0,1},{1,1,0,0},{1,0,1,0},{0,1,0,1},{0,1,1,0},{1,0,0,1},{0,1,1,0},{1,0,0,1},{1,0,1,0},{1,1,0,1},{0,0,1,0},{1,1,0,1},{0,0,1,0},{0,1,0,1},{1,1,0,0},{1,0,1,0},{0,0,0,1},{1,1,0,0},{1,1,1,0},{0,0,1,1},{1,1,0,1},{0,0,1,0},{0,1,1,1},{1,0,1,1},{0,1,0,1},{1,0,1,0},{0,1,0,1},{1,1,0,0},{1,1,1,0},{0,0,1,1},{0,0,0,1},{1,1,0,0},{1,1,0,0},{0,1,0,0},{1,1,0,0},{0,1,0,0},{1,1,1,0},{0,1,0,1},{1,1,0,0},{0,0,0,0},{1,0,0,0},{1,1,1,0},{1,0,0,1},{0,1,1,0},{0,1,0,1},{1,1,0,0},{1,0,1,0},{1,1,0,1},{1,0,0,0},{1,1,0,0},{1,0,1,0},{1,0,0,1},{1,1,0,0},{0,1,1,0},{0,0,0,1},{1,0,1,0},{0,1,0,1},{1,1,0,0},{1,1,0,0},{1,0,1,0},{0,0,1,1},{1,0,0,1},{0,1,0,0},{1,1,1,0},{0,0,1,1},{0,1,1,1},{1,1,0,1},{1,1,1,0},{0,1,1,1},{0,1,0,1},{1,1,0,0},{1,1,0,0},{1,1,0,0},{0,1,1,0},{0,1,0,1},{0,1,1,0},{1,1,0,1},{1,1,0,0},{0,1,1,0}};
	
	//robot data
	static int[][] maze = new int[196][4]; //array of walls
	static int[] dist = new int[196]; //array of floodfill values
	static boolean[] visited = new boolean[196]; //true if cell is visited
	static int posX=0, posY=0; //current position of the robot
	static int heading = EAST; //heading of the robot
	static int go = DEST; //Determines if the robot is going to the destination or back to the starting point
	static boolean finished = false; //becomes true when destination is reached
	static int minNeighbour = 999; //value of the minimum neighbour
	
	//stack variables
	static int stackLimit = 200;
	static int stackCount = 0;
	static int[] stack = new int[stackLimit];
	
	// For simulation purpose
	
	static void printMaze()
	{
		for(int i=0; i<14; i++)System.out.print("+---");
		System.out.println("+");
		for (int y=0; y<14; y++) 
		{
			for (int x=0; x<14; x++)
			{
				if(maze[x+14*y][WEST]==1)
					System.out.print("|");
				else
					System.out.print(" ");
				if (x==posX && y==posY)
					switch (heading)
					{
						case(NORTH):System.out.print(" ^ "); break;
						case(SOUTH):System.out.print(" v "); break;
						case(EAST):System.out.print(" > "); break;
						case(WEST):System.out.print(" < "); break;
					}
				else
				{
					if (dist[x+14*y]==99) System.out.print("|||");
					else if (dist[x+14*y]>9) System.out.print(dist[x+14*y]+" ");
					else System.out.print(" "+dist[x+14*y]+" ");
				}
			}
			System.out.println("|");
			for (int x=0; x<14; x++)
			{
				System.out.print("+");
				if(maze[x+14*y][SOUTH]==1)
					System.out.print("---");
				else
					System.out.print("   ");
			}
			System.out.println("+");
		}
	}
	
	static void delay(int time)
	{
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	static int abs(int a, int b)
	{
		return a>b? a-b: b-a;
	}
	
	//Replace with relevant sensor and motor functions
	static void wallCheck()
	{
		maze[posX+14*posY] = data[posX+14*posY];
		if (posX!=0) maze[posX-1+14*posY][EAST]=maze[posX+14*posY][WEST];
		if (posX!=13) maze[posX+1+14*posY][WEST]=maze[posX+14*posY][EAST];
		if (posY!=0) maze[posX+14*(posY-1)][SOUTH]=maze[posX+14*posY][NORTH];
		if (posY!=13) maze[posX+14*(posY+1)][NORTH]=maze[posX+14*posY][SOUTH];
	}
	
	static void move()
	{
		switch(heading)
		{
		case(NORTH): posY--; break;
		case(SOUTH): posY++; break;
		case(EAST): posX++; break;
		case(WEST): posX--; break;
		}
		visited[posX+14*posY]=true;
	}
	
	static void turnLeft()
	{
		heading = left();
	}
	
	static void turnRight()
	{
		heading = right();
	}
	
	
	// Must be implemented
	static void init()
	{
		posX = startX;
		posY = startY;
//		for (int i=0; i<=6; i++) for(int j=0; j<=6; j++)
//		{
//			dist[i+14*j] = 12-i-j;
//			dist[i+7+14*j] = 6-j+i;
//			dist[i+14*(j+7)] = 6+j-i;
//			dist[i+7+14*(j+7)] = j+i;
//		}
		for(int x=0; x<14; x++) for(int y=0; y<14; y++)
			dist[x+14*y] = abs(x,6) + abs(y,1);
		dist[6+14*1] = 0;
		dist[7+14*1] = 0;
		dist[6+14*2] = 0;
		dist[7+14*2] = 0;
		for(int i=0; i<14; i++)
		{
			maze[i][NORTH]=1;
			maze[i+182][SOUTH]=1;
			maze[14*i][WEST]=1;
			maze[14*i+13][EAST]=1;
		}
		visited[posX+14*posY]=true;
	}
	
	static void lowestFirst()
	{	
		if ((go==DEST)&&((posX==6&&posY==6)||(posX==7&&posY==7||(posX==6&&posY==7)||(posX==7&&posY==6)))) 
		{
			finished = true;
			return;
		}
		
		if ((go==HOME)&&(posX==startX&&posY==startY))
		{
			finished = true;
			return;
		}
		
		wallCheck();
		floodFill();
		
		cellConforms(posX,posY);
		
		if(maze[posX+14*posY][heading]==0 && dist[getCell(heading)]==minNeighbour);
		else if(maze[posX+14*posY][right()]==0 && dist[getCell(right())]==minNeighbour) heading = right();
		else if(maze[posX+14*posY][left()]==0 && dist[getCell(left())]==minNeighbour) heading = left();
		else heading = back();
	}
	
	static void floodFill()
	{
		int n=0;
		push(posX+14*posY);
		
		if(maze[posX+14*posY][NORTH]==1 && posY!=0 && dist[posX+14*(posY-1)]!=0) push(posX+14*(posY-1));
		if(maze[posX+14*posY][SOUTH]==1 && posY!=13 && dist[posX+14*(posY+1)]!=0) push(posX+14*(posY+1));
		if(maze[posX+14*posY][EAST]==1 && posX!=13 && dist[posX+1+14*posY]!=0) push(posX+1+14*posY);
		if(maze[posX+14*posY][WEST]==1 && posX!=0 && dist[posX-1+14*posY]!=0) push(posX-1+14*posY);
		
		while(stackCount!=0)
		{
			n++;
			int check = pop();
			int x = check%14;
			int y = check/14;
			
			if (!cellConforms(x,y))
			{
				if (dist[check]!=0) dist[check] = dist[check]<98?minNeighbour+1:99;
				if (maze[check][NORTH]==0) push(x+14*(y-1));
				if (maze[check][SOUTH]==0) push(x+14*(y+1));
				if (maze[check][EAST]==0) push(x+1+14*y);
				if (maze[check][WEST]==0) push(x-1+14*y);
			}
			if (n>200) break;
		}
	}
	
	static boolean cellConforms(int x, int y)
	{
		minNeighbour = 999;
		
		if(maze[x+14*y][NORTH]==0 && dist[x+14*(y-1)]<minNeighbour) minNeighbour = dist[x+14*(y-1)];
		if(maze[x+14*y][SOUTH]==0 && dist[x+14*(y+1)]<minNeighbour) minNeighbour = dist[x+14*(y+1)];
		if(maze[x+14*y][EAST]==0 && dist[x+1+14*y]<minNeighbour) minNeighbour = dist[x+1+14*y];
		if(maze[x+14*y][WEST]==0 && dist[x-1+14*y]<minNeighbour) minNeighbour = dist[x-1+14*y];
		if((maze[x+14*y][NORTH]+maze[x+14*y][SOUTH]+maze[x+14*y][WEST]+maze[x+14*y][EAST])==4) minNeighbour = 98;
		if(dist[x+14*y]==minNeighbour+1) return true;
		return false;
	}
	
	static void virtualWalls()
	{
		for(int x=0; x<14; x++) for(int y=0; y<14; y++) if(!visited[x+14*y] && dist[x+14*y]!=0)
		{
			maze[x+14*y][NORTH] = 1;
			maze[x+14*y][SOUTH] = 1;
			maze[x+14*y][EAST] = 1;
			maze[x+14*y][WEST] = 1;
			if (x!=0) maze[x-1+14*y][EAST] = 1;
			if (x!=13) maze[x+1+14*y][WEST] = 1;
			if (y!=0) maze[x+14*(y-1)][SOUTH] = 1;
			if (y!=13) maze[x+14*(y+1)][NORTH] = 1;
		}
	}
	
	static int left()
	{
		switch (heading)
		{
		case EAST : return NORTH;
		case SOUTH : return EAST;
		case WEST : return SOUTH;
		case NORTH : return WEST;
		}
		return 0;
	}
	
	static int right()
	{
		switch (heading)
		{
		case EAST : return SOUTH;
		case SOUTH : return WEST;
		case WEST : return NORTH;
		case NORTH : return EAST;
		}
		return 0;
	}
	
	static int back()
	{
		switch (heading)
		{
		case EAST : return WEST;
		case SOUTH : return NORTH;
		case WEST : return EAST;
		case NORTH : return SOUTH;
		}
		return 0;
	}
	
	static int getCell(int direction)
	{
		switch (direction)
		{
			case NORTH : return posX + 14*(posY-1);
			case SOUTH : return posX + 14*(posY+1);
			case EAST : return posX+1+14*posY;
			case WEST : return posX-1+14*posY;
		
		}
		return 0;
	}
	
	//stack implementation
	static void push(int data)
	{
		for(int x = stackLimit-1; x>0; x--)
			stack[x] = stack[x-1];
		stack[0] = data;
		stackCount++;
	}
	
	static int pop()
	{
		stackCount--;
		int val = stack[0];
		for(int x=0; x<stackLimit-1; x++)
			stack[x] = stack[x+1];
		stack[stackLimit-1]=0;
		return val;
	}
	
	public static void main(String[] args) 
	{
		init();
		printMaze();
		
		while(!finished)
		{
			lowestFirst();
			floodFill();
			printMaze();
			if(!finished)move();
			delay(500);
		}
		printMaze();
		
		for(int x=0; x<14; x++) for(int y=0; y<14; y++)
			dist[x+14*y] = abs(x,startX) + abs(y,startY);
		
		int tempX = posX, tempY = posY;
		for(int x=0; x<14; x++) for(int y=0; y<14; y++)
		{
			posX=x;
			posY=y;
			floodFill();
		}
		posX = tempX;
		posY = tempY;
		
		printMaze();
		
		finished = false;
		go = HOME;
		
		while(!finished)
		{
			lowestFirst();
			floodFill();
			printMaze();
			if(!finished) move();
			delay(500);
		}
		
		printMaze();
		
		for (int i=0; i<=6; i++) for(int j=0; j<=6; j++)
		{
			dist[i+14*j] = 12-i-j;
			dist[i+7+14*j] = 6-j+i;
			dist[i+14*(j+7)] = 6+j-i;
			dist[i+7+14*(j+7)] = j+i;
		}
		
		virtualWalls();
		printMaze();
		
		tempX = posX;
		tempY = posY;
		for(int x=0; x<14; x++) for(int y=0; y<14; y++)
		{
			posX=x;
			posY=y;
			floodFill();
		}
		posX = tempX;
		posY = tempY;
		
		heading = back();
		printMaze();
	}

}
