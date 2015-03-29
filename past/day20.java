package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class day20 {
	
	
	
	
	
	@Test
	public void testhasPathSum(){
		TreeNode root = new TreeNode(1);
	}
	
	
	
	 public boolean hasPathSum(TreeNode root, int currsum){
		  
	        if(root == null){
	            return false;
	        }
	        
	        if(root.left == null && root.right == null){
	            return currsum == root.val;
	        }
	        
	        return hasPathSum(root.left, currsum - root.val) || hasPathSum(root.right, currsum - root.val);
	       
	  }
	 
	
	
	
	 public int minPathSum(int[][] grid){
	        int m = grid.length;
	        int n = grid[0].length;
	        
	        int[][] table = new int[m][n];
	        
	        table[0][0] = grid[0][0];
	        
	        for(int i = 1; i < m; i++){
	            table[i][0] = table[i-1][0] + grid[i][0];
	        }
	        
	        for(int i = 1; i <n ; i++){
	            table[0][i] = table[0][i-1] + grid[0][i];
	        }
	        
	        for(int i = 1; i < m; i++){
	            for(int j = 1; j < n; j++){
	                table[i][j] = Math.min(table[i-1][j], table[i][j-1]) + grid[i][j];
	            }
	        }
	        
	        return table[m-1][n-1];
	    }
	 
	
	
	
	
	public String intToRoman(int A){
	       List<Integer> res = new ArrayList();
	       
	       int div = 1;
	       
	       for(int i = 3; i >= 0; i--){
	            int digit = (int) ((A/Math.pow(10, i)) % 10);
	            res.add(digit);
	       }
	       
	       StringBuffer buf = new StringBuffer();
	       buf.append(convert(res.get(0), 'M',' ',' '));
	       buf.append(convert(res.get(1), 'C','D','M'));
	       buf.append(convert(res.get(2), 'X','L','C'));
	       buf.append(convert(res.get(3), 'I','V','X'));
	       return buf.toString();
	    }
	    
	    public String convert(int digit, char one, char five, char ten){
	        StringBuffer res = new StringBuffer();
	        switch(digit){
	            case 1:
	            case 2:
	            case 3:
	                for(int i = 0; i < digit; i++){
	                    res.append(one);
	                }
	                break;
	            case 4:
	            case 5:
	                if(digit == 4){
	                    res.append(one);
	                    res.append(five);
	                }else{
	                    res.append(five);
	                }
	                break;
	            case 6:
	            case 7:
	            case 8:
	                res.append(five);
	                for(int i = 5; i < digit; i++){
	                    res.append(one);
	                }
	                break;
	            case 9:
	                res.append(one);
	                res.append(ten);
	                break;
	        }
	        return res.toString();
	        
	    }
	    
	    
	    
	
	
	public void flatten111(TreeNode root){
        if(root == null){
            return;
        }        
        convertToList(root);
    }
    
    public TreeNode convertToList(TreeNode root){
        if(root == null){
            return null;
        }
        
        TreeNode tmpRight = convertToList(root.right);
        root.right = convertToList(root.left);
        root.left = null;
        
        TreeNode trav = root;
        while(trav.right != null){
            trav = trav.right;
        }
        trav.right = tmpRight;
        return root;
    }
    
    
    
	@Test
	public void testflatten(){
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(6);
		
		flatten(root);
		
	}
	
	private TreeNode lastNode = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        if (lastNode != null) {
            lastNode.left = null;
            lastNode.right = root;
        }

        lastNode = root;
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    }
    
    
    
    
    
	
	public int longestValidParentheses(String s){
        
        Stack<Integer> stack = new Stack();
        int maxLen = 0;
        int prestart = -1;
        
        for(int i=0; i < s.length(); i++){
            if( s.charAt(i) == '(' ){
                stack.push(i);
            }else{
                if(stack.empty()){
                    prestart = i;
                }
                else{
                    stack.pop();
                    //most important steps 
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
	
	
	
	
	 /**
     *  since h depend the min(height[left] , height[right]),  we did not need the lower one,
     *  and drop it, and move inside.
     **/
	
	
    public int maxArea(int[] height) {
        int n = height.length;
        int start = 0;
        int last = n - 1;
        
        int maxArea = 0;
        while(start < last){
            int min = Math.min(height[start], height[last]);
            int curArea = min * (last - start);
            maxArea = Math.max(curArea, maxArea);
            if(height[start] < height[last]){
                start++;
            }else{
                last--;
            }
        }
        return maxArea;
        
    }
	
	public int atoi(String str) {
        if(str.length() == 0){
            return 0;
        }
        str = str.trim();
        if(str.length() == 0){
            return 0;            
        }
    
        int sign = 1;
        int index = 0;
        
        if(str.charAt(0) == '+'){
            index++;
        }else if(str.charAt(0) == '-'){
            sign = -1;
            index++;
        }
        
        long num = 0;
        
        for(int i = index; i < str.length(); i++){
            if(str.charAt(i) < '0' || str.charAt(i) > '9'){
                break;
            }
            num = num * 10 + (str.charAt(i) - '0');
            if(num > Integer.MAX_VALUE){
                break;
            }
        }
        
        if(num * sign > Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        
        if(num * sign < Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }
        
        return sign * (int)num;
          
    }
	
	
	
	
	 public String minWindow(String S, String T) {

			int start = 0;
			int minstart = -1;
			int minend = S.length() + 1;
			HashMap<Character, Integer> map = new HashMap();
			HashMap<Character, Integer> cur = new HashMap();

			for (int i = 0; i < T.length(); i++) {
			    cur.put(T.charAt(i),0);
				if (map.containsKey(T.charAt(i))) {
					map.put(T.charAt(i), map.get(T.charAt(i)) + 1);
				} else {
					map.put(T.charAt(i), 1);
				}
			}

			int len = 0;

			for (int i = 0; i < S.length(); i++) {
				char c = S.charAt(i);
				if (map.containsKey(c)) {
					cur.put(c, cur.get(c) + 1);
					if(cur.get(c) <= map.get(c))
					    len++;
					
					if(T.length() == len){
					    while( !map.containsKey(S.charAt(start))
	                    	|| cur.get(S.charAt(start)) > map.get(S.charAt(start))){
	                    	
	                    	if(cur.containsKey(S.charAt(start))){
	                    		cur.put(S.charAt(start), cur.get(S.charAt(start)) -1);
	                    	}
	                    	start ++;
	                    }
	                    
	                    if(i - start < minend - minstart){
	                        minstart = start;
	                        minend = i;
	                    }
	                    
					
					}
					
				}
				
			}

			if (minstart == -1)
				return "";
			return S.substring(minstart, minend + 1);
		}
	 
	 
	 
	 




	
	
	
	
		public int lengthOfLastWord(String s) {
	        s = s.trim();
	        
	        int n = s.length();
	        int count = 0;
	        for(int i = n-1; i >= 0; i--, count++){
	            if(s.charAt(i) == ' '){
	                return count;
	            }
	        }
	        return count;
	   }
	
		public boolean isBalanced(TreeNode root){
	        return maxDepth11(root) != -1;
	    }
	    
	    private int maxDepth11(TreeNode root){
	        if(root == null)
	            return 0;
	            
	        int left = maxDepth(root.left);
	        int right = maxDepth(root.right);
	        
	        if(left == -1 || right == -1 || Math.abs(left - right) > 1){
	            return -1;
	        }
	        
	        return Math.max(left, right) + 1;
	    }
	
	
	
	
	  public List<Integer> postorderTraversal11(TreeNode root){
		    LinkedList<Integer> res = new LinkedList();
		    if(root == null)
		        return res;
		    TreeNode sentinel = new TreeNode(0);
		    sentinel.left = root;
		    
		    TreeNode cur = sentinel;
		    TreeNode pre = null;
		    
		    while(cur != null){
		        
		        if(cur.left != null){
		            
		            pre = cur.left;
		            while(pre.right != null  && pre.right != cur){
	                    pre = pre.right;
		            }
		            
		            if(pre.right == null){
	                    pre.right = cur;
	                    cur = cur.left;
		            }else{
		                reverseadd(cur.left, pre, res);
		                pre.right = null;
	                    cur = cur.right;
		            }
		        }else{
	                res.add(root.val);
	                cur = cur.right;
		        }
		    }
	        return res;
		}
		
		void reverseadd(TreeNode start, TreeNode end, LinkedList<Integer> res){
		    while(start != end){
		        res.push(start.val);
		        start = start.right;
		    }
		    res.push(end.val);
		}
	
	
	
	
	
	
	
	
	@Test
	public void testpostorderTraversal11(){
		int[] inorder = {10, 30, 40, 50, 60, 70, 90};
		int[] preorder = {50, 30,10, 40, 70, 60, 90};
		TreeNode root = preorder(inorder, preorder);
		inorderTraversal(root);
	}
	
	
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList();
		TreeNode cur = root;
		TreeNode pre = null;
		
		while(cur != null){
			
			if(cur.left != null){
				pre = cur.left;
				while(pre.right != null && pre.right != cur){
					pre = pre.right;
				}
				if(pre.right == null){
					res.add(cur.val);
					pre.right = cur;
					cur = cur.left;
				}else{
					pre.right = null;//already add the node and we need to return to the father node.
					cur = cur.right;
				}
			}else{
				//find the left most child, add(it) and go back to father node. 
				res.add(cur.val);
				cur = cur.right;// return to the father node
			}
		}
		return res;
	}

	
	
	
	
//	public ArrayList<Integer> inorderTraversal(TreeNode root) {
//	    ArrayList<Integer> res = new ArrayList<Integer>();
//	    TreeNode cur = root;
//	    TreeNode pre = null;
//	    while(cur != null)
//	    {
//	        if(cur.left != null)
//	        {
//	        	pre = cur.left;
//	            while(pre.right!=null && pre.right!=cur)
//	                pre = pre.right;
//	            if(pre.right==null)
//	            {
//	                pre.right = cur;
//	                cur = cur.left;  //go to the left child
//	            }
//	            else
//	            {
//	                pre.right = null;//return to the father node
//	                res.add(cur.val);
//	                cur = cur.right;
//	            }
//	            
//	        }
//	        else
//	        {
//	        	//find the left most child, add(it) and go back to father node.
//	        	res.add(cur.val);
//	            cur = cur.right;//use pre.right to go back to the father node or it has right child, go to its right child.
//	            
//	        }
//	    }
//	    return res;
//	}
	
	
//	public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList();
//        Stack<TreeNode> stack = new Stack();
//        
//        while( root != null || !stack.empty()){
//            
//            if(root != null){
//                stack.push(root);
//                root = root.left;                
//            }else{
//                //we find left most
//                TreeNode curr = stack.pop();
//                res.add(curr.val);
//                root = curr;
//                root = root.right;//then find right node. 
//            }
//        }
//        
//        return res;
//    }
	
	
	
//	 public List<Integer> postorderTraversal(TreeNode root){
//	        
//	        Stack<TreeNode> stack = new Stack();
//	        List<Integer> res = new ArrayList();
//	        
//	        if(root == null){
//	            return res;
//	        }
//	        
//	        stack.push(root);
//	        
//	        TreeNode prev = null;
//	        TreeNode curr = root;
//	        
//	        while(!stack.empty()){
//	            curr = stack.peek();
//	            if(prev == null || prev.left == curr || prev.right == curr){
//	                if(curr.left != null){
//	                    stack.push(curr.left);
//	                }else if(curr.right != null){
//	                    stack.push(curr.right);
//	                }
//	            }else if(curr.left == prev){
//	                if(curr.right != null){
//	                    stack.push(curr.right);
//	                }
//	            }else{
//	                res.add(stack.pop().val);
//	            }
//	            prev = curr;
//	        }
//	        return res;
//	    }
	
	
	
	@Test
	public void testpostorderTraversal(){
		int[] inorder = {10, 30, 40, 50, 60, 70, 90};
		int[] preorder = {50, 30,10, 40, 70, 60, 90};
		TreeNode root = preorder(inorder, preorder);
		postorderTraversal(root);
		
		
	}
	
	
	
	TreeNode preorder(int[] inorder, int[] preorder){
		return getTree(inorder, 0, preorder, 0, preorder.length);
	}
	
	
	private TreeNode getTree (int[] inorder, int istart, int[] preorder, int pstart, int length){
		if( length == 0){
			return null;
		}
		
		if( length == 1){
			return new TreeNode(preorder[pstart]);
		}
		
		int rootvalue = preorder[pstart];
		int rootindex = -1;
		
		for(int i = istart; i < istart + length; i++){
			if(inorder[i] == rootvalue){
				rootindex = i;
				break;
			}
		}
		
		TreeNode root = new TreeNode(rootvalue);
		
		int lengthleft = rootindex - istart;//end - start
		int lengthright = length - lengthleft - 1;//total - 1 - left
		
		root.left  = getTree(inorder, istart, preorder, pstart+1, lengthleft);//left istart, pstart + 1
		root.right = getTree(inorder, rootindex+1, preorder, pstart + lengthleft + 1, lengthright);
		return root;
		
	}
	
	
	
	
	
	
	
	
	//Iterative
	public ArrayList<Integer> postorderTraversal(TreeNode root) {
	    ArrayList<Integer> result = new ArrayList<Integer>();
	    Stack<TreeNode> stack = new Stack<TreeNode>();
	    TreeNode prev = null; // previously traversed node
	    TreeNode curr = root;

	    if (root == null) {
	        return result;
	    }

	    stack.push(root);
	    while (!stack.empty()) {
	        curr = stack.peek();
	        if (prev == null || prev.left == curr || prev.right == curr) { // traverse down the tree
	            if (curr.left != null) {
	                stack.push(curr.left);
	            } else if (curr.right != null) {
	                stack.push(curr.right);
	            }
	        } else if (curr.left == prev) { // traverse up the tree from the left
	            if (curr.right != null) {
	                stack.push(curr.right);
	            }
	        } else { // traverse up the tree from the right
	            result.add(curr.val);
	            stack.pop();
	        }
	        prev = curr;
	    }

	    return result;
	}
		/**
		 *    prev.left == curr || prev.right == curr  --> go down the tree, push the curr value; 
		 *    prev == curr --> hit bottom, add(value) and pop() it
		 *	  curr = stack.peek() //go back father node, if(curr.left == prev) then if(curr.right != null) stack.push(right)
		 *	  prev = father, curr = right child,  prev.right == curr, if(curr.left != null) or if(curr.right != null) 
		 * 	  
		 */
	
	
	
	
	
	/**
     * The best thing must push at last, this is the main rule of stack
    */
    
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        Stack<TreeNode> stack = new Stack();
        
        stack.push(root);
        
        while(!stack.empty()){
            TreeNode now = stack.pop();
            res.add(now.val);
            
            if(now.right != null){
                stack.push(now.right);
            }
            if(now.left != null){
                stack.push(now.left);
            }
        }
        
        return res;
    }
	
	
	public TreeNode sortedArrayToBST(int[] num){
        if(num == null)
            return null;

        return getTree(num, 0, num.length - 1);
        
    }
    
    private TreeNode getTree(int[] num, int start, int length){
        if(length == 0)
            return null;
        if(length == 1)
            return new TreeNode(num[start]);
        
        int midindex = start + length/2;
        
        TreeNode midnode = new TreeNode(num[midindex]);
        int lengthleft = midindex - start;
        int lengthright = length - lengthleft - 1;
        
        midnode.left = getTree(num, start, lengthleft);
        midnode.right = getTree(num, midindex+1, lengthright);
                
        return midnode;
    }
    
	
	
	 public List<List<Integer>> levelOrderBottom(TreeNode root) {
	        LinkedList<List<Integer>> res = new LinkedList();
	        
	        if(root == null)
	            return res;
	        
	        List<Integer> sol = new ArrayList();
	        
	        Deque<TreeNode> queue = new ArrayDeque();
	        queue.offer(root);
	        
	        int currLevel = 1;
	        int nextLevel = 0;
	        
	        while(queue.peek() != null){
	            TreeNode now = queue.poll();
	            currLevel--;
	            sol.add(now.val);
	            
	            if(now.left != null){
	                queue.offer(now.left);
	                nextLevel ++;
	            }
	            
	            if(now.right != null){
	                queue.offer(now.right);
	                nextLevel ++;
	            }
	            
	            if(currLevel == 0){
	                currLevel = nextLevel;
	                nextLevel = 0;
	                
	                List<Integer> tmp = new ArrayList(sol);
	                res.push(tmp);
	                sol.clear();
	            }
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
	       
	       
	        List<Integer> curr = new ArrayList();

	       while(queue.peek() != null){
	           TreeNode subroot = queue.poll();
	           currLevel--;
	           curr.add(subroot.val);
	           
	           if(subroot.left != null){
	               queue.offer(subroot.left);
	               nextLevel ++;
	           }
	           if(subroot.right != null){
	               queue.offer(subroot.right);
	               nextLevel ++;
	           }
	           
	           
	           
	           if(currLevel == 0){
	                currLevel = nextLevel;
	                nextLevel = 0;
	                List<Integer> tmp = new ArrayList(curr);
	                res.add(tmp);
	                curr.clear();
	           }
	           
	       }
	       
	       
	              
	       return res;
	       
	   }
	
	
	
	
	public boolean isValidBST(TreeNode root) {
        return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        
        
   }
   
   public boolean helper(TreeNode root, int min, int max){
       if(root == null)
            return true;
       
       if(root.val <= min || root.val >= max)
            return false;
       
       if(root.left != null && root.right != null){
            return helper(root.left,min, root.val) && helper(root.right, root.val, max);           
       }else if(root.left != null){
           return helper(root.left, min, root.val);
       }else if(root.right != null){
           return helper(root.right, root.val, max);
       }else{
           return true;
       }
       
   }
	
	
	
	
	
	 public int maxDepth(TreeNode root) {
	        if(root == null)
	            return 0;
	        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
	    }
	
	public int minDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        
        
        if(root.left != null && root.right != null){
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }else if(root.left != null){
            return minDepth(root.left) + 1;
        }else{
            return minDepth(root.right) + 1;
        }
	}
	
	
	/**
   	 * divide and conquer
   	 * we have count[0] = 1; count[1] = 1; count[2] = count[0] * count[1] + count[1] * count[0]
   	 * count[3] = count[0]*count[2]+count[1] *count[1] +count[2] * count[0] 
   	 * 
   	 * 
   	 * */
   	
   	
   	public int numTrees(int n) {
   	    if(n <= 1)
   	        return 1;
   	        
   	    int[] dp = new int[n+1];
   	    dp[0] = 1;
   	    dp[1] = 1;
   	    
   	    for(int k = 2; k < n+1; k++){
   	        int sum = 0;
   	        for(int i = 0; i < k; i++){
   	            sum+= dp[i] * dp[k-1-i];
   	        }
   	        dp[k] = sum;
   	    }
   	    return dp[n];
	}
	
	
	
	
}
