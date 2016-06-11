package mylearncode.undergrowth.algorithm;

public class ExcelSheetColumnTilte {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution=new Solution();
		System.out.println(solution.convertToTitle(27));
	}

	/**
	 * Given a positive integer, return its corresponding column title as appear
	 * in an Excel sheet.
	 * 
	 * For example: 1 -> A 2 -> B 3 -> C ... 26 -> Z 27 -> AA 28 -> AB
	 * 
	 * @author u1
	 * 
	 */
	public static class Solution {
		public String convertToTitle(int n) {
			String builder="";
			while(n>0){
				builder=(char)((n-1)%26+'A')+builder;
				n=(n-1)/26;
			}
			return builder;
		}
	}
}
