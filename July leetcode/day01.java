package leetcode;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Test;

public class leetcode {
	
	public int maxProfit1(int[] a) {
		int n = a.length;

		int valley = -1;
		int peak = -1;

		if (n == 0)
			return 0;
		if (n == 1)
			return 1;

		if (a[0] <= a[1])
			valley = a[0];

		ArrayList<Integer> list = new ArrayList();

		for (int i = 1; i < n; i++) {
			if (i != n - 1) {
				if (a[i] <= a[i - 1] && a[i] <= a[i + 1])
					valley = a[i];
				if (a[i] >= a[i - 1] && a[i] >= a[i + 1]) {
					peak = a[i];
					if (valley != -1) {
						int tmp = peak - valley;
						list.add(tmp);
					}
				}
			} else {
				if (a[i] >= a[i - 1]) {
					peak = a[i];
					if (valley != -1) {
						int tmp = peak - valley;
						list.add(tmp);
					}
				}
			}
		}

		int max = 0;
		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) > max) {
				max = list.get(i);
				index = i;
			}
		}
		list.remove(index);
		int res = max;
		max = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) > max) {
				max = list.get(i);
			}
		}

		res += max;
		return res;

	}
	
	
	
	
	
	
	 public int maxProfit(int[] a) {
	        int n = a.length;
	        int sum  = 0;
	        int peak = -1;
	        int valley = -1;
	        if( n==0)
	            return 0;
	        if( n ==1)
	            return 0;
	        
	        if(a[0] <= a[1])
	            valley = a[0];
	        
	        for(int i = 1; i < n ; i++){
	            if(i != n-1){
	                if(a[i] <= a[i+1] && a[i] <= a[i-1]){
	                    valley = a[i];
	                }else if(a[i] >= a[i+1] && a[i] >= a[i-1]){
	                    peak = a[i];
	                    if(valley != -1){
	                        sum += (peak - valley);
	                        valley = -1;
	                    }
	                }
	            }else{
	                if(a[i] >= a[i-1]){
	                    peak = a[i];
	                    if(valley != -1){
	                        sum += (peak - valley);
	                        valley = -1;
	                    }
	                }
	            }
	        }
			return sum;  
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testcandy(){
		int[] ratings = new int[]{1,2,3,1,2,3,4,2,1};
		candy(ratings);
	}
	@Test
	public void testcandy1(){
		int[] ratings1 = new int[]{1,1,1};
		int[] ratings2 = new int[]{1,0,2};
		int[] ratings = new int[]{1,2,2};
		System.out.println(candy(ratings));
	}
	public int candy(int[] ratings) {
		int n = ratings.length;
        int[] candy = new int[n];
        
        if(n==0)
			return 0;
		if(n==1)
			return 1;
		if(n==2){
			if(ratings[1] == ratings[0])
				return 2;
			else
				return 3;
		}
		
        ArrayList<Integer> minIndex = new ArrayList();
        //init
        if(ratings[0] <= ratings[1]){
            minIndex.add(0);
            candy[0] = 1;
        }
        for(int i = 1; i < n-1; i++){
            if(ratings[i] <= ratings[i-1] && ratings[i] <=ratings[i+1]){
                minIndex.add(i);
                candy[i] = 1;
            }
        }
        if(ratings[n-1] <= ratings[n-2]){
            minIndex.add(n-1);
            candy[n-1] = 1;
        }
        //add all min Index
        
    
        
        
        
        int a = minIndex.get(0);
        if(a != 0){
            for(int i= a-1; i>=0 ; i--){
                candy[i] = candy[i+1] + 1;
            }
        }
        for(int i = 1; i < minIndex.size(); i++){
            int lower_bound = minIndex.get(i-1);
            int upper_bound = minIndex.get(i);
            if(lower_bound + 1 == upper_bound){
                continue;
            }else{
               lower_bound++;
               upper_bound--;
               for(;lower_bound <= upper_bound;){
                    if(lower_bound == upper_bound){
                        candy[lower_bound] = Math.max(candy[lower_bound-1], candy[upper_bound+1]) + 1;
                    }else{
                        candy[lower_bound] = candy[lower_bound-1] + 1;
                        candy[upper_bound] = candy[upper_bound+1] + 1;
                        if(lower_bound + 1 == upper_bound){
                            if(ratings[lower_bound] > ratings[upper_bound])
                                candy[lower_bound] = Math.max(candy[lower_bound], candy[upper_bound] + 1);
                            else if(ratings[lower_bound] < ratings[upper_bound]){
                                candy[upper_bound] = Math.max(candy[lower_bound]+1, candy[upper_bound]);   
                            }
                        }
                    }  
                    lower_bound++;
                    upper_bound--;
               }
            }
        }
      
        
        int b = minIndex.get(minIndex.size()-1);
        if(b != n-1){
            for(int i = b + 1; i<n; i++){
                candy[i] = candy[i-1] + 1;
            }
        }
        
        int sum = 0;
        for(int i=0; i<n;i++){
            sum += candy[i];
            System.out.print(candy[i] + ",");
        }
        return sum;
	}
	
	
	
	
	
	
	public int candy1(int[] ratings) {
		// 1 2 3 4 1 3 4 5
		int n = ratings.length;
		int[] candy = new int[n];
		ArrayList<Integer> minIndex = new ArrayList();
	
		
		if(n==0)
			return 0;
		if(n==1)
			return 1;
		if(n==2){
			if(ratings[1] == ratings[0])
				return 2;
			else
				return 3;
		}
		
		
		if (ratings[0] < ratings[1]) {
			minIndex.add(0);
			candy[0] = 1;
		}
		for (int i = 1; i < n - 1; i++) {
			if (ratings[i] < ratings[i - 1] && ratings[i] < ratings[i + 1]) {
				candy[i] = 1;
				minIndex.add(i);
			}
		}
		if (ratings[n - 1] < ratings[n - 2]) {
			minIndex.add(n - 1);
			candy[n - 1] = 1;
		}
		// now we get all min index.

		
		
		
		if(minIndex.size()==0)
			return n;
		
		
		int a = minIndex.get(0);
		
		if (a != 0) {
			for (int i = a - 1; i >= 0; i--) {
				if (ratings[i] > ratings[i + 1])
					candy[i] = candy[i + 1] + 1;
				else
					candy[i] = candy[i + 1];
			}
		}

		
		
		
		for (int i = 1; i < minIndex.size(); i++) {
			int lower_bound = minIndex.get(i - 1);
			int upper_bound = minIndex.get(i);
			lower_bound++;
			upper_bound--;

			for (; lower_bound <= upper_bound;) {
				if (lower_bound == upper_bound) {
					candy[lower_bound] = Math.max(candy[lower_bound - 1],
							candy[upper_bound + 1]) + 1;
				} else if (lower_bound + 1 == upper_bound) {
					if (ratings[lower_bound] > ratings[lower_bound - 1])
						candy[lower_bound] = candy[lower_bound - 1] + 1;
					else
						candy[lower_bound] = candy[lower_bound];
					if (ratings[upper_bound] > ratings[upper_bound + 1])
						candy[upper_bound] = candy[upper_bound] + 1;
					else
						candy[upper_bound] = candy[upper_bound];

					if (ratings[lower_bound] > ratings[upper_bound])
						candy[lower_bound] = Math.max(candy[lower_bound],
								candy[upper_bound] + 1);
					else if (ratings[lower_bound] == ratings[upper_bound]) {

					} else
						candy[upper_bound] = Math.max(candy[upper_bound],
								candy[lower_bound] + 1);

				} else {
					if (ratings[lower_bound] > ratings[lower_bound - 1])
						candy[lower_bound] = candy[lower_bound - 1] + 1;
					else
						candy[lower_bound] = candy[lower_bound];
					if (ratings[upper_bound] > ratings[upper_bound + 1])
						candy[upper_bound] = candy[upper_bound + 1] + 1;
					else
						candy[upper_bound] = candy[upper_bound];
				}
				lower_bound++;
				upper_bound--;
			}
		}
		// System.out.println("pittburg");

		int b = minIndex.get(minIndex.size() - 1);
		//System.out.println("b="+b + "size="+minIndex.size());
		if (b != n - 1) {
			for (int i = b + 1; i < n; i++) {
				if (ratings[i] > ratings[i - 1])
					candy[i] = candy[i - 1] + 1;
				else
					candy[i] = candy[i - 1];
			}
		}

		int sum = 0;
		for (int i = 0; i < n; i++) {
			System.out.print(candy[i] + ",");
			sum += candy[i];
		}
		return sum;
	}
	
	
	
	
	
	
	
	
	
	
	
	public boolean search3(int[] A, int target){
		int start = 0;
		int n = A.length;
		int last = n - 1;
		
		
		
		return false;
	}

	
	
	@Test
	public void test32(){
		try{
//		System.out.println("result = " + search(new int[]{}, 2));
//		System.out.println("result = " + search(new int[]{1,2,3}, 2));
//		System.out.println("result = " + search(new int[]{4,5,6,1,2,3}, 7));
//		System.out.println("result = " + search(new int[]{4,5,6,1,2,3}, 6));
//		System.out.println("result = " + search(new int[]{4,5,6,1,2,3}, 2));
//		System.out.println("result = " + search(new int[]{3,1}, 1));
		
//			assertEquals(-1, search(new int[]{}, 2));
//			assertEquals(1, search(new int[]{1,2,3}, 2));
//			assertEquals(-1, search(new int[]{4,5,6,1,2,3},7));
//			assertEquals(2, search(new int[]{4,5,6,1,2,3}, 6));
//			assertEquals(1, search(new int[]{3,1},1));
//			assertEquals(-1, search(new int[]{5,1,3}, 0));
//			assertEquals(2, search (new int[]{3,5,1}, 1));
//			assertEquals(-1, search (new int[]{3,5,1}, 0));
//			assertEquals(1, search (new int[]{3,5,1}, 5));
//			assertEquals(2, search (new int[]{5,1,3}, 3));

			System.out.println("success!");
		}catch(Exception e){
			System.out.println("something wrong!S");
		}
	}
	public int search(int[] A, int target) {
		int n = A.length;
		int start = 0;
		int last = n - 1;
		if (n == 0)
			return -1;
		if (n == 1)
			return A[0] == target ? 0 : -1;

		if (n == 2) {
			if (A[0] == target)
				return 0;
			if (A[1] == target)
				return 1;
			return -1;
		}

		if (A[start] < A[last])
			return binarySearch(start, last, A, target);
		int max = -1;
		int min = -1;

		for (;;) {
			int mid = (start + last) / 2;
			// array out of index -1
			if (mid - 1 < 0) {
				if (A[mid] > A[mid + 1]) {
					max = mid;
					break;
				}
			}else{
				if (A[mid - 1] < A[mid] && A[mid] > A[mid + 1]) {
					max = mid;
					break;
				}
				// 5 1 3
				if (A[mid] < A[last])
					last = mid;
				// 2 3 1
				if (A[start] <= A[mid])
					start = mid;
			}
		}
		//System.out.println("max = " + max);
//		System.out.println(A[max+1]);
//		System.out.println("target= " + target);
		//123   3 1 2     2 3 1
		last = n - 1;
		start = 0;
		if (target > A[max] || target < A[max + 1])
			return -1;
//		System.out.println(1212);
		if (target >= A[start])
			return binarySearch(start, max, A, target);
		//if (target <= A[last] && target >= A[max + 1])
		else
			return binarySearch(max+1, last, A, target);
			
	}

	
	
	
	int binarySearch(int start, int last, int[] A, int target) {
		for (; start <= last;) {
			int mid = (start + last) / 2;
			
				if (A[mid] > target)
					last = mid - 1;
				else if(A[mid] < target)
					start = mid + 1;
				else if(A[mid] == target)
					return mid;
		}
		return -1;
	}
	
	
	
	
	
	
	@Test 
	public void test23(){
		System.out.println(binarySearch(1,2, new int[]{5,1,3}, 3));
	}
//
//	@Test
//	public void test51(){
////		System.out.println("result = " + search(new int[]{}, 2));
////		System.out.println("result = " + search(new int[]{1,2,3}, 2));
////		System.out.println("result = " + search(new int[]{4,5,6,1,2,3}, 7));
////		System.out.println("result = " + search(new int[]{4,5,6,1,2,3}, 6));
////		System.out.println("result = " + search(new int[]{4,5,6,1,2,3}, 2));
//		System.out.println("result = " + search(new int[]{3,1}, 1));
//
//	}
//	
//	 public int search23(int[] A, int target) {
//	        int n = A.length;
//	        int start = 0;
//	        int last = n - 1;
//	        if(n == 0)
//	        	return -1;
//	        
//	        if(A[start] < A[last])
//	            return binarySearch(start, last, A , target);
//	        
//	            
//	        for(;start <= last;){
//	            int mid = (start + last)/2;
//	            if(A[mid] >= A[start]){
//	                if( A[mid] > target && A[start]  < target){
//	                    return binarySearch(start, mid, A, target);
//	                }
//	                if(A[mid] == target)
//	                    return mid;
//	                
//	                if(A[start] > target || target > A[mid])
//	                    start = mid + 1;
//	                
//	            }
//	            if(A[mid] < A[start]){
//	                if(target > A[mid] && target < A[last])
//	                    return binarySearch(mid, last, A, target);
//	                if(A[mid] == target)
//	                    return mid;
//	                // if(target > A[last])
//	                
//	                    last = mid - 1;
//	            }
//	        }
//	        return -1;
//	    }
//	 	int binarySearch(int start, int last, int[] A, int target){
//	 		
//	 		for(;start <= last;){
//	 			int mid = (start + last)/2;
//	 			if(target < A[mid])
//	 				last = mid - 1;
//	 			if(target > A[mid])
//	 				start = mid + 1;
//	 			
//	 			if( target == A[mid])
//	 				return mid;	
//	 		}
//	 		return -1;
//	 		
//	 	}
//	
//	@Test
//	public void test5(){
//		int[] B = searchRange1(new int[]{1}, 1);
//		System.out.println(B[0] + "..." + B[1]);
//	}
//	
//	int[] searchRange1(int A[], int target){
//		int[] result = new int[2];
//		result[0] = -1;
//		result[1] = -1;
//		int n = A.length;
//		int start = 0;
//		int last = n - 1;
//		
//		if(n==0)
//			return result;
//		
//		for(;start < last;){
//			int mid = (start + last)/2;
//			if(A[mid] < target)
//				start = mid+1;
//			if(A[mid] >= target){
//				last = mid;
//			}
//		}
//		int low_bound = A[start] == target?start:-1;
//		if(low_bound == -1)
//			return result;
//		
//		//find the high bound of the range, O(lgn)
//		start = low_bound;
//		
//		last = n - 1;
//		for(;start < last;){
//			int mid = (start + last+1)/2;
//			if(A[mid] > target){
//				last = mid - 1;
//			}
//			if(A[mid] <=target){
//				start = mid;
//			}
//		}
//		result[0] = low_bound;
//		result[1] = last;
//		
//		return result;	
//	}
//	
//	
//	
//	
////	@Test
////	public void test(){
////		reverseWords("dog a play");
////	}
////	
////	
////	
////	
////	public String reverseWords(String s) {
////		    if(s == null ||  s.trim().equals(""))
////		        return "";
////		    s = s.trim();
////		    int n = s.length();
////		    StringBuffer sb  = new StringBuffer();
////		    LinkedList<String> stack = new LinkedList();
////		    for(int i=0; i< n;i++){
////		        char c = s.charAt(i);
////		        if(c != ' '){
////		            sb.append(c);
////		        }else{
////		            stack.push(sb.toString());
////		            sb = new StringBuffer();
////		            for(;;){
////		                if(s.charAt(i) != ' '){
////		                    sb.append(s.charAt(i));
////		                    break;
////		                }
////		                i++;
////		            }
////		            
////		        }
////		    }
////		    
////		    stack.push(sb.toString());
////		    
////		    
////		    Iterator it = stack.iterator();
////		    StringBuffer result = new StringBuffer();
////		    
////	        for(;it.hasNext();){
////	            result.append(it.next());
////	            result.append(" ");
////	        }
////	        System.out.println(result);
////		    //result.deleteCharAt(result.length()-1);
////		    return result.toString();
////	    
////    }
//	
////	
////	 @Test
////	 public void test4(){
////		 int[] A = new int[]{1,4,7,9};
////		 int[] B = searchRange(A, 4);
////		 System.out.println(B[0] +"..."+ B[1]);
////	 }	
////	
////	public int[] searchRange(int[] A, int target) {
////		int n = A.length;
////		int start = 0;
////		int last = n - 1;
////		int left = -1;
////		int right = -1;
////		if(A[start] == target && A[last] == target){
////			return new int[]{start, last};
////		}
////		
////		//not exist
////		//start must not be true
////		for (;;) {
////			int mid = (start + last)/ 2;
////			if (A[mid] == target) {
////				left = mid;
////				right = mid;
////				int mid1 = mid;
////				int mid2 = mid;
////				// edge case
////				for (; start != left || right != last;) {
////					if (start != left) {
////						mid1 = (start + left) / 2;
////						if (A[mid1] == target) {
////							left = mid1;
////						} else {
////							start = mid1;
////						}
////					}
////					if (right != last) {
////						mid2 = (right + last) / 2;
////						
////						if (A[mid2] == target) {
////							right = mid2;
////						} else {
////							last = mid2;	
////						}
////					}
////				}
////				return new int[]{left, right};
////			}
////			if(A[mid] > target){
////				last = mid;
////				continue;
////			}
////			
////			if(A[mid] < target){
////				mid = start;
////				continue;
////			}
////		}
////	}
	
	
	
}
