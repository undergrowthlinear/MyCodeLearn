package mylearncode.undergrowth.algorithm;

public class HouseRubber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution=new Solution();
		int[] nums={12,45,78,10,100,456,12,234};
		System.out.println(solution.rob(nums));;
	}

	/**
	 * You are a professional robber planning to rob houses along a street. Each
	 * house has a certain amount of money stashed, the only constraint stopping
	 * you from robbing each of them is that adjacent houses have security
	 * system connected and it will automatically contact the police if two
	 * adjacent houses were broken into on the same night.
	 * 
	 * Given a list of non-negative integers representing the amount of money of
	 * each house, determine the maximum amount of money you can rob tonight
	 * without alerting the police.
	 * �Ƚ��Ƿ����ٵ�N�����ӵ����ֵ 
	 * ����Ϊ--maxMoney[N-1]
	 * ����Ϊ--maxMoney[N-2]+num[N]
	 * �ĸ��󣬾�ִ���ĸ�
	 * maxMoney[N]=max(maxMoney[N-2]+num[N],maxMoney[N-1]);
	 */
	public static class Solution {
		public int rob(int[] nums) {
			int n=nums.length;
			//������ٵ����ֵ
			int[] maxMoney=new int[n];
			if(n==0) return 0;
			else if(n==1) return nums[0];
			else {
				maxMoney[0]=nums[0];
				maxMoney[1]=Math.max(nums[0], nums[1]);
				for(int i=2;i<n;i++){
					maxMoney[i]=Math.max(maxMoney[i-2]+nums[i], maxMoney[i-1]);
				}
			}
			return maxMoney[n-1];
		}
	}
}
