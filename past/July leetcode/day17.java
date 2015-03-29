package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class day17 {
	
	
	public ListNode sortList(ListNode head) {
		if(head == null || head.next == null)
			return head;
		
		if(head.next.next == null){
			if(head.val > head.next.val){
				ListNode newhead = head.next;
				head.next =null;
				newhead.next = head;
				return newhead;
			}
			return head;
		}
		
		int len = 0;
		ListNode trav = head;
		for(;trav != null; trav = trav.next)  
			len++;
		
		ListNode newhead = head;
		ListNode tail = head;
		for(int i = 0; i < len/2; i++){
			tail = newhead;
			newhead = newhead.next;
		}
		tail.next = null;
		
		ListNode A = sortList(head);
		ListNode B = sortList(newhead);
	
		return mergelist(A, B);
	}
	
	public ListNode mergelist(ListNode A, ListNode B){
		ListNode head = null;
		ListNode tail = null;
		ListNode newnode = null;
		while(A != null || B != null){
			if( A != null && B != null){
				if(A.val < B.val){
					newnode = A;
					A = A.next;
					newnode.next = null;
				}else{
					newnode = B;
					B = B.next;
					newnode.next = null;
				}
			}else if(  A != null){
				newnode = A;
				A = A.next;
				newnode.next = null;
			}else{
				newnode = B;
				B = B.next;
				newnode.next = null;
			}
			
			if(head == null){
				head = tail = newnode;
			}else{
				tail.next = newnode;
				tail = tail.next;
			}
			
		}
		
		return head;
		
		
		
	}
	
	
	
	public ListNode mergeKLists(List<ListNode> lists) {
		if(lists.size() == 0){
			return null;
		}
		
		if(lists.size() == 1){
			return lists.get(0);
		}
		
		if(lists.size() == 2){
			return merge(lists.get(0), lists.get(1));
		}
		
		int len  = lists.size();
		List<ListNode> Alist = new ArrayList();
		
		List<ListNode> Blist = new ArrayList();
		
		for(int i = 0; i < len; i++){
			if(i < len/2){
				Alist.add(lists.get(i));
			}else{
				Blist.add(lists.get(i));
			}
		}
		
		ListNode A = mergeKLists(Alist);
		ListNode B = mergeKLists(Blist);
		
		List<ListNode> res = new ArrayList();
		res.add(A);
		res.add(B);
		return mergeKLists(res);
		
		
	}
	
	public ListNode merge(ListNode A, ListNode B){
		ListNode head = null;
		ListNode tail = null;
		ListNode newnode = null;
		
		if(A != null ||  B != null ){
			if( A == null && B == null){
				if(A.val < B.val){
					newnode = A;
					A = A.next;
					newnode.next = null;
				}else{
					newnode = B;
					B = B.next;
					newnode.next = null;
				}
			}else if(A != null){
				newnode = A;
				A = A.next;
				newnode.next = null;
			}else{
				newnode = B;
				B = B.next;
				newnode.next = null;
			}
			
			if(head == null){
				head = tail = newnode;
			}else{
				tail.next = newnode;
				tail = tail.next;
			}
		}
		return head;
		
		
		
		
		
	}
	

	
	
	
	
	
	public class LRUCache {
		class DoubleListNode{
			int key;
			int val;
			DoubleListNode prev,next;
			DoubleListNode(int key, int val){
				this.key = key;
				this.val = val;
			}
		}
		//use key to find value in map.
		HashMap<Integer, DoubleListNode> map = new HashMap();
		int capacity;
		DoubleListNode head;
		DoubleListNode tail;
		int count = 0;
		LRUCache(int capacity) {
			this.capacity = capacity;
		}

		public int get(int key) {
			if(!map.containsKey(key)){
				return -1;
			}else{
				DoubleListNode curr = map.get(key);
				
				if(capacity == 1){
					return curr.val;
				}else{
					if(curr == head)
						return curr.val;
					if(curr == tail){
						curr.prev.next = null;
						tail = curr.prev;
						
						curr.next = head;
						head.prev = curr;
						head = curr;
						head.prev = null;
						
						return curr.val;
					}
					
					curr.prev.next = curr.next;
					curr.next.prev = curr.prev;
					
					curr.next  = head;
					head.prev = curr;
					head = curr;
					head.prev = null;
					return curr.val;
				}
			}
		}

		//大 big bug， forget to set curr.prev = null. 
		void set(int key, int value) {
			if(map.containsKey(key)){
				DoubleListNode curr = map.get(key);
				curr.val = value;
				if(capacity == 1){
					return;
				}else{
					if(curr == head)
						return;
					if(curr == tail){
						tail = curr.prev;
						tail.next = null;
						
						curr.next = head;
						head.prev = curr;
						head = curr;
						head.prev = null;
					}else{
						curr.next.prev = curr.prev;
						curr.prev.next = curr.next;
						
						curr.next = head;
						head.prev = curr;
						
						head = curr;
						head.prev = null;
					}
				}
			}else{
				count ++;
				if(count > capacity){
					count = capacity;
					
					//remove one from tail
					map.remove(tail.key);
					
					tail = tail.prev;
					tail.next = null;
					
				}
				//insert one
				DoubleListNode newnode = new DoubleListNode(key,value);
				map.put(key, newnode);
				newnode.next = head;
				head.prev = newnode;
				head.prev = null;
				
			}
			
		}
	}	  
	
	
	@Test
	public void testArea(){
		int[] height = new int[]{1,1};
		int max = maxArea(height);
		System.out.println(max);
	}
	
	
	public int maxArea(int[] height) {
        int n = height.length;
        
        int left = 0;
        int right = n-1;
        int max = 0;
        
        while(left < right){
            int min = Math.min(left, right);
            int curArea = (right - left) * min;
            max = Math.max(curArea, max);
            
            if(min == left){
                left++;
            }else{
                right--;
            }                          
        }
        return max;
    }
	
	
	
//	@Test
//	public void testmax1(){
//		int[] height = new int[]{3,2,1,3};
//		int max = maxArea(height);
//		System.out.println(max);
//	}
//	
//	
//	@Test
//	public void testmaxArea(){
//		int[] height = new int[]{2,1,5,5,6,2};
//		int max = maxArea(height);
//		System.out.println(max);
//		
//	}
//	
//	public int maxArea(int[] height) {
//        LinkedList<Integer> stack = new LinkedList();
//        
//        int n = height.length;
//        int max = 0;
//        for(int i = 0; i < n; i++){
//            while(stack.peek()!=null && height[stack.peek()] > height[i]){
//                int index = stack.pop();
//                int curArea = stack.peek() == null ? (i-1) * height[index] : (i - 1 - (stack.peek()+1)) * height[index];
//                max = Math.max(curArea, max);
//            }
//            stack.push(i);
//        }
//        
//        while(stack.peek()!= null){
//            int index = stack.pop();
//            int curArea = stack.peek() == null ? (n-1) * height[index] : (n-1 - (stack.peek() + 1))* height[index]; 
//            max = Math.max(curArea, max);
//        }
//            
//        return max;    
//    }
	

	
	@Test
	public void testwordladders(){
		String start = "hit";
		String last = "cog";
		Set<String> dict = new HashSet();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("log");
		dict.add("lot");
		
		findLadders(start, last, dict);
	}
	
	
	
	class GraphNode{
        String label;
        List<GraphNode> nexts;
        GraphNode(String label){
            this.label = label;
            nexts = new ArrayList();
        }
    }
    
    public List<List<String>> findLadders(String start, String last, Set<String> dict){
                
        Deque<String> queue = new ArrayDeque();
        HashSet<String> visited = new HashSet();
        GraphNode newhead = new GraphNode(start);
    
        queue.offer(start);

        HashMap<String, GraphNode> map = new HashMap();
        map.put(start, newhead);
        GraphNode newtail = new GraphNode(last);
        
        int depth = 1;
        int curLevel = 1;
        int nextLevel = 0;
        
        HashSet<String> passport = new HashSet();
        
        while(queue.peek()!=null){
            String str = queue.poll();   
            GraphNode currnode = map.get(str);
            
            curLevel --;
            boolean lastlevel = false;
            
            for(int i = 0; i < str.length(); i++){
                char[] chars =  str.toCharArray();
                for(char c = 'a';  c <= 'z'; c++){
                    chars[i] = c;
                    String tmp = String.copyValueOf(chars);
                    if(tmp.equals(last)){
                        depth++;
                        lastlevel = true;
                        currnode.nexts = new ArrayList();
                        currnode.nexts.add(newtail);
                        break;
                    }
                    
                    if( dict.contains(tmp) && (!visited.contains(tmp) || passport.contains(tmp))){
                        passport.add(tmp);
                        if(!visited.contains(tmp)){
                            visited.add(tmp);
                            GraphNode node = new GraphNode(tmp);
                            currnode.nexts.add(node);
                        }
                        queue.offer(tmp);
                        nextLevel++;
                    }
                }
            }
            
            if(curLevel == 0){
                if(lastlevel ){
                    break;
                }
                passport.clear();
                curLevel = nextLevel;
                nextLevel = 0;
                depth ++;
            }
        }
        
        
        List<List<String>> res = new ArrayList();
        List<String> sol = new ArrayList();
        worddfs(depth, newhead, sol, res,last);
        return res;
    }
    
    public void worddfs(int depth, GraphNode head,  List<String> sol, List<List<String>> res, String last){
        if(depth == 1 && head.label.equals(last)){
        	sol.add(head.label);
            List<String> tmp = new ArrayList(sol);
            res.add(tmp);
            sol.remove(sol.size()-1);
            return;
        }
        
        for(GraphNode node : head.nexts){
            sol.add(node.label);
            worddfs(depth-1, node, sol, res, last);
            sol.remove(sol.size()-1);
        }
    }

    
    
    
	
	
	 public int ladderLength(String start, String last, Set<String> dict) {
		 if(start == null || last == null || start.length() != last.length() || start.length() ==0)
			 return 0;
         
         HashSet<String> visited = new HashSet();
         Deque<String> queue = new ArrayDeque();
         
         int curLevel = 1;
         int nextLevel = 0;
         int depth = 1;
         
         while(queue.peek()!= null){
             String curr = queue.poll();
             curLevel--;
             
             for(int i= 0; i < curr.length(); i++){
            	 char[] chars = curr.toCharArray();
            	 for(char c = 'a'; c <= 'z'; c++){
            		 chars[i] = c;
            		 String tmp = new String(chars);
            		 if(tmp.equals(last)){
            			 return depth + 1;
            		 }
            		 if(dict.contains(tmp) && !visited.contains(tmp)){
            			 visited.add(tmp);
            			 queue.offer(tmp);
            			 curLevel ++;
            		 }
            	 }
             } 
             
             if(curLevel == 0){
            	 curLevel = nextLevel;
            	 nextLevel = 0;
            	 depth++;
             }
         }
		return 0;
	 }
	
	
	public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int count = 1;
        int left = 0;
        int right = n-1;
        int up = 0;
        int down = n - 1;
        
        while(left <= right && up <= down){
            
            for(int i = left; i <= right; i++){
                res[up][i] = count++;
            }
            
            for(int i = up+1; i <= down; i++){
                res[i][right] = count++;
            }
            
            for(int i = right-1; i >= left; i--){
                res[down][i] = count++;
            }
            
            for(int i = down-1; i > up; i--){
                res[i][left] = count++;
            }
            
            left++;
            up++;
            right--;
            down--;
        }
        return res;
    }
	
	
	
	
	
	
	
	public List<Integer> spiralOrder(int[][] matrix){
	      int m = matrix.length;
	      if( m == 0)
	          return new ArrayList();
	      int n = matrix[0].length;
	      
	      int left = 0;
	      int right = n-1;
	      int up = 0;
	      int down = m-1;
	      
	      List<Integer> res = new ArrayList();
	      
	      while(left <= right && up <= down){
	          
	          for(int i = left; i <= right; i++){
	              res.add( matrix[up][i] );              
	          }                
	            
	          for(int i = up+1; i <= down; i++){
	              res.add( matrix[i][right]);
	          }   
	          
	          for(int i = right-1; i >= left; i--){
	              if(up != down)
	              res.add(matrix[down][i]);
	          }
	          
	          for(int i = down-1; i > up; i--){
	              if(left != right)
	              res.add(matrix[i][left]);
	          }
	          
	          
	          left++;
	          up++;
	          right--;
	          down--;
	          
	      }
	      
	      return res;
	   }
	
	
	/**
	 * @author Xiaomin
	 * @param matrix
	 */

	public void setZeroes(int[][] matrix){
		int m = matrix.length;
		int n = matrix[0].length;
		
		boolean firstrow = false;
		boolean firstcol = false;
		
		for(int i = 0; i < n ; i++){
			if(matrix[0][i] == 0){
				firstrow = true;
//				matrix[0][i] = 1;
			}
		}
		
		for(int i = 0; i < m ; i++){
			if(matrix[i][0] == 0){
				firstcol = true;
//				matrix[i][0] = 1;
			}
		}

		for(int i = 1; i < m ; i++){
			for(int j = 1; j < n; j++){
				if(matrix[i][j] == 0){
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		
		
		for(int i = 1; i < n; i++){
			if(matrix[0][i] == 0){
				for(int j =0; j < m ; j++){
					matrix[j][i] = 0;
				}
			}
		}
		
		for(int i = 1; i < m ; i++){
			if(matrix[i][0] == 0){
				for(int j = 0; j < n; j++){
					matrix[i][j] = 0;
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
	
	
	
	

	public void setZeroes1(int[][] matrix) {
	      Deque<Integer> idx = new ArrayDeque();
	      Deque<Integer> idy = new ArrayDeque();
	      
	      int m = matrix.length;
	      int n = matrix[0].length;
	      
	      for(int i = 0; i < m ; i++){
	          for(int j = 0; j < n; j++){
	              if(matrix[i][j] == 0){
	                  idx.offer(i);
	                  idy.offer(j);
	              }
	          }
	      }
	      
	      while(idx.peek()!= null){
	        int x = idx.poll();
	        int y = idy.poll();
	        
	        for(int i= 0;i < n; i++){
	            matrix[x][i] = 0;
	        }
	        
	        for(int i = 0; i < m; i++){
	            matrix[i][y] = 0;
	        }
	          
	      }       
	      
	      
	    }  
	
	
	
	@Test
	public void testrotate(){
		int[][] matrix = new int[4][4];
		int count = 1;
		for(int i = 0; i < 4; i++){
			for(int j = 0;j < 4; j++){
				matrix[i][j] = count;
				count++;
			}
		}
		rotate(matrix);
		
	}
	
	
	/**
	 * 
	 * @param matrix
	 *  Input:	[[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]]
		Output:	[[13,14,13,1],[10,6,9,2],[11,7,5,3],[16,12,8,4]]
		Expected:	[[13,9,5,1],[14,10,6,2],[15,11,7,3],[16,12,8,4]]

	 */
	public void rotate(int[][] matrix) {
        int n = matrix.length;
        
        int left = 0;
        int right = n-1;
        int up = 0;
        int down = n-1;
        
        while(left < right && up < down){
            int[] buf = new int[right - left+1];
            
            for(int i = up,k=0; i <= down; i++,k++){
                buf[k] = matrix[k][0];
            }
            for(int i = up; i <= down; i++){
                matrix[i][0] = matrix[down][i];                
            }
            
            for(int i = left; i <= right; i++){
                matrix[down][i] = matrix[down-i][right];
            }    
            
            for(int i = down; i >= up; i--){
                matrix[i][right] = matrix[0][i];
            }
            
            for(int i = right, k = 0; i>= left; i--,k++){
                matrix[0][i] = buf[k];
            }
            
            left++;
            right--;
            up++;
            down--;
        }
    }
	 
	 
	
	
	public List<List<Integer>> fourSum(int[] A, int target){
        List<List<Integer>> res = new ArrayList();
       
        Arrays.sort(A);//must sort A
        int n = A.length;
        
        for(int i = 0; i < n; i++){
            for(int j = i + 1 ; j < n; j++){
                int start = j + 1;
                int last = n - 1;
                for(;start < last;){
                    int sum = A[i] + A[j] + A[start] + A[last];
                    int diff = sum - target;
                    if(diff == 0){
                         List<Integer> sol = new ArrayList();
                         sol.add(A[i]);
                         sol.add(A[j]);
                         sol.add(A[start]);
                         sol.add(A[last]);
                         if(!res.contains(sol))
                            res.add(sol);
                        start++;
                        last--;
                    }else if(diff > 0){// can we use binary search here?
                        last--;
                    }else{
                        start++;
                    }
                }
            }
        }
        
        return res;
    }
	
	
	
	
	
	
	
	
	
	
	
	 @Test
	 public void testpermuteUnique(){
		 int[] A = new int[]{1,1,2};
		 List<List<Integer>> res = permuteUnique(A);
		 System.out.println(res);
	 }
	
	
	 public List<List<Integer>> permuteUnique(int[] A){
		    List<List<Integer>> res = new ArrayList();
		    List<Integer> sol = new ArrayList();
		    boolean[] used = new boolean[A.length];
		    permuteUniquedfs(A.length, A, used, sol, res);
		    return res;
	    }
	    
	 public void permuteUniquedfs(int count, int[] A, boolean[] used, List<Integer> sol, List<List<Integer>> res){
	        if(count == 0){
	            List<Integer> tmp = new ArrayList(sol);
	            res.add(tmp);
	            return;
	        }
	        
	        for(int i = 0; i < A.length; i++){
	            if(used[i]){
	                continue;
	            }
	            
	            if(i > 0 && A[i] == A[i-1] && !used[i-1])
	                continue;
	                
	            used[i] = true;
	            sol.add(A[i]);
	            permuteUniquedfs(count -1, A, used, sol, res);
	            sol.remove(sol.size() - 1);
	            used[i] = false;
	        }        
	    }   
	    
	    
	    
	
	
	
	
	@Test
	public void testpermute(){
		
		int[] A= new int[]{1,2,3};
		List<List<Integer>> res = permute(A);
		System.out.println(res);
	}
	
	
	public List<List<Integer>> permute(int[] A) {
		List<List<Integer>> res = new ArrayList();
		List<Integer> sol = new ArrayList();
		int n = A.length;
		boolean[] used = new boolean[n];
		permutedfs( A.length, A, used, sol, res);
		return res;
	}
	
	
	
	void permutedfs( int count, int[] A, boolean[] used, List<Integer> sol, List<List<Integer>> res){
	    if(count == 0){
	        List<Integer> tmp = new ArrayList(sol);
	        res.add(tmp);
	        return;
	    }
	    
	    for(int i = 0; i < A.length; i++){
	        if(used[i]) 
	            continue;
	        
	        sol.add(A[i]);
	        used[i] = true;
	        permutedfs( count-1, A, used, sol, res);//add start, it is increasing recursive, 
	        used[i] = false;
	        sol.remove(sol.size()-1);
	    }
	}
	
	
	
	
	public String getPermutation(int n, int k){

	    List<Integer> list = new ArrayList();
	    for(int i = 1; i <= n; i++){
	        list.add(i);
	    }
	    int index = k - 1;
        StringBuffer res = new StringBuffer();
        for(int i = n-1; i >= 0; i--){
            int fac = factorial(i);
            int curr =index / fac;//because it is kth  
            index = index % fac;
            res.append(list.get(curr));
            list.remove(curr);
        }
        return res.toString();
	}
	
    public int factorial(int n){
        int res = 1;
        for(int i = n; i >= 1; i--){
            res *= i;
        }
        return res;
    }
    
    
    
	
	
	 @Test
	 public void testnextPermutation(){
		 int[] A= new int[]{1,2,3,4};
		 nextPermutation(A);
		 for(int i =0; i < A.length; i++){
			 System.out.print(A[i] +",");
		 }
		 
	 }
	
	
	 public void nextPermutation(int[] A) {
			int target = -1;
			int firstlarge = -1;
			
			int n = A.length;
			for(int i = n - 2; i >= 0; i--){
			    if(A[i] < A[i+1]){
			        target = i;
			        break;
			    }
			}
			
			if(target == -1){
			    Arrays.sort(A);
			    return;
			}
			
			for(int i = n-1; i >= 0; i--){
			    if(A[i] > A[target]){
			        firstlarge = i;
			    }
			}
			
			int tmp = A[target];
			A[target] = A[firstlarge];
			A[firstlarge] = tmp;
			
			Arrays.sort(A, target+1, A.length);
	   }
	
	
	
	
	
	@Test
	public void testtotalNQueens(){
		int res = totalNQueens(4);
		System.out.println(res);
		
	}
	
	
	 public int totalNQueens(int n) {
	        int[] res = new int[n];
	        String[] sol = new String[n];        
	        totalNQueensdfs(0, n, sol, res);
	        return res[0];
	   }
	   
	   void totalNQueensdfs(int curRow, int n, String[] sol, int[] res){
	       if(curRow == n){
	           res[0] ++;
	           return;
	       }
	       
	       for(int col = 0; col < n ; col++){
	           if(curRow > 0 && check(curRow, col, sol) == false){
	               continue;
	           }
	           
	           char[] chars = new char[n];
	           Arrays.fill(chars, '.');
	           chars[col] = 'Q';
	           sol[curRow] = String.copyValueOf(chars);
	           totalNQueensdfs(curRow + 1, n , sol, res);
	       }
	       
	   }
	   
	    public boolean check(int curRow, int col, String[] sol){
	        
	        for(int i = 0; i < curRow; i++){
	            
	            int Qcol = sol[i].indexOf('Q');
	            if(Qcol == col)
	                return false;
	            
	            int diffcol = Math.abs(Qcol - col);
	            int diffrow = Math.abs(curRow - i);
	            if(diffcol == diffrow)
	                return false;
	            
	        }        
	        return true;        
	        
	    }   
	
	
	
	
	@Test
	public void testsolveNQueens(){
		List<String[]> res = solveNQueens(4);
		System.out.println(res);
		
	}
	
	
	public List<String[]> solveNQueens(int n){
        List<String[]> res = new ArrayList();
        String[] sol = new String[n];
        solveNQueensdfs(0, n, sol, res);
        return res;
    }
    
    void solveNQueensdfs(int curRow, int n, String[] sol, List<String[]> res){
        if(curRow == n){  //2.when we have n rows, put this in rows
            res.add(sol.clone());
            return;
        }
        
        for(int col = 0; col < n; col++){
            if( curRow > 0 ){//1.check queens are in valid position, curRow > 0,
            	if( check(curRow, col, sol) ==false )
            		continue;
            }
            	
            	
            char[] tmp = new char[n];
            Arrays.fill(tmp, '.');
            tmp[col] = 'Q';
            sol[curRow] = String.copyValueOf(tmp);
            solveNQueensdfs(curRow+1, n, sol, res);
            
          
        }
    }
    
    public boolean check1(int curRow, int col, String[] sol){
        
        
        for(int i = 0; i < curRow; i++){
            int Qcol = sol[i].indexOf('Q');//check col
            
            if(Qcol == col)
                return false;
            
            int diffcol = Math.abs(Qcol - col);
            int diffrow = Math.abs(curRow - i);
            
            if(diffcol == diffrow)//check triangle line
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
	        
	        List<Integer> sol =new ArrayList();
	        List<List<Integer>> res = new ArrayList();
	        boolean[] used = new boolean[n +1]; //just for match the 1 --- n
	        
	        combinedfs(0, 0, k, n, used, sol, res);
	        return res;
	    }
	    	
	    void combinedfs(int start, int last, int count, int n, boolean[] used, List<Integer> sol, List<List<Integer>> res){
	        //1.limited the count num to k
	        if(count == 0){
	            List<Integer> tmp = new ArrayList(sol);
	            res.add(tmp);
	            return;
	        }
	        
	        for(int i = 1; i <= n ; i++){
	            if(i < last)   //2. keep ascending order by compare it with lastest num that we touch
	                continue;
	            if(used[i])    //3. keep every number use only once.
	                continue;
	            
	            int curcount = count - 1;
	            if(count >= 0){
	                used[i] = true;
	                last = i;
	                sol.add(i);  
	                combinedfs(i + 1, last, curcount, n, used, sol, res);
	                sol.remove(sol.size() - 1);
	                used[i] = false;
	            }else{
	                return;
	            }
	            
	        }
	    } 
	    
	    
	    
	    
	
	
	@Test
	public void testcombinations(){
		List<List<Integer>> res = combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
		System.out.println(res);
	}
	
	public List<List<Integer>> combinationSum2(int[] A, int target) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> sol = new ArrayList();
        
        int n = A.length;
        Arrays.sort(A);//1. must sort
        boolean[] used =new boolean[n];
        combinationSum2dfs(0, 0, target, used, A, sol ,res);
        return res;
    }
    
    
     void combinationSum2dfs(int start, int last, int target, boolean[] used, int[] A, List<Integer> sol, List<List<Integer>> res){
        if(target == 0){
            List<Integer> tmp = new ArrayList(sol);//7. when add to collection, it only add pointers but not clone.deep copy
            res.add(tmp);
            return;
        }
        
        for(int i = 0; i < A.length; i++){
            int curtarget = target -  A[i];//2. use curtarget to make sure fit in every recursive call 
            if(curtarget >= 0){
            	if(A[i] < last)
            		continue;
                //9.deal with duplicate condition
                if(i > 0 && A[i] == A[i-1] && !used[i-1] ){
                    continue;//3. if two number are same, the later one can use only in the former is use. otherwise is duplicate
                }
                
                //8.can only use at once
                if(used[i])  
                	continue;
                
                last = A[i];
                used[i] = true;//4. mark the number is used
                sol.add(A[i]);   // 5. add to the solution
                combinationSum2dfs(i+1, last, curtarget, used, A, sol, res);
                sol.remove(sol.size() - 1);
                used[i] = false;
                
            }else{
                return ;//if target < 0 it is not possible. return;
            }
        }
            
     }
     
     
     
     
     
     
	
	
	@Test
	public void testcombinationSum(){
		List<List<Integer>> res = combinationSum(new int[]{2,3,6,7}, 7);
		System.out.println(res);
		
		
	}
	
	public List<List<Integer>> combinationSum(int[] A, int target) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> sol = new ArrayList();
        
        Arrays.sort(A);
        dfs(0, A, target, sol ,res );
        
		return res;
   }
   
   void dfs(int start, int[] A, int target, List<Integer> sol, List<List<Integer>> res){
        if(target == 0){
            List<Integer> tmp = new ArrayList(sol);
            res.add(tmp);
            return;
        }
        
        for(int i = start; i < A.length; i++){
            int curtarget = target - A[i];
            if(curtarget >= 0){
                sol.add(A[i]);
                dfs(i, A, curtarget, sol, res);
                sol.remove(sol.size() - 1);
            }else{
                return;
            }        
            
        }
    }
   
   
   
	
	
	
	@Test
	public void testanagrams(){
		String[] A = new String[]{"abc", "bac"};
		List<String> res = anagrams(A);
		System.out.println(res);
	}
	
	
	
	//we can add the collections.
	public List<String> anagrams(String[] A){
		List<String> res = new ArrayList();
		
		if( A.length == 0)
			return res;
		
		int n = A.length;
		
		HashMap<String, List<String>> map = new HashMap();
		
		
		for(int  i = 0; i < n; i++){
			String str = A[i];
			
			char[] arr = str.toCharArray();
			
			Arrays.sort(arr);
			
			String tmp = new String(arr);
			
			if(map.containsKey(tmp)){
				map.get(tmp).add(str);
			}else{
				List<String> list = new ArrayList();
				list.add(str);
				map.put(tmp, list);
			}
		}
		
		
		Iterator it  = map.keySet().iterator();
		
		while(it.hasNext()){
			String tmp = (String) it.next();
			List<String> list = map.get(tmp);
			if(list.size() > 1){
				res.addAll(list);
			}
		}
		return res;
	}
		

	public boolean exist(char[][] board, String word){
        if(word==null || word.length()==0) 
            return false;
        if(board == null || board.length == 0)
            return false;
        
        int m = board.length;
        int n = board[0].length;
        
        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n; j++){
                if(word.charAt(0) == board[i][j]){
                    boolean[][] used = new boolean[m][n];
	                if(explore(i,j,word,0,used,board)){
	                    return true;
	                }      
                }
            }
        }
       
        return false;
 }

 public boolean explore(int x, int y, String word, int index, boolean[][] used, char[][] board){
     if(index >= word.length())
        return true;
    if(x < 0 || y < 0 || x >= board.length || y >= board[0].length || used[x][y] || board[x][y] != word.charAt(index))
        return false;
        
    used[x][y] = true;
    boolean result = explore(x-1, y, word, index + 1, used, board) ||
                explore(x + 1, y, word, index + 1, used, board) ||
                explore(x, y-1, word, index + 1, used, board) ||
                explore(x, y+1, word, index + 1, used, board);
        
    used[x][y] = false;
    
    return result;
 }
 
 
 
 
 
 
	
	
	@Test
	public void testisValid(){
		
		boolean  c = isValid("{}");
		System.out.println(c);
		
		
	}
	
	public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList();
        
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            
            if( c == ')' || c == ']' || c == '}' ){
                if(stack.size() > 0){
                    char left = stack.pop();
                    if( left == '(' && c == ')' || left == '[' && c == ']' || left == '{' &&  c== '}'){
                      
                    	continue;
                    	
                    	
                    	
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }else{
                stack.push(c); // ( [ {
            }
        }
                
        if(stack.size() == 0){
            return true;
        }else{
            return false;
        }
    }
	
	
	
	
	
	
	
	public boolean isPalindrome(String s){
        
        s = s.trim();
        s = s.toLowerCase();
        StringBuffer valid = new StringBuffer();
        
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
                valid.append(s.charAt(i));
        }
        
        return Palindrome(valid.toString());
        
    }
    
    
    
    public boolean Palindrome(String valid){
        
        for(int i = 0; i < valid.length()/2; i++){
            char first = valid.charAt(i);
            char last = valid.charAt(valid.length() - i - 1);
            if(first != last){
                    return false;
            }
        }
        return true;
    }
    
    
    
	
	
	@Test
	public void testuniquePathsWithObstacles(){
		uniquePathsWithObstacles(new int[][]{{0,0}});
		
	}
	
	
	 public int uniquePathsWithObstacles(int[][] A) {
	        int m = A.length;
	        if( m == 0 )
	            return 0;
	        int n = A[0].length;
	        
	        int[][] table = new int[m][n];
	        
	        table[0][0] = A[0][0] == 1 ? 0 : 1;
	        
	        if( table[0][0] != 0 ){
	            for(int i = 1; i < m; i++){
	              if(table[i-1][0] == 0){
	                  break;
	              }else{
	                  table[i][0] = A[i][0] == 1 ? 0 : 1;
	              }
	            }
	            
	            for(int i = 1; i < n; i++){
	                if(table[0][i-1] == 0){
	                    break;
	                }else{
	                    table[0][i] = A[0][i] == 1 ? 0 : 1;
	                }
	            }
	        }
	        
	        for(int i = 1; i < m; i++){
	            for(int j = 1; j < n; j++){
	                if(A[i][j] == 1){
	                    table[i][j] = 0;//we can skip in java because the default is 0;
	                }else{
	                    table[i][j] = table[i][j-1] + table[i-1][j];
	                }
	            }
	        }
	        
	        
	        return table[m-1][n-1];
	        
	    }
	 
	 
	 
	 
	
	
	public int uniquePaths(int m, int n){
        if(m == 0 || n == 0){
            return 0;
        }
        
        int[][] table = new int[m][n];
        
        for(int i =0; i < m; i++){
            table[i][0] = 1;
        }
        
        for(int i = 0; i < n; i++){
            table[0][i] = 1;
        }
        
        for(int i = 1; i < m ;i++){
            for(int j =1; j < n ;j ++){
                table[i][j] = table[i-1][j] + table[i][j-1];
            }
        }
        
        return table[m-1][n-1];        
        
    }
	
	
	
	
	
	public boolean isSymmetric(TreeNode root){
        if(root == null)
            return true;
        return symmetric(root.left, root.right);
    }
    
    public boolean symmetric(TreeNode t1, TreeNode t2){
        if(t1 == null || t2 == null)
            return t1 == null && t2 == null;
        
        if(t1.val != t2.val)
            return false;
        
        return symmetric(t1.left, t2.right) && symmetric(t1.right, t2.left);
    }
	
	
	
	
	
	public  int sumNumbers(TreeNode root){
	    int[] res = new int[1];
        dfs(root, 0, res);	 
        return res[0];
	}
	
	public void dfs(TreeNode root, int curr, int[] res){
        if(root == null)
            return;
        
        int newcurr = curr * 10 + root.val;
        
        if(root.left == null && root.right == null)
            res[0] += newcurr;
	    
	    dfs(root.left, newcurr, res);
	    dfs(root.right, newcurr, res);
	    
	}
	
	
	
}
