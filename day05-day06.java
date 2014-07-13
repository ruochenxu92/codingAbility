package leetcode;

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

public class leetcodes {
	
	
	/*
	 * S = rabbbit   T = rabbit
	 *  distinct subsequences
	 * 
	 * 
	 */
	

	
	
	
	@Test
	public void testgenerateParenthesis(){
		
		ArrayList<String> list = generateParenthesis(3);
		for(String str : list){
			System.out.println(str);
		}
	}
	
    public ArrayList<String> generateParenthesis(int n){
    	
    	StringBuffer buffer = new StringBuffer();
    	ArrayList<String> result = new ArrayList<String>();
    	
    	dfs(0,0, n,buffer, result);
    	
    	return result;
    }
    
    public void dfs(int left, int right, int n, StringBuffer buffer, ArrayList<String> result){
    	if(left < n){
    		buffer.append("(");
    		dfs(left + 1, right, n , buffer, result);
    		buffer.deleteCharAt(buffer.length()-1);
    	}
    	

    	if(right < left){  //this is requirement condition, very important in this condition
    		buffer.append(")");
    		dfs(left, right+1, n, buffer, result);
    		buffer.deleteCharAt(buffer.length()-1);
    	}
    	
    	if(left== n && right== n){
    		result.add(buffer.toString());
    		return;
    	}
    	
    }
	
	
	
	
	
	
	
	
//	 1->2->3->4
//     2->3->4
//     10->11->12->13->14
	
	 @Test
	 public void testgray(){
		 List<Integer> res = gray(2);
		 for(Integer n : res){
			 System.out.println(n);
		 }
	 }
	 
	 
	
	
	  public List<Integer> gray(int n){
		  List<List<Integer>> binary = graycode(n);
		  List<Integer> res = new ArrayList();
		  
		  
		  for(List<Integer> list : binary){
			  res.add(b2n(list));
		  }
		  
		  return res;
	  }
	  
	  @Test
		 public void testb2n(){
			 List<Integer> arr = new ArrayList();
			 arr.add(1);
			 arr.add(1);
			 arr.add(0);
			 assertEquals(6, b2n(arr));
		 }
	
	  public int b2n(List<Integer> list){
		  int sum = 0;
		  for(int i=0; i < list.size(); i++){
			  int shift = list.size()-1-i;//n-1 n-2 n-3
			  sum += list.get(i) *  (1 << shift);
		  }
		  return sum;
	  }
	  
	  
	  public List<List<Integer>>    graycode(int n){
		  if(n == 1){
			  List<Integer> base0 = new ArrayList();
			  base0.add(0);
			  List<Integer> base1 = new ArrayList();
			  base1.add(1);
			  List<List<Integer>> res = new ArrayList();
			  res.add(base0);
			  res.add(base1);
			  return res;
		  }
		  List<List<Integer>> up = graycode(n-1);
		  List<List<Integer>> down = new ArrayList();
		  
		  for(List<Integer> list : up){
			  List<Integer> tmp = new ArrayList(list);
			  down.add(tmp);
			  list.add(0, 0);
		  }
		  
		  down = reverse(down);
		  
		  for(List<Integer> list : down){
			  list.add(0, 1);
		  }
		  
		  List<List<Integer>> res = new ArrayList();
		  for(List<Integer> list : up){
			  res.add(list);
		  }
		  
		  for(List<Integer> list : down){
			  res.add(list);
		  }
		  return res;
	  }
	  
	  
	  
	  @Test
	  public void testreverse(){
		  List<Integer> a1 = new ArrayList();
		  List<Integer> a2 = new ArrayList();
		  List<Integer> a3 = new ArrayList();
		  List<Integer> a4 = new ArrayList();
		  
		  
		  a1.add(0);
		  a1.add(0);
		  
		  a2.add(0);
		  a2.add(1);
		  
		  a3.add(1);
		  a3.add(1);
		  
		  a4.add(1);
		  a4.add(0);
		  
		  List<List<Integer>> res = new ArrayList();
		  res.add(a1);
		  res.add(a2);
		  res.add(a3);
		  res.add(a4);
		  reverse(res);
	  }
	  
	  
	  List<List<Integer>> reverse(List<List<Integer>> down){
		  LinkedList<List<Integer>> res = new LinkedList();
		  
		  for( List<Integer> list : down){
			  res.push(list);
		  }
		  return res;
	  }
	
	
	
	
	
	
	
	
	   public boolean isInterleave2(String s1, String s2, String s3){
		   //transition condition    s1 = aaabbcc 
		                          // s2 = ababa    s3 = 
		   
		   int n = s1.length();
		   int m = s2.length();
		   boolean[][] dp = new boolean[n][m];
		   
		   for(int i =0; i < n; i++){
			   if(s1.substring(0,i).equals(s3.substring(0,i)))
				   dp[i][0] = true;
		   }
		   
		   for(int i =m; i < m; i++){
			   if(s2.substring(0,i).equals(s2.substring(0,i)))
				   dp[0][i] = true;
		   }
		   
		   for(int i =1; i < n; i ++){
			   for(int j =1; j > m; j++){
				   if(s3.charAt(i + j) == s2.charAt(j)){
					   if(dp[i][j-1]){
						   dp[i][j] = true; 
						   continue;
					   }
				   }
					   
				   if( s3.charAt(i+j)==s1.charAt(i) ){
					   if(dp[i-1][j]){
						   dp[i][j] = true;
						   continue;
					   }
				   }
					   
			   }
		   }
		   return dp[n-1][m-1];
		   
		   
	   } 
		   
		   
		   
		   

	
	   @Test
	   public void testlongest(){
		   int[] A =new int[]{1,2,3,4,10,11,12,13,14,200,300};
		   int length = longestConsecutive(A);
		   System.out.println(length);
	   }
	
	   public int lengthOfLongestSubstring(String s) {
	        //xiaominxuaaawuzhaoyingaaashenyangyiaaa
	        int n = s.length();
	        int buffer = 0;
	        int max= 0;
	        
	        char[] A = s.toCharArray();
	        
	        for(int i = 1; i < n ; i ++){
	            if(buffer > max)
	                max = buffer;
	            if(A[i] == A[i-1]){
	                buffer = 0;
	                continue;
	            }       
	            buffer++;            
	        }
	        return max;
	    }
	
       public int longestConsecutive(int[] A){
    	   int n = A.length;
    	   Map<Integer, Integer> dict = new HashMap();
    	   for(int i=0; i < n; i++){
    		   dict.put(A[i],A[i]);
    	   }
    	   
    	   int max = -1;

    	   Map<Integer, Integer> visited = new HashMap();
    	   
    	   for(int i =0 ; i < n; i++){
    		   if(visited.containsKey(A[i])){
    			   continue;
    		   }
    		   int mid = A[i];
    		   visited.put(A[i], A[i]);
    		   int last = mid;
    		   int start = mid;
    		   for(; dict.get(last+1) != null;last++){
    			   visited.put(last, last);
    		   }
    		   for(;dict.get(start-1) != null;start--){
    			   visited.put(start, start);
    		   }
    		   int len = last + 1 - start;
    		   if(len > max)
    			   max = len;
    	   }
    	   return max;
       }

	
	
		
	
	
	   public String longestPalindrome(String s) {
		   //12321
		   int n = s.length();
		   boolean[][] dp = new boolean[n][n];
		   
		   char[] A = s.toCharArray();
		   for(int i=0; i < n;i++){
			   dp[i][i] = true;
		   }
		   
		   for(int i=0;i+1 < n;i++)
			   dp[i][i+1] = A[i] == A[i+1];
		   
		   for(int i=2; i < n; i++){
			   for(int j=0; j+ i < n;j++){
				   if(dp[j+1][j+i-1] && A[j] == A[j+i]){
					   dp[j][j+i] = true;
				   }
			   }
		   }
		   
		   int max = 0;
		   if(dp[0][n-1])
			   return s.substring(0);
		   
		   String ret = new String();
		   
		   for(int start=0; start < n; start++){
			   for(int last=start; last < n; last++){
				   if(dp[start][last]){
					   if(last - start > max){
						   max = last - start;
						   ret = s.substring(start, last+1);
					   }
				   }
			   }
		   }
		   return ret;
		   
	   }
	
	
/*
 * 	             {'O','X','O','O','O','O','O','O','O'}
				 ,
				 {'O','O','O','X','O','O','O','O','X'}
				 ,
				 {'O','X','O','X','O','O','O','O','X'}
				 ,
				 {'O','O','O','O','X','O','O','O','O'}
				 ,
				 {'X','O','O','O','O','O','O','O','X'}
				 ,
				 {'X','X','O','O','X','O','X','O','X'}
				 ,
				 {'O','O','O','X','O','O','O','O','O'}
				 ,
				 {'O','O','O','X','O','O','O','O','O'}
				 ,
				 {'O','O','O','O','O','X','X','O','O'}
 */
	
	
	
	
	
	
	public int maximalRectangle(char[][] matrix){
		int max = 0;
		int n = matrix.length;
		int m = matrix[0].length;
		
		for(int i=0;i < n; i++){
			for(int j=0; matrix[i][j] !='0' &&j< m;j++){
				int tmp = Maxrectangle(i,j,matrix);
				if(tmp > max)
					max = tmp;
			}
		}	
		return max;
	}

	int Maxrectangle(int i, int j, char[][] matrix){
		int max = Integer.MIN_VALUE;
		int n = matrix.length;
		int m = matrix[0].length;
		int minwidth = Integer.MAX_VALUE;
		for(int row = i; matrix[row][j]=='1' && row < n; row++){
			
			int col =0;
			for( col = j; matrix[i][col]=='1' && col < n;col++);
			int width = col + 1 - j;
			
			if(width < minwidth)
				minwidth = width;
			int area = minwidth * (row + 1 - i);
			if(area > max)
				max = area;
			
		}
		return max;
	}
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testmax(){
		int[] A = new int[]{ -2 ,1,-3,4,-1,2,1,-5,4};
		System.out.println("maxsum = "+maxSubArray(A));
		
	}
	
	public int maxSubArray(int[] A) {
		int n = A.length;
		
		int maxsum = 0;
		int buffer = 0;
		for(int i =0;i < n; i++){
			if(buffer < 0){
				buffer = 0;
			}
				buffer += A[i];
			if(buffer > maxsum)
				maxsum = buffer;
		}
		return maxsum;
	}

	
	@Test
	public void testisPalindrome(){
		
		boolean test = isPalindrome(91219);
		System.out.println(test);
	}
	
	
	public boolean isPalindrome(int x) {
        
        int count = 0;
        int y = x;
        for(;y != 0;){
            y = y /10;
            count ++ ;
        }

        for(int i = 0; i < count/2; i++){
            int last =  x/getnum(i);
            last = last % 10;
            int digit = count - i-1;
            int first = x/getnum(digit);
            first = first % 10;
            if(last != first){
                return false;
            }
        }
        return true;
        
    }
    
    int getnum(int digit){
        int res =1;
        for(int i =0;i < digit;i++){
            res *= 10;
        }
        return res;            
    }
    
    
    
    
	
	/*
	 * Palindrome Partitioning II 
	 */
	@Test
	public void testpartitionII(){
		int a = minCut("ababasss");
		System.out.println("minCut = " + a);
	}
	
	
	
	/*
	 * 
	 */
	
    public int minCut(String s) {
    	//we need boolean dp[][]
    	//we need another dp
    	int n = s.length();
    	boolean[][] dp = new boolean[n][n];
    	
    	for(int i=0; i < n; i++){
    		dp[i][i] = true;
    	}
    	
    	for(int i=0; i+1 < n; i++){
    		dp[i][i+1] = true;
    	}
    	
    	char[] A = s.toCharArray();
    	for(int i=2; i < n; i++){
    		for(int j= 0;j+i < n; j++){
    			if(dp[j+1][j+i-1] && A[i] == A[j] ){
    				dp[j][j+i] = true;
    			}
    		}
    	}
    	
    	int[] dp2 = new int[n];//last
    	dp2[1] = 0;
    	dp2[0] = 0;
    	
    	for(int i=2; i < n ; i++){
    		if(dp[0][i]){
    			dp2[i] = 0;
    			continue;
    		}
        	int min = Integer.MAX_VALUE;
    		for(int j = 0; j < i; j++)//you should be careful about it 
    			if( dp[j+1][i] ){
    				if(dp2[j] + 1 < min )
        				min = dp2[j] + 1; 
    			}
    		dp2[i] = min;
    	}
    	
		return dp2[n-1];
    }
	
	
	
	/*
	 *   babaddd
	 *   dodg
	 *   boolean dp[][] = new boolean[][]   start and end 
	 * 
	 */
	
	@Test
	public void testpartition(){
		partition("aabb");
	}
	
	public ArrayList<ArrayList<String>> partition(String s) {
		int n = s.length();
		char[] A = s.toCharArray();
		boolean[][] dp = new boolean[n][n];//last index
		
		for(int i=0; i < n; i++){
			dp[i][i] = true;
		}
		
		for(int i=0; i+1 < n; i++){
			dp[i][i+1] = A[i] == A[i+1];
		}
		
		for(int i=2; i < n; i++){
			for(int j=0;j+i < n ;j++){
				if(dp[j+1][j+i-1] && A[j]== A[j+i])
					dp[j][j+i] = true;
			}
		}
		
		//System.out.println(dp);
		
		ArrayList<ArrayList<String>> res = new ArrayList();
		ArrayList<String> curr = new ArrayList();
		dfs(0, s, curr, res, dp);
		return res;
	}
	
	
	public void dfs(int start, String s,  ArrayList<String> curr, ArrayList<ArrayList<String>> res, boolean[][] dp ){
		int n = s.length();
		//exit
		if(start >= s.length()){
			ArrayList<String> tmp = new ArrayList();
			for(String entry : curr){
				tmp.add(entry);
			}
			res.add(tmp);
			return;
		}
		
		StringBuffer buffer = new StringBuffer();
		for(int i=start; i < n; i++){
			buffer.append(s.charAt(i));
			if(dp[start][i]){
				curr.add(buffer.toString());
				dfs(i+1, s, curr, res, dp);
				curr.remove(curr.size()-1);
			}
		}
		//game over
	}

	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testgetRow(){
		List<Integer> list = getRow(6);
	}
	
	
	public static List<Integer> getRow(int rowIndex) {
		int[] prev = new int[rowIndex];
		int[] curr = new int[rowIndex];
		
		for(int i = 1; i <= rowIndex;  i++){
			for(int j =0; j < i ; j++){
				if(j == 0 || j == i-1){
					curr[j] = 1;
				}else{
					curr[j] = prev[j-1] + prev[j];
				}
			}
			prev = curr;
			curr = new int[rowIndex];
		}
		List<Integer> list = new ArrayList();
		for(int i=0; i< rowIndex; i++){
			list.add(prev[i]);
		}
		
		
		return list;
		
	  
	}
	
	
	
	
	
	
	
	 @Test
	 public void testparse(){
		 String[] aaa = new String[]{"OXOOOOOOO","OOOXOOOOX","OXOXOOOOX","OOOOXOOOO","XOOOOOOOX","XXOOXOXOX","OOOXOOOOO","OOOXOOOOO","OOOOOXXOO"};
		 for(int i =0; i < aaa.length; i++){
			 System.out.print("{");
			 String tmp = aaa[i];
			 for(int j = 0; j < tmp.length(); j++){
				 if(j != tmp.length()-1)
					 System.out.print(  "'" + tmp.charAt(j) + "',");
				 else
					 System.out.print("'" + tmp.charAt(j) + "'");
			 }
			 System.out.println("}");
			 System.out.println(",");
			 
			 
		 }
	 }
	
	 @Test
	 public void testsolve(){
		 //["OXOOOOOOO","OOOXOOOOX","OXOXOOOOX","OOOOXOOOO","XOOOOOOOX","XXOOXOXOX","OOOXOOOOO","OOOXOOOOO","OOOOOXXOO"]
		 char[][] test = new char[][]{
				 {'O','X','O','O','O','O','O','O','O'}
				 ,
				 {'O','O','O','X','O','O','O','O','X'}
				 ,
				 {'O','X','O','X','O','O','O','O','X'}
				 ,
				 {'O','O','O','O','X','O','O','O','O'}
				 ,
				 {'X','O','O','O','O','O','O','O','X'}
				 ,
				 {'X','X','O','O','X','O','X','O','X'}
				 ,
				 {'O','O','O','X','O','O','O','O','O'}
				 ,
				 {'O','O','O','X','O','O','O','O','O'}
				 ,
				 {'O','O','O','O','O','X','X','O','O'}
		 };
		 solve(test);
		 
		 
		 for(int i =0; i < test.length; i++){
			 for(int j=0; j < test[0].length; j++)
				 System.out.print(test[i][j] + ",");
			 System.out.println();
		 }
			 
		 
		 
		 
		 
	 }
	 
	 public void solve(char[][] board) {
	        int n = board.length;
	        int m = board[0].length;
	        
	        Deque idx = new ArrayDeque();
	        Deque idy = new ArrayDeque();
	        
	        for(int i=0; i < n; i++){
	            for(int j=0; j < m;j++){
	                if(board[i][j] == 'O'){
	                    idx.offer(i);
	                    idy.offer(j);
	                }                
	            }
	        }
	        
	        while(idx.peek() != null){
	            int x = (int) idx.poll();
	            int y = (int) idy.poll();
	            if( bfs(x,y, board) )
	                board[x][y] = 'W';
	            else
	                board[x][y] = 'X';
	        }
	        
	        for(int i =0; i < n; i++){
	        	for(int j=0; j < m; j++){
	        		if(board[i][j] == 'W')
	        			board[i][j] = 'O';
	        		else
	        			board[i][j] = 'X';
	        	}
	        }
	    }
	    
	    boolean bfs(int x, int y, char[][] board){
	        int n = board.length;
	        int m = board[0].length;
	        if(board[x][y] == 'W')   
	            return true;
	        if(x==0 || y == 0 || x == n-1 || y == m-1){
	            if(board[x][y] == 'O')
	                return true;
	            else
	                return false;
	        }

	        boolean res =  bfs(x+1,y, board)
	        || bfs(x-1,y, board)
	        || bfs(x,y+1,board)
	        || bfs(x,y-1,board);
	        return res;
	    }
	    
	
	 public void solve1(char[][] board) {
	        int n = board.length;//rows
	        int m = board[0].length;//cols
	        
	        boolean[][] record = new boolean[n][m];
	        //init
	        //first row
	        for(int i=0; i < m ; i++)
	        	record[0][i] = board[0][i] == 'O'?true:false;
	        
	        //last row
	        for(int i=0; i < m; i++)
	        	record[n-1][i] = board[n-1][i] == 'O' ? true: false;
	        //first col
	        for(int i=0; i < n ; i++)
	        	record[i][0] = board[i][0] == 'O'?true: false;
	        //last col
	        for(int i=0; i < n; i++)
	        	record[i][m-1] = board[i][m-1] == 'O'?true:false;
	            
	        for(int i=0; i < m ; i++)
	            dfs(record, board, 0,i);
	        for(int i=0; i < m ; i++)
	            dfs(record, board,n-1,i);
	        for(int i=0; i < n ; i++)
	            dfs(record, board,i,0);
	        for(int i =0; i < n ; i++)
	            dfs(record,board, i,m-1);
	            
	            
	        for(int i=0; i < n; i++){
	            for(int j=0; j < m; j++){
	                board[i][j] = record[i][j] ? 'O': 'X';
	            }
	        }
	            
	    
	    }
	    
	    void dfs(boolean[][] record, char[][] board, int i, int j){
	    	int n = board.length;
	    	int m = board[0].length;
	        if(i < 0 || j < 0 || i > n-1 || j > m-1)
	            return;
	        
	        if(board[i][j]== 'X'){
	            return;
	        }
	    
	        else if(board[i][j] == 'O'){
	        	record[i][j] = true;
	        	
	        	
	            board[i][j] = 'X';
	            dfs(record, board, i+1,j);
	            dfs(record, board, i-1,j);
	            dfs(record, board, i, j+1);
	            dfs(record, board, i, j-1);
	            board[i][j] = 'O';
	        }
	    }
	
	
	@Test
	public void testfullJustify(){
		//String[] words= new String[]{"aaaa","bb","cc","ddddddd","ee","ffff","ggggggggggg"};
		String[] words = new String[]{"a"};
		//a b
		List<String> res =fullJustify(words, 16);
		for(String word : res){
			System.out.println(word);
		}
	}
	
	 public List<String> fullJustify(String[] words, int L){
		 List<String> res =new ArrayList();
		 int n = words.length;
		 
		 int count = 0;
		 int slow = 0;
		 for(int i =0; i < n ; i++){
			if (count + words[i].length() + i - slow - 1 > L) {
				int wordsnum = i - slow;
				int spacenum = wordsnum - 1;
				int remain = L - count;
				StringBuffer buffer = new StringBuffer();

				if (spacenum > 0) {
					int base = remain / spacenum;
					int mod = remain % spacenum;

					for (int k = slow; k < i; k++) {
						buffer.append(words[k]);
						if (k != i - 1) {
							if (k < slow + mod) {
								buffer.append(getextraspace(base + 1));
							} else {
								buffer.append(getextraspace(base));
							}
						}
					}
				} else {
					buffer.append(words[slow]);
					buffer.append(getextraspace(remain));
				}
				res.add(buffer.toString());
				count = 0;
				slow = i;
				 
			 }
			 count += words[i].length();
		 }
		 
		int wordsnum = n - slow;
		int spacenum = wordsnum - 1;
		int remain = L - count;
		StringBuffer buffer = new StringBuffer();
		
		if(spacenum > 1){
			int base = remain / spacenum;
			int mod  = remain % spacenum;
			for(int k =slow; k < n; k++){
				buffer.append(words[k]);
				if(k != n - 1){
					if(k < slow + mod){
						buffer.append(getextraspace(base + 1));
						
					}else{
						buffer.append(getextraspace(base));
					}
				}
			}
		}else{
			buffer.append(words[slow]);
			buffer.append(getextraspace(remain));
			
		}
		 res.add(buffer.toString());
		 
		 return res;
	 }
	    
	    String getextraspace(int n){
	        StringBuffer ret = new StringBuffer();
	        for(int i = 0; i < n ; i++){
	            ret.append(' ');
	        }
	        return ret.toString();        
	    }
	
 public List<String> fullJustify111(String[] words, int L) {
         
         List<String> result = new ArrayList();
         
         int n = words.length;
         if(n == 0)
            return result;
        
         int buffersum = 0;
         int slow = 0; 
     
         for(int fast =0; fast < n; fast++){
            String tmp = words[fast];
            if(buffersum + tmp.length() <= L){
                buffersum += tmp.length();
                continue;
            }
            if(buffersum + tmp.length() > L){
                int wordsnum = fast - slow;
                int remainingspace = L - buffersum;
                if(wordsnum > 2){
                    int emptynum = wordsnum - 1;
                    int Avg = remainingspace/(wordsnum-1);
                    int last = remainingspace%(wordsnum-1);
                    StringBuffer curr = new StringBuffer();
                    for(int i = slow; i < fast; i++){
                        curr.append(words[i]);
                        if(emptynum > 1)
                            curr.append(getextraspace(Avg));
                        else if(emptynum == 1)
                            curr.append(getextraspace(last));
                        emptynum--;
                    }
                    result.add(curr.toString());
                }
                if(wordsnum == 2){
                    StringBuffer curr = new StringBuffer();
                    curr.append(words[slow]);
                    curr.append(getextraspace(L-words[slow].length()-words[slow+1].length()));
                    result.add(curr.toString());
                }
                if(wordsnum == 1){
                    StringBuffer curr = new StringBuffer();
                    curr.append(words[slow]);
                    String extraspace = getextraspace(L - curr.length());
                    curr.append(extraspace);
                    result.add(curr.toString());
                }
                slow = fast;
            }
         }
         return result;
    }
    
    String getextraspace11(int n){
        StringBuffer res = new StringBuffer();
        for(int i =0; i < n ; i++){
            res.append(' ');
        }
        return res.toString();        
    }
	
	
	
	 @Test
	 public void testmin(){
		 List<Integer> list = new ArrayList();
		 list.add(-10);
		 List<List<Integer>> triangle  = new ArrayList();
		 triangle.add(list);
		 minimumTotal(triangle);
	 }
	
	
	 public int minimumTotal(List<List<Integer>> triangle) {
	        //1.create a dp array       
	        int n = triangle.size();
	        int m = triangle.get(n-1).size();
	        int[][] dp = new int[n][m];
	        
	        //2.init array
	        for(int i =0; i < n; i++){
	           dp[i][0] = ((List<Integer>)triangle.get(i)).get(0);
	           dp[i][i] = ((List<Integer>)triangle.get(i)).get(i);
	        }
	        
	        //3.big loop row
	        for(int i =0; i < n; i++){
	            for(int j=1; j < i; j++){
	                dp[i][j] = Math.max(dp[i-1][j-1],dp[i-1][j]) + ((List<Integer>)triangle.get(i)).get(j);
	            }
	        }
	        
	        int max=0;
	        for(int i=0; i < n; i++){
	            if(dp[n-1][i] > max)
	                max = dp[n-1][i];
	        }
	        return max;
	    }
	
	
	
	
	public boolean isMatch(String s, String p){
        if(s == null || p == null){
            return s == null && p == null;
        }
        
        if(s.length() == 0 || p.length() == 0){
            if(p.length() == 0){
                return s.length()==0;
            }
            if(s.length() == 0){
                for(int i=0; i < p.length() ; i++){
                    if(p.charAt(i) != '*'){
                        return false;       
                    }
                }
            }
        }
        //s and p always are length great than zero
        //2.init
        char[] sA = s.toCharArray();
        char[] pA = p.toCharArray();
        int n = s.length();
        int m = p.length();
        
        boolean dp[][] = new boolean[n][m];
        if(pA[m-1] == '*'){
            for(int i =0; i < n;i++){
                dp[i][m-1] = true;
            }
        }else{
            for(int i = 0; i < n; i++){
                dp[i][m-1] = false;
            }
            if(pA[m-1] == sA[n-1] || pA[m-1] == '?'){
                dp[n-1][m-1] = true;
            }
        }
        
        //3. big loop
        for(int j=m-2; j>=0; j--){
            //4.small loop
        	
        	if(pA[j] == '*'){
        		for(int i=n-1; i >=0;i--){
        			dp[i][j] = true;
        		}
        		continue;
        	}
        	
        	//pA[j] != '*'
        	for(int i= n-1; i>=0; i--){
        		if(i == n-1){
        			if(pA[j+1] == '*' && (sA[i] == pA[j] || pA[j] == '?')){
        				dp[i][j] = true;
        			}
        			continue;
        		}
        		if(dp[i+1][j+1] == true && (sA[i] == pA[j] || pA[j] == '?')){
        			dp[i][j] = true;
        		}
            }
        }
        return dp[0][0];
    }
	
	
	@Test
	public void teststring(){
		String s = "";
		System.out.println(s.length());
	}
	@Test
	public void testisMatch(){
		String s = new String("a");
		String p = new String("a*");
		System.out.println(isMatch(s,p));
	}
	
	
	public boolean isMatch12(String s, String p){
		if(s == null || p == null){
            return s == null && p == null;
        }
        if(s.length() == 0 || p.length() == 0){
            if(p.length() == 0){
                return s.length() ==0;
            }
            if(s.length() == 0){
                for(int i =0;i < p.length();i++){
                    if(p.charAt(i) !='*')
                        return false;
                }
                return true;
            }
        }

        //s and p length > 1
        int n = s.length();
        int m = p.length();
        boolean dp[][] = new boolean[n][m];
        char[] xiaomin = s.toCharArray();
        char[] jay = p.toCharArray();
        if(jay[m-1] == '*'){
            for(int i =0; i < n; i++){
                dp[i][m-1] = true;
            }
        }else{
            for(int i =0; i < n-1; i++){
                dp[i][m-1] = false;
            }
            if(jay[m-1] == xiaomin[n-1] || jay[m-1] == '?')
                dp[n-1][m-1] = true;
            else
                dp[n-1][m-1] = false;
        }
        
        for(int j=m-2;j>=0;j--){
        	int offset = m-1-j;
        	for(int i=n-1; i >= 0; i--){
//                if(j==m-1){
//                    if( jay[j] == '*'){
//                        dp[i][j] = true;
//                    }else{
//                        dp[i][j] = false;
//                    }
//                    continue;
//                }
                if(i == n-1){
                    if( dp[i][j+1] == true ){
                        if(jay[j] == '*')
                            dp[i][j] = true;
                        else
                            dp[i][j] = false;
                    }else{
                        dp[i][j] = false;
                    }
                    continue;
                }
                
                if(dp[i+1][j+1] == false){
                    dp[i][j] = false;
                    continue;
                }
                
                if(dp[i+1][j+1] == true){
                    if(xiaomin[i] == jay[j]){
                        dp[i][j] = true;
                    }else if(jay[j] == '?'){
                        dp[i][j] = true;
                    }else if(jay[j] == '*'){
                        for(;i >= 0;i--){
                        	dp[i][j] = true;
                        }
                    }
                }
                
            }
        }
        return dp[0][0];
    }
	
	
	
	 public boolean isMatch1(String s, String p){
	        if(s == null || p == null)
	            return s == null && p == null;
	        if(s.length() == 0 || p.length()==0){
	            if(p.length() == 0){
	                return s.length() == 0;
	            }
	            if(s.length() == 0){
	                if(p.charAt(0) == '*')
	                    return isMatch(s,p.substring(1));
	                else
	                    return false;
	            }
	        }
	        if(s.length() > 50 && p.length() > 50)
	            return false;
	        //now s, p length > 0;
	        if(p.charAt(0) != '*'){ 
	            if(s.charAt(0) == p.charAt(0) || p.charAt(0) =='?'){
	                return isMatch(s.substring(1), p.substring(1));
	            }else
	                return false;
	        }else{//p.charAt(0) == '*'
	            int i;
	            for(i=0;i<p.length() && p.charAt(i)=='*';i++);//** == '*'
	                if(p.substring(i).equals("") )//i=3
	                    return true;
	            for(int j =0;j < s.length();j++){
	                if(s.charAt(j) == p.charAt(i) || p.charAt(i)=='?'){
	                    if(isMatch(s.substring(j+1), p.substring(i+1)))
	                        return true;
	                }
	            }
	            return false;
	        }
	    }
	
	@Test
	public void testwordbreak(){
		Set<String> dict = new HashSet();
		dict.add("cat");
		dict.add("cats");
		dict.add("and");
		dict.add("sand");
		dict.add("dog");
		String s = new String("catsand");
		wordBreak(s, dict);
		
	}
	
	 public ArrayList<String> wordBreak(String s, Set<String> dict){
	        int n = s.length();
	        boolean[] dp = new boolean[n];
	        for(int i = 0; i < n; i++){
	            String tmp = s.substring(0,i+1);
	            if(dict.contains(tmp)){
	                dp[i] = true;
	                continue;
	            }
	            for(int j=0; j < n; j++){
	                if(dp[j] && dict.contains(s.substring(j+1,i+1))){
	                    dp[i] = true;
	                    break;
	                }
	            }
	        }
	        ArrayList<String> res = new ArrayList();
	        if(dp[n-1]==false)
	            return res;
	        StringBuffer sb =new StringBuffer();
	        dfs(0,s,sb,res,dict);
	        return res;
	    }
	    
	    
	    void dfs(int start, String s, StringBuffer curr,  ArrayList<String> res, Set<String> dict){
	        if(start >= s.length()){
	            //finally enter here
	            res.add(curr.toString());
	            return;
	        }
	        
	       
	        for(int i = start; i < s.length(); i++){
	            String tmp = s.substring(start, i+1);
	            if(dict.contains(tmp)){
	                int old = curr.length();
	                if(start != 0)
	                    curr.append(" ");
	                curr.append(tmp);
	                dfs(i+1,s,curr, res, dict);
	                curr.delete(old, curr.length());
	            }
	        }
	    
	    }
	
	
	


}
