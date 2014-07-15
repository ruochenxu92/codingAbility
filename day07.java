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
	
	
	public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] table = new boolean[n][n];//last index
        
        char[] A = s.toCharArray();
        
        for(int i =0; i < n; i ++){
            table[i][i] = true;
        }
        
        for(int i=0; i+1 < n; i++){
            table[i][i+1] = A[i] == A[i+1];
        }
    
        for(int i=2; i < n ; i++){
            for(int j=0; j+i< n; j++){
                if(table[j+1][j+i-1])
                    table[j][j+i] = A[j] == A[j+i];
            }
        }
        
        List<String> sol = new ArrayList();
        List<List<String>> res = new ArrayList();
        dfs(0, A, sol, res, table);
        
        return res;
    }
    
    
    public void dfs(int slow, char[] A,  List<String> sol, List<List<String>> res, boolean[][] table){
        int n = A.length;
        if(slow >= n){
            res.add(sol);
            return;
        }
        
        StringBuffer buf = new StringBuffer();
        for(int i = slow; i < n; i++){
            buf.append(A[i]);
            if(table[slow][i]){
                sol.add(buf.toString());
                
                dfs(i+1, A, sol,res, table);//go to next, start at slow, after whole return, go to the following, remove last one. and then to continue for(;;) 121
                sol.remove(sol.size()-1);
            }
        }
    }
    
    
	
		@Test
		public void testjump(){
			int[] A = new int[]{1,2,3};
			
			
			
		}
	
	
	    public int jump(int[] A) {
	        int maxJump = 0;
	        int n = A.length;
	        if(n == 0) return 0;
	        int count = 0;
	        
	        for(int i =0; i < n ; i++){
	            if(maxJump >= n-1){
	                return count;
	            }
	            count++;

	            int start = i;
	            int k;
	            for(k = i;k < n && k <= maxJump; k++){
	                if(k + A[k] > maxJump){
	                    maxJump = k + A[k];
	                    start = k;
	                }
	                if(maxJump >= n-1){
	                    return count;
	                }
	            }
	            i = start;
	        }
	       return -1 ;
	    }
	 
	 
	 
	
	
	
	public List<List<Integer>> generate(int numRows) {
        if(numRows == 1){
            List<Integer> list = new ArrayList();
            list.add(1);
            List<List<Integer>> res = new ArrayList();
            res.add(list);
            return res;
        }    
        
        List<List<Integer>> list = generate(numRows);
        List<List<Integer>> res = new ArrayList(list);
        
        List<Integer> prev = list.get(list.size()-1);
        List<Integer> curr = new ArrayList();
        curr.add(1);
        for(int i=1; i < prev.size(); i++){
            curr.add(prev.get(i-1) + prev.get(i));
        }
        curr.add(1);
        res.add(curr);
        
        return res;
    }
    
	
	@Test
	public void testisInterleave(){
		isInterleave("abc","123","a1b2c3");
	}
	
	public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        
        if(s3.length() != m + n)
            return false;
        
        
        boolean[][] table = new boolean[m+1][n+1];
        
        table[0][0] = true;
        for(int i =1; i < m+1; i ++){
        	if(table[i-1][0] )
        		table[i][0] = s1.charAt(i-1) == s3.charAt(i-1);
        	else
        		break;
        }
        
        for(int i =1; i < n+1; i++){
        	if(table[0][i-1]){
        		table[0][i] =  s2.charAt(i-1) == s3.charAt(i-1);
        	}else{
        		break;
        	}
        }
        
        for(int i =1; i < m+1; i++){
            for(int j=1; j < n+1; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1) && s2.charAt(j-1) == s3.charAt(i+j-1)){
                    table[i][j] = table[i-1][j] || table[i][j-1];
                }else if(s1.charAt(i-1) == s3.charAt(i+j-1) ){
                    table[i][j] = table[i-1][j];
                }else if(s2.charAt(j-1) == s3.charAt(i+j-1) ){
                    table[i][j] = table[i][j-1];
                }else{
                    table[i][j] = false;
                }
            }
        }
        
        return table[m][n];
    }
	
	
	
	
	
	
	
	@Test 
	public void test11(){
		System.out.println(54321);
		System.out.println(54321%10000/10);
		System.out.println(432%100/10);
		
	}
	
	
	
	@Test
	public void isPalindrome(){
		boolean s = isPalindrome(10000021);
		System.out.println(s);
	}
	 public boolean isPalindrome(int x) {
	        if(x < 0) return false;
	        int div = 1;
	        while(x/div >= 10)
	            div*=10;
	        
	        while(x > 0){
	            int l = x / div;
	            int r = x % 10;
	            if( l!= r) return false;
	            x = x %  div      /10;
	            div/=100;
	        }
	        return true;
	    }
	 
	 
//	 public boolean isPalindrome(int x) {
//	        if(x < 0) return false;
//	        int div = 1;
//	        while(x / div >= 10){
//	            div*= 10;
//	        }
//	        
//	        while(x > 10){
//	            int l = x % 10;
//	            int r = x / div;
//	            if(l != r)  return false;
//	            
//	            x = x % div/10;
//	            div = div/100;
//	        }
//	        
//	        return true;
//	        
//	        
//	                
//	    }
	
	public boolean isPalindrome1(int x) {

        LinkedList<Integer> list = new LinkedList();
        for(;;){
            // 
        	int buf = x % 10;
        	list.push(buf);
        	if(x != 0){
        		x = x/10;
        	}
        	if(x == 0){
        		break;
        	}
        }

        
        List<Integer> res = new ArrayList();
        
        Iterator it = list.iterator();
        while(it.hasNext()){
            res.add((int)it.next());
        }
        
        
        int last = res.size()-1;
        for(int i =0; i < res.size()/2; i++){
            if(res.get(i) != res.get(last-i)){
                return false;
            }
        }
        return true;      
        
                
    }
	
	
	 public boolean isMatch(String s, String p) {
	        int n = s.length();
	        int m = p.length();
	        char[] A = s.toCharArray();
	        char[] B = p.toCharArray();
	        
	        boolean[][] table = new boolean[n][m];
	        
	       
	        
	        if(B[m-1] == '*'){
	            for(int i =0; i < n; i++){
	                table[i][m-1] = true;
	            }
	        }else if(B[m-1] == '?' || B[m-1] == A[n-1]){
	            table[n-1][m-1]= true;
	        }else{
	            return false;
	        }
	        
	        for(int i =n-2; i >=0; i --){
	            if(table[n-1][i+1]){
	                if(B[i] == '*'){
	                    table[n-1][i] = true;
	                }else if(B[i+1] == '*'){
	                    table[n-1][i] = B[i] == '?' || B[i] == A[n-1];
	                }
	            }else{
	                break;
	            }
	        }
	        
	        for(int i =n-2; i >=0; i--){
	            for(int j =m-2; j>=0; j--){
	                if(table[i+1][j+1]){
	                    table[i][j] = B[j] == '?' || B[j] == A[i];
	                }
	            }
	        }
	        
	        return table[0][0];
	    }
	 
	 
	 
	 
	 
	
	 public List<String> wordBreak(String s, Set<String> dict) {
	        int n = s.length();
	        boolean[] table = new boolean[n];
	        for(int i =0; i < n ; i++){
	            if(dict.contains(s.substring(0,i+1))){
	                table[i] = true;
	                continue;
	            }
	            for(int j = 0; j < i ; j++){
	                if(dict.contains(s.substring(j+1,i+1))){
	                    if(table[j]){
	                        table[i] = true;
	                    }
	                }
	            }
	        }
	        List<String> res = new ArrayList();
	        StringBuffer sol = new StringBuffer();
	        if(!table[n-1]){
	            return res;
	        }
	        char[] A = s.toCharArray();
	        dfs(0, A, dict, sol, res);
	        return res;
	    }
	    
	    void dfs(int slow, char[] A, Set<String> dict, StringBuffer sol, List<String> res){
	        int n = A.length;
	        if(slow >= n){
	            res.add(sol.toString());
	            return ;
	        }
	        
	        StringBuffer buf = new StringBuffer();
	        for(int i = slow; i < n ;i++){
	            buf.append(A[i]);
	            if(dict.contains(buf.toString())){
	                int old = sol.length();
	                sol.append(buf.toString());
	                dfs(i+1, A, dict, sol, res);
	                sol.delete(old,sol.length());
	            }
	                        
	        }
	        
	    }
	    
	    
	    
	    
	    
	    
	    
	
	
	
	
	public int firstMissingPositive(int[] A) {
        
        //2 1 0 
        //0 1 2
        //
        int n = A.length;
        for(int i=0; i < n; i++){
            while(A[i] != i + 1){
                // swap A[i] with A[tmp - 1]
                if(A[i] > n || A[i] <= 0 ) break;
                int tmp = A[i];
                A[i] = A[tmp - 1];
                A[tmp-1] = tmp;
            }   
            if(A[i] >= 1 || A[i] != i+1)
            	return i+1;
        }
        return n+1;
       
    }
	
	
	public int evalRPN(String[] tokens) {
        LinkedList<String> list = new LinkedList();
        int n = tokens.length;
        
        for(int i=0; i < n ; i ++){
            if(tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/")){
                String s1 = list.pop();
                String s2 = list.pop();
                int tmp = 0;
                if(tokens[i].equals("+")){
                    tmp = Integer.parseInt(s2) + Integer.parseInt(s1);
                }else if(tokens[i].equals("-")){
                    tmp = Integer.parseInt(s2) - Integer.parseInt(s1);
                }else if(tokens[i].equals("*")){
                    tmp = Integer.parseInt(s2) * Integer.parseInt(s1);
                }else{
                    tmp = Integer.parseInt(s2) / Integer.parseInt(s1);
                }
                list.push(tmp + "");
            }
        }        
        return Integer.parseInt(list.pop());

    }
	
	
	
	
	
	 public int numDistinct(String S, String T) {
		    //two kinds of questions should be considered about by asking
		    int m = T.length();
		    int n = S.length();

		    if(n ==0 || m==0){
		        return 0;
		    }
		    
		    int[][] dp = new int[m+1][n+1];
		    
		    dp[0][0] = 1;
		    
		    for(int i=1; i < n + 1; i ++){
		        dp[0][i] = 1;
		    }
		    
		    for(int i=1; i <= m; i++){
		        for(int j=i; j <= n; j++){
		           if( T.charAt(i-1) != S.charAt(j-1)){
		               dp[i][j] = dp[i][j-1];
		           }else{
		               dp[i][j] = dp[i][j-1] + dp[i-1][j-1];
		           }
		        }
		    }
		    return dp[m][n];
	   }
	
	
	 public int minDistance(String w1, String w2) {
	        int m = w1.length();
	        int n = w2.length();
	        
	        
	        int[][] dp = new int[m][n];
	        char[] A = w1.toCharArray();
	        char[] B = w2.toCharArray();
	        
	        for(int i =0; i< m; i++){
	            dp[i][0] = i;
	        }
	        
	        for(int i =0; i < n; i++){
	            dp[0][i] = i;
	        }
	        
	        for(int i =1 ; i < m; i++){
	            for(int j=1; j < n; j++){
	                if(A[i] == B[j]){
	                    dp[i][j] = dp[i-1][j-1] + 1;
	                }else{
	                    dp[i][j] = Math.min(dp[i-1][j-1] + 1, Math.min(dp[i-1][j]+1, dp[i][j-1]+1));
	                }
	            }
	        }
	        return dp[m][n];        
	    }
	 
	 
	 
	//[2,3,1,1,4]
	//Your goal is to reach the last index in the minimum number of jumps.
	/*
	 * dp[n]  dp[0] = 0; dp[1] = 1, dp[2] = 1, dp[3]=2  if(maxJump >= n)  dp[i] + 1
	 * 
	 */
	
	/*
	 *  'A' -> 1
		'B' -> 2
		...
		'Z' -> 26

	 * 
	 */
	
	
	
	
	@Test
	public void testfirstmissingposition(){
		int[] A =new int[]{1};
		
		
		int a = firstMissingPositive(A);
		System.out.println(a);
		
	}
	
    public int firstMissingPositive1(int[] A){
    	int n = A.length;
    	//0 1 2 3 4 5 6 7 8 9 10
    	for(int i =0; i< n ;i++){
    		if(A[i] != i+1){
    			if(A[i] >= n) break;
    			int tmp = A[i];
    			A[i] = A[A[i]];
    			A[A[i]] = tmp;  //   A[A[i]] = A[i]   1238567
    		}
    	}
    	
    	for(int i =0; i < n; i++){
    		if(A[i] != i+1)
    			return i+1;
    	}
    	return n+1;
    }
	
	
	
	
	
	public String countAndSay(int n) {
	    //1  11 21 1211 111221 312211 13112221
        if(n <= 0)
            return null;
        return build(n);
  	}
  	
  	String build(int n){
  	    if(n == 1){
  	        return new String("1");
  	    }
  	    
  	    String tmp = build(n-1);
  	    char[] A = tmp.toCharArray();
  	    StringBuffer res =new StringBuffer();
  	    for(int i=0; i < tmp.length(); i ++){
  	        int start = i;
            for(;i+1 < tmp.length() && A[i] == A[i+1];i++){
                
            }
            int end = i+1;
            int count = end - start;
            res.append(count);
            res.append(A[start]);
  	    }
  	    return res.toString();
  	}
	


}
