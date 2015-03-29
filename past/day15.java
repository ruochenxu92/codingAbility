package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class day15 {

	
	 public void recoverTree(TreeNode root) {
		 
		 
		 List<Integer> vals = new ArrayList();
		 List<TreeNode> nodes = new ArrayList();
		 InorderTraverse(root, vals, nodes);
		 Collections.sort(vals);
		 
		 for(int i = 0; i < vals.size(); i++){
			 nodes.get(i).val = vals.get(i);
		 }
		 
		 
	 }
	 
	 void InorderTraverse(TreeNode root, List<Integer> vals, List<TreeNode> nodes){
		 
		 if(root == null)
			 return ;
		 
		 InorderTraverse(root.left, vals, nodes);
		 	vals.add(root.val);
		 	nodes.add(root);
		 InorderTraverse(root.right, vals, nodes);
		 
		 
	 }
	
	
	
	
	
	
//	 public void recoverTree(TreeNode root) {
//	        TreeNode n1;
//	        TreeNode n2;
//	        
//	        
//	    }
//	    
//	    void find(TreeNode root,int count, TreeNode left, TreeNode right){
//	        if(root == null){
//	            return;
//	        }
//	        
//	        if(count == 2){
//	            return;
//	        }
//	        if(root.left != null && root.left.val > root.val){
//	            left = root.left;
//	            count++;
//	            
//	        }
//	        
//	        if(root.right != null && root.right.val < root.val){
//	            right = root.right;
//	            count++;
//	        }
//	        
//	      
//	        
//	        find(root.left, count, )
//	        
//	        
//	    }
	    
	
	@Test
	public void testIpAddress(){
		List<String> res =restoreIpAddresses("255255110123");
		System.out.println(res);
	}
	
	
	public List<String> restoreIpAddresses(String s) {
        
        List<String> res = new ArrayList();
        StringBuffer curr = new StringBuffer();
        dfs(0, 4, s, curr, res);
        return res;
    }
    
    
    void dfs(int start, int count, String s,  StringBuffer sol, List<String> res){
        if(start >= s.length()){
            res.add(sol.toString());
            count++;
            return;
        }
        if(count == 0){
        	return;
        }
        
        StringBuffer curr = new StringBuffer();

        for(int i = start; i < s.length(); i ++){
        	
            curr.append(s.charAt(i));
            String tmp = curr.toString();
            if(tmp.charAt(0) == '0' && tmp.length() > 1){
            	return;
            }
            int num = Integer.parseInt(tmp);
            if(num < 256){
            	int oldcurr = curr.length();
                if(i!= s.length()-1){
                    curr.append('.');
                }  
                int old = sol.length();
                sol.append(curr);
                dfs(i+1,count-1, s, sol, res);
                sol.delete(old, sol.length());
                curr.delete(oldcurr, curr.length());
                
            }else{
                return;
            }
            
            
        }
    }
    
	
	
	
	@Test
	public void testreverse(){
		int sum = reverse(10);
		System.out.println(sum);
	}
	
	public int reverse(int x) {
        int absx = Math.abs(x);
        
        //get first and put one 
        
        int div = 1;
        while(absx / div >= 10){
            div *= 10;
        }
        
        Deque<Integer> queue = new ArrayDeque();
        
        while(absx >= 10){
            int num = absx % 10;
            queue.offer(num);
            absx /= 10;
        }
        
        queue.offer(absx % 10);
        
        
        int sum = 0;
        while(queue.peek() != null){
            int num = queue.poll();
            sum += num * div;
            div /= 10;
        }
        if(x >= 0)
        return sum;
        else
        	return 0-sum;
    }
	
	
	 public boolean isSameTree(TreeNode p, TreeNode q){
	        if(p == null || q == null)
	            return p == null && q == null;
	        if(p.val != q.val)
	            return false;
	            
	        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	        
	 }
	
	
	
	
	 @Test
	 public void testisScramble(){
		 String s1= new String("great");
		 String s2 = new String("rgeat");
		 boolean res = isScramble(s1, s2);
		 System.out.println(res);
		 
	 }
	
	
	    public boolean isScramble(String s1, String s2){
			if(s1 == null || s2 == null){
				return s1 == null && s2==null;
			}
			
			return dac(s1,s2);
		}

		public boolean dac(String s1, String s2){
		    if( s1.length() != s2.length() )
		        return false;
		        
		    if( s1.length() == 1){
		        return s1.charAt(0) == s2.charAt(0);
		    }
		    //charset
		    if(!check( s1, s2))
		        return false;
		     
		     int n = s1.length();
		     
		     
		    //partition
		    boolean result = false;
		    for(int i = 1; i < n && !result ; i++){
		        String s11 = s1.substring(0,i);
		        String s12 = s1.substring(i);
		        
		        String s21 = s2.substring(0,i);
		        String s22 = s2.substring(i);
		        
		        result = dac(s11, s21) && dac(s12,s22);
		        
		        if(!result){
		            String s31 = s2.substring(n-i);//i
		            String s32 = s2.substring(0, n-i);
		            result = dac(s11, s31) && dac(s12, s32);
		        }
		    }
		    return result;
		}
		
		
		
		
		boolean check(String s1, String s2){
	        char[] bucket1 = new char[26];
	        char[] bucket2 = new char[26];
	        
	        for(int i = 0; i < s1.length(); i++){
	            int index  = s1.charAt(i) - 'a';
	            bucket1[index]++;
	        }
		    
		    for(int i = 0; i < s2.length(); i++){
		        int index = s2.charAt(i) - 'a';
		        bucket2[index]++;
		    }
		    
		    for(int i = 0; i < 26; i++){
		        if(bucket1[i] != bucket2[i])
		            return false;
		    }
		    return true;
		}
		
		
	
		@Test
		public void testcheck(){
			boolean res = check("great", "rgeat");
			System.out.println(res);
		}
		
		
		
	/**
	 * [2],
	  [1],
	  [1,2,2],
	  [2,2],
	  [1,2],
	  []
	 */
	
	@Test
	public void testsubsetsWithDup(){
		int[] A = new int[]{1,2,2};
		
		
		ArrayList<ArrayList<Integer>> res= subsetsWithDup(A);
		System.out.println(res);
		
	}
	
		public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] A) {
		    int n = A.length;
		    ArrayList<Integer> curr = new ArrayList();
		    ArrayList<ArrayList<Integer>> res = new ArrayList();
		    Arrays.sort(A);
		    res.add(new ArrayList());
		    boolean[] visited = new boolean[n];
		    buildsubset(0, curr, visited, A, res);
		    return res;
		 }
		 
		
		
		 
		 void buildsubset(int start, ArrayList<Integer> curr, boolean[] visited, int[] A, ArrayList<ArrayList<Integer>> res){
	        
	        for(int i = start; i < A.length; i ++){
	            if( !visited[i] ){
	                if(i > 0 && A[i] == A[i-1] && !visited[i-1]){
	                    continue;
	                }
	                curr.add(A[i]);
	                visited[i] = true;
	                buildsubset(i + 1, curr, visited, A, res);                 
	                visited[i] = false;
	                res.add((ArrayList<Integer>) curr.clone());
	                curr.remove(curr.size()-1);
	            }
	        }
		     
		 }

	
	
	public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        if(S.length == 0)
           return new ArrayList();
        ArrayList<Integer> curr = new ArrayList();
        ArrayList<ArrayList<Integer>> res =new ArrayList();
         res.add(new ArrayList<Integer>());	
        
        
        Arrays.sort(S);
        dfs(0, curr, S, res);
        return res;
    }
    
    void dfs(int start, ArrayList<Integer> curr, int[] S, ArrayList<ArrayList<Integer>> res){
        
        
        for(int i = start; i < S.length; i++){
           curr.add(S[i]);
           dfs(i+1, curr, S, res);
           res.add((ArrayList<Integer>)curr.clone());
           curr.remove(curr.size()-1);
        }
        
        
    }
	
	
	
	public  int sumNumbers(TreeNode root){
        if(root == null)
            return 0;
        int[] res = new int[1];
        dfs(root, 0, 0, res);
        return res[0];
    }
        
    void dfs(TreeNode subroot, int curr, int level,  int[] res){
        if(subroot == null)
            return;
        if(subroot.left == null && subroot.right == null){
            res[0] += curr;
            return;
        }
        
        curr = (int) (curr * Math.pow(10, level)  + subroot.val);
        
        dfs(subroot.left, curr, level + 1, res);
        dfs(subroot.right, curr, level + 1, res);
        
        curr = curr* level + subroot.val;
    }      
    
	
	
	
	 public boolean isSymmetric(TreeNode root){
	        if(root == null){
	            return true;            
	        }        
	        
	        return Symmetric(root.left, root.right);
	        
	    }
	    
	    
	    public boolean Symmetric(TreeNode left, TreeNode right){
	        if(left == null || right == null){
	            return left == null && right == null;
	        }
	        if( left.val != right.val){
	            return false;
	        }
	        
	        return Symmetric(left.left, right.right) && Symmetric(left.right, right.left);
	        
	    }
	
	
	
	@Test
	public void testlargestRectangleArea3(){
		
		int[] height = new int[]{2,2,2,2,2,1,5,6,2,3};
		int res = largestRectangleArea3(height);
		System.out.println(res);
	}
	
	
	
	 public int largestRectangleArea3(int[] height) {
	        if(height==null || height.length==0)
	            return 0;
	       
	        LinkedList<Integer> stack = new LinkedList();
	        int n = height.length;
	        int max = 0;
	        
	        for(int i = 0; i < n; i++){
	            
	            while( stack.peek() != null && height[i] <= height[stack.peek()] ){
	                int index = stack.pop();
	                
	                int curArea = stack.peek() == null ? (i - 0) * height[index] : (i - (stack.peek() + 1)) * height[index];
	                
	                max = Math.max(max, curArea);
	            }
	            
	            stack.push(i);
	        }
	        
	        
	        while(stack.peek() != null){
	            int index = stack.pop();
	            int curArea = stack.peek() == null ? (n - 0) * height[index] : (n - (stack.peek() + 1)) * height[index];
	            max = Math.max(max, curArea);
	        }
	        
	        
	        return max;
	    }
	
	@Test
	public void testlargestRectangleArea(){
		
		int[] height = new int[]{2,1,5,6,2,3};
		largestRectangleArea(height);
		
	}
	
	
	//O(n)    O(n)
	public int largestRectangleArea(int[] height) {
		if(height == null || height.length == 0){
			return 0;
		}
		
		int max = 0;
		LinkedList<Integer> stack = new LinkedList();
		
		for(int i = 0 ; i < height.length; i++){
			while(!stack.isEmpty() && height[i] <= height[stack.peek()]){
				int index = stack.pop();
				int curArea = stack.isEmpty()? (i - 0) * height[index]:(i - (stack.peek() + 1)) * height[index];
				max = Math.max(max, curArea);
			}
			
		}
		while(!stack.isEmpty()){
			int index = stack.pop();
			int curArea = stack.peek() == null ? (height.length - 0) * height[index] : (height.length - (stack.peek()+1))*height[index];
			max = Math.max(max, curArea);
		}
		return max;
	}

	
	
	
	public int largestRectangleArea2(int[] height) {
        if(height==null || height.length==0)
            return 0;
        int max = 0;
        LinkedList<Integer> stack = new LinkedList<Integer>();
        for(int i=0;i<height.length;i++)
        {
            while(!stack.isEmpty() && height[i]<=height[stack.peek()])
            {
                int index = stack.pop();
                int curArea = stack.isEmpty()?i*height[index]:(i - (stack.peek()+1))*height[index];
                max = Math.max(max,curArea);
            }
            stack.push(i);
        }
        while(!stack.isEmpty())
        {
            int index = stack.pop();
            int curArea = stack.isEmpty()?height.length*height[index]:(height.length-stack.peek()-1)*height[index];
            max = Math.max(max,curArea);            
        }
            return max;
    }
	
	
	public int largestRectangleArea1(int[] A) {
		int n = A.length;

    	if(n == 0)
    	    return 0;

		int[] table = new int[n];

		for (int i = 0; i < n; i++) {
			int border = A[i];
			int start = 0;
			int last = n - 1;
			for (int j = 0; j < n; j++) {
				if (A[j] < border) {
					if (j < i) {
						start = j;
					} else {
						if (last > j)
							last = j;
					}
				}

			}
			table[i] = last - start + 1;
		}

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			int area = A[i] * table[i];
			if (area > max) {
				max = area;
			}
		}

		return max;
	}
	
	
	
	
	public int uniquePathsWithObstacles(int[][] A) {
        int m = A.length;
        if(m == 0){
            return 0;
        }
        int n = A[0].length;
        
        int[][] table =new int[m][n];
        if(A[0][0] == 0){
            table[0][0] = 1;
        }
        if(table[0][0] == 1){
        for(int i = 1; i < m ; i++){
            if(A[i][0] == 0 && A[i-1][0]>0){
                table[i][0] = 1;
            }else{
                table[i][0] = 0;
            }
        }
        
        for(int i = 1; i < n ;i++){
            if(A[0][i] == 0 && A[0][i-1]>0){
                table[0][i] = 1;
            }else{
                table[0][i] = 0;
            }
        }
        }
        
        
        for(int i = 1; i < m ;i++){
            for(int j= 1; j < n; j++){
                if(A[i][j] == 1){
                    table[i][j] = 0;
                }else{
                    table[i][j] = table[i-1][j] + table[i][j-1];                   
                }
            }
        }
        
        
        return table[m-1][n-1];
        
    }
        
     
	
	public int uniquePaths(int m, int n){
	
	
		
        
        
        if( m == 0 || n == 0 ){
            return 1;
        }
        
        int[][] table = new int[m][n];
        
        for(int i = 0; i < m ; i++){
            table[i][0] = 1;
        }
        
        
        for(int i = 0; i < n; i++){
            table[0][i] = 1;
        }
        
        for(int i = 1; i < m; i ++){
            for(int j = 1; j < n; j++){
                table[i][j] = table[i-1][j] + table[i][j-1];
            }
        }
        return table[m-1][n-1];
        
        
    }
	
	
	@Test
	public void testisPalindrome(){
		String s = new String("1aba2");
		System.out.println(isPalindrome(s));
	}
	
	public boolean isPalindrome(String s) {
        s = s.trim();
        s = s.toLowerCase();
        
        StringBuffer buf = new StringBuffer();
        for(int i = 0;i  < s.length(); i++){
            if(s.charAt(i) <= 'z' && s.charAt(i) >= 'a' || s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                buf.append(s.charAt(i));
            }
        }
        
        String res = buf.toString();
        char[] A = res.toCharArray();
        int n = A.length;
        for(int i = 0; i < A.length/2; i++){
        	if(A[i] != A[n-1 - i]){
        		return false;
        	}
        }
        return true;
        
    }
	
	
	
	 public boolean isValid(String s) {
	        
	        StringBuffer buf = new StringBuffer();
	        LinkedList<String>  stack = new LinkedList();
	        
	        
	        for(int i = 0; i < s.length(); i++){
	            char c = s.charAt(i);
	            if(c == '(' || c == '[' || c == '{' ){
	                stack.push(c + "");    
	            }else if( c== ')'){
	                if(stack.size() == 0){
	                    return false;
	                }
	                String pop = stack.pop();
	                if(!pop.equals("("))
	                    return false;
	            }else if( c== ']'){
	                if(stack.size() == 0){
	                    return false;
	                }
	                String pop = stack.pop();
	                if(!pop.equals("[")){
	                    return false;
	                }
	            }else if( c== '}'){
	                if(stack.size() == 0){
	                    return false;
	                }
	                String pop = stack.pop();
	                if(!pop.equals("{")){
	                    return false;
	                }
	            }
	        }
	        
	        if(stack.size() > 0){
	            return false;
	        }else{
	            return true;
	        }
	        
	        
	        
	    }
	
	
	
	 public boolean exist(char[][] board, String word){
	        if(word==null || word.length()==0)  
	            return true;  
	        if(board==null || board.length==0 || board[0].length==0)  
	            return false;  
	        
	        for(int i = 0; i < board.length; i++){
	            for(int j = 0; j < board[0].length; j++){
	                boolean[][] used = new boolean[board.length][board[0].length];
	                if(explore(0,i,j,board, word, used)){
	                    return true;
	                }
	            }
	        }
	        return false;
	    }
	    
	    
	    public boolean explore(int index, int x, int y, char[][] board, String word, boolean[][] used){
	        if(index == word.length())
	            return true;
	        if( x  < 0 || y  < 0 || x >= board.length || y>=board[0].length || used[x][y] || board[x][y] != word.charAt(index))
	            return false;
	        
	        used[x][y] = true;
	            
	            boolean res =  explore(index + 1, x+1, y, board, word, used) || 
	                           explore(index + 1, x-1, y, board, word, used) ||
	                           explore(index + 1, x, y + 1, board, word, used) || 
	                           explore(index + 1, x, y - 1, board, word, used);
	        
	        used[x][y] = false;
	        return res;
	        
	    }
	    
	
	
	public List<String> anagrams(String[] A){
		
		HashMap<String, List<String>> map = new HashMap();
		int n = A.length;
		
		for(int i = 0; i < n; i++){
			char[] chars = A[i].toCharArray();
			Arrays.sort(chars);
			String tmp = String.copyValueOf(chars);
			
			if(map.containsKey(tmp)){
				List<String> list = map.get(tmp);
				list.add(A[i]);
			}else{
				List<String> list = new ArrayList();
				list.add(A[i]);
				map.put(tmp, list);
			}
		}
		
		
		List<String> res = new ArrayList();
		
		Iterator it = map.values().iterator();
		
		while(it.hasNext()){
			List<String> tmp = (List<String>) it.next();
			if(tmp.size() > 1){
				res.addAll(tmp);
			}
		}
		return res;
		
	}

	
	
	
	public List<String> anagrams2(String[] A){
        //burn the disk method
        
        //keep it
		String[] B = A.clone();
        int n = A.length;

        
//        int bucket[][] = new int[n][26];
        
//        for(int i = 0;i  < n;i++){
//        	String tmp = A[i];
//        	for(int j= 0; j < n; j++){
//        		int index = tmp.charAt(j) - 'a';
//        		bucket[i][index]++;
//        	}
//        }
        List<String> res = new ArrayList();
        boolean find = false;
        boolean[] invalid = new boolean[n];
        Arrays.fill(invalid, true);
        for(int i =0; i < n; i++){
        	char[] chars = A[i].toCharArray();
        	Arrays.sort(chars);//O(nlgn)
        	A[i] = String.copyValueOf(chars);
        }
        
        for(int i=0; i < n; i++){
        	for(int j = i+1; j < n; j++){
        		if(!invalid[j] && A[i].equals(A[j])){
        			find = true;
        			res.add(A[j]);
        		}
        		
        	}
        	if(find){
        		res.add(A[i]);
        	}else{
        		invalid[i] = true;
        	}
        }
        
        return res;
   }
	
	@Test
	public void testanagrams(){
		String[] A = new String[]{"abc", "bac", "acb", "qqq","reg","fink","you"};
		List<String> res = anagrams(A);
		System.out.println(res);
	}
	
	 public List<String> anagrams1(String[] A) {
	        int n = A.length;
	        
	        List<String> rs = new ArrayList();        
	        if( n == 0)
	        	return rs;
	        boolean find = false;
	        for(int i = 0; i < n; i++){
	            String s1 = A[i];
	            for(int j = i+1; j < n; j++){
	                String s2 = A[j];
	                if(checkAnagrams(s1, s2)){
	                    rs.add(A[j]);
	                    find = true;
	                }
	            }
	            if(find){
	            	rs.add(s1);
	            }
	        }       
	        return rs;
	    }
	 	
	 	@Test
	 	public void testcheckAnagrams(){
	 		boolean b = checkAnagrams("ape", "and");
	 		System.out.println(b);
	 	}
	 
	 
	 
	    boolean checkAnagrams(String s1, String s2){
	        int n = s1.length();
	        if(n != s2.length())        
	            return false;
	        int[] bucket1 = new int[26];
	        int[] bucket2 = new int[26];
	        
	        
	        for(int i =0 ; i < n; i++){
	            int index = s1.charAt(i) - 'a';
	            bucket1[index] ++;
	        }
	        
	        for(int i =0; i < n; i++){
	            int index = s2.charAt(i) - 'a';
	            bucket2[index] ++;
	        }
	        
	        for(int i = 0; i < 26; i++){
	            if(bucket1[i] != bucket2[i]){
	                return false;
	            }
	        }
	        return true;
	    }
	    
	    
	
	
	@Test
	public void testcombinationSum2(){
		int[] A = new int[]{1,1,2,5,6,7,10};
		List<List<Integer>> res = combinationSum2(A, 8);
		System.out.println(res);
	}
	
	
	public List<List<Integer>> combinationSum2(int[] A, int target) {
        List<List<Integer>> res = new ArrayList();
        int n = A.length;
        int[] visited = new int[n];
        List<Integer> curr = new ArrayList();
        Arrays.sort(A);
        combinationSum2(0, 0, 0, A, visited, res, curr, target); 
        return res;
    }
    
    public void combinationSum2(int start, int last, int sum,  int[] A, int[] visited, List<List<Integer>> res, List<Integer> curr, int target){
        if(sum > target){
            return;
        }
        
        if(sum == target){
            List<Integer> tmp = new ArrayList(curr);
            res.add(tmp);
            return;
        }
        
        
        for(int i = start; i < A.length; i++){
            if(visited[i] == 0){
                if(i > 0 && A[i] == A[i-1] && visited[i-1] == 0)
                    continue;
                if(A[i] < last){
                    continue;
                }
                
                curr.add(A[i]);
                visited[i] = 1;
                sum += A[i];
                last = A[i];
                combinationSum2(start + 1, last, sum, A, visited, res, curr, target);
                sum -= A[i];
                visited[i] = 0;
                curr.remove(curr.size()-1);
            }
        }
    }
    
    
    
    
    
	
	
		 @Test
		 public void testcombinationSum(){
			 int[] A =new int[]{2,3,6,7};
			 List<List<Integer>> res= combinationSum(A, 7);
			 System.out.println(res);
			 
		 }
	
	 
	 	public List<List<Integer>> combinationSum(int[] A, int target) {
	        List<List<Integer>> res = new ArrayList();
	        List<Integer> curr =new ArrayList();
	        
	        
	        cominationSum(0,0, false, A, 0, curr, res, target);
	        return res;
	    }
	    
	    
	    public void cominationSum(int recent, int start, boolean last, int[] A,  int sum, List<Integer> curr, List<List<Integer>> res, int target){
	        if( sum > target ){
	        	last = true;
	            return;
	        }
	        
	        if( sum == target ){
	            List<Integer> tmp = new ArrayList(curr);
	            res.add(tmp);
	            return;
	        }
	        
	        for(int i = start; i < A.length; i++){
	        	if(last == true){
	        		last = false;
	        		break;
	        	}
	        	if(A[i] < recent){
	        		continue;
	        	}
	            curr.add(A[i]);
	            sum += A[i];
	            recent = A[i];
	            cominationSum(recent, start, last, A, sum, curr, res, target);            
	            sum -= A[i];
	            curr.remove(curr.size()-1);
	            
	        }
	    }
	
	
	
	@Test
	public void testcombine(){
		
		List<List<Integer>> res = combine(4,2);
		
		System.out.println(res);
		
	}
	  
    public List<List<Integer>> combine(int n, int k) {  
    	
    	int[] visited = new int[n+1];
    	List<Integer> curr = new ArrayList();
    	List<List<Integer>> res = new ArrayList();
    	
    	combinek(1, 0,  n,k, visited, curr, res);
    	
    	return res;
    	
    }
    
    
    public void combinek(int start, int last, int n, int k, int[] visited, List<Integer> curr, List<List<Integer>> res){
    	if(curr.size() == k){
    		List<Integer> tmp = new ArrayList(curr);
    		res.add(tmp);
    		return;
    	}
    	
    	for(int i = start; i <= n; i++){
    		if(visited[i] == 0 && i > last){
    			visited[i] = 1;
    			
    			curr.add(i);
    			last = i;
    			combinek(start + 1,last, n, k, visited, curr, res);
    			curr.remove(curr.size() - 1);
    			visited[i] = 0;
    		}
    	}
    }
    
    

	
	
	public int totalNQueens(int n) {
        int stRow = 0;
        String[] rows = new String[n];
        int[] sum = new int[1];
        sum[0] = 0;
        dfs(stRow, n, rows, sum);
        return sum[0];
    }
    
    void dfs(int stRow, int n, String[] rows, int[] sum){
        if(stRow == n){
            sum[0] ++;
            return;
        }
        
        
        for(int col = 0; col < n; col++){
            if(!check(col, stRow, rows)){
                    continue;
            }   
            
            char[] chars = new char[n];
            
            Arrays.fill(chars, '.');
            
            chars[col] = 'Q';
            rows[stRow] = String.copyValueOf(chars);
            dfs(stRow+1, n, rows, sum);
        }        
    }
    
    public boolean check(int col, int stRow, String[] rows){
        
        for(int i =0; i < stRow; i++){
            int Qcol = rows[i].indexOf("Q");
            
            if(Qcol == col)
                return false;
            
            int diffcol = Math.abs(Qcol - col);
            int diffrow = Math.abs(stRow- i);
            
            if(diffcol == diffrow){
                return false;
            }
        }
        
        return true;
    }
	  
	  
			  
		
}
