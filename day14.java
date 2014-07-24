package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class day14 {
	
	
	
//    public ArrayList<String[]> solveNQueens(int n){
//    	ArrayList<String[]> result = new ArrayList();
//    	String[] rows = new String[n];
//    	
//    	solutions(0, n, rows, result);
//    	
//    	
//    	
//    	
//    }
	
    private void solutions(int stRow, int n,String[] rows, ArrayList<String[]> result ){
    	if(stRow == n){
    		result.add(rows.clone());
    		return;
    	}
    	
    	for(int col=0; col<n; col++){
            if (!isValid(col, stRow,  rows)){
                continue;
                
            }
            
            char[] chars=new char[n];
            Arrays.fill(chars, '.');
            chars[col]='Q';
            
            
            rows[stRow]=String.copyValueOf(chars);
            solutions(stRow+1, n, rows, result);
            
        }
    	
    }
    
    public boolean isValid(int col, int stRow, String[] rows){
    	
    	
    	
    	return false;
    	
    }
	
	
	
	
	@Test
	public void testadd(){
		int[] abc = new int[10];
		Arrays.fill(abc, 1);
		for(int i =0; i < 10; i++){
			System.out.print(abc[i] + ",");
		}
		
		
		char[] carr = new char[]{'x','x','u'};
		String tmp = String.copyValueOf(carr);
		System.out.println(tmp);
	}
	
	
	
	
	/**
	 * [
		 [".Q..",  // Solution 1
		  "...Q",
		  "Q...",
		  "..Q."],
		
		 ["..Q.",  // Solution 2
		  "Q...",
		  "...Q",
		  ".Q.."]
		]
	 * 
	 */
	
	class Point{
		int x;
		int y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	
	void dfs(int start, List<Point> sol,  List<List<Point>> res, int n){
		if(sol.size() >= n){
			List<Point> tmp = new ArrayList(sol);
			res.add(tmp);
			return;
		}
		
		int cols = n-1;
		int x = start / cols;
		int y = start / cols;
		
		for(int i = x; i < n; i++){
			for(int j= y; j < n; j++){
				if(check(sol, i, j)){
					Point p = new Point(i,j);
					sol.add(p);
					dfs(i * cols + j +1, sol, res, n);
					sol.remove(sol.size()-1);
				}
			}
		}
		
	}
	
	public boolean check(List<Point> sol, int x, int y){
		
		for(int i = 0; i < sol.size(); i++){
			Point A = sol.get(i);
			
			if(A.x == x || A.y == y){
				return false;
			}
			
			if((A.x - x)== (A.y- y)){
				return false;
			}
		}
		return true;
	}
	
	
	
	@Test
	public void testsolveNQueens(){
		
		solveNQueens1(4);
		
		
	}
	
	
	public List<List<Point>> solveNQueens1(int n) {
        
		List<Point> sol =new ArrayList();
		List<List<Point>> res =new ArrayList();
		
		dfs(0, sol, res, n);
		return res;
        
    }

	
	
	
	
	
	
	/*
	 * 1.this is math question, from right to left, find the first digit(parition number)
	 * which violate the increase trend
	 * in this example, 6 will
	 * 
	 * 2. first large number
	 * 
	 * 3. swap partition number and large 
	 * 
	 * 4. sort.
	 * 
	 */
	@Test
	public void testnextPermutation0(){
		int[] A = new int[]{4,3,2,1};
		
		nextPermutation(A);
		
		for(int i = 0; i  < A.length; i++){
			System.out.print(A[i] +",");
		}
	}
	
	@Test
	public void testnextPermutation1(){
		int[] A = new int[]{1,2,3,4};
		
		nextPermutation(A);
		
		for(int i = 0; i  < A.length; i++){
			System.out.print(A[i] +",");
		}
	}
	
	
	public void nextPermutation(int[] A) {
		
		int nextindex = -1;
		int firstlarge = -1;
		
		int n = A.length;
		
		for(int i = n-2; i >= 0; i--){
			if(A[i] < A[i+1]){
				nextindex = i;
				break;
			}
		}
		if(nextindex == -1){
			Arrays.sort(A);
			return;
		}
				
		for(int i = n-1; i > nextindex;i--){
			if(A[i] > A[nextindex]){
					firstlarge = i;
					break;
			}
		}
		
		int tmp = A[nextindex];
		A[nextindex] = A[firstlarge];
		A[firstlarge] = tmp;
		
		
		Arrays.sort(A, nextindex + 1, n-1);
		
    }

	
	
	
	
	//k = 1,2,3,4,5
	@Test
	public void testgetPermutation(){
		String res = getPermutation(5, 30);
		System.out.println(res);
	}
	
	
	
	
	public String getPermutation(int n, int k){
		//n!
		StringBuffer buf = new StringBuffer();
		List<Integer> nums = new ArrayList();
		if(k > factorial(n)){
			return "";
		}
		for(int i = 1; i <= n; i ++){
			nums.add(i);
		}
		
		for(int i=n-1; i >= 0; i--){
			
			int mod = factorial(i);
			
			int currIndex = k/mod ;
			
			k = k % mod;
			int currnum = nums.get(currIndex);
			
			buf.append(currnum);
			
			nums.remove(currIndex);
			
		}
		return buf.toString();
		
	}
	
	
	public int factorial(int n){
		int factorial = 1;
		for(int i = n; i > 0; i--){
			factorial *= i;
		}
		return factorial;
	}
	
	
	@Test
	public void testpermuteUnique(){
		int[] A = new int[]{1,1,2};
		List<List<Integer>> res = permuteUnique(A);
		System.out.println(res);
		
	}
	
	
	public List<List<Integer>> permuteUnique(int[] A){
		List<List<Integer>> res = new ArrayList();
		List<Integer> sol =new ArrayList();
		Arrays.sort(A);
		int n  = A.length;
		int[] visited = new int[n];
		
		buildpermutation(A, sol, visited, res);
		return res;
		
	}
		
		void buildpermutation(int[] A, List<Integer> sol, int[] visited, List<List<Integer>> res){
			
			if(sol.size() == A.length){
				List<Integer> tmp = new ArrayList(sol);
				res.add(tmp);
				return;
			}
			
			for(int i=0; i < A.length; i++){
				if(visited[i] == 0){
					
					if(i > 0 && A[i] == A[i-1] && visited[i-1] == 0)
						continue;
					visited[i] = 1;
					sol.add(A[i]);
					buildpermutation(A,sol, visited, res);
					sol.remove(sol.size()-1);
					visited[i] = 0;
				}
			}
			
		}
	
	
	
	
	
	
	public List<List<Integer>> permuteUnique1(int[] A) {
		List<List<Integer>> res = new ArrayList();
		List<Integer> dict = new ArrayList();
		List<Integer> sol = new ArrayList();
		for(int i =0; i < A.length; i++){
			dict.add(A[i]);
		}
		Set<List<Integer>> set = new HashSet();
		
		dfs1(A, sol, dict, set);
		Iterator it = set.iterator();
		while(it.hasNext()){
			res.add((List<Integer>) it.next());
		}
		
		return res;
		
    }
	
	

	void dfs1(int[] A, List<Integer> sol, List<Integer> dict, Set<List<Integer>> res){
		if(sol.size() >= A.length){
			List<Integer> tmp = new ArrayList(sol);
			res.add(tmp);
			return;
		}
		
		for(int i =0; i < A.length; i++){
			Integer curr = A[i];
			if(dict.contains(curr)){
				dict.remove(curr);
				sol.add(curr);
				dfs1(A, sol, dict, res);
				dict.add(curr);
				sol.remove(sol.size()-1);
			}
		}
	}
	
	
	
	
	@Test
	public void testpermute(){
		int[] A= new int[]{1,2,3};
		permute(A);
		
	}
	
	public List<List<Integer>> permute(int[] A) {
		List<List<Integer>> res = new ArrayList();
		List<Integer> sol = new ArrayList();
		HashSet<Integer> visited =new HashSet();
		
		dfs(A, sol, visited, res);
        return res;
    }
	
	
	void dfs(int[] A, List<Integer> sol, Set<Integer> visited, List<List<Integer>> res){
		if(sol.size() >= A.length){
			List<Integer> tmp = new ArrayList(sol);
			res.add(tmp);
			return;
		}
		
		for(int i =0; i < A.length; i++){
			int curr = A[i];
			if(!visited.contains(curr)){
				visited.add(curr);
				sol.add(curr);
				dfs(A, sol, visited, res);
				visited.remove(curr);
				sol.remove(sol.size()-1);
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testrotate(){
		int[][] A = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
		int[][] B = new int[][]{{1,2},{3,4}};
		rotate(B);
		System.out.println(A);
	}
	
	public void rotate(int[][] matrix){
		int n = matrix.length;
		
		
		
		
		
		
	}
	
	
	
	
	
	public void rotatereverseclockwise(int[][] matrix) {
        int n = matrix.length;
        
      
        int left = 0;
        int right = n-1;
        int up = 0;
        int down = n-1;
        
        while(left < right && up < down){
        	int len = right-left + 1;
            int[] buf = new int[len];
              
            //copy to the buffer
            for(int i =up, k = 0; i <= down; i++,k++){
                buf[k] = matrix[i][left];
            }
            
            for(int i = down; i >= up; i--){
            	
                matrix[i][left] = matrix[up][down - i];
            
            }
            
            for(int i = left; i <= right; i++){
            	
            	matrix[up][i] = matrix[i][right];
            
            }
            
            for(int i = up; i <= down; i++){
            	
            	matrix[i][right] = matrix[down][right-i];
            
            }
            
            for(int i = left; i <= right; i++){
            	matrix[down][i] = buf[i];
            }
            
            left++;
            up++;
            right--;
            down--;
            
        }
        
        
    }
	  
	  
	  
	  
	  
	
	@Test
	public void testsetZeros(){
		int[][] A = new int[][]{{1,2,3,4},{0,1,2,3},{0,3,2,4},{2,3,4,5}};
		
		setZeroes(A);
	}
	
	
	public void setZeroes(int[][] matrix) {
        Set<Integer> idx = new HashSet();
        Set<Integer> idy = new HashSet();
        
        int m = matrix.length;
        if(m == 0){
        	return; 
        }
        int n = matrix[0].length;
        
        for(int i =0; i < m; i++){
        	for(int j = 0; j < n; j++){
        		if(matrix[i][j] == 0){
        			idx.add(i);
        			idy.add(j);
        		}
        		
        	}
        }
        
        //reduce;
        Iterator it = idx.iterator();
        while(it.hasNext()){
        	int row = (int) it.next();
        	for(int i = 0; i < n; i++){
        		matrix[row][i] = 0;
        	}
        }
        
        it = idy.iterator();
        while(it.hasNext()){
        	int col = (int) it.next();
        	for(int i =0; i <n; i++){
        		matrix[i][col] = 0;
        	}
        }        
        
    }
	
	
	@Test
	public void testgenerateMatrix(){
		int n =3;
		int[][] res = generateMatrix(n);
	
	}
	
	
	public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        
        int count = 1;
        
        
        int left = 0;
        int right = n-1;
        int up = 0;
        int down = n-1;
        
        while(left <= right && up <= down){
        	
        	for(int i = left; i <= right; i++){
        		res[up][i] = count;
        		count++;
        	}
        	
        	for(int i = up+1; i <= down; i++){
        		res[i][right] = count;
        		count++;
        	}
        	
        	for(int i = right-1; i>=left; i--){
        		res[down][i] = count;
        		count++;
        	}
        	
        	for(int i = down-1; i > up; i--){
        		res[i][left] = count;
        		count++;
        	}
        	
        	left++;
        	up++;
        	right--;
        	down--;
        }
        
        return res;
    }
	
	@Test
	public void testspiralOrder(){
		int[][] matrix1 = new int[][]{{1,2,3,4},{5,6,7,8}};
		
		int[][] matrix2 = new int[][]{{1,2},{5,6}};
		int[][] matrix3 = new int[][]{{1}};
		int[][] matrix = new int[][]{{2,3}};
		
		List<Integer> res = spiralOrder(matrix);
		System.out.println(res);
	}

	
    public List<Integer> spiralOrder(int[][] matrix) {
    	int m = matrix.length;
    	int n = matrix[0].length;
    	if(n == 0){
    		return new ArrayList();
    	}
    	
    	List<Integer> res = new ArrayList();
    	if(m == 0){
    		return res;
    	}
    	
    	//we can change this
    	int left = 0; 
    	int right = n-1;
    	int up = 0;
    	int down = m-1;
    	
    	
    	while(left <= right && up <= down){
	    	for(int i = left; i <= right; i++){
	    		res.add(matrix[up][i]);
	    	}
	    	
	    	for(int i = up+1;  i <= down; i++){
	    		res.add(matrix[i][right]);
	    	}
	    	
	    	for(int i = right-1; i >= left; i--){
	    		if(up != down){
		    		res.add(matrix[down][i]);
	    		}
	    	}
	    	
	    	for(int i = down-1; i > up; i--){
	    		if(left != right){
		    		res.add(matrix[i][left]);
	    		}
	    	}
	    	
	    	left++;
	    	up++;
	    	right--;
	    	down--;
    	}
    	
    	return res;
    	
    }

	
	//Runtime: 1052 ms, wow, I love it. I pass one of the most difficult question in leetcode, Yeah, that's the best
	//gift for my birthday. Happy!  I use GraphNode + BFS + DFS 
	class GraphNode{
		String label;
		List<GraphNode> nexts;
		GraphNode(String label){
			this.label = label;
			nexts = new ArrayList();
		}
	}
	
	@Test
	public void testfindLadders(){
		Set<String> dict = new HashSet();
		String start = new String("hit");
		String last = new String("cog");
		dict.add("hot");
		dict.add("dot");
		dict.add("lot");
		dict.add("dog");
		dict.add("log");
		
		List<List<String>> res = findLadders(start, last, dict);
	}
	
	
	public List<List<String>> findLadders(String start, String last, Set<String> dict){
        if(start.length() == 0 || last.length() ==0 || start.length() != last.length() || dict.size() == 0){
          return new ArrayList();
      }
      
      // if(dict.size()> 50){
      //     return new ArrayList();
      // } 
  	
  	Deque<String> queue = new ArrayDeque();
  	HashSet<String> visited = new HashSet();
  	
  	int currLevel = 1;
  	int nextLevel = 0;
  	
  	queue.offer(start);
  	
  	int depth  = 1;
  	HashMap<String, GraphNode> map = new HashMap();
  	GraphNode newhead = new GraphNode(start); 
  	map.put(start, newhead);

  	boolean lastlevel = false;
      HashSet<String> passport = new HashSet();
  
  
  	while(queue.peek()!= null){
  		
  		String str = queue.poll();
  		GraphNode currNode = map.get(str);
  		currLevel--;
  		boolean hitlast = false;
  		for(int i =0; i < str.length() && !hitlast; i++){
  			char[] A = str.toCharArray();
  			for(char c = 'a'; c <= 'z'; c++){
  				A[i] = c;
  				String tmp = new String(A);
  				
  				
  				if(dict.contains(tmp) && (!visited.contains(tmp) || passport.contains(tmp))){
  				    if(!visited.contains(tmp)){
  					GraphNode newNode = new GraphNode(tmp);
  					map.put(tmp, newNode);
  					currNode.nexts.add(newNode);
  					
  					queue.offer(tmp);
  					visited.add(tmp);
  					passport.add(tmp);
  					nextLevel++;
  				    }else{
  				        GraphNode visitednode = map.get(tmp);
  				        currNode.nexts.add(visitednode);
  				    }
  				}
  				
  				if(tmp.equals(last)){
  					GraphNode newNode = new GraphNode(tmp);
  					map.put(tmp, newNode);
  					currNode.nexts = new ArrayList();
  					currNode.nexts.add(newNode);
  					hitlast = true;
  					
  					if(lastlevel == false){
  						depth++;
  					}
  					lastlevel = true;
  					break;
  				}
  				
  			}
  		}
  		
  		if(currLevel == 0){
  			if(lastlevel == true){
  				break;
  			}
  			passport.clear();
  			currLevel = nextLevel;
  			nextLevel = 0;
  			depth++;
  		}
  	}
  	
  	List<String> sol = new ArrayList();
  	List<List<String>> res = new ArrayList();
  	dfs(newhead, depth, sol, res, last);
  	
  	return res;
  }
    
    
    
    @Test
    public void testdfs(){
    	
    	GraphNode head = new GraphNode("hit");
    	GraphNode n1 = new GraphNode("hot");
    	GraphNode n2 = new GraphNode("dot");
    	GraphNode n3 = new GraphNode("lot");
    	GraphNode n4 = new GraphNode("dog");
    	GraphNode n5 = new GraphNode("log");
    	GraphNode tail = new GraphNode("cog");
    	
    	head.nexts.add(n1);
    	n1.nexts.add(n2);
    	n1.nexts.add(n3);
    	n2.nexts.add(n4);
    	n3.nexts.add(n5);
    	n4.nexts.add(tail);
    	n5.nexts.add(tail);
    	
    	List<String> sol = new ArrayList();
    	List<List<String>> res = new ArrayList();
    	
    	dfs(head, 5, sol, res, "cog");
    	
    }
    
    
    public void dfs(GraphNode head, int depth, List<String> sol, List<List<String>> res, String last){
    	if(depth == 1 && !head.label.equals(last)){
    		return;
    	}
    	
    	if(depth == 1){
    		sol.add(head.label);
    		List<String> tmp = new ArrayList(sol);
    		res.add(tmp);
    		sol.remove(sol.size()-1);
    		return;
    	}
    	
    	for(GraphNode node: head.nexts){
    		sol.add(head.label);
    		dfs(node, depth-1, sol, res,last);
        	sol.remove(sol.size()-1);
    	}
    }
    
	
	
	@Test
	public void testsimplifyPath(){
		String s = new String("/.../");
		System.out.println(simplifyPath(s));
	}
	
	
	public String simplifyPath(String A) {
       LinkedList<String> stack = new LinkedList();
       
       int n = A.length();
       
       
       StringBuffer buf = new StringBuffer();
       //buf.append('/');
       for(int i =0; i < n; i++){
    	   char c = A.charAt(i);
    	   
    	   if(c == '/'){
    		   
    		   for(; i < n && A.charAt(i) == '/'; i++);
    		   i--;
    		   
    		   String tmp = buf.toString();
    		   if(tmp.equals("/..") ){
    			   if(stack.size() != 0)
    			   stack.pop();
    		   }else if( !tmp.equals("/.")){
    			   stack.push(buf.toString());
    		   }
    		   buf = new StringBuffer();
    		   buf.append('/');
    	   }else{
    		   buf.append(A.charAt(i));
    	   }
       }
       
       
       String tmp = buf.toString();
       if(tmp.equals("/..") ){
    	   if(stack.size() != 0)
    		   stack.pop();
       }else if(!tmp.equals("/.")){
    	   stack.push(tmp);
       }
       
       
       buf = new StringBuffer();
       Iterator it = stack.descendingIterator();//do a reverse, you know, it is magic.
       while(it.hasNext()){
    	   String s = (String) it.next();
    	   buf.append(s);
       }
       
       
       if(buf.length() ==0){
    	   buf.append('/');
       }else if(buf.length() > 1 && buf.charAt(buf.length() -1) == '/'){
    	   buf.deleteCharAt(buf.length()-1);
       }
       return buf.toString();
    }
	
	
	
	
	
	@Test
	public void test(){
		int a = (int) Math.pow(2,16);
		System.out.println(a);
		int b = 1;
		b = b << 3;//  1 0000 0000 0000 0000 17 bit   1000 = 2^3   0-16bit
		System.out.println(b);
	}
	
	@Test
	public void testSingleNumber1(){
		int[] A = new int[]{-1,-1,-1,5};
		System.out.println(singleNumber1(A));
	}
	
	public int singleNumber1(int[] A) {
        
        int[] bit = new int[32];
        
        
        for(int i =0; i < A.length; i++){
            int tmp = A[i];
            for(int k = 0; k < 32; k++){
               int rotated = tmp >> k;
               if(rotated == 0) break;
               bit[k] += rotated & 1;
            }
        }
        
        int res = 0;
        for(int i =0; i < 32; i++){
            res += bit[i] %3 << i;
        }
        return res;
    }
	
	@Test
	public void testbitmanu(){
		int a = 4;//100
		System.out.println(a >> 1);
		int b = 32;
		System.out.println(4&1);
		
		
	}
	
	
	public int singleNumber(int[] A) {
        
        int res = 0;
        for(int i =0; i < A.length; i++){
            res ^= A[i];            
        }
        return res;
    }
	
	
	@Test
	public void testdivide(){
		int dividend = 100;
		int divisor = -4;
		int res = dividend/divisor;
		
		System.out.println(dividend + " / " + divisor + " = " + res);
		
		
	}
	
	public int divide(int dividend, int divisor) {
        //8 / 2 = 4,  8 = 1000   2 = 10   
        int shift = 1;
        int absdividend = Math.abs(dividend);
        int absdivisor = Math.abs(divisor);
        
        int tmp = absdividend;
        while(tmp >= absdivisor){
            tmp = tmp >> shift;
            shift++;
        }
        
        int remain = dividend - 1 << (shift-1);
        
        int count = 0;
        while(remain >= absdivisor){
            remain -= absdivisor;
            count ++;
        }
        int res = count + 1 << (shift-1);
        return (dividend^divisor >>> 31) == 0 ? res : -res;      
    }
	
		@Test
		public void testbitshift(){
			int a = 1 << 0;//1
			int b = 1 << 1;//10
			int c = 1 << 2;//100
			System.out.println(a + "," + b +"," + c);
			int d = 0 << 2;//000
			System.out.println(d);
			
		}
	
	  @Test
	  public void testchar(){
		  char a = '2' - 2;
		  char b = '3' - 2;
		  System.out.println(a + " " + b);
	  }
	  
	  @Test
	  public void testaddBinary(){
		  String a = new String("1");
		  String b = new String("1");
		  String res = addBinary(a,b);
		  System.out.println(res);
		  
	  }
		
	
	  public String addBinary(String a, String b) {
	        char[] A = a.toCharArray();
	        char[] B = b.toCharArray();
	        
	        int m = A.length;
	        int n = B.length;
	        int pA = m-1;
	        int pB = n-1;
	        LinkedList<Character> stack = new LinkedList();
	        char carrier = '0';
	        for(;pA >= 0 || pB >= 0;){
	            if(pA >= 0 && pB >= 0){
	                char c = add(A[pA--], B[pB--]);
	                c = add(c,carrier);
	                if(c >= '2'){
	                    c -= 2;
	                    carrier = '1';
	                }else{
	                    carrier = '0';
	                }
	               
	                stack.push(c); 
	            }else if(pA >= 0){
	                char c = add(A[pA--], carrier);
	                if( c >='2'){
	                    c -= 2;
	                    carrier = '1';
	                }else{
	                    carrier = '0';
	                }
	                stack.push(c);
	            }else{
	                char c = add(B[pB--], carrier);
	                if( c >= '2'){
	                    c -= 2;
	                    carrier = '1';
	                }else{
	                    carrier = '0';
	                }
	                stack.push(c);
	            }
	        }
	        if(carrier == '1'){
	        	stack.push(carrier);
	        }
	        StringBuffer buf = new StringBuffer();
	        Iterator it = stack.iterator();
	        while(it.hasNext()){
	            buf.append((char)it.next());
	        }
	        return buf.toString();        
	        
	    }
	    
	    public char add(char a, char b){
	        if(a == '2' && b =='1'){
	            return '3';
	        }else if(a == '2' && b == '0'){
	        	return '2';
	        }
	        else if( a== '1' && b == '1'){
	            return '2';
	        }else if(a == '1' || b =='1'){
	            return '1';
	        }else{
	            return '0';
	        }
	    }
	    
	    
}
