package mylearncode.undergrowth.algorithm;

public class MajorityElement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums={10,20,30,40,30,10,20,30,30,30,30};
		System.out.println(new Solution().majorityElement(nums));
	}

	/**
	 * Given an array of size n, find the majority element. The majority element
	 * is the element that appears more than ⌊ n/2 ⌋ times.
	 * 
	 * You may assume that the array is non-empty and the majority element
	 * always exist in the array.
	 * 
	 * 使用投票法--
	 * 	设置候选人和计数器
	 * 		将候选人与所有选票进行比较 如果一致 则计数器加一
	 * 		如果不是 则计数器减一
	 * 		如果计数器为0 表示此人已不能做候选人 需将其换为正在比较数
	 * 
	 * @author u1
	 * 
	 */
	public static class Solution {
		public int majorityElement(int[] nums) {
			if(nums.length==1) return nums[0];
			int candiate=nums[0];
			int count=1;
			for (int i = 1; i < nums.length; i++) {
				if(candiate==nums[i]) count++;
				else {
					count--;
					if(count==0) {
						candiate=nums[i];
						count=1;
					}
				}
			}
			return candiate;
		}
	}
}
