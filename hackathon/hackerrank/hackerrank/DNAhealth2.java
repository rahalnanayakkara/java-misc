package hackerrank;

import java.io.*;
import java.util.*;

public class DNAhealth2 
{
	static class Vertex
	{
		int index;
		int let;
		boolean isLeaf=false;
		int[] children = new int[26];
        List<Integer> wordIDs;
        int parent;
        int failure;
        int leafLink;
        
        Vertex()
        {
        	Arrays.fill(children, -1);
        }
        
        Vertex(int index)
        {
        	this();
        	this.index=index;
        }
        
        Vertex(int index, int let)
        {
        	this(index);
        	this.let=let;
        }
	}
	
	static class AhoCorasick
	{
		List<Vertex> trie = new ArrayList<Vertex>();
		String[] patterns;
		int nodes=0;
		
		AhoCorasick(String[] patterns)
		{
			trie.add(new Vertex(0));
			nodes++;
			this.patterns=patterns;
			for(int x=0; x<patterns.length; x++) 
            {
                add(patterns[x],x);
            }
            constructFailures();
            leafLinks();
		}
		
		void add(String s, int wordID)
		{
			int currNode = 0;
			int len = s.length();
			for(int x=0; x<len; x++)
			{
				int c = s.charAt(x)-97;
				if (trie.get(currNode).children[c]!=-1) currNode = trie.get(currNode).children[c];
				else
				{
					trie.add(new Vertex(nodes,c));
					trie.get(nodes).parent = currNode;
					trie.get(currNode).children[c]=nodes;
					currNode = nodes;
					nodes++;
				}
				if(x==len-1) 
                {
                    if(trie.get(currNode).isLeaf)
                        trie.get(currNode).wordIDs.add(wordID);
                    else 
                    {
                        trie.get(currNode).isLeaf=true;
                        trie.get(currNode).wordIDs = new ArrayList<Integer>();
                        trie.get(currNode).wordIDs.add(wordID);
                    }
                }
			}
		}
		
		void constructFailures()
		{
			trie.get(0).failure=0;
			LinkedList<Integer> vQ = new LinkedList<Integer>();
			for(int x=0; x<26; x++) if(trie.get(0).children[x]!=-1) vQ.add(trie.get(0).children[x]);
			here : while (vQ.size()>0)
			{
				int currV = vQ.remove();
				for(int x=0; x<26; x++)
					if(trie.get(currV).children[x]!=-1) vQ.add(trie.get(currV).children[x]);
				
				if(trie.get(currV).parent==0) trie.get(currV).failure = 0;
				else
				{
					int chkNode = trie.get(trie.get(currV).parent).failure;
					while (true)
					{
						for(int i : trie.get(chkNode).children)
							if(i!=-1 && trie.get(i).let==trie.get(currV).let)
							{
								trie.get(currV).failure=i;
								continue here;
							}
						if (chkNode==0)
						{
							trie.get(currV).failure=0;
							continue here;
						}
						chkNode = trie.get(chkNode).failure;
					}
				}
			}
		}
		
		void leafLinks()
		{
			for(Vertex v : trie) 
				if(v.index!=0 && v.parent!=0) v.leafLink = getLink(v);
				else v.leafLink=-1;
		}
		
		int getLink(Vertex v)
		{
			if (trie.get(v.failure).isLeaf) return v.failure;
			if (v.failure==v.index) return -1;
			return getLink(trie.get(v.failure));
		}
		
		int[] getCount(String text)
		{
			int[] count = new int[patterns.length];
			Vertex currNode = trie.get(0);
			here : for(int x=0; x<text.length(); x++)
			{
				int c = text.charAt(x)-97; 
				while(currNode.children[c]==-1)
				{
					if(currNode.index==0) continue here;
					currNode = trie.get(currNode.failure);
				}
				
				currNode = trie.get(currNode.children[c]);
				
				Vertex chkNode = currNode;
				
				if(currNode.isLeaf) 
                {
                    for(Integer wordID : currNode.wordIDs)
                        count[wordID]+=1;
                }
                
                while(true)
                {
                    if(chkNode.leafLink==-1) break;
                    chkNode = trie.get(chkNode.leafLink);
                    if (chkNode.wordIDs==null) System.out.println(chkNode.index);
                    for(Integer wordID : chkNode.wordIDs)
                        count[wordID]+=1;
                }
			}
			return count;
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("/home/rahal/Documents/data.txt")));

        int n = Integer.parseInt(in.readLine());

        String[] genes = in.readLine().split(" ");

        int[] health = new int[n];

        String[] healthItems = in.readLine().split(" ");

        AhoCorasick ac = new AhoCorasick(genes);

        for (int i = 0; i < n; i++)
            health[i] = Integer.parseInt(healthItems[i]);

        int s = Integer.parseInt(in.readLine());
        long min = Long.MAX_VALUE;
        long max = 0;

        for(int x=0; x<s; x++)
        {
            String[] data = in.readLine().split(" ");
            int start = Integer.parseInt(data[0]);
            int end = Integer.parseInt(data[1]);
            int[] count = ac.getCount(data[2]);
            long dnaHealth = 0;
            for(int y=start; y<=end; y++)
            	dnaHealth+=(long)health[y]*(long)count[y];
            max = dnaHealth>max?dnaHealth:max;
            min = dnaHealth<min?dnaHealth:min;
        }
        System.out.println(min+" "+max);
	}
}
