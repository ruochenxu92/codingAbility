package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class day18 {
	
	
	@Test
	public void test(){
		int a = 11 * 14;
		int b = 24 * 36;
		int c = b / a;
		System.out.println(a  + ","  + b + "," + c);
		
		
		
	}
	
	 public TreeNode buildTree(int[] preorder, int[] inorder) {
	        
	        TreeNode root = null;
	        preorder(preorder, root, 0);
	        inorder(inorder, root, 0);
	        return root;
	        
	    }
	    
	    
	    void preorder(int[] preorder, TreeNode root, int index){
	        root = new TreeNode(preorder[index]);
	        preorder(preorder, root.left, index+1);
	        preorder(preorder, root.left, index+2);
	    }
	    
	    void inorder(int[] inorder,  TreeNode root, int index){
	        
	        inorder(inorder, root.left, index);
	        root.val = inorder[index+1];
	        inorder(inorder, root.right, index+2);
	        
	    }

	
	
	
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
        //special BFS
        List<List<Integer>> res = new ArrayList();
        if(root == null){
            return res;
        }
        
        Deque<TreeNode> queue = new ArrayDeque();
        
        queue.offer(root);
        int currLevel = 1;
        int nextLevel = 0;
        List<Integer> list = new ArrayList();
        
        while(queue.peek() != null){
            TreeNode curr = queue.poll();
            currLevel--;
            list.add(curr.val);
            
            if(curr.left != null){
                queue.offer(curr.left);
                nextLevel++;
            }
            if(curr.right != null){
                queue.offer(curr.right);
                nextLevel++;
            }
            
            if(currLevel == 0){
                currLevel = nextLevel;
                nextLevel = 0;
                List<Integer> copy = new ArrayList(list);
                res.add(copy);
                list.clear();
            }
        }
        
        Collections.reverse(res);
        return res;
    }
	
	
	 public List<List<Integer>> levelOrder(TreeNode root) {
		 List<List<Integer>> res = new ArrayList();
		 if(root == null) return res;
		 Deque<TreeNode> queue = new ArrayDeque();
		 
		 queue.offer(root);
		 //while there is at least one discovered node
		 int currLevel = 1;
		 int nextLevel = 0;
		 
		 List<Integer> sol = new ArrayList();
		 
		 while(queue.peek() != null){
			 TreeNode curr = queue.poll();
			 currLevel--;
			 sol.add(curr.val);
			 if(curr.left!= null){
				 
				 queue.offer(curr.left);
				 nextLevel++;
			 }
			 if(curr.right != null){
				 
				 queue.offer(curr.right);
				 nextLevel++;
			 }
			 if(currLevel == 0){
				 currLevel = nextLevel;
				 nextLevel = 0;
				 
				 List<Integer> tmp = new ArrayList(sol);
				 res.add(tmp);
				 sol.clear();
			 }
		 }
		 return res;
	 }
	
	
	
	
	
//	void LevelOrder(Node * root){
//		if(root == NULL) return;
//		queue<Node*> Q;
//		Q.push(root);
//		//while there is at least one discovered node
//		while(!Q.empty()){
//			Node * current = Q.front();
//			cout << current->data <<" ";
//			if(current->left != NULL) 	Q.push(current->left);
//			if(current->right != NULL) Q.push(current->right);
//			Q.pop();
//
//		}
//	}
	
	
	
	 @Test
	 public void testlevel(){
		 TreeNode root = new TreeNode(1);
		 root.left = new TreeNode(2);
		 root.right = new TreeNode(3);
		 
		 root.left.left = new TreeNode(4);
		 root.left.right = new TreeNode(5);
		 
		 root.right.left = new TreeNode(6);
		 root.right.right = new  TreeNode(7);
		 levelOrderBottom(root);
		// levelOrder(root);
		 
	 }
	
	 
	 
	 
	
	    
	    
	    void level(Deque<Integer> queue, TreeNode root){
	        if(root == null){
	            return;            
	        }
	        queue.offer(root.val);
	        
	        if(root.left != null && root.right != null){
	            queue.offer(root.left.val);
	            queue.offer(root.right.val);
	            level(queue, root.left);
	            level(queue, root.right);
	        }else if(root.left != null){
	        	queue.offer(root.left.val);
	            level(queue,root.left);
	        }else if(root.right != null){
	            
	            queue.offer(root.right.val);
	            level(queue,root.right);
	        }
	    }
	    
	    
	
	
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; next = null; }
	 * }
	 */
	/**
	 * Definition for binary tree
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */
	
	
	
		public TreeNode sortedArrayToBST(int[] num) {
		    int n = num.length;
		    return getTree(num, 0, n-1);
	    }
	    
	    TreeNode getTree(int[] A, int start, int last){
	        if(start > last)
	            return null;
	        if(start == last)
	            return new TreeNode(A[start]);
	        
	        int mid = (start + last)/2;
	        
	        TreeNode midnode = new TreeNode(A[mid]);
	        midnode.left = getTree(A, start, mid-1);
	        midnode.right = getTree(A, mid+1, last);
	        
	        return midnode;
	    }
	
	
	
	    public TreeNode sortedListToBST(ListNode head){
		    if(head == null)
		        return null;
		    if(head.next == null)
		        return new TreeNode(head.val);
		    ListNode sentinel = new ListNode(0);
		    sentinel.next = head;
		    
		    int length = 0;
		    ListNode curr = head;
		    
		    for(;curr!= null; curr = curr.next) 
		        length++;
		    
		    curr = sentinel;
		    for(int i = 0; i < length/2; i++){
		        curr = curr.next;
		    }
		    
		    ListNode midnode = curr.next;
		    curr.next = null;
		    TreeNode mid = new TreeNode(midnode.val);
		    
		    mid.left = sortedListToBST(head);
		    mid.right =  sortedListToBST(midnode.next);
		    return mid;
		}
	
	
//	public TreeNode sortedListToBST(ListNode head){
//	    if(head == null)
//	        return null;
//	    if(head.next == null)
//	        return new TreeNode(head.val);
//	    
//	    int length = 0;
//	    ListNode trav = head;
//	    for(;trav != null; trav = trav.next) length++;
//	    
//	    ListNode curr = head;
//	    ListNode prev = curr;
//	    for(int i =0; i < (length-1)/2; i++){
//	        prev = curr;
//	        curr = curr.next; 
//	    }
//        
//        ListNode newhead;
//	    if(length % 2 == 0){
//	        newhead = curr.next;
//	        curr.next = null;
//	    }else{
//	        newhead = curr;
//	        prev.next = null;
//	    }
//	    
//	    sortedListToBST(head);
//	    sortedListToBST()
//	    
//	    
//        	    
//	    
//	    
//	}
	
	
	public int minDepth(TreeNode root){
        if(root == null)
           return 0;
        return findmin(root);
    }
    
    public int findmin(TreeNode root){
       if(root == null)
           return 0;
       if(root.left == null && root.right == null){
           return 1;
       }
       
       if(root.left != null && root.right != null){
           return  Math.min(findmin(root.left), findmin(root.right)) + 1;
       }
       
       if(root.left != null){
           return findmin(root.left) + 1;
       }
       
       return findmin(root.right) + 1;
       
    }
    
    
    
	
	
	
	@Test
	public void testgenerateTrees(){
		generateTrees(5);
	}
	
	
	
	public ArrayList<TreeNode> generateTrees(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
     
     ArrayList<TreeNode> result=generateBST(1,n);
     
     return result;
    }
    public ArrayList<TreeNode> generateBST(int start, int end){
        ArrayList<TreeNode> subTree=new ArrayList<TreeNode>();
        
        if (start>end){
            subTree.add(null);
        }
        else if  (start==end){
            
            subTree.add(new TreeNode(start));
           
            
        }
        else{
             for (int i=start; i<=end; i++){
               // divied and conquer.
               // get collection of all left trees and right trees
               // then construct them together
                ArrayList<TreeNode> leftTree=generateBST(start, i-1);
                ArrayList<TreeNode> rightTree=generateBST(i+1, end);
                
                for (TreeNode leftNode:leftTree){
                    for(TreeNode rightNode:rightTree){
                        TreeNode root=new TreeNode(i);
                        root.left=leftNode;
                        root.right=rightNode;
                                   
                        subTree.add(root);
                      }
                 }
                       
             }
             
        }
        return subTree;
    }
	
	
	
	
	
	
	@Test
	public void testnumTrees(){
		int res = numTrees(3);
		System.out.println(res);
		
	}
	
	public int numTrees(int n) {
	    if( n <= 1 )
	        return 1;
        
        int[] A = new int[n + 1];
	    A[0] = 1;
	    A[1] = 1;
	    for(int i = 2; i <= n; i++){
            int sum = 0;
            for(int k = 0; k < n; k++){
                sum += A[k] * A[i-1-k];
            }
            A[i] = sum;
	    }
        return A[n];    
	    
	}
	
	
	
	
	
	
	
	  public boolean isValidBST(TreeNode root) {
	        if(root == null)
	            return true;
	        
	        return check(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	   }
	   
	   boolean check(TreeNode root, int min, int max){
	        if(root == null){
	            return true;
	        }
	        if(root.val <= min || root.val >= max)
	            return false;
	        
	        
	        if(root.left != null && root.right != null){
	            return check(root.left, min, root.val) && check(root.right, root.val, max);
	        }else if(root.left != null){
	            return check(root.left, min, root.val);
	        }else if(root.right != null){
	            return check(root.right, root.val, max);
	        }else{
	            return true;
	        }
	   }
	   
	   
	   
	
	
	
	 public String strStr(String haystack, String needle) {
	        int w = needle.length();
	        
	        int n = haystack.length();
	        
	        
	        for(int i = 0; i+ w<= n; i++){
	            String tmp = haystack.substring(i, i + w);
	            if(tmp.equals(needle))
	                return haystack.substring(i);
	        }
	        return null;
	    }
	
	
	@Test
	public void testlengthOfLastWord(){
		String tmp = new String("   askdfjsad; skfjdsalfj;dskj word   ");
		int es = lengthOfLastWord(tmp);
		System.out.println(es);
	}
	
	
    public int lengthOfLastWord(String s) {
    	s = s.trim();
    	
    	s = new String(" " + s);
    	
    	int count = 0;
    	for(int i = s.length() - 1; i >= 0; i--){
    		if(s.charAt(i) == ' '){
    			return count;
    		}
    		count++;
    	}
    	return count;
    }
	
	
	@Test
	public void testminWindow(){
		String S = new String("xxu2323sjf46xxiiiiuxxu3246");
		String T = new String("xxu46");
		
		String tmp = minWindow(S, T);
		System.out.println(tmp);
	}
	
	@Test
	public void testminWindow1(){
		String S = new String("ab");
		String T = new String("a");
		
		String tmp = minWindow(S, T);
		System.out.println(tmp);
	}
	
	
	
	
	
	/**
	 * The way we shrink the window is keep the alreadyFind HashMap, and continue update the values of keys, and 
	 * keep track of the start when we have a window that contains all the Characters that we need, we can move the window
	 * only in two conditions. the index start position is not invalid Character(The Character that is not in dict,
	 * another condition is that we find a new Character that is same as the start Character, we can use this new Character
	 * to replace the start Character, so start++ and update the minEnd - minStart, 
	 * the time is Actually < 2 * n = O(n) Space is O(T);
	 * @param S
	 * @param T
	 * @return
	 */
	 public String minWindow(String S, String T) {
         if (S==null||T==null){
             return null;
         }
         
         if(S.length()==0 && T.length()==0){
             return "";
         }
         if (S.length()<T.length()){
             return"";
         }
         
         HashMap<Character, Integer>needFind=new HashMap<Character, Integer>();
         HashMap<Character, Integer>alreadyFind=new HashMap<Character, Integer>();
         
         for(int i=0; i<T.length(); i++){
             alreadyFind.put(T.charAt(i), 0);
             
             if (needFind.containsKey(T.charAt(i))){
                 needFind.put(T.charAt(i), needFind.get(T.charAt(i))+1);
             }
             else{
                 needFind.put(T.charAt(i), 1);
             }
             
         }
         int minStart=-1;
         int minEnd=S.length();
         int start=0;
         int len=0;
         for (int i=0; i<S.length(); i++){
             if (alreadyFind.containsKey(S.charAt(i))){
            	 alreadyFind.put(S.charAt(i), alreadyFind.get(S.charAt(i)) + 1);
            	 
                 if(alreadyFind.get(S.charAt(i)) <= needFind.get(S.charAt(i)))
                	 len++;
                 
                 if (len==T.length()){
                    while( !needFind.containsKey(S.charAt(start))
                    	|| alreadyFind.get(S.charAt(start)) > needFind.get(S.charAt(start))){
                    	
                    	if(alreadyFind.containsKey(S.charAt(start))){
                    		alreadyFind.put(S.charAt(start), alreadyFind.get(S.charAt(start)) -1);
                    	}
                    	start ++;
                    }
                    
                    if(i - start < minEnd - minStart){
                    	minStart = start;
                    	minEnd = i;
                    }
                     
                 }
             }
         }
         if (minStart==-1){
             return "";
         }
         return S.substring(minStart, minEnd+1);
            
    }
	
	
	
	
	
	
	
	//cannot pass the big set. TLE
	public String minWindow1(String S, String T) {
        //A D O B E C O D E B A N C
        //A B C  BANC
        Deque<Integer> queue = new ArrayDeque();
        int count = 0;
        HashMap<Character, Integer> map = new HashMap();
        
        for(int i = 0; i < T.length(); i++){
            char c = T.charAt(i);
            if(!map.containsKey(T.charAt(i))){
                map.put(c, 1);
            }else{
                map.put(c, map.get(c) + 1);
            }
        }
        int[] res = new int[2];
        
        int start = 0;
        int min = Integer.MAX_VALUE;
        
        HashMap<Character, Integer> curr = new HashMap();
        
        for(int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            
            if(map.containsKey(c)){
                if(curr.containsKey(c)){
                    int now = curr.get(c);
                    if(now + 1 < map.get(c)){
                        count++;
                    }
                    curr.put(c, now+1);
                    if(!queue.contains(Integer.valueOf(i)))
                    	queue.offer(i);
                }else{
                    if(!queue.contains(Integer.valueOf(i)))
                    	queue.offer(i);
                    curr.put(c, 1);
                    count++;
                }
            }
            
            if(count == T.length()){
                if(i - start< min){
                    res[0] = start;
                    res[1] = i + 1;
                    min = i - start + 1;
                }
                
                curr.clear();
                count = 1;
                curr.put(S.charAt(start), 1);
                
                if(queue.peek() != null)
                	start = i = queue.poll();
            }
        }
        return S.substring(res[0], res[1]);
    }
	
	
	
	
	
	
	
	
	@Test
	public void testmultiply(){
		String A1 = new String("9");
		String A2 = new String("9");
		String res = multiply(A1,A2);
		System.out.println(res);
		int sum = 9 * 9;
		System.out.println(sum);
	}

	
	
	
	public String multiply(String A1, String A2) {
		StringBuffer num1 = new StringBuffer(A1);
		StringBuffer num2 = new StringBuffer(A2);
		num1.reverse();
		num2.reverse();
		
        int m = num1.length();
        int n = num2.length();
        
        int carrier = 0;
        int[] res = new int[m + n + 1];
        
        
        for(int k = 0; k <m ; k++){
            int d1 = num1.charAt(k) - '0';
            int carry = 0;
            
            for(int i = 0; i < n; i ++){
                int d2 = num2.charAt(i) - '0';
                
                int exist = res[k + i];
                res[k + i] = ( d1 * d2 +carry + exist )% 10 ;
                carry = ( d1 * d2 +carry + exist ) /10 ;
            }
            
            if(carry > 0){
            	res[k + n] = carry;
            	
            }
        }
        
        
        StringBuffer ret = new StringBuffer();
        for(int i = n + m; i >= 0; i--){
        	ret.append(res[i]);
        }
        while(ret.length() > 1 && ret.charAt(0) == '0'){
        	ret.deleteCharAt(0);
        }
        return ret.toString();
    }
	
	
	
//	@Test
//	public void testaddstring(){
//		String res = addString("1111111111111111111111123", "1110");
//		System.out.println(res);
//	}
//	
//	
//	
//	
//	@Test
//	public void testsinglemulti(){
//		String res = SingleMulti("12345", '0');
//		System.out.println(res);
//	}
//	
//	
//
//	
//	public String multiply(String A1, String A2) {
//        int n = A2.length();
//        List<String> res = new ArrayList();
//        
//        for(int i= n-1; i >= 0; i--){
//        	res.add(SingleMulti(A1, A2.charAt(i)));
//        }
//        
//        String sum = res.get(0);
//        for(int i = 1; i < n; i++){
//        	if(res.get(i).equals("0")){
//        		continue;
//        	}else{
//        		String tmp = getzero(res.get(i), i);
//        		sum = addString(sum, tmp);
//        	}
//        }
//        return sum;
//    }
//	
//	public String getzero(String tmp, int n){
//		StringBuffer buf =new StringBuffer();
//		for(int  i = 0; i < n; i++){
//			buf.append("0");
//		}
//		buf.append(tmp);
//		return buf.toString();
//	}
//	
//	
//	
//	public String SingleMulti(String main, char single){
//		int n = main.length();
//		int value = 0;
//		int carrier = 0;
//		if(single == '0')
//			return "0";
//		int mul = Integer.parseInt(single + "");
//		StringBuffer buf = new StringBuffer();
//		for(int i = n -1; i >= 0; i--){
//			value = carrier + Integer.parseInt(main.charAt(i) + "") * mul;
//			carrier = value / 10;
//			value = value % 10;
//			buf.append(value);
//		}
//		if(carrier != 0){
//			buf.append(carrier);
//		}
//		
//		return buf.reverse().toString();
//		
//	}
//	
//	
//	
//	public String addString(String A, String B){
//		int m = A.length()-1;
//		int n = B.length()-1;
//		
//		StringBuffer buf = new StringBuffer();
//		int carrier = 0;
//		
//		while(m >= 0 || n >= 0){
//			int value = 0;
//			if( m >= 0 && n >= 0){
//				 value = carrier + Integer.parseInt(A.charAt(m)+"") + Integer.parseInt(B.charAt(n) + "");
//				 m--;
//				 n--;
//			}else if( m >= 0){
//				 value = carrier + Integer.parseInt(A.charAt(m)+"");
//				 m--;
//			}else{
//				 value = carrier + Integer.parseInt(B.charAt(n) + "");
//				 n--;
//			}
//			if(value >= 10){
//				value -= 10;
//				carrier = 1;
//			}else{
//				carrier = 0;
//			}
//			buf.append(value);
//		}
//		if(carrier == 1){
//			buf.append(1);
//		}
//		
//		buf.reverse();//oh, yeah
//		return buf.toString();
//	}
	
	
	
	
	
	/**
	 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 
		For "(()", the longest valid parentheses substring is "()", which has length = 2.
		 
		Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
	 * *
	 */
	
	
	
	
	@Test
	public void testlongestvalidparentheses(){
		String A = new String("()(()");
		int count = longestValidParentheses(A);
		System.out.println(count);
	}
	
	
	
	 public int longestValidParentheses(String s) {
	        if (s==null||s.length()==0){
	            return 0;
	        }
	        int prestart=-1;
	        int maxLen=0;
	        Stack<Integer> stack=new Stack<Integer>();
	        for (int i=0; i<s.length();i++){
	        	if(s.charAt(i) == '('){
	        		stack.push(i);
	        	}else{
	        		if (stack.isEmpty()){
	                    // record the position before first left parenthesis
	                    prestart=i;
	                }else{
		        		stack.pop();
		        		
		        		
		        		if(stack.isEmpty()){
		        			maxLen = Math.max(i +1 - (prestart+1), maxLen);
		        		}else{
		        			maxLen = Math.max(i + 1 - (stack.peek() + 1), maxLen);
		        		}
	                }
	        	}
	        }
	        return maxLen;
	    }
	 
	 
	 
	 
//	
//	
//	
//	public int longestValidParentheses(String s){
////		LinkedList<Character> stack = new LinkedList();
//		int n = s.length();
//		char[] A = s.toCharArray();
//		int left = 0;
//		int right = 0;
//		int start = 0;
//		int max = 0;
//		int count = 0;
//		
//		for(int i = 0; i < n; i++){
//			
//			if(A[i] == ')'){
//				right++;
//			}else{
//				left++;
//			}
//			
//			if(left < right){
//				left = 0;
//				right = 0;
//				start ++;
//				i = start;
//				continue;
//			}
//			
//			if(left == right && left != 0){
//				count = left + right;
//				max = Math.max(count, max);
//			}
//			
//			if(left > right){
//				count = right * 2;
//				max = Math.max(count, max);
//			}
//			
//		}
//		
//		return max;
//	}
//	
	
	
	
	
	
//	public int longestValidParentheses(String s) {
//        int start = 0;
//        int n = s.length();
//        char[] A = s.toCharArray();
//        
//        int count = 0;
//        int left = 0;
//        int right = 0;
//        int max = 0;
//        for(int i = 0; i < n; i++){
//            if(A[i] == '('){
//                left++;
//            }else{
//                right++;
//            }
//            if(left < right){//invalid
//                left = 0;
//                right = 0;
//                start++;
//                i = start;
//                continue;
//            }
//            if(left == right && left != 0){
//                count = left + right;
//                max = Math.max(count, max);
//            }
//        }
//        return max;
//    }
	
	
//	public int longestValidParentheses(String s) {
//	    
//        // int start = 0;
//        int max = 0;
//        int count = 0;
//        int n = s.length();
//        for(int i = 0; i+1 < n; i ++){
//            if(check(s.charAt(i), s.charAt(i+1))){
//                for(;i+1 < n && check(s.charAt(i), s.charAt(i+1)); i+=2){
//                    count++;
//                }
//                max = Math.max(count, max);
//                count = 0;                
//            }
//        }
//        return max;
//    }
//    
//    boolean check(char left, char right){
//        return left == '(' && right == ')';
//    }
	
	
    
	@Test
	public void testatoi(){
		StringBuffer buf = new StringBuffer();
		buf.append("+1");
		String A = new String("-123");
		System.out.println(atoi(buf.toString()));
	}
	
	public int atoi(String str) {
		HashMap<Character, Integer> map = new HashMap();
		char c = '0';
		for(int i = 0 ; i < 10; i++,c++)
			map.put(c, i);
		
		//check str is valid number
		str = str.trim();
		if(str.length() == 0){
			return 0;
		}
		int n = str.length();
		//it is all the number
		
		
		boolean negative = false;
		if(str.charAt(0) == '-'){
			if(n == 1)
				return 0;
			else{
				negative = true;
				str = str.substring(1);
				n--;
			}
		}else if(str.charAt(0) == '+'){
			if(n == 1)
				return 0;
			else{
				str = str.substring(1);
				n--;
			}
		}else if(str.charAt(0) <= '0' || str.charAt(0) > '9'){
			return 0;
		}
		
		for(int i = 1; i < str.length(); i++){
			if(str.charAt(i)  < '0' || str.charAt(i) > '9')
				return 0;
		}
		
		//after check the str is number, then we do this
		if(n > 11)
			return 0;
		
		long sum = 0;
		
		for(int i = n-1; i>= 0; i--){
			int num = map.get(str.charAt(n-1-i));
			sum +=  num *  Math.pow(10, i);
		}
		
		
		if(negative){
			sum = 0 - sum;
			if(sum < Integer.MIN_VALUE)
				return 0;
			return (int) (0-sum);
		}else{
			if(sum > Integer.MAX_VALUE)
				return 0;
			return (int) sum;
		}
    }
	
	
}
