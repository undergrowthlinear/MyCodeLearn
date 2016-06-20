package mylearncode.undergrowth.algorithm;

public class CountPrimes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution = new Solution();
		System.out.println(solution.countPrimes(3));
		System.out.println(solution.countPrimes(4));
		System.out.println(solution.countPrimes(5));
		System.out.println(solution.countPrimes(499979));
		System.out.println(solution.countPrimes(499979 / 2));
		System.out.println(solution.countPrimes(1500000));
		System.out.println(solution.countPrimes2(1500000));

	}

	/**
	 * Description: 1141565 507 Count the number of prime numbers less than a
	 * non-negative number, n
	 */
	public static class Solution {
		/**
		 * 此方法的时间复杂度太大 为o(n*n)
		 * 
		 * @param n
		 * @return
		 */
		public int countPrimes(int n) {
			long start = System.currentTimeMillis();
			int count = 0;
			if (n < 2)
				return count;
			for (int i = 2; i < n; i++)
				if (isPrime(i))
					count++;
			System.out.println("使用：" + String.valueOf(System.currentTimeMillis() - start));
			return count;
		}

		/**
		 * 此方法的时间复杂度为 o(n) 比上面一种降低了一个级别 如果当前数是素数 则将除本身以外的所有倍数置为非素数
		 * 
		 * @param n
		 * @return
		 */
		public int countPrimes2(int n) {
			long start = System.currentTimeMillis();
			if (n < 2)
				return 0;
			// 除去1和本身
			int count = n - 2;
			// 初始化设置素数标志
			boolean[] primeFlag = new boolean[n];
			for (int i = 2; i < n; i++) {
				primeFlag[i] = true;
			}
			// 如果当前数是素数 则将除本身以外的所有倍数置为非素数
			for (int i = 2; i < Math.sqrt(n); i++)
				if (primeFlag[i]) {
					for (int j = 2; j * i < n; j++) {
						if (primeFlag[j * i]) { // 去除重复的情况
							primeFlag[i * j] = false;
							count--;
						}
					}

				}
			System.out.println("使用：" + String.valueOf(System.currentTimeMillis() - start));
			return count;
		}

		/**
		 * 判断是否是素数
		 * 
		 * @param i
		 * @return
		 */
		private boolean isPrime(int n) {
			// TODO Auto-generated method stub
			for (int i = 2; i <= Math.sqrt(n); i++)
				if (n % i == 0)
					return false;
			return true;
		}
	}
}
