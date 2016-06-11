package mylearncode.undergrowth.algorithm;

import java.lang.reflect.Array;
import java.util.Arrays;

public class RotateArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
		Solution solution = new Solution();
		System.out.println(Arrays.toString(nums));
		solution.rotate(nums, 3);
		System.out.println(Arrays.toString(nums));
	}

	/**
	 * Rotate an array of n elements to the right by k steps.
	 * 
	 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated
	 * to [5,6,7,1,2,3,4].
	 * 
	 * ��ת����--��ָ��λ�ÿ�ʼ ��תָ������
	 * 
	 * @author u1
	 * 
	 */
	public static class Solution {
		/**
		 * �ȷ�תǰn-k�� �ٷ�תʣ��k�� Ȼ��һ��ת
		 * 
		 * @param nums
		 * @param k
		 */
		public void rotate(int[] nums, int k) {
			int length=nums.length;
			k=k%length;
			reverse(nums,0,length-k-1);
			reverse(nums,length-k,length-1);
			reverse(nums,0,length-1);
		}

		/**
		 * ����������ָ��λ�õ�ֵ
		 * @param nums
		 * @param i
		 * @param j
		 */
		private void reverse(int[] nums, int i, int j) {
			// TODO Auto-generated method stub
			int tmp;
			for (int k = i; k < j; k++,j--) {
				tmp=nums[k];
				nums[k]=nums[j];
				nums[j]=tmp;
			}
		}
	}
}
