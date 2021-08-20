package project_euler;

public class test 
{
	
	static String[] weightedUniformStrings(String s, int[] queries) 
    {
        int len = queries.length;
        String[] res = new String[len];
        for(int x=0; x<len; x++) res[x]="No";
        int aVal = Character.getNumericValue('a');
        char[] chars = (s+"1").toCharArray();
        int[] nChars = new int[26];
        char testChar = 'a';
        int count=0;
        for (char c : chars)
        {
            if (c==testChar) count++;
            else
            {
                nChars[Character.getNumericValue(testChar)-aVal] = count;
                testChar = c;
                count=1;
            }
        }
        for (int n : nChars) System.out.println(n);

        for(int x=0; x<len; x++)
        {
            int num = queries[x];
            for(int y=0; y<nChars.length; y++)
            {
                if (num%(y+1)==0&&(nChars[y]>=num/(y+1))) 
                {
                    res[x]="Yes";
                    break;
                }
            }
        }
        return res;
    }
	
	public static void main(String[] args)
	{
		String s = "jkfjakfhddalfjdlafjslannmdknxjsafxnnncddsaa";
		int[] queries = {9,7,8,12,5};
		String[] result = weightedUniformStrings(s, queries);
		//for(String str:result)
		//	System.out.println(str);
	}

}
