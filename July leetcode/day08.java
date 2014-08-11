package leetcode;

import static org.junit.Assert.*;

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
	public void testatoi(){
		int res = atoi("-14256");
		System.out.println("res = " +res);
	}
	
	public int atoi(String s){
		boolean negative = false;
		if(s.charAt(0) == '-'){
			negative = true;
			s = s.substring(1);
		}
		
		HashMap<Character, Integer> map = new HashMap();
		map.put('0', 0);
		map.put('1', 1);
		map.put('2', 2);
		map.put('3', 3);
		map.put('4', 4);
		map.put('5', 5);
		map.put('6', 6);
		map.put('7', 7);
		map.put('8', 8);
		map.put('9', 9);
		
		//12345
		int sum =0;
		for(int i =s.length()-1; i >= 0; i--){
				int pow = s.length()- 1 - i;
				int num = map.get( s.charAt(i));
			    sum += num*Math.pow(10,pow);
		}
		if(negative == false)
			return sum;
		else
			return sum * (-1);
	}
	
	
	
	
	
	
	
	
	@Test
	public void testreversefrom(){
		ListNode A = new ListNode(1);
		A.next = new ListNode(2);
		A.next.next = new ListNode(3);
		A.next.next.next = new ListNode(4);
		A.next.next.next.next = new ListNode(5);
		ListNode B = A.next.next;
		ListNode aft = A.next.next.next;
		
		ListNode sentinel = new ListNode(0);
		sentinel.next = A;
		sentinel = reverse(sentinel,  aft);
		A.next.next = reverse(B, null);
		
		System.out.println(sentinel);
	}
	
	
	
	
	public ListNode reverse(ListNode pre, ListNode aft){
		if(pre == aft){
			return pre.next;
		}
		
		ListNode prev = pre.next;
		ListNode curr = prev.next;
		
		while(curr != aft){
			ListNode tmp = prev;
			prev = curr;
			curr = curr.next;
			prev.next = tmp;
		}
		pre.next.next = curr;
		pre.next = prev;
		return pre;
    }
    
	
	
	
	
	@Test
 	public void testMerge(){
 		ListNode A = new ListNode(1);
 		A.next = new ListNode(2);
 		ListNode B = new ListNode(4);
 		B.next = new ListNode(5);
 		B.next.next = new ListNode(6);
 		ListNode res = merge(A, B);
 		System.out.println(res);
 	}
	
	
	 @Test
	 public void testmergeKLists(){
		 List<ListNode> lists = new ArrayList();
		 ListNode  A = new ListNode(1);
		 A.next  = new ListNode(2);
		 
		 ListNode B = new ListNode(4);
		 B.next = new ListNode(5);
		 B.next.next = new ListNode(6);
		 
		 
		 ListNode C  = new ListNode(3);
		 C.next = new ListNode(34);
		 C.next.next = new ListNode(100);
		 
		 
		 lists.add(A);
		 lists.add(B);
		 lists.add(C);
		 ListNode res = mergeKLists(lists);
		 System.out.println(res);
		 
	 }
	
	 public ListNode mergeKLists(List<ListNode> lists) {
	        if(lists.size() == 1){
	            return lists.get(0);
	        }
	        if(lists.size() == 2){
	            ListNode A = lists.get(0);
	            ListNode B = lists.get(1);
	            return merge(A, B);            
	        }
	        int n = lists.size();
	        int partA = n/2;
	        List<ListNode> listA = new ArrayList();
	        List<ListNode> listB = new ArrayList();
	        
	        for(int i=0; i < n; i++){
	            if(i < partA)
	                listA.add(lists.get(i));
	            else
	                listB.add(lists.get(i));
	            
	        }      
	        
	        ListNode A = mergeKLists(listA);
	        ListNode B = mergeKLists(listB);
	        
	        return merge(A, B);
		}       
	    
	    public ListNode merge(ListNode A, ListNode B){
	        ListNode head = null;
	        ListNode tail = null;
	        ListNode newNode = null;
	        
	        while(A != null || B != null){
	            
	            if(A != null && B != null){
	                if(A.val > B.val){
	                    newNode = new ListNode(B.val);
	                    B = B.next;
	                }else{
	                    newNode = new ListNode(A.val);
	                    A = A.next;
	                }
	            }else if(A!= null){
	                newNode = new ListNode(A.val);
	                A = A.next;
	            }
	            else{
	                newNode = new ListNode(B.val);                
	                B = B.next;
	            }
	                     
	            
	            if(tail == null){
	                head = tail = newNode;
	            }else{
	                tail.next = newNode;
	                tail = tail.next;
	            }
	        }
	        return head;      
	    }
	    
	
	
	
		  class RandomListNode {
		      int label;
		      RandomListNode next, random;
		      RandomListNode(int x) { this.label = x; }
		  };
	
	@Test
	public void testcopyRandomList(){
		RandomListNode head = new RandomListNode(1);
		head.next = new RandomListNode(2);
		head.random = head.next;
		RandomListNode copy = copyRandomList(head);
		System.out.println(copy);
		
	}
		  
		  
	public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null)
            return null;
        
        HashMap<RandomListNode, RandomListNode> map = new HashMap();
        
        RandomListNode newcopy = new RandomListNode(head.label);
        RandomListNode it = head.next;
        RandomListNode copyit = newcopy;
        
        for(;it!= null;it = it.next,copyit = copyit.next){
                copyit.next = new RandomListNode(it.label);
                map.put(it, copyit.next);
        }        
        
        for(it = head,copyit = newcopy;it!= null;it = it.next,copyit = copyit.next){
            RandomListNode key = it.random;
            RandomListNode value = null;
            if(map.get(key) != null)
            	 value = map.get(key); 
            copyit.random = value;
        }
        return newcopy;    
        
    }
	
	
	
	
	
	@Test
	public void testinsertionsort(){
		ListNode head = new ListNode(7);
		head.next = new ListNode(5);
		head.next.next = new ListNode(6);
		head.next.next.next  = new ListNode(8);
		head.next.next.next.next = new ListNode(4);
		
		head = insertionSortList(head);
		
		
		while(head != null){
			System.out.print(head.val+ ",");
			head = head.next;
		}
		
		
		
	}
	
	public static ListNode insertionSortList(ListNode head){
   	    ListNode tail = head;
   	    if(head == null){
   	        return head;
   	    }

   	    ListNode it = head.next;
   	    
   	    while(it != null){
   	    	ListNode insert = it;
            if(insert.val >= tail.val){
                tail = tail.next;
                it = it.next;
                continue;
            }else{
                tail.next = insert.next;
                it = it.next;
                head = insert(head, tail, insert);
            }
   	    }
   	    
   	    return head;
   	}
       
	@Test
	public void testinsert(){
		ListNode head = new ListNode(7);
		head.next = new ListNode(18);
		ListNode tail = head.next;
		ListNode insert = new ListNode(5);
		head = insert(head,tail,insert);
		
		while(head != null){
			System.out.print(head.val + ",");
			head = head.next;
		}
	}
	
	
    public static ListNode insert(ListNode head, ListNode tail, ListNode insert){
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode prev = sentinel;
        ListNode curr = head;
        
        while(true){
        	if(insert == tail){
        		prev.next = insert;
        		insert.next = curr;
        		break;
        	}
            if(insert.val <= curr.val){
                prev.next = insert;
                insert.next = curr;
                break;
            }
            prev = prev.next;
            curr = curr.next;
        }
        return sentinel.next;
    }  
    
    
    
	
	
	@Test
	public void testaddTwoNumbers() {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(9);

		ListNode l2 = new ListNode(3);
		l2.next = new ListNode(7);
		ListNode res = addTwoNumbers(l1, l2);
		ListNode it = res;
		for (; it != null; it = it.next) {
			System.out.print(it.val);
		}
	}
	
	
	
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //reverse reverse
        l1 = reverse(l1);
        l2 = reverse(l2);
        
        ListNode it1 = l1;
        ListNode it2 = l2;
        
        ListNode res = new ListNode(0);
        ListNode it3 = res;
        int extra = 0;
        for(;it1 != null && it2 != null;){
            int value = it1.val + it2.val + extra;
            extra = value/10;
            value = value%10;
            
            it3.next = new ListNode(value);
            it3 = it3.next;
            it2 = it2.next;
            it1 = it1.next;
        }
        if(it1 != null){
            for(;it1!=null;it1 = it1.next){
                int value = it1.val + extra;
                extra = 0;
                it3.next = new ListNode(value);
                it3 = it3.next;
            }
            
        }else if(it2 != null){
            for(;it2!=null;it2 = it2.next){
                int value = it2.val + extra;
                extra = 0;
                it3.next = new ListNode(value);
            }
            
        }
        ListNode head = res.next;
        head = reverse(head);
        return head;
    }
	
	
	
	@Test
	public void testreverse() {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);

		head = reverse(head);
		ListNode it = head;
		while (it != null) {
			System.out.println(it.val);
			it = it.next;
		}
	}

	ListNode reverse(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode prev = head;
		ListNode curr = prev.next;
		while (curr != null) {
			ListNode tmp = prev;
			prev = curr;
			curr = curr.next;
			prev.next = tmp;
		}
		head.next = null;
		return prev;
	}
    
	
	@Test
	public void testdeleteDuplicates(){
		ListNode  head = new ListNode(1);
		head.next = new ListNode(1);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(3);
		
		head = deleteDuplicates(head);
		ListNode it = head;
		for(;it!= null;){
			System.out.println(it.val);
		}
	}
	
	public ListNode deleteDuplicates(ListNode head) {
        if(head == null)
            return head;
        if(head.next == null)
            return head;
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode it = sentinel;
        ListNode prev = head;
        ListNode curr = prev.next;
        
        for(;curr != null;){
            if(curr.val == prev.val){
                for(;curr != null && curr.val == prev.val;){
                    prev = curr;
                    curr = curr.next;
                }
                if(curr == null){
                    return sentinel.next;                    
                }
                prev = curr;
                curr = curr.next;
                if(curr == null){
                    it.next = prev;
                    it = it.next;//update it
                    return sentinel.next;
                }
                
                if(prev.val != curr.val){
                    it.next = prev;
                    it = it.next;//update it
                }
                continue;
            }else{
                it.next = prev;
                it = it.next;
                prev = curr;  //prev = curr
                curr = curr.next;
            }
        }
        
        
        
        
        
        //I do not update the curr one
        return sentinel.next;
    }
	@Test
	public void testreverseBetween(){
		ListNode head = new ListNode(1);
		ListNode it  = head;
		for(int i =2; i <= 5; i++){
			it.next = new ListNode(i);
			it = it.next;
		}
		
		
		
		head  = reverseBetween(head, 2, 4);
		
		it = head.next;
		while(it != null){
			System.out.println(it.val);
			it = it.next;
		}
		
	}
	
	
	
	
	
	
	
	
	public ListNode reverseBetween(ListNode head, int m, int n) {
        //1 -> 2 -> 3 -> 4 -> 5        
		ListNode start = null;
        ListNode end = null;
        //5->4->3->2->1
//        int indexstart = m - 1;
//        int indexlast = n - 1;
        ListNode it = head;
        
        if(head == null){
            return head;
        }

        if(m == n){
            return head;
        }
        
        for(int i =1; it != null && i <= n; i++ ){
            if( m != 1){
                if( i == m-1){
                    start = it; 
                }
            }
            if(i == n){
                end = it;
            }
            it = it.next;
        }
        
        if(m == 1){
            start = head; 
        }
        
        it = head;
       
        ListNode prev = start.next;
        ListNode curr = prev.next;
        ListNode tmp;
        
        while(curr != end){
            tmp = prev;
            prev = curr;
            curr = curr.next;
            prev.next = tmp;
        }
        tmp = start.next;
        start.next = prev;
        tmp.next = curr;
        return head;
    }
	
	
	
	 
	public List<String> fullJustify(String[] words, int L) {
        int n = words.length;
        
        int count =0;
        List<String> res = new ArrayList();
        int slow = 0; 
        for(int i =0; i < n; i++){
            if(count + i - slow + words[i].length()  > L){
                int wordnum = i - slow;
                int spacenum = wordnum - 1;
                int remain = L - count;
                StringBuffer buf = new StringBuffer();
                if(spacenum >= 1){
                    int base =  remain/spacenum;
                    int mod  =  remain%spacenum;
                    for(int k =slow; k < i; k++){
                        buf.append(words[k]);
                        if(k < mod){
                            buf.append(getextraspace(base+1));
                        }else{
                            buf.append(getextraspace(base));
                        }
                    }
                }else{
                    buf.append(words[slow]);
                    buf.append(getextraspace(remain));
                }
                count = 0;
                res.add(buf.toString());
                slow = i;             
            }
            count +=words[i].length();    
        }
        
        int wordnum = n - slow - 1;
        int remain = L - count; 
        
        StringBuffer buf = new StringBuffer();
        for(int i =slow; i < n; i++){
            buf.append(words[i]);
            buf.append(" ");
        }
        remain = remain - wordnum;
        buf.append(getextraspace(remain));
        res.add(buf.toString());
        return res;
    }
    
    public String getextraspace(int n ){
        
        StringBuffer buf = new StringBuffer();
        for(int i =0; i < n ; i++){
            buf.append(" ");
        }
        return buf.toString();
    }
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length;
        int[] height = new int[n];  // Height of consecutive 1's directly above a row
        int largestArea = 0;

        // Calculate the area of the maximal rectangle with its bottom at each row
        for (int i = 0; i < m; i++) {
            // Update the heights of consecutive 1's according to the elements in the current row
            for (int j = 0; j < n; j++)
                height[j] = matrix[i][j]=='0' ? 0 : height[j]+1;
            // Get the area of the maximal rectangle residing on the current row, and 
            // update the largest area if necessary
            largestArea = Math.max(largestArea, largestRectangleArea(height));
        }

        return largestArea;
    }

    // Taken from LeetCode - Largest Rectangle in Histogram
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int n = height.length;
        int largestArea = 0;
        Deque<Integer> indexStack = new LinkedList<Integer>();

        // For each bar, pop out the bars higher than it and calculate the largest area of
        // the rectangle using this full bar, before pushing it to the stack
        for (int i = 0; i < n; i++) {
            while (!indexStack.isEmpty() && height[i] <= height[indexStack.peek()]) {
                int index = indexStack.pop();
                if (indexStack.isEmpty())
                    largestArea = Math.max(largestArea, i*height[index]);
                else
                    largestArea = Math.max(largestArea, (i-indexStack.peek()-1)*height[index]);
            }
            indexStack.push(i);
        }

        // Calculate the largest area of the rectangle using each full bar remaining in the stack
        while (!indexStack.isEmpty()) {
            int index = indexStack.pop();
            if (indexStack.isEmpty())
                largestArea = Math.max(largestArea, n*height[index]);
            else
                largestArea = Math.max(largestArea, (n-indexStack.peek()-1)*height[index]);
        }

        return largestArea;
    }
	
	
	
	
	
	 public int minCut(String s) {
	        
	        //aab
	        char[] A = s.toCharArray();
	        int n = A.length;
	        boolean[][] table = new boolean[n][n];
	        
	        for(int i =0; i < n ; i++){
	            table[i][i] = true;
	        }
	        
	        for(int i=0; i+1 < n ; i++){
	            table[i][i+1] = A[i] == A[i+1];
	        }
	        
	        for(int i =2; i < n; i++){
	            for(int j=0; j+i < n ;j++){
	                if(table[i+1][j+i-1]){
	                    table[i][j] = A[i] == A[i+j];
	                }
	            }
	        }
	        
	        int[] dp = new int[n];
	        dp[0] = 0;
	        for(int i=1; i < n; i ++){
	            int min = Integer.MAX_VALUE;
	            if(table[0][i]){
	                dp[i] = 0;
	                continue;
	            }
	            
	            for(int j=0; j < i; j++){
	                if(table[j+1][i]){
	                    if(dp[j] + 1 < min)
	                        min = dp[j]+1;
	                }                
	            }
	        }
	        return dp[n-1];
	    }
	
	
	


}
