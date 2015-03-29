package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import leetcode.day13.Interval;

import org.junit.Test;

public class day13 {
	
	
	@Test
	public void testthreeSum(){
		int[] A = new int[]{-1,2,1,-4};
		int res = threeSumClosest(A, 1);
		System.out.println(res);
	}
	
	 public int threeSumClosest(int[] A, int target) {
	        int n = A.length;
	        int res = Integer.MAX_VALUE;
	        int closest = Integer.MAX_VALUE;
	        Arrays.sort(A);
	        for(int i = 0; i < n; i++){
	            int start = i + 1;
	            int last = n - 1;
	            for(;start < last;){
	                int sum = A[i] + A[start] + A[last];
	                
	                int diff = Math.abs(sum - target);
	                if(diff < closest){
	                    closest = diff;
	                    res = sum;
	                }
	                
	                if(diff == 0){
	                    return sum;
	                }else if(sum-target > 0){
	                    last--;
	                }else{
	                    start++;
	                }
	                
	            }
	            
	        }
	        return res;
	        
	    }
	 
	 
	 

	@Test
	public void testfindKthSmall1(){
		int A[] = new int[]{1,2,3};
		int B[] = new int[]{4,5,6};
		
		int abc = findKth(2, A, A.length, B,B.length);
		System.out.println(abc);
		
		HashMap map = new HashMap();
		
	}
	
	
	
	public int findKth(int k, int A[], int m, int[] B, int n) {
		if (m > n)
			return findKth(k, B, n, A, m);
		int currA = A.length - m;
		int currB = B.length - n;

		if (k == 1) {
			return A[currA] < B[currB] ? A[currA] : B[currB];
		}

		int pA = Math.min(k / 2, m);
		int pB = k - pA;
		// length sum - 1
		if (A[pA + currA - 1] == B[pB + currB - 1]) {
			return A[pA + currA];
		} else if (A[currA + pA - 1] < B[currB + pB - 1]) {

			return findKth(k - pA, A, m - pA, B, n);
		} else {
			return findKth(k - pB, A, m, B, n - pB);
		}

	}
	    
	    
	 public double findMedianSortedArrays(int A[], int B[]) {
	        int n = A.length;
	        int m = B.length;
	        
	        int sum = n + m;
	        if(sum % 2 == 0){
	            return (findKth(sum/2, A, n, B, m)  + findKth(sum/2 + 1, A,n, B, m))/2.0;
	        }else{
	            return (findKth(sum/2 + 1, A, n, B, m))* 1.0;
	        }
	                
	  }
	
	
	
	public int ladderLength(String start, String end, Set<String> dict) {
        if(start.length() == 0 || end.length() ==0 || dict.size() == 0){
            return 0;
        }
        
        Deque<String> queue = new ArrayDeque();
        HashSet<String> visited = new HashSet();
        
        queue.offer(start);
        
        int currLevel = 1;
        int nextLevel = 0;
        int depth = 1;
        
        while(queue.peek() != null){
            currLevel--;
            String curr = queue.poll();
            for(int i =0; i < curr.length(); i++){
                char[] A = curr.toCharArray();
                for(char c = 'a'; c <= 'z'; c++){
                    A[i] = c;
                    String tmp = new String(A);
                    
                    if(tmp.equals(end)){
                        return depth+1;
                    }
                    //in order to avoid circle
                    if(dict.contains(tmp) && !visited.contains(tmp)){
                        visited.add(tmp);
                        queue.offer(tmp);
                        nextLevel++;
                    }                  
                }
            }
            if(currLevel == 0){
                currLevel = nextLevel;
                nextLevel = 0;
                depth++;
            }
        }
        
        return 0;
    }
	
	
	
	
	
	class UndirectedGraphNode {
	    int label;
	    List<UndirectedGraphNode> neighbors;
	    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	};
	
	
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        
        Deque<UndirectedGraphNode> queue = new ArrayDeque();
        if(node == null){
            return null;
        }
        UndirectedGraphNode newhead = new UndirectedGraphNode(node.label);
        
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap();
        queue.offer(node);
        map.put(node, newhead);
        
        
        while(queue.peek()!=null){
            UndirectedGraphNode A = queue.poll();
            
            List<UndirectedGraphNode> neighbors = A.neighbors;
            
            UndirectedGraphNode clone = map.get(A);
            
            for(UndirectedGraphNode neighbor : neighbors){
                if(map.containsKey(neighbor)){
                    UndirectedGraphNode newnode = map.get(neighbor);
                    clone.neighbors.add(newnode);
                }else{
                    UndirectedGraphNode newnode = new UndirectedGraphNode(neighbor.label);
                    map.put(neighbor, newnode);
                    clone.neighbors.add(newnode);
                    queue.offer(neighbor);
                }
            }
        }
        
        return newhead;
        
    }
	
	@Test
	public void testmaxProfit3(){
		int[] A = new int[]{14,3,5,1,7,0,9};
		
		int a = maxProfit3(A);
		System.out.println("res = " + a);
		
	}
	
	
	public int maxProfit3(int[] A) {
        int n = A.length;
        if( n <= 1)
        		return 0;
        int[] dp = new int[n+1];//length
        int[] dp2 = new int[n];//lenght
        
        int min = Integer.MAX_VALUE;
        int maxProfit = Integer.MIN_VALUE;
        dp[0] = 0;
        for(int i=0; i < n; i++){
            if(A[i] < min){
                min = A[i];
            }
            int tmp = A[i] - min;
            if(tmp > maxProfit){
                maxProfit = tmp;
            }
            
            dp[i+1] = maxProfit;//length
        }

        int max = Integer.MIN_VALUE;
        maxProfit = Integer.MIN_VALUE;
        for(int i=n-1; i >= 0; i--){
            if(A[i] > max){
                max = A[i];
            }
            int tmp  = max - A[i];
            if(tmp > maxProfit){
                maxProfit = tmp;
            }
            dp2[i] = maxProfit;
        }
        
        
        max = Integer.MIN_VALUE;
        for(int i =0; i < n; i++){
            if(dp[i] + dp2[i] > max){
                max = dp[i]  + dp2[i];
            }
        }
                
        return max;
    }
	
	
	
	@Test
	public void testmaxProfit2(){
		int res = maxProfit2(new int[]{1,3,2,4,5,6,3,2,4,9});
		System.out.println("res = " + res);
	}
	
	
	
	public int maxProfit2(int[] A) {
        int valley = -1;
        int peak = -1;
        int n = A.length;
        
        if(n <= 1)
            return 0;
        
        int total = 0;
        
        for(int i = 0; i < n; i++){
            if(i == 0){
                if(A[0] < A[1]){
                    valley = A[0];
                }
            }else if(i == n-1){
                if(A[n-1] > A[n-2]){
                    peak = A[n-1];
                    if(valley != -1){
                        total += peak - valley;
                    }
                }
            }else{
                if(A[i] >= A[i-1] && A[i] >= A[i+1]){
                    peak = A[i];
                    if(valley != -1){
                        total += peak - valley;
                        valley = -1;
                    }
                }
                
                if(A[i] <= A[i-1] && A[i] <= A[i+1]){
                    valley = A[i];
                }
            }
        }    
        return total;
    }


	
	@Test
	public void testmaxProfit(){
		int a = maxProfit(new int[]{1,3,5,7,1,9,2});
		System.out.println(a);
	}
	
	public int maxProfit(int[] A) {
        int maxProfit = 0;
        
        int min = Integer.MAX_VALUE;
        int n = A.length;
        
        for(int i =0;  i < n ;i ++){
            if(A[i] < min){
                min = A[i];
            }
            
            if(A[i]- min > maxProfit){
                maxProfit = A[i] - min;
            }
        }
        
        return maxProfit;
    }
	
	public int candy(int[] ratings) {
        int n = ratings.length;
        int[]  candy = new int[n];
        
        for(int i = 0; i < n ;i++){
            candy[i] = 1;
        }
        
        for(int i = 1; i < n; i++){
            if(ratings[i] > ratings[i-1]){
                candy[i] = candy[i-1] + 1;
            }
        }
        
        for(int i =n - 2; i >= 0; i--){
            if(ratings[i] > ratings[i+1]){
                candy[i] = Math.max(candy[i], candy[i+1]  + 1);
            }
        }
        int sum = 0;
        for(int i =0; i < n ;i ++){
            sum += candy[i];
        }
        return sum;
        
        
    }
	
	
	@Test
	public void testinsert(){
		
		List<Interval> intervals = new ArrayList();
		intervals.add(new Interval(1,2));
		intervals.add(new Interval(3,5));
		intervals.add(new Interval(6,8));
			
		insert(intervals, new Interval(6,9));
		
		for(Interval i : intervals){
			System.out.println(i.start + "," + i.end + "    ");
		}
		
	}
	
	 public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
	        
	        if(intervals.size()==0){
	            intervals.add(newInterval);
	            return intervals;
	        }
	        
	        int last = intervals.size() - 1;
	        if(newInterval.start > intervals.get(last).start){
	            intervals.add(newInterval);
	        }else{
	            for(int i = 0; i < intervals.size(); i++){
	                if(intervals.get(i).start >= newInterval.start){
	                    intervals.add(i, newInterval);
	                    break;
	                }
	            }
	        }
	        

	        for(int i =1; i < intervals.size(); i++){
	            Interval A = intervals.get(i-1);
	            Interval B = intervals.get(i);
	            
	            if( A.end  >= B.start){
	                A.end = Math.max(A.end, B.end);
	                intervals.remove(i);
	                i--;
	            }
	        }
	        
	        
	        return intervals;
	        
	    }
		
	
	class Interval {
	    int start;
	    int end;
	    Interval() { start = 0; end = 0; }
	    Interval(int s, int e) { start = s; end = e; }
	}
	
	
	@Test
	public void testmergeInterval(){
		List<Interval> intervals = new ArrayList();
		intervals.add(new Interval(0, 3));
		intervals.add(new Interval(2,5));
		intervals.add(new Interval(4,6));
		intervals.add(new Interval(8,9));
		intervals.add(new Interval(3,4));
		
		List<Interval> res = merge(intervals);
		for(Interval tmp : res){
			System.out.print("(" + tmp.start + "," + tmp.end + ")");
		}
		
	}
	
	
	public List<Interval> merge(List<Interval> intervals) {
		
		Collections.sort(intervals, new Comparator<Interval>(){
			@Override
			public int compare(Interval A, Interval B) {
				return A.start > B.start ? 1 : (A.start == B.start ? 0 : -1);
			}
		});
	        
	    for(int i = 1; i < intervals.size(); i++){
	    	Interval A = intervals.get(i - 1);
	    	Interval B = intervals.get(i);
	    		
	    	if(A.end >= B.start){
	    		intervals.remove(i);
	    		i--;
	    		A.end = Math.max(A.end, B.end);
	    	}
	    	
	    }
	        
	    return intervals;    
	        
	}
	
		
	@Test
	public void testmerge(){
		int A[] = new int[10];
		for(int i =0; i < 7; i++){
			A[i] = 2 *i;
		}
		int B[] = new int[]{3,5,7};
		merge(A,10, B,3);
		
		for(int i =0; i < 10; i++){
			System.out.print(A[i]  + ",");
		}
		
	}
	
	
	public void merge(int A[], int sizeA, int B[], int sizeB) {
        int startA = 0;
        int startB = 0;
        
        int[] res =  mergetwoArray(A, 0, sizeA, B, 0, sizeB);        
        for(int i = 0; i < A.length; i++){
            A[i] = res[i];
        }
    }
    
    int[] mergetwoArray(int A[], int startA, int sizeA, int B[],int startB, int sizeB){
        int[] res = new int[sizeA + sizeB];
        int dist = 0;
        for(;startA < sizeA || startB < sizeB;){
            if( startA < sizeA && startB < sizeB){
                if(A[startA] < B[startB]){
                    res[dist++] = A[startA++];
                }else{
                    res[dist++] = B[startB++];
                }
            }else if( startA < sizeA ){
                res[dist++] = A[startA++];
            }else{
                res[dist++] = B[startB++];
            }
        }
                
        return res;
    }
	    
	    
	    
	
	@Test
	public void testplusOne(){
		int[] A = new int[]{1,2,9};
		int[] res = plusOne(A);
		for(int i =0; i < res.length; i++){
			System.out.print(res[i] + ",");
		}
		
	}
	
	public int[] plusOne(int[] A) {
        
        LinkedList<Integer> res = new LinkedList();
        
        int carrier = 0;
        for(int i = A.length - 1; i>= 0;i--){
        	
            int value = carrier + A[i];
            if(i == A.length - 1){
        		value++;
        	}
            if( value >= 10){
                value = value - 10;
                carrier = 1;
            }else{
                carrier = 0;
            }
            res.push(value);
        }
        
        if(carrier == 1){
        	res.push(1);
        }
        
        
        
        int[] result = new int[res.size()];
        Iterator it = res.iterator();
        for(int i =0; i < res.size(); i ++){
        	result[i] = (int) it.next(); 
        }
        
        
        return result;
        
    }


	
	public int removeDuplicates2(int[] A) {
        int n = A.length;
        if( n <= 1)
            return n;
        int dup = 0;
        int dist = 0;
        for(int i =1; i < n; i++){
            if(A[i-1] == A[i]){
                dup++;
                if(dup < 2){
                    A[++dist] = A[i];
                }
                continue;
            }
            if(A[i-1] != A[i]){
                dup = 0;
                A[++dist] = A[i];
                continue;
            }
        }
        
        return dist + 1;
        
        
    }
	
	
	 public int removeDuplicates(int[] A) {
	        int n = A.length;
	        if(n <= 1){
	            return n;
	        }
	        
	        
	        int dist = 1;
	        for(int i =1; i< n; i++){
	            if(A[i-1] != A[i]){
	                A[dist++] = A[i];
	            }
	            
	        }
	        
	        return dist;        
	  }
	
	
	
	@Test
	public void testreverseWords(){
		
		String res = reverseWords("a   b");
		System.out.println(res);
		
	}
	
	 public String reverseWords(String s) {
	        s = s.trim();
	        int n = s.length();
	        LinkedList<String> stack = new LinkedList();
	        
	        StringBuffer buf = new StringBuffer();
	        for(int i =0 ;i  < n ; i++){
	            if(s.charAt(i) == ' '){
	                for(; i < n && s.charAt(i) == ' '; i++);
	                    i--;
	                stack.push(buf.toString());
	                buf = new StringBuffer();
	            }else{
	            	
	                buf.append(s.charAt(i));                
	            }
	        }
	        //last one did not push, we need push it to stack
	        stack.push(buf.toString());
	        
	        buf = new StringBuffer();
	        Iterator it = stack.iterator();
	        while(it.hasNext()){
	            String tmp = (String)it.next();
	            buf.append(tmp);
	            buf.append(' ');
	        }
	        buf.deleteCharAt(buf.length()-1);
	        
	        return buf.toString();
	    }
	
	
	 public int romanToInt(String s) {
	        /** *   I 	1
	                V 	5
	                X 	10
	                L 	50
	                C 	100
	                D 	500
	                M 	1,000
	            */
	        
	        HashMap<Character, Integer> map = new HashMap();
	        map.put('I', 1);
	        map.put('V', 5);
	        map.put('X',10);
	        map.put('L',50);
	        map.put('C',100);
	        map.put('D',500);
	        map.put('M',1000);
	        
	        int sum = 0;
	        char c = s.charAt(0);
	        
	        sum += map.get(c);
	        
	        for(int i =1; i < s.length(); i++){
	            char c1 = s.charAt(i-1);
	            int n1 = map.get(c1);
	            
	            char c2 = s.charAt(i);
	            int n2 = map.get(c2);
	            
	            if(n2 > n1){
	            	sum -= n1;
	                sum -= n1;
	                sum += n2;
	            }else{
	                sum += n2;
	            }
	        }
	        
	        return sum;
	    }
	
	
	
	@Test
	public void testsearchRange(){
		int[] A = new int[]{1,2,3};
		int[] res = searchRange(A, 2);
		System.out.println("(" + res[0] + "," + res[1] + ")");
		
	}
		
	 public int[] searchRange(int[] A, int target) {
	        
	        int n = A.length;
	        
	        int start = 0;
	        int last = n - 1;
	        
	       
	        
	        for(;start <= last;){
	            int mid = (start + last)/2;
	            
	            if(A[mid] == target){
	                //this is my exit condition
	                if(A[start] == target && A[last] == target){
	                    return new int[]{start, last};
	                }else if(A[start] == target){
	                    last --;
	                }else if(A[last] == target){
	                    start ++;
	                }else{
	                	start ++;
	                	last--;
	                }
	            }else if(target < A[mid]){
	                last = mid - 1;
	            }else{
	                start = mid + 1;
	            }
	            
	        }
	        
	        
	        return new int[]{-1,-1};
	        
	    }
	 
	 
	 
	 
	 
	
	 public boolean search2(int[] A, int target) {
	        
	        int n = A.length;
	        int start  = 0; 
	        int last = n - 1;
	        
	        for(; start <= last; ){
	            int mid = (start + last)/2;
	            
	            if(A[mid] == target){
	                return true;
	            }
	            
	            if(A[start]  < A[mid]){
	                if(target < A[mid] && target >= A[start]){
	                    last = mid - 1;
	                }else if(target > A[mid]){
	                    start = mid + 1;
	                }else if(target < A[start]){
	                    start = mid + 1;
	                }
	            }else if(A[start] > A[mid]){
	                if(target > A[mid] && target <= A[last]){
	                    start = mid + 1;
	                }else if(target > A[last]){
	                    last = mid - 1;
	                }else if(target < A[mid]){
	                    last = mid - 1;
	                }
	            }else{
	                start ++;
	            }
	        }
	        
	        
	        return false;
	        
	    }
	
	
	 @Test
	 public void testsearch(){
		 int[] A = new int[] {4,5,6,1,2,3};
		 int res = search(A,6);
		 System.out.println("res = " + res);
	 }
	
	
	 public int search(int[] A, int target) {
	        
         
	        int start = 0;
	        int n = A.length;
	        int last = n - 1;
	        for(;start <= last;){
	              
	              int mid = (start + last)/2;
	              if(A[mid] == target){
	                return mid;
	              }
	              
	              if(A[start] <= A[mid]){
	                  if(target < A[mid] && target >= A[start]){
	                      last = mid-1;
	                  }else if( target < A[start] ){
	                      start = mid + 1;
	                  }else if(target > A[mid]){
	                      start = mid + 1;
	                  }
	              }else{
	                  if(target > A[mid] && target <= A[last]){
	                      start = mid + 1;
	                  }else if( target < A[mid] ){
	                      last = mid - 1;
	                  }else if( target > A[last]){
	                      last = mid - 1;
	                  }
	              }
	        }
	        
	        
	        return -1;
	        
	    }
	
	
	
	 public int searchInsert(int[] A, int target) {
	        int n = A.length;
	        if(n == 0){
	            return 0;
	        }
	        
	        if(target < A[0]){
	            return 0;
	        }
	        for(int i =0; i < n; i++){
	            if( target < A[i]){
	                return i;
	            }
	        }
	        return n;        
	        
	        
	        
	    }
	 
	 
	@Test
	public void testsimplifyPath(){
		String str = new String("/a/./b/../../c/");
		
		String tmp = simplifyPath(str);
		System.out.println(tmp);
		
	}
	
	public String simplifyPath(String path) {
        LinkedList<String> stack = new LinkedList();
        
        int n = path.length();
        
        
        StringBuffer buf = new StringBuffer();
        buf.append('/');
        for(int i =1; i < n; i++){
           if(path.charAt(i) == '/'){
               
               for(;i < n && path.charAt(i) == '/'; i++);
               if(i!= n){
                   i--;
               }
               
               String tmp = buf.toString();
               if(tmp.equals("/..")){
                   if(stack.size() != 0){
                       stack.pop();
                   }
               }else if(tmp.equals("/.")){
                   
               }else{
                   stack.push(tmp);
               }
               buf = new StringBuffer();
               buf.append('/');
           }else{
               buf.append(path.charAt(i));
           }
        }
        
        String tmp = buf.toString();
        if(tmp.equals("/..")){
            if(stack.size() != 0)
                stack.pop();
        }else if(tmp.equals("/.")){
            
        }else{
            stack.push(tmp);
        }
        
        StringBuffer sb = new StringBuffer();
        List<String> res = new ArrayList(stack);
        
        for(int i = res.size()-1; i >=0; i--){
            sb.append(res.get(i));
        }
        
        if(sb.toString().equals("")){
            sb.append("/");
        }else if(sb.length() > 1){
            sb.deleteCharAt(sb.length()-1);
        }
        
        
        return sb.toString();
        
    }
	
	
	
	@Test
	public void testfindKthSmall(){
		int A[] = new int[]{1,2,3};
		int B[] = new int[]{4,5,6};
		
		int abc = findKthSmall(1, A, A.length, B,B.length);
		System.out.println(abc);
		
		HashMap map = new HashMap();
		
	}
	
	
	
	
	int findKthSmall(int k, int A[], int m, int[] B, int n){
        int currA = A.length - m;
        int currB = B.length - n;
        if(k == 0)
        	return A[currA] < B[currB] ? A[currA] : B[currB];
        
        if(m > n)
            return findKthSmall(k, B,n, A,m);
        if(m == 0){
            return  B[currB + k];
        }
        
        int ALen = k/2;
        int BLen = k - k/2;
        if(ALen > m){
            ALen = m;
            BLen = k - m;
        }
        if(A[currA + ALen] == B[currB + BLen]){
            return A[currA+m];
        }else if(A[currA + ALen] < B[currB + BLen ]){
            
            return findKthSmall(k-ALen, A, m - ALen, B,n);
        }else{
            return findKthSmall(k-BLen, A, m, B, n-BLen);
        }
    }
	
	
	
	@Test
	public void testtwoSum(){
		
		int[] A = new int[]{3,2,4};
		int[] res = twoSum(A, 6);
		for(int i =0;i < 2; i++){
			System.out.print(res[i] + ",");
		}
		
		
	}
	//O(nlgn)
	 public int[] twoSum(int[] A, int target) {
         
         int n = A.length;
         
         List<Integer> dict = new ArrayList();
         for(int i =0; i < n ;i ++){
        	 dict.add(A[i]);
         }
         
         Arrays.sort(A);
         
         
         int v1 = -1;
         int v2 = -1;
         
         for(int i =0; i < n; i++){
              v1 = A[i];
              v2 = target-v1;
             int flag = binarySearch(A, i+1, n-1, v2);
             if(flag != -1){
                 break;
             }
         }
         
         int index1 = dict.indexOf(v1);
         int index2 = dict.lastIndexOf(v2);
         if(index1 > index2){
        	 return new int[]{index2  +1, index1 + 1};
         }else{
        	 return new int[]{index1 +1, index2 + 1};
         }
     }
     
	 
	 @Test
	 public void testbinarySearch(){
		 int[] A= new int[]{3,2,4};
		 
	 }
	 
     public int binarySearch(int[] A, int start, int last, int target){
         for(;start <= last;){
             int mid = (start + last)/2;
             if(A[mid] == target){
                 return mid;
             }else if( target < A[mid]){
                 last = mid-1;
             }else{
                 start = mid + 1;
             }
         }
         return -1;
     }
	
	
	@Test
	public void testsqrt(){
		int x = 1000000;
		int res = sqrt(x);
		System.out.println("sqrt " + x + "=" + res);
	}
	
	
	public int sqrt(int x) {
        
        long last = Integer.MAX_VALUE;
        long start = 0;
        
        for(;start < last;){
            if(start + 1 == last){
                if(last * last != x)
                    return (int)start;  
                else
                    return (int)last;
            }
            
            long mid = (start + last)/2;
            
            if(mid*mid == x){
                return (int)mid;
            }else if(x < mid * mid){
                last = mid;
            }else{
                start = mid;
            }
        }
        return (int)start;
    }
	
	 public boolean searchMatrix(int[][] matrix, int target) {
	        int m = matrix.length;
	        if(m == 0)
	            return false;
	        int n = matrix[0].length;
	        
	        int last = m * n - 1;
	        int start = 0;
	        
	        for(;start <= last;){
	            
	            int mid = (last + start)/2;
	            
	            int midx = mid / n;
	            int midy = mid % n;
	            
	            if(matrix[midx][midy] == target){
	                return true;
	            }else if(matrix[midx][midy] > target){
	                last = mid - 1;
	            }else{
	                start = mid + 1;
	            }
	            
	        }
	        return false;
	    }
	 
	public boolean isMatch(String str, String pattern) {
		char[] A = str.toCharArray();
		char[] B = pattern.toCharArray();
		boolean star = false;
		int pA;
		int pB;
		int startA = 0;
		int startB = 0;
		for(pA = startA, pB = startB;pA < A.length; pA++, pB++){
			switch(B[pB])
			{
			case '?':
				break;
			case '*':
				star = true;
				startA = pA;
				startB = pB;
				while(B[startB] == '*')
					startB++;
				if(startB == B.length)
					return true;
				pA = startA;
				pB = startB;
				break;
			default:
			{
				if(A[pA] != B[pB]){
					if(!star)
						return false;
					startA ++;
					pA = startA;
					pB = startB;
				}
			}
			}
		}
		while(B[pB] == '*')	
			pB++;
		return pB == B.length;
	}
	
	
	
	
	@Test
	public void testfullJustify(){
		String[] words1 = new String[]{"this", "is","Xiaomin", "Illinois", "Xu","Champaign","Urbana"};
		String[] words = new String[]{""};
		List<String> res = fullJustify(words, 0);
		for(String str : res){
			System.out.println(str);
		}
	}
	
	
	public List<String> fullJustify(String[] words, int L){
        List<String> res = new ArrayList();
        
        int count = 0;
        int start = 0;
        int n = words.length;
        int i = 0;
        for(; i < n;  i ++){
            if(count + i - start + words[i].length() > L){
                int wordsnum = i - start;
                int spacenum = wordsnum-1;
                StringBuffer buf = new StringBuffer();
                int remain = L - count;
                if(spacenum == 0){
                    buf.append(words[start]);
                    buf.append(space(remain));
                }
                else if(spacenum == 1){
                    buf.append(words[start]);
                    buf.append(space(remain));
                    buf.append(words[start+1]);
                }else{
                    int base = remain/spacenum;
                    int mod = remain%spacenum;
                    for(int k = start; k < i; k++){
                        buf.append(words[k]);
                        if(k < mod + start){
                            buf.append(space(base + 1));
                        }else if(k < i - 1){
                            buf.append(space(base));
                        }
                    }
                } 
                res.add(buf.toString());
                start = i;
                count = 0; 
                
            }
            count += words[i].length();
        }
        
        //left justified and no extra space
        int wordsnum = i - start;
        int spacenum = wordsnum - 1;
        int remain = L - count;
        StringBuffer buf = new StringBuffer();
        
        if(spacenum == 0){
            buf.append(words[start]);
            buf.append(space(remain));
        }else if(spacenum == 1){
            buf.append(words[start]);
            buf.append(" ");
            buf.append(words[start+1]);
            buf.append(space(remain-1));
        }else{
            
            for(int k = start; k < i; k++){
                buf.append(words[k]);
                buf.append(" ");
            }
            buf.append(space(remain-wordsnum));
        }
        res.add(buf.toString());
        
        return res;    
    }
    
    public String space(int n){
        StringBuffer res = new StringBuffer();
        
        for(int i =0; i < n; i++){
            res.append(" ");
        }
        return res.toString();
    }
	
	
	@Test
	public void testwordBreak(){
	 //	"ab", ["a","b"]
		String s = new String("ab");
	 	Set<String> dict = new HashSet();
	 	dict.add("a");
	 	dict.add("b");
	 	wordBreak(s,dict);
		
		
	}
	
	
	public List<String> wordBreak(String s, Set<String> dict){
        int n = s.length();

        boolean[] dp = new boolean[n];
        dp[0] = true;
        
        for(int i=1; i < n; i++){
            if(dict.contains(s.substring(0,i))){
                dp[i] = true;
                continue;
            }
            
            for(int j= 0; j < i ;j++){
                if(dp[j] && dict.contains(s.substring(j,i))){
                    dp[i] = true;
                }
            }
            
        }
        
        List<String> res =new ArrayList();
        if(!dp[n-1]){
            return res;
        }
        StringBuffer buf = new StringBuffer();
        dfs(0, s, buf, dict, res);
        return res;
        
    }
	    
    void dfs(int start, String s, StringBuffer buf, Set<String> dict, List<String> res){
        if(start >= s.length()){
            res.add(buf.toString());
            return;
        }
        
        
        StringBuffer tmp = new StringBuffer();
        for(int i = start; i < s.length(); i++){
            tmp.append(s.charAt(i));
            if(dict.contains(tmp.toString())){
                int oldLen = buf.length();
                if(i != 0) buf.append(" ");
                buf.append(tmp);
                dfs(i+1, s, buf, dict, res);
                buf.delete(oldLen, buf.length());
            }
        }
         
    }
	
	 public boolean wordBreak1(String s, Set<String> dict) {
	        int n = s.length();
	        char[] A = s.toCharArray();
	        
	        boolean[] dp = new boolean[n+1];
	        dp[0] = true;//length = 0
	                    
	        for(int i =1; i <= n ; i++){
	            if(dict.contains(s.substring(0,i))){
	                dp[i] = true;
	                continue;
	            }
	            
	            for(int j = 0; j < i;j++){
	                if(dp[j] && dict.contains(s.substring(j,i))){
	                    dp[i] = true;
	                }                
	            }
	        }
	        
	        return dp[n];
	    }
	    
}
