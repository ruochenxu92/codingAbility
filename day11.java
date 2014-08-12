package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
/*
 * I do review for linked list and hash table
 * 
 */
public class day11 {
	
	
	public ListNode insertionSortList(ListNode head){
        
        if(head == null || head.next == null)   
            return head;
         
        
        ListNode tail = head;
        ListNode curr = head.next;
        
        for(;curr!= null;){
            if(curr.val >= tail.val){
                tail = tail.next;
            }else{
                ListNode ins = curr;
                curr = curr.next;
                tail.next =curr;
                    
                //insert
                if(ins.val < head.val){
                    ins.next = head;
                    head = ins;
                    continue;
                }else{
                    ListNode pre = head;
                    for(ListNode it = head;; it = it.next){
                       if(ins.val < it.val){
                           pre.next = ins;
                           ins.next = it;
                           break;
                       } 
                       pre = it;
                    }
                }
                
            }
        }
        return head;
 }
	public ListNode mergeKLists(List<ListNode> lists) {
		   if(lists.size() == 0)
		        return null;
		   if(lists.size() == 1){
		       return lists.get(0);
		   }
		   if(lists.size() == 2){
		       ListNode A = lists.get(0);
		       ListNode B = lists.get(1);
		       
		       ListNode head = null;
		       ListNode tail = null;
		       ListNode newNode = null;
		       
		       for(;A != null || B != null;){
		           if( A!= null && B!= null){
		               if(A.val < B.val){
		                   newNode = A;
		                   A = A.next;
		                   newNode.next = null;
		               }else{
		                   newNode = B;
		                   B = B.next;
		                   newNode.next = null;
		               }
		           }else if(A != null){
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
	   
	       
	       int n = lists.size();
	       
	       List<ListNode> A = new ArrayList();
	       List<ListNode> B = new ArrayList();
	       
	       for(int i =0; i < n ;i ++){
	           if( i < n/2){
	               A.add(lists.get(i));
	           }else{
	               B.add(lists.get(i));
	           }
	       }
	       
	              
	       ListNode n1 = mergeKLists(A);
	       ListNode n2 = mergeKLists(B);
	       
	       
	       List<ListNode> res = new ArrayList();
	       res.add(n1);
	       res.add(n2);
	       return mergeKLists(res);
	   }
	
	 public ListNode partition(ListNode head, int x) {
	        //1->4->3->2->5->2;    x = 3     1->2->2->4->3->5
	        //fir->1->2->2    sec->4->3->5
	        if(head == null || head.next == null)
	            return head;
	        
	        ListNode first = new ListNode(0);
	        ListNode fir = first;
	        ListNode second = new ListNode(0);
	        ListNode sec = second;
	        
	        for(;head != null; ){
	            if(head.val < x){
	                fir = head;
	                head = head.next;
	                fir.next = null;
	            }else{
	                sec = head;
	                head = head.next;
	                sec.next = null;
	            }
	        }
	                
	        fir.next = second.next;
	        return first.next;
	    }
	
	@Test
	public void testdeleteDup(){
		ListNode head = new ListNode(1);
		head.next = new ListNode(1);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(2);
		deleteDuplicates1(head);
		
		
	}
	
	 public ListNode deleteDuplicates1(ListNode head) {
	        if(head == null || head.next == null)
	            return head;
	        
	        ListNode sentinel = new ListNode(0);
	        sentinel.next = head;
	        
	        
	        ListNode pre = sentinel;
	        
	        ListNode prev = head;
	        ListNode curr = head.next;
	        
	        for(;curr!= null;){
	            if(prev.val == curr.val){
	                while(curr!=null &&prev.val ==curr.val ){
	                    prev = prev.next;
	                    curr = curr.next;
	                }
	                if(curr == null){
	                    pre.next = null;
	                    break;
	                }
	                pre.next = curr;
	                if(curr.next == null){
	                    break;
	                }
	                
	                curr = curr.next;
	                prev = prev.next;
	            }else{
	                pre = pre.next;
	                if(curr.next == null){
	                    pre.next = curr;
	                    break;
	                }
	                prev = prev.next;
	                curr = curr.next;
	            }
	        }
	        
	        return sentinel.next;
	    }
	
	public ListNode deleteDuplicates(ListNode head) {
        if(head == null)
            return head;
        if(head.next == null)
            return head;
        
        ListNode prev = head;
        ListNode curr = head.next;
        
        for(;curr!=null;){
            if(prev.val == curr.val){
                prev.next = curr.next;
                curr= curr.next;
            }else{
                prev = prev.next;
                curr = curr.next;
            }
        }        
        return head;
    }
	
	public ListNode mergeTwoLists(ListNode A, ListNode B) {
        ListNode head = null; ListNode tail = null;
        ListNode newNode = null;
       for(;A != null || B != null;){
            if( A != null && B != null){
                if(A.val < B.val){
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
            }else {
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
	 public int removeElement(int[] A, int elem) {
	        int n = A.length;
	        if(n == 0)
	            return 0;
	        int dist = 0;
	        for(int i =0; i < n; i++){
	            if(A[i] != elem){
	                A[dist++] = A[i];
	            }
	        }
	        return dist;
	        
	    }
	
	public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)    
            return head;
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode curr = head;
        int length = 0;
        for(;curr != null; curr = curr.next) length++;
        
        if(n > length )  
            return head;
        int k = length - n - 1 + 1;
        ListNode pre = sentinel;
        curr = head;
        for(int i =0; i < k; i++){
            pre = pre.next;
            curr = curr.next;
        }
        pre.next = pre.next.next;
        return sentinel.next;
        
    }
	
	 public void reorderList(ListNode head){
         if(head == null || head.next == null || head.next.next == null)
            return;
         
         int length  = 0; 
         ListNode curr = head;
         for(;curr!=null; curr = curr.next) length++;
         
         int n = length/2;
         curr = head;ListNode prev = curr;
         
         
         if(length % 2 == 0){
            for(int i = 0; i < n; i++){
                prev = curr;
                curr = curr.next;
            }                 
            
            ListNode newhead = reverse(prev);
            prev.next = null;
            
             ListNode it = head;
             for(int i =0; i < n; i ++){
                 ListNode ins = newhead;
                 newhead = newhead.next;

                 ListNode tmp = it.next;
                 it.next = ins;
                 ins.next = tmp;
                 it = tmp;
             }
         }else{
             for(int i =0; i < n ; i++){
                 curr = curr.next;
             }
             ListNode newhead = reverse(curr);
             curr.next = null;
             ListNode it = head;
             
             for(int i =0;i < n; i++){
                 ListNode ins = newhead;
                 newhead  = newhead.next;
                 
                 ListNode tmp = it.next;
                 it.next = ins;
                 ins.next = tmp;
                 it = tmp;
             }
         }
     }
     
     public ListNode reverse(ListNode prehead){
         if(prehead == null)
            return prehead;
         ListNode prev = prehead.next;
         if(prev == null)
            return prehead;
         ListNode curr = prev.next;
         for(;curr != null;){
            ListNode tmp = prev;
            prev = curr;
            curr = curr.next;
            prev.next = tmp;
         }
         
         ListNode res = prehead.next;
         prehead.next.next = null;
         prehead.next = prev;
         return res;
     }
 
	
	 public ListNode reverseBetween(ListNode head, int m, int n) {
	        //1 -> 2 -> 3 -> 4 -> 5
	        //from index m-1 to n-1;
	        if(m==n)
	            return head;
	        int length = 0;
	        ListNode curr = head;
	        for(;curr != null;curr = curr.next) length ++;
	        int id1 = m-1;
	        int id2 = n-1;
	        ListNode sentinel = new ListNode(0);
	        sentinel.next = head;
	        
	        ListNode pre = null;
	        ListNode aft = null;
	        ListNode it = sentinel;
	        for(int i =0; i <= id2; i++){
	            if(i == id1){
	                pre = it;
	            }
	            if(i == id2){
	                aft = it;
	                break;
	            }
	            it = it.next;
	        }
	        aft = aft.next.next;
	        
	        reverse(pre, aft);
	        return sentinel.next;
	    }
	    
	    
	    public void reverse(ListNode pre, ListNode aft){
	        ListNode prev =pre.next;
	        ListNode curr = prev.next;
	        
	        for(; curr != aft; ){
	            ListNode tmp = prev;
	            prev = curr;
	            curr = curr.next;
	            prev.next = tmp;
	        }
	        
	        pre.next.next = aft;
	        pre.next = prev;
	        
	    }
	
	public ListNode rotateRight(ListNode head, int n) {
	    if(head == null || n == 0)
	    	return head;
	    int count = 0;
	    ListNode curr = head;
	    for(;count < n ; count++){ //n % length
	        if(curr.next == null)
	            curr = head;
	        else
	            curr = curr.next;
	    }
	    
	    if(curr == head)
	        return head;
	    
	    ListNode tail = head;
	    while(curr.next != null){
	        tail = tail.next;//tail++
	        curr = curr.next;//curr++
	    }
	    //window shift right
	    
	    ListNode res = tail.next;
	    tail.next = null;
	    curr.next = head;
	    return res;
	}
	
	public void sortColors(int[] A) {
	       
	       int[] bucket = new int[3];
	       int n = A.length;
	       
	       for(int i = 0; i < n; i++){
	            bucket[A[i]]++;
	       }
	        
	       for(int i = 0; i < n; i++){
	           if(i < bucket[0])
	            A[i] = 0;
	            else if(i < bucket[0] + bucket[1])
	            A[i] = 1;
	            else
	                A[i] = 2;
	        
	       }  
	       
	    }
	@Test
	public void testswapPairs1(){
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		ListNode res = swapPairs1(head);
		for(;res!=null;){
			System.out.print(res.val + ",");
			res = res.next;
		}
	}
	
	public ListNode swapPairs1(ListNode head) {
	       ListNode sentinel = new ListNode(0);
	       sentinel.next = head;
	       
	       ListNode pre = sentinel;
	       ListNode curr =  head;
	       for(int i = 1; curr!= null; i++){
	           if( i % 2 == 0){
	               ListNode aft = curr.next;
	               pre = reverseBetween1(pre, aft);
	               curr = pre.next;
	               continue;
	           }
	           curr = curr.next;
	       }
	       return sentinel.next;
	    }
	    
	    public ListNode reverseBetween1(ListNode pre, ListNode aft){
	       
	        ListNode prev = pre.next;
	       
	        ListNode curr = prev.next;
	        
	        for(;curr!=aft;){
	            ListNode tmp = prev;
	            prev = curr;
	            curr = curr.next;
	            prev.next = tmp;
	        }
	        
	        ListNode res = pre.next;
	        pre.next.next = aft;
	        pre.next = prev;
	        return res;
	    }    
	    
	    
	    
	
	public String longestCommonPrefix2(String[] strs) {
	    int n = strs.length;
	    for(int i =0; i < strs[0].length(); i++){
	        char c = strs[0].charAt(i);
	        for(int j=0; j < n; j++){
                String s = strs[j];
                if(i >= s.length()){
                    return s;
                }
                if(s.charAt(i) != c){
                    return s.substring(0,i);
                }
	        }
	    }
	    return strs[0];
	 }
	
	 public int maxPoints1(Point[] points){
		    HashMap<Float, Integer> map = new HashMap();
		    int maxNum = 0;
		    for(int i =0; i < points.length; i++){
		        Point A = points[i];  
		        int dup = 0;
		        map.clear();
		        for(int j=0; j < points.length; j++){
		            Point B = points[j];
		            if( i == j ) continue;
		            //duplicate
		            if( A.x == B.x && A.y == B.y )
		                dup++;
		            else if( A.x == B.x ){
		                if(map.get(Float.MAX_VALUE)==null){
		                    map.put(Float.MAX_VALUE, 1);
		                }else{
		                    int value = map.get(Float.MAX_VALUE);
		                    map.put(Float.MAX_VALUE, value + 1);                    
		                }              
		            }else{
		                float tangent= (A.y - B.y)/(float)(A.x - B.x);
		                if(map.get(tangent) == null){
		                    map.put(tangent, 1);
		                }else{
		                    int value = map.get(tangent);
		                    map.put(tangent,value + 1);
		                }
		            }
		        }
		        
		        Iterator it = map.keySet().iterator();
		        for(;it.hasNext();){
		            float key = (float)it.next();           
		            int value = map.get(key);
		            if(value + dup > maxNum)
		                maxNum = value + dup;
		        }
		    }
		    return maxNum;		
		  }
	
	
	
	@Test
	public void test(){
		LRUCache cache = new LRUCache(3);
		cache.set(1, 10);
		cache.set(2, 20);
		cache.set(3, 30);
		
		cache.get(1);
		cache.get(2);
		cache.get(3);
		cache.set(4, 40);
		int a = cache.get(1);
		System.out.println(a);
	}
	
	
	public class LRUCache {
	    
	    HashMap<Integer, DoubleListNode> map = new HashMap();
	    int count = 0;
	    int capacity = 0;
	    // the scope of this one is in here.
	    DoubleListNode head = null;
	    DoubleListNode tail = null;
	    DoubleListNode newNode = null;
	    
	    class DoubleListNode{
	        int key,val;
	        DoubleListNode prev,next;
	        DoubleListNode(int key, int val){
	            this.key = key;
	            this.val = val;
	        }
	    }
	    
	    LRUCache(int capacity){
	        this.capacity = capacity;
	    }
	    
	    public int get(int key){
	        DoubleListNode node = map.get(key);
	        if(node == null){
	            return -1;
	        }else{
	            //move the node to the first and return the value
	            if(head == node){//first one
	                return node.val;
	            }else if(tail == node){//last one
	                tail = tail.prev;
	                tail.next = null;
	                
	                node.next = head;
	                head.prev = node;
	                head = node;
	            }else{//not the first, not the last one
	                DoubleListNode pre = node.prev;
	                pre.next = node.next;
	                node.next.prev = pre;
	                
	                node.next = head;
	                head.prev = node;
	                head = node;
	            }
	            return node.val;
	        }
	    }   
	    
	    
	    public void set(int key, int value){
	        //update
            newNode = new DoubleListNode(key, value);

	        if(map.containsKey(key)){
	            DoubleListNode node = map.get(key);
	            node.val = value;
	            
	            if(node == head){
	                return;
	            }else if(node == tail){
	                tail = tail.prev;
	                tail.next = null;
	                
	                //move to first
	                node.next = head;
	                head.prev = node;
	                head = node;
	            }else{
	                //node != head, node != tail 
	                DoubleListNode pre = node.prev;
	                pre.next = node.next;
	                node.next.prev = pre;
	                
	                //move to first
	                node.next = head;
	                head.prev = node;
	                head = node;
	            }
	        }else{
	            count++;
	            if(count <= capacity){
	                //insert
	                if(head == null){
	                    head = tail = newNode;
	                    map.put(key, newNode);
	                }else{
	                    newNode.next = head;
	                    head.prev = newNode;
	                    head = newNode;
	                    map.put(key, newNode);
	                }
	            }else{
	                count = capacity;
	                //remove
	                if(capacity == 1){
	                    map.remove(tail.key);
	                    head = null;
	                    tail = null;
	                }else{
	                    tail = tail.prev;
	                    tail.next = null;
	                    map.remove(tail.key);
	                }
	                
	                //insert
	                if(head == null){
	                    head = tail = newNode;
	                    map.put(key,newNode);
	                }else{
	                	//move to first
	                    newNode.next = head;
	                    head.prev = newNode;
	                    head = newNode;
	                    map.put(key,newNode);
	                }
	            }
	        }
	    }
	}
	
	 @Test
	 public void testmerge(){
		 ListNode A = new ListNode(4);
		 A.next = new ListNode(5);
		 ListNode B = new ListNode(1);
		 B.next = new ListNode(2);
		 B.next.next = new ListNode(3);
		 ListNode res = merge(A,B);
		 
		 for(;res != null;){
			 System.out.print(res.val + ",");
			 res = res.next;
		 }
	 }
	
	 @Test
	 public void testsortList(){
		 ListNode A = new ListNode(5);
		 A.next = new ListNode(4);
		 A.next.next = new ListNode(3);
		 A.next.next.next = new ListNode(2);
		 A.next.next.next.next = new ListNode(1);
		 ListNode res = sortList(A);
		 for(;res != null;  res = res.next){
			 System.out.print(res.val + ",");
		 }
		 
		 
		 
		 
	 }
	 
	 
	 //Time O(nlgn), Space O(1)
		public ListNode sortList(ListNode head) {
	        if(head == null)
	            return null;
	        if(head.next == null)
	            return head;
	        if(head.next.next == null){
	            ListNode A = head;
	            ListNode B = head.next;
	            if(B.val < A.val){
	                B.next = A; 
	                A.next = null;
	                return B;
	            }
	            return head;
	        }
	        //length >= 3
	        int length = 0; ListNode curr = head;
	        for(;curr!= null; curr = curr.next) length++;
	        
	        int n = length/2;
	       
	        curr = head; ListNode prev = head;
	        for(int i =0; i < n ; i ++){
	            prev = curr;
	            curr = curr.next;
	        }
	        prev.next = null;//two seperate list now
	        ListNode A = sortList(head);
	        ListNode B = sortList(curr);
	        return merge(A, B);
	    }
	    //O(n) -> O(1)
	    public ListNode merge(ListNode A, ListNode B){
	        ListNode newhead = null; ListNode newNode = null;
	        ListNode tail = null;
	        for(;A != null || B != null;){
	            if(A != null && B !=null){
	                if(A.val < B.val){
	                    newNode  = A;
	                    A = A.next;
	                    newNode.next = null;
	                }else{
	                    newNode = B;
	                    B = B.next;
	                    newNode.next = null;
	                }
	            }else if( A != null){
	                newNode = A;
	                A  =  A.next;
	                newNode.next = null;
	            }else{
	                newNode = B;
	                B = B.next;
	                newNode.next = null;
	            }
	            
	            if(newhead == null){
	                newhead = tail = newNode;
	            }else{
	                tail.next = newNode;
	                tail = tail.next;
	            }
	        }
	        return newhead;
	    }
	
	public class LRUCache2 {
        private HashMap<Integer, DoubleListNode> map = new HashMap();
        private DoubleListNode head = null;
        private DoubleListNode tail = null;
        private DoubleListNode newNode = null;
        private int count = 0;
        private int capacity = 0;    
		
        class DoubleListNode{
            int key,val;
            DoubleListNode prev,next;
            DoubleListNode(int key, int val){
                this.key = key; 
                this.val = val;
            }
        }        
	
		public LRUCache2(int capacity) {
		    this.capacity = capacity;
		}

		public int get(int key) {
            if(map.get(key) == null)
                return -1;
            else{
            	DoubleListNode node = map.get(key);
                movehead(map.get(key));
                return node.val;
            }
		
		}
	    
	    
		public void set(int key, int value) {
            if(map.containsKey(key)){
                //only need to update
                DoubleListNode node = map.get(key);
                node.val = value;
                movehead(node);
            }else{
                count ++;
                newNode = new DoubleListNode(key,value);
                if(count <= capacity){
                    head = insert(newNode);
                }else{
                    count = capacity;
                    remove();
                    head = insert(newNode);
                }
                map.put(key, newNode);
            }
		}
        
        public void remove(){
            if(capacity == 1){
                map.remove(tail.key);
                head = null;
                tail = null;
                return;
            }
            tail = tail.prev;
            tail.next = null;
            map.remove(tail.key);
        }
        
        		
        public DoubleListNode insert(DoubleListNode newNode){
            if(head == null){
                head = tail = newNode;
                return head;
            }
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return head;
        }
		
		public void movehead(DoubleListNode node){
		    //Assume node is not null
		    if(node == head)
		        return;
		    if(node == tail){
		        tail = tail.prev;
		        tail.next = null;
		        
		        node.next = head;
		        head.prev = node;
		        head = node;
		        return;
		    }
		    //node is not equal to, remove in the current place
		    DoubleListNode pre = node.prev;
		    pre.next = node.next;
		    node.next.prev = pre;
		    
		    //move to first
		    node.next = head;
		    head.prev = node;
		    head = node;
		}
	}
	
	
	
	
	//I do not know what happen here, but I think the problem is the scope problem.
	@Test
	public void testLRUCache3(){
		LRUCache2 cache = new LRUCache2(4);
		cache.set(1, 10);
		cache.set(2, 20);
		cache.set(3, 30);
		cache.set(4, 40);
		cache.set(5, 50);
		cache.get(2);
		
	}
	
	
	//Runtime: 508 ms
	public class LRUCache3{

		class DoubleListNode {

			int key;
			int val;

			DoubleListNode prev, next;
			DoubleListNode(int key, int val) {
				this.key = key;
				this.val = val;
			}
		}

		private HashMap<Integer, DoubleListNode> map = new HashMap();
		private int capacity;
		private DoubleListNode head = null;
		private DoubleListNode tail = null;
		private DoubleListNode newNode = null;
		private int count = 0;

		// When the cache reached its capacity, it should invalidate the least
		// recently used item before inserting a new item.
		LRUCache3(int capacity) {
			this.capacity = capacity;
		}

		public int get(int key) {
			if (map.get(key) == null) {
				return -1;
			}

			DoubleListNode node = map.get(key);
			if (count > 1) {
				if (tail == node) {

					tail = tail.prev;
					tail.next = null;

					node.next = head;
					head.prev = node;
					head = node;
					return node.val;

				} else {
					if (head == node) {
						return node.val;
					} else {

						DoubleListNode pre = node.prev;
						pre.next = node.next;
						node.next.prev = pre;

						node.next = head;
						head.prev = node;
						head = node;

						return node.val;

					}
				}

			} else {
				return node.val;
			}
		}
	    
	    
		void set(int key, int value) {

			/* update */
			if (map.containsKey(key)) {
				    //move the node to first
				    DoubleListNode node = map.get(key);
				    node.val = value;
				    if(head == node){
				        return;
				    }
				    if(tail == node){
				        tail = tail.prev;
				        tail.next = null;
                        
                        node.next = head;
                        head.prev = node;
                        head = node;
                        return;
				    }
				    
				    DoubleListNode pre  = node.prev;
				    pre.next = node.next;
				    node.next.prev = pre;
				    
				    node.next = head;
				    head.prev = node;
				    head = node;

			} else {
			 /*insert new node */
			    newNode = new DoubleListNode(key, value);
			    count ++;
				if (count <= capacity) {
					if (head == null && tail == null) {
						head = tail = newNode;

					} else {
						newNode.next = head;
						head.prev = newNode;
						head = newNode;
					}
					map.put(key, newNode);

				} else {
				    /*greater than capacity and need to delete the oldest node*/
					count = capacity;
                    if(capacity > 1){
					int keydelete = tail.key;
					tail = tail.prev;
					tail.next = null;
					map.remove(keydelete);
					
					if(head == null && tail == null){
						head = tail = newNode;
					}else{
						newNode.next = head;
						head.prev = newNode;
						head = newNode;
					}
					map.put(key, newNode);
                    }else{
                        int keydelete = tail.key;
                        map.remove(keydelete);
                        head = null;
                        tail = null;
                        head = tail = newNode;
                        map.put(key,newNode);
                    }
				}

			}
		}
	}
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	  
	    
	
	
	
//	public int maxPoints(Point[] points){
//		HashMap<Float, Integer> statistic;
//		int maxNum = 0;
//		for(int i = 0; i < points.length; i ++){
//			statistic.clear();
//			statistic[Integer.MIN_VALUE] = 0;
//			int duplicate  = 1;
//			for(int j =0; j < points.length; j++){
//				if(j == i) continue;
//				if(points[j].x == points[i].x && points[j].y == points[i].x){
//					duplicate ++;
//					continue;
//				}
//				float key = (points[j].x-points[i].x == 0? Integer.MAX_VALUE : (float)(points[j].y - points[i].y)/(points[j].x - points[i].x));
//				statistic[key] ++;
//
//			}
//			for(Iterator it = statistic.iterator(); it.hasNext(); ){
//				if(it.next() + duplicate > maxNum){
//					maxNum = it.next() + duplicate;
//				}
//			}
//
//		}
//	}
	
	//Runtime: 516 ms
	public int maxPoints(Point[] points){
		HashMap<Float, Integer> statistic = new HashMap();
		int maxNum = 0;
		for(int i =0; i< points.length; i++){
			statistic.clear();
			statistic.put(Float.MIN_VALUE, 0);
			
			int dup = 1;
			for(int j = 0; j < points.length; j++){
				Point A  = points[j];
				Point B  = points[i];
				if(j == i) continue;
				if(A.x == B.x  && A.y == B.y){
					dup ++;
					continue;
				}
				
				if(A.x == B.x){// 
					if(statistic.get(Float.MAX_VALUE) == null){
						statistic.put(Float.MAX_VALUE, 1);
					}else{
						int tmp = statistic.get(Float.MAX_VALUE);
						statistic.put(Float.MAX_VALUE, tmp + 1);
					}
					continue;
				}
				float key = (A.y - B.y)/ (float)(A.x - B.x);
				if(statistic.get(key) == null){
					statistic.put(key, 1);
				}else{
					statistic.put(key, statistic.get(key) + 1);
				}
			}
			
			Iterator it = statistic.keySet().iterator();
			for(;it.hasNext();){
				Float key = (Float) it.next();
				int value = statistic.get(key);
				if(value + dup > maxNum){
					maxNum = value + dup;
				}
			}
		}
		return maxNum;
	}
	
	
	
	
	

	
	
//	 Iterator it = mp.entrySet().iterator();
//	    while (it.hasNext()) {
//	        Map.Entry pairs = (Map.Entry)it.next();
//	        System.out.println(pairs.getKey() + " = " + pairs.getValue());
//	        it.remove(); // avoids a ConcurrentModificationException
//	    }
//	    
	 @Test
	 public void testhashmap(){
		 
		 HashMap<Integer, String> map = new HashMap();
		 map.put(1, "Xiaomin");
		 map.put(2, "Wu");
		 Iterator it = map.entrySet().iterator();
		 for(;it.hasNext();){
			 Map.Entry entry = (Entry) it.next();
			 System.out.println(entry.getKey() + "=" + entry.getValue());
		 }
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
	
	 private class Tan{
	        int index;
	        Double val;
	        Tan(){
	        	this.index = 0;
	        	this.val = 0.0;
	        }
	        Tan(int index, Double val){ this.index = index; this.val =  val;}
	    }
	    
	    
//	    public int maxPoints(Point[] points) {
//	        HashMap<Tan, Integer> tangent = new HashMap();
//	        HashMap<Point, Integer> repeat = new HashMap();
//	        int max = 0;
//	        
//	        int n = points.length;
//	        for(int i =0; i < n; i++){
//	            Point A = points[i];
//	            boolean isRepeat = false;
//	            if(repeat.get(A) != null)
//	            	isRepeat = true;
//	            for(int j =i+1; j < n; j++){
//	                Point B = points[j];
//	                if(A.x == B.x && A.y == B.y && isRepeat == false){
//	                    if(repeat.get(A) == null)
//	                        repeat.put(A, 1);
//	                    else{
//	                        repeat.put(A,repeat.get(A)+1);
//	                    }
//	                }else if( A.x == B.x){
//	                   
//	                   
//	                
//	                }
//	                   
//	                   
//	              
//	            }
//	        }
//	    }
	
	//O(n!)
	 public List<String> letterCombinations(String digits) {
	        HashMap<Character, String> dict = new HashMap();
	        dict.put('2', "abc");
	        dict.put('3', "def");
	        dict.put('4', "ghi");
	        dict.put('5', "jkl");
	        dict.put('6', "mno");
	        dict.put('7', "pqrs");
	        dict.put('8', "tuv");
	        dict.put('9', "wxyz");
	        StringBuffer buf = new StringBuffer();
	        List<String> res = new ArrayList();
	        char[] A= digits.toCharArray();
	        dfs(0, A, buf, res, dict);
	        return res;
	    }
	    
	    void dfs(int pos, char[] A,  StringBuffer buf, List<String> res, HashMap<Character, String> dict){
	        if(pos >= A.length){
	            res.add(buf.toString());
	            return;
	        }
	        
	        char num = A[pos];
	        String ts = dict.get(num);
	        for(int i =0; i < ts.length(); i++){
	            buf.append(ts.charAt(i));
	            dfs(pos+1, A, buf, res, dict);
	            buf.deleteCharAt(buf.length() - 1);
	        }
	    }
	
	  //O(n^2)
	  public String longestCommonPrefix(String[] strs) {
	        
	        int n = strs.length;
	        if( n==0)
	            return "";
	        for(int i=0; i< strs[0].length(); i ++){
	            char c  = strs[0].charAt(i);
	            for(int j = 1; j < n; j ++){
	                if(i >= strs[j].length() )                    
	                    return strs[0].substring(0,i+1);
	                if(c != strs[j].charAt(i))
	                    return strs[0].substring(0,i+1);
	            }
	        }
	        return strs[0];
	    }
	
	
	
	//Runtime: 364 ms
	public ListNode swapPairs(ListNode head) {
        ListNode sentinel = new ListNode(0);
		sentinel.next = head;
		
		ListNode pre = sentinel;
		ListNode curr = head;
		
		for(int i = 1; curr != null; i++){
			if( i % 2 == 0){
				ListNode aft = curr.next;
				pre = reverseBetweentwo(pre, aft);
				curr = pre.next;
				continue;
			}
			curr = curr.next;
		}
		
		return sentinel.next;
    }
    
    public ListNode reverseBetweentwo(ListNode pre, ListNode aft){
        ListNode prev = pre.next;
        if(prev.next == null)
            return pre;
        ListNode curr = prev.next;
        for(;curr != aft;){
            ListNode tmp = prev;
            prev = curr;
            curr = curr.next;
            prev.next = tmp;
        }
        
        pre.next.next = aft;
        ListNode res = pre.next;
        pre.next = prev;
        return res;
    }

	
	
//	public void swappairs(ListNode head){
//		ListNode sentinel = new ListNode(0);
//		sentinel.next = head;
//		
//		ListNode pre = sentinel;
//		ListNode curr = head;
//		
//		for(int i = 1; curr != null; i++){
//			if( i % 2 == 0){
//				ListNode aft = curr.next;
//				pre = reverseBetween(pre, aft);
//			}
//			
//		}
//	}
	
	
	ListNode reverseBetween(ListNode pre, ListNode aft){
		if(pre.next == null)
			return pre;
		ListNode prev = pre.next;
		if(prev.next == aft){
			return pre;
		}
		ListNode curr = prev.next;
		
		for(;curr != aft;){
			ListNode tmp = prev;
			prev = curr;
			curr = curr.next;
			prev.next = tmp;
		}
		pre.next.next = aft;
		pre.next = prev;
		return prev;
	}
	
}
