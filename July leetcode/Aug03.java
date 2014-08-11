package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

public class Aug03 {
	
	@Test
	public void testequvi(){
		String[][] str = new String[][] {{"a","b"},{"b","b","a"},{"b","a"}};
		System.out.println(allStringSetsIdentical(str));
	}
	
	boolean allStringSetsIdentical(String[][] str){
		if(str.length == 0)
			return true;
		
		HashSet<HashSet<String>> hs = new HashSet();
		for(String[] arr : str){
			HashSet<String> tmp = new HashSet();
			for(String s : arr){
				tmp.add(s);
			}
			hs.add(tmp);
		}
		return hs.size() == 1;
	}
	
	
	@Test
	public void testcs(){
		int[] A = new int[]{4,1,3,2,3};
		for(int i = 0; i < A.length; i++){
			System.out.print(A[i]  + ",");
		}
		cs(A);
	}
	
	void cs(int[] A){
		//41323 -> 12334
		int[] cs = new int[6];
		
		int sum = A.length;
		for(int i =0; i < A.length; i++)
			cs[A[i]]++;
		
		for(int i = A.length-1; i >= 0; i--){
			int pre = cs[i];
			cs[i] = sum - cs[i];
			sum = sum - pre;
		}
		
		int[] cout = new int[A.length];
		for(int i=0; i < A.length; i++){
			int index = cs[A[i]]++;
			cout[index] =A[i]; 
		}
		for(int i : cout){
			System.out.print(i  + ",");
		}
	}
	
	/**
	 * 1,1,1,1,2,2,2,3,3,3
	 */
	
	class Node{
		int key;
		int value;
		Node(int key, int value){
			this.key = key;
			this.value = value;
		}
	}
	
	@Test
	public void countingsort(){
		Node[] nodes = new Node[6];
		
		Node n1 = new Node(4,40);
		Node n2 = new Node(1,10);
		Node n3 = new Node(3,31);
		Node n4 = new Node(2,20);
		Node n5 = new Node(3,32);
		Node n6 = new Node(3,33);

		nodes[0] = n1;
		nodes[1] = n2;
		nodes[2] = n3;
		nodes[3] = n4;
		nodes[4] = n5;
		nodes[5] = n6;
		
		
		int[] count = new int[nodes.length + 1];
		
		for(int i = 0; i < nodes.length; i++){
			int key = nodes[i].key;
			count[key]++;
		}
		
		int lessandequal = nodes.length;
		
		for(int i = count.length-1; i >= 0; i--){
			int equal = count[i];
			count[i] = lessandequal - equal;
			lessandequal -= equal;
		}
		
		Node[] cout = new Node[nodes.length];
		
		for(int i = 0; i < nodes.length; i++){
			int index = nodes[i].key;
			cout[count[index]++] = nodes[i];
		}
		
		for(int i = 0; i < nodes.length; i++){
			System.out.print(cout[i].value + ",");
		}
	}
	
	
	class StringNode{
		Character key;
		String value;
		StringNode(Character key, String value){
			this.key = key;
			this.value = value;
		}
	}
	
	@Test
	public void testradix(){
		//node      a,a1 a,a2 a,a3
		//node     
		
		List<StringNode> res = new ArrayList();
		
		int n = 3;
		char[] chars = new char[]{'c','b','a','d'};
		StringBuffer value = new StringBuffer();
		
		
		for(int k = 0; k < chars.length;k ++){
			char base = chars[k];
			for(int i = 0; i < n; i++){
				value.append(base);
				value.append(i);
				StringNode tmp = new StringNode(base, value.toString());
				res.add(tmp);
				value = new StringBuffer();
			}
		}
		
		int len = chars.length * n;//12
		int[] count = new int[len + 1];
		//c1 c2 c3 b1 b2 b3 a1 a2 a3 d1 d2 d3
		for(int i = 0; i < res.size(); i++){
			char c = res.get(i).key;
			int key = c - 'a';
			count[key]++;
		}
		
		int lessandequal = len;
		for(int i = count.length-1;  i >= 0; i--){
			int equal = count[i];
			count[i] = lessandequal - count[i];
			lessandequal -= equal;
		}
		
		StringNode[] cout = new StringNode[len];//12
		for(int i = 0; i < len; i++){
			int key = res.get(i).key - 'a';
			cout[count[key]++] = res.get(i);
		}
		
		
		for(int i =0; i < cout.length; i++){
			System.out.print(cout[i].value + ",");
		}
	}
	
	//radix sort     
	@Test
	public void testradixsort(){
		String[] src = new String[]{"2341","4321","3214","1234","2314","1243"};
//		for(String s : src){
//			System.out.println(s);
//		}
		
		
		int len = src.length;//6
		String[] cout = new String[len];//6
		for(int k = src[0].length() - 1; k >= 0;k--){
			char min = '1';//the min value in our numbers
			
			int count[] = new int[len +1];//7
			
			//get counting numbers
			for(int i = 0; i < src.length;i++){
				int key = src[i].charAt(k) - min;
				count[key]++;
			}
			
			//get less than numbers inorder to the index of particular value;
			int lessandequal = len;
			for(int i = count.length-1; i >= 0; i--){
				int equal = count[i];
				count[i] = lessandequal - equal;
				lessandequal  -= equal;
			}
			
			for(int i = 0; i < len; i++){
				int key = src[i].charAt(k) - min;
				cout[count[key]++] = src[i];
			}
		}
		
		
		for(int i = 0; i < cout.length; i++){
			System.out.println(cout[i]);
		}
		
		/*
		 *  1234
			1243
			2341
			2314
			3214
			4321
		 * 
		 */
		
		
		/*
		 *  1 3 4 6  
		 *  3 8 9 2
		 *  2 9 0 1
		 *  1 2 3 4
		 *  make it general  int[] count = new count[] count[key]++ we can make it ten.
		 */
	}
	
	@Test
	public void testten(){
		int[] src = new int[]{1,9,9,2};
		int[] count = new int[10];
		
		//1. get the counting number
		for(int i =0; i< src.length; i++){
			int key = src[i];
			count[key]++;
		}
		
		//2.get the lessthan numbers in order to the index of particular number
		int lessandequal = src.length;
		for(int i = count.length - 1; i >=0; i--){
			int equal = count[i];
			count[i] = lessandequal - equal;
			lessandequal -= equal;
		}
		
		//3.get newindex and go to new array
		int[] cout = new int[src.length];
		for(int i = 0; i < cout.length; i++){
			int key = src[i];
			cout[count[key]++] = src[i];
		}
		
		for(int i = 0; i < cout.length; i++){
			System.out.println(cout[i]);
		}
	}
	
	@Test
	public void testgeneral(){
		int[] src = new int[]{2343, 5283, 3244, 1235, 3824,2850,3753};
		
		int div = 1;
		int len = src.length;
		int[] cout = new int[len];
		
		
		//key domain int counting sort is most important concept and it should be known before we do the counting sort
		while(div < 10000){
			
			
			int[] count = new int[10];
			//1.counting numbers
			for(int i = 0; i <  len; i++){
				int key = src[i]/div % 10;//get only the last digit and sort it
				count[key]++;
			}
			
			//2. less and index
			int lessandequal = len;
			for(int i = count.length-1; i>=0; i--){
				int equal = count[i];
				count[i] = lessandequal - equal;
				lessandequal -= equal;
			}
			
			//3. pick new index and go into new array
			for(int i = 0; i < len; i++){
				int key = src[i]/div%10;  //this is how we get one digit and base on one digit and we sort it.
				cout[count[key]++] = src[i];
			}
			
			div *= 10;
		}
		
		for(int i = 0; i  < len; i++){
			System.out.println(cout[i]);
		}
		
	}
	
}
