package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class DNAhealth 
{
    static class Vertex
    {
        int index;
        char c;
        boolean isLeaf = true;
        HashMap<Character,Vertex> children = new HashMap<Character,Vertex>();
        List<Integer> wordIDs;
        Vertex parent;
        Vertex failure;
        Vertex leafLink;
        
        Vertex(){}
        
        Vertex(int index)
        {
            this.index = index;
        }
        
        Vertex(int index, char c)
        {
            this.index = index;
            this.c = c;
        }
        
        String toWords()
        {
        	return word(this);
        }
        
        static String word(Vertex v)
        {
        	String res = "";
        	while(v.index!=0)
        	{
        		res = v.c+res;
        		v = v.parent;
        	}
        	return res;
        }
    }
    
    static class AhoCorasick
    {
        List<Vertex> trie = new ArrayList<Vertex>();
        String[] patterns;
        int nodes = 0;
        
        AhoCorasick(String[] patterns)
        {
            trie.add(new Vertex(0));
            nodes++;
            this.patterns = patterns;
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
                char c = s.charAt(x);
                if (trie.get(currNode).children.containsKey(c)) currNode = trie.get(currNode).children.get(c).index;
                else
                {
                    trie.add(new Vertex(nodes,c));
                    trie.get(nodes).parent = trie.get(currNode);
                    trie.get(currNode).children.put(c, trie.get(nodes));
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
        	trie.get(0).failure=trie.get(0);
        	LinkedList<Vertex> vQ = new LinkedList<Vertex>();
        	vQ.addAll(trie.get(0).children.values());
            here : while (vQ.size()>0)
            {
            	Vertex currV = vQ.remove();
            	vQ.addAll(currV.children.values());
            	
                if (currV.parent.index==0) currV.failure = trie.get(0);
                else
                {
                	Vertex chkNode = currV.parent.failure;
                	while (true) 
                	{
                		for(Vertex v : chkNode.children.values()) if (currV.c==v.c) 
                        {
                            currV.failure = v;
                            continue here;
                        }
                		if(chkNode.index==0)
                		{
                			currV.failure = trie.get(0);
                            continue here;
                		}
                		chkNode = chkNode.failure;
                    }
                }
            }
        }
        
        void leafLinks()
        {
            for(Vertex v : trie) if(v.index!=0 && v.parent.index!=0)
                v.leafLink = getLink(v);
        }
        
        Vertex getLink(Vertex v)
        {
            if (v.failure.isLeaf) return v.failure;
            if (v.failure.index==v.index) return null;
            return getLink(v.failure);
        }
        
        int[] getCount(String text)
        {
            int[] count = new int[patterns.length];
            Vertex currNode = trie.get(0);
            here : for(int x=0; x<text.length(); x++)
            {
                while(!currNode.children.containsKey(text.charAt(x)))
                {
                    if(currNode.index==0) continue here;
                    currNode = currNode.failure;
                }
                
                currNode = currNode.children.get(text.charAt(x));
                
                Vertex chkNode = currNode;
                
                if(currNode.isLeaf) 
                {
                    for(Integer wordID : currNode.wordIDs)
                        count[wordID]+=1;
                }
                
                while(true)
                {
                    if(chkNode.leafLink==null) break;
                    chkNode = chkNode.leafLink;
                    for(Integer wordID : chkNode.wordIDs)
                        count[wordID]+=1;
                }
            }
            return count;
        }
        
        void test(Vertex v)
        {
        	System.out.println(v.parent.failure.index);
        	if (v.parent!=null) test(v.parent);
        }
    }
    
    public static int count(String parent, String child)
    {
        int len = parent.length();
        int n = child.length();
        int count = 0;
        here : for(int x=0; x<=len-n; x++)
        {
            for(int y=0; y<n; y++)
                if(child.charAt(y)!=parent.charAt(x+y)) continue here;
            count++;
        }
        return count;
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