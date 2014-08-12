package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class day19 {
	
	
	/**
	 * sentinel, midnode, mid is very important in this question
	 * @param head
	 * @return
	 */
	
	
	public TreeNode sortedListToBST(ListNode head){
	    if(head == null)
	        return null;
	    if(head.next == null){
	        return new TreeNode(head.val);
	    }
	    
	    int length = 0;
	    ListNode curr = head;
	    
	    for(;curr!= null;curr = curr.next)
	    	length++;
	    
	    ListNode sentinel =  new ListNode(0);
	    sentinel.next = head;
	    
	    curr = sentinel;
	    for(int i = 0;i  < length/2; i++){
	    	curr = curr.next;
	    }
	    
	    ListNode newhead = curr.next;
	    TreeNode midnode = new TreeNode(newhead.val);
	    curr.next = null;
	    midnode.left = sortedListToBST(head);
	    midnode.right = sortedListToBST(newhead.next);
	    
	    return midnode;
	}
	
	
	public List<TreeNode> generateTrees(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
     
     List<TreeNode> result=buildBST(1,n);
     
     return result;
    }
    
    List<TreeNode> buildBST(int start, int end){
        List<TreeNode> res = new ArrayList();

        if(start > end){
            return res;
        }
        if(start == end){
        	res.add(new TreeNode(start));
            return res;
        }

                
        for(int i = start; i <= end; i++){
            List<TreeNode> lefts = buildBST(start, i-1);
            List<TreeNode> rights = buildBST(i+1, end);
                        
            for(TreeNode left : lefts){
                
                for(TreeNode right : rights){
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right =right;
                    
                    res.add(root);                    
                }
            }
        }
        return res;
    }
    
	
	private class ResultType{
        int singlepath, maxpath;
        ResultType(int singlepath,int maxpath){
            this.singlepath = singlepath;
            this.maxpath = maxpath;
        }
    }
     
    ResultType helper(TreeNode root){
        if(root == null){
            return new ResultType(0, Integer.MIN_VALUE);
        }
        
        //divide
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        int singlepath = Math.max(left.singlepath, right.singlepath) + root.val;
        singlepath = Math.max(singlepath, 0);
        
        int maxpath = Math.max(left.maxpath, right.maxpath);
        maxpath = Math.max(maxpath, left.singlepath + right.singlepath + root.val);
        
        return new ResultType(singlepath, maxpath);
    }
     
    
    public int maxPathSum(TreeNode root) {
        ResultType result = helper(root);
        return result.maxpath;
    }
	
	
	public List<List<Integer>> zigzagLevelOrder11(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        
        
        Stack<TreeNode> curr = new Stack();
        Stack<TreeNode> next = new Stack();
        Stack<TreeNode> tmp  = new Stack();
        
        curr.push(root);
        
        boolean normalorder = true;
        while(!curr.isEmpty()){
            
            List<Integer> currLevelresult = new ArrayList();    
            
            while(!curr.isEmpty()){
                TreeNode currnode = curr.pop();
                currLevelresult.add(currnode.val);
                
                if(normalorder){
                    if(currnode.left != null){
                        next.push(currnode.left);            
                    }
                    
                    if(currnode.right != null){
                        next.push(currnode.right);
                    }
                }else{
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
        }
        
        return res;
        
        
    }
	
	
	
	public TreeNode buildTree111(int[] inorder, int[] postorder) {
		if (inorder.length != postorder.length) {
			return null;
		}

		return getTree111(inorder, 0, postorder, 0, inorder.length);
	}

	public TreeNode getTree111(int[] inorder, int istart, int[] postorder,
			int pstart, int length) {
		if (length == 0)
			return null;
		if (length == 1)
			return new TreeNode(postorder[pstart + length - 1]);
		int rootvalue = postorder[pstart + length - 1];
		int rootindex = -1;

		for (int i = istart; i < istart + length; i++) {
			if (inorder[i] == rootvalue) {
				rootindex = i;
				break;
			}
		}

		TreeNode root = new TreeNode(rootvalue);

		int lengthleft = rootindex - istart;
		int lengthright = length - lengthleft - 1;

		root.left = getTree(inorder, istart, postorder, pstart, lengthleft);
		root.right = getTree(inorder, rootindex + 1, postorder, pstart
				+ lengthleft, lengthright);
		return root;

	}
	
	 public TreeNode buildTree11(int[] preorder, int[] inorder){
	       if(preorder.length != inorder.length)
	            return null;
	        return getTree1111(inorder, 0, preorder, 0, preorder.length);       
	       
	   }
	   
	   TreeNode getTree1111(int[] inorder, int istart, int[] preorder, int pstart, int length){
	        if(length == 0){
	            return null;
	        }       
	        
	        if(length == 1){
	            return new TreeNode(preorder[pstart]);
	        }
	        
	        int rootvalue = preorder[pstart];
	        
	        TreeNode root = new TreeNode(rootvalue);
	                
	        int rootindex = -1;
	        for(int i = istart; i < istart + length; i++){
	            if(inorder[i] == rootvalue){
	                rootindex = i;
	                break;
	            }
	        }     
	        
	        int lengthleft = rootindex - istart;
	        int lengthright = length - lengthleft - 1;
	        
	        root.left = getTree(inorder, istart, preorder, pstart+1, lengthleft);
	        root.right = getTree(inorder, rootindex+1, preorder, pstart + lengthleft +1, lengthright);
	        return root;
	   }
	   
	   
	   
	
	 /**
     *  since h depend the min(height[left] , height[right]),  we did not need the lower one,
     *  and drop it, and move inside.
     * */
     
    public int maxArea(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n - 1;
        
        int max = 0;
        while(left < right){
            int w = right - left;
            int h = Math.min(height[right], height[left]);
            max = Math.max(w * h, max);
            
            if(height[right] > height[left]){
                left++;
            }else{
                right--;
            }
        }
        
        return max;
        
    }
	
	
	
	@Test
	public void testthreeSum(){
		 int[] A = new int[]{-1, -1, 0, 1, 2, -4};
		 List<List<Integer>> res = threeSum(A);
		 System.out.println(res);
		
	}
	
	public List<List<Integer>> threeSum(int[] A){
        List<List<Integer>> res = new ArrayList();
        int n = A.length;
        Arrays.sort(A);
        for(int i = 0; i < n; i++){
            if( i > 0 && A[i] == A[i-1])// to skip duplicates
                continue;
            int start = i + 1; 
            int last = n - 1;
            for(;start < last;){
            
                
                int sum = A[i] + A[start] + A[last];
                if(sum == 0){
                    List<Integer> tmp = new ArrayList();
                    tmp.add(A[i]);
                    tmp.add(A[start]);
                    tmp.add(A[last]);
                    
                    res.add(tmp);
                    start++;
                    last--;
                    while (start < last && A[start] == A[start - 1]) { // to skip duplicates
						start++;
					}
					while (start < last && A[last] == A[last + 1]) { // to skip duplicates
						last--;
					}
                }else if(sum > 0){
                    last--;
                }else{
                    start++;
                }
            }
        }
        return res;
    }
	
	@Test
	public void testlargestRectangleArea(){
		
		largestRectangleArea(new int[]{2,1,5,6,2,3});
	}
	
	
	public int largestRectangleArea(int[] height){
        int n = height.length;
        Stack<Integer> stack = new Stack();
        int max = 0;
        
        for(int i = 0; i < n; i++){
            while( !stack.isEmpty() && height[stack.peek()] > height[i] ){
                int index = stack.pop();
                int curArea = stack.isEmpty() ? height[index] * (i - 0) : height[index] * (i-(stack.peek() + 1));
                max = Math.max(curArea, max);
            }
            stack.push(i);
        }
        
        while(!stack.isEmpty()){
            int index = stack.pop();
            int curArea = stack.isEmpty() ? height[index] * (n - 0) : height[index] * (n-(stack.peek() +1));
            max = Math.max(curArea, max);
        }
        return max;
    }
	
	
	
	@Test
	public void testconvert(){
		String s = convert("123456789", 3);
	}
	
	
	
	public String convert(String s, int nRows) {
        int length = s.length();
        if (length <= nRows || nRows == 1) return s;
        char[] chars = new char[length];
        int step = 2 * (nRows - 1);
        int count = 0;
	    for (int i = 0; i < nRows; i++){
    		int interval = step - 2 * i;
    		for (int j = i; j < length; j += step){
    		   	chars[count] = s.charAt(j);
    			count++;
    			if (interval < step && interval > 0 && j + interval < length && count <  length){
    				chars[count] = s.charAt(j + interval);
    				count++;
    			}
    		}
    	}
        return new String(chars);
    }
	
//	public String convert(String s, int nRows){
//		int length = s.length();
//		if(length <= nRows || nRows == 1) return s;
//		char[] chars = new char[length];
//		int steps = 2 * (nRows-1);
//		int count = 0;
//		for(int i = 0; i < nRows; i++){
//			int interval = steps - 2 * i;
//			for(int j = i; j < length; j+= steps){
//				chars[count++] = s.charAt(j);
//				if(interval < steps && interval > 0 && j + interval < length && count < length){
//					chars[count++] = s.charAt(j + interval);
//				}
//				
//			}
//		}
//	}
//	
	
	
	   private class ResultType{
           int singlepath, maxpath;
           ResultType(int singlepath, int maxpath){
               this.singlepath = singlepath;
               this.maxpath = maxpath;
           }
       }
       
       private ResultType helper(TreeNode root){
           if(root == null){
               return new ResultType(0, Integer.MIN_VALUE);
           }
           
           //divide
           ResultType left = helper(root.left);
           ResultType right = helper(root.right);
           
           
           //conquer
           int singlepath = Math.max(left.singlepath, right.singlepath);
           singlepath = Math.max(0, singlepath);
           
           int maxpath = Math.max(left.maxpath, right.maxpath);
           maxpath = Math.max(maxpath, left.singlepath + right.singlepath + root.val);//left single >= 0 && right single >= 0
           
           return new ResultType(singlepath, maxpath);
       }
       
       public int maxPathSum1(TreeNode root) {
           ResultType result = helper(root);
           return result.maxpath;
       }
       
       
	    
	    
	    
	    
	    
	    
	    
	
	 @Test
	 public void testisNumber(){
		 isNumber("+1");
		 isNumber("00001000");
		 isNumber("001a");
		 
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
	            if (Character.isDigit(c)) {
	                num = true;
	            }
	            else if (c == '.') {
	                if(exp || dot) return false;
	                dot = true;
	            }
	            else if (c == 'e') {
	                if(exp || num == false) return false;
	                exp = true;
	                num = false;
	            }
	            else if (c == '+' || c == '-') {
	                if (s.charAt(i - 1) != 'e') return false;
	            }
	            else {
	                return false;
	            }
	            i++;
	        }
	        return num;
	    }
	 
	 
	 
	
	
	
	
//	public int maxPathSum(TreeNode root) {
//        int[] max = new int[1];
//        max[0] = Integer.MIN_VALUE;
//        if(root == null){
//            return 0;
//        }
//        getdac(root,max);
//        return max[0];
//    }
//    
//    public int getdac(TreeNode root, int[] max){
//        if(root.left != null && root.right != null){
//            int leftpartial = getdac(root.left, max);
//            int rightpartial = getdac(root.right, max);
//            
//            int maxpartial = Math.max(leftpartial, rightpartial);
//            max[0] = Math.max(max[0], maxpartial + root.val);
//            max[0] = Math.max(max[0], maxpartial);
//            max[0] = Math.max(max[0], leftpartial + rightpartial + root.val);
//            max[0] = Math.max(max[0], root.val);
//            return maxpartial + root.val;
//        }else if(root.left != null){
//            int leftpartial = getdac(root.left, max);
//            max[0] = Math.max(max[0], leftpartial + root.val);
//            max[0] = Math.max(max[0], leftpartial);
//            max[0] = Math.max(max[0], root.val);
//            return leftpartial + root.val;            
//        }else if(root.right != null){
//            int rightpartial = getdac(root.right, max);
//            max[0] = Math.max(max[0], rightpartial + root.val);
//            max[0] = Math.max(max[0], rightpartial );
//            max[0] = Math.max(max[0], root.val);
//            return rightpartial + root.val;
//        }else{
//            max[0] = Math.max(max[0],root.val);
//            return root.val;
//        }
//    }
    

	
	
	@Test
	public void testZigzagLevelOrder(){
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		
		zigzagLevelOrder1(root);
		
	}
	
	
	
	public ArrayList<ArrayList<Integer>> zigzagLevelOrder1(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        if (root == null) {
            return result;
        }

        Stack<TreeNode> currLevel = new Stack<TreeNode>();
        Stack<TreeNode> nextLevel = new Stack<TreeNode>();
        Stack<TreeNode> tmp;
        
        currLevel.push(root);
        boolean normalOrder = true;

        while (!currLevel.isEmpty()) {
            ArrayList<Integer> currLevelResult = new ArrayList<Integer>();

            while (!currLevel.isEmpty()) {
                TreeNode node = currLevel.pop();
                currLevelResult.add(node.val);

                if (normalOrder) {
                    if (node.left != null) {
                        nextLevel.push(node.left);
                    }
                    if (node.right != null) {
                        nextLevel.push(node.right);
                    }
                } else {
                    if (node.right != null) {
                        nextLevel.push(node.right);
                    }
                    if (node.left != null) {
                        nextLevel.push(node.left);
                    }
                }
            }

            result.add(currLevelResult);
            tmp = currLevel;
            currLevel = nextLevel;
            nextLevel = tmp;
            normalOrder = !normalOrder;
        }

        return result;
    }
	
	
	   public int maxPathSum(TreeNode root) {
	        int[] max = new int[1];
	        max[0] = Integer.MIN_VALUE;
	        if(root == null){
	            return 0;
	        }
	        getdac(root,max);
	        return max[0];
	    }
	    
	    public int getdac(TreeNode root, int[] max){
	        if(root.left != null && root.right != null){
	            int leftpartial = getdac(root.left, max);
	            int rightpartial = getdac(root.right, max);
	            
	            int maxpartial = Math.max(leftpartial, rightpartial);
	            max[0] = Math.max(max[0], maxpartial + root.val);
	            max[0] = Math.max(max[0], leftpartial + rightpartial + root.val);
	            max[0] = Math.max(max[0], root.val);
	            return maxpartial + root.val;
	        }else if(root.left != null){
	            int leftpartial = getdac(root.left, max);
	            max[0] = Math.max(max[0], leftpartial + root.val);
	            max[0] = Math.max(max[0], root.val);
	            return leftpartial + root.val;            
	        }else if(root.right != null){
	            int rightpartial = getdac(root.right, max);
	            max[0] = Math.max(max[0], rightpartial + root.val);
	            max[0] = Math.max(max[0], root.val);
	            return rightpartial + root.val;
	        }else{
	            max[0] = Math.max(max[0],root.val);
	            return root.val;
	        }
	    }
	    
	    
	    
	
	
	  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
	        List<List<Integer>> res = new ArrayList();
	        
	        Deque<TreeNode> queue = new ArrayDeque();
	        
	        queue.offer(root);
	        int currLevel = 1;
	        int nextLevel = 0;
	        
	        
	        List<Integer> sol = new ArrayList();
	        boolean reverse = false;
	        while(queue.peek() != null){
	            
	            TreeNode treenode = queue.poll();
	            currLevel --;
	            sol.add(treenode.val);
	            
	            if(treenode.left != null){
	                queue.offer(treenode);
	                nextLevel++;
	            }       
	            if(treenode.right != null){
	                queue.offer(treenode);
	                nextLevel++;
	            }
	            
	            if(currLevel == 0){
	                if(reverse){
	                	Collections.reverse(sol);
	                }
	                reverse = !reverse;
	                List<Integer> tmp = new ArrayList();
	                res.add(sol);
	                currLevel = nextLevel;
	                nextLevel = 0;
	            }
	        }
	        
	        return res;
	    }
	
	
	
//	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
//        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
//
//        if (root == null) {
//            return result;
//        }
//
//        Stack<TreeNode> currLevel = new Stack<TreeNode>();
//        Stack<TreeNode> nextLevel = new Stack<TreeNode>();
//        Stack<TreeNode> tmp;
//        
//        currLevel.push(root);
//        boolean normalOrder = true;
//
//        while (!currLevel.isEmpty()) {
//            ArrayList<Integer> currLevelResult = new ArrayList<Integer>();
//
//            while (!currLevel.isEmpty()) {
//                TreeNode node = currLevel.pop();
//                currLevelResult.add(node.val);
//
//                if (normalOrder) {
//                    if (node.left != null) {
//                        nextLevel.push(node.left);
//                    }
//                    if (node.right != null) {
//                        nextLevel.push(node.right);
//                    }
//                } else {
//                    if (node.right != null) {
//                        nextLevel.push(node.right);
//                    }
//                    if (node.left != null) {
//                        nextLevel.push(node.left);
//                    }
//                }
//            }
//
//            result.add(currLevelResult);
//            tmp = currLevel;
//            currLevel = nextLevel;
//            nextLevel = tmp;
//            normalOrder = !normalOrder;
//        }
//        return result;
//    }
	
	
	
//	1,2,4,5,3,6,7,
//	4,2,5,1,6,3,7,
	
	
	
	public ListNode removeNthFromEnd(ListNode head, int n) {
        if( head == null)   
            return head;
        
        int length = 0;
        ListNode curr = head;
        
        for(; curr != null; curr = curr.next) 
            length ++;
        
        if( n > length || n <= 0 )
            return head;
        
        int pre = length - n;
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        
        curr = sentinel;
        
        for(int i = 0; i < pre; i++){
            curr = curr.next;
        }
        curr.next = curr.next.next;
        
        return sentinel.next;        
    }
	
	
	
	
	
	
	@Test
	public void testbuildTree1(){
		int[] inorder = new int[]{10,30,40,50,60,70,90};
		int[] postorder = new int[]{10,40,30,60,90,70,50};

		TreeNode res = buildTree1(inorder, postorder);
		inorder(res);
		System.out.println();
		postorder(res);
		
	}
	
	
	
	
	public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if(inorder.length != postorder.length)
            return null;
        int n = inorder.length;
        return getTree(inorder, 0, postorder, 0, inorder.length);
    }
    
    private TreeNode getTree(int[] inorder, int istart, int[] postorder, int pstart, int length){
        if(length == 0)
            return null;
        if(length == 1)
            return new TreeNode(postorder[pstart + length - 1]);//here may have index out of border
            
        int rootvalue = postorder[pstart + length-1];
        int rootindex = -1;
        for(int i = istart; i < istart + length; i++){
            if(inorder[i] == rootvalue){
                rootindex = i;
                break;
            }
        }
                
        int lengthleft = rootindex - istart;
        int lengthright = length - lengthleft - 1;
        
        TreeNode root = new TreeNode(rootvalue);
        root.left = getTree(inorder, istart, postorder, pstart, lengthleft);
        root.right = getTree(inorder, rootindex+1, postorder, pstart + lengthleft, lengthright);
        return root;
    }
	
    
    
    
	@Test
	public void testbuildTree(){
		int[] preorder = new int[]{50,30,10,40,70,60,90};
		int[] inorder = new int[]{10,30,40,50,60,70,90};

		TreeNode res = buildTree(preorder, inorder);
		preorder(res);
		System.out.println();
		inorder(res);
		
	}
	
	/**
	 *   Actually, this questions can be solved by using length + start index, this is very correct way to do in Java
	 *   in C, you can just deliver the new head address, but not in Java.
	 * 
	 * @param preorder
	 * @param inorder
	 * @return
	 */

	
	 public TreeNode buildTree(int[] preorder, int[] inorder){
			if (inorder == null || preorder == null || inorder.length != preorder.length)
	            return null;
	        
			int n = preorder.length;
	        if(n == 0)
	            return null;
	        return getTree(inorder, 0, n, preorder, 0, n);
	    }
	    
		
		
		public TreeNode getTree(int[] inorder, int istart, int len1, int[] preorder, int pstart, int len2){
		    if(len1 == 0)
		        return null;
		    
			if(len1 == 1){
				return new TreeNode(preorder[pstart]);
			}
			
			int rootvalue = preorder[pstart];
		 	int rootindex = -1;
			for(int i = istart; i < istart + len1; i++){
				if(inorder[i] == rootvalue){
					rootindex = i;
					break;
				}
			}
			
			TreeNode root = new TreeNode(rootvalue);
			int lengthleft = rootindex - istart;
			int lengthright = len1 - 1 - lengthleft;
			
			root.left = getTree(inorder, istart, lengthleft, preorder, pstart+1, lengthleft);
			root.right = getTree(inorder, rootindex+1, lengthright, preorder, pstart + lengthleft +1, lengthright);
			
			return root;
		}
//		if(inorder[istart] == preorder[pstart])
//			return new TreeNode(inorder[istart]);
//		
//		int rootvalue = preorder[pstart];
//		
//		int rootindex = -1;
//		for(int i = istart; i <= ilast; i++){
//			if(inorder[i] == rootvalue){
//				rootindex = i;
//				break;
//			}
//		}
//		
//		TreeNode root = new TreeNode(rootvalue);
//		
//		root.left = getTree(inorder, istart, rootindex-1, preorder, pstart+1, rootindex);
//		root.right = getTree(inorder, rootindex, ilast, preorder, rootindex+1, plast);
//		return root;
	
	
	
	
	
	
    
//    public TreeNode getTree(int[] preorder, int startx,int lastx, int[] inorder, int starty, int lasty){
//    	if(startx > lastx)
//    		return null;
//    	if(startx == lastx)
//    		return new TreeNode(preorder[startx]);
//    		
//        TreeNode root = new TreeNode(preorder[startx]);
//        int rootindex = 0;
//        
//        for(int i = starty; i <= lasty; i++){
//        	if(inorder[i] == preorder[startx]){
//        		rootindex = i;
//        		break;
//        	}
//        }
//        
//        root.left = getTree(preorder, startx + 1, rootindex, inorder, starty, rootindex-1);
//        root.right = getTree(preorder, rootindex+1,lastx, inorder, rootindex+1, lasty);
//        return root;
//    }
	
	
	
	
	
	
	@Test
	public void testsummary(){
		double a = 36600;
		double JS = 5000;
		double b = 50000;
		double c = JS/b;
		System.out.println(c);
		System.out.println("73% Java, 12% C, 10% JS, 6% PHP, Total 50600 lines, 36600 lines Java");
		
	}
	
	@Test
	public void testpreorder(){
		TreeNode root = new TreeNode(50);
		root.left = new TreeNode(30);
		root.right = new TreeNode(70);
		root.left.left = new TreeNode(10);
		root.left.right = new TreeNode(40);
		root.right.left = new TreeNode(60);
		root.right.right = new TreeNode(90);
		preorder(root);
		System.out.println();
		inorder(root);
		System.out.println();
		postorder(root);
		
	}
	
	
	void preorder(TreeNode root){
		if(root == null)
			return;
		System.out.print(root.val + ",");
		if(root.left != null)
			preorder(root.left);
		if(root.right!= null)
			preorder(root.right);
		
	}
	
	void inorder(TreeNode root){
		if(root == null)
			return;
		if(root.left != null)
			inorder(root.left);
		
		System.out.print(root.val + ",");
		
		if(root.right != null)
			inorder(root.right);
	}
	
	void postorder(TreeNode root){
		if(root == null)
			return;
		if(root.left != null)
			postorder(root.left);
		if(root.right != null)
			postorder(root.right);
		System.out.print(root.val + ",");
	}
	
	
}
