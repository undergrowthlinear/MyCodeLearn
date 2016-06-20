package mylearncode.undergrowth.algorithm;

public class ReverseBits {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution = new Solution();
		System.out.println(solution.reverseBits(43261596));
		System.out.println(solution.reverseBitsAndOr(43261596));
	}

	/**
	 * Reverse bits of a given 32 bits unsigned integer.
	 * 
	 * For example, given input 43261596 (represented in binary as
	 * 00000010100101000001111010011100), return 964176192 (represented in
	 * binary as 00111001011110000010100101000000).
	 * 
	 * 反转32整数的二进制位数
	 * 
	 * @author u1
	 * 
	 */
	public static class Solution {
		// you need treat n as an unsigned value
		public int reverseBits(int n) {
			return Integer.reverse(n);
		}

		/**
		 * 与和或操作 将传递数不断向右移动 获取其最后一位给结果数 让结果数不断左移 即可
		 * 
		 * @param n
		 * @return
		 */
		public int reverseBitsAndOr(int n) {
			int result = 0;
			int count = 0;
			while (count < 32) {
				// 左移结果数
				result <<= 1;
				// 获取n的最后一位 给结果数
				result |= (n & 1);
				n = n >> 1;
				count++;
			}
			return result;
		}

	}
}
