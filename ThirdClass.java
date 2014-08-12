package leetcode;

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class ThirdClass {
	
	
	
	
//	DFS Template
//	Binary Tree DFS template
	/**
	 * 0.0 template
	 * Copyright: NineChapter
	 * - Algorithm Course, Mock Interview, Interview Questions
	 * - More details on: http://www.ninechapter.com/
	 */

	//Template 1: Traverse

	public class Solution {
	    public void traverse(TreeNode root) {
	        if (root == null) {
	            return;
	        }
	        // do something with root
	        traverse(root.left);
	        // do something with root
	        traverse(root.right);
	        // do something with root
	    }
	}


	//Tempate 2: Divide & Conquer

//	public class Solution {
//	    public ResultType traversal(TreeNode root) {
//	        // null or leaf
//	        if (root == null) {
//	            // do something and return;
//	        }
//	        
//	        // Divide
//	        ResultType left = traversal(root.left);
//	        ResultType right = traversal(root.right);
//	        
//	        // Conquer
//	        ResultType result = Merge from left and right.
//	        return result;
//	    }
//	}
	
	
	
	@Test
	public void testpreorder(){
		TreeNode root = new TreeNode(5);
		root.left  = new TreeNode(3);
		root.right = new TreeNode(7);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		
		List<Integer> res = preorder1(root);
		System.out.println(res);
		
		List<Integer> res1 = dacpreorder(root);
		System.out.println(res);
		/*
		 * [5, 3, 1, 4, 7, 6, 9]
		   [5, 3, 1, 4, 7, 6, 9]
		 */
	}
	
	
	
	/**
	 * 1.0 preorder recursion
	 * preorder Traversal(DFS)
	 * 
	 */
	
	public void preorder(TreeNode root) {
		if (root == null) {
			return;
		}
		System.out.println(root.val);
		preorder(root.left);
		preorder(root.right);
	}
	
	/**
	 * 1.1 stack preorder
	 * use stack to memorize
	 */
	
	public List<Integer> preorder1(TreeNode root) {
		List res = new ArrayList();
		if (root == null) {
			return res;
		}
		Stack<TreeNode> stack = new Stack();
		stack.push(root);
		while(!stack.empty()){
			TreeNode cur = stack.pop();
			res.add(cur.val);
			if (cur.right != null) {
				stack.push(cur.right);
			}
			if (cur.left != null) {
				stack.push(cur.left);
			}
		}
		return res;
	}
	
	
	/**
	 * 1.2 divide and conquer(dac)
	 *      5
	 *   3    7
	 * 1  4  6  9
	 * 
	 * 1 3 4  left
	 * 5      root
	 * 6 7 9  right
	 */
	
	public List<Integer> dacpreorder(TreeNode root) {
		List res = new ArrayList();
		if (root == null) {
			return res;
		}
		
		//1.divide
		List<Integer> left = dacpreorder(root.left);
		List<Integer> right = dacpreorder(root.right);
		
		//2.conquer
		res.addAll(left);
		res.add(root.val);
		res.addAll(right);
		return res;
	}
	
	/**
	 * 2.0
	 * merge sort an array, this is not easy,
	 * merge sort has two steps, partition and merge, partition is automatically
	 * finish, but how to merge
	 * I start from len = 1, 
	 * so many details, I need to find another good implementations, anyway, understand the thoughts of merge sort 
	 * is important.
	 * I think merge sort > quick sort since the performance can be improve by using multi-thread
	 */
	
	
	@Test
	public void testmerge(){
		int[] num = new int[] {5, 1, 2};
		mergesort(num);
		assertArrayEquals(new int[]{1,2,5}, num); 

	}
	
	public void mergesort(int[] num) {
		if (num == null) {
			return;
		}
		
		for(int len = 1; len <= num.length; len *= 2) {
			for (int i = 0; i + len <= num.length; i += len * 2) {
			     merge(num, i, len, i + len, len);
			}
		}
	}
	
	private void merge(int[] num, int Astart, int ALen, int Bstart, int BLen) {
		int[] res = new int[num.length];
		
		int start = Astart;
		
		if (Bstart + BLen > num.length) {
			BLen = num.length - Bstart;
		}
		int len = ALen + BLen;
		int index  = Astart;
		while(ALen > 0 || BLen > 0) {
			if (ALen > 0 && BLen > 0) {
				if (num[Astart]  < num[Bstart]) {
					res[index++] = num[Astart++];
					ALen--;
				} else{
					res[index++] = num[Bstart++];
					BLen--;
				}
			} else if(ALen > 0) {
				res[index++] = num[Astart++];
				ALen--;
			} else {
				res[index++] = num[Bstart++];
				BLen--;
			}
		}
		
		for(int i = 0; i < len; i++) {
			num[start + i] = res[start + i];
		}
	}
	
	
	/**
	 * 3.0 quick sort, skip yet, update later
	 */
	
	
	/**
	 * 4. binary Tree Maximum path sum
	 * 记住， 一个 Node can be 整条通路
	 * 
			92 / 92 test cases passed.
			Status: Accepted
			Runtime: 512 ms
			Submitted: 0 minutes ago

	 */
	
	class ResultType{
		int singlePath,maxPath;
		ResultType(int singlePath, int maxPath){
			this.singlePath = singlePath;
			this.maxPath = maxPath;
		}
	}
	
	public ResultType maxpath(TreeNode root) {
		if (root == null) {
			return new ResultType(0, Integer.MIN_VALUE);//at least one node, if only have one node like -3, we also need to pick it
		}
		
		//divide
		ResultType left = maxpath(root.left);
		ResultType right = maxpath(root.right);
		
		int singlepath = Math.max(left.singlePath, right.singlePath) + root.val; //if not add root, we cannot continue to climb up
		singlepath = Math.max(singlepath, 0);
		
		int maxpath = Math.max(left.maxPath, right.maxPath);
		maxpath = Math.max(maxpath, left.singlePath + right.singlePath + root.val); //whole path >= singlepath for root
		
		
		return new ResultType(singlepath, maxpath);
	}
	
	
	public int maxPathSum(TreeNode root) {
        ResultType result = maxpath(root);
        return result.maxPath;
    }
	
	/**
	 * 5.0   return all the keys in the giveRange in BST
	 *      5
	 *   3    7
	 * 1  4  6  9
	 */
	
	@Test
	public void testRange(){
		TreeNode root = new TreeNode(5);
		root.left  = new TreeNode(3);
		root.right = new TreeNode(7);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		Interval interval = new Interval(4,7);
		List<Integer> res = Range(interval, root);
		assertEquals(res, Arrays.asList(4,5,6,7));
	}
	
	class Interval {
		int start;
		int end;
		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	
	public List<Integer> Range(Interval interval, TreeNode root) {
		if (interval.start > interval.end || root == null) {
			return new ArrayList();
		}
		
		if (root.val > interval.end) {
			return Range(interval, root.left);
		}
		
		if (root.val < interval.start) {
			return Range(interval, root.right);
		}
		
		//divide
		List<Integer> left = Range(interval, root.left);
		List<Integer> right = Range(interval, root.right);
		
		//conquer
		List<Integer> res = new ArrayList();
		res.addAll(left);
		res.add(root.val);
		res.addAll(right);
		return res;
	}
	
	
	/**
	 * 6. implement an iterator in BST
	 * http://docs.oracle.com/javase/7/docs/api/java/util/Iterator.html
	 * only need to implement three methods for iterator interface
	 * boolean has Next()
	 * Object next();     
	 * void remove(); //optional operation
	 * 
	 *     1. ascending order
	 *     2. next, hasNext, O(1).
	 *     use queue
	 *     3. O(1) space, waiting for St.Huang to solve it.
	 */
	
	class treeIterator {
		Deque<TreeNode> queue;
		
		//init
		treeIterator(TreeNode root) {
			queue = new ArrayDeque();
			
			Stack<TreeNode> stack = new Stack();
			if (root == null) {
				return;
			}
			stack.push(root);
			while( !stack.isEmpty() ) {
				TreeNode cur = stack.pop();
				queue.offer(cur);
				if (cur.right != null) {
					stack.push(cur.right);
				}
				
				if (cur.left != null) {
					stack.push(cur.left);
				}
			}
		}
		
		
		public boolean hasNext(){
			return queue.peek() != null;
		}
		
		public Object next() {
			TreeNode cur = queue.poll();
			return (Object) cur;
		}
		
	}
	
	
	/**
	 * 
	 * 7.0 level order traversal
	 * BST template (level Order traversal)
	 * BFS question in leetcode
	 * Zagzig traversal 
	 * Clone Graph
	   Word Ladder II
	   Word Ladder
	   Surrounded Regions
	 */
	
	public List<List<Integer>> levelOrder(TreeNode root) {
		List res = new ArrayList();
		if (root == null) {
			return res;
		}
		Deque<TreeNode> queue = new ArrayDeque();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			List<Integer> level = new ArrayList();
			
			int size = queue.size();//St.Huang remind, do not directly put queue.size() inside loop
			
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				level.add(cur.val);
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
			}
			
		}
		return res;
	}
	
	
	/**
	 * 7.1 Zagzig traversal
	 */
	
	public List<List<Integer>> ZagzigTraversal(TreeNode root) {
		List res = new ArrayList();
		if (root == null) {
			return res;
		}
		Deque<TreeNode> queue = new ArrayDeque();
		queue.offer(root);
		boolean normalOrder = true;
		
		while(!queue.isEmpty()) {
			List level = new ArrayList();
			int size = queue.size();
			
			for(int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				level.add(cur);
				if( cur.left !=  null) {
					queue.offer(cur.left);
				}
				if( cur.right != null) {
					queue.offer(cur.right);
				}
			}
			
			if(!normalOrder) {
				Collections.reverse(level);
			}
			res.add(level);
			normalOrder = !normalOrder;
		}
		return res;
	}
	
	/**
	 * 7.2 
	 * Clone Graph
	 * 
	 */
	
	
	/**
	 * 7.3 
	 * Word Ladder II
	 * 
	 */
	
	
	/**
	 * 7.4 
	 * Surrend Region.
	 */
	
	
	
	/**
	 * 8. validate BST
	 * thinking from the definition of BST
	 * left < root < right
	 */
	
	public boolean validate(TreeNode root) {
		if (root == null) {
			return true;
		}
		return dac(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private boolean dac(TreeNode root, int min, int max) {
		if (root == null) {
			return true;
		}
		if (root.val < min || root.val > max) {
			return false;
		}
		return dac(root.left, min, root.val) && dac(root.right, root.val , max);
	}
	
	/**
	 * 9. insert a node in BST, only insert at bottom
	 * if (root != null) {
	 * 1. value > root.val -> root.right
	 * 2. value < root.val -> root.left
	 * 3. value == root.val -> return false; // insert failed
	 * } 
	 * root == null
	 * root = new TreeNode(value);
	 * return true;
	 */
	
	 public boolean insert(TreeNode root, int value) {
		 if (root == null ){
			 root = new TreeNode(value);
			 return true;
		 }
		 
		 if (value == root.val) {
			 return false;
		 }
		 
		 if (value > root.val) {
			 return insert(root.right, value);
		 } else {
			 return insert(root.left, value);
		 }
	 }
	
	 
	 /**
	  * 10.delete a node in BST
	  * 
	  * there is a lot details when the delete is not leave node.
	  * 前驱节点(left child rightmost node) the node that is closest to root
	  * is useful in this case.
	  */
	 
	@Test
	public void testdelete() {
		TreeNode root = new TreeNode(5);
		root.left  = new TreeNode(3);
		root.right = new TreeNode(7);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		
		root = delete(root, 5);
		assertEquals(4, root.val);
	}
	 
	 
	public TreeNode delete (TreeNode root, int tar) {
		if (root == null) {
			 return root;
		}
		if (root.val < tar) {
			 return delete(root.right, tar);
		}
		if (root.val > tar) {
			 return delete(root.left, tar);
		}
		
		//start delete
		if (root.left == null) {
			root = root.right;
			
		} else {
			TreeNode trav = root.left;
			if (trav.right == null) {
				trav.right = root.right;
				root = trav;
			} else {
				TreeNode pre = trav;
				while(trav.right != null) {
					pre = trav;
					trav = trav.right;
				}
				pre.right = trav.left;
				trav.right = root.right;
				trav.left = root.left;
				root = trav; //make a bug here, change root pointer to trav does not work in the 上层，因为root 传进来时已经指向地址id = 51 固定。 
			}
		}
		return root;
	}
	 
	/**
	 * 11.0 Least Common Ancester(Divide And Conquer)
	 * has a parent
	 * 
	 */
	class Node{
		Node left, right, parent;
	}
	
	public Node LCA(Node node1, Node node2) {
		int dep1 = maxDepth(node1);
		int dep2 = maxDepth(node2);
		
		if (node1 == null || node2 == null) {
			return null; //invalid
		}
		if (node1.parent == null || node2.parent == null) {
			if (node1.parent == null) {
				return node1;
			} else {
				return node2;
			}
		}
		
		if (dep1 == dep2) {
			return LCA(node1.parent, node2.parent);
		}
		
		if (dep1 > dep2) {
			return LCA(node1.parent, node2);
		} else {
			return LCA(node1, node2.parent);
		}
		
	}
	
	private int maxDepth(Node root){
		if (root == null) {
			return 0;
		}
		return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
	}
	
	
	/**
	 * 11.1 
	 * if we only know root, the easy way to think is from up to bottom
	 * 
	 * if return is null, means node1 and node2 is not in this subtree.
	 * 
	 */
	
	public TreeNode getAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
		if (root == null || root == node1 || root == node2) {
			return root;
		}
		TreeNode left = getAncestor(root.left, node1, node2);
		TreeNode right = getAncestor(root.right, node1, node2);
		
		if (left != null && right != null) {
			//both left and right are not null means, the two nodes are disperse in two subtrees, LCA is root
			return root;
		} else if(left != null) {
			//only left is not null, right does not contains nodes, but this does not means both nodes are in left. only know at least one in left.
			return left;
		} else if(right != null) {
			return right;  //let  上一层 去处理，他们有更多信息去judge. 
		}
		
		return null;//does not contains any of two nodes
	}
	
	
	
	
	
	
	/**
	 * 12.0 construct tree from preorder, inorder (DAC);
	 * 12.1 construct tree from inorder, postorder(DAC);
	 */
	
	
	
	 
	
	
}
