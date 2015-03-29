package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class Aug07 {
	
	
	
	public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack();
        int prestart = -1;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }else{
                if (stack.empty()) {
                    prestart = i;//if right ( appear as seperate sign
                }else{
                    stack.pop();
                    
                    if(stack.empty()) {
                        maxLen = Math.max(maxLen, i + 1 - (prestart + 1));
                    }else{
                        maxLen = Math.max(maxLen, i + 1 - (stack.peek() + 1));
                    }
                }
            }
        }
        return maxLen;
    }
	
	@Test
	public void testsimplifyPath(){
		String tmp = simplifyPath("/a/b/c/..");
		System.out.println(tmp);
	}
	
	public String simplifyPath(String s){
        if( s == null || s.length() == 0) {
            return "";
        }
        LinkedList<String> stack = new LinkedList<String>();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if(i != 0 && s.charAt(i) == '/'){
                while(i < s.length() && s.charAt(i) == '/' ){
                    i++;
                }
                i--;
                String tmp = buf.toString();
                if(tmp.equals("/..")){
                    if(!stack.isEmpty()) {
                        stack.pop();
                    }
                }else if(!tmp.equals("/.") && !tmp.equals("")){
                    stack.push(tmp);
                }
                buf = new StringBuffer();
            }
            buf.append(s.charAt(i));
           
            if(i == s.length() - 1){
                String tmp = buf.toString();
                if(tmp.equals("/..")){
                    if(!stack.isEmpty()){
                        stack.pop();
                    }
                }else if(!tmp.equals("/.") && !tmp.equals("")){
                        stack.push(tmp);
                }
            }
        }
    
    
        StringBuffer res = new StringBuffer();
        
        Collections.reverse(stack);
        Iterator it = stack.iterator();
        while(it.hasNext()){
            String tmp = (String)it.next();
            res.append(tmp);
        }
        
        if(res.length() > 1){
        	if(res.charAt(res.length() - 1) == '/'){
        		res.deleteCharAt(res.length() - 1);
        	}
        }
        
        
        return res.toString();
    }
	
	public String reverseWords(String s){
        if(s == null || s.length() == 0){
            return "";
        }
        s = s.trim();
        Stack<Character> stack = new Stack();
        StringBuffer buf = new StringBuffer();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                while(i >= 0 && s.charAt(i) == ' ') {
                    i--;
                }
                i++;
                while(!stack.empty()){
                    buf.append(stack.pop());                    
                }
                if(i != 0){
                    buf.append(' ');
                }
            }else{
                if(i == 0){
                    buf.append(s.charAt(i));
                    while(!stack.empty()){
                        buf.append(stack.pop());
                    }
                }else{
                    stack.push(s.charAt(i));
                }             
            }
        }
        return buf.toString();
    }
	
	
	
	@Test
	public void testgrayCode(){
		List<Integer> res = grayCode(3);
		System.out.println(res);
	}
	
	public List<Integer> grayCode(int n){
		List<Integer> res = new ArrayList();
		
		if(n == 0){
			res.add(0);
			return res;
		}
		
		List<Integer> pre = grayCode(n - 1);
		List<Integer> cur = new ArrayList(pre);
		
		Collections.reverse(cur);
		
		for(int i = 0; i < cur.size(); i++) {
			cur.set(i, cur.get(i) + (int) Math.pow(2, n - 1));
		}
		res.addAll(pre);
		res.addAll(cur);
		return res;
	}
	
	
	@Test
	public void testpow(){
		double a = pow(2, -3);
		System.out.println(a);
	}
	
	public double pow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if( n > 0 ){
            int k = n / 2;
            return pow(x, k) * pow(x, n - k);
        }else{
            return 1 / pow(x, -n);
        }
    }
	
	
	

}
