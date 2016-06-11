package mylearncode.undergrowth.algorithm;

import java.util.Arrays;

public class ContainsDuplicate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution=new Solution();
		int[] nums=new int[10];
		for (int i = 0; i < nums.length; i++) {
			nums[i]=(int)(Math.floor(Math.random()*100));
		}
		System.out.println(Arrays.toString(nums));
		System.out.println(solution.containsDuplicate(nums));
	}

	/**
	 * Given an array of integers, find if the array contains any duplicates.
	 * Your function should return true if any value appears at least twice in
	 * the array, and it should return false if every element is distinct.
	 * 
	 * ������ Ȼ�����
	 * 
	 * @author u1
	 * 
	 */
	public static class Solution {
		public boolean containsDuplicate(int[] nums) {
			if(nums==null) return false;
			if(nums.length==1 || nums.length==0) return false;
			//������
			Arrays.sort(nums);
			System.out.println(Arrays.toString(nums));
			//Ȼ������Ƿ����ظ�Ԫ��
			int preNum=nums[0];
			for (int i = 1; i < nums.length; i++) {
				if(preNum==nums[i]) return true;
				preNum=nums[i];
			}
			return false;
		}
	}
}
