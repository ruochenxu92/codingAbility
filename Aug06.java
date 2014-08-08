package leetcode;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.Test;

public class Aug06 {
	
	public int maxPoints(Point[] points) {
        if (points == null || points.length== 0) {
            return 0; 
        }
        int maxPoints = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            HashMap<Float, Integer> hs = new HashMap();
            int dup = 1;
            for (int j = 0; j < points.length; j++) {
                if (j == i) {
                    continue; 
                }
                Point A = points[i];
                Point B = points[j];
                if (A.x == B.x && A.y == B.y) {
                    dup++;
                    continue;
                }
                if (A.x == B.x) {
                    if (hs.containsKey(Float.MAX_VALUE)) {
                        hs.put(Float.MAX_VALUE, hs.get(Float.MAX_VALUE) + 1);
                    }else{
                        hs.put(Float.MAX_VALUE, 1);
                    }
                    continue;
                }
                float tangent = (A.y - B.y) / (float)(A.x - B.x);
                if (hs.containsKey(tangent)) {
                    hs.put(tangent, hs.get(tangent) + 1);
                }else{
                    hs.put(tangent, 1);
                }
                
            }
            
            Iterator it = hs.values().iterator();//what will happen if only has one point
            while(it.hasNext()){
                int cur = (int)it.next();
                maxPoints = Math.max(maxPoints, cur + dup);
            }
            maxPoints = Math.max(maxPoints, dup);
        }
        return maxPoints;
	}
	
	public ListNode sortList(ListNode head) {
	    if (head == null || head.next == null) {
	       return head; 
	    }
	    ListNode middle = findMiddle(head);
	    ListNode head2 = middle.next;
	    middle.next = null;
	    ListNode left = sortList(head);
	    ListNode right = sortList(head2);
	    return merge(left, right);
	}
	
	private ListNode findMiddle(ListNode head){
	    if(head == null){
	        return head;
	    }
	    ListNode slow = head;
	    ListNode fast = head.next;
	    while(fast != null && fast.next != null){
	        fast = fast.next.next;
	        slow = slow.next;
	    }
	    return slow;
	}
	
	private ListNode merge(ListNode left, ListNode right){
	    ListNode dummy = new ListNode(0);
	    ListNode tail = dummy;
	    while(left != null || right != null){
	        if(left != null && right != null){
	            if(left.val < right.val){
    	            tail.next = left;
    	            left = left.next;
	            }else{
	                tail.next = right;
	                right = right.next;
	            }
	        }else if(left != null){
	            tail.next = left;
	            left = left.next;
	        }else{
	            tail.next = right;
	            right = right.next;
	        }
	        tail = tail.next;//very important
	    }
	    return dummy.next;
	}
	
	
	
	@Test
	public void test(){
		Node a = new Node(0);
		
	}
	
	class Node{
		int key;
		Node(int key){
			this.key = key;
		}
		
	}
	
	
	
	public class LRUCache {
	    class Node {
	        int key;
	        int value;
	        Node prev;
	        Node next;
	        Node(int key, int value) {
	            this.key = key;
	            this.value = value;
	            prev = null;
	            next = null;
	        }
	    }
	    HashMap<Integer, Node> hs;
	    int capacity;
	    Node head;
	    Node tail;

	    public LRUCache(int capacity) {
	        this.capacity = capacity;
	        hs = new HashMap();
	        head = new Node(-1, -1);
	        tail = new Node(-1, -1);
	        head.next = tail;
	        tail.prev = head;
	    }

	    public int get(int key) {
	        if (!hs.containsKey(key)) {
	            return -1;
	        }
	        Node node = hs.get(key);
	        node.prev.next = node.next;
	        node.next.prev = node.prev;
	        
	        move_to_tail(node);
	        return node.value;
	    }

	    public void set(int key, int value) {
	        if( get(key) != -1 ){
	            hs.get(key).value = value;
	            return;
	        }    
	        Node node = new Node(key, value);
	        if( hs.size() == capacity ){
	            Node del = head.next;
	            head.next = del.next;
	            del.next.prev = head;
	            hs.remove(del.key);
	        }
	        hs.put(key, node);
	        move_to_tail(node);
	    }
	    
	    public void move_to_tail(Node node){
	        tail.prev.next = node;
	        node.next = tail;
	        
	        node.prev = tail.prev;
	        tail.prev = node;
	    }
	}	    
			 
	
//	public RandomListNode copyRandomList(RandomListNode head) {
//	    if(head == null){
//	        return null;
//	    }
//	    RandomListNode newhead = new RandomListNode(head.val);
//	    HashMap<RandomListNode, RandomListNode> map = new HashMap();
//	    map.put(head, newhead);
//	    RandomListNode trav = head.next;
//	    RandomListNode it = newhead;
//	    while(trav != null){
//	        RandomListNode newnode = new RandomListNode(trav.val);
//	        it.next = newnode;
//	        map.put(trav, newnode);
//	        trav = trav.next;
//	        it = it.next;
//	    }
//	    trav = head;
//	    it = newhead;
//	    while(trav != null){
//	        it.random = map.get(trav.random);
//	        trav = trav.next;
//	        it = it.next;
//	    }
//	    
//	    return newhead;
//	}
	
//	public List<Interval> merge(List<Interval> intervals) {//arraylist, has all intervals
//        Collections.sort(intervals, new Comparator(){
//           @Override
//           public int compare(Interval o1, Interval o2){
//               if(o1.start > o2.start){
//                   return 1;
//               }else if(o1.start == o2.start){
//                   return 0;
//               }else{
//                   return -1;
//               }
//           }
//        });
//        
//        for(int i = 0; i < intervals.size(); i++){
//            Interval pre = interval.get(i - 1);
//            Interval cur = interval.get(i);
//            if (pre.end >= cur.start) {
//                pre.end = Math.max(pre.end, cur.end);
//                intervals.remove(i);
//                i--;
//            }
//        }
//        return intervals;
//    }

}
