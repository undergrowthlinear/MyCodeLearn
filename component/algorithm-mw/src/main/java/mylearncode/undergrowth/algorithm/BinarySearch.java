package mylearncode.undergrowth.algorithm;

import java.util.Arrays;

public class BinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution = new Solution();
		int[] nums = new int[100];
		for (int i = 0; i < 100; i++) {
			nums[i] = (int) (Math.floor(Math.random() * 100));
		}
		Arrays.sort(nums);
		System.out.println(Arrays.toString(nums));
		System.out.println(solution.binarySearch(nums,111));
		System.out.println(solution.binarySearch(nums,111,0,nums.length-1));
		System.out.println(solution.binarySearch(nums,nums[80]));
		System.out.println(solution.binarySearch(nums,nums[80],0,nums.length-1));
		System.out.println(solution.BinSearch(nums,0,nums.length-1,nums[80]));
		System.out.println(solution.binarySearchSet(nums,nums[80],0,nums.length-1));
		System.out.println(Arrays.toString(nums));
		
	}

	public static class Solution {

		/**
		 * 二分查找:在有序数组中,利用对分查找
		 * 
		 * @param nums
		 */
		private int binarySearch(int[] nums, int findNum) {
			// TODO Auto-generated method stub
			int low = 0;
			int high = nums.length - 1;
			int middle = 0;
			while (low <= high) {
				middle = (low + high) / 2;
				if (findNum == nums[middle]) {
					return middle;
				} else if (findNum < nums[middle]) {
					high = middle - 1;
				} else {
					low = middle + 1;
				}
			}
			return -1;
		}
		
		public int binarySearch(int[] nums, int findNum, int low, int high) {
			int middle = (low + high) / 2;
			if (findNum < nums[low] || findNum > nums[high] || low > high) {
				return -1;
			}
			if (findNum < nums[middle])
				return binarySearch(nums, findNum, low, middle - 1);
			else if (findNum > nums[middle])
				return binarySearch(nums, findNum, middle + 1, high);
			else
				return middle;

		}
		
		/**
		 * 递归方法实现二分查找法.
		 * @param Array数组
		 * @param low 数组第一位置
		 * @param high 最高
		 * @param key 要查找的值.
		 * @return 返回值.
		 */
		int BinSearch(int Array[],int low,int high,int key)
		{
			System.out.println(Arrays.toString(Array)+",key:"+key+",low:"+low+",high:"+high);
			if (low<=high)
			{
				int mid = (low+high)/2;
				if(key == Array[mid])
					return mid;
				else if(key<Array[mid])
					//移动low和high
					return BinSearch(Array,low,mid-1,key);
				else if(key>Array[mid])
					return BinSearch(Array,mid+1,high,key);
			}
			else
				return -1;
			return -1;
		}
		
		public int binarySearchSet(int[] dataset, int data, int beginIndex, int endIndex) {
			int midIndex = (beginIndex + endIndex) / 2;
			if (data < dataset[beginIndex] || data > dataset[endIndex] || beginIndex > endIndex) {
				return -1;
			}
			if (data < dataset[midIndex]) {
				return binarySearchSet(dataset, data, beginIndex, midIndex - 1);
			} else if (data > dataset[midIndex]) {
				return binarySearchSet(dataset, data, midIndex + 1, endIndex);
			} else {
				return midIndex;
			}

		}
	}

}
