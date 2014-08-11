package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class day21 {
	
	
	
	public List<Integer> spiralOrder(int[][] matrix){
	      ArrayList<Integer> rst = new ArrayList<Integer>();
	        if(matrix == null || matrix.length == 0) 
	            return rst;
	        
	        int rows = matrix.length;
	        int cols = matrix[0].length;
	        int count = 0;
	        while(count * 2 < rows && count * 2 < cols){
	            for(int i = count; i < cols-count; i++)
	                rst.add(matrix[count][i]);
	            
	            
	            for(int i = count+1; i< rows-count; i++)
	                rst.add(matrix[i][cols-count-1]);
	            
	            if(rows - 2 * count == 1 || cols - 2 * count == 1)  // if only one row /col remains
	                break;
	                
	            for(int i = cols-count-2; i>=count; i--)
	                rst.add(matrix[rows-count-1][i]);
	                
	            for(int i = rows-count-2; i>= count+1; i--)
	                rst.add(matrix[i][count]);
	            
	            count++;
	        }
	        return rst;
	   }
		
	public int[][] generateMatrix(int n) {
        if (n < 0) {
            return null;
        }

        int[][] result = new int[n][n];

        int xStart = 0;
        int yStart = 0;
        int num = 1;

        while (n > 0) {
            if (n == 1) {
                result[yStart][xStart] = num++;
                break;
            }

            for (int i = 0; i < n - 1; i++) {
                result[yStart][xStart + i] = num++;
            }

            for (int i = 0; i < n - 1; i++) {
                result[yStart + i][xStart + n - 1] = num++;
            }

            for (int i = 0; i < n - 1; i++) {
                result[yStart + n - 1][xStart + n - 1 - i] = num++;
            }

            for (int i = 0; i < n - 1; i++) {
                result[yStart + n - 1 - i][xStart] = num++;
            }

            xStart++;
            yStart++;
            n = n - 2;
        }

        return result;
    }
	
	  public void setZeroes(int[][] matrix){
		    int m = matrix.length;
		    int n = matrix[0].length;
		    
		    boolean firstrow = false;
		    boolean firstcol = false;
		    
		    for(int i = 0; i < m; i++){
		        if(matrix[i][0] == 0)
		            firstcol = true;
		    }
		    
		    for(int i = 0; i < n; i ++){
		        if(matrix[0][i] == 0)
		            firstrow = true;
		    }
		    
		    for(int i = 0; i < m ; i++){
		        for(int j = 0; j < n; j++){
	                if(matrix[i][j] == 0){
	                    matrix[i][0] = 0;
	                    matrix[0][j] = 0;
	                }	            
		        }
		    }
		    
		    for(int i = 1; i < m; i++){
		        if(matrix[i][0] == 0){
		        for(int j = 0; j < n; j++){
		            matrix[i][j] = 0;
		            
		        }
		        }
		    }
		    
		    for(int i = 1; i < n; i++){
		        if(matrix[0][i] == 0){
		            for(int k = 0; k < m ; k++){
		                matrix[k][i] = 0;
		            }
		        }
		    }
		    
		    if(firstrow){
		        for(int i = 0; i < n; i++){
		            matrix[0][i] = 0;
		        }
		    }
		    
		    if(firstcol){
		        for(int i = 0; i < m; i++){
		            matrix[i][0] = 0;
		        }
		        
		    }
		    
		} 
	
	public void rotate(int[][] matrix) {
	    
	    if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
	        return;
	    
        int n = matrix.length;
        
        for(int i = 0; i < n/2; i++){
            for(int j = 0; j < (n+1)/2; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = tmp;
            }
        }
    }
	
	public List<List<Integer>> fourSum(int[] A, int target){
        Arrays.sort(A);
        List<List<Integer>> res = new ArrayList();
        
        for(int i = 0; i < A.length; i++){
            if(i > 0 && A[i] == A[i-1])
                continue;
            for(int j = i + 1; j < A.length; j++){
                if(j > i +1 && A[j] == A[j-1])
                    continue;
                int start = j+1;
                int last = A.length - 1;
                
                while(start < last){
                    int sum = A[i] + A[j] + A[start] + A[last];                    
                    if(sum == target){
                        List<Integer> cur = new ArrayList();
                        cur.add(A[i]);
                        cur.add(A[j]);
                        cur.add(A[start]);
                        cur.add(A[last]);
                        res.add(cur);
                        
                        
                        start++;
                        last--;
                        
                        while(start < last && A[start] == A[start-1]) start++;
                        while(start < last && A[last] == A[last+1]) last--;
                        
                    }else if(sum < target){
                        start++;
                    }else{
                        last--;
                    }
                }
            }
        }
        return res;
       
    }
	
	  @Test
	  public void testpermutation(){
		  List<List<Integer>> rs=  permuteUnique(new int[]{1,1,2});
		  System.out.println(rs);
	  }
	
	  public List<List<Integer>> permuteUnique(int[] A){
	        List<List<Integer>> res = new ArrayList();
	        List<Integer> sol = new ArrayList();
	        boolean[] visited = new boolean[A.length];
	        Arrays.sort(A);
	        permuteUniquedfs(A, A.length, visited, sol, res);
	        return res;
	    }
	    
	    void permuteUniquedfs(int[] A, int count, boolean[] visited, List<Integer> sol, List<List<Integer>> res){
	        if(count == 0){
	            List<Integer> tmp = new ArrayList(sol);
	            res.add(tmp);
	            return;
	        }
	        
	        
	        for(int i = 0; i < A.length; i++){
	        	if( i > 0 && A[i] == A[i-1] && !visited[i-1])
                    continue;
	            if(visited[i]){
	            	continue;
	            }
	                
                visited[i] = true;
                sol.add(A[i]);
                permuteUniquedfs(A, count - 1, visited, sol,res);
                sol.remove(sol.size() - 1);
                visited[i] = false;
	                      
	        }
	    }
	
	
	
	 @Test
	 public void testpermute(){
		 int[] A= new int[]{1,2,3};
		 List<List<Integer>> res = permute(A);
		 System.out.println(res);
		 
	 }
	
	
	 public List<List<Integer>> permute(int[] A){
	       List<List<Integer>> res = new ArrayList();
	       List<Integer> sol = new ArrayList();
	       boolean[] visited = new boolean[A.length];
	       dfs(A, A.length, visited, sol, res);
	       return res;
	       
	   }
	   
	   public void dfs(int[] A, int count,boolean[] visited, List<Integer> sol, List<List<Integer>> res){
	        
	        if(count == 0){
	            List<Integer> tmp = new ArrayList(sol);
	            res.add(tmp);
	            return;
	        }
	        
	        

	        for(int i =0; i < A.length; i++){
	            if(!visited[i]){
	                visited[i] = true;
	                sol.add(A[i]);
	                dfs(A, count - 1,visited, sol, res);
	                sol.remove(sol.size() - 1);
	                visited[i] = false;
	            }
	        } 
	   }
	
	
	@Test
	public void testgetPermutation(){
		String tmp = getPermutation(4, 6);
		System.out.println(tmp);
	}
	
	
	public String getPermutation(int n, int k){
        List<Integer> list = new ArrayList();
        for(int i = 1; i <= n; i++){
            list.add(i);
        }
        
        
        k--;//mod
        
        StringBuffer buf = new StringBuffer();
        
        for(int i = n-1; i >= 0; i--){
            int index = k / factorial(i);
            k = k % factorial(i);
            buf.append(list.get(index));
            list.remove(index);
        }
        return buf.toString();
    }
    
    public int factorial(int num){
        int fac = 1;
        for(int i = 1; i <= num; i++){
            fac *= i;
        }
        return fac;
    }
	
	
	
	@Test
	public void nextPermutation(){
		nextPermutation(new int[]{1,2,4,3});
	}
	
	 public void nextPermutation(int[] A) {
         
	        int partition = -1;
	        int n = A.length;
	        if( n <= 1 )
	            return;
	        
	        for(int i = n - 2; i >= 0; i--){
	            if(A[i] < A[i+1]){
	                partition = i;
	                break;
	            }
	        }
	        
	        if(partition == -1){
	            
	        	for(int i = 0; i < n/2; i ++){
	        		int tmp = A[i];
	        		A[i] = A[n-1-i];
	        		A[n-1-i] = tmp;
	        	}
	            return;
	        }
	        
	        int swap = -1;  
	        
	        for(int i = n-1; i > partition; i--){
	            if(A[i] > A[partition]){
	                swap = i;
	                break;
	            }
	        }	
		
		    int tmp = A[partition];
		    A[partition] = A[swap];
		    A[swap] = tmp;
		    
		    Arrays.sort(A, partition+1, A.length);//nlgn
		    
		    //System.out.println(A);
		 }
	
	
	
	
	
	
	
	
	@Test
	public void testsovlveNQueens(){
		List<String[]> res = solveNQueens(3);
		System.out.println(res);
	}
	
	
	public List<String[]> solveNQueens(int n){
        
        List<String[]> res = new ArrayList();
        String[] sol = new String[n];
        
        dfs(0, n, sol, res);
        return res;
    }
    
    void dfs(int row,  int n, String[] sol, List<String[]> res){
        
        if(row >= n){
            res.add(sol.clone());
            return;
        }
        
        
        for(int i = 0; i < n; i++){
            if(check(row, i, sol)){
                char[] chars = new char[n];
                Arrays.fill(chars,'.');
                chars[i] = 'Q';
                sol[row] = new String(chars);
                dfs(row +1, n, sol, res);
            }
        }
        
    }
    
    boolean check(int row, int col, String[] sol){
        
        for(int i = 0; i < row; i++){
            int Qcol = sol[i].indexOf('Q');
            
            if(Qcol == col)
                return false;
            
            int diffcol = Math.abs(Qcol - col);
        
            int diffrow = Math.abs(row - i);
            
            if(diffcol == diffrow)
                return false;
        }
        return true;
    }    
	
	
	
	@Test
	public void testcombine(){
		List<List<Integer>> res = combine(4,2);
		System.out.println(res);
	}
	
	
	public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> sol = new ArrayList();
        
        dfs(1, n, k, sol, res);
        return res;
    }   
    
    void dfs(int start, int n, int k,  List<Integer> sol, List<List<Integer>> res){
        if(k == 0){
            List<Integer> tmp = new ArrayList(sol);
            res.add(tmp);
            return;
        }
        
        
        for(int i = start; i <= n; i++){
            sol.add(i);
            dfs(i + 1, n, k-1, sol, res);
            sol.remove(sol.size() - 1);
        }
    }
    
	
	
	
	@Test
	public void testcombinationSum2(){
		List<List<Integer>> res = combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
		System.out.println(res);
	}
	
	public List<List<Integer>> combinationSum2(int[] A, int target) {
		 List<List<Integer>> res = new ArrayList();
		 List<Integer> sol = new ArrayList();
         Arrays.sort(A);
         
         boolean[] visited = new boolean[A.length];
         dfs(0, 0, visited, target, A, sol, res);
         return res;
   }
   
   
   void dfs(int start, int last, boolean[] visited,int target, int[] A, List<Integer> sol,List<List<Integer>> res){
       if(target == 0){
           List<Integer> tmp = new ArrayList(sol);
           res.add(tmp);
           return;
       }
       
       if(target < 0)// all numbers must be positive
           return;
       
       for(int i = start; i < A.length; i++){
           if(!visited[i]){
               if(A[i] < last)
                   continue;
               if(i > 0 && A[i] == A[i-1] && !visited[i-1])
                   continue;
               visited[i] = true;
               sol.add(A[i]);
               dfs(i+1, A[i], visited, target-A[i], A, sol, res);
               sol.remove(sol.size()-1);
               visited[i] = false;
               
           }
       }
   }
	
	
	@Test
	public void testcombinationSum(){
		List<List<Integer>> res = new ArrayList();
		res = combinationSum(new int[]{2,3,6,7}, 7);
		System.out.println(res);
		
	}
	
	public List<List<Integer>> combinationSum(int[] A, int target) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> sol = new ArrayList();
        Arrays.sort(A);
        dfs(0, A, target, sol, res);
        return res;
                
    }
    
    
    public void dfs(int start, int[] A, int target, List<Integer> sol, List<List<Integer>> res){
        if(target == 0){
            List<Integer> tmp = new ArrayList(sol);
            res.add(tmp);
            return;
        }
        
        if(target < 0)
        	return;
            
        for(int i = start; i < A.length; i++){
            sol.add(A[i]);
            dfs(i, A, target-A[i], sol, res);
            sol.remove(sol.size()-1);
        }
    }
    
    
    
	
	@Test
	public void testisPrime(){
		int a = 378551;
		int b = 63689;
		System.out.println(b + "=" + isPrime(b));
		
		
	}
	
	
	public boolean isPrime(int A){
		
		for(int i = 2; i < A; i++){
			if(A % i == 0){
				return false;
			}
		}
		return true;
		
		
	}
	
	

	 public List<String> anagrams(String[] A){
	        
	        HashMap<String, List<Integer>> map = new  HashMap();
	        
	        for(int i = 0; i < A.length; i++){
	            char[] chars = A[i].toCharArray();
	            Arrays.sort(chars);
	            String tmp = new String(chars);
	            if(map.containsKey(tmp)){
	                map.get(tmp).add(i);
	            }else{
	                List<Integer> list = new ArrayList();
	                list.add(i);
	                map.put(tmp, list);
	            }
	        }
	        
	        
	        Iterator it = map.values().iterator();
	        
	        List<String> res = new ArrayList();
	        while(it.hasNext()){
	        	List<Integer> list = (List<Integer>) it.next();
	        	if(list.size() > 1){
	        		for(Integer i : list){
	        			res.add(A[i]);
		        	}
	        	}
	        }
	        return res;
		}
	 
	 
	
	public boolean exist(char[][] board, String word){
        if(word == null || word.length() == 0)
            return true;
        if(board == null || board.length == 0 || board[0].length == 0)
            return false;
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    boolean visited[][] = new boolean[board.length][board[0].length];
                    if(explore(board, visited, 0, 0, 0, word))
                        return true;
                }
            }
        }
        
        return false;
     }
     
     public boolean explore(char[][] board, boolean visited[][], int index, int x, int y, String word){
        if(index >= word.length())
            return true;
        
        if( x < 0 || y < 0 || x >= board.length || y  >= board[0].length)
            return false;
        
        if( visited[x][y] || board[x][y] != word.charAt(index))
            return false;
        
        visited[x][y] = true;
        boolean result = explore(board, visited, index + 1, x+1, y, word) ||
            explore(board, visited, index+1, x-1, y, word) ||
            explore(board, visited, index+1, x, y+1, word) ||
            explore(board, visited, index+1, x, y-1, word);
        visited[x][y] = false;
        
        return result;
     }
	
	
	
	 public boolean isValid(String s) {
	        LinkedList<Character> stack = new LinkedList();
	        
	        for(int i= 0; i < s.length(); i++){
	            char c = s.charAt(i);
	            if("([{".contains(String.valueOf(c))){
	                stack.push(c);
	            }else{
	                if( stack.isEmpty() || !is_valid(stack.pop(), c)){
	                    return false;
	                }
	            }
	        } 
	        return stack.isEmpty();
	    }
	    
	    
	    public boolean is_valid(char a, char b){
	        return (a == '(' && b == ')') || (a == '[' && b == ']') || ( a == '{' && b == '}');
	    }
	
	public boolean isPalindrome(String s){
        if(s.length() == 0)
            return true;
        int start = 0;
        int end = s.length() - 1;
        
        while(start <= end){
            while(start < s.length() && !isvalid(s.charAt(start)))
                start++;
            if(start == s.length())
                return true;
            while(end >= 0 && !isvalid(s.charAt(end)))
                end--;
            if(end <= 0)
                return true;
            
            if(s.toLowerCase().charAt(start) != s.toLowerCase().charAt(end))
                break;
            else{
                start++;
                end--;
            }
            
        }
        
        return start >= end;
    }
    
    private boolean isvalid(char c){
        return Character.isLetter(c) || Character.isDigit(c);
    } 
    
    
    
    
	
	
	 public int uniquePathsWithObstacles(int[][] A) {
	       if(A == null || A.length == 0 || A[0].length == 0)
	            return 0;
	        
	        int m = A.length;
	        int n = A.length;
	        
	        int[][] dp = new int[m][n];
	        
	        dp[0][0] = A[0][0] == 0 ? 1 : 0;
	        
	        for(int i = 1; i < m; i++){
	            if(A[i][0] == 1){
	                dp[i][0] = 0;
	            }else{
	                dp[i][0] = dp[i-1][0];
	            }
	        }
	        
	        for(int i = 1; i < n; i++){
	            if(A[0][i] == 1){
	               dp[0][i] = 0;
	            }else{
	                dp[0][i] = dp[0][i-1];
	            }
	        }
	        
	        
	        for(int i = 1; i < m; i++){
	            for(int j = 1; j < n; j++){
	                if(A[i][j] == 1){
	                    dp[i][j] = 0;
	                }else{
	                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
	                }
	            }
	        }
	        
	        
	        return dp[m-1][n-1];
	    }
	 
	 
	 
	 
	
	
	 public int uniquePaths(int m, int n){
	       if(m == 0 || n == 0){
	           return 0;
	       }
	       
	       int[][] dp = new int[m][n];
	       
	       
	      for(int i = 0; i < m; i++)
	        dp[i][0] = 1;
	        
	      for(int i = 0; i < n; i++)
	        dp[0][i] = 1;
	        
	      for(int i = 1; i < m; i++){
	          for(int j = 1; j < n; j++){
	              dp[i][j] = dp[i-1][j] + dp[i][j-1];       
	          }
	      }
	      
	      return dp[m-1][n-1]; 
	       
	    }
	
	
	 	public boolean isSymmetric(TreeNode root){
	        if(root == null){
	            return true;
	        }
	        return helper(root.left, root.right);
	    }
	    
	    private boolean helper(TreeNode left, TreeNode right){
	        if(left == null || right == null)
	            return left==null && right == null;
	        
	        if(left.val != right.val)
	            return false;
	            
	        return helper(left.left, right.right) && helper(left.right, right.left);
	    }
	    
	    
	    
	
	
	    public int sumNumbers(TreeNode root) {
	        return dfs(root, 0);
	    }

	    private int dfs(TreeNode root, int prev){
	        if(root == null) {
	            return 0;
	        }

	        int sum = root.val + prev * 10;
	        if(root.left == null && root.right == null) {
	            return sum;
	        }

	        return dfs(root.left, sum) + dfs(root.right, sum);
	    }
	
	
	  public List<List<Integer>> subsetsWithDup(int[] A) {
		    int n = A.length;
	        boolean[] visited = new boolean[n];
	        List<Integer> sol = new ArrayList();
	        List<List<Integer>> res= new ArrayList();
	        Arrays.sort(A);
	        dfs(0, visited, A, sol, res);
	        return res;
		}
		
		public void dfs(int start, boolean[] visited, int[] A, List<Integer> sol, List<List<Integer>> res){
	        List<Integer> tmp = new ArrayList(sol);
		    res.add(tmp);
		    
		    for(int i = start; i < A.length; i++){
		        if(visited[i])
		            continue;
		        if(i > 0 && A[i] == A[i-1] && !visited[i-1])
		            continue;
		        
		        visited[i] = true;
		        sol.add(A[i]);
		        dfs(i+1,  visited, A, sol, res);
		        sol.remove(sol.size() - 1);
		        visited[i] = false;
		    }
		    
		}
	
	
	@Test
	public void testsubsets(){
		int[] s = new int[]{1,2,3};
		ArrayList<ArrayList<Integer>> res = subsets(s);
		System.out.println(res);
	}
	
	
	public ArrayList<ArrayList<Integer>> subsets(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length == 0) {
            return result;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        Arrays.sort(num);  
        subsetsHelper(result, list, num, 0);

        return result;
    }


    private void subsetsHelper(ArrayList<ArrayList<Integer>> result,
        ArrayList<Integer> list, int[] num, int pos) {

        result.add(new ArrayList<Integer>(list));

        for (int i = pos; i < num.length; i++) {

            list.add(num[i]);
            subsetsHelper(result, list, num, i + 1);
            list.remove(list.size() - 1);
        }
    }
     
     
	
	
	public boolean isScramble(String s1, String s2){
        if(s1.length() != s2.length())
            return false;
        return dac(s1, s2);
    }
    
    
    public boolean dac(String s1, String s2){
        if(s1.length() != s2.length())
            return false;
            
            
        //base case
        if(s1.length() == 1){
            return s1.charAt(0) == s2.charAt(0);
        }
        
        //then check charset
        if(!check(s1, s2))
            return false;
        int n = s1.length();
        
        for(int i = 1; i < s1.length(); i++){
            String s11 = s1.substring(0, i);//i-0 = i
            String s12 = s1.substring(i, s1.length());//n - i
            
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i, s2.length());
            
            boolean result = dac(s11, s21) && dac(s12, s22);
            if(!result){
                String s31 = s2.substring(0, n-i); //n-i
                String s32 = s2.substring(n-i, n); //n - n-i = i
                result = dac(s31, s12) && dac(s11, s32);
            }
            
            if(result){
                return result;
            }
            
        }
        return false;
    }
    
    
    boolean check(String s1, String s2){
        int n = s1.length();
        int[] buck1 = new int[26];
        int[] buck2 = new int[26];
        
        for(int i =0; i < n; i++){
            int index = s1.charAt(i) - 'a';
            buck1[index]++;
        }
        
        for(int i = 0; i < n; i++){
            int index = s2.charAt(i) - 'a';
            buck2[index]++;
        }
        
        for(int i = 0; i < 26; i++){
            if(buck1[i] != buck2[i])
                return false;
        }
        return true;
    }
    
	
	
	
	public boolean isSameTree(TreeNode p, TreeNode q){
        if(p == null || q == null)
            return p == null && q == null;
        if(p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
	
	
	@Test
	public void testreverse(){
		int res = reverse(123);
		System.out.println(res);
	}
	
	
	public int reverse1(int x) {
        int rst = 0;
        while(x != 0){
            rst = rst * 10 + x % 10;
            x = x / 10;
        }
        return rst;
    }
	
	public int reverse(int x){
        int sign = 1;
        if(x < 0){
            sign = -1;
            x = x * sign;//now x is non-negative
        }

        //general condition
        int div = 1;
        List<Integer> list = new ArrayList();
        while( x / div >= 10){
            int digit = x / div % 10;
            list.add(digit);
            div *= 10;
        }
        
        list.add(x / div);
        
        
        Iterator it = list.iterator();
        long sum = 0;
        
        while(it.hasNext()){
            sum += (int)it.next() * div;
            div /= 10;
        }
        if(sum > Integer.MAX_VALUE){
        	
        }
        return (int)sum * sign;        
        	    
	}
	
	
	
	
	
	
	
	@Test
	public void testrestoreIPAddress(){
		String s = new String("255255112122");
		List<String> res = restoreIpAddresses(s);
		System.out.println(res);
		
	}
	
	
	
	public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList();
        StringBuffer sol = new StringBuffer();
        
        buildIP(0, 4, s, sol, res);
        return res;
	}
	
	void buildIP(int start, int count, String s, StringBuffer sol, List<String> res){
	    if(count == 0 && start >= s.length() ){
	        res.add(sol.toString());
	        return;
	    }
	    
	    if(count == 0){
	        return;
	    }
	    
	    
	    StringBuffer buf = new StringBuffer();
	    for(int i = start; i < s.length(); i++){
	        buf.append(s.charAt(i));
	        String tmp = buf.toString();
	        if(tmp.charAt(0) == '0' && tmp.length() > 1){
				return;
			}	        
	        
	        int num = Integer.parseInt(tmp);
	        if(num < 256){
	            int bufLen = buf.length();
	            if(i != s.length() - 1)
	                buf.append('.');

	            int oldLen = sol.length();
                sol.append(buf);	            
	            buildIP( i+1, count-1, s, sol, res);
	            sol.delete(oldLen, sol.length());
	            buf.delete(bufLen, buf.length());
	        }else{
	            return;
	        }
	    }
	    
	}
	
	
	public double pow(double x, int n) {
        if(n == 0)
            return 1.0;
        
        int k = n/2;
        double half = pow(x, k);
        if(n % 2 == 0){
            return half * half;
        }
        if(n > 0){
            return half * half * x; 
        }else{
            return half * half * 1.0 / x;
        }
        
      
    }
	
	
	
	 public void connect1(TreeLinkNode root){
	        if(root == null){
	            return;
	        }
	        
	        if(root.left != null){
	            root.left.next = root.right;
	            if(root.next != null){
	                root.right.next = root.next.left;
	            }
	            connect(root.left);
	            connect(root.right);
	        }
	    }
	 
	 public void connect(TreeLinkNode root){
         if(root == null){
             return;                 
         }	
         TreeLinkNode trav = root.next;
         if(root.left != null && root.right != null){
             root.left.next = root.right;
             root.right.next = connect2(trav);
             //I have a bug here,                 
             connect(root.right);
             connect(root.left);
         }else if(root.left != null){
             root.left.next = connect2(trav);
             connect(root.left);
         }else if(root.right != null){
             root.right.next = connect2(trav);              
             connect(root.right);
         }
 	  }
 	  
 	  
 	  public TreeLinkNode connect2(TreeLinkNode trav){
 	      if(trav == null)
 	        return null;
 	      if(trav.left != null){
 	          return trav.left;
 	      }
 	      if(trav.right != null){
 	          return trav.right;
 	      }
 	      return connect2(trav.next);
 	  }
	 
	 
	 
	 
	 
}
