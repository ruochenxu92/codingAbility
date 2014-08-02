package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class day23 {
	
	public void solve(char[][] board) {
        Deque<Integer> idx = new ArrayDeque();
        Deque<Integer> idy = new ArrayDeque();
        
        int m = board.length;
        if(m == 0)
            return;
        int n = board[0].length;
        
        for(int i=0; i < m; i++){
            if(board[i][0] == 'O'){
                idx.offer(i);
                idy.offer(0);
            }
            if(board[i][n-1] == 'O'){
                idx.offer(i);
                idy.offer(n-1);
            }
        }
        
        for(int i =0; i < n; i++){
            if(board[0][i] == 'O'){
                idx.offer(0);
                idy.offer(i);
            }
            if(board[m-1][i] == 'O'){
                idx.offer(m-1);
                idy.offer(i);
            }
        }
        
        while(idx.peek() != null){
            int x = idx.poll();
            int y = idy.poll();
            board[x][y] = 'W';
            
            if(x+1 < m && board[x+1][y] == 'O'){
                idx.offer(x+1);
                idy.offer(y);
            }
            if(x-1 >= 0 && board[x-1][y] == 'O'){
                idx.offer(x-1);
                idy.offer(y);
            }
            if(y-1 >= 0 && board[x][y-1] == 'O'){
                idx.offer(x);
                idy.offer(y-1);
            }
            if(y+1 < n && board[x][y+1] == 'O'){
                idx.offer(x);
                idy.offer(y+1);
            }
        }
        
        for(int i=0; i < m; i++){
            for(int j = 0;j < n; j++){
                if(board[i][j] == 'W'){
                    board[i][j] = 'O';
                }else{
                    board[i][j] = 'X';
                }
            }
        }
        
    }



	
	
	
	public List<Integer> getRow(int nRows) {
//        if(nRows == 0){
//            return new ArrayList();
//        }
        if(nRows == 1){
            List<Integer> res = new ArrayList();
            res.add(1);
            return res;
        }
       
        List<Integer> pre = getRow(nRows - 1);
        List<Integer> cur = new ArrayList();
        
        cur.add(1);
        for(int i =1; i < pre.size(); i++){
            int sum = pre.get(i-1) + pre.get(i);
            cur.add(sum);
        }
        cur.add(1);

        return cur;
	}
	
	
		
	 public static List<List<Integer>> generate(int nRows){
	        if(nRows == 0)
	            return new ArrayList();
	        if(nRows == 1){
	            List<List<Integer>> res = new ArrayList();
	            List<Integer> tmp = new ArrayList();
	            tmp.add(1);
	            res.add(tmp);
	        }
	        // if(nRows == 2){
	        //     List<List<Integer>> res = new ArrayList();
	        //     List<Integer> tmp = new ArrayList();
	        //     List
	        // }
	        List<List<Integer>> pre = generate(nRows - 1);
	        
	        List<Integer> last = pre.get(pre.size() - 1);
	        List<Integer> curr = new ArrayList();
	        curr.add(1);
	        for(int i = 1; i < last.size(); i++){
	            int sum = last.get(i-1) + last.get(i);
	            curr.add(sum);
	        }
	        curr.add(1);
	        pre.add(curr);
	        return pre;
	   }
	
	
	
	public int minCut(String s){
        int n = s.length();
        
        boolean[][] table = new boolean[n][n];//start, last
        
        for(int i=0; i<n; i++){
            table[i][i] = true;
        }
        
        for(int i=0; i+1<n;i++){
            table[i][i+1] = s.charAt(i) == s.charAt(i+1);
        }
        
        for(int k = 2; k < n; k++){
            for(int i = 0; i + k < n; i++){
                if(table[i+1][i+k-1]){
                    if(s.charAt(i) == s.charAt(i+k))
                        table[i][i+k] = true;
                }
            }
        }
        
        int[] dp = new int[n];
        dp[0] = 0;
        for(int i=1; i<n; i++){
            if(table[0][i]){
                dp[i] = 0;
                continue;
            }
            for(int j=1; j <= i; j++){
                int min = Integer.MAX_VALUE;
                if(table[j][i]){
                     min = Math.min(min, dp[j-1] + 1);
                }
                dp[i] = min;
            }     
        }
        return dp[n-1];
    }
	 public List<List<String>> partition(String s) {
	        if(s == null || s.length() == 0)
	            return new ArrayList();
	        int n = s.length();
	        boolean[][] table = new boolean[n][n];
	        
	        for(int i=0; i<s.length(); i++){
	            table[i][i] = true;
	        }   
	        
	        for(int i=0; i+1 < s.length(); i++){
	            table[i][i+1] = s.charAt(i) == s.charAt(i+1);
	        }
	        
	        for(int k = 2;  k< n; k++){
	            for(int i =0; i+k < n; i++){
	                if(table[i+1][i+k-1]){
	                    if(s.charAt(i) == s.charAt(i+k))
	                        table[i][i+k] = true;
	                }
	            }
	        }   
	        
	        List<List<String>> res = new ArrayList();
	        List<String> sol = new ArrayList();
	        dfs(0, s, table, sol, res);
	        return res;
		}
		
		
		void dfs(int start, String s, boolean[][] table, List<String> sol, List<List<String>> res){
		    if(start == s.length()){
		        List<String> tmp = new ArrayList(sol);
		        res.add(tmp);
		        return;
		    }
		    
	        StringBuffer buf = new StringBuffer();	    
		    for(int i = start; i < s.length(); i++){
		        buf.append(s.charAt(i));
		        if(table[start][i]){
	                sol.add(buf.toString());
	                dfs(i+1, s, table, sol, res);
	                sol.remove(sol.size() - 1);
		        }
		    }
		}
		
	
	
	
	public boolean isPalindrome(int x) {
        if(x < 0) return false;
        
        int div = 1;
        while(x / div >= 10){
            div *= 10;
        }
        
        while(x > 0){
            int head = x / div;
            int tail = x % div;
            if(head != tail)
                return false;
            x = x % div /10;
            div/=100;
        }
        return true;
    }
	
	
	 @Test
	 public void testlongestPalidrome(){
		 System.out.println(longestPalindrome1("12321"));
	 }
	 public String longestPalindrome1(String s) {
	        int n = s.length();
	        int maxl = 0;
	        String res = ""; 
	        for(int i = 0; i < 2 * n - 1; i++){
	            int left = i/2;
	            int right = i/2;
	            
	            if(i % 2 == 1)
	                right++;
	            
	            String longest = longeststring(s, left, right);
	            if(longest.length() > maxl){
	                maxl = longest.length();
	                res = longest;
	            }
	        }
	        return res;
	    }
	 	
	    
	    String longeststring(String s, int left, int right){
	        while(left >= 0 && right <s.length() && s.charAt(left) == s.charAt(right)){
	            left--;
	            right++;
	        }       
	        return s.substring(left+1, right);
	    }
	    
	    
	    
	    
	
	public String longestPalindrome(String s) {
        //O(n^2)   O(n^2)
        int n = s.length();
        
        
        boolean[][] dp = new boolean[n][n];
        for(int i = 0; i < n; i++){
            dp[i][i] = true;       
        }
        
        
        int maxl = 0;
        int start = 0;
        int last = 0;
        
        for(int i = 1; i + 1 < n; i++){
            dp[i][i+1] = s.charAt(i) == s.charAt(i+1);
            if(dp[i][i+1]){
                if(maxl == 0){
                    maxl = 1;
                    start = i;
                    last = i+1;
                }
            }
        }        
        
      
        for(int k = 2; k  < n; k++){
            for(int i = 0; i + k < n; i++){
                if(dp[i+1][i+k-1] && s.charAt(i) == s.charAt(i+k)){
                    dp[i][i+k] = true;
                    if(k > maxl){
                        maxl = k;
                        start = i;
                        last = i + k;
                    }
                }
                    
            }
        }
        return s.substring(start, last +1);
   }   

	
	
	public int longestConsecutive1(int[] A) {
        HashMap<Integer, Integer> hs = new HashMap();
        for(int i : A)
            hs.put(i, 0);
        int maxl = 0;
        for(int i : A){
            if(hs.get(i) == 1)
                continue;
            int upper = i;
            while(hs.containsKey(upper+1)){
                upper++;
                hs.put(upper, 1);
            }
            int lower = i;
            while(hs.containsKey(lower-1)){
                lower--;
                hs.put(lower, 1);
            }
            
            int len = upper +1 - lower;
            maxl = Math.max(len, maxl);
        }
        return maxl;
    }

	
	
	public int longestConsecutive(int[] A) {
        HashSet<Integer> dict = new HashSet();
        HashSet<Integer> visit = new HashSet();
        int n = A.length;
        for(int i =0; i < n; i++){
            dict.add(A[i]);
        }
        int maxNum = 0;
        // n  n  O(n)
        for(int i =0; i < n; i++){
            if(visit.contains(A[i])){
                continue;
            }
            int count = 1;
            visit.add(A[i]);
            int init = A[i];
            
            while( dict.contains(init + 1) ){
                visit.add(init + 1);
                init++;
                count++;
            }
            
            init = A[i];
            while(dict.contains(init - 1)){
                visit.add(init - 1);
                init--;
                count++;
            }
            
            maxNum = Math.max(maxNum, count);
        }
        return maxNum;
    }
	
	public boolean isInterleave(String s1, String s2, String s3){
        int m = s1.length();
        int n = s2.length();
        
        boolean[][] table = new boolean[m+1][n+1];
        
        
        table[0][0] = true;
        for(int i=1; i <= m; i++){
        	if(table[i-1][0]){
        		if(s1.charAt(i-1) == s3.charAt(i-1))
        			table[i][0] = true;
        	}
        }
        
        for(int j=1; j <= n; j++){
        	if(table[0][j-1]){
        		if(s2.charAt(j-1) == s3.charAt(j-1))
        			table[0][j] = true;
        	}
        }
        
        for(int i =1; i <= m; i++){
        	for(int j=1; j <=n; j++){
        		if(s1.charAt(i-1)==s3.charAt(i+j-1) && table[i-1][j]){
        			table[i][j] = true;
        			continue;
        		}
        		if(s2.charAt(j-1) == s3.charAt(i+j-1) && table[i][j-1]){
        			table[i][j] = true;
        		}
        	}
        }
        
        return table[m][n];
     }
	
	
	 public int minDistance(String w1, String w2) {
	        int m = w1.length();
	        int n = w2.length();
	        
	        int[][] table = new int[m+1][n+1];
	        for(int i = 0; i <= m; i++)
	            table[i][0] = i;
	        for(int j = 0; j <= n; j++)
	            table[0][j] = j;
	        
	        for(int i = 1; i <= m; i++){
	            for(int j = 1; j <= n; j++){
	                if(w1.charAt(i-1) == w2.charAt(j-1))
	                    table[i][j] = table[i-1][j-1];
	                else{
	                    table[i][j] = Math.min(table[i-1][j], table[i][j-1]) + 1;
	                    table[i][j] = Math.min(table[i-1][j-1]+1, table[i][j]);
	                }
	            }
	        }
	        
	        return table[m][n];
	    }
	 
	 
	 
	 
	
	 public int numDistinct(String S, String T) {
	        int m = T.length();
	        int n = S.length();//n > m
	        if(n < m)
	            return 0;
	        
	        int[][] dp = new int[m+1][n+1];
	        
	        dp[0][0] = 1;
	        
	        for(int i = 0; i < n; i++){
	            dp[0][i] = i;
	        }
	        
	        for(int i = 1; i < n; i++){
	            for(int j = 1; j <m && j<= i; j++){
	                if(S.charAt(i) == T.charAt(j)){
	                    dp[j][i] = dp[j][i-1] + dp[j-1][i-1];
	                }else{
	                    dp[j][i] = dp[j][i-1];
	                }
	            }
	        }
	        return dp[m][n];
	   }
	
	
	
	
	public int numDecodings(String s) {
        if(s == null || s.length() == 0)
            return 0;
        int n = s.length();
        
        int[] dp = new int[n+1];
        char[] A = s.toCharArray();
        dp[n] = 0;
        if(A[n-1] > '0' && A[n-1] <= '9')
            dp[n-1] = 1;
        
        for(int i = n - 2; i >= 0; i--){
            if(A[i] <= '0' || A[i] > '9'){
                dp[i] = 0;
                continue;
            }
            dp[i] += dp[i+1];
            if(valid(A[i], A[i-1]))
                dp[i] += dp[i+2];
        }
        return dp[0];                
   }
   
   public boolean valid(char a, char b){
       if( a == '1' || (b =='2' && b <='6'))
            return true;
       return false;
   }
   
   
	
	
	
	public int numTrees(int n) {
   	    if( n == 0)
   	        return 1;
   	    if( n == 1)
   	        return 1;
   	    int[] dp = new int[n+1];
   	    
   	    dp[0] = 1;
   	    dp[1] = 1;
   	    for(int i =2; i <= n; i++){
   	        int sum = 0;
   	        for(int k = 0; k < i; k++){
                sum += dp[k] *   dp[i - 1 - k];
   	        }
   	        dp[i] = sum;
   	    }
   	    
   	    return dp[n];   
	}
	
	class Point{
		 int x;
		 int y;
		 Point(){x = 0; y = 0;}
		 Point(int x, int y){
			 this.x = x;
			 this.y = y;
		 }
	 }
	
	@Test
	public void testmaxPoints(){
		Point p1 = new Point(4,0);
		Point p2 = new Point(4,1);
		Point p3 = new Point(4,5);
		Point[] points = new Point[]{p1,p2,p3};
		
		maxPoints(points);
	}
	
	
	public int maxPoints(Point[] points){
	     HashMap<Float, Integer> hs = new HashMap();
	     if(points.length == 0)
	        return 0;
	     
	     int max = 1;
	     for(int i = 0;i < points.length; i++){
	         hs.clear();
	         
           int dup = 1;
           hs.put(Float.MAX_VALUE,0);

           
           Point A = points[i];
           for(int j = 0; j < points.length; j++){
               if( j == i ){
                   continue;
               }
               Point B = points[j];
               
               if(A.x == B.x && A.y == B.y){
                   dup++;
                   continue;
               }else if(A.x == B.x){
                   
                   hs.put(Float.MAX_VALUE, hs.get(Float.MAX_VALUE) + 1);
                   continue;
               }else{
                  Float tangent =((float)(A.y - B.y))/(A.x - B.x);
                  if(hs.containsKey(tangent)){
                      hs.put(tangent, hs.get(tangent)+1);
                  }else{
                      hs.put(tangent, 1);
                  }
               }
            }
            
            Iterator it = hs.values().iterator();
            while(it.hasNext()){
                int tmp = (int)it.next();
                max = Math.max(max, tmp + dup);
            }
            max = Math.max(max, dup);
	     }
	     return max;
 }
	
	
	
	
	
	@Test
	public void testLRUCache(){
		LRUCache cache = new LRUCache(1);
		
		cache.set(1, 10);
		cache.set(1, 11);
		cache.set(2, 20);
		cache.set(3, 30);
		
		System.out.println(cache.get(1));
		System.out.println(cache.get(2));
		System.out.println(cache.get(3));
	}
	
	
	
	public class LRUCache {
		private class Node{
	        Node prev;
	        Node next;
	        int key;
	        int value;

	        public Node(int key, int value) {
	            this.key = key;
	            this.value = value;
	            this.prev = null;
	            this.next = null;
	        }
	    }
		
		int capacity;
		private HashMap<Integer, Node> map = new HashMap();
		private Node head = new Node(-1,-1);
		private Node tail = new Node(-1,-1);
	    

	    public LRUCache(int capacity) {
	        this.capacity = capacity;
	        head.next = tail;
	        tail.prev = head;
	    }

	    public int get(int key) {
	    	if(!map.containsKey(key)){
	    		return -1;
	    	}
	    	
	    	Node current = map.get(key);
	    	
	    	//remove current
	    	current.prev.next = current.next;
	    	current.next.prev = current.prev;
	    	
	    	move_to_tail(current);
	    	
	    	return current.value; 
	    }

	    public void set(int key, int value) {
	    		if(get(key) != -1){
	    			map.get(key).value = value;
	    			return;
	    		}
	    	
	    		if(map.size() == capacity){
	    			
	    			//remove the last one
	    			map.remove(head.next.key);
	    			
	    			Node delete = head.next;
	    			head.next = delete.next;
	    			delete.next.prev = head;
	    		}
	    		
	    		Node newNode = new Node(key, value);
	    		map.put(key, newNode);
	    		move_to_tail(newNode);
	    	
	    }
	    

	    private void move_to_tail(Node current) {
	    	current.prev = tail.prev;
	    	tail.prev = current;
	    	
	    	current.next = tail;
	    	current.prev.next = current;
	    }
	}   
	
	
	
	@Test
	public void testfirstMissingPositive(){
		int[] A = new int[]{2,1};
		firstMissingPositive(A);
		
		
	}
	
	public int firstMissingPositive(int[] A){
    	int i = 0;
    	for(; i < A.length; i++){
            while(A[i] != i+1){
                if(A[i]-1 <0 || A[i]-1 >= A.length || A[i] == A[A[i]-1])
                    break;
                int index = A[i] - 1;
                int tmp = A[i];
                A[i] = A[index];
                A[index] = tmp;
            }    	    
    	}
    	return i+1;
    }
	
	
	  @Test
	  public void testswapPairs(){
		  ListNode head = new ListNode(1);
		  head.next = new ListNode(2);
		  ListNode abc = swapPairs(head);
	  }
	
	  public ListNode swapPairs(ListNode head) {
	        ListNode sentinel = new ListNode(0);
	        sentinel.next = head;

	        ListNode pre = sentinel;

	        while(head != null && head.next != null){
	            pre.next = head.next;   
	            ListNode tmp = head.next.next;
	            head.next.next = head;
	            head.next = tmp;
	            pre = head;
	            head = tmp;
	        }

	        return sentinel.next;
	    }
	
	
	
	
	public ListNode sortList(ListNode head){
        if(head == null)
            return head;
        
        ListNode slow = head;
        ListNode fast = head.next;
        
        //find middle
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        ListNode nexthead = slow.next;
        slow.next = null;
        
        ListNode A = sortList(nexthead);
        ListNode B = sortList(head);
        
        return merge1(A, B); 
	}
	
	ListNode merge1(ListNode A, ListNode B){
	    ListNode head = null;
	    ListNode tail = null;
	    ListNode newNode = null;
	    
	    while(A != null || B != null ){
	        if(A != null && B != null){
	            if( A.val  < B.val){
	                newNode = A;
	                A = A.next;
	                newNode.next = null;
	            }else{
	                newNode = B;
	                B = B.next;
	                newNode.next =null;
	            }
	        }else if( A != null){
	            newNode = A;
	            A = A.next;
	            newNode.next = null;
	        }else{
	            newNode = B;
	            B = B.next;
	            newNode.next = null;
	        }
	        
	        if(head == null){
	            head = tail = newNode;
	        }else{
	            tail.next = newNode;
	            tail = tail.next;
	        }
	    }
	    
	    return head;
	}
	
	
	public ListNode rotateRight(ListNode head, int n) {
        if(head == null || head.next == null)
            return head;
            
        ListNode tail = head;
        int length = 1;
        while(tail.next != null){
            tail = tail.next;
            length++;
        }
        
        n = n % length;
        if(n == 0)
            return head;
        int k = length - n;
        ListNode newhead = head;
        ListNode prev = newhead;
        for(int i = 0; i < k ; i++){
            prev = newhead;
            newhead = newhead.next;
        }
        tail.next = head;
        prev.next = null;
        return newhead;
    }
	
	
	
	
	 public ListNode reverseKGroup(ListNode head, int k){
	        if(k <= 1)
	            return head;
	        if(head == null || head.next == null)
	            return head;
	            
	        ListNode sentinel = new ListNode(0);
	        sentinel.next = head;
	        ListNode pre = sentinel;
	        
	        int index = 1;
	        ListNode cur = head;
	        
	        while(cur != null){
	            if(index % k == 0){
	                ListNode aft = cur.next;
	                
	                //reverse(pre, aft)
	                ListNode h = pre.next;
	                ListNode newhead = aft;
	                
	                while(h != aft){
	                    ListNode tmp = h.next;
	                    h.next = newhead;
	                    newhead = h;
	                    h = tmp;
	                }
	                ListNode prev = pre.next;
	                pre.next = newhead;
	                pre = prev;
	                cur = aft;
	            }else{
	                cur = cur.next;
	            }
	            index++;
	        }
	        return sentinel.next;
	    }
	
	@Test
	public void testreverseBetween(){
		ListNode head = new ListNode(3);
		head.next = new ListNode(1);
		
		reverseBetween( head,1,2 );
		
		
	}
	
	
	 public ListNode reverseBetween(ListNode head, int m, int n) {
	        ListNode sentinel = new ListNode(0);
	        sentinel.next = head;
	        ListNode pre = sentinel;
	        ListNode aft = sentinel;
	        ListNode cur = sentinel;
	        
	        if(head == null || head.next == null){
	            return head;
	        }
	        
	        if(m >= n || m <= 0)
	            return head;
	        
	        int length = -1;
	        for(int i = 0; cur != null; i++){
	            length++;
	            if(i == m-1){
	                pre = cur;
	            }else if( i == n ){
	                aft = cur.next;
	                break;
	            }
	            cur = cur.next;
	        }
	        //judge the condition
	        
	        if(m > length || n > length )
	            return head;
	        
	        //reverse from 
	        ListNode newhead = aft;
	        ListNode trav = pre.next;
	        
	        while(trav != aft){
	            ListNode tmp = trav.next;
	            trav.next = newhead;
	            newhead = trav;
	            trav = tmp;
	        }
	        
	        pre.next = newhead;
	        return sentinel.next;
	    }
	
	
	
	public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode(0);
        
        sentinel.next = head;
        
        ListNode curr = head;
        int length = 0;
        for(;curr!= null; curr =  curr.next){
            length++;
        }
        if(n > length || n == 0)
            return head;
        
        int index = length - n; 
            
        ListNode prev = sentinel;
        curr = head;
        for(int i = 0; i < length; i++){
            if(i == index){
                prev.next = curr.next;
            }else{
                prev = prev.next;
                curr = curr.next;
            }
        }
        return sentinel.next;
    }
	
	
	
	 public void reorderList(ListNode head) {
	        if (head == null || head.next == null) {
	            return;
	        }

	        ListNode mid = findMiddle(head);
	        ListNode tail = reverse(mid.next);
	        mid.next = null;

	        merge(head, tail);
	    }
	    
	    ListNode findMiddle(ListNode head){
	        if(head == null || head.next == null)
	            return head;
	        ListNode slow = head;
	        ListNode fast = head.next;
	        
	        while(fast != null && fast.next != null){
	            slow = slow.next;
	            fast = fast.next.next;
	        }
	        return slow;
	    }
	    
	    ListNode reverse(ListNode head){
	        ListNode newhead = null;
	        while(head != null){
	            ListNode tmp = head.next;
	            head.next = newhead;
	            newhead = head;
	            head = tmp;
	        }
	        return newhead;
	    }
	    
	    void merge(ListNode head1, ListNode head2){
	        ListNode dummy = new ListNode(0);
	        int index = 0;
	        while(head1 != null && head2 != null){
	            if(index % 2== 0){
	                dummy.next = head1;
	                head1 = head1.next;
	            }else{
	                dummy.next = head2;
	                head2 = head2.next;
	            }
	            dummy = dummy.next;
	            index++;
	        }
	        if(head1 != null){
	            dummy.next = head1;
	        }else{
	            dummy.next = head2;
	        }
	    }

}
