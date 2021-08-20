package test;

import io.Keyboard;

public class Maze2 
{
	static final int NORTHP = 1, SOUTHP = 2, EASTP = 4, WESTP = 8;
	static final int NORTH=0, SOUTH=1, EAST=2, WEST=3;
	static final int startX=0, startY=13;
	static final int DEST=0, HOME=1;
	
	//known data
	static int[] data = {147,19,91,53,147,53,149,165,165,163,18,109,146,108,150,220,181,129,54,201,92,131,106,52,202,93,204,166,131,51,94,165,160,104,53,201,53,233,126,178,120,124,162,90,54,149,217,94,220,149,201,53,162,38,161,38,236,204,144,55,177,124,181,146,38,204,145,122,90,52,163,93,204,236,149,163,22,145,106,74,54,206,151,165,204,217,105,23,201,35,92,234,106,54,163,34,55,166};
	
	//robot data
	static int[] maze = new int[98]; //array of walls
	static int[] dist = new int[196]; //array of floodfill values
	static int[] visited = new int[25]; //true if cell is visited
	static int[] queued = new int[25]; //true if cell has been added to queue
	static int posX=0, posY=0; //current position of the robot
	static int heading = EAST; //heading of the robot
	static int go = DEST; //Determines if the robot is going to the destination or back to the starting point
	static boolean finished = false; //becomes true when destination is reached
	static int minNeighbour = 100; //value of the minimum neighbour
	
	//stack variables
	static int stackLimit = 200;
	static int stackCount = 0;
	static int[] stack = new int[stackLimit];
	
	//queue variables
	static int queueLimit = 200;
	static int queueCount = 0;
	static int[] queue = new int[queueLimit];
	
	// For simulation purpose
	
	static void printMaze()
	{
		for(int i=0; i<14; i++)System.out.print("+---");
		System.out.println("+");
		for (int y=0; y<14; y++) 
		{
			for (int x=0; x<14; x++)
			{
				if(readMaze(x+14*y,WEST)==1)
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
				if(readMaze(x+14*y,SOUTH)==1)
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
	
	static int pow(int base, int exp)
	{
		return (int)Math.pow(base, exp);
	}
	
	static int dec(int address, int dir)
	{
		int val = data[address/2];
		int[] walls = new int[4];
		if (address%2==0) val/=16;
		walls[WEST] = (val&8)==8?1:0;
		walls[EAST] = (val&4)==4?1:0;
		walls[SOUTH] = (val&2)==2?1:0;
		walls[NORTH] = (val&1)==1?1:0;
		return walls[dir];
	}
	
	//Replace with relevant sensor and motor functions
	static void wallCheck()
	{
		enc(posX+14*posY,dec(posX+14*posY,WEST)*8+dec(posX+14*posY,EAST)*4+dec(posX+14*posY,SOUTH)*2+dec(posX+14*posY,NORTH));
		if (posX!=0) enc(posX-1+14*posY,dec(posX+14*posY,WEST)*4);
		if (posX!=13) enc(posX+1+14*posY,dec(posX+14*posY,EAST)*8);
		if (posY!=0) enc(posX+14*(posY-1),dec(posX+14*posY,NORTH)*2);
		if (posY!=13) enc(posX+14*(posY+1),dec(posX+14*posY,SOUTH));
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
		visit(posX+14*posY);
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
		for (int i=0; i<=6; i++) for(int j=0; j<=6; j++)
		{
			dist[i+14*j] = 12-i-j;
			dist[i+7+14*j] = 6-j+i;
			dist[i+14*(j+7)] = 6+j-i;
			dist[i+7+14*(j+7)] = j+i;
		}
		for(int i=0; i<14; i++)
		{
			enc(i,1);
			enc(i+182,2);
			enc(14*i,8);
			enc(14*i+13,4);
		}
		visit(posX+14*posY);
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
		floodFill2();
		
		cellConforms(posX,posY);
		
		if(readMaze(posX+14*posY,heading)==0 && dist[getCell(heading)]==minNeighbour);
		else if(readMaze(posX+14*posY,right())==0 && dist[getCell(right())]==minNeighbour) heading = right();
		else if(readMaze(posX+14*posY,left())==0 && dist[getCell(left())]==minNeighbour) heading = left();
		else heading = back();
	}
	
	static void floodFill()
	{
		int n=0;
		push(posX+14*posY);
		
		if(readMaze(posX+14*posY,NORTH)==1 && posY!=0 && dist[posX+14*(posY-1)]!=0) push(posX+14*(posY-1));
		if(readMaze(posX+14*posY,SOUTH)==1 && posY!=13 && dist[posX+14*(posY+1)]!=0) push(posX+14*(posY+1));
		if(readMaze(posX+14*posY,EAST)==1 && posX!=13 && dist[posX+1+14*posY]!=0) push(posX+1+14*posY);
		if(readMaze(posX+14*posY,WEST)==1 && posX!=0 && dist[posX-1+14*posY]!=0) push(posX-1+14*posY);
		
		while(stackCount!=0)
		{
			n++;
			int check = pop();
			int x = check%14;
			int y = check/14;
			
			if (!cellConforms(x,y))
			{
				if (dist[check]!=0) dist[check] = dist[check]<98?minNeighbour+1:99;
				if (readMaze(check,NORTH)==0) push(x+14*(y-1));
				if (readMaze(check,SOUTH)==0) push(x+14*(y+1));
				if (readMaze(check,EAST)==0) push(x+1+14*y);
				if (readMaze(check,WEST)==0) push(x-1+14*y);
			}
			if (n>stackLimit) break;
		}
	}
	
	static void floodFill2()
	{
		queued = new int[25];
		
		for(int x=0; x<196; x++) if(dist[x]==0) 
		{
			add(x);
			queuefy(x);
		}
		
		while(queueCount!=0)
		{
			int check = remove();
			int x = check%14;
			int y = check/14;
			cellConforms(x,y);
			int chkDist = dist[check];
			if (readMaze(check,NORTH)==0 && !hasQueued(x+14*(y-1))) 
			{
				add(x+14*(y-1));
				dist[x+14*(y-1)] = chkDist+1;
				queuefy(x+14*(y-1));
			}
			if (readMaze(check,SOUTH)==0 && !hasQueued(x+14*(y+1))) 
			{
				add(x+14*(y+1));
				dist[x+14*(y+1)] = chkDist+1;
				queuefy(x+14*(y+1));
			}
			if (readMaze(check,EAST)==0 && !hasQueued(x+1+14*y)) 
			{
				add(x+1+14*y);
				dist[x+1+14*y] = chkDist+1;
				queuefy(x+1+14*y);
			}
			if (readMaze(check,WEST)==0 && !hasQueued(x-1+14*y)) 
			{
				add(x-1+14*y);
				dist[x-1+14*y] = chkDist+1;
				queuefy(x-1+14*y);
			}
		}
	}
	
	static boolean cellConforms(int x, int y)
	{
		minNeighbour = 999;
		
		if(readMaze(x+14*y,NORTH)==0 && dist[x+14*(y-1)]<minNeighbour) minNeighbour = dist[x+14*(y-1)];
		if(readMaze(x+14*y,SOUTH)==0 && dist[x+14*(y+1)]<minNeighbour) minNeighbour = dist[x+14*(y+1)];
		if(readMaze(x+14*y,EAST)==0 && dist[x+1+14*y]<minNeighbour) minNeighbour = dist[x+1+14*y];
		if(readMaze(x+14*y,WEST)==0 && dist[x-1+14*y]<minNeighbour) minNeighbour = dist[x-1+14*y];
		if((readMaze(x+14*y,NORTH)+readMaze(x+14*y,SOUTH)+readMaze(x+14*y,WEST)+readMaze(x+14*y,EAST))==4) minNeighbour = 98;
		if(dist[x+14*y]==minNeighbour+1) return true;
		return false;
	}
	
	static void virtualWalls()
	{
		for(int x=0; x<14; x++) for(int y=0; y<14; y++) if(!hasVisited(x+14*y) && dist[x+14*y]!=0)
		{
			enc(x+14*y,15);
			if (x!=0) enc(x-1+14*y,EASTP);
			if (x!=13) enc(x+1+14*y,WESTP);
			if (y!=0) enc(x+14*(y-1),SOUTHP);
			if (y!=13) enc(x+14*(y+1),NORTHP);
		}
	}
	
	static void eliminate()
	{
		for(int x=0; x<14; x++) for(int y=0; y<14; y++) 
			if(readMaze(x+14*y,NORTH)==1 & readMaze(x+14*y,SOUTH)==1 & readMaze(x+14*y,EAST)==1 & readMaze(x+14*y,WEST)==1)
				dist[x+14*y]=99;
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
	
	// ENCODE DECODE
	
	static void enc(int address, int val)
	{
		if (address%2==0) val*=16;
		maze[address/2] = maze[address/2] | val;
	}
	
	static int readMaze(int address, int dir)
	{
		int val = maze[address/2];
		int[] walls = new int[4];
		if (address%2==0) val/=16;
		walls[WEST] = (val&8)==8?1:0;
		walls[EAST] = (val&4)==4?1:0;
		walls[SOUTH] = (val&2)==2?1:0;
		walls[NORTH] = (val&1)==1?1:0;
		return walls[dir];
	}
	
	static void visit(int address)
	{
		int ad = address/8;
		visited[ad] = visited[ad] | pow(2,7-address%8);
	}
	
	static boolean hasVisited(int address)
	{
		return (visited[address/8] & pow(2,7-address%8)) == pow(2,7-address%8);
	}
	
	//set value of queued
	static void queuefy(int address)
	{
		int ad = address/8;
		queued[ad] = queued[ad] | pow(2,7-address%8);
	}
	
	//check if queued
	static boolean hasQueued(int address)
	{
		return (queued[address/8] & pow(2,7-address%8)) == pow(2,7-address%8);
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
	
	//queue implementation
	static void add(int data) 
	{
		queue[queueCount] = data;
		queueCount++;
	}
	
	static int remove() 
	{
		queueCount--;
		int val = queue[0];
		for(int x=0; x<queueLimit-1; x++)
			queue[x] = queue[x+1];
		queue[queueLimit-1] = 0;
		return val;
	}
	
	public static void main(String[] args) 
	{
		init();
		printMaze();
		
		while(!finished)
		{
			lowestFirst();
			printMaze();
			if(!finished)move();
			delay(500);
		}
		printMaze();
		
		for(int x=0; x<14; x++) for(int y=0; y<14; y++)
			dist[x+14*y] = abs(x,startX) + abs(y,startY);
		
		floodFill2();
		
		printMaze();
		
		finished = false;
		go = HOME;
		
		while(!finished)
		{
			lowestFirst();
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
		eliminate();
		printMaze();
		
		for(int x=0; x<14; x++) for(int y=0; y<14; y++) floodFill2();
		
		heading = back();
		printMaze();
		
//		System.out.println(Integer.toBinaryString(visited[0]));
//		for(int x=0; x<8; x++) System.out.print(hasVisited(x)?1:0);
	}

}
