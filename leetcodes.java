package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;



public class leetcodes {
	
	private boolean canTrans(String a, String b){
	        int dif = 0;
	        for (int i = 0; i < a.length(); i++){
	            if (a.charAt(i) != b.charAt(i)){
	                dif++;
	                if (dif >= 2){
	                    return false;
	                }
	            }
	        }
	        return true;
	}
	 
	 
//	public int ladderLength(String start, String end, Set<String> dict) {
//		
//	        Queue<String> wq;
//	        Queue<Integer> lq;
//	        wq.add(start);
//	        lq.add(1);
//	        while (!(wq.size()==0)){
//	            String word = wq.poll();
//	            int lev = lq.poll();
//	            
//	            for (int i = 0; i < word.length(); i++){
//	            	
//	                char temp = word.charAt(i);
//	                for (char c = 'a'; c <= 'z'; c++){
//	                    word.charAt(i) = c;
//	                    if (word == end){
//	                        return lev + 1;
//	                    }
//	                    if (dict.contains(word) != ){
//	                        wq.push(word);
//	                        lq.push(lev + 1);
//	                        dict.erase(word);
//	                    }
//	                    word[i] = temp;
//	                }
//	                
//	            }
//	        }
//	        return 0;
//	    }
//	}
//	
//	
//	
//	
//	
//	
	
	
	
	
	
	
	
	
	
	

	@Test
	public void testladderLength(){

		Set<String> dict = new LinkedHashSet();
		dict.add("aaa");
		dict.add("ccc");
//		dict.add("c");
	
		
		int a = ladderLength("aaa","ccc", dict);
		System.out.println(a);
	}
			
	public int ladderLength(String start, String last, Set<String> dict) {
        int minlength = Integer.MAX_VALUE;
         
        int startIndex = -1;
        int lastIndex = -1;
        if(helpfunc(start,last))
        	return 2;
        Iterator it = dict.iterator();
        ArrayList<String> list = new ArrayList();
        Iterator it2 = dict.iterator();
        for(int j = 0; it2.hasNext() ; j++){
        	list.add((String) it2.next());
        }
        
        int i = 0;
        while(it.hasNext()){
        	String tmp = (String) it.next();
        	if(helpfunc(start, tmp))
        		startIndex = i;
        	if(helpfunc(last,tmp) ){
        		lastIndex = i;
        		if(startIndex != -1){
        			int length = lastIndex - startIndex;
        			if(!list.get(lastIndex).equals(last))
        				length ++;
        			if(!list.get(startIndex).equals(start))
        				length ++;
        			
        			if(length < minlength){
        				minlength = length;
        			}
        		}
        	}
        	i++;
        }
        return minlength;
    }
	
    
    public boolean helpfunc(String A, String B){
        int n = A.length();
        int count = 0;
        for(int i = 0; i < n; i++){
            if(A.charAt(i) != B.charAt(i))
                count++;
        }
        return count < 2? true:false;     
    }	
	
//	public int ladderLength1(String start, String last, Set<String> dict) {
//        int minlength = Integer.MAX_VALUE;
//         
//        int startIndex = -1;
//        int lastIndex = -1;
//        
//        Iterator it = dict.iterator();
//        int i = 0;
//        while(it.hasNext()){
//        	String tmp = (String) it.next();
//        	if(helpfunc(start, tmp))
//        		startIndex = i;
//        	if(helpfunc(last,tmp)){
//        		lastIndex = i;
//        		if(startIndex != -1){
//        			int length = lastIndex + 1 - startIndex;
//        			if(length < minlength)
//        				minlength = length;
//        		}
//        	}
//        	i++;
//        }
//        return minlength;
//
//        
//      
//    }
//    
//    public boolean helpfunc1(String A, String B){
//        int n = A.length();
//        int count = 0;
//        for(int i = 0; i < n; i++){
//            if(A.charAt(i) != B.charAt(i))
//                count++;
//        }
//        return count < 2? true:false;     
//    }
//    
    
//  for(int i = 0; i < dict.size(); i++){
//  String tmp = dict.get(i);
//  
//  if(helpfunc(start, tmp))
//      startIndex = i;
//  if(helpfunc(last, tmp)){
//      lastIndex = i;
//      if(startIndex != -1){
//          int length = lastIndex + 1 - startIndex;
//          if(length < minlength){
//              minlength = length;
//          }
//      }
//
//  }
//}
//if(minlength == Integer.MAX_VALUE)
//  return 0;
//else
//  return minlength;
    
    
    
    
	
	 @Test
	 public void testthreeSumClosest(){
		 
	 }
	
	
	
	 public int threeSumClosest(int[] A, int target) {
	        int n = A.length;
	        
	        int min = Integer.MAX_VALUE;
	        int res = A[0];
	        Arrays.sort(A);
	        
	        
	        for(int i = 0; i < n; i++){
	            int start = i + 1;
	            int last = n - 1;
	            for(;start < last;){
	                int sum = A[i] + A[start] + A[last];
	                    int tmp = Math.abs(sum - target);
	                    if( tmp == 0)
	                        return target;
	                    else if(tmp < min){
	                        min = tmp;
	                        res = sum;
	                    }
	                    
	                    if(sum > target){
	                        last--;
	                    }else{
	                        start++;
	                    }
	                
	            }
	        }
	        return res;
	    }
	
	@Test
	public void testhashset(){
		HashSet set = new HashSet();
		ArrayList a = new ArrayList();
		a.add(1);
		a.add(2);
		
		ArrayList b = new ArrayList();
		b.add(1);
		b.add(2);
		set.add(a);
		set.add(b);
		
		System.out.println(a.equals(b));
		System.out.println(set.size());
	}
	
	
	
	
	@Test
	public void test4Sum(){
		fourSum(new int[]{1, 0, -1, 0, -2, 2},0);
	}
	
	
	
	  public List<List<Integer>> fourSum(int[] A, int target) {
	        int n = A.length;
	        Arrays.sort(A);
	        List<List<Integer>> result = new ArrayList();
	                
	        HashSet<List<Integer>> hashset = new HashSet();
	        for(int i = 0; i < n ; i++){
	            for(int j = i+ 1; j < n; j++){
	               int k = j + 1;
	               int l = n - 1;
	               for(;k < l; ){
	                   int sum = A[i] + A[j] + A[k] + A[l];
	                   if(sum == target){
	                       List<Integer> tmp = new ArrayList();
	                       tmp.add(A[i]);
	                       tmp.add(A[j]);
	                       tmp.add(A[k]);
	                       tmp.add(A[l]);
	                       
	                       if(!hashset.contains(tmp)){
	                           hashset.add(tmp);
	                           result.add(tmp);
	                          
	                       }
	                       k++;
	                       l--;
	                       continue;
	                   }else if( sum > target)
	                       l--;
	                   else
	                       k++;

	               }
	            } 
	        }
	        return result;
	        
	    }
	
	
	
	
	

	public List<List<Integer>> fourSum1(int[] A, int target) {
        int n  = A.length;
        Arrays.sort(A);
        List<Integer> curr = new ArrayList<Integer>();
        List<List<Integer>> res = new ArrayList();
        dfs1(A,target, res, curr,0);
        return res;
    }
    
    public void dfs1(int[] A, int target, List<List<Integer>>  res, List<Integer> curr, int cut){
        int n = A.length;
        
        if(curr.size() == 3){
        	int sum  = curr.get(0) + curr.get(1) + curr.get(2);
        	int offset = target - sum;
        	if(offset < A[cut-1]){
        		return;
        	}
        }
        
        if(curr.size() == 4){
            int sum = curr.get(0) + curr.get(1) + curr.get(2) + curr.get(3);
            if(sum == target){
            	List<Integer> tmp = new ArrayList();
            	tmp.addAll(curr);
            	res.add(tmp);	
            }
            
            return;
        }

        for(int i = cut; i < n; i++){
            curr.add(A[i]);
            dfs1(A, target, res, curr,i+1);
            curr.remove(curr.size()-1);
        }
    }
	
	
	 public int sqrt(int x) {
	        long  last = Integer.MAX_VALUE;
	        long  start = 0;
	        
	     
	        for(;start < last;){  //0 100000
	            long  mid = start + (last - start)/2;
	            long  a = mid * mid;
	            if( a == x){
	                return (int) mid;
	            }
	        
	            if(start + 1==last){
	                return (int) start;
	            }
	            
	            if(a > x){
	                last = mid;
	                continue;
	            }
	            if(a < x){
	                start = mid;
	                continue;
	            }
	            
	            
	        }    
	        return (int) start;  
	    }
	
	
	
	
	
	 @Test
	 public void testtwoSum(){
		 int a = binarySearch(0,2, new int[]{3,2,4},6);
		 System.out.println(a);
		 twoSum(new int[]{3,2,4}, 6);
		 
	 }
	
	public int[] twoSum(int[] numbers, int target) {
		int n = numbers.length;

		ArrayList<Integer> list = new ArrayList();
		for (int i = 0; i < n; i++) {
			list.add(numbers[i]);
		}

		Arrays.sort(numbers);

		int start = -1;
		int last = -1;
		for (int i = 0; i < n; i++) {
			start = numbers[i];
			int tmp = target - numbers[i];
			last = binarySearch(i + 1, n - 1, numbers, tmp);
			if (last != -1) {
				break;
			}
		}
		int index1 = list.indexOf(start);
		int index2 = list.lastIndexOf(numbers[last]);

		return new int[] { index1 + 1, index2 + 1 };

	}

	int binarySearch(int start, int last, int[] A, int target) {
		for (; start <= last;) {
			int mid = (start + last) / 2;
			if (A[mid] == target) {
				return mid;
			} else if (A[mid] > target) {
				last = mid - 1;

			} else {
				start = mid + 1;
			}
		}
		return -1;

	}
	
	    
	    
	    
	    
	
	
	
	
	 public int[] twoSum1(int[] numbers, int target) {
	        int n = numbers.length;
	        Arrays.sort(numbers);

	        int last = -1;
	        for(int i = 0; i < n; i++){
	            int tmp = target - numbers[i];
	            last = binarySearch(i+1, n-1, numbers, tmp);
	            if(last != -1){
	                return new int[]{i+1, last+1};
	            }
	        }
	        return new int[]{-1,-1};
	   
	    }
	    
	    int binarySearch1(int start, int last, int[] A, int target){
	        for(;start <= last;){
	            int mid = (start + last)/2;
	            if(A[mid]  == target){
	                return mid;
	            }else if(A[mid] > target){
	                last = mid - 1;
	                
	            }else{
	                start = mid +1;
	            }
	        }
	        return -1;
	        

	    }
	    
	
	@Test
	public void testaddBinary(){
		String a = addBinary("0","0");
		System.out.println(a);
	}
	
	public String addBinary(String a, String b) {
        int n = a.length();
        int m = b.length();
        if(n  < m )
          return addBinary(b,a);


       int[] bitA = new int[n];
       int[] bitB = new int[n];
       int[] res = new int[n+1];
       StringBuffer sb = new StringBuffer();
       for(int i = 0; i < n ; i++){
          bitA[i] = Integer.parseInt(a.charAt(i) + "");
       }
       
       
       
       int offset = n-m;
      
       for(int i = 0; i < m; i++){//5 1      4
           bitB[offset + i]  = Integer.parseInt(b.charAt(i) + "");
       }
      
       for(int i = n-1; i>=0; i--){
         res[i+1] = bitA[i] + bitB[i] ;
       }
       
       for(int i = n; i >0; i--){
           if(res[i] >= 2){
               res[i]%=2;
               res[i-1]++;
           }
       }      
       for(int i = 0; i < n+1; i++){
           sb.append(res[i]);
       }
       
      if(sb.charAt(0) == '0')
    	  sb.deleteCharAt(0);
       
       
       return sb.toString();   
  }
	
	
	
	
	@Test
	public void testnumber(){
		
		
		String s =new String("11111");
		String s1 = new String("1");
		
		
		int a = Integer.parseInt(s);
		System.out.println(a);
		
	}
	
	
	
	
	@Test 
	public void testcandy(){
		int[] test = new int[]{1,2,3,1,3,1};
							  
		
		candy(test);
		
	}
	
	public int candy(int[] ratings){
		
		int n = ratings.length;
		
		int[] candy = new int[n];
		for(int i =0; i < n ; i++)
			candy[i] = 1;
		
		for(int i =1; i < n ; i++){
			if(ratings[i] > ratings[i-1])
				candy[i] = candy[i-1]  + 1;
			
		}
		for(int i = n -2; i >=0; i--){
			if(ratings[i] > ratings[i+1] && candy[i] <= candy[i+1]){
				candy[i] = candy[i+1] + 1;
			}			
		}
		
		int sum = 0; 
		for(int i =0; i < n ; i++){
			sum += candy[i];
			System.out.print(candy[i] + ",");
		}
		return sum;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testintarray(){
		int[] A = new int[]{3,3,3,4};
		singleNumber(A);
	}
	
	public int singleNumber(int[] A) {
        int[] bit = new int[32];
        int n = A.length;
        for(int i = 0; i < n; i++){
            int k = 1;

            for(int j = 0;  j < 32; j++){
                int rotated;
                if((rotated = A[i]  >> j) == 0) break;
                bit[j] += rotated & k;
            }
        }
        int target = 0;
        for(int i = 0; i  < 32; i++)
            target += (bit[i]%3 <<i);
        return target;
        
    }
	
	
	
	
	
	
	
	
	@Test
	public void testfindkth(){
		
		int[] b = new int[]{3};
		int[] a = new int[]{4};
		
		int res= findKth(a, a.length , b, b.length , 2);
		System.out.println("res="+res);
		// k <= m + n
	}
	
	@Test
	public void testfindkth1(){
		
		int[] b = new int[]{1,2,3};
		int[] a = new int[]{2,3,4};
		
		int res= findKth(a, a.length , b, b.length , 6);
		System.out.println("res="+res);
		// k <= m + n
	}
	
	
	
	public int findkth1(int[] A, int i, int[] B, int j, int k){
		if(A.length - i > B.length - j)
			return findkth1(B, j, A, i, k);
		if(i == A.length)
				return B[k-1];
		if(k == 1)
				return A[i] > B[j] ? B[j] : A[i];
			
		if(A[i] > B[j])
			return findkth1(A,i, B, j+1, k-1);
		else
			return findkth1(A, i+1, B, j, k-1);	
	}
	
//	
//	public int findkth2(int[] A, int i , int[] B , int j, int k){
//	
//		
//		
//		
//		
//		
//		
//		
//		
//		
//	}
//	
	
	
//	int findKth(int a[], int m, int b[], int n, int k)  
//	{  
//	    //always assume that m is equal or smaller than n  
//	    if (m > n)  
//	        return findKth(b, n, a, m, k);  
//	    if (m == 0)  
//	        return b[k - 1];  
//	    if (k == 1)  
//	        return Math.min(a[0], b[0]);  
//	    //divide k into two parts  
//	    int pa = Math.min(k / 2, m), pb = k - pa;
//	    if (a[pa - 1] < b[pb - 1])  
//	        return findKth(a + pa, m - pa, b, n, k - pa);  
//	    else if (a[pa - 1] > b[pb - 1])  
//	        return findKth(a, m, b + pb, n - pb, k - pb);  
//	    else  
//	        return a[pa - 1];  
//	}  
	
	
	
	// a.length , b.length
	// a, a.length, b, b.length
	int findKth(int a[], int m, int b[], int n, int k){
		int curA = a.length - m;
		int curB = b.length - n;
		
		if(m > n)
			return findKth(b,n, a, m, k);
		if(m == 0)
			return b[k-1];
		if(k == 1)
			return Math.min(a[curA], b[curB]);
		
		int pA = Math.min(k/2, m);
		int pB = k - pA;
		if(a[curA + pA - 1] < b[curB + pB - 1])
			return findKth(a, m - pA, b, n, k-pA);
		else if( a[ curA + pA - 1] > b[curB + pB - 1])
			return findKth(a, m, b, n - pB, k-pB);
		else
			return a[curA + pA - 1];
	}
	
	
	 public double findMedianSortedArrays(int A[], int B[]) {
	       int m = A.length;
	       int n = B.length;
	       
	       int length = n + m;
	       if(length % 2 == 1)
	            return (double)findKth(A, m, B, n , (length + 1)/2 );
	       else
	            return (findKth(A,m,B,n,length/2) + findKth(A,m,B,n,(length/2+1)))/2.0;
	}
	
	
	@Test 
	public void testfindMedian(){
		int[] a = new int[]{1,2,3};
		int[] b = new int[]{4,5};
		
		double res = findMedianSortedArrays(a,b);
		System.out.println("res=" + res);
		
	}
	
	
	
	
	
	
}
 
