package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class day22 {
//	 public ListNode deleteDuplicates(ListNode head){
//	        if(head == null || head.next == null)
//	            return head;
//	        
//	        ListNode sentinel = new ListNode(0);
//	        ListNode tail = sentinel;
//	        ListNode prev = head;
//	        ListNode curr = head.next;        
//	        
//	        while(curr != null){
//	            if(prev.val != curr.val){
//	                prev = prev.next;
//	                curr = curr.next;
//	            }else{
//	                while(curr != null && prev.val == curr.val){
//	                    prev = prev.next;
//	                    curr = curr.next;
//	                }                
//	                if(curr == null){
//	                    tail.next = curr;
//	                    return head;
//	                }else{
//	                    tail.next = curr;
//	                    if(curr.next != null){
//	                        if(curr != curr.next){
//	                            tail = tail.next;
//	                        }else{
//	                            
//	                        }
//	                    }
//	                    
//	                }
//	                
//	            }
//	        }
//	        
//	        
//	        
//	    }
//	
//	 public ListNode deleteDuplicates(ListNode head){
//	       if(head == null || head.next == null)
//	            return head;
//	        
//	       ListNode prev = head;
//	       ListNode curr = head.next;
//	       
//	       while(curr != null){
//	           if(prev.val == curr.val){
//	               prev.next = curr.next;
//	               curr = curr.next;
//	           }else{
//	               prev = prev.next;
//	               curr = curr.next;
//	           }
//	       }
//	        
//	       return head;
//	    }
//	 
	 
	 
	
	public ListNode partition(ListNode head, int x) {
        ListNode head1 = null;//less than x
        ListNode tail1 = null;
        ListNode head2 = null;
        ListNode tail2 = null;
       
        ListNode A = head;
        while( A != null){
        	 ListNode newNode = A;A = A.next;newNode.next = null;
             if(newNode.val < x){
                if(head1 == null){
                    head1 = tail1 = newNode;
                }else{
                    tail1.next = newNode;
                    tail1 = tail1.next;
                }
            }else{
                if(head2 == null){
                    head2 = tail2 = newNode;
                }else{
                    tail2.next = newNode;
                    tail2 = tail2.next;
                }                
            }
            A = A.next;
        }
        if(head1 == null)
            return head2;
        tail1.next = head2;
        return head1;
    }
	
	public ListNode mergeTwoLists(ListNode A, ListNode B) {
        ListNode head = null;
        ListNode tail = null;
        ListNode newNode = null;
        
        while( A!= null || B!= null){
            if(A != null && B!= null){
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
	
	
	
	public ListNode mergeKLists(List<ListNode> lists){   
	       if(lists.size() == 0){
	           return null;
	       }
	       if(lists.size() == 1){
	           return lists.get(0);           
	       }
	       if(lists.size() == 2){
	           ListNode A = lists.get(0);
	           ListNode B = lists.get(1);
	           
	           ListNode head = null;
	           ListNode tail = null;
	           ListNode newNode = null;
	           
	           while( A != null || B != null){
	               if(A!= null && B != null){
	                    if(A.val < B.val){
	                        newNode = A;
	                        A = A.next;
	                        newNode.next = null;
	                    }else{
	                        newNode  = B;
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
	       
	       int len = lists.size()/2;
	       
	       List<ListNode> lstA = new ArrayList();
	       List<ListNode> lstB = new ArrayList();
	       
	       for(int i = 0; i < lists.size(); i++){
	           if(i < len){
	               lstA.add(lists.get(i));
	           }else{
	               lstB.add(lists.get(i));
	           }
	       }
	       
	       List<ListNode> res = new ArrayList();
	       ListNode A = mergeKLists(lstA);
	       ListNode B = mergeKLists(lstB);
	       res.add(A);
	       res.add(B);
	       
	       return mergeKLists(res);
	   }
	
	public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        boolean firstTime = true;
        
        while(true){
            if(fast == slow){
                if(firstTime){
                    firstTime = false;
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
            slow = slow.next;
        }
        
        slow = head;
        while(true){
            if(fast == slow)
                return fast;
            fast = fast.next;
            slow = slow.next;
        }
    }
	
	
	
	
	
	public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean firstTime = true;
        
        while(true){
            if(slow == fast){
                if(firstTime){
                    firstTime = false;
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
	
	
	public ListNode insertionSortList(ListNode head){
        if(head == null){
            return head;
        }        
        if(head.next == null){
            return head;
        }
        
        ListNode trav = head.next;
        ListNode tail = head;
        
        while(trav != null){
            ListNode ins = trav;
            
            if(ins.val >= tail.val){
                tail = tail.next;
            }else{
                tail.next = ins.next;
                
                if(ins.val <= head.val){
                    ins.next = head;
                    head = ins;
                }else{
                    ListNode curr = head;
                    while(true){
                        if(ins.val < curr.next.val){
                            ListNode tmp = curr.next;
                            curr.next = ins;
                            ins.next = curr.next;
                            break;
                        }
                    }
                }
            }
        }
        return head;
    } 
	
	
	
	
	 public RandomListNode copyRandomList(RandomListNode head) {
	        
	        RandomListNode newhead = new RandomListNode(head.label);
	        HashMap<RandomListNode, RandomListNode> map = new HashMap();
	        map.put(head, newhead);
	        
	        
	        RandomListNode trav = head.next;
	        RandomListNode newtrav = newhead;
	        
	        while(trav != null){
	            RandomListNode deepcopy = new RandomListNode(trav.label);
	            map.put(trav, deepcopy);
	            
	            newtrav.next =deepcopy;
	            newtrav = newtrav.next;
	            trav = trav.next;
	        }        
	        
	        trav = head;
	        newtrav = newhead;
	        while(trav != null){
	            newtrav.random = map.get(trav.random);
	            trav = trav.next;
	            newtrav = newtrav.next;
	        }
	        return newhead;
	    }
	 
	 
	
	
	 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        ListNode head = null;
        ListNode tail = null;
        ListNode newNode = null;
        int carry = 0;
        
        while(l1 != null || l2 != null){
            if(l1 != null && l2 != null){
                int value = l1.val + l2.val + carry;
                carry = value / 10;
                value = value % 10;
                newNode = new ListNode(value);
                
                l1 = l1.next;
                l2 = l2.next;
            }else if(l1 != null){
                int value = l1.val + carry;
                carry = value/10;
                value =value % 10;
                newNode = new ListNode(value);
                l1 = l1.next;
            }else{
                int value = l2.val + carry;
                carry = value / 10;
                value = value % 10;
                newNode = new ListNode(value);
                l2 = l2.next;
            }
            
            
            if(head == null){
                head = tail = newNode;
            }else{
                tail.next = newNode;
                tail = tail.next;
            }
        }
        
        if(carry == 1){
            tail.next = new ListNode(1);
        }
        
        return head;
    }
	
	
	
   public List<String> letterCombinations(String digits) {
		HashMap<Integer,String> keyboard = new HashMap<Integer,String>();
		keyboard.put(2,"abc");
		keyboard.put(3,"def");
		keyboard.put(4,"ghi");
		keyboard.put(5,"jkl");
		keyboard.put(6,"mno");
		keyboard.put(7,"pqrs");
		keyboard.put(8,"tuv");
		keyboard.put(9,"wxyz");
        List<String> res = new ArrayList();
	    StringBuffer sol = new StringBuffer();
	    dfs(0, digits, sol, keyboard, res);
	    return res;
   }
   
   void dfs(int start, String digits, StringBuffer sol,  HashMap<Integer, String> keyboard, List<String> res){
       if(start == digits.length()){
           res.add(sol.toString());
           return;           
       }
       
       int pos = digits.charAt(start) - '0';
       String tmp = keyboard.get(pos);
       
       for(int i = 0;i < tmp.length(); i++){
           sol.append(tmp.charAt(i));
           dfs(start+1, digits, sol, keyboard, res);
           sol.deleteCharAt(sol.length() - 1);
       }
   }
	
	
	
	@Test
	public void testconvert(){
		convert("ABC", 2);
	}
	
	
	public String convert(String s, int nRows){
        char[] A = s.toCharArray();
        int len  = s.length();
        char[] res = new char[len];
        int steps = 2 * (nRows - 1);
        int index = 0;  
        if(nRows == 1 || len < nRows){
            return s;
        }
        for(int i = 0; i < nRows; i++){
            int interval = (nRows - 1 - i) * 2;
            for(int k = i; k < len; k+= steps){
                res[index] = A[k];
                index++;
                if(interval < steps && interval > 0  && k + interval < len && index < len){
                    res[index] = A[k + interval];
                    index++;
                }
            }
        }
       
        return new String(res);
   }
	
	
	
	
	
	
	public ArrayList<Integer> grayCode(int n) {
	     if(n == 0){
	         ArrayList<Integer> res = new ArrayList();
	         res.add(0);
	         return res;
	     }
	     
	     ArrayList<Integer> prev = grayCode(n-1);
	     ArrayList<Integer> curr = (ArrayList<Integer>) prev.clone();
	     Collections.reverse(curr);
	     
	     int add = (int) Math.pow(2, n-1);
	     for(Integer num : curr){
	         num = num + add;
	     }
	     
	     ArrayList<Integer> res = new ArrayList<Integer>();
	     res.addAll(prev);
	     res.addAll(curr);
	     return res;
	 }
	 
	
	@Test
	public void testcountAndSay(){
		String tmp  =countAndSay(4);
		System.out.println(tmp);
	}
	
	public String countAndSay(int n) {
		   if( n == 0)
		    return "";
		   if(n == 1)
		    return "1";
		   if(n == 2)
		    return "11";
		   
		   String str = countAndSay(n-1);
		   
		   StringBuffer buf = new StringBuffer();
		   char[] A = str.toCharArray();
		   for(int i = 1; i < str.length(); i++){
	           if(A[i] != A[i-1]){
	               buf.append(1);
	               buf.append(A[i-1]);
	           }else{
	                int count = 1;
	                while(i < str.length() && A[i] == A[i-1]){
	                    i++;
	                    count++;
	                }               
	                buf.append(count);
	                buf.append(A[i-1]);
	           }
		   }
		   
		   if(A[str.length()-1] != A[str.length()-2]){
		       buf.append(1);
		       buf.append(A[str.length()-1]);
		   }
		   
		   return buf.toString();
	  	}
	
	
	
	public int maxSubArray(int[] A) {
        int local = 0;
        int global = Integer.MIN_VALUE;
        if(A.length == 0)
            return 0;
        for(int i = 0;i < A.length; i++){
            local = Math.max(local + A[i], A[i]);         
            global = Math.max(global, local);
        }
        return global;
        
    }
	
	
	
	 public int jump(int[] A) {
	        int lastreach = 0;
	        int reach = 0;
	        int steps = 0;
	        
	        for(int i =0; i <= reach && i < A.length; i++){
	            if(i > lastreach){
	                lastreach = reach;
	                steps++;
	            }
	            
	            reach = Math.max(A[i] + i, reach);
	        }
	        
	        
	        if(reach < A.length - 1){
	            return 0;
	        }
	        return steps;
	    }
	
	 public boolean canJump(int[] A){
	        int global = 0;
	        if(A == null || A.length == 0)
	            return false;
	        
	        int i = 0;
	        for(; i <= global && i < A.length; i++){
	            int local = A[i] + i;
	            global = Math.max(global, local);
	        }
	        if(i >= A.length){
	            return true;
	        }
	        return false;
	        
	    }
	
	
	public int maximalRectangle(char[][] matrix){
        int m = matrix.length;
        if(m == 0)   return 0;
        int n = matrix[0].length;
        int height[] = new int[n];
        int max = 0;
        for(int i = 0; i < m; i++){
            for(int k = 0; k < n; k++){
                if(matrix[i][k] == '0'){
                    height[k] = 0;
                }else{
                    height[k] = height[k] + 1;
                }
            }
            Stack<Integer> stack = new Stack();
            for(int j = 0; j <= n; j++){
                int curt = j == n ? -1 : height[j];
                while(!stack.empty() && height[stack.peek()] > curt){
                    int index = stack.pop();
                    int w = stack.empty() ? j : j - (stack.peek()+1);
                    int h = height[index];
                    int curArea = w * h;
                    max = Math.max(curArea, max);
                }
                stack.push(j);                
            }            
        }
        return max;
    }
	/**
	 * stack at most push every elem once,  still it is O(n)
	 */
	
	
	
	
	public int evalRPN(String[] tokens){
		Stack<Integer> stack = new Stack();
		String operators = new String("+-*/");
		
		for(int i = 0; i < tokens.length; i++){
			if(!operators.contains(tokens[i])){
				stack.push(Integer.valueOf(tokens[i]));
				continue;
			}
			
			int a = stack.pop();
			int b = stack.pop();
			if(tokens[i].equals("+")){
				stack.push(b + a);
			}else if(tokens[i].equals("-")){
				stack.push(b - a);
			}else if(tokens[i].equals("*")){
				stack.push(b * a);
			}else {
				stack.push(b / a);
			}
		}
		return stack.pop();
		
	}

	
	
	
	
	
	
	public int evalRPN1(String[] tokens){
        int n = tokens.length;
        Stack<String> stack  = new Stack();
        
        for(int i =0; i < n; i++){
            String tmp = tokens[i];
            if("+-*/".contains(tmp)){
                if(tmp.equals("+")){
                    String s1 = stack.pop();
                    String s2 = stack.pop();
                    int res = Integer.parseInt(s2) + Integer.parseInt(s1);
                    stack.push(res + "");
                }else if(tmp.equals("-")){
                    String s1 = stack.pop();
                    String s2 = stack.pop();
                    int res = Integer.parseInt(s2) - Integer.parseInt(s1);
                    stack.push(res + "");
                }else if(tmp.equals("*")){
                    String s1 = stack.pop();
                    String s2 = stack.pop();
                    int res = Integer.parseInt(s2) * Integer.parseInt(s1);
                    stack.push(res + "");
                }else{
                    String s1 = stack.pop();
                    String s2 = stack.pop();
                    int res = Integer.parseInt(s2) / Integer.parseInt(s1);
                    stack.push(res + "");
                }
            }else{
                stack.push(tmp);
            }
        }
        return Integer.parseInt(stack.pop());
    }
	
	
	 public TreeNode buildTree(int[] preorder, int[] inorder){
         if(preorder.length != inorder.length)
            return null;
         return buildTreeHelper(inorder, 0, preorder, 0, inorder.length);
     }
     
     public TreeNode buildTreeHelper(int[] inorder, int istart, int[] preorder, int pstart, int len){
         if(len == 0)
            return null;
         if(len == 1)
            return new TreeNode(1);
         
        int rootvalue = preorder[pstart];
        TreeNode root = new TreeNode(rootvalue);
        
        int rootindex = -1;
        
        for(int i = 0; i < len; i++){
            if(rootvalue == inorder[i]){
                rootindex = i;
                break;
            }
        }
        
        int lengthleft = rootindex - istart;
        int lengthright = len - 1 - lengthleft;
        
        root.left = buildTreeHelper(inorder, istart, preorder, pstart + 1, lengthleft );
        root.right = buildTreeHelper(inorder,rootindex+1, preorder, pstart + 1 + lengthleft, lengthright);
        return root;
     }
     
     
	
	
	
	
	public TreeNode buildTree1(int[] inorder, int[] postorder){
        if(inorder.length != postorder.length)
            return null;
        return buildhelper(inorder, 0, postorder, 0, inorder.length);        
    }
    
    public TreeNode buildhelper(int[] inorder, int istart, int[] postorder, int pstart, int len){
        if(len == 0){
            return null;
        }
        
        if(len == 1)
            return new TreeNode(postorder[pstart + len - 1]);
        int rootvalue = postorder[pstart + len - 1];
        int rootindex = -1;
        
        for(int i = istart; i < istart + len; i++){
            if(inorder[i] == rootvalue){
                rootindex = i;
                break;
            }            
        }
        
        TreeNode root = new TreeNode(rootvalue);
        
        int lengthleft = rootindex - istart;
        int lengthright = len - 1 - lengthleft;
        
        root.left = buildhelper(inorder, istart, postorder, pstart, lengthleft);
        root.right = buildhelper(inorder, rootindex+1, postorder, pstart + lengthleft, lengthright);
        return root;
        
    }
	
	
	  
		public int minDepth(TreeNode root){
	        if(root == null){
	            return 0;
	        }
	        
	        if(root.left != null && root.right != null){
	            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
	        }else if(root.left != null){
	            return minDepth(root.left) + 1;
	        }else if(root.right != null){
	            return minDepth(root.right) +1;
	        }else{
	            return 1;
	        }
	        
		}
	
	
	
	public TreeNode sortedListToBST(ListNode head){
        if(head == null)
            return null;
        int length = 0;
        ListNode curr = head;
        for(; curr!= null; curr = curr.next)
            length++;
        
            
        return helper(head, length);
	}
	
	public TreeNode helper(ListNode root, int len){
	    if(len == 0)
	        return null;
	    if(len == 1)
	        return new TreeNode(root.val);
	    
	    ListNode midnode = root;
	    for(int i = 0; i < len/2; i++){
	        midnode = midnode.next;
	    }
	    
	    TreeNode res = new TreeNode(midnode.val);
	    
	    int lengthleft = len/2;
	    int lengthright = len - lengthleft - 1;
	    
	    res.left = helper(root, lengthleft);
	    res.right = helper(midnode.next, lengthright);
	    return res;
	}
	
	
	public TreeNode sortedArrayToBST(int[] A){
        int n = A.length;
        return helper(A, 0, n);
    }
    
    public TreeNode helper(int[] A, int start, int len){
        if(len == 0)
            return null;
        if(len == 1)
            return new TreeNode(A[start]);
        
        int midindex = len/2 + start;
        TreeNode midnode = new TreeNode(A[midindex]);
        
        int lengthleft = midindex - start;
        int lengthright = len - 1 - lengthleft;
        
        midnode.left = helper(A, start, lengthleft );
        midnode.right = helper(A, midindex+1, lengthright);
        return midnode;       
    }
    
    
    
	
	
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        
        if(root == null){
            return res;
        }    

        Stack<TreeNode> curr = new Stack();
        Stack<TreeNode> next = new Stack();
        Stack<TreeNode> tmp;
        boolean normalorder = true;
        
        while(!curr.empty()){
            List<Integer> sol = new ArrayList();
            while(!curr.empty()){
                TreeNode currnode = curr.pop();
                sol.add(currnode.val);
                
                if(normalorder){
                    if(currnode.right != null){
                        next.push(currnode.right);
                    }
                    if(currnode.left != null){
                        next.push(currnode.left);
                    }
                }
            }
            
            tmp = curr;
            curr = next;
            next = tmp;
            normalorder = !normalorder;
            res.add(sol);
        }
        return res;
    }
	
	
	
	
	
	
	public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> res = new ArrayList();
        if(root == null)
            return res;
        Deque<TreeNode> queue = new ArrayDeque();
        queue.offer(root);
        
        int currLevel = 1;
        int nextLevel = 0;
        
        List<Integer> sol = new ArrayList();
        while(queue.peek() != null){
            TreeNode currnode = queue.poll();
            currLevel --;
            sol.add(currnode.val);
            
            if(currnode.left != null){
                nextLevel ++;
                queue.offer(currnode.left);
            }
            if(currnode.right != null){
                nextLevel++;
                queue.offer(currnode.right);
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
	
	
	@Test
	public void testdelivervalue(){
		HashSet sol = new HashSet();
		add(sol);
		System.out.println(sol.size());
	}
	
	public void add(HashSet sol){
		sol.add(1);
	}
	
	
	
	 public int canCompleteCircuit(int[] gas, int[] cost) {
	        
	        int total = 0;
	        int cursum = 0;
	        int start = 0;
	        
	        for(int i = 0; i < gas.length; i++){
	            cursum += gas[i] - cost[i];
	            total += gas[i] - cost[i];
	            if(cursum < 0){
	                start = i + 1;
	                cursum = 0;
	            }
	        }
	        
	        if(total < 0)
	            return -1;
	        return start;
	        
	 }
	
	
	
	 public boolean isNumber(String s) {
	        int len = s.length();
	        int i = 0, e = len - 1;
	        while (i <= e && Character.isWhitespace(s.charAt(i))) i++;
	        if (i > len - 1) return false;
	        while (e >= i && Character.isWhitespace(s.charAt(e))) e--;
	        // skip leading +/-
	        if (s.charAt(i) == '+' || s.charAt(i) == '-') i++;
	        
	        boolean num = false; // is a digit
	        boolean dot = false; // is a '.'
	        boolean exp = false; // is a 'e'
	        
	        
	        while (i <= e) {
	            char c = s.charAt(i);
	            if(Character.isDigit(c)){
	                num = true;
	            }else if(c == '.'){
	                if(dot || exp) return false;
	                dot = true;
	            }else if(c == 'e'){
	                if(num || exp) return false;
	                exp = true;
	            }else if( c== '+' || c == '-'){
	                if(s.charAt(i-1) != 'e') return false;
	            }else{
	                return false;
	            }
	            i++;
	        }
	        return num;
	    }
	
	
	
	 public int lengthOfLongestSubstring(String S) {
		 	int n = S.length();
	        char[] A = S.toCharArray();
	        HashSet<Character> hashSet = new HashSet();
	        
	        //O(n^2)
	        int max = Integer.MIN_VALUE;
	        int start = 0;
	        for(int i =0 ; i < n ; i++){
	            if(!hashSet.contains(A[i])){
	                hashSet.add(A[i]);
	            }else{
	                int length = i - start;
	                if(length > max)
	                    max = length;
	                for(int k = start; k < i; k++){
	                    if(A[k] == A[i]){
	                        start = k + 1;
	                        break;
	                    }else{
	                        hashSet.remove(A[k]);
	                    }                    
	                }
	            }
	        }
	        if(n - start > max)
	            max = n- start;
	        return max;
	    }
	

	@Test
	public void testpostorderTraversal(){
		TreeNode root = new TreeNode(50);
		root.left = new TreeNode(30);
		root.right = new TreeNode(70);
		root.left.left = new TreeNode(10);
		root.left.right = new TreeNode(40);
		
		root.right.left = new TreeNode(60);
		root.right.right = new TreeNode(90);
		
		List<Integer> res = postorderTraversal(root);
		System.out.println(res);
		
	}
	
	public List<Integer> postorderTraversal(TreeNode root){
        Stack<TreeNode> stack = new Stack();
        List<Integer> res = new ArrayList();
        stack.push(root);
        
        TreeNode curr = root;
        TreeNode pre  = null;
        
        
        while(!stack.empty()){
            curr = stack.peek();
            
            //traverse to the bottom
            if(pre == null || pre.left == curr || pre.right == curr){
                if(curr.left != null){
                    stack.push(curr.left);
                }else if(curr.right != null){
                    stack.push(curr.right);
                }
            }else if(curr.left == pre){
                //traverse up from the left tree
                if(curr.right != null){
                    stack.push(curr.right);
                }
            }else{
                //traverse up from the right tree
                res.add(curr.val);
                stack.pop();
            }
            pre = curr;            
        }
        return res;
    }
	
	
	
	
	
	 public List<Integer> preorderTraversal(TreeNode root) {
	        List<Integer> res = new ArrayList();
	        
	        
	        Stack<TreeNode> stack = new Stack();
	        
	        TreeNode curr = root;
	        TreeNode pre  = null;
	        stack.push(root);
	        res.add(root.val);
	        
	        while(curr!=null || !stack.isEmpty()){
	            curr = stack.peek();
	            if(pre == null || pre.left == curr || pre.right == curr){
	                //traverse down to the bottom
	                if(curr.left != null){
	                    stack.push(curr.left);
	                    res.add(curr.left.val);
	                }else if(curr.right != null){
	                    stack.push(curr.right);
	                    res.add(curr.right.val);
	                }
	            }else if(pre == curr){
	                //left most node
	                if(curr.right != null){
	                    stack.push(curr.right);
	                    res.add(curr.right.val);
	                }else{
	                    //leave node
	                    stack.pop();
	                }
	            }else{
	            	//traverse up to the tree
	            	stack.pop();
	            }
	            pre = curr;
	        }
	        
	        return res;
	    }
}
