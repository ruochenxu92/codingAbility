package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Aug05 {
	public List<List<Integer>> permute(int[] num){
        if(num == null || num.length == 0){
            return new ArrayList();
        }       
        Arrays.sort(num);
        List<Integer> sol = new ArrayList();
        List<List<Integer>> res = new ArrayList();
        boolean[] visited = new boolean[num.length];
        dfs(num, sol, res, visited);
        return res;
    }
    
    private void dfs(int[] num, List<Integer> sol, List<List<Integer>> res, boolean[] visited){
        if(sol.size() == num.length){
            List<Integer> tmp = new ArrayList(sol);
            res.add(tmp);
            return;
        }
        
        for(int i = 0; i < num.length; i++){
            if(!visited[i]){
                if(i > 0 && num[i] == num[i-1] && !visited[i-1]){
                    continue;                   
                }            
                visited[i] = true;
                sol.add(num[i]);
                dfs(num, sol, res, visited);
                sol.remove(sol.size() - 1);
                visited[i] = false;
            }
        }
    }
    
    
    
    public String getPermutation(int n, int k){
        if(k <= 0 || n <= 0 || k > fac(n)){
            return "";
        }
        k--;
        StringBuffer buf = new StringBuffer();
        List<Integer> dict = new ArrayList();
        for(int i = 0; i < n; i++){
            dict.add(i + 1);
        }
        
        for(int i = n - 1; i >= 0; i--){
            int index = k / fac(i);
            buf.append(dict.get(index));
            dict.remove(index);
            k = k % fac(i);
        }
        return buf.toString();
    }
    
    private int fac(int num){
        int base = 1;
        for(int i = 1; i <= num; i++){
            base *= i;
        }
        return base;
    }
    
    
    
	public void nextPermutation(int[] num) {
		if (num == null || num.length == 0) {
			return;
		}
		int partition = -1;
		for (int i = num.length - 1; i >= 0; i--) {
			if (num[i] < num[i - 1]) {
				partition = i;
				break;
			}
		}
		if (partition == -1) {
			Arrays.sort(num);
			return;
		}
		for (int i = num.length - 1; i > partition; i--) {
			if (num[i] > num[partition]) {
				int tmp = num[i];
				num[i] = num[partition];
				num[partition] = tmp;
				break;
			}
		}
		Arrays.sort(num, partition + 1, num.length);
	}
	
	
	
	public boolean canJump(int[] num) {
		int reach = 0;
		for (int i = 0; i <= reach && i < num.length; i++) {
			int local = num[i] + i;
			reach = Math.max(reach, reach);
		}
		return reach >= num.length - 1;
	}
	
	
	public int climbStairs(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        int[] steps = new int[n];
        steps[0] = 1;
        steps[1] = 1;
        for (int i = 2; i <= n; i++) {
            steps[i] = steps[i - 1] + steps[i - 2];
        }
        return steps[n];
    }
	
	
	public int minPathSum(int[][] grid){
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] table = new int[m][n];
        table[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            table[i][0] = table[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            table[0][i] = table[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) { 
                table[i][j] = Math.min(table[i - 1][j], table[i][j - 1]) + grid[i][j];
            }
        }
        return table[m - 1][n - 1];
    }
	
	public int uniquePathsWithObstacles(int[][] grid) {
	       if (grid == null || grid.length == 0 || grid[0].length == 0) {
	           return 0;
	       }
	       int m = grid.length;
	       int n = grid[0].length;
	       
	       int[][] count = new int[m][n];
	       if(grid[0][0] == 1){
	           return 1;
	       }
	       
	       for (int i = 0; i < m; i++) {
	           if (grid[i][0] == 1) {
	               break;
	           }else{
	               count[i][0] = 1;
	           }
	       }
	       
	       for (int j = 0; j < n; j++){
	           if (grid[0][j] == 1) {
	               break;
	           }else{
	               count[0][j] = 1;
	           }
	       }
	       
	       for (int i = 1; i < m; i++) {
	           for (int j = 1; j < n; j++) {
	               if (grid[i][j] == 1) {
	                   count[i][j] = 0;
	               }else{
	                   count[i][j] = count[i - 1][j] + count[i][j - 1];
	               }
	           }
	       }
	       
	       return count[m - 1][n - 1];
	    }
	
	
	
	public List<List<String>> partition(String src) {
        if (src == null || src.length() == 0) {
            return new ArrayList();
        }	    
        
        int n = src.length();
        boolean[][] table = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            table[i][i] = true;                
        }
        for (int i = 0; i + 1 < n; i++) {
            table[i][i + 1] = src.charAt(i) == src.charAt(i + 1);
        }
        for (int k = 2; k < n; k++) {
            for (int i = 0; i + k < n; i++) {
                if (table[i + 1][i + k - 1] && src.charAt(i) == src.charAt(i + k)) {
                    table[i][i + k] = true;
                }
            }
        }        
        
        List<String> sol = new ArrayList();
        List<List<String>> res = new ArrayList();
        dfs(0, src, sol, res, table);
        return res;
	}
	
	public void dfs(int pos, String src, List<String> sol, 
	                List<List<String>> res, boolean[][] table){
	    if (pos == src.length()) {
	        List<String> tmp = new ArrayList(sol);
	        res.add(tmp);
	        return;
	    }
	    
	    for(int i = pos; i < src.length(); i++){
            if (table[pos][i]){
                sol.add(src.substring(pos, i + 1));
                dfs(i + 1, src, sol, res, table);
                sol.remove(sol.size() - 1);
            }
	    }
	}
	
	public List<Integer> getRow(int nRows) {
        if(nRows < 0){
            return new ArrayList();
        }
        
        List<Integer> start = new ArrayList();
        start.add(1);
        List<Integer> pre = start;
        for(int i = 1; i <= nRows; i++){
            List<Integer> tmp = new ArrayList();
            tmp.add(1);
            for(int k = 1; k < i; k++){
                int sum = pre.get(k - 1) + pre.get(k);
                tmp.add(sum);
            }
            tmp.add(1);
            pre = tmp;
        }
        return pre;
	}
	
	
	public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0){
            return 0;
        }
        int n = triangle.size();
        for(int i = n - 2; i >= 0; i--){
            for(int j = 0; j <= i; j++){
                int min = Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1));
                triangle.get(i).set(j, min + triangle.get(i).get(j));
            }
        }
        return triangle.get(0).get(0);
    }
	
	
	public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0){
            return false;
        }
        int n = s.length();
        boolean[] dp = new boolean[n];
        for(int i = 0; i < n; i++){
            if (dict.contains(s.substring(0, i + 1))) {
                dp[i] = true;
            }else{
                for (int j = 0; j < i; j++){
                    if (dp[j] && dict.contains(s.substring(j + 1, i + 1))) {//divide and conquer, 0-j, j+1-i
                        dp[i] = true;
                        break;
                    } 
                }
            }
        }
        return dp[n - 1];
	}
	
	
	public boolean isMatch(String s, String p) {
		if (s.length() == 0 && p.length() == 0)
			return true;
		if (p.length() == 0)
			return false;

		boolean[][] res = new boolean[s.length()][p.length()];
		res[0][0] = true;
		for (int j = 0; j < p.length(); j++) {
			if (s.charAt(j) == '*') {
				if (j > 0 && res[0][j - 1]) {
					res[0][j + 1] = true;
				}
				if (j == 0) {
					continue;
				}
				if (p.charAt(j - 1) != '.') {
					for (int i = 0; i < s.length(); i++) {
						if (res[i + 1][j] || j > 0 && res[i + 1][j - 1]
								|| i > 0 && j > 0
								&& s.charAt(i - 1) == s.charAt(i)
								&& s.charAt(i) == p.charAt(j - 1)
								&& res[i][j + 1]) {
							res[i + 1][j + 1] = true;
						}
					}
				} else {
					boolean flag = false;
					for (int i = 0; i < s.length(); i++) {
						if (flag) {
							res[i][j + 1] = true;
							continue;
						}
						if (j > 0 && res[i][j - 1]) {
							flag = true;
						}
					}
				}
			} else {
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
						res[i + 1][j + 1] = res[i][j];
					}
				}
			}
		}
		return res[s.length()][p.length()];
	}
	
	
	public boolean isMatch1(String s, String p){
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }
        if (p.length() == 0) {
            return false;
        }
        boolean[][] res = new boolean[s.length() + 1][p.length() + 1];
        for(int j = 0; j < p.length(); j++){
            if (p.charAt(j) == '*') {
                int i = 0; 
                while(i <= s.length() && !res[i][j]){
                    i++;
                }
                for(; i <= s.length(); i++){
                    res[i][j + 1] = true;
                }
            }else{
                for(int i = 0; i < s.length(); i++){
                    if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'){
                        res[i + 1][j + 1] =res[i][j];                        
                    }                    
                }
            }
        }
        return res[s.length()][p.length()];
    }
	
}

