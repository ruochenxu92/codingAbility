package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class leetcodes {
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test 
	public void testString(){
		String test = new String("Xiaomin Xu UIUC Zhaoying Wu");
		char[] aaa = new char[100];
		for(int i =0; i < test.length(); i++){
			aaa[i] = test.charAt(i);
			
		}
		aaa[test.length()] = '\0';
		ReplaceFun(aaa);
		System.out.println(aaa);
	}
	
	//in java, parse parameter pointer, did not change it pointer addresss;
	//if you change variable in place;
	
	@Test
	public void testString1(){
		
		
		String s = new String("Zhaoying Wu");
		aaaa(s);
		
		System.out.println(s);
	}
	
	
	public void aaaa(String s){//do concatenating will create new String, because String is immutable in java
		s += "Xiaomin Xu";	
	}
	
	
	public void ReplaceFun(char[] str ){
		StringBuffer sb =new StringBuffer();
		int n = str.length;
		for(int i = 0; i < n; i++){
			if(str[i] == ' '){
				sb.append("%20");
			}else{
				sb.append(str[i]);
			}
		}
		String s = sb.toString();
	
		for(int i =0; i < str.length; i++){
			str[i] = s.charAt(i);
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 @Test
	 public void testfind(){
		 findMedianSortedArrays(new int[]{1,2}, new int[]{1,2,3});
	 }

	 
	 public double findMedianSortedArrays(int A[], int B[]) {
	        int n = A.length;
	        int m = B.length;
	        int Astart = 0;
	        int Bstart = 0;
	        int Aend = n;
	        int Bend = n;
	        double Amedian;
	        double Bmedian;

	        if(n == 0 || m == 0){
	            if(n == 0){
	               if(m == 0)
	                    return 0;
	                else{
	                    if(m % 2 == 1)
	                        return B[m/2];
	                    else{
	                        return (B[m/2] + B[m/2-1])/2;
	                    }
	                }
	            }else{
	                if(m == 0)
	                    return 0;
	                else{
	                    if(n % 2 == 1)
	                        return A[n/2];
	                    else
	                        return (A[n/2] + A[n/2-1])/2;
	                }
	            }
	        }
        
	        for(;;){
	            if(Astart < Aend && Bstart < Bend){
	            	int Amid = (Astart + Aend)/2;
	                if(n % 2==1)
	                	Amedian = A[Amid];
	                else
	                	Amedian = (A[Amid] + A[Amid-1])/2.0;
	                int Bmid = (Bstart + Bend)/2;
	                if(m % 2==1)
	                	Bmedian = B[Bmid];
	                else
	                	Bmedian = (B[Bmid] + B[Bmid-1])/2.0;
	                n = n / 2;
	                m = m / 2;
					if (Amedian == Bmedian)
						return Amedian;
					if (Amedian < Bmedian) {
						Astart = Amid;
						Bend = Bmid;
					} else {
						Aend = Amid - 1;
						Bstart = Bmid + 1;
					}
	            }else if(Astart <= Aend){
	                int Amid = (Astart + Aend)/2;
	                if(n % 2 == 1)
	                	Amedian = A[Amid];
	                else
	                	Amedian = (A[Amid] + A[Amid-1])/2.0;
	                return Amid;
	            }else if(Bstart <= Bend){
	            	int Bmid = (Bstart + Bend)/2;
	            	if(n % 2==1)
	            		Bmedian = B[Bmid];
	            	else
	            		Bmedian = (B[Bmid] + B[Bmid-1])/2.0;
	                return Bmid;
	            }
	        }
	}
	
	

	 public int maxProfit2(int[] a) {
	        
	        int n = a.length;
	        if(n <= 1)
	            return 0; 
	        int[] dp = new int[n+1];
	        int[] dp2 = new int[n];
	    
	        int min = Integer.MAX_VALUE;
	        int max = Integer.MIN_VALUE;
	        dp[0] = 0;
	        for(int i = 1; i <= n; i++){
	            
	            if(a[i-1] < min)
	                min = a[i-1];
	            // if(a[i-1] > max)
	            //     max = a[i-1];
	            // dp[i] = max-min;
	            int tmp = a[i-1] - min;
	            if(tmp > max)
	                max = tmp;
	            dp[i]= max;
	        }
	        
	        int maxProfit = Integer.MIN_VALUE;
	        max = Integer.MIN_VALUE;
	        for(int i = n-1; i >= 0; i--){
	            
	            if(a[i] > max)
	                max = a[i];
	            // if(a[i] < min)
	            //     min = a[i];
	            // dp2[i]  = max - min;
	            int tmp = max - a[i];
	            if(tmp > maxProfit)
	                maxProfit = tmp;
	            dp2[i] = tmp;
	        }
	        
	        int maxSum = Integer.MIN_VALUE;
	        
	        for(int i = 0; i< n; i++){
	            if(dp[i] + dp2[i] > maxSum)
	                maxSum = dp[i] + dp2[i];
	        }
	        
	        return maxSum;
	    }
	
public int maxProfit(int[] a) {
        
        int n = a.length;
        if(n <= 1)
            return 0; 
        int[] dp = new int[n];
        int[] dp2 = new int[n];
    
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for(int i = 0; i < n; i++){
            if(a[i] < min){
                min = a[i];
            }    
            
            int tmp = a[i] - min;
            if(tmp > max){
                max = tmp;
            }
            dp[i] = max-min;
        }
        
        int maxProfit = Integer.MIN_VALUE;
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        for(int i = n-1; i > 0; i--){
            if(a[i] > max){
                max = a[i];
            }   
            
            if(a[i] < min)
                min = a[i];
            
            dp2[i]  = max - min;
        }
        
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0; i+1< n; i++){
            if(dp[i] + dp2[i+1] > maxSum)
                maxSum = dp[i] + dp2[i+1];
        }
        
        return maxSum;
    }
	
	
	
	
	
	
	@Test
	public void testcandyDup(){
		
		int[] ratings = new int[]{1,2,3,1,2,7,9,3,1,1,1,0};
		
		
	
	}
	
	
	
	@Test
	public void testcandy(){
		
		int[] ratings = new int[]{1,2,3,1,2,3,1,2,3};

		candy(ratings);
		
	
	}

	public int candy(int[] ratings) {
        //Each child is assigned a rating value 
		int m = ratings.length;
      
        for(int i = 0; i < m; i++){
        	ratings[i] = ratings[i];
        }
        ratings[m] = ratings[m-1] - 1;
        int[] candy = new int[m];
        int n = ratings.length;

        
        int low_bound = -1;
        int upper_bound = -1;

        if( n == 1)
            return 1;
        if( n == 2)
            return ratings[0] == ratings[1] ? 2:3;
            
        ArrayList<Integer> minIndex = new ArrayList();
        for(int i = 1; i < n-1 ; i++){
            if(ratings[i] < ratings[i-1] && ratings[i] < ratings[i+1]){
                minIndex.add(i);
                candy[i] = 1;
            }
        }     
        if(ratings[0] < ratings[1]){
             candy[0] = 1;
             minIndex.add(0,0);
        }
        if(ratings[n-1] < ratings[n-2]){
            minIndex.add(n-1);
            candy[n-1]  = 1;
        }
        
        Iterator it = minIndex.iterator();
        //对于n <= 1 directly ignore it.
		for (int i = 1; i < minIndex.size(); i++) {
			low_bound = minIndex.get(i - 1);
			upper_bound = minIndex.get(i);
			low_bound++;
			upper_bound--;
			for (; low_bound < upper_bound;) {
				if (low_bound == upper_bound)
					candy[low_bound] = Math.max(candy[low_bound - 1],
							candy[upper_bound + 1]) + 1;
				else if (low_bound + 1 == upper_bound) {
					candy[low_bound] = candy[low_bound - 1] + 1;
					candy[upper_bound] = candy[upper_bound + 1] + 1;
					if (ratings[low_bound] > ratings[upper_bound])
						candy[low_bound] = Math.max(candy[low_bound],
								candy[upper_bound]);
					else if (ratings[low_bound] == ratings[upper_bound]) {// 3 4
						// equal ratings get equal candies.
						candy[low_bound] = Math.max(candy[low_bound],
								candy[upper_bound]);
						candy[upper_bound] = Math.max(candy[low_bound],
								candy[upper_bound]);
					} else {
						candy[upper_bound] = Math.max(candy[low_bound] + 1,
								candy[upper_bound]);
					}
				} else {
					//this does not work in duplicate model
					candy[low_bound] = candy[low_bound - 1] + 1;
					candy[upper_bound] = candy[upper_bound + 1] + 1;
				}
				low_bound++;
				upper_bound--;
			}
		}
        
        int sum = 0;
        for(int i = 0; i < n ; i++){
        	System.out.print(candy[i] + ",");
        	sum += candy[i];
        }
        return sum;
        
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void test123(){
		ArrayList<Interval> list = new ArrayList<Interval>();
		Interval a = new Interval(1,5);
		list.add(a);
		Interval b = new Interval(0,3);
//		System.out.println(insert(list, b));		
		insert(list,b);
		
		Iterator it = list.iterator();
		
		while(it.hasNext()){
			Interval tmp = (Interval) it.next();
			System.out.println(tmp.start+"," + tmp.end);
		}
		
		
		
		
		
	}
	
	
	
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

		int start = newInterval.start;

		for (int i = 0; i < intervals.size(); i++) {
			Interval tmp = intervals.get(i);
			if (start < tmp.start) {
				intervals.add(i, newInterval);
				break;
			}
		}
	//	System.out.println(intervals.size());

		// size = 1 condition

		for (int i = 1; i < intervals.size();) {
			Interval prev = intervals.get(i - 1);
			Interval curr = intervals.get(i);
			if (prev.end > curr.start) {
				intervals.remove(i - 1);
				curr.start = prev.start;
				curr.end = Math.max(curr.end, prev.end);
			} else {
				i++;
			}
		}
		
		return intervals;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testmerge(){
		
		List<Interval> list = new ArrayList<Interval>();
		Interval a = new Interval(1,3);
		Interval c = new Interval(2,6);
		Interval b = new Interval(8,10);
		list.add(a);
		list.add(b);
		list.add(c);
		List<Interval> intervals = merge(list);
		
		for(int i  =0; i < intervals.size(); i++){
			Interval tmp = intervals.get(i);
			System.out.println("start="+tmp.start + "end=" + tmp.end);
		}
	}
	
	
	public List<Interval> merge(List<Interval> intervals) {//arraylist, has all intervals
		
		
		Collections.sort(intervals, new Comparator<Interval>(){
			@Override
			public int compare(Interval A, Interval B) {
				return A.start > B.start?1:-1;
			}
		});
		//now it is sort. 
		
//		for(int i=0; i < n; i++){
//			Interval tmp = intervals.get(i);
//			System.out.println("i = "+ tmp.start);
//		}
		
		for(int i =1; i < intervals.size();){
			Interval prev = intervals.get(i-1);
			Interval curr = intervals.get(i);
			if(prev.end  > curr.start){
				intervals.remove(i-1);
				curr.end = Math.max(prev.end, curr.end);	
			}else{
				i++;
			}
		}
		return intervals;
    }
  
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void test1234(){
		
	}
	
	
	public void merge(int A[], int m, int B[], int n) {
        
        int[] result = new int[m + n];
        int i;
        int j;
        int dist;
        for(dist = 0, i = 0,j =0;i < m && j <n;){
            if(A[i] < B[j]){
                result[dist++] = A[i];
                i++;
            }else{
                result[dist++] = B[j];
                j++;
            }
        }
        
        if(i == m && j < n){
            for(; j < n ;j++)
            result[dist++] = B[j];
        }else if( j == n && i < m)
            for(; i < m; i++)
                result[dist++] = A[i];
            
            for(int k = 0; k < m + n; k++)
                A[k] = result[k];
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testdigits(){
		int[] b = plusOne(new int[]{8,9,9,9});
		for(int i = 0; i < b.length ; i++){
			System.out.print(b[i]);
		}
	}
	
	
	
	public int[] plusOne(int[] digits) {
        int n = digits.length;
        int i;
        for(i =0; i < n ; i++){
            if(digits[i] != 9)
                break;
        }
        if( i == n ){
            int[] res = new int[n+1];
            res[0] = 1;
            return res;
        }else{
        	digits[n-1]++;
            for(int j = n-1; j >= 0; j--){
                if(digits[j] == 10)
                	digits[j-1]++;
                digits[j] %=10;
            }
           
            return digits;
        }
        
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void test233232(){
//		assertEquals("/home", simplifyPath("/home/"));
//		assertEquals("/c", simplifyPath("/a/./b/../../c/"));
//		assertEquals("/", simplifyPath("/../"));
//		assertEquals("/", simplifyPath("/."));
//		assertEquals("/...", simplifyPath("/..."));
//		assertEquals("/", simplifyPath("///"));   
//		assertEquals("/home/foo",simplifyPath("/home//foo/"));
		assertEquals("/",simplifyPath("///eHx/.."));

		System.out.println("success");

	}						
	
	  public String simplifyPath(String path) {
	        int n = path.length();
	        int start=0;
	        int last = n - 1;

	        LinkedList<String> stack = new LinkedList();
	        if(n == 0)
	            return "";
	        if(n == 1)
	            return path;   
	        StringBuilder sb = new StringBuilder();
	        for(int i =0; i< n;i++){
	            char c = path.charAt(i);
	            if( c == '/' ){
	                String command = sb.toString();
	               for( ;i < n;i++){
	            	   if(path.charAt(i) != '/'){
	            		   i--;
	            		   break;
	            	   }
	               }
	                
	                if(command.equals("/.")){
	                	
	                }else if(command.equals("/..")){
	                    if(stack.size() > 0) stack.pop();
	                }else
	                    stack.push(command);
	                
	                sb = new StringBuilder();
	            }
	            	sb.append(c);

	        }
	        LinkedList<String> s1 = new LinkedList();

	        String tmp1 = sb.toString();
	        if(!tmp1.equals("/") && !tmp1.equals("/.") && !tmp1.equals("/.."))
	        	stack.push(sb.toString());//put all thing in stringbuffer
	        if(tmp1.equals("/..")){
	        	if(stack.size() > 0)
	        		stack.pop();
	        }
	        
	        Iterator it2 = stack.iterator();
	        while(it2.hasNext()){
	        	s1.push((String) it2.next());
	        }
	        
	        
	        
	        
	        Iterator it = s1.iterator();
	        sb = new StringBuilder();
	        while(it.hasNext()){
	            String tmp = (String) it.next();
	            sb.append(tmp);
	        }
	        String result = sb.toString();
	        if(result.equals(""))
	            return "/";
	        if(sb.charAt(sb.length()-1) == '/')
	        	sb.deleteCharAt(sb.length()-1);
	        
	        System.out.println(sb.toString());
	        return sb.toString();
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String simplifyPath1(String path) {
        int n = path.length();
        if(path == null || path.trim().equals(""))
        	return "";
        LinkedList<String> stack = new LinkedList();
        
        StringBuilder sb = new StringBuilder();
        sb.append('/');
        for(int i = 1; i < n; i++){
            char c = path.charAt(i);
            if(c=='/'){
                String tmp = sb.toString();
                if(tmp.equals("/.")){

                }else if(tmp.equals("/..")){
                	if(stack.size() > 0)
                    stack.pop();
                }else{
                    stack.push(tmp);
                }
                sb = new StringBuilder();
                sb.append('/');
            }else{
                sb.append(c);
            }
        }
        stack.push(sb.toString());
        Iterator it = stack.iterator();
        sb = new StringBuilder();
        while(it.hasNext()){
            sb.append(it.next());
        }
        
        return sb.toString();
    }
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void test2323(){
		assertEquals(3,romanToInt("III"));
		assertEquals(4,romanToInt("IV"));
		assertEquals(3999,romanToInt("MMMCMXCIX"));
		assertEquals(999,romanToInt("CMXCIX"));
		assertEquals(444,romanToInt("CDXLIV"));

	
	
	}
	
	public int c2n(char c){
		switch(c){
		case 'I': return 1;
		case 'V': return 5;
		case 'X': return 10;
		case 'L': return 50;
		case 'C': return 100;
		case 'D': return 500;
		case 'M': return 1000;
		default: return 0;
		}
	}
	
	public int romanToInt(String s){
		int result = 0;
		int n = s.length();
		char p = s.charAt(0);
		int prev = c2n(p);
		result += prev;
		for(int i=1; i < n; i++){
			char c = s.charAt(i);
			int curr = c2n(c);
			if(curr > prev){
				result -= prev;
				result += (curr - prev);
				prev = curr;
			}else{
				result += curr;
				prev = curr;
			}
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void test() {
		search(new int[] { 1, 1, 1 }, 0);
		search(new int[] { 1, 1, 2 }, 0);
//		search(new int[] { 1, 2, 1 }, 0);
//		search(new int[] { 2, 1, 1 }, 0);
	}

	public boolean search(int[] A, int target) {
		int n = A.length;
		int start = 0;
		int last = n - 1;
		int max = -1;
		for (;start < last;) {
			int mid = (start + last) / 2;
			if (mid - 1 < 0) {
				if (A[mid] > A[mid + 1]) {
					max = mid;
					break;
				}
			} 
			else if( mid + 1 > last){
				if(A[mid] > A[mid-1]){
					max = mid;
					break;
				}
			}
			else {
				if (A[mid] > A[mid - 1] && A[mid] < A[mid + 1]) {
					max = mid;
					break;
				}
				if (A[mid] >= A[start])
					start = mid+1;
				if (A[mid] <= A[last])
					last = mid;
			}
		}
//		System.out.println("max=" + max);
		if (max == -1) {
			return A[0] == target ? true : false;
		}
		start = 0;
		last = n - 1;
		return binarySearch(start, max, A, target) || binarySearch(max + 1, last, A, target);
	}

	boolean binarySearch(int start, int last, int[] A, int target) {
		for (;start < last;) {
			int mid = (start + last) / 2;
			if (A[mid] > target)
				last = mid - 1;
			else if (A[mid] < target)
				start = mid + 1;
			else
				return true;
		}
		return false;
	}
}

  class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }
 
