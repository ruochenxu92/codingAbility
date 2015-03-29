package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class day12 {
	
	
	
	
	
	public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        if(m == 0)
            return 0;
        int n = triangle.get(m-1).size();
        int[][] table = new int[m][n];
        
        table[0][0] = triangle.get(0).get(0);
        
        for(int i =1 ; i < m; i++){
            List<Integer> curr = triangle.get(i);
            for(int j = 0; j < curr.size(); j++){
                if( j == 0 ){
                    table[i][0] = table[i-1][0] + curr.get(0);
                }else if( j== curr.size() - 1){
                    table[i][i] = table[i-1][i-1] + curr.get(j);
                }else{
                    table[i][j] = Math.min(table[i-1][j-1], table[i-1][j]) + curr.get(j);
                }
            }
        }
     
        int min = Integer.MAX_VALUE;
        for(int i =0; i < n; i++){
            if(min > table[m-1][i]){
                min = table[m-1][i];
            }
        }
        return min;
        
     
    }
	
	
	
	@Test
	public void testtrap(){
		int[] A = new int[]{1,2,1};
		int res = trap(A);
		System.out.println(res);
	}
	
	public int trap(int[] A) {
        //find the last max index 
		int n = A.length;
		if( n <= 2)
			return 0;
		
		int leftsum = 0;
		int rightsum = 0;
		int border = 0;
		int max = Integer.MIN_VALUE;
		
		for(int i =0; i < n; i++){
			if(A[i] >= max){
				max = A[i];
				border = i;
			}
		}
		
		int slow = 0;
		for(int i = 0; i <= border; i++){
			if(A[i] >= A[slow])
				slow = i;
			leftsum += A[slow] - A[i];
		}
		
		slow = n-1;
		for(int i = n-1; i >= border; i--){
			if(A[i] >= A[slow])
				slow = i;
			rightsum += A[slow] - A[i];
		}
        return leftsum + rightsum;
        
        
        
    }
	
	
	@Test
	public void test(){
		char[][] board = new char[4][4];
		
		for(int i =0; i <  4 ; i++){
			for(int j = 0; j < 4; j++){
				board[i][j] = 'X';
			}
		}
		
		board[1][1] = 'O';
		board[1][0] = 'O';
		board[3][3] = 'O';
		board[2][2] = 'O';
		
		for(int i =0; i <  4 ; i++){
			for(int j = 0; j < 4; j++){
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		
		solve(board);
		System.out.println("=================");
		
		for(int i =0; i <  4 ; i++){
			for(int j = 0; j < 4; j++){
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		
	}
	
	
	 public void solve(char[][] board) {
	       Deque<Integer> idx = new ArrayDeque();
	       Deque<Integer> idy = new ArrayDeque();
	       
	       int m = board.length;
	       if(m == 0)
		        return;
	       int n = board[0].length;
	       
	       for(int i =0; i < m; i++){
	           if(board[i][0] == 'O'){
	               idx.add(i);
	               idy.add(0);
	           }
	           if(board[i][n-1] == 'O'){
	               idx.add(i);
	               idy.add(n-1);
	           }
	       }
	       
	       for(int i = 0; i < n; i++){
	           if(board[0][i] == 'O'){
	               idx.add(0);
	               idy.add(i);
	           }
	           
	           if(board[m-1][i] == 'O'){
	               idx.add(m-1);
	               idy.add(i);
	           }
	       }
	       
	       //BFS
	       while(idx.peek() != null){
	           int x = idx.poll();
	           int y = idy.poll();
	           board[x][y] = 'W';
	           
	           if(x-1 >= 0 && board[x-1][y] == 'O'){
	                idx.add(x-1);
	                idy.add(y);
	           }
	           if(x+1 < m && board[x+1][y] == 'O'){
	               idx.add(x+1);
	               idy.add(y);
	           }
	           if(y-1 >= 0 && board[x][y-1] == 'O'){
	               idx.add(x);
	               idy.add(y-1);
	           }
	           if(y+1 < n && board[x][y+1] == 'O'){
	               idx.add(x);
	               idy.add(y+1);
	           }
	       }
	       
	       for(int i =0; i < m ; i++){
	           for(int j = 0; j < n ;j++){
	               if(board[i][j] == 'W'){
	                   board[i][j] = 'O';
	               }else{
	                   board[i][j] = 'X';
	               }
	           }
	       }
	       
	        
	    }
	
	
	 public List<Integer> getRow(int n) {
	        if(n == 0){
	            List<Integer> res = new ArrayList();
	            res.add(1);
	            return res;            
	        }
	        List<Integer> base = getRow(n-1);
	        List<Integer> res = new ArrayList();
	                
	        res.add(1);
	        for(int i = 1; i < base.size(); i++){
	            int value = base.get(i-1) + base.get(i);
	            res.add(value);
	        }
	        res.add(1);
	        return res;
	    }
	 
	 
	
	public List<List<Integer>> generate(int n) {
        List<List<Integer>> res = new ArrayList();
        if( n == 0 )
            return res;
        if( n == 1 ){
            List<Integer> sol = new ArrayList();
            sol.add(1);
            res.add(sol);
            return res;
        }
        
        
        List<List<Integer>> curr = generate(n-1);
        List<Integer> base = curr.get(curr.size() - 1);
        
        
        List<Integer> newrow = new ArrayList();
        newrow.add(1);
        for(int i = 1; i < base.size(); i++){
            int sum = base.get(i-1) + base.get(i);
            newrow.add(sum);
        }
        newrow.add(1);
        curr.add(newrow);
        return curr;
        
    }
	
	
	
	@Test
	public void testminCut(){
		String s = new String("aab");
		System.out.println(minCut(s));
		
		
	}
	
	public int minCut(String s){
	       char[] A = s.toCharArray();
	       
	       int n = A.length;
	       
	       boolean[][] table = new boolean[n][n];
	       
	       for(int i =0; i < n; i++){
	            table[i][i] = true;
	       }
	       
	       for(int i =0; i+1 < n; i++){
	           table[i][i+1] = A[i] == A[i+1];
	       }
	       
	       for(int k = 2; k < n; k++){
	           for(int j = 0; j+k < n; j++){
	               if(A[j] == A[j+k]){
	                   if(table[j+1][j+k-1]){
	                       table[j][k] = true;
	                   }
	               }
	               
	           }
	       }
	       
	       
	       int start = 0;
	       int[] dp = new int[n];
	       dp[0] = 0;
	       for(int i =0; i < n; i++){
	           if(table[0][i]){
	               dp[i] = 0;
	               continue;
	           }
	           for(int j=1; j <= i; j++){
	             if(table[j][i]){
	                dp[i] = dp[j-1] + 1;  
	                break;
	             }              
	           }
	       }
	       
	       return dp[n-1];
	       
	       
	       
	   }
	
	
	@Test
	public void testpartition(){
		String s = new String("abbab");
		List<List<String>> res = partition(s);
		System.out.println(res);
		
	}
	
	
	public List<List<String>> partition(String s){
        char[] A = s.toCharArray();
        int n = A.length;
        boolean[][] table = new boolean[n][n];
        
        for(int i =0; i < n; i++){
            table[i][i] = true;
        }
        
        for(int i=0; i+1 < n ;i++){
            table[i][i+1] = A[i] == A[i+1];
        }
        
        for(int k = 2; k < n; k++){
            for(int j = 0; j + k < n; j++){
                if(table[j+1][k+j-1]){
                    if(A[j] == A[j+k]){
                        table[j][j+k] = true;//bug here table[j][k] should be table[j][j+k]
                    }
                }
            }
        }
        
        List<String> sol = new ArrayList();
        List<List<String>> res = new ArrayList();
        dfs(0, A, sol, res, table);
        return res;
        
    }
    
    void dfs(int start, char[] A,  List<String> sol, List<List<String>> res, boolean[][] table){
        int n = A.length;
        
        if(start >= n){
            List<String> tmp = new ArrayList(sol);
            res.add(tmp);
            return;
        }
        
        
        StringBuffer buf = new StringBuffer();
        for(int i =start; i < n; i++){
            buf.append(A[i]);
            if(table[start][i] == true){
                sol.add(buf.toString());
                dfs(i+1, A, sol, res, table);
                sol.remove(sol.size()-1);
            }
        }
    }
    
	
	@Test
	public void testArrayList(){
		List<String> A = new ArrayList();
		A.add("aaa");
		A.add("bbb");
		
		
//		List<String> B = new ArrayList(A);
//		List<List<String>> res = new ArrayList();
//		res.add(B);
//		A.remove(0);
//		A.remove(0);
		List<List<String>> res = new ArrayList();
		res.add(A);
		A.remove(0);
		
		System.out.println(res.get(0).size());
	}
	
	public boolean isPalindrome(int x) {
        if( x < 0 ) return false;
        
        int div = 1;
        while( x / div >= 10){
            div *= 10;
        }
        
        
        while(x > 0){
            int first = x % 10;
            int last  = x / div;
            if(first != last) return false;
            x = x % div / 10;
            div /= 100;
        }
        return true;
        
        
    }
	
	
	 public int maxSubArray(int[] A) {
	        int n = A.length;
	        
	        int max = Integer.MIN_VALUE;
	        int sum = 0;
	        for(int i = 0; i < n; i++){
	            sum += A[i];
	            if(sum > max)
	                max = sum;
	            if(sum < 0)
	                sum = 0;
	                
	        }
	        return max;
	        
	        
	              
	    }
	
	
	@Test
	public void testlengthOfLongestSubstring(){
		int length = lengthOfLongestSubstring("abcdababcdefghiabadcknmkabcdefghijklmnopqrstuvwxyz");
		System.out.println("longest length =" + length);
		
	}
	
	 public int lengthOfLongestSubstring(String S) {
	        int n = S.length();
	        HashSet<Character> set = new HashSet();
	        int start = 0;
	        
	        int max = Integer.MIN_VALUE;
	        for(int curr =0; curr < n ; curr++){
	            char c = S.charAt(curr);
	            if(!set.contains(c)){
	                if(curr + 1 - start  > max)
	                    max = curr + 1 - start;
	                set.add(c);
	            }else{
	                for(int i = start; i < curr; i++){
	                    if(S.charAt(i) == c){
	                        start = i + 1;
	                        break;
	                    }else{
	                    	set.remove(S.charAt(i));
	                    }
	                }
	            }
	        }
	          
	        return max;
	       
	    }
	
	@Test
	public void testlongestPalindrome(){
		String s = longestPalindrome("bb");
		System.out.println(s);
		
	}
	
	
	public String longestPalindrome(String s) {
        int n = s.length();
        
        boolean[][] table = new boolean[n][n];//start index and end index
        
        for(int i =0; i < n ;i++){
            table[i][i] = true;
        }
        
        for(int i=0; i+1 < n; i++){
            table[i][i+1] = s.charAt(i) == s.charAt(i+1);
        }
        
        for(int k = 2; k < n; k++){
            for(int i=0; i + k < n; i++){
                if(table[i+1][i+k-1]  ){
                    if(s.charAt(i) == s.charAt(i+k))
                         table[i][i+k] = true;
                }
                   
            }
        }
        
        int max = Integer.MIN_VALUE;
        int start = 0;
        int last = 0;
        
        for(int i = 0;i < n; i++){
            for(int j = i; j <n;j++){
                if(table[i][j]){
                    if(j - i >  max){
                        max = j - i;
                        start = i;
                        last = j;
                    }
                }
            }
        }
        
        return s.substring(start,last + 1);
    }
	
	
	@Test
	public void testjump(){
		int[] A = new int[]{1,1,2,1,1};
		System.out.println(jump(A));
		
		
		
		
	}
	
	//Time: O(n) Space: O(1)
	public int jump(int[] A) {
        int n = A.length;
        if( n== 1)
        	return 0;
        
        int count = 1;
        int maxjump = A[0];
        // 1 1 2 1 1
        for(int i = 1; i < n; ){
            if(maxjump >= n-1)
            	return count;
            count++;
            int currstepmaxjump = maxjump;
            if(i <= n-2 && i <= currstepmaxjump){
            for(; i <= n-2 && i <= currstepmaxjump; i++){
                if(i + A[i] > maxjump)
                    maxjump = i + A[i];
            }
            }else{
            	i++;
            }
        }
        return count;
    }
	
	public boolean canJump(int[] A) {
        int n = A.length;
        
        int maxjump = A[0];
        
        for(int i=1;i < n; i++){
            if(maxjump < i){
                return false;
            }
            
            if(i + A[i] > maxjump)
                maxjump = i + A[i];
            
            if(maxjump >= n-1)
                return true;
        }
        
        return true;           
    }
	
	public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        
        if((m + n) != s3.length())
            return false;
        
        boolean[][] table = new boolean[m+1][n+1];
        
        table[0][0] = true;
        
        for(int i=1; i <= m; i++){
            if(table[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1))
                table[i][0] = true;
            else
                break;
        }
        
        for(int i=1; i <= n; i++){
            if(table[0][i-1] && s2.charAt(i-1) == s3.charAt(i-1))
                table[0][i] = true;
            else
                break;
        }
        
        for(int i=1; i <= m; i++){
            for(int j=1; j <= n; j++){
                
                if(s1.charAt(i-1) == s3.charAt(i+j-1)){
                    table[i][j] = table[i-1][j];                    
                }
                
                if(!table[i][j] && s2.charAt(j-1) == s3.charAt(i+j-1)){
                    table[i][j] = table[i][j-1];
                }
                
                if(s1.charAt(i-1) != s3.charAt(i+j-1) && s2.charAt(j-1) != s3.charAt(i+j-1))
                    table[i][j] = false;
                
            }
        }
        
        return table[m][n];        
        
        
    }
	
	
	@Test
	public void testgrayCode(){
		List<Integer> res = grayCode(2);
		for(Integer i:res){
			System.out.print(i + ",");
		}
		
	}
	
	 public List<Integer> grayCode(int n) {
	        if( n == 0){
	            List<Integer> res = new ArrayList();
	            res.add(0);
	            return res;
	        }
	        
	        List<Integer> curr = grayCode(n-1);
	        
	        List<Integer> reverse = new ArrayList();//make a deep copy
	        
	        for(int i =curr.size()-1; i>=0; i--){
	        	int newvalue = curr.get(i) + ( 1 << (n-1) ) ;
	            reverse.add(newvalue);
	        }
	        List<Integer> res = new ArrayList();
	        res.addAll(curr);
	        res.addAll(reverse);
	        
	        
	        return res;
	    }
	
	 //"((()))", "(()())", "(())()", "()(())", "()()()"
    public List<String> generateParenthesis(int n){
        StringBuffer buf = new StringBuffer();
        List<String> res = new ArrayList();
        dfs(0,0,n,buf,res);
        return res;
        
    }
    
    void dfs(int left, int right, int n, StringBuffer buf, List<String> res){
        if(left < n){
            buf.append("(");
            dfs(left+1, right,n, buf, res);
            //when finish the this, delete (
            buf.deleteCharAt(buf.length() - 1);
        }
        
        if(right < left){
            buf.append(")");
            dfs(left, right+1, n, buf, res);
            buf.deleteCharAt(buf.length() - 1);
        }
        
        if(left == n && right == n){
            res.add(buf.toString());
            return;
        }
    }
    
	
	 public int canCompleteCircuit(int[] gas, int[] cost) {
	        int n = gas.length;
	        
	        int total = 0;
	        int slow = 0;
	        int sum = 0;
	        
	        for(int i = 0; i < n; i++){
	            total += gas[i] - cost[i];
	            sum += gas[i] - cost[i];
	            
	            if(sum < 0){
	                sum = 0;
	                slow = i+1;
	            }
	        }
	        if(total < 0)
	            return -1;
	        return slow;
	        
	    }
	
	 public int minDistance(String w1, String w2) {
	        int m = w1.length();
	        int n = w2.length();
	        
	        int[][] table = new int[m+1][n+1];//based one length
	        
	        table[0][0] = 0;
	        
	        for(int i=1; i <= m; i++){
	            table[i][0] = i;
	        }
	        
	        for(int i=1; i <= n; i++){
	            table[0][i] = i;
	        }
	        
	    
	        for(int i = 1; i <= m; i++){
	            for(int j = 1; j <= n; j++){
	                if(w1.charAt(i-1) == w2.charAt(j-1)){
	                    table[i][j] = table[i-1][j-1];
	                }else{
	                    table[i][j] = Math.min(table[i-1][j-1]+1, Math.min(table[i][j-1], table[i-1][j]) + 1);                    
	                }
	            }
	        }
	        
	        return table[m][n];
	           
	    }
	
	
	public int numDistinct(String S, String T) {
        int m = T.length();
        int n = S.length();
        
        if(m == 0 || n == 0){
            if(n == 0)
                return 0;
            else
                return 1;
        }
        
        if(m > n)
            return 0;
        
        int[][] dp = new int[m+1][n+1];
        
        dp[0][0] = 1;
        
        for(int i=0;  i<= n; i++){
            dp[0][i] = 1;
        }
        
        for(int i=1; i <= n; i++){
            char c = S.charAt(i-1);
            for(int j=1; j<=m &&  j <= i; j++){
                if(c == T.charAt(j-1)){
                    dp[j][i] = dp[j][i-1] + dp[j-1][i-1];
                }else{
                    dp[j][i] = dp[j][i-1];
                }
            }
        }           
        
        return dp[m][n];
        
        
        
    }
	
	
	 public int numDecodings(String s) {
	        int n = s.length();
	        if( n== 0)
	            return 0;
	        int[] dp = new int[n+1];
	        
	        dp[n] = 1;
	        if(s.charAt(n-1) <='9' && s.charAt(n-1) >='1')
	            dp[n-1] = 1;
	            
	        for(int i = n-2; i >= 0; i--){
	            if(s.charAt(i) <= '9' && s.charAt(i) >= '1'){
	            if(check(s.charAt(i), s.charAt(i+1)))
	                dp[i] = dp[i+1] + dp[i+2];
	            else 
	                dp[i] = dp[i+1];
	            }else{
	                dp[i] = 0;
	            }
	        }
	        return dp[0];
	    }
	    
	    public boolean check(char c1, char c2){
	        if(c1 == '1' || c1=='2' && c2 <= '6')
	            return true;
	        else
	            return false;
	    }
	@Test
	public void testcountAndSay(){
		String tmp = countAndSay(6);
		System.out.println(tmp);
	}
	
	
	
	public String countAndSay(int n) {
        if( n == 0)
            return "";
        if( n == 1)
            return "1";
        if( n == 2)
            return "11";
        
        String str = countAndSay(n-1);
        char[] s = str.toCharArray();
        StringBuffer buf = new StringBuffer();
        
        for(int i = 0; i + 1 < s.length;){
            if(s[i] == s[i+1]){
                int count = 1;
                for(;i+1 < s.length && s[i] == s[i+1];i++){
                    count++;
                }
                if(i+1 == s.length){
                    buf.append(count);
                    buf.append(s[i]);
                    break;
                }else{
                    buf.append(count);
                    buf.append(s[i]);
                    i++;
                    if(i+1 == s.length){
                    	buf.append(1);
                    	buf.append(s[i]);
                    }
                }
            }else{
                buf.append(1);
                buf.append(s[i]);
                
                i++;
                if(i + 1 == s.length){
                    buf.append(1);
                    buf.append(s[i]);
                }
            }
        }
        System.out.println(buf.toString());
        return buf.toString();
    }
	
	
	
//	public String countAndSay(int n) {
//        if( n == 0)
//            return "";
//        if( n == 1)
//            return "1";
//        if( n == 2)
//            return "11";
//        
//        String str = countAndSay(n-1);
//        char[] s = str.toCharArray();
//        StringBuffer buf = new StringBuffer();
//        
//        for(int i = 1; i < s.length;){
//            int count = 1;
//            if(s[i-1] == s[i]){
//            for(;i < s.length && s[i-1]==s[i];i++){
//                count++;
//            }
//           
//            buf.append(count);
//            buf.append(s[i-1]);
//            
//            if(i == s.length)
//            	break;
//            if(i+1 >= s.length){
//                buf.append(1);
//                buf.append(s[i]);
//                break;
//            }
//            
//            if(s[i+1] == s[i]){
//                i++;
//            }
//            
//            }else{
//                buf.append(1);
//                buf.append(s[i]);
//                i++;
//            }
//        }
//        return buf.toString();
//    }
	
	@Test
	public void testCountandSay(){
		
		for(int i =0; i < 10;i++ ){
			for(;i == 2;){
				System.out.println("," + i);
				i = 4;
				continue;//did not do i++
			}
			System.out.println("," + i);
			
		}
		
		
		
		
		
		
	}
	
	
	 public int climbStairs(int n) {
	        int[] dp = new int[n+1];
	        dp[0] = 1;
	        dp[1] = 1;
	        for(int i =2; i <= n ;i++){
	            dp[i] = dp[i-1] + dp[i-2];
	        }
	        
	        return dp[n];
	    }
	 
	 public int firstMissingPositive(int[] A) {
	        int n = A.length; 
	        if(n == 0)
	            return 1;
	        
	        for(int i =0; i < n ; i ++){
	            while(A[i] != i+1){
	                int index = A[i] - 1;
	                if(index < 0 || A[i] >= n || A[index] == index+1) break;
	            
	            
	                //swap with index and i
	                int tmp = A[index];
	                A[index] = A[i];
	                A[i] = tmp;
	            }
	            
	            
	        }
	        
	        
	        for(int i =0; i < n ;i++){
	            if(A[i] != i+1)
	                return i+1;
	        }
	        return n+1;
	        
	    }
	public int evalRPN(String[] tokens) {
        LinkedList<String> stack = new LinkedList();

        int n = tokens.length;
        
        for(int i =0; i < n; i++){
            if(tokens[i].equals("+")){
                String s1 = stack.pop();
                String s2 = stack.pop();
                int res = Integer.parseInt(s2)/Integer.parseInt(s1);
                stack.push(res + "");
            }else if(tokens[i].equals("-")){
                String s1 = stack.pop();
                String s2 = stack.pop();
                int res = Integer.parseInt(s2)/Integer.parseInt(s1);
                stack.push(res + "");
            }else if(tokens[i].equals("*")){
                String s1 = stack.pop();
                String s2 = stack.pop();
                int res = Integer.parseInt(s2)/Integer.parseInt(s1);
                stack.push(res + "");
            }else if(tokens[i].equals("/")){
                String s1 = stack.pop();
                String s2 = stack.pop();
                int res =Integer.parseInt(s2)/Integer.parseInt(s1);
                stack.push(res + "");
            }else {
                stack.push(tokens[i]);
            }
        }
        return Integer.parseInt(stack.pop());
    }
	
	
	class RandomListNode{
		int label;
		RandomListNode next, random;
		RandomListNode(int label){
			this.label = label;
		}
	}
	
	public RandomListNode copyRandomList(RandomListNode head) {
	     if(head == null)
	        return head;
	     
	     HashMap<RandomListNode, RandomListNode> map = new HashMap();
	     RandomListNode newhead = new RandomListNode(head.label);
	     map.put(head, newhead);
	     
	     RandomListNode curr = head.next;
	     RandomListNode newNode = newhead;
	     for(;curr!=null; ){
	         newNode.next = new RandomListNode(curr.label);
	         map.put(curr, newNode.next);
	         
            curr = curr.next;
            newNode = newNode.next;
	     }
	     
	     curr = head;
	     newNode = newhead;
	     
	     for(;curr!=null;){
	         RandomListNode value = map.get(curr.random);
	         newNode.random = value;
           
           curr = curr.next;
           newNode = newNode.next;
	         
	     }
	     
	     return newhead;
	     
    }
	
	public ListNode addTwoNumbers(ListNode A, ListNode B) {
        ListNode head = null;
        ListNode tail = null;
        ListNode newNode = null;
        int carrier =0;
        for(;A!=null || B!=null;){
            if( A!= null && B != null ){
                int value = A.val + B.val + carrier;
                if(value >= 10){
                    carrier = 1;
                    value = value - 10;
                }else{
                    carrier = 0;
                }
                A = A.next;
                B = B.next;
                newNode = new ListNode(value);
            }else if(A!= null){
                int value = A.val + carrier;
                if(value >= 10){
                    carrier = 1;
                    value = value - 10;
                }else{
                    carrier = 0;
                }
                A = A.next;
                newNode = new ListNode(value);
            }else{
                int value = B.val + carrier;
                if(value >= 10){
                    carrier = 1;
                    value = value - 10;
                }else{
                    carrier = 0;
                }
                B = B.next;
                newNode = new ListNode(value);
            }
            
            if(head == null){
                head = tail = newNode;
            }else{
                tail.next = newNode;
                tail = tail.next;
            }
            
        }
        if(carrier == 1){
            tail.next = new ListNode(1);
        }
        return head;
    }
	
	
	
}
