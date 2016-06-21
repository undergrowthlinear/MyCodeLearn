package mylearncode.undergrowth.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mylearncode.undergrowth.algorithm.ContainsDuplicate.Solution;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* 按照 这个---->[10,12,13,8,19]
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月20日
* @version 1.0.0
 */
public class InsertSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution = new Solution();
		List<Integer> nums = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++) {
			nums.add((int) (Math.floor(Math.random() * 100)));
		}
		System.out.println(nums);
		solution.insertSort(nums);
		System.out.println(nums);
	}

	public static class Solution {
		/**
		 * 插入排序 将待排序的数据插入到已排序的数组中
		 * 
		 * @param list
		 */
		private void insertSort(List<Integer> list) {
			// TODO Auto-generated method stub
			int tmp = 0, j = 0;
			int num = list.size();
			for (int i = 1; i < num; i++) {
				if (list.get(i - 1) > list.get(i)) {
					// 存储要被替换的值
					tmp = list.get(i);
					j = i;
					while (j > 0 && list.get(j - 1) > tmp) {
						list.set(j, list.get(j - 1));
						j--;
					}
					list.set(j, tmp);
				}
				//System.out.println(i + "次排序," + list);
			}
		}
	}

}
