package mylearncode.undergrowth.algorithm;

public class ExcelSheetColumnNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution=new Solution();
		System.out.println(solution.titleToNumber("A"));
		System.out.println(solution.titleToNumber("Z"));
		System.out.println(solution.titleToNumber("AA"));
		System.out.println(solution.titleToNumber("AB"));
		System.out.println(solution.titleToNumber("BA"));
		System.out.println(solution.titleToNumber2("BA"));
	}

	/**
	 * Related to question Excel Sheet Column Title
	 * 
	 * Given a column title as appear in an Excel sheet, return its
	 * corresponding column number.
	 * 
	 * For example: A -> 1 B -> 2 C -> 3 ... Z -> 26 AA -> 27 AB -> 28
	 * 
	 * @author u1
	 * 
	 */
	public static class Solution {
		public int titleToNumber(String s) {
			int num=0;
		    int length=s.length();
			for (int i = 0; i <length ; i++) {
				num=num*26+(s.charAt(i)-64);
			}
			return num;
		}
		
		public int titleToNumber2(String s) {  
			       int tmp = 0;  
			        for(int i=0;i<s.length();i++){  
			           int in = s.charAt(i)-64;  
			          tmp = tmp*26+in;  
			        }  
			        return tmp;  
			    }  

	}
}
