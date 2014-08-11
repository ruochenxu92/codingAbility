package leetcode;

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

	
	@Test
	public void testfindmerge(){
		int[] A = new int[10];
		
		
		
		
	}
	
	public void merge(int A[], int m, int B[], int n) {
		int i;
		int j;
		int dist = 0;

		int[] res = new int[m + n];

		for (i = 0, j = 0; i < m && j < n;) {

			if (A[i] > B[j]) {
				res[dist++] = B[j];
				j++;
			} else {
				res[dist++] = A[i];
				i++;
			}
		}
		if (i == m && j < n) {
			for (; j < n; j++) {
				res[dist++] = B[j];
			}
		} else if (j == n && i < m) {
			for (; i < m; i++) {
				res[dist++] = A[i];
			}
		}
		int length = m + n;
		for (int k = 0; k < length; k++) {
			A[k] = res[k];
		}

	}
	
	
	
	
	
	
	@Test
	public void findmedian(){
		int[] A = new int[]{1};
		int[] B = new int[]{1};
		double res = findMedianSortedArrays(A,B);
		System.out.println("res = " + res);
	}
	@Test
	public void testfindKth(){
		int[] A = new int[]{1};
		int[] B = new int[]{2,3};
		int n = findKth(A, A.length, B, B.length, 2);

		System.out.println(n);
		
		
	}
	int findKth(int[] A, int m,int[] B, int n , int k){
		if (m > n)
			return findKth(B, n, A, m, k);// make sure that m <= n
		
		int currA = A.length - m;//0
		int currB = B.length - n;//0
		if(m ==0){
		  return B[k-1];
		}
		if( k == 1){
			return A[currA] > B[currB] ? B[currB] : A[currA];
		}
		
		int pA = Math.min(k / 2, m);
		int pB = k - pA;
        
		if (A[pA+currA-1] < B[pB+currB-1]) {
			return findKth(A, m - pA, B, n, k - pA);
		} else if (A[pA+currA-1] > B[pB+currB-1]) {
			return findKth(A, m, B, n - pB, k - pB);
		} else {
			return A[pA+currA-1];
		}
	}
	public double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;
		int sum = m + n;
		if (sum % 2 == 1) {
			return findKth(A, m, B, n, (sum + 1) / 2);
		} else {
			return (findKth(A, m, B, n, sum / 2) + findKth(A, m, B, n,
					sum / 2 + 1)) / 2.0;
		}
	}
	
	
	
	
	
	
	
	
	 @Test
	 public void testsimplifyPath(){
		 String aaa = simplifyPath("/home//");
		 
	 }
	
	
	
	
	 public String simplifyPath(String s){
	        int n = s.length();
	        s = s.trim();
	        LinkedList<String> stack = new LinkedList();
	        StringBuffer sb = new StringBuffer();
	        for(int i = 0; i < n ; i++){
	            if(s.charAt(i) == '/'){
	                for(; i < n && s.charAt(i) == '/';i++);
	                i--;
	                String tmp = sb.toString();
	                if(tmp.equals("/.")){
	                    
	                }else if(tmp.equals("/..")){
	                    if(stack.size()!=0)
	                        stack.pop();
	                }else
	                    stack.push(tmp);
	                sb = new StringBuffer();
	                sb.append('/');
	            }else{
	                sb.append(s.charAt(i));
	            }
	        }        
	        
	        if(sb.length()!=0){
	            String tmp = sb.toString();
	            if(tmp.equals("/.")){
	                
	            }else if(tmp.equals("/..")){
	                if(stack.size()!=0)
	                    stack.pop();
	            }else{
	                stack.push(tmp);
	            }
	        }
	        
	        
	            
	            
	        LinkedList<String> s1 = new  LinkedList();
	        Iterator it = stack.iterator();
	        while(it.hasNext()){
	            String aaa = (String)it.next();
	            s1.push(aaa);
	        }
	            
	        StringBuffer res = new StringBuffer();
	        Iterator it2 = s1.iterator();
	        while(it2.hasNext()){
	            res.append((String)it2.next());
	        }
	        
	        
	        if(res.length() > 1 && res.charAt(res.length()-1)=='/'){
	        	res.deleteCharAt(res.length()-1);
	        }
	        
	        
	        return res.toString();
	    }
	
	
	public String reverseWords(String s) {
	    if(s == null || s.trim().equals(""))
	        return "";
	    s = s.trim();

	    int n = s.length();
	    LinkedList<String> stack = new LinkedList();
	    StringBuffer sb = new StringBuffer();
	    for(int i =0; i < n; i++){
	        char c = s.charAt(i);
	        if(c == ' '){
	            while(i < n && s.charAt(i++) == ' ');
                stack.push(sb.toString());
                sb = new StringBuffer();
	        }else{
	            sb.append(c);
	        }
	    }
	    stack.push(sb.toString());
	    
	    StringBuffer res = new StringBuffer();
	    Iterator it = stack.iterator();
	    while(it.hasNext()){
	        String tmp = (String) it.next();
	        res.append(tmp);
	        res.append(" ");
	    }
	    res.deleteCharAt(res.length()-1);
	    return res.toString();
	    
	   
    }
//	
//	public List<Interval> merge(List<Interval> intervals) {//arraylist, has all intervals
//        Arrays.sort(a, new Comparator<T>() {
//			@Override
//			public int compare(T o1, T o2) {
//				return o1.start>o2.start?1:(o1.start==o2.start?0:1);
//			}
//        	
//		});
//    }
//	
//	
//	
//	
//	
//	
//	
//	 public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
//	        int start = newInterval.start;
//	        int size = intervals.size();
//	        
//	        for(int i =0; i < intervals.size(); i++){
//	            Interval interval = intervals.get(i);
//	            if(start < intervals.start){
//	                intervals.add(i,newInterval);
//	                break;
//	            }
//	        }
//	        
//	        if(intervals.size() == size){
//	            intervals.add(newInterval);
//	        }
//	      
//	        for(int i =1; i < intervals.size(); i++){
//	            Interval prev = intervals.get(i-1);
//	            Interval curr = intervals.get(i);
//	            if(prev.end >= curr.start){
//	                prev.end = Math.max(prev.end, curr.end);
//	                intervals.remove(i);
//	                i--;
//	            }
//	            
//	        }
//	        return intervals;
//	    }
	   
	   @Test
	   public void testmaxProfit(){
		   maxProfit(new int[]{1,2});
	   }
	
	   public int maxProfit(int[] a) {
	        int n = a.length;
	        if(n <= 1)
	            return 0;
	        int[] dp = new int[n];
	        int[] dp2 = new int[n+1];
	        dp2[n] = 0;
	        int min = Integer.MAX_VALUE;
	        int max = Integer.MIN_VALUE;
	        
	        for(int i=0; i<n; i++){
	            if(a[i] < min)
	                min =a[i];
	            int tmp = a[i] - min;
	            if(tmp> max)
	                max = tmp;
	            dp[i] = max;
	        }
	     
	        
	        int maxP = Integer.MIN_VALUE;
	        max = Integer.MIN_VALUE;
	        for(int i=n-1; i>=0; i--){
	            if(a[i] > max)
	                max = a[i];
	            int tmp = max - a[i];
	            if(tmp > maxP)
	                maxP = tmp;
	            dp2[i] = maxP;
	        }
	        
	        max = Integer.MIN_VALUE;
	        
	        for(int i=0; i < n; i++){
	            int sum = dp[i] + dp2[i+1];
	            if(sum > max)
	                max = sum;
	        }
	     
	        return max;     
	    }
	   
	
	
	
	 public UndirectedGraphNode cloneGraph22(UndirectedGraphNode node) {  
	        Deque<UndirectedGraphNode> queue = new ArrayDeque();
	        UndirectedGraphNode retclone = new UndirectedGraphNode(node.label);
	        
	        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap();
	        map.put(node, retclone);
	        queue.offer(node);
	        
	            
	        while(queue.peek()!= null){
	            UndirectedGraphNode curr = queue.poll();
	            
	            UndirectedGraphNode currclone = map.get(curr);
	            
	            List<UndirectedGraphNode> neighbors = curr.neighbors;
	            
	            for(UndirectedGraphNode neighbor : neighbors){
	                if(map.containsKey(neighbor)){
	                    UndirectedGraphNode tmp = (UndirectedGraphNode) map.get(neighbor);
	                    currclone.neighbors.add(tmp);                    
	                }else{
	                    UndirectedGraphNode tmp = new UndirectedGraphNode(neighbor.label);
	                    map.put(neighbor,tmp);
	                    currclone.neighbors.add(tmp);
	                    queue.offer(neighbor);
	                }
	    
	            }
	            
	            
	        }        
	        return retclone;
	        
	        
	        
	    }
	@Test
	public void testaddBinary(){
		addBinary("11","1");
		
		
		
	}
	//8:40-8:52
	public String addBinary(String a, String b){
        int n = a.length();
        int m = b.length();
        if(n < m)
            return addBinary(b,a);
        
        int[] A = new int[n];
        int[] B = new int[n];
        
        for(int i =0; i < n; i++){
            String s = a.substring(i,i+1);
            A[i] = Integer.parseInt(s);
        }
        
        for(int i = 0; i < m; i++){
            String s = b.substring(i,i+1);
            int offset = n - m;
            B[offset + i] = Integer.parseInt(s);
        }
    
        int[] res = new int[n+1];
        
        for(int i =n-1; i >=0; i--){
            int sum = A[i] + B[i];
            res[i+1] = (sum+res[i+1]) % 2;
            if(sum >= 2)
                res[i]++;
        }
        StringBuffer sb = new StringBuffer();
        if(res[0] == 0){
        
            for(int i =1;i <= n;i++){
                sb.append(res[i] + "");
            }
        }else{
            
            for(int i =0;i <= n;i++){
                sb.append(res[i] + "");
            }
        }
        
        
        return sb.toString();
        
    }
        
	
    //8:29-8:39
	@Test
	public void testTwoSum(){
		twoSum(new int[]{3,2,4},6);
		
		
	}
	
	 public int[] twoSum(int[] A, int target) {
	      int n = A.length;
	      List<Integer> list = new ArrayList();
	      
	      for(int i =0; i < n; i++){
	          list.add(A[i]);
	      }
	      
	      int index1 = -1;
	      int index2 = -1;
	      Arrays.sort(A);
	      
	      for(int i=0; i < n; i++){
	          
	          index1 = A[i];
	          int diff = target - A[i];
	          
	          index2 = binarySearch(i+1, n-1, A, diff);
	          if(index2!=-1){
	              break;
	          }
	      }
	      
	      index2 = A[index2];
	      
	      int a = list.indexOf(index1);
	      int b = list.lastIndexOf(index2);
	      
	      return new int[]{a+1, b+1};  
	  }
	 
	 int binarySearch(int start, int last, int[] A, int target){
		 
		 for(;start <= last;){
			 int mid = (start + last)/2;
			 if(A[mid]  == target){
				 return mid;
				
			 }else if( target < A[mid]){
				 last = mid-1;
			 }else
				 start = mid + 1;
			 
		 }
		 return -1;
		 
	 }
	 
	 
	 
	
	
////	
////	
////	 public int[] twoSum(int[] A, int target) {
////	       int n = A.length - 1;
////	       List<Integer> list = new ArrayList();
////	       for(int i =0; i < n ; i++){
////	           list.add(A[i]);
////	       }
////	       int index1value = -1;
////	       int index2value = -1;
////	       int index1 = -1;
////	       int index2 = -1;
////	       Arrays.sort(A);
////	       
////	       for(int i =0;i < n;i++){
////	           int curr = target - A[i];
////	            index1 = i;
////	            index1value = A[i];
////	            index2 = binarySearch(i+1, n -1, A, curr);
////	            if(index2 != -1){
////	                index2value = A[index2];
////	                break;
////	            }
////	       }
////	       
////	      
////	           
////	           int id1 =  list.indexOf(index1value);
////	           int id2 =  list.lastIndexOf(index2value);
////	           
////	           return new int[]{id1+1,id2+1};
////	       
////	          
////	      
////	   }
////	   
//	   int binarySearch(int start, int last, int[] A, int target){
//	       for(;start <= last;){
//	           int mid = (start + last)/2;
//	           if(A[mid] == target)
//	                return mid;
//	            else if(A[mid] > target)
//	                start = mid +1;
//	            else
//	                last = mid-1;
//	           
//	           
//	       }
//	       return -1;
//	   }
	   
	public int sqrt(int x) {
        //8:06 - 8:10
        long start = 0;
        long last = Integer.MAX_VALUE;
        
        for(;start < last;){
            long mid = (start + last)/2;
            long a = mid* mid;
            if(a == x){
                return (int)mid;
            }
            
            if(start+1 == last)
                return (int)start;
            
            
            if(a > x)
                last = mid;
            if(a < x)
                start = mid;
            
        
            
        }
        
        return (int) start;//why you need this
        

	}
	
	//7:59 - 8:05   must not contains duplicate
    public List<List<Integer>> fourSum3(int[] A, int target) {
        int n = A.length;
        Arrays.sort(A);
        
        List<List<Integer>> res = new ArrayList();
        
        for(int i = 0;i < n;i++){
            for(int j= i+1;j < n;j++){
                int start = j + 1;
                int last = n - 1;
                for(;start < last;){
                    int sum = A[i] + A[j] + A[start] + A[last];
                    if(sum == target){
                        List<Integer> tmp = new ArrayList();
                        tmp.add(A[i]);
                        tmp.add(A[j]);
                        tmp.add(A[start]);
                        tmp.add(A[last]);
                        
                        if(!res.contains(tmp))
                            res.add(tmp);   
                        
                        last--;
                        start++;
                    }else if(sum > target){
                        last--;
                    }else
                        start++;
                }
            }
        }
        return res;
    }
	
	public List<List<Integer>> fourSum1(int[] A, int target) {
	       int n = A.length;
	       Arrays.sort(A);
	       List<List<Integer>> res = new ArrayList();
	       
	       for(int i=0; i < n; i++){
	           for(int j = i+1; j < n; j++){
	                int s = j + 1;
	                int l = n - 1;
	                for(;s < l;){
	                    
	                    int sum = A[i] + A[j] + A[s] + A[l];
	                    if(sum == target){
	                        List<Integer> list = new ArrayList();

	                        list.add(A[i]);
	                        list.add(A[j]);
	                        list.add(A[s]);
	                        list.add(A[l]);
	                        
	                        res.add(list);
	   
	                        s++;
	                        l--;
	                        
	                    }else if(sum > target){
	                        l--;
	                    }else{
	                        s++;
	                    }
	                }
	          
	           }
	       }
	       return res;
	  
	    }
	
	 public List<List<Integer>> fourSum(int[] A, int target) {
	       int n = A.length;
	       Arrays.sort(A);
	    
	       
	       List<List<Integer>> res = new ArrayList();
	       if(n==0)
	    	   return res;
	       for(int i=0; i < n; i++){
	           for(int j = i+1; j < n; j++){
	                int s = j + 1;
	                int l = n - 1;
	                for(;s < l;){
	                    int sum = A[i] + A[j] + A[s] + A[l];
	                    if(sum == target){
	                        List<Integer> list = new ArrayList();

	                        list.add(A[i]);
	                        list.add(A[j]);
	                        list.add(A[s]);
	                        list.add(A[l]);
	                        
	                        res.add(list);
	                    }else if(sum > target){
	                        l--;
	                    }else{
	                        s++;
	                    }
	                }
	          
	           }
	       }
	       return res;
	  
	    }
	
	
	@Test 
	public void testthreesum(){
		threeSumClosest(new int[]{1,1,1,0},100);
	};
	
	public int threeSumClosest(int[] A, int target) {
	       //7:30 - 7:44
	       //forget to sort the array
	       int n = A.length;
	       int res = 0;
	       int min = Integer.MAX_VALUE;
	       for(int i = 0; i< n;i++){
	           int start = i + 1;
	           int last = n - 1;
	           
	           for(;start < last;){
	              
	               int sum = A[i] + A[start] + A[last];
	               
	               if(sum == target)
	                    return target;
	             
	                    int diff = Math.abs(sum - target);
	                    if(diff < min){
	                        min = diff;
	                        res = sum;
	                    }
	                    
	                    
	                    
	                    
	                     if(sum > target){
	                         last --;
	                     }else{
	                         start ++;
	                     }
	                
	           }
	       }
	       
	       return res;
	    }
	
	
	
	
	
	
	
	public boolean exist(char[][] board, String word) {
	      //7:28
		      if(board == null)
					return true;
				if(word == null)
					return true;
				char[] w = word.toCharArray();
				char s = w[0];
				
				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board[0].length; j++) {
						if (s == board[i][j]) {
							
						    if(w.length==1)
						        return true;
							boolean[][] maze = new boolean[board.length][board[0].length];
						    boolean flag = explore(board, w, 0, i, j, maze);
						    
							if (flag == true){
								return true;
							}
						//	System.out.println(i+"|"+j+":"+flag);
							
						}
					}
				}
				return false;
			}

			// explore function
			public boolean explore(char[][] board, char[] w, int steps, int i, int j,
					boolean[][] maze) {
			    
			    
			    
			    //eliminate the invalid condition, indexOfBound, cannot go back, isnot equal the right value
				if (i >= board.length|| j >= board[0].length || i < 0 || j < 0   
				|| maze[i][j] == true || board[i][j] != w[steps] )//index out of board
					return false;
				
				
				// when exit
				if (steps+1 >= w.length)//steps to more than w.length
					return true;
					
					
				// DFS recursive search one way
				maze[i][j] = true;
				boolean flag =   
						 explore(board, w, steps + 1, i - 1, j, maze)
						|| explore(board, w, steps + 1, i, j - 1, maze)
						||  explore(board, w, steps + 1, i + 1, j, maze)
						|| explore(board, w, steps + 1, i, j + 1, maze);
				maze[i][j] = false;	
				return flag;
			}
		
	
	
	
	public int ladderLength2(String start, String last, Set<String> dict) {
		 if(start == null || last == null || start.length() != last.length() || start.length() ==0)
			 return 0;
		 //7:12-7:26
		 
		 Deque<String> queue = new ArrayDeque();
		 queue.offer(start);
		 
		 int depth = 1;
		 Set<String> visited = new HashSet();
		 int nodesInCurrentLevel = 1;
		 int nodesInNextLevel = 0;
		 
		 while(queue.peek()!=null){
		     String curr = queue.poll();
		     nodesInCurrentLevel--;
		     for(int i=0; i < curr.length(); i++){
		        char[] ch = curr.toCharArray();
		        for(char c= 'a'; c <='z';c++){
		            ch[i] = c;
		            String tmp = new String(ch);
		            if(tmp.equals(last)){
		                return depth + 1;
		            }
		            if( dict.contains(tmp) && !visited.contains(tmp) ){
		                visited.add(tmp);
		                queue.offer(tmp);
		                nodesInNextLevel ++;
		            }
		            
		        }
		     }
		     
		     if(nodesInCurrentLevel == 0){
		         nodesInCurrentLevel = nodesInNextLevel;
		         nodesInNextLevel = 0;
		         depth++;
		     }
		 }
        return 0;		 
	 }
	
	
	
	
	
	
	
	
	 class UndirectedGraphNode {
		      int label;
		      List<UndirectedGraphNode> neighbors;
		      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	 }
	
	
	
	
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {  
    	if(node == null)
    		return null;
    	Deque<UndirectedGraphNode> queue = new ArrayDeque();
    	
    	Hashtable<UndirectedGraphNode, UndirectedGraphNode> hashtable = new Hashtable();
    	
    	
    	UndirectedGraphNode retclone = new UndirectedGraphNode(node.label);
    	hashtable.put(node, retclone);
    	queue.offer(node);
    	
    	for(;queue.peek()!=null;){
    		UndirectedGraphNode curr = queue.poll();
    		UndirectedGraphNode currclone = hashtable.get(curr);
    		
    		ArrayList<UndirectedGraphNode> neighbors = (ArrayList<UndirectedGraphNode>) curr.neighbors;
    		
    		for(int i=0;i<neighbors.size();i++){
    			UndirectedGraphNode srctmp = neighbors.get(i);
    			
    			if(hashtable.containsKey(srctmp)){
    				UndirectedGraphNode clone = hashtable.get(srctmp);
    				currclone.neighbors.add(clone);
    			}else{
    				UndirectedGraphNode clone = new UndirectedGraphNode(srctmp.label);
    				hashtable.put(srctmp, clone);
    				currclone.neighbors.add(clone);
    				queue.offer(srctmp);
    			}	
    		}
    		
    	}
    	return retclone;
    }
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testdivide(){
		divide(500,4);
	}
	
	
	
	// 440ms for 987 test cases
    public int divide(int dividend, int divisor) {
        if (divisor == 0)
            return Integer.MAX_VALUE;
        int quotient = 0;
        // In order to make the Math.abs method work as expected, we need to consider the case
        // when either number is Integer.MIN_VALUE (Math.abs(Integer.MIN_VALUE)=Integer.MIN_VALUE)
        if (dividend == Integer.MIN_VALUE && divisor == Integer.MIN_VALUE) {
            return 1;
        } else if (divisor == Integer.MIN_VALUE) {
            return 0;
        } else if (dividend == Integer.MIN_VALUE) {
            quotient++;
            dividend += Math.abs(divisor);
        }

        int posDividend = Math.abs(dividend), posDivisor = Math.abs(divisor);
        // The maximum number of right shifts that can be applied to posDividend while
        // Making it larger than posDivisor
        int shift = 0;
        while ((posDividend>>(shift+1)) >= posDivisor)
            shift++;
        // Subtract multiples of posDivisor from posDividend and sum up the number
        // of multiples in quotient.
        while (shift >= 0) {
            if (posDividend >= (posDivisor << shift)) {
                quotient += 1 << shift;
                posDividend -= posDivisor << shift;
            }
            shift--;
        }
        // Determine whether quotient should be positive or negative
        return ((dividend^divisor) >>> 31 == 0) ? quotient : -quotient;
    }
	
	
	

	@Test
	public void testfindladders() {
		HashSet<String> hashset = new HashSet();
		hashset.add("hot");
		hashset.add("dot");
		hashset.add("dog");
		hashset.add("lot");
		hashset.add("log");
		
		
		int length = ladderLength("hit", "cog", hashset);
		System.out.println(length);
		
	}
/*Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.*/
	 public int ladderLength(String start, String last, Set<String> dict) {
		 if(start == null || last == null || start.length() != last.length() || start.length() ==0)
			 return 0;
		 Deque<String> queue = new ArrayDeque();
		 Set<String> visited = new HashSet();
		 
		 int depth = 1;
		 queue.offer(start);
		 int nodesincurrentlevel = 1;
		 int nodesinnextlevel = 0;
		 
		 while(queue.peek()!= null){
			 nodesincurrentlevel--;
			 String curr = queue.poll();
			 
			 for(int i = 0;i < start.length();i++){
				 char[] chcurr = curr.toCharArray();
				 
				 for(char c ='a'; c <='z';c++){
					 chcurr[i] = c;
					 String tmp = new String(chcurr);
					 if(tmp.equals(last))
						 return depth + 1;
					 if(dict.contains(tmp) && !visited.contains(tmp)){
						 queue.offer(tmp);
						 visited.add(tmp);
						 nodesinnextlevel++;
					 }
				 }
			 }
			 
			 if(nodesincurrentlevel == 0){
				 nodesincurrentlevel = nodesinnextlevel;
				 nodesinnextlevel = 0;
				 depth++;
			 }	 
		 }
		 return 0;
	 }
	 

}
