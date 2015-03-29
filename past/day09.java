package leetcode;

import org.junit.Test;

public class day09 {
	
	
	@Test
	public void testreorderList2(){
		ListNode test = new ListNode(1);
		test.next = new ListNode(2);
		test.next.next = new ListNode(3);
		test.next.next.next = new ListNode(4);
		test.next.next.next.next = new ListNode(5);
		
		reorderList(test);
		ListNode curr = test;
		for(; curr != null; curr = curr.next){
			System.out.print(curr.val);
		}
		
		
	}
	
	
	
	@Test
	public void testreorderList(){
		ListNode test = new ListNode(1);
		test.next = new ListNode(2);
		test.next.next = new ListNode(3);
		test.next.next.next = new ListNode(4);
		reorderList(test);
		
		ListNode curr = test;
		int length = 0;
		for(; curr!= null; ){
			length++;
			curr = curr.next;
		}
		
		curr = test;
		for(int i =0; i < length ; i ++){
			System.out.print(curr.val + ",");
			curr = curr.next;
		}
	}
	
	@Test
	public void testReverse1(){
		ListNode test = new ListNode(1);
		test.next = new ListNode(2);
		test.next.next = new ListNode(3);
		test.next.next.next = new ListNode(4);
		ListNode sentinel  = new ListNode(0);
		sentinel.next = test;
		reverse(sentinel);
		ListNode curr = sentinel.next;
		for(int i =0; curr!=null; i ++){
			System.out.println(curr.val);
			curr = curr.next;
		}
	}
	
	
	public void reorderList(ListNode head) {
        // avoid corner case
        if(head == null)
            return;
        if(head.next == null)
            return;
        if(head.next.next == null)
            return;
        // start normal condition    1 2 3 => 1 3 2
        int length = 0; ListNode curr = head;
        for(;curr != null;){
            length ++ ;
            curr  = curr.next;
        }
        // get length
        if(length % 2 == 0){
            //even case
            int n = length /2;
            ListNode trav = head;
            for(int i = 0; i < n ; i++){
                trav = trav.next;
            }
            reverse(trav);
            // 1 2 4 3
            ListNode newhead = trav.next;
            trav.next = null;
            ListNode slow = head;
            
            for(int i =0; i < n -1; i++){
                // insert slow next
                ListNode ins = newhead;
                newhead = newhead.next;
                
                // insert
                ListNode tmp = slow.next;
                slow.next = ins;
                ins.next = tmp;
                
                slow = tmp;
            }
        }else{
            int n = length/2;
            ListNode trav = head;
            for(int i =0;i < n ;i++){
                trav = trav.next;
            }
            
            reverse(trav);
            ListNode newhead = trav.next;
            //System.out.println("newhead=" + newhead.val);
            trav.next = null;
            
            ListNode slow = head;
            for(int i =0; i < n; i++){
                ListNode ins = newhead;
                newhead = newhead.next;
                
                ListNode tmp = slow.next;
                slow.next = ins;
                ins.next = tmp;
                
                slow = tmp;
            }
        }
    }
    
    
    
    
    
    public void reverse(ListNode prehead){
        if(prehead.next == null)
            return;
        
        ListNode prev = prehead.next;
        if(prev.next == null)
            return;
        ListNode curr = prev.next;
        
        for(;curr!=null;){
            ListNode tmp = prev;
            prev = curr;
            curr = curr.next;
            prev.next = tmp;
        }
        ListNode tmp = prehead.next;
        prehead.next = prev;
        tmp.next = null;
    }
	
	
	
	
	@Test
	public void testmedian(){
		ListNode test = new ListNode(1);
		test.next  = new ListNode(2);
		test.next.next = new ListNode(3);
		
		ListNode sentinel = new ListNode(0);
		int length = 0;
		ListNode curr = test;
		for(;curr!= null;){
			length++;
			System.out.print(curr.val + ",");
			curr = curr.next;
		}
		System.out.println("length = " + length);
		ListNode median = findmedian(test,length);
		System.out.println("median = " + median.val);
		
		
		
	}
	ListNode findmedian(ListNode head, int length){
		if(head == null){
			return null;
		}
		int n;
		
		//we define median by this way
//		if(length%2 == 0){
//			n = length/2;
//		}else if(length == 1){
//			return head;
//		}else{
//			n = length/2 + 1;
//		}
		n = length/2;
		
		ListNode curr = head;
		for(int i =0; i < n; i ++){
			curr = curr.next;//curr will finally equals to n curr++  curr < n 
		}
		return curr;
	}
	
	
	
	
	
	
	
	@Test
	public void testReverse(){
		ListNode test = new ListNode(1);
		test.next = new ListNode(2);
		ListNode sentinel = new ListNode(0);
		sentinel.next = test;
		reverselinkedlist(sentinel);
		ListNode it = sentinel.next;
		int length = 0;
		ListNode curr = test;
		
		for(;curr!= null;){
			curr = curr.next;
			length++;
		}
		
		for(int i =0; i < length; i ++){
			System.out.print(it.val);
			it = it.next;
		}
		
	}
	
	
	public void reverselinkedlist(ListNode prehead){
		if(prehead.next == null)
			return ;
		ListNode prev = prehead.next;
		if(prev.next == null)
			return;
		ListNode curr = prev.next;
		
		while(curr != null){
			ListNode tmp = prev;
			prev = curr;
			curr= curr.next;
			prev.next = tmp;
		}
		
		ListNode res = prehead.next;
		res.next = curr;
		prehead.next = prev;
		
	}
	
	
	
	
	
	
	@Test
	public void testNode(){
		ListNode test = new ListNode(1);
		test.next  = new ListNode(2);
		test.next.next = new ListNode(3);
		test.next.next.next = new ListNode(4);
		
		int length = 0;
		for(ListNode trav = test;  trav != null; trav = trav.next){
			length++;
		}
		
		ListNode it = test;
		for(int i =0; i< (length)/2; i++){
			it = it.next;
		}
		System.out.println(it.val);
	}
	
	@Test
	public void testlength(){
		int length = 0;
		
		ListNode test = new ListNode(0);
		test.next = new ListNode(1);
		test.next.next = new ListNode(2);
		test.next.next.next = new ListNode(3);
		
		for(ListNode trav = test; trav != null; trav = trav.next){
			length++;
		}
		
		System.out.println(length);
		
	}
	
	
	
	
	
	@Test
	public void testrotateRight(){
		
		ListNode test = new ListNode(1);
		test.next  = new ListNode(2);
		test.next.next = new ListNode(3);
		test.next.next.next = new ListNode(4);
		test.next.next.next.next = new ListNode(5);
		
		ListNode res = rotateRight(test, 2);
		System.out.println(res);
	}
	
	
	public ListNode rotateRight(ListNode head, int n){
        if(head == null || head.next == null){
            return head;
        }
        
        int count = 0;
        ListNode curr = head;
        ListNode tail = null;
        
        for(;;){
            if(count == n){
                tail = curr;
                break;
            }
            
            if(curr == null){
                return head;
            }
            if(curr.next == null)
                return head;
            count ++;
            curr = curr.next;
        }
        
        ListNode sentinel = new ListNode(0);
        ListNode pre = sentinel;
        pre.next = head;
        ListNode aft = tail.next;
        ListNode newpre = reverseK(pre, aft);
        reverseK(newpre, null);
        reverseK(sentinel, null);
        return sentinel.next;
    }
    
    public ListNode reverseK(ListNode pre, ListNode aft){
        if(pre == aft)
            return pre;
        if(pre.next == null)
            return pre;
        
        ListNode prev = pre.next;
        if(prev.next == null)
            return prev;
        ListNode curr = prev.next;
        
        for(;curr != aft;){
            ListNode tmp = prev;
            prev = curr;
            curr = curr.next;
            prev.next = tmp;            
        }
        ListNode res = pre.next;
        pre.next.next = curr;
        pre.next = prev;
        return res;
    }
	
	
	
    
    
    
    
    
	
	@Test
	public void testReverseKgroup(){
		ListNode test = new ListNode(1);
		test.next = new ListNode(2);
		test.next.next = new ListNode(3);
		test.next.next.next = new ListNode(4);
		ListNode res = reverseKGroup(test, 2);
		System.out.println(res);
	}
	
	public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null)
            return head;
        if(k == 1)
            return head;
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode pre = sentinel;
        ListNode curr = head;
        int count = 0;
        for(;curr != null;){
            count ++;
            ListNode aft = curr.next;
            if(count % k == 0){
                pre = reverseK1(pre, aft);
            }
            curr = aft;
            //we need to keep track of pre and curr;
        }
        return sentinel.next;
    }
    
    public ListNode reverseK1(ListNode pre, ListNode aft){
        ListNode prev = pre.next;
        if(pre == aft)
            return pre;
        if(prev.next == null)
            return pre;
        ListNode curr = prev.next;
        for(;curr != aft;){
            ListNode tmp = prev; 
            prev = curr;
            curr = curr.next;
            prev.next = tmp;
        }
        ListNode res =pre.next;// return the last one 1 as the pre node to the next one
        pre.next.next = curr;
        pre.next = prev;
        return res;
    }    
    
	
	
	
	
	   // 428ms for 166 test cases
	    public ListNode deleteDuplicates2(ListNode head) {
	        if (head == null || head.next == null)
	            return head;
	 
	        // Use a sentinel node in case the first node will be removed
	        ListNode sentinel = new ListNode(0);
	        sentinel.next = head;
	 
	        ListNode previous = head, current = head.next;
	        ListNode lastDistinct = sentinel;   // The last distinct node wrt the current node
	        if (previous.val != current.val)
	            lastDistinct = head;
	 
	        while (current != null) {
	            if (current.val != previous.val &&
	                    (current.next == null || current.val != current.next.val)) {
	                // The current node is a new distinct node; remove all nodes between
	                // the last distinct one and it
	                lastDistinct.next = current;
	                lastDistinct = current;
	            }
	            previous = current;
	            current = current.next;
	        }
	        lastDistinct.next = null;   // Make sure the list ends properly
	 
	        return sentinel.next;
	    }
	
	
	public ListNode deleteDuplicates(ListNode head){
        if(head == null || head.next == null)
            return head;
        
        ListNode prev = head;
        ListNode curr = head.next;
        
        for(;curr != null;){
            if(prev.val == curr.val){
                prev.next = curr.next;
                curr = curr.next;
            }else{
                prev = prev.next;
                curr = curr.next;
            }
        }
        return head;
    }
	
	
	public ListNode partition(ListNode head, int x) {
        ListNode A = new ListNode(0);
        ListNode listA = A;
        ListNode B = new ListNode(0);
        ListNode listB = B;
        if(head == null)
            return head;
        if(head.next == null)
            return head;
        ListNode it = head;
        for(;it != null; it = it.next){
            if(it.val < x){
                listA.next = new ListNode(it.val);
                listA = listA.next;
            }else{
                listB.next = new ListNode(it.val);
                listB = listB.next;
            }
        }
        
        listA.next = B.next;
        return A.next;
        
    }
	
	
	
	
	public ListNode detectCycle(ListNode head) {
        if(head == null)
            return null;
            
        ListNode slow = head;
        ListNode fast = head;
        boolean firsttime = true;
        for(;;){
            if(fast == null)
                return null;
            if(slow == fast){
                if(firsttime){
                    firsttime = false;
                }else{
                    break;
                }
            }
            fast = fast.next;
            if(fast == null)
                return null;
            fast = fast.next;
            slow = slow.next;
        }
        
        slow = head;
        for(;;){
            if(slow == fast){
                return slow;
            }
            slow = slow.next;
            fast = fast.next;
        }
    }
	
	
	
	

}
