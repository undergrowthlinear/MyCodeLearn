package mylearncode.undergrowth.algorithm;

import java.util.ArrayList;
import java.util.List;

public class MpSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution = new Solution();
		List<Integer> nums = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++) {
			nums.add((int) (Math.floor(Math.random() * 100)));
		}
		System.out.println(nums);
		solution.mpSort(nums);
		System.out.println(nums);
	}

	public static class Solution {
		/**
		 * 两个数两两比较,将较小的交互到最前面
		 * 
		 * @param list
		 */
		private void mpSort(List<Integer> list) {
			// TODO Auto-generated method stub
			int tmp = 0;
			int num = list.size();
			for (int i = 0; i < num; i++) {
				for (int j = i; j < num; j++) {
					if (list.get(i) > list.get(j)) {
						tmp = list.get(i);
						list.set(i, list.get(j));
						list.set(j, tmp);
					}
				}
				//System.out.println(i + "次排序," + list);
			}
		}
	}


}
