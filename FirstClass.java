package leetcode;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;



/**
 * @author Xiaomin
 */
public class FirstClass {
	
	/**
	 * 1. strStr leetcode implement strStr
	 */
	public String strStr(String haystack, String needle) {
		if (haystack == null || needle == null || needle.length() > haystack.length()) {
			return null;
		}
		for (int i = 0; i + needle.length() <= haystack.length(); i++) {
			String tmp = haystack.substring(i, i + needle.length());
			if (tmp.equals(needle)) {
				return haystack.substring(i);
			}
		}
		return null;
	}
	
	
	
	/**
	 *  2.0
	 *  subsets I template
	 * 
		10 / 10 test cases passed.
		Status: Accepted
		Runtime: 432 ms
		Submitted: 0 minutes ago

	 */
	
	public ArrayList<ArrayList<Integer>> subsets(int[] num) {
		ArrayList res= new ArrayList();
		if (num == null || num.length == 0) {
			return res;
		}
		Arrays.sort(num);
		ArrayList sol = new ArrayList();
		subsetsdfs(0, num, sol, res);
		return res;
	}
	
	private void subsetsdfs(int pos, int[] num, ArrayList sol, ArrayList res) {
		res.add(sol.clone());
		
		for(int i = pos; i < num.length; i++) {
			sol.add(num[i]);
			subsetsdfs(i + 1, num, sol, res);
			sol.remove(sol.size() - 1);
		}
	}
	
	
	/**
	 * 2.1
	 * subset II template
	 * the array may have duplicates
	 */
	/*
	19 / 19 test cases passed.
	Status: Accepted
	Runtime: 488 ms
	Submitted: 0 minutes ago
	*/
	
	
	public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
		ArrayList res = new ArrayList();
		if (num == null || num.length == 0) {
			return res;
		}
		Arrays.sort(num);
		ArrayList sol = new ArrayList();
		dfs(0, num, sol, res);
		return res;
	}
	
	
	
	// i == pos 表示第一次走，i != pos 表示recursion back 时，i继续走， 这时num[i] == num[i - 1]， 既然num[i - 1] 走过了， num[i] 就不用走了，跳过。
	private void dfs(int pos, int[] num, ArrayList sol, ArrayList res) {
		res.add(sol.clone());
		
		for(int i = pos; i < num.length; i++) {
			if (i != pos && i > 0 && num[i] == num[i - 1]) {
				continue;
			}
			sol.add(num[i]);
			dfs(i + 1, num, sol, res);
			sol.remove(sol.size() - 1);
		}
	}
	
	
	
	
	
//-----------------------------------------------------------------------------------------------------------------------------
//下面进入正题 搜索题目,排列组合问题的一般解法 DFS recursion，先回想一下template,再上！
	
	/**
	 * Main Topic: DFS
		Subsets II
		Subsets
	    N-Queens II   					应该能看出url规律
		N-Queens                        https://oj.leetcode.com/problems/n-queens/
		Combinations					https://oj.leetcode.com/problems/combinations/
		Combination Sum II
		Combination Sum					https://oj.leetcode.com/problems/combination-sum/
		Sum Root to Leaf Numbers
		Restore IP Addresses
		Sudoku Solver
		Valid Sudoku
		Word Break II
		Generate Parentheses
		Letter Combinations of a Phone Number
		Permutations II
		Permutations
		Permutation Sequence
		Next Permutation
	 */
	
	/**
	 * 3.1 N-Queens
	 */
	
	public ArrayList<String[]> solveNQueens(int n){
        ArrayList res = new ArrayList();
        if (n <= 0) {
            return res;
        }
        String[] sol = new String[n];
        dfs(0, n, sol, res);
        return res;
    }
    
    private void dfs(int stRow, int n, String[] board, ArrayList res) {
        if (stRow == n) {
            res.add(board.clone());//only need to change here,  count++; return;   N-Queens II answer
            return;
        }
        
        for (int col = 0; col < n; col++) {
            if (isValid(stRow, col, board)) {
                char[] cur = new char[n];
                Arrays.fill(cur, '.');
                cur[col] = 'Q';
                board[stRow] = String.copyValueOf(cur);
                dfs(stRow + 1, n, board, res);
            }
        }      
    }
    
    private boolean isValid(int stRow, int col, String[] board) {
        
        for (int i = 0; i < stRow; i++) {
            int Qcol = board[i].indexOf('Q');
            
            if (Qcol == col) {
                return false;
            }
            
            int colDiff = Math.abs(Qcol - col);
            int rowDiff = Math.abs(stRow - i);
            
            if (colDiff == rowDiff) {
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * 4.0
     * Combinations
     * n = 4, k = 2
     * 1 2
     * 1 3 
     * 1 4
     * 2 3 
     * 2 4
     * 3 4
     * 
26 / 26 test cases passed.
Status: Accepted
Runtime: 456 ms
Submitted: 0 minutes ago
	
     */
    
    public ArrayList<ArrayList<Integer>> combination(int n, int k) {
    	ArrayList res = new ArrayList();
    	if (k > n || n <= 0) {
    		return res;
    	}
    	//already sort
    	ArrayList sol = new ArrayList();
    	dfs(0, k, n, sol, res);
    	return res;
    }
	
    private void dfs(int pos, int k, int n, ArrayList sol, ArrayList res) {
    	if (k == 0) {
    		res.add(sol.clone());
    		return;
    	}
    	
    	for (int i = pos; i < n; i++) {
    		sol.add(i + 1);
    		dfs(i + 1, k - 1, n, sol, res);
    		sol.remove(sol.size() - 1);
    	}
    }
    
    
    
    /**
     * 5.0
     * combination sum II 
     */
    public ArrayList<ArrayList<Integer>> combinationSum(int[] data, int tar) {
		ArrayList res = new ArrayList();
		if (data == null || data.length == 0 || tar <= 0) {
			return res;
		}
		Arrays.sort(data);
		ArrayList sol = new ArrayList();
		dfs(0, tar, data, sol, res);
		return res;
	}
	
	void dfs(int pos, int sum, int[] data, ArrayList sol, ArrayList res) {
		if (sum == 0) {
			res.add(sol.clone());
			return;
		}
		
		if (sum < 0) {
			return;
		}
		
		for (int i = pos; i < data.length; i++) {
			if (i != pos && i > 0 && data[i] == data[i - 1]) {
				continue;
			}
			sol.add(data[i]);
			dfs(i + 1, sum - data[i], data, sol, res);
			sol.remove(sol.size() - 1);
		}
	}
    
	
	
	/**
	 * 6.0 sum from root to leave node
	 *      1
		   / \
		  2   3
		The root-to-leaf path 1->2 represents the number 12.
		The root-to-leaf path 1->3 represents the number 13.
		
		Return the sum = 12 + 13 = 25.
	 */
	
	
    private int sumNumbers = 0;
	public int sumNumbers(TreeNode root) {
		if (root == null) {
			return 0;
		}
		dfs(root, 0);
		return sumNumbers;
	}
	
	void dfs(TreeNode root, int sum) {
		if (root == null) {
			return;
		}
		int cursum = sum * 10 + root.val;
		if (root.left == null && root.right == null) {
			sumNumbers += cursum;
			return;
		}
		dfs(root.left,  cursum);
		dfs(root.right, cursum);
	}
	
	
	
	@Test
	public void testpathsum(){ 
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(0);
		root.right = new TreeNode(0);
		root.left.left = new TreeNode(0);
		int res = pathsum(root).pathsum;
		int res2 = sumNumbers(root);
		assertEquals(res2, res);
	}
	
	class ResultType{
		int div;
		int pathsum;
		ResultType(int div, int pathsum) {
			this.div = div;
			this.pathsum = pathsum;
		}
	}
	
	//这题适用Divide and conquer
	public ResultType pathsum(TreeNode root) {
		if (root == null) {
			return new ResultType(1, 0);
		}
		if (root.left == null && root.right == null) {
			return new ResultType(1, root.val);
		}
		//divide
		ResultType left = pathsum(root.left);
		ResultType right = pathsum(root.right);
		
		//conquer
		int div = Math.max(left.div, right.div) * 10;
		
		int pathsum = left.pathsum  + root.val * left.div * 10 + right.pathsum + root.val * right.div * 10;
		return new ResultType(div, pathsum);
	}
	
	
	
	
	
	/**
	 * generate Parenthesis
	 * 
	 * For example, given n = 3, a solution set is:
		"((()))", "(()())", "(())()", "()(())", "()()()"
	 * @param n
	 * @return
	 */
	public ArrayList<String> generateParenthesis(int n) {
        ArrayList res = new ArrayList();
        if(n <= 0){
            return res;
        }
        StringBuffer sol = new StringBuffer();
        dfs(0, 0, n, sol, res);
        return res;
    }
    
    void dfs(int left, int right, int n, StringBuffer sol, ArrayList res){
        if (left < n) {
            sol.append('(');
            dfs(left + 1, right, n, sol, res);
            sol.deleteCharAt(sol.length() - 1);
        }
        
        if (right < left) {
            sol.append(')');
            dfs(left, right + 1, n, sol, res);
            sol.deleteCharAt(sol.length() - 1);
        }
        
        if (left == n && right == n) {
            res.add(sol.toString());
            return;
        }
    }
	
    
    /**
     * word break II
     * This is cannot pass the leetcode because of Time Limited Exceeded.
     * But this version should pass the 面试官. 
     * There are two ways to improve the performance:
     * 1. use dp
     * 2. use hashmap
     */
    
    @Test
    public void testwordbreak(){
    	String s = new String("catsanddog");
    	Set<String> dict = new HashSet();
    	String[] strs = {"cat", "cats", "and", "sand", "dog"};
    	for (int i = 0; i < strs.length; i++) {
    		dict.add(strs[i]);
    	}
    	ArrayList<String> res = wordBreak(s, dict);
    	System.out.println(res);
    }
    
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        ArrayList<String> res = new ArrayList();
        if (s == null || dict.size() == 0) {
            return res;
        }
        StringBuffer sol = new StringBuffer();
        dfs(0, s, dict, sol, res);
        return res;
    }
    
    void dfs(int pos, String s, Set<String> dict, StringBuffer sol, ArrayList<String> res) {
        if (pos == s.length()) {
            res.add(sol.toString());
            return;
        }        
        
        StringBuffer buf = new StringBuffer();
        for (int i = pos; i < s.length(); i++) {
            buf.append(s.charAt(i));
            String tmp = buf.toString();
            if (dict.contains(tmp)) {
                int buflen = buf.length();
                if (i != s.length() - 1) {
                    buf.append(' ');                                                
                }
                int sollen = sol.length();
                sol.append(buf);
                dfs(i + 1, s, dict, sol, res);
                buf.delete(buflen, buf.length());
                sol.delete(sollen, sol.length());
            }
        }
    }
    
    
    
	
    
    
	
	

}
