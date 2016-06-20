package mylearncode.undergrowth.algorithm;

public class HammingWeight {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution = new Solution();
		System.out.println("测试数为:" + Integer.MAX_VALUE);
		System.out.println(solution.hammingWeight(Integer.MAX_VALUE));
		System.out.println(solution.hammingWeightXor(Integer.MAX_VALUE));
		System.out.println(solution.hammingWeightDivide(Integer.MAX_VALUE));
		System.out.println("测试数为:" + 11);
		System.out.println(solution.hammingWeight(11));
		System.out.println(solution.hammingWeightXor(11));
		System.out.println(solution.hammingWeightDivide(11));

	}

	/**
	 * Write a function that takes an unsigned integer and returns the number of
	 * ’1' bits it has (also known as the Hamming weight).
	 * 
	 * For example, the 32-bit integer ’11' has binary representation
	 * 00000000000000000000000000001011, so the function should return 3.
	 * 
	 * 汉明重量--返回一个32位整数中的1的个数 11---1011--返回3
	 * 
	 * @author u1
	 * 
	 */
	public static class Solution {
		// you need to treat n as an unsigned value
		/**
		 * 
		 * @param n
		 * @return
		 */
		public int hammingWeight(int n) {
			return Integer.bitCount(n);
		}

		/**
		 * 使用异或运算、移位运算
		 * 
		 * @param n
		 * @return
		 */
		public int hammingWeightXor(long n) {
			long start = System.currentTimeMillis();
			int num = 0;
			// 使用移位和异或运算
			while (n != 0) {
				if (n - (n ^ 1) == 1) {// 表示最后一位为1
					num++;
				}
				// 移出最后一位
				n = n >> 1;
			}
			System.out.println("使用移位、异或，总共用时:" + String.valueOf(System.currentTimeMillis() - start));
			return num;
		}

		/**
		 * 使用除余法
		 * 
		 * @param n
		 * @return
		 */
		public int hammingWeightDivide(long n) {
			long start = System.currentTimeMillis();
			int num = 0;
			// 使用除余法
			while (n != 0) {
				if (n % 2 == 1) {// 表示最后一位为1
					num++;
				}
				//
				n = n / 2;
			}
			System.out.println("使用除余法，总共用时:" + String.valueOf(System.currentTimeMillis() - start));
			return num;
		}
	}
}
