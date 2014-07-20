package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class day10 {
	
	
	
	@Test
	public void testsortlist(){
		ListNode head = new ListNode(5);
		head.next  = new ListNode(4);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(2);
		head.next.next.next.next  = new ListNode(1);
		ListNode res = sortList(head);
		ListNode curr = res;
		for(;curr != null; ){
			System.out.println(curr.val + ",");
			curr = curr.next;
		}
	}
	
	//Time O(nlgn), Space O(1)
	public ListNode sortList(ListNode head) {
        if(head ==  null)
            return head;
        if(head.next == null)
            return head;
        if(head.next.next ==null){
            ListNode fir = head;
            ListNode sec = head.next;
            if(fir.val > sec.val){
                sec.next = fir;
                fir.next = null;
                return sec;
            }else{
                return head;
            }
        }
        
        int length  = 0; ListNode curr = head;
        for(;curr!=null; curr=curr.next) length++;
        
        curr = head;
        ListNode prev = head;
        for(int i =0; i < length/2; i++){
            prev = curr;
            curr = curr.next;
        }
        prev.next = null;
                
        ListNode A =  sortList(head);
        ListNode B =  sortList(curr);
        
        return merge(A,B);        
    }
    
	
	
	
	@Test
	public void testmerge(){
		ListNode A = new ListNode(1);
		A.next = new ListNode(2);
		ListNode B = new ListNode(3);
		B.next = new ListNode(4);
		ListNode res = merge(A,B);
		ListNode curr = res;
		for(;curr!=null;){
			System.out.print(curr.val + ",");
			curr = curr.next;
		}
	}
    
    public ListNode merge(ListNode A, ListNode B){
        ListNode newhead = null;
        ListNode newNode = null;
        ListNode tail = null;
        while(A != null || B != null){
            if( A!=null && B != null){
                if(A.val < B.val){
                    newNode = A;
                    A = A.next;
                    newNode.next = null;
                }else{
                    newNode = B;
                    B = B.next;
                    newNode.next = null;
                }
            }else if( A!= null){
            	newNode = A;
            	A = A.next;
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
	
	
	public void sortColors(int[] A) {
        int n = A.length;
        
        int[] bucket = new int[3];
        for(int i=0; i<A.length; i++){
            bucket[A[i]]++;
        }
        
        
        for(int i =0; i < n;i++){
            if(i < bucket[0])
                A[i] = 0;
            else if(i < bucket[0] + bucket[1])
                A[i] = 1;
            else 
                A[i] = 2;
        }
    }
	
	
	// 1 -> 2 -> 3 -> 4 -> 5 -> 6
	
	@Test
	public void testrotateRight1(){
		ListNode head = generate(3);
		rotateRight1(head, 10);
	}
	
	
	public ListNode rotateRight1(ListNode head, int n) {
	    if( n==0 || head == null)
	        return head;
	    
	    int count = 0;
	    ListNode trav = head;
	    for(int i =0; i < n; i ++){  // mod function
	        if(trav.next == null){
	            trav = head;
	        }else{
	            trav = trav.next;
	        }
	    }
	    
	    if(trav == head)
	        return head;
	       
	    ListNode n1 = head;
	    
	    for(;trav.next != null;){
	        trav = trav.next;
	        n1 = n1.next;
	    }
	    
	    ListNode tmp = head;
	    head = n1.next;
	    trav.next = tmp;
	    n1.next = null;

        return head;	    
	}
	
	
	
	
	
	@Test
	public void testrotateRight(){
		ListNode head = generate(5);
		ListNode res = rotateRight(head,4);
		for(;res != null;){
			System.out.print(res.val + ",");
			res = res.next;
		}
	}
	
	
	
	public ListNode rotateRight(ListNode head, int k) {
		if(k == 0)
			return head;
		

		int n = 0;
		ListNode curr = head;
		for (; curr != null; curr = curr.next)
			n++;
		if(k>= n)
			return head;
		int m = n - k;

		ListNode tail = head;

		for (int i = 0; i < m-1; i++) {
			tail = tail.next;
		}
		ListNode newhead = tail.next;
		tail.next = null;
		ListNode tmp = head;
		head = newhead;

		tail = head;
		while (tail.next != null) {
			tail = tail.next;
		}
		tail.next = tmp;
		return head;
	}
	
	
	
	
	@Test
	public void testreverseKGroup(){
		ListNode head = generate(5);
		
		ListNode res = reverseKGroup(head, 2);
		
		ListNode curr = res;
		for(;curr!= null; ){
			System.out.println(curr.val + ",");
			curr = curr.next;
		}
	}
	
	
	
	
    public ListNode reverseKGroup(ListNode head, int k) {
		
		ListNode sentinel = new ListNode(0);
		sentinel.next = head;

		int count =1;
		ListNode pre = sentinel;
		ListNode curr = head;
		
		for(;curr != null;){
			if(count % k == 0){
				ListNode tmp = pre.next;
				ListNode aft = curr.next;
				reverse(pre,aft);
				pre = tmp;
				curr = tmp.next;
				count++;
				continue;
				
			}
			count ++;
			curr = curr.next;
		}
		
		return sentinel.next;
	}
	
	public void reverseBetween(ListNode pre, ListNode aft){
		if(pre.next == null)
				return;
		if(pre.next == aft)
				return;
		
		ListNode prev = pre.next;
		if(prev.next == null)
				return;
		ListNode curr = prev.next;
		
		for(;curr != aft;){
			ListNode tmp = prev;
			prev = curr;
			curr = curr.next;
			prev.next = tmp;
		}
		
		pre.next.next= aft;
		pre.next = prev;
	}
	
	
	@Test 
	public void testreverseBetween33(){
		ListNode head = generate(1);
		ListNode sentinel = new ListNode(0);
		sentinel.next = head;
		reverseBetween(sentinel, null);
		ListNode curr = sentinel.next;
		for(;curr != null;){
			System.out.println(curr.val);
			curr = curr.next;
		}
	}
	
	
	@Test
	public void testreverse(){
		ListNode head = generate(2);
		ListNode sentinel = new ListNode(0);
		sentinel.next = head;
		reverse(sentinel, null);
		ListNode curr = sentinel.next;
		for(;curr != null; ){
			System.out.print(curr.val + ",");
			curr = curr.next;
		}
	}
	
	@Test
	public void testreverseBetween(){
		ListNode head = generate(2);
		reverseBetween(head, 1,2);
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
	
	
	
	public ListNode generate(int n){
		ListNode head = new ListNode(1);
		ListNode curr = head;
		n--;
		for(int i = 2; n > 0; i++){
			curr.next = new ListNode(i);
			n--;
			curr = curr.next;
		}
		return head;
	}
	
	
	
	 @Test
	 public void testreorderList(){
		ListNode head = generate(4);
//		for(int i =0; i< 4; i ++){
//			System.out.println(head.val +",");
//			head = head.next;
//		}
		
		 
		 reorderList(head);
		 
		 for(int i =0; i < 4; i++){
			 System.out.print(head.val + ",");
			 head = head.next;
		 }
	 }
	
	 public void reorderList(ListNode head) {
	        if(head == null)
	            return;
	        if(head.next == null)
	            return;
	            
	        int length = 0;
	        ListNode curr =head;
	        for(;curr!=null;curr = curr.next) length++;
	        
	        if(length % 2 == 0){
	            int n = length/2;
	            ListNode trav = head;
	            for(int i =0; i < n-1; i++) trav = trav.next;
	            reverse(trav);
	            ListNode newhead = trav.next;
	            trav.next = null;
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
	            int n = length/2;
	            ListNode trav = head;
	            for(int i =0; i < n; i++) trav = trav.next;
	            
	            reverse(trav);
	            ListNode newhead = trav.next;
	            trav.next = null;
	            ListNode it = head;
	            for(int i =0;i < n; i++){
	                ListNode ins = newhead;
	                newhead = newhead.next;
	                
	                ListNode tmp = it.next;
	                it.next = ins;
	                ins.next = tmp;
	                it = tmp;
	            }
	        }
	    }
	 
	 	public void reverse(ListNode prehead){
	 		if(prehead == null)
	 			return;
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
	 		prehead.next.next = null;
	 		prehead.next = prev;
	 		
	 	}
	 
	 
	 
	 
	 
	 
	
	 public int removeElement(int[] A, int elem) {
	        int n = A.length;
	        
	        int dist = 0;
	        for(int i=0; i < n; i++){
	            if(A[i] != elem){
	                A[dist] = A[i];
	                dist ++;
	            }
	        }
	        return dist+1;
	                
	    }
	
	public ListNode deleteDuplicates1(ListNode head) {
        if(head == null)
            return null;
        if(head.next == null)
            return head;
        
        ListNode prev = head;
        ListNode curr = prev.next;
        
        for(;curr!= null;){
            if(prev.val == curr.val){
                prev.next = curr.next;
                curr  = curr.next;
            }else{
                prev = prev.next;
                curr = curr.next;
            }
        }
            
        return head;
    }
	
	
	
	@Test
	public void testdeleteDuplicates(){
		ListNode test = new ListNode(1);
		test.next = new ListNode(2);
		ListNode res = deleteDuplicates(test);
		for(;res!= null; ){
			System.out.print(res.val + ",");
			res = res.next;
		}
		
	}
	
	 public ListNode deleteDuplicates(ListNode head) {
	        ListNode prehead = new ListNode(0);
	        prehead.next = head;
	        
	        if(head == null)
	            return head;
	        if(head.next == null)
	            return head;
	        
	        ListNode previous = head;
	        ListNode current = head.next;
	        ListNode pre = prehead;
	        
	        for(;current != null; ){
	            if(current.val == previous.val){
	                for(;current != null && previous.val == current.val;){
	                    current = current.next;
	                    previous = previous.next;
	                }
	                if(current == null){
	                    pre.next = current;
	                    break;
	                }
	                pre.next = current;
	                previous = previous.next;
	                current = current.next;
	            }else{
	                pre.next = previous;
	                pre = pre.next;
	                if(current.next == null){
	                    pre.next = current;
	                    break;
	                }
	                
	                previous = previous.next;
	                current = current.next;
	                
	            }
	        }
	        
	        return prehead.next;
	    }
	
	
	public ListNode partition(ListNode head, int x) {
        //1->4->3->2->5->2;    x = 3     1->2->2->4->3->5
        //fir->1->2->2    sec->4->3->5
        
        if(head == null)    
            return head;
        if(head.next == null)
            return head;
        
        
        
        ListNode fir = new ListNode(0);
        ListNode first = fir;
  
        ListNode sec = new ListNode(0);
        ListNode second = sec;
        ListNode curr = head;
        
        
        
        for(;curr !=null;){
            ListNode ins = curr;
            curr = curr.next;
            
            if(ins.val < x){
                ListNode tmp = fir.next;
                fir.next = ins;
                ins.next = tmp;
            
                fir = fir.next;
            }else{
                ListNode tmp = sec.next;
                sec.next = ins;
                ins.next = tmp;
                
                sec = sec.next;
            }      
        }
        
        if(second.next == null){
            return first.next;
        }
        if(first.next == null){
            return second.next;
        }
        fir.next = second.next;
        return first.next;
                
        
    }
	
	
	 public ListNode mergeTwoLists(ListNode A, ListNode B) {
	        
	        ListNode newhead = null; ListNode tail = null;
	        ListNode newNode = null;
	        for(;A != null || B != null; ){
	            if(A != null && B != null){
	                if(A.val < B.val){
	                    newNode = new ListNode(A.val);
	                }else{
	                    newNode = new ListNode(B.val);
	                }
	            }else if(A != null){
	                newNode = new ListNode(A.val);
	                A = A.next;
	            }else{
	                newNode = new ListNode(B.val);
	                B = B.next;
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
	
	 @Test
	 public void testmergeKLists(){
		 ListNode A = new ListNode(1);
		 A.next = new ListNode(3);
		 ListNode B = new ListNode(2);
		 B.next = new ListNode(4);
		 ListNode C = new ListNode(5);
		 C.next = new ListNode(6);
		 List<ListNode> lists = new ArrayList();
		 lists.add(A);
		 lists.add(B);
		 lists.add(C);
		 
		 ListNode res = mergeKLists(lists);
		 ListNode curr = res;
		 for(;curr != null;){
			 System.out.print(curr.val);
			 curr = curr.next;
		 }
		 
		 
	 }
	
	
	
    
	public ListNode mergeKLists(List<ListNode> lists) {
	        if(lists.size() == 0){
	            return null;
	        }
	        if(lists.size() == 1)
	            return lists.get(0);
	        
	        if(lists.size() == 2){
	            ListNode A = lists.get(0);
	            ListNode B = lists.get(1);
	            
	            ListNode newhead = null; 
	            ListNode newNode = null;
	            ListNode tail = null;
	            
	            while(A != null || B!= null){
	                if( A != null && B != null){
	                    if( A.val < B.val){
	                        newNode = new ListNode(A.val);
	                        A = A.next;
	                    }else{
	                        newNode = new ListNode(B.val);
	                        B = B.next;
	                    }
	                }else if(A != null){
	                    newNode = new ListNode(A.val);
	                    A = A.next;
	                }else{
	                    newNode = new ListNode(B.val);
	                    B = B.next;
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
	        
			List<ListNode> listA = new ArrayList();
	        List<ListNode> listB = new ArrayList();
	        
	        int n = lists.size();
	        for(int i =0; i < n; i++){
	        	if( i < n/2)
	        		listA.add(lists.get(i));   
	        	else
	        		listB.add(lists.get(i));
	        }
	        
	        ListNode C = mergeKLists(listA);
	        ListNode D = mergeKLists(listB);
	        
	        List<ListNode> res = new ArrayList();
	        res.add(C);
	        res.add(D);
	        return mergeKLists(res);
	    }
	 
	 
	 
	 public ListNode detectCycle(ListNode head) {
	        if(head == null)
	            return null;
	        if(head.next == null)
	            return null;
	        
	        ListNode slow = head;
	        ListNode fast = head;
	        
	        boolean virgin = true;
	        
	        for(;;){
	            if(slow == fast){
	                if(virgin){
	                    virgin = false;
	                }else{
	                    break;
	                }
	            }
	            if(fast == null)
	                return null;
	            fast = fast.next;
	            if(fast == null)
	                return null;
	            fast = fast.next;
	            if(fast == null)
	                return null;
	           
	            slow = slow.next;
	        }
	        slow = head;
	        
	        for(;;){
	            if(slow != fast){
	                slow = slow.next;
	                fast = fast.next;
	            }else{
	                return slow;
	            }
	        }
	    }
	
	
	
	
	public boolean hasCycle(ListNode head) {
        if(head == null)
            return false;
        if(head.next == null)
            return false;
            
        ListNode fast = head;
        ListNode slow = head;
        boolean virgin = true;
        
        for(;;){
            if(slow == fast){
                if(virgin == true){
                    virgin = false;
                }else{
                    return true;
                }
            }
            if(fast == null)
                    return false;
            fast = fast.next;
            if(fast == null)
                    return false;
            fast = fast.next;
            if(fast == null)
                return false;
            slow = slow.next;
        }
    }
	
	
	
	
	public ListNode insertionSortList1(ListNode head){
        if(head == null)
           return null;
        if(head.next == null)
           return head;
        
        
        ListNode curr = head.next;
        ListNode tail = head;
        
        for(;curr != null;){
            ListNode ins = curr;
            curr = curr.next;
            
            if(ins.val >= tail.val){
               tail = tail.next;
            }else{
               if(head == tail){
                   tail = ins.next;
                   ListNode tmp = head;
                   head = ins;
                   ins.next = tmp;
                   continue;
               }else{
            	   tail = ins.next;//must update tail
            	   
                   if(ins.val < head.val){
                       ListNode tmp = head;
                       head = ins;
                       ins.next = tmp;
                   }else{
                       ListNode pre = head;
                       ListNode trav = head.next;
                       for(;;){
                           if(ins.val < trav.val){
                               ListNode tmp = pre.next;
                               pre.next = ins;
                               ins.next = tmp;
                               break;
                           }
                           pre = pre.next;
                           trav = trav.next;
                       }
                   }
               }
            }
        }
       
        return head;
    }
	
	@Test
	public void testinsertionSortList(){
		ListNode head = new ListNode(3);
		head.next = new ListNode(4);
		head.next.next = new ListNode(1);
		
		head  = insertionSortList(head);
		ListNode curr = head;
		for(;curr != null;){
			System.out.print(curr.val+",");
			curr = curr.next;
		}
	}
	
	//O(n^2) O(1)
	public ListNode insertionSortList(ListNode head) {
        if(head == null)
            return null;
        if(head.next == null)
            return head;
        //start normal case
        
        ListNode tail = head;
        ListNode curr = head.next;
        
        for(;curr!= null;){
            ListNode ins = curr;
            curr = curr.next;
            
            //insert  tail must be careful
            if(ins.val >= tail.val)
                tail = tail.next;
            else{
            	
            	if(head == tail){
                    tail.next = ins.next;
                    head = ins;
                    ins.next = tail;
                    continue;
            	}
            	
                tail.next = ins.next;
                
                ListNode pre = head;
                ListNode trav = head.next;//what if head == 1
                if(ins.val < head.val){
                    ListNode tmp = head;
                    head = ins;
                    head.next = tmp;
                }else{
					for (;;) {
						if (ins.val < trav.val) {
							pre.next = ins;
							ins.next = trav;
							break;
						}
						pre = pre.next;
						trav = trav.next;
					}
                }
                
            }
        }
        return head;
    }
	
	
	
	
	
	
	
	
	
	 public RandomListNode copyRandomList(RandomListNode head) {
	        if(head ==  null)
	            return null;
	        RandomListNode newhead = new RandomListNode(head.label);
	        
	        HashMap<RandomListNode, RandomListNode> map = new HashMap();
	        
	        map.put(head, newhead);//first one
	        
	        RandomListNode curr = head.next;
	        RandomListNode newNode= newhead;
	        
	        for(;curr != null;){
	            newNode.next = new RandomListNode(curr.label);
	            
	            map.put(curr, newNode.next);
	            
	            
	            //go ahead
	            newNode = newNode.next;
	            curr = curr.next;
	        }
	        //start to copy random
	        RandomListNode trav = head;
	        newNode  = newhead;
	        
	        for(;trav != null;){
	        	RandomListNode value = map.get(trav.random);
	        	
	            newNode.random = value;
	            
	            trav = trav.next;
	            newNode = newNode.next;
	        }
	        return newhead;
	    }
	 
	 
	 
	 
	
	@Test
	public void testaddTwoNumbers(){
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		
		ListNode l2 = new ListNode(9);
		l2.next = new ListNode(9);
		l2.next.next = new ListNode(6);
		ListNode res = addTwoNumbers(l1, l2);
		
		for(;res != null;){
			System.out.print(res.val + "->");
			res = res.next;
		}
		
		
		
	}
	
	
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newhead = null;
        int carry = 0;
        ListNode tail = null;
        ListNode newNode = null;
        
        for(;l1 != null || l2 != null;){
            int value = 0;
            if(l1 != null && l2!= null){
                value = l1.val + l2.val + carry;
                if(value >= 10){
                    value = value - 10;
                    carry=1;
                }else{
                	carry = 0;
                }
                newNode  = new ListNode(value);
                l1 = l1.next;
                l2 = l2.next;
            }else if(l1 != null){
                value  = l1.val + carry;
                if(value >= 10){
                    value = value - 10;
                    carry = 1;
                }else{
                	carry = 0;
                }
                newNode = new ListNode(value);
                l1 = l1.next;
            }else{
                value = l2.val + carry;
                if(value >= 10){
                    value = value - 10;
                    carry = 1;
                }else{
                	carry = 0;
                }
                newNode = new ListNode(value);
                l2 = l2.next;
            }
            
            if(newhead == null){
                newhead = tail= newNode;
            }else{
                tail.next = newNode;
                tail = tail.next;
            }
        }
        if(carry == 1){
        	tail.next = new ListNode(1);
        }
        return newhead;
    }
	
}
