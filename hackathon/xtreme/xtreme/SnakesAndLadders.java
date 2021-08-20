package xtreme;

import java.util.*;

public class SnakesAndLadders 
{
	public static int size, nPlayers, nSnakes, nLadders, nRolls, finishedPlayers=0;
	public static Cell[][] cells;
	public static Snake[] snakes;
	public static Ladder[] ladders;
	public static int[] rolls;
	public static Player[] players;
	
	public static void main(String[] args) 
	{
		loadData();
		
		
		int moveCount=0;
		here : while (moveCount<nRolls)
		{
			for(Player player : players) if (!player.hasFinished)
			{
				player.currCell = snl(advance(player.currCell,rolls[moveCount]));
				if (player.currCell.x==0 && player.currCell.y==4) 
				{
					player.hasFinished = true;
					finishedPlayers++;
				}
				moveCount++;
				if (moveCount>=nRolls||finishedPlayers==nPlayers) break here;
			}
		}
		
		for(Player player : players) if (!player.hasFinished) System.out.println(player.num+" "+player.currCell.x+" "+player.currCell.y);
		else System.out.println(player.num+" winner");
	}
	
	public static void loadData()
	{
		Scanner in = new Scanner(System.in);
		size = in.nextInt();
		
		cells = new Cell[size+1][size+1];
		for(int x=0; x<=size; x++) for(int y=0; y<=size; y++) cells[x][y]=new Cell(x,y);
		
		nPlayers = in.nextInt();
		
		players = new Player[nPlayers];
		for(int count=0; count<nPlayers; count++) players[count] = new Player(count+1,cells[0][4]);
		
		nSnakes = in.nextInt();
		
		snakes = new Snake[nSnakes];
		for(int count=0; count<nSnakes; count++) 
		{
			int beginX = in.nextInt();
			int beginY = in.nextInt();
			int endX = in.nextInt();
			int endY = in.nextInt();
			snakes[count] = new Snake(cells[beginX][beginY],cells[endX][endY]);
			cells[beginX][beginY].hasSnake=true;
			cells[beginX][beginY].snake = snakes[count];
		}
		
		nLadders = in.nextInt();
		
		ladders = new Ladder[nLadders];
		for(int count=0; count<nLadders; count++) 
		{
			int beginX = in.nextInt();
			int beginY = in.nextInt();
			int endX = in.nextInt();
			int endY = in.nextInt();
			ladders[count] = new Ladder(cells[beginX][beginY],cells[endX][endY]);
			cells[beginX][beginY].hasLadder=true;
			cells[beginX][beginY].ladder = ladders[count];
		}
		
		nRolls = in.nextInt();
		
		rolls = new int[nRolls];
		for(int count=0; count<nRolls; count++) rolls[count]=in.nextInt()+in.nextInt();
	}
	
	public static Cell advance(Cell currCell, int moves)
	{
		int x = currCell.x;
		int y = currCell.y;
		while(moves>0)
		{
			if(y%2==1) 
			{
				if(x==size)
				{
					y++;
					moves--;
				}
				else if(x+moves>size)
				{
					moves = moves - size + x;
					x=size;
				}
				else
				{
					x+=moves;
					moves=0;
				}
			}
			else if(y%2==0)
			{
				if(x==1)
				{
					y++;
					moves--;
				}
				else if(x-moves<1)
				{
					moves = moves - x + 1;
					x=1;
				}
				else
				{
					x-=moves;
					moves=0;
				}
			}
			if (x==1&&y==4&&moves>0)
			{
				x=0;
				moves=0;
			}
			
		}
		return cells[x][y];
	}
	
	public static Cell snl(Cell currCell)
	{		
		while(true)
		{
			if (currCell.hasSnake) currCell = currCell.snake.end;
			else if (currCell.hasLadder) currCell = currCell.ladder.end;
			else break;
		}
		
		return currCell;
	}
	
	public static class Cell
	{
		int x,y;
		boolean hasSnake, hasLadder;
		Snake snake;
		Ladder ladder;
		
		public Cell()
		{
			super();
		}
		
		public Cell(int x, int y)
		{
			this.x=x;
			this.y=y;
		}
	}
	
	public static class Ladder
	{
		Cell begin, end;
		
		public Ladder(Cell begin, Cell end)
		{
			this.begin=begin;
			this.end=end;
		}
	}
	
	public static class Snake
	{
		Cell begin, end;
		
		public Snake(Cell begin, Cell end)
		{
			this.begin=begin;
			this.end=end;
		}
	}
	
	public static class Player
	{
		int num;
		Cell currCell;
		boolean hasFinished;
		
		public Player(int num, Cell cell)
		{
			this.num=num;
			currCell = cell;
		}
	}
	
	public static String s = "4\n" + 
			"2\n" + 
			"1\n" + 
			"4 1 2 1\n" + 
			"1\n" + 
			"4 2 2 3\n" + 
			"1\n" + 
			"2 4\n" + 
			"4 1\n" + 
			"3 1\n" + 
			"5 3\n" + 
			"5 6\n" + 
			"5 4\n" + 
			"1 4\n" + 
			"1 4\n" + 
			"1 3\n" + 
			"3 3";

}
