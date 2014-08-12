package leetcode;

import java.util.HashMap;

import leetcode.day11.LRUCache;
import leetcode.day11.LRUCache.DoubleListNode;

import org.junit.Test;

public class codes {
	
	//Runtime: 508 ms
	public class LRUCache {

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
		LRUCache(int capacity) {
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
	
	
	
	
	
	public class LRUCache1 {
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
	
		public LRUCache1(int capacity) {
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
	public void testLRUCache(){
		LRUCache cache = new LRUCache(4);
		cache.set(1, 10);
		cache.set(2, 20);
		cache.set(3, 30);
		cache.set(4, 40);
		cache.set(5, 50);
		cache.get(2);
	}
}
