package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class day16 {
	
	
	 public List<Integer> findSubstring1(String S, String[] L){
	        int n = L.length;
	        int w = L[0].length();
	        
	        List<Integer> res = new ArrayList();
	        
	        HashMap<String, Integer> map = new HashMap();
	        for(int i = 0; i < n; i ++){
	            if(map.containsKey(L[i])){
	                map.put(L[i], map.get(L[i]) + 1);
	            }else{
	                map.put(L[i],1);
	            }
	        }
	        
	        int idx = 0;
	        
	        while(idx + n * w <= S.length()){
	            if(map.containsKey(S.substring(idx, idx + w))){
	                boolean flag = true;
	                HashMap<String, Integer> curr =new HashMap();
	                for(int i = idx; i <= idx + n * (w-1); i += w){
	                    String tmp = S.substring(i, i+w);
	                    if(map.containsKey(tmp) == true){
	                       if(curr.get(tmp) == null){
	                           curr.put(tmp, 1);
	                       }else{
	                           int now = curr.get(tmp);
	                           if(now +1 > map.get(tmp)){
	                               flag = false;
	                               break;
	                           }else{
	                               curr.put(tmp, now+1);
	                           }
	                       }
	                    }else{
	                       flag =false;
	                       break;
	                    }
	                }
	                
	                if(flag){
	                    res.add(idx);
	                }
	            }
	            idx++;
	        }
	        return res;
	   }
	 
	 
	 
	 
	 
	
	
	
	public List<List<Integer>> subsetsWithDup(int[] A) {
		List<Integer> sol = new ArrayList();
		List<List<Integer>> res = new ArrayList();
		res.add(new ArrayList());

		boolean[] visited = new boolean[A.length];
		builduniform(0, A, visited, sol, res);
		return res;
	}

	public void builduniform(int start, int[] A, boolean[] visited,
			List<Integer> sol, List<List<Integer>> res) {

		for (int i = 0; i < A.length; i++) {
			if (i > 0 && A[i] == A[i - 1] && visited[i - 1] == false)
				continue;
			sol.add(A[i]);
			visited[i] = true;
			builduniform(i + 1, A, visited, sol, res);
			visited[i] = false;
			List<Integer> tmp = new ArrayList();
			res.add(tmp);
			sol.remove(sol.size()-1);

		}
	}
	
	
	
	public List<List<Integer>> subsets(int[] s){
        List<List<Integer>> res = new ArrayList();
        List<Integer> sol = new ArrayList();
        res.add(new ArrayList());
        Arrays.sort(s);
        buildsubsets(0, s, sol, res);
        return res;
    }
    
    
    //ascending order, start, ,
    
    public void buildsubsets(  int start, int[] s, List<Integer> sol,List<List<Integer>> res){
        
        for(int i = start; i < s.length; i ++){
           sol.add(s[i]);             
           buildsubsets(i + 1,  s, sol, res);
           List<Integer> tmp = new ArrayList(sol);
           res.add(tmp);
           
           sol.remove(sol.size() - 1);
        }
    }
     
     
	
	
	
	@Test
	public void testisScramble(){
		boolean now = isScramble("gr", "rg");
		System.out.println(now);
		
	}
	
	
	
	 public boolean isScramble(String s1, String s2){
         if(s1.length() != s2.length())
            return false;
         return dac(s1,s2);
     }
	 
	 public boolean dac(String s1, String s2){
		 if(s1.length() != s2.length())
			 return false;
		 
		 //check charset
		 if(check(s1, s2) == false){
			 return false;
		 }
		 
		 //I did not consider the i = 0;
		 if(s1.length() == 1){
			 return s1.charAt(0) == s2.charAt(0);
		 }
		 
		 
		 int n = s1.length();
		 
		 for(int i = 1; i < n; i++){
			 String s11 = s1.substring(0, i);//i - 0  = i 
			 String s12 = s1.substring(i); // n- i
			 
			 String s21 = s2.substring(0, i);
			 String s22 = s2.substring(i);
			 
			 boolean result = dac(s11, s21) && dac(s12, s22);
			 
			 if(result == false){
				 
				 String s31 = s2.substring(0, n-i); //n-i;
				 String s32 = s2.substring(n-i);//n-(n-i) = i
				 
				 result = dac(s11, s32) && dac(s12, s31);
			 }
			 
			 if(result){
				 return true;
			 }
		 
		 }
		 return false;
		 
	 }
	 
	 
	 
	 public boolean check(String s1, String s2){
		 int[] b1 = new int[26];
		 int[] b2 = new int[26];
		 
		 for(int i = 0; i < s1.length(); i++){
			 int index = s1.charAt(i) - 'a';
			 b1[index] ++;
		 }
		 
		 for(int i = 0; i < s2.length(); i++){
			 int index = s2.charAt(i) - 'a';
			 b2[index] ++;
		 }
		 
		 for(int i = 0; i < 26; i++){
			 if(b1[i] != b2[i])
				 return false;
		 }
		 
		 return true;
		 
	 }
	 
	 
	 
	 
	 
	
	public boolean isSameTree(TreeNode p, TreeNode q){
        if(p == null || q == null)
            return p == null && q == null;
        if(p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
	
	
	
	
	//123 321
	
	@Test
	public void testreverse(){
		int a = reverse(123);
		System.out.println(a);
	}
	
	public int reverse(int x) {
        int postx = Math.abs(x);
        
        List<Integer> res = new ArrayList();
        
        int div = 1;
        while( postx/ div >= 10){
        	int num = postx/div %10;
        	res.add(num);   // 123    3 2 1
        	div *= 10;
        }
        res.add(postx/div);
        //System.out.println(res);
        
        int sum = 0;
        for(int i =0; i < res.size(); i ++){
        	sum += res.get(i) * div;
        	div /= 10;
        }
        return sum;
    }

	
	
	
	public List<String> restoreIpAddresses(String s) {
		List<String> res = new ArrayList();
		StringBuffer sol = new StringBuffer();
		
		IPbuilder(0, s, 4, sol, res);
		return res;
	}
	
	
	/*
	 * count < 4, ascending order, buf is only in one scope but sol is in all scope,
	 * when run after the 0, must stop, when num >= 256 must stop this recursive. return back to the before recursive
	 */
	
	void IPbuilder(int start, String s, int count, StringBuffer sol, List<String> res){
		if(count == 0 && start >= s.length()){
			res.add(sol.toString());
			count++;
			return;
		}
		if(count == 0){
			return;
		}
		
		StringBuffer buf = new StringBuffer();
		for(int i = start; i < s.length(); i++){
			buf.append(s.charAt(i));
			String tmp = buf.toString();
			if(tmp.charAt(0) == '0' && tmp.length() > 1){
				return;
			}
			int num  = Integer.parseInt(tmp);
			if(num < 256){
				int oldbuf = buf.length();
				if(i != s.length() - 1){
					buf.append('.');
				}
				int old = sol.length();
				sol.append(buf);
				IPbuilder(i + 1, s, count-1, sol, res);
				
				buf.delete(oldbuf, buf.length());
				sol.delete(old, sol.length());
				
			}else{
				return ;//waste
			}
			
		}
	}
	
	
	
	
	
	
//   public List<String> restoreIpAddresses(String s) {
//        
//        List<String> res = new ArrayList();
//        StringBuffer sol = new StringBuffer();
//        dfs(4, 0, s, sol, res);
//        return res;
//	}
	
//	public void dfs(int count, int start, String s, StringBuffer sol, List<String> res){
//        if(count == 0 && start >= s.length()){
//            res.add(sol.toString());
//            return;
//        }
//        
//        if(count == 0){
//            return;
//        }
//        
//        //if there is no start, every time, it will start from zero, which is bad. 
//        StringBuffer buf = new StringBuffer();
//        for(int i = start; i < s.length(); i++){
//            buf.append(s.charAt(i));
//            String tmp = buf.toString();
//            int num = Integer.parseInt(tmp);
//            if( num < 256 ){
//                int old = sol.length();
//                if(i != s.length() -1 ){
//                    buf.append('.');
//                }
//                sol.append(buf);
//                dfs(count-1, i+1, s,sol,res);
//                buf = new StringBuffer();
//                sol.delete(old,sol.length());
//            }else{
//                return;
//            }
//        }        
//	    
//	}
	
	
	
	
	
	 public void recoverTree(TreeNode root) {
			
			List<TreeNode> res = new ArrayList();
			List<Integer> vals = new ArrayList();
		    	 
		    	 
		    //go to left one first
		    InorderTraveral( root, res,  vals);
		    
		    Collections.sort(vals);
		    
		    
		    for(int i = 0; i < res.size(); i++){
		        res.get(i).val = vals.get(i);
		    }
		    
		    
		
	    }	
	    
	    public void InorderTraveral(TreeNode root, List<TreeNode> res, List<Integer> vals){
	        if(root == null)
	            return;
	        
	        InorderTraveral(root.left, res, vals);
	        vals.add(root.val);
	        res.add(root);
	        InorderTraveral(root.right, res, vals);
	        
	    } 
	    
	    
	    
	    
	
	
	
	//recursive from right and then left. otherwise, it cannot connect to the right, this bug is very implicit, sign...
	public void connect11(TreeLinkNode root){
		   if(root == null)
		        return;
		   if(root.left != null || root.right != null){
		       TreeLinkNode trav = root.next;
		       
		       if(root.left != null && root.right != null){
		           root.left.next = root.right;
		           root.right.next = connect22(trav);
                 
                
                 connect(root.right);
                 connect(root.left);
		       }else if(root.left != null){
		           root.left.next = connect22(trav);
		           connect(root.left);
		       }else{
		           root.right.next = connect22(trav);
		           connect(root.right);
		       }
		   }  
	  }
	  
	  public TreeLinkNode   connect22(TreeLinkNode trav){
	      if(trav == null)
	            return null;
	       if(trav.left != null){
	           return trav.left;
	       }else if(trav.right != null){
	           return trav.right;
	       }else{
	           return connect2(trav.next);
	       }
	  }
	  
	  
	  
	  
	
	
	
	
		public void connect23(TreeLinkNode root){
	        if(root == null)
	            return ;
	            
	        if(root.left != null){
	            root.left.next = root.right;
	            if(root.next != null){
	                root.right.next = root.next.left;                
	            }
	            
	            connect(root.left);
	            connect(root.right);
	            
	            
	            
	        }
	    
	    }
		
	 	public boolean hasPathSum(TreeNode root, int sum){
	        return hasPath(root, sum);
	    }
	    
	    
	    boolean hasPath(TreeNode root, int sum){
	        if(root == null){
	            return false;
	        }
	        int currsum = sum - root.val;
	        if(currsum == 0 && root.left == null && root.right == null){
	            return true;
	        }
	        
	        return hasPath(root.left, currsum) ||
	        hasPath(root.right, currsum);
	    
	        
	        
	      
	    }
	    
	
	
	
	   public List<List<Integer>> pathSum1(TreeNode root, int sum) {
	        List<List<Integer>> res  = new ArrayList();
	        if(root == null)
	            return res;
	        
	        List<Integer> sol = new ArrayList();
	        PS1(root, sum, sol, res);
	        return res;
	    
	    }
	    
	    
	    public void PS1(TreeNode root, int sum, List<Integer> sol, List<List<Integer>> res){
	        
	        if(root == null)
	            return;
	            
	        int currsum = sum - root.val;
	        sol.add(root.val);
	        if(currsum == 0 && root.left == null && root.right == null){
	            List<Integer> tmp = new ArrayList(sol);
	            res.add(tmp);
	        }
	        
	        PS(root.left, currsum, sol, res);
	        PS(root.right, currsum, sol, res);
	        
	        sol.remove(sol.size()-1);
	    }
	    
	
	
	
	 public int minPathSum1(int[][] grid){
	        int m = grid.length;
	        if(m == 0){
	            return 0;
	        }
	        int n = grid[0].length;
	        
	        int[][] table= new int[m][n];
	        
	        table[0][0] = grid[0][0];
	        
	        for(int i = 1; i < m;i++){
	            table[i][0] = table[i-1][0] + grid[i][0];                         
	        }
	        

	        for(int i = 1; i < n; i++){
	            table[0][i] = table[0][i-1] + grid[0][i];    
	        }


	        
	        for(int i = 1; i < m; i++){
	            for(int j=1; j < n; j++){
	                table[i][j] = Math.min(table[i-1][j], table[i][j-1]) + grid[i][j];
	            }
	        }
	        
	        return table[m-1][n-1];
	        
	        
	    }
	 
	 
	 
	 
	
	
	
	
	@Test
	public void testlargestRectangleArea(){
		
		int area =largestRectangleArea(new int[]{4,2});
		System.out.println(area);
	}
	
	
	public int largestRectangleArea(int[] height) {
		int n = height.length;

		LinkedList<Integer> stack = new LinkedList();

		int max = 0;
		for (int i = 0; i < n; i++) {

			while (stack.peek() != null && height[i] < height[stack.peek()]) {
				int index = stack.pop();
				int curArea = stack.peek() != null ? height[index]
						* (i - (stack.peek() + 1)) : height[index] * (i - 0);
				max = Math.max(curArea, max);
			}

			stack.push(i);
		}

		while (stack.peek() != null) {
			int index = stack.pop();
			int curArea = stack.peek() != null ? height[index]
					* (n - (stack.peek() + 1)) : height[index] * n;
			max = Math.max(curArea, max);
		}

		return max;
	}
	
	@Test
	public void testinttoRoman1(){
		
		String tmp = intToRoman1(11);
		System.out.println(tmp);
	}
	

	public String intToRoman1(int A){
        StringBuffer res = new StringBuffer();
        
        List<Integer>  digits = new ArrayList();
        int num = 0;
        for(int i = 3; i >=0; i--){
            digits.add((int) (A / Math.pow(10,i)   % 10));
        }        
        System.out.println(digits);
        res.append(convert(digits.get(0), 'M',' ',' '));
        res.append(convert(digits.get(1), 'C','D','M'));
        
        res.append(convert(digits.get(2), 'X','L','C'));
        res.append(convert(digits.get(3), 'I','V','X'));
        return res.toString();
        
    }
    
    String convert1(int digit, char one, char five, char ten){
        StringBuffer buf = new StringBuffer();
        switch(digit){
            case 1:
            case 2:
            case 3:
                for(int i = 0; i < digit; i++){
                    buf.append(one);
                }
                break;
            case 4:
            case 5:
                if(digit == 4){
                    buf.append(one);
                    buf.append(five);
                }else{
                    buf.append(five);
                }
                break;
            case 6:
            case 7:
            case 8:
                buf.append(five);
                for(int i =6; i <= digit; i++){
                    buf.append(one);
                }
                break;
            case 9:
                buf.append(one);
                buf.append(ten);
                break;
        }
        
        return buf.toString();
    }
	
	
	
	 public void flatten111(TreeNode root){
	        if(root == null){
	            return;
	        }        
	        
	        convertToList1(root);
	    }
	    
	    public TreeNode convertToList1(TreeNode root){
	        if(root == null)
	            return root;
	        
	        TreeNode tmpRight = convertToList1(root.right);
	        
	        root.right =convertToList1(root.left);
	        
	        root.left = null;
	        
	        TreeNode trav = root;
	        
	        while(trav.right != null){
	            trav = trav.right;
	        }
	        
	        trav.right = tmpRight;
	            
	        return root;        
	    }
	    
	
	
	
	
	@Test
	public void testfind(){
		String s = new String("abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababab");
		String arr[] = new String[]{"ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba"};
		
		findSubstring(s,arr);
//		for(int i = 0; i < 9999; i += 2){
//			if(s.charAt(i) == 'a' && s.charAt(i+1) == 'b' ){
//				
//			}else{
//				System.out.println("wrong");
//			}
//		}
//		
//		
//		
//		System.out.println(arr.length);
//		System.out.println(s.length());
//		
		
		
	}
	
	
	@Test
	public void findSubstring(){
		String[] L1 = new String[]{"bar","foo","foo"};
		String S1 =new String("barfoofooxxx");
		
		String[] L = new String[]{"a"};
		String S =new String("a");
		
		
		List<Integer> res = findSubstring(S, L);
		System.out.println(res);
	}
	
	
	
	
	public List<Integer> findSubstring(String S, String[] L){
        int start = 0;
        int end = 0;
        
        List<Integer> res = new ArrayList();
        if(L.length == 0)
            return res;
         
        HashMap<String, Integer> dict = new HashMap();
        for(int i = 0; i < L.length; i++){
            if(dict.containsKey(L[i])){
                dict.put(L[i], dict.get(L[i]) + 1);
            }else{
                dict.put(L[i], 1);
            }
        }        
        
        int idx = 0;
        int w = L[0].length();
        int n = L.length;
        int len = n * w;
        
        while(idx + len <= S.length()){
            String tmp  = S.substring(idx, idx + w);
            boolean flag = true;
            
            if(dict.containsKey(tmp)){
                HashMap<String, Integer> map = new HashMap();
                for(int i = idx; i < idx + len; i +=w ){
                     String str = S.substring(i, i + w);
                     if(dict.containsKey(str)){
                         if(map.get(str) == null){
                             map.put(str, 1);
                         }else{
                             int now =map.get(str);
                             if(now + 1 > dict.get(str)){
                                 flag = false;
                                 break;
                             }else{
                                 map.put(str, now + 1);//hand is shaking, I type it wrong
                             }
                         }
                     }else{
                         flag = false;
                         break;
                     }
                }
                
                if(flag){
                    res.add(idx);
                }
            }
            idx++;
        }
        
        return res;
    }
	
	
	
	
	
	
	
	 public int minPathSum(int[][] grid){
	        int m = grid.length;
	        if( m == 0)
	            return 0;
	        
	        int n = grid[0].length;
	        int[][] table = new int[m][n];
	        
	        table[0][0] = grid[0][0];
	            
	        for(int i = 1; i < m; i++){
	            table[i][0] = table[i-1][0] + grid[i][0]; 
	        }
	        
	        for(int j = 1; j < n; j++){
	            table[0][j] = table[0][j-1] + grid[0][j];
	        }
	        
	        for(int i = 1; i < m; i++){
	            for(int j = 1; j < n; j++){
	                int min = Math.min(table[i-1][j], table[i][j-1]);
	                table[i][j] = min + grid[i][j];
	            }
	        }
	        
	        return table[m-1][n-1];       
	    }
	 
	 
	 
	
	
	 public void flatten1(TreeNode root) {
	        if( root == null ){
	            return;
	        } 
	        convertToList(root);
	    }
	    
	      
	    public TreeNode convertToList(TreeNode root){
	        if(root == null){
	            return root;
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
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		flatten(root);
		
	}
	
	public void flatten(TreeNode root) {
        if (root==null){
            return;
        }
        
        covertToList(root);
    }
	
	
    private TreeNode covertToList(TreeNode root){
        
        if (root==null){
            return root;
        }
        // convert right tree to list
        TreeNode tempRight=covertToList(root.right);
        
        // convert left tree to list and connect it to right of root
        root.right=covertToList(root.left);
        
        // make left side to null;
        root.left=null;
        
        TreeNode tempRoot=root;
        // find the right most node which used to connect list coverted by original right tree
        while (tempRoot.right!=null){
            tempRoot=tempRoot.right;
        }
        
        tempRoot.right=tempRight;
        
        return root;
    }
    
    
    
    
    
    
    
    public TreeNode convert(TreeNode root){
    	if(root == null)
    		return root;
    	
    	TreeNode tmpRight = convert(root.right);
    	
    	root.right = convert(root.left);
    	
    	root.left = null;
    	 
    	TreeNode trav = root.right;
    	
    	while(trav.right != null){
    		trav = trav.right;
    	}

    	trav.right = tmpRight;
    	
    	return root;
    }
    
    
    
	
	 public void connect(TreeLinkNode root){
		 if(root == null){
			 return;
		 }
		 
		 if(root.left != null || root.right != null){
			 TreeLinkNode trav = root.next;
			 if(root.left != null && root.right != null){
				 root.left.next = root.right;
				 root.right.next = connect2(trav);
				 connect(root.left);
				 connect(root.right);
			 }else if(root.left != null){
				 root.left.next = connect2(trav);
				 connect(root.left);
			 }else{
				 root.right.next = connect2(trav);
				 connect(root.right);
			 }
		 }
	 }
	 
	 public TreeLinkNode connect2(TreeLinkNode trav){
		 if(trav == null)
			 	return null;
		 
		 if(trav.left != null || trav.right != null){
		 	 if(trav.left != null)
		 		 return trav.left;
		 	 else
		 		 return trav.right;
			 
		 }else{
			 return connect2(trav.next);
		 }

	 }
	 
	 
	 
	 
	
	
	
	
//    public  void connect(TreeLinkNode root) {
//    	if(root == null){
//    		return;
//    	}
//    	
//    	if(root.left != null ){
//    		if(root.right != null){
//    			root.left.next = root.right;
//    			if(root.next != null){
//    				TreeLinkNode trav = root;
//    				while(trav.next != null){
//    					if(trav.next.left == null && trav.next.right == null){
//    						trav = trav.next;
//    					}else if( trav.next.left != null){
//    						root.right.next = trav.next.left;
//    						break;
//    					}else if(trav.next.right != null){
//    						root.right.next = trav.next.right;
//    						break;
//    					}
//    				}
//    			}
//    		}else{
//    			if(root.next != null){
//					TreeLinkNode trav = root;
//
//					while(trav.next != null){
//    					if(trav.next.left == null && trav.next.right ==null){
//    						trav = trav.next;
//    					}else if(trav.next.left != null){
//    						root.left.next = trav.next.left;
//    						break;
//    					}else if(trav.next.right != null){
//    						root.left.next = trav.next.right;
//    						break;
//    					}
//    				}
//    			}
//    		}
//    	}else{
//    		//
//    		if(root.right != null){
//    			if(root.next != null){
//    				TreeLinkNode trav = root;
//    				while(trav.next != null){
//    					if(trav.next.left == null && trav.next.right == null)
//    						trav = trav.next;
//    					else if(trav.next.left != null){
//    						root.right.next = trav.next.left;
//    						break;
//    					}else if(trav.next.right != null){
//    						root.right.next = trav.next.right;
//    						break;
//    					}
//    				}
//    			}
//    		}
//    	}
//    	
//    	
//    	connect(root.left);
//    	connect(root.right);
//    	
//    }

    
    
    
	
	
    public void connect1(TreeLinkNode root){
    	if(root == null)
    		return;
    	
    	root.left.next  = root.right;
    	if(root.next != null){
    		root.right.next = root.next.left;
    	}
    	
    	connect(root.left);
    	connect(root.right);
    }
	
	
	
	
	@Test
	public void testpathsum(){
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.left = new TreeNode(5);
		root.right.right.right =new TreeNode(1);
		
		List<List<Integer>> res = pathSum(root, 22);
		System.out.println(res);
		
	}
	
	
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
    	List<List<Integer>> res = new ArrayList();
    	if(root == null)
    		return res;
    	List<Integer> sol = new ArrayList();
    	dfs(root, sum, sol, res);    
    	
    	return res;
    }
    
    
    
    
    public void PS(TreeNode root, int sum, List<Integer> current, List<List<Integer>> result){
        if(root == null)    
            return;
            
        int current_sum = sum - root.val;
        current.add(root.val);

        //exit
        if(current_sum == 0 && root.left == null && root.right == null){
        	List<Integer> temp  = new ArrayList<Integer> (current);
            result.add(temp);
        }
        
        //not leave node
        PS(root.left,current_sum, current,result);
        PS(root.right, current_sum,current, result);
        
        current.remove(current.size() - 1);//should remove the last value, current.size()-1 last index
    }
    
    public void dfs(TreeNode start,  int sum, List<Integer> sol, List<List<Integer>> res){
    	if(start == null){  //not leave node, bye
    		return;
    	}
    	
    	int currsum = sum - start.val;
    	sol.add(start.val);
    	
    	if(start.left == null && start.right == null && currsum == 0){
    		List<Integer> tmp = new ArrayList(sol);
    		res.add(tmp);
    		//can not add return here, otherwise, it will die.
    	}
    	
    	dfs(start.left, currsum, sol, res);
    	dfs(start.right, currsum, sol, res);
    	
    	sol.remove(sol.size() - 1);
    }
    
    
    
    
    
    
//    void dfs(TreeNode start, int sum, int curr_sum, List<Integer> sol, List<List<Integer>> res){
//    	if(start == null){
//    		return;
//    	}
//    
//    	
//    	if(start.left == null && start.right == null){
//    		if(sum == 0){
//    			List<Integer> tmp = new ArrayList(sol);
//    			res.add(tmp);
//    		}
//    	}
//    	
//    	dfs(start.left, sum, sol, res);
//    	sum = oldsum;
//    	
//    	
//    	dfs(start.right,sum, sol, res);
//    	sum = oldsum;
//
//		sol.remove(sol.size() -1);
//    	sum += start.val;
//    }
    
    
	
	
	
	
	public double pow1(double x, int n) {
		 
        // n is even , then x^n=x^n/2*x^n/2
 
        //  n>0 and n is odd, x^n=x^n/2 * x^n/2 *x
 
        // n<0 and n is odd, x^n=x^n/2 *x^n/2 *x^-1
 
        if (n==0) {
 
            return 1.0;
 
        }
 
      double half=pow(x, n/2);
 
      
      if(n % 2 == 0){
          return half * half;
      }
      if(n > 0){
         return half * half * x; 
      }else{
          return half* half * (1/x);
      }
      
    }
	
	
	
	@Test
	public void testpow(){
		double res = pow(2, -4);	
		System.out.println(res);
	}
	
	
	
	
	public double pow(double x, int n) {
	        if(n == 0){
	            return 1;
	        }
	        if(n == 1){
	            return x;
	        }
	        
	        if(n < 0){
	        	return 1.0/pow(x, -n);
	        }
	        
	        
	        int k1 = n/2;
	        int k2 = n - k1;
	        
	        return pow(x, k1) * pow(x, k2);
	}
	 
	 
	
	
	@Test
	public void testinttoroman(){
		
		String res = intToRoman(1992);
		System.out.println(res);
		
		
	}

	public String intToRoman(int A) {
	       // M
	       // C D
	       // X L C
	       // I V X
	       
	       List<Integer> list = new ArrayList();
	       
	       for(int i = 3; i >= 0; i--){
	           int digit = (int) (A / Math.pow(10,i));
	           digit %= 10;
	           list.add(digit);
	       }
	        
	       System.out.println(list);
	       
	       
	        StringBuffer res = new StringBuffer();
	           res.append(convert(list.get(0), 'M',' ',' '));//1000
	           res.append(convert(list.get(1), 'C','D','M'));//100
	           res.append(convert(list.get(2), 'X','L','C'));//10
	           res.append(convert(list.get(3), 'I','V','X'));//10
	          return res.toString();
	    }
	    
	    public String convert(int digit, char one, char five, char ten){
	        
	        StringBuffer buf = new StringBuffer();
	        switch(digit){
	            case 1:
	            case 2:
	            case 3:
	                while(digit > 0){
	                buf.append(one);
	                digit--;
	                }
	                break;
	            case 4:
	            case 5:
	                if(digit == 4){
	                    buf.append(one);
	                    buf.append(five);
	                }else{
	                    buf.append(five);
	                }
	                break;
	            case 6:
	            case 7:
	            case 8:
	                buf.append(five);
	                for(int i = digit; i >= 6; i --){
	                    buf.append(one);
	                }
	                break;
	                
	            case 9:
	                buf.append(one);
	                buf.append(ten);
	                break;
	            default:
	            	break;
	            
	        }
	        return buf.toString();
	    }
	
	
}
