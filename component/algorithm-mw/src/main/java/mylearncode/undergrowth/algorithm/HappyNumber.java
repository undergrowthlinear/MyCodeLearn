package mylearncode.undergrowth.algorithm;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution = new Solution();
		System.out.println(solution.isHappy(19));
		System.out.println(solution.isHappy(12));
	}

	/**
	 * 辛福数判断--> 任意一个正整数，将其各个位数上的数字平方后相加，再次递归其平方和，直至平方和为1，则此正整数为幸福数， 若平方和出现重复
	 * 则不为幸福数
	 **/

	public static class Solution {
		public boolean isHappy(int n) {
			// 判断是否为负数
			if (n < 1)
				return false;
			// 判断是否等于1
			if (n == 1)
				return true;
			// 用于存放平方和的结果集 若出现重复的结果 表示此正整数不是幸福数
			Set<Integer> resultSet = new HashSet<Integer>();
			// 当待测试的结果 超过整数范围时 不进行测试
			while (n <= Integer.MAX_VALUE) {
				n = getBitSum(n);
				// 判断是否为1
				if (n == 1)
					return true;
				// 判断是否曾经出现过
				else if (resultSet.contains(n))
					return false;
				// 没有出现过 则添加到结果集中
				else
					resultSet.add(n);
			}
			return false;
		}

		// 计算各个位数上的平方和
		public int getBitSum(int n) {
			int sum = 0;
			while (n != 0) {
				sum += Math.pow(n % 10, 2);
				n = n / 10;
			}
			return sum;
		}
	}

}
