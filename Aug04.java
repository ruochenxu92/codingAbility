package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Aug04 {
	/**
	 * leetcode below
	 */
	
	public int maxProfit(int[] A){
        int n = A.length;
        if(n <= 1)
            return 0;
        int valley = -1;
        int peak = -1;
        if(A[0] <= A[1]){
            valley =A[0];
        }
        int maxProfit = 0;
        for(int i= 1;i < A.length-1; i++){
            if(A[i] <= A[i-1] && A[i] <= A[i+1]){
                valley = A[i];
            }
            if(A[i] >= A[i-1] && A[i] >= A[i+1]){
                peak = A[i];
                if(valley != -1){
                    maxProfit += peak - valley;
                    valley = -1;
                }
            }
        }
        if(A[A.length-1] > A[A.length-2]){
            if(valley != -1){
                maxProfit += peak - valley;
            }
        }
        
        return maxProfit;
    }
	
	
	
	 public int[] plusOne(int[] digits){
	        
	        boolean allnine = true;
	        for(int i : digits){
	            if(i != 9){
	                allnine = false;
	                break;
	            }
	        }
	        
	        if(allnine){
	            int[] res = new int[digits.length+1];
	            res[0] = 1;
	            return res;
	        }
	        
	        int[] res = new int[digits.length];
	        
	        int carry = 0;
	        for(int i = digits.length -1 ; i >= 0; i--){
	            int value =digits[i] + 1 + carry;
	            carry = value / 10;
	            value = value % 10;
	            res[i] = value;
	        }
	        return res;
	    }
	 
	 
	 
	 
	 
	 
	 
	  @Test
	  public void testreverseWords(){
		  String tmp = reverseWords(new String("a b c"));
		  
		  System.out.println(tmp);
		  
	  }
	  
	  
	   public String reverseWords(String s){
	        if(s == null || s.length() < 0)
	            return null;
	        
	        String res = "";
	        s = s.trim();
	        int start = 0;
	        for(int i = 0; i <= s.length(); i++){
	            if(i == s.length() || s.charAt(i) == ' '){
	                int end = i;
	                res = s.substring(start, end) + ' ' + res;
	                
	                while(i < s.length() && s.charAt(i) == ' ')
	                	i++;
	                start = i;
	            }
	        }
	        return res.trim();
	    }
	   
	   
	   
	   @Test
	   public void testsearch(){
		   boolean b = search(new int[]{3,1}, 1);
		   System.out.println(b);
	   }
	   
	   public boolean search(int[] A, int target) {
	       int n = A.length;
	       int start = 0;
	       int last = n-1;
	       
	       for(;start  <= last;){
	           int mid = start + (last - start)/2;
	           if(A[mid] == target)
	                return true;
	            if(A[mid] >= A[start]){
	                if( A[start] <= target && target <= A[mid]){
	                    last =mid-1;
	                }else{
	                    start = mid+1;
	                }
	            }else{
	                if(target > A[mid] && target <= A[last]){
	                    start = mid+1;
	                }else{
	                    last = mid-1;
	                }
	            }
	       }
	       return false;
	    }
	   
	   
	   @Test
	   public void testcombination(){
		   int[] num = new int[]{1,1,1,2};
		   System.out.println(CombinationSum(num,4));
	   }
	   
	   public List<List<Integer>> CombinationSum(int[] num, int sum){
		   if(num == null || num.length == 0 || sum <= 0)
			   return new ArrayList();
		   Arrays.sort(num);
		   List<Integer> sol = new ArrayList();
		   List<List<Integer>> res = new ArrayList();
		   dfs(0, num, sum, sol, res);
		   return res;
	   }
	   
	   private void dfs(int start, int[] num, int sum, List<Integer> sol, List<List<Integer>> res){
		   if(sum == 0){
			   List<Integer> copy = new ArrayList(sol);
			   res.add(copy);
			   return;
		   }
		   if(sum < 0){
			   return;
		   }
		   for(int i = start; i < num.length; i++){
			   if(i > 0 && i != start && num[i] == num[i-1]){
				   continue;
			   }
			   sol.add(num[i]);
			   dfs(i+1, num, sum - num[i], sol, res);
			   sol.remove(sol.size() - 1);
		   }
	   }
	   
	   
	   public List<String[]> solveNQueens(int n){
	        if(n <= 0){
	            return new ArrayList();
	        }
	        List<String[]> res = new ArrayList();
	        String[] sol = new String[n];
	        dfs(0, n, sol, res);
	        return res;
	    }
	    
	    private void dfs(int stRow, int n, String[] sol, List<String[]> res){
	        if(n == stRow){
	            res.add(sol.clone());
	            return;
	        }
	        
	        for(int i = 0; i < n; i++){
	            if(isValid(stRow, i, sol)){
	                char[] chars = new char[n];
	                Arrays.fill(chars, '.');
	                chars[i] = 'Q';
	                sol[stRow] = String.copyValueOf(chars);
	                dfs(stRow + 1, n, sol, res);
	            }
	        }
	    }
	    
	    private boolean isValid(int stRow, int col, String[] sol){
	        for(int i = 0; i < stRow; i++){
	            int Qcol = sol[i].indexOf('Q');
	            if(Qcol == col){
	                return false;
	            }
	            int coldiff = Math.abs(Qcol - col);
	            int rowdiff = Math.abs(stRow - i);
	            if(coldiff == rowdiff){
	                return false;
	            }
	        }
	        return true;
	    }
	    
	    
	    public List<List<Integer>> subsets(int[] num){
	        if(num == null && num.length == 0){
	            return new ArrayList();
	        }   
	        Arrays.sort(num);
	        ArrayList<Integer> sol = new ArrayList();
	        List<List<Integer>> res = new ArrayList();
	        dfs(0, num, sol, res);
	        return res;
	    }
	    
	    public void dfs(int pos, int[] num, ArrayList<Integer> sol, List<List<Integer>> res){
	        res.add((List<Integer>) sol.clone());
	        
	        for(int i = pos; i < num.length; i++){
	            sol.add(num[i]);
	            dfs(i + 1, num, sol, res);
	            sol.remove(sol.size() - 1);
	        }
	    }
	    
	    
	    
	    
	    
//	    ["....5..1."
//	    ,".4.3....."
//	    ,".....3..1",
//	     "8......2."
//	    ,"..2.7...."
//	    ,".15......"
//	    ,".....2..."
//	    ,".2.9....."
//	    ,"..4......"]
	    
	    
	

	
	
	@Test
	public void test(){
		String[] str = new String[]{"..9748...","7........",".2.1.9...","..7...24.",".64.1.59.",".98...3..","...8.3.2.","........6","...2759.."};
		
		char[][] board = new char[9][9];
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				board[i][j] = str[i].charAt(j); 
			}
		}
		solveSudoku(board);
	}
	
	
	public void solveSudoku(char[][] board) {
		dfs(0, board);
	}
	
	private void dfs(int count, char[][] board) {
		if (count == 81) {
			return;
		}

		int x = count / 9;
		int y = count % 9;

		if (board[x][y] != '.') {// already have a number
			dfs(count + 1, board);
		} else {
			for (char c = '1'; c <= '9'; c++) {
				board[x][y] = c;
				if (isValid(x, y, board)) {
					dfs(count + 1, board);
				}

			}
		}
	}

	private boolean isValid(int row, int col, char[][] board) {
		boolean[] visited = new boolean[9];
		for (int i = 0; i < 9; i++) {
			if (!process(visited, board[row][i])) {
				return false;
			}
		}
		Arrays.fill(visited, false);
		for (int i = 0; i < 9; i++) {
			if (!process(visited, board[i][col])) {
				return false;
			}
		}
		Arrays.fill(visited, false);
		for (int i = row / 3; i < row / 3 + 3; i++) {
			for (int j = col / 3; j < col / 3 + 3; j++) {
				if (!process(visited, board[i][j])) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean process(boolean[] visited, char digit) {
		if (digit == '.') {
			return true;
		}

		int num = digit - '0';
		if (num < 1 || num > 9 || visited[num - 1]) {
			return false;
		}

		visited[num - 1] = true;
		return true;
	}
	 
	
//	public void solveSudoku(char[][] board) {
//        if(board == null || board.length != 9 || board[0].length != 9){
//            return;
//        }  
//        solve(board, 0);
//    }
//    
//    public boolean solve(char[][] board, int count){
//        if(count == 9 * 9){
//            return true;
//        }
//        int x = count/9;
//        int y = count%9;
//        if(board[x][y] != '.'){
//            return solve(board, count + 1);
//        }else{
//            for(char num = '1'; num <= '9'; num++){
//                board[x][y] = num;
//                if(isValid(x, y, board) && solve(board, num + 1)){
//                    return true;
//                }
//                board[x][y] = '.';
//            }
//            return false;
//        }
//    }
//    
//    public boolean isValid(int row, int col, char[][] board){
//        for(int i = 0; i < 9; i++){
//            if(board[row][i] == board[row][col]){
//                return false;
//            }
//        }
//        for(int i = 0; i < 9; i++){
//            if(board[i][col] == board[row][col]){
//                return false;
//            }
//        }
//        int x = row/3*3;
//        int y = col/3*3;
//        for(int i = x; i < x + 3; i++){
//            for(int j = y; j < y + 3; j++){
//                if(board[i][j] == board[row][col]){
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
	
	
	public List<String> generateParenthesis(int n) {
        if(n <= 0){
            return new ArrayList();
        }
        StringBuffer sol = new StringBuffer();
        List<String> res = new ArrayList();
        dfs(0, 0, n, sol, res);
        return res;
    }
    
    private void dfs(int left, int right, int n, StringBuffer sol, List<String> res){
        if(left < n){
            sol.append('(');
            dfs(left + 1, right, n, sol, res);
            sol.deleteCharAt(sol.length() - 1);
        }
        
        if(right < left){
            sol.append(')');
            dfs(left, right + 1, n, sol, res);
            sol.deleteCharAt(sol.length() - 1);
        }
        
        if(left == n && right == n){
            res.add(sol.toString());
        }
    }
    
    
//    public boolean isValidSudoku(char[][] board) {
//        boolean[] visited = new boolean[9];
//        for(int i = 0; i < 9; i++){
//            Arrays.fill(visited, false);
//            for(int j = 0; j < 9; j++){
//                if(!process(visited, board[i][j])){
//                    return false;
//                }
//            }
//        }        
//        
//        for(int i = 0; i < 9; i++){
//            Arrays.fill(visited, false);
//            for(int j = 0; j < 9; j++){
//                if(!process(visited, board[j][i])){
//                    return false;
//                }
//            }
//        }
//        
//        for(int i = 0; i < 9; i += 3){
//            for(int j = 0; j < 9; j += 3){
//                Arrays.fill(visited, false);
//                for(int k = 0; k < 9; k++){
//                    int x = k / 3;
//                    int y = k % 3;
//                    if(!process(visited, board[x + i][y + j])){
//                        return false;
//                    }
//                }
//            }
//        }
//        return true;
//    }
//    
//    public boolean process(boolean[] visited, char c){
//        int index = c - '1';
//        if(c == '.'){
//            return true;
//        }
//        if(visited[index]){
//            return false;
//        }
//        visited[index] = true;
//        return true;
//    }
}
