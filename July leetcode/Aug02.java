package leetcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;


public class Aug02 {
	
	
	
	@Test
	public void testaddBinary(){
		String a = "11";
		String b = "1";
		System.out.println(addBinary(a,b));
	}
	
	 public String addBinary(String a, String b){
	        int m = a.length();
	        int n = b.length();
	        
	        String result = new String();
	        int carry = 0;
	        while(m > 0 || n > 0){
	            if(m > 0 && n > 0){
	                int value = a.charAt(m - 1) -'0'  + b.charAt(n-1) -'0' + carry;
	                carry = value / 2;
	                value = value % 2;
	                result = value + result;
	                m--;n--;
	            }else if(m > 0){
	                int value = a.charAt(m-1)-'0' + carry;
	                carry = value / 2;
	                value = value % 2;
	                result = value + result;
	                m--;
	            }else{
	                int value = b.charAt(n-1) - '0' + carry;
	                carry = value/2;
	                value = value%2;
	                result = value + result;
	                n--;
	            }
	        }
	        if(carry > 0){
	            result = carry + result;
	        }
	        return result;
	    }
	 
	
	@Test
	public void testfindKth(){
		int[] A = new int[]{1,3,5};
		int[] B = new int[]{2,4,6};
		int b = findKth(A, A.length, B, B.length, 6);
		int c = findKth(A, A.length, B, B.length, 1);

		System.out.println(b);
		System.out.println(c);
	}
	
	 
	int findKth(int[] A, int m, int[] B, int n , int k){
		int curA = A.length - m;
		int curB = B.length - n;
		if(k == 1)
			return A[curA] > B[curB] ? B[curB] : A[curA];
		if(m > n){
			return findKth(B, n, A, m, k);
		}
		if(m == 0){
			return B[curB + k - 1];
		}
		
		int len1 = Math.min(k/2,m);
		int len2 = k - len1;
		
		if(A[curA + len1-1] < B[curB+len2-1]){
			return findKth(A, m - len1, B, n, k- len1);
		}else{
			return findKth(A, m, B, n- len2, k - len2);
		}
		
	}
	
	
	
	
	class resultType{
        int val1;
        int val2; 
    }
    //8:29-8:39
    public int[] twoSum(int[] A, int target) {
        List<Integer> dict = new ArrayList();
        for(int i = 0; i < A.length; i++){
            dict.add(A[i]);
        }
        resultType rs = new resultType();
    
        int start = 0;
        int last = A.length;
        Arrays.sort(A);
        while(start < last){
            int sum = A[start] + A[last];
            if(sum == target){
                rs.val1 = A[start];
                rs.val2 = A[last];
                break;
            }else if(sum > target){
                start++;
            }else{
                last--;
            }
        }
        int index1 = dict.indexOf(rs.val1);
        int index2 = dict.lastIndexOf(rs.val2);
        
        return new int[]{index1+1, index2 + 1};
    }
	
	 public ArrayList<String> generateParenthesis(int n) {
	        ArrayList<String> res = new ArrayList();
	        StringBuffer sol = new StringBuffer();
	        dfs(0,0,n, sol, res);
	        return res;        
	    }
	    
	    /**
	     * rule: () ()() )()( ()()(()))(()) )()( 
	     * left == right, right parenthese numbers must less than left parentheses.
	     * 
	     **/
	    public void dfs(int left, int right, int n, StringBuffer sol, ArrayList<String> res){
	        if(left == n && right == n){
	            res.add(sol.toString());
	        }
	        if(left < n){
	            sol.append('(');
	            dfs(left+1, right, n, sol, res);
	            sol.deleteCharAt(sol.length() - 1);
	        }
	        if(right < left){
	            sol.append(')');
	            dfs(left, right+1, n, sol, res);
	            sol.deleteCharAt(sol.length() -1);
	        }
	    }
	    
	    
	
	 public int sqrt(int x) {
	        long start = 0;
	        long last = Integer.MAX_VALUE;
	        
	        while(true){
	            long mid = (start + last)/2;
	            
	            
	            if(mid * mid == x){
	                return (int) mid;
	            }
	            if(start + 1 == last){
	                return (int) start;
	            }
	            
	            if(mid * mid < x){
	                start = mid;
	            }else{
	                last = mid;
	            }
	        }
	    } 
	
	
	public int threeSumClosest(int[] A, int target){
        int n = A.length;
        int result = 0;
        int close = Integer.MAX_VALUE;
        
        for(int i =0; i < n; i++){
            int start = i + 1; 
            int last = n - 1;
            while(start < last){
               int sum = A[i] + A[start] + A[last];
                
                if(sum == target){
                    return sum;
                }else if(sum > target){
                    if(sum - target < close){
                        close = sum - target;
                        result = sum;
                    }
                    last--;
                }else if(sum < target){
                    if(target-sum < close){
                        close = target - sum;
                        result = sum;
                    }
                }
            }
        }
        return result;
    }
	
	
	 public boolean isMatch(String s, String p) {
	        //Java note: s.substring(n) will be "" if n == s.length(), but if n > s.length(), index oob error
	        if(s.length() == 0 || p.length() == 0){
	            if(p.length() == 0)
	                return s.length() == 0;
	            else{
	                for(int i = p.length()-1; i >= 0; i--){
	                    if(p.charAt(i) != '*')
	                        return false;
	                }
	                return true;
	            }
	        }
	        
	        //now s and p is greater than 0;
	        if(p.charAt(0) == '*'){
	            boolean result = false;
	            for(int i =0; i <= s.length(); i++){
	                result = isMatch(s.substring(i), p.substring(1));
	                if(result){
	                    return true;
	                }
	            }
	            return false;
	        }else{
	            if(p.charAt(0) == '.' || p.charAt(0) == s.charAt(0))
	                return isMatch(p.substring(1), s.substring(1));
	            return false;            
	        }
	 }
	        
	        
	
	 public int maxProfit(int[] A) {
	        int n = A.length;
	        if(n == 0)
	            return 0;
	        
	        int maxProfit = 0;

	        int min = A[0];
	        int[] forward = new int[n];
	        forward[0] = 0;
	        for(int i = 1; i < n; i++){
	            if(A[i] < min){
	                min = A[i];
	            }
	            int p = A[i] - min;
	            forward[i] = Math.max(p, forward[i-1]);
	            maxProfit = Math.max(maxProfit, forward[i]);
	        }
	        
	        int max = A[n-1];
	        int[] back = new int[n];
	        back[n-1] = 0;
	        
	        for(int i = n-2; i >= 0; i--){
	            if(A[i] > max){
	                max = A[i];
	            }
	            int p = max - A[i];
	            back[i] = Math.max(p, back[i+1]);
	        }
		    
		    for(int i =0; i + 1 < n; i++){
		        maxProfit = Math.max(forward[i] + back[i+1], maxProfit);
		    }
		    return maxProfit;
	    }
	
	
	@Test
	public void testwordBreak(){
		Set<String> dict = new HashSet();
		dict.add("cat");
		dict.add("cats");
		dict.add("sand");
		dict.add("and");
		dict.add("dog");
		
		ArrayList<String> res = wordBreak("catsanddog", dict);
		System.out.println(res);
	}
	
	public ArrayList<String> wordBreak(String s, Set<String> dict) {
		ArrayList<String> res = new ArrayList();
		String sol ="";
		dfs(0, s, sol, res, dict);
		return res;
	}

	
	public void dfs(int start, String s, String sol, ArrayList<String> res, Set<String> dict){
		if(start == s.length()){
			res.add(sol);
			return;
		}
		
		StringBuffer buf = new StringBuffer();
		for(int i = start; i < s.length(); i++){
			buf.append(s.charAt(i));
			
			if(dict.contains(buf.toString())){
				String newsol = sol.length() == 0 ? buf.toString() : sol + " " + buf.toString();
				dfs(i+1, s, newsol, res, dict);
			}
		}
	}
	
	
	
	
	
	
	public ArrayList<String> wordBreak1(String s, Set<String> dict) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        return wordBreakHelper(s,dict,map);
    }

    public ArrayList<String> wordBreakHelper(String s, Set<String> dict, Map<String, ArrayList<String>> memo){
        if(memo.containsKey(s)) return memo.get(s);
        ArrayList<String> result = new ArrayList<String>();
        int n = s.length();
        if(n <= 0) return result;
        for(int len = 1; len <= n; ++len){
            String subfix = s.substring(0,len);
            if(dict.contains(subfix)){
                if(len == n){
                    result.add(subfix);
                }else{
                    String prefix = s.substring(len);
                    ArrayList<String> tmp = wordBreakHelper(prefix, dict, memo);
                    for(String item:tmp){
                        item = subfix + " " + item;
                        result.add(item);
                    }
                }
            }
        }
        memo.put(s, result);
        return result;
    }
	
	
	
	public boolean wordBreak11(String s, Set<String> dict) {
        if(s == null || s.length() == 0)
            return true;
        int n = s.length();
        boolean[] dp = new boolean[n];
        
        for(int i=0; i < n; i++){
            if(dict.contains(s.substring(0, i))){
                dp[i] = true;
            }
            for(int j =0; j < i; j++){
                if(dp[j] && dict.contains(s.substring(j+1,i+1)))
                    dp[i] = true;
            }
        }
        return dp[n-1];
    }
	
	
	
	public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0){
            return 0;
        }
        int n = triangle.size();
        
        int[][] sum = new int[n][n];        
        
        for(int i = 0; i < n; i++){
            sum[n-1][i] = triangle.get(n-1).get(i);
        }
        
        for(int i = n-2; i >= 0; i--){
            for(int j =0; j < i; j++){
                sum[i][j] = sum[i+1][j] + sum[i+1][j+1];                
            }
        }
        
        return sum[0][0];
    }
	
	
	
	@Test
	public void testtrap(){
		int a = trap(new int[]{2,1,0,2});
		System.out.println(a);
	}
	
	public int trap(int[] A) {
        int maxIndex = 0;
        int maxValue = Integer.MIN_VALUE;
        
        for(int i = 0; i < A.length; i++){
            if(A[i] >= maxValue){//last maxIndex
                maxIndex = i;
                maxValue = A[i];
            }
        }
        
        int leftsum = 0;
        int slow =0;
        for(int i = 1; i < maxIndex; i++){
            if(A[i] > A[slow]){
                slow = i; 
            }else{
                leftsum += A[slow] - A[i];
            }
        }
        
        int rightsum = 0;
        slow = A.length - 1;
        for(int i = A.length -1; i > maxIndex; i--){
            if( A[i] > A[slow]){
                slow = i;
            }else{
                rightsum += A[slow] - A[i];
            }
        }
        return leftsum + rightsum;
    }
	
	
	
	
	public List<String> fullJustify(String[] words, int L){
        List<String> res = new ArrayList();
        int count = 0;
        int start = 0;
        for(int i=0; i < words.length; i++){
            if(count + i - start + words[i].length()> L){
                int wordnum = i - start;
                int spacenum = wordnum - 1;
                StringBuffer buf = new StringBuffer();
                int remain = count - spacenum;
                
                if(spacenum >= 2){
                    int base = remain/spacenum;
                    int mod =  remain%spacenum;
                    for(int k = start; k < start + wordnum; k++){
                        buf.append(words[k]);
                        if(k < start + mod){
                            buf.append(space(base + 1));
                        }else{
                            buf.append(space(base));
                        }
                    }
                }else if(spacenum == 1){
                    buf.append(words[start]);
                    buf.append(space(remain));
                    buf.append(words[start+ 1]);
                }else{
                    buf.append(words[start]);
                    buf.append(space(remain));
                }
                res.add(buf.toString());
                i = start;
                count = 0;                
                                    
                }
            
            count += words.length;            
        }
        
        int wordsnum = words.length - start;
        int remain = L - count- wordsnum;
        
        StringBuffer buf = new StringBuffer();
        
        if(wordsnum == 1){
            buf.append(words[start]);
            buf.append(space(remain));
        }else{
            for(int k = start; k < start + wordsnum; k++){
                buf.append(words[k]);
                if(k != start + wordsnum -1)
                    buf.append(' ');
            }
            remain = remain - wordsnum - 1 - count;
            if(remain > 0){
                buf.append(space(remain));
            }
        }
        res.add(buf.toString());
        
        
        
        return res;
    }


	String space(int n){
		StringBuffer buf = new StringBuffer();
		for(int i =0; i < n; i++){
			buf.append(' ');
		}
		return buf.toString();
	}	
}
