package mylearncode.undergrowth.algorithm;

public class HammingWeight {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution = new Solution();
		System.out.println("������Ϊ:" + Integer.MAX_VALUE);
		System.out.println(solution.hammingWeight(Integer.MAX_VALUE));
		System.out.println(solution.hammingWeightDivide(Integer.MAX_VALUE));
		

	}

	/**
	 * Write a function that takes an unsigned integer and returns the number of
	 * ��1' bits it has (also known as the Hamming weight).
	 * 
	 * For example, the 32-bit integer ��11' has binary representation
	 * 00000000000000000000000000001011, so the function should return 3.
	 * 
	 * ��������--����һ��32λ�����е�1�ĸ��� 11---1011--����3
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
		 * ʹ��������㡢��λ����
		 * 
		 * @param n
		 * @return
		 */
		public int hammingWeightXor(long n) {
			long start = System.currentTimeMillis();
			int num = 0;
			// ʹ����λ���������
			while (n != 0) {
				if (n - (n ^ 1) == 1) {// ��ʾ���һλΪ1
					num++;
				}
				// �Ƴ����һλ
				n = n >> 1;
			}
			System.out.println("ʹ����λ������ܹ���ʱ:"
					+ String.valueOf(System.currentTimeMillis() - start));
			return num;
		}

		/**
		 * ʹ�ó��෨
		 * 
		 * @param n
		 * @return
		 */
		public int hammingWeightDivide(long n) {
			long start = System.currentTimeMillis();
			int num = 0;
			// ʹ�ó��෨
			while (n != 0) {
				if (n % 2 == 1) {// ��ʾ���һλΪ1
					num++;
				}
				//
				n = n / 2;
			}
			System.out.println("ʹ�ó��෨���ܹ���ʱ:"
					+ String.valueOf(System.currentTimeMillis() - start));
			return num;
		}
	}
}
