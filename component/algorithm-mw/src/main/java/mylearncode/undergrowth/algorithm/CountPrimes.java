package mylearncode.undergrowth.algorithm;

public class CountPrimes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution=new Solution();
		System.out.println(solution.countPrimes(3));
		System.out.println(solution.countPrimes(4));
		System.out.println(solution.countPrimes(5));
		System.out.println(solution.countPrimes(499979));
		System.out.println(solution.countPrimes(499979/2));
		System.out.println(solution.countPrimes(1500000));
		System.out.println(solution.countPrimes2(1500000));
		
	}

	/**
	 * Description:
	 * 1141565 507
	 * Count the number of prime numbers less than a non-negative number, n
	 */
	public static class Solution {
		/**
		 * �˷�����ʱ�临�Ӷ�̫�� Ϊo(n*n)
		 * @param n
		 * @return
		 */
		public int countPrimes(int n) {
			long start=System.currentTimeMillis();
			int count = 0;
			if(n<2) return count;
			for(int i=2;i<n;i++)
				if(isPrime(i))	count++;
			System.out.println("ʹ�ã�"+String.valueOf(System.currentTimeMillis()-start));
			return count;
		}
		
		/**
		 * �˷�����ʱ�临�Ӷ�Ϊ o(n) ������һ�ֽ�����һ������
		 * �����ǰ��������  �򽫳�������������б�����Ϊ������
		 * @param n
		 * @return
		 */
		public int countPrimes2(int n) {
			long start=System.currentTimeMillis();
			if(n<2) return 0;
			//��ȥ1�ͱ���
			int count = n-2;
			//��ʼ������������־
			boolean[] primeFlag=new boolean[n];
			for(int i=2;i<n;i++){
				primeFlag[i]=true;
			}
			//�����ǰ��������  �򽫳�������������б�����Ϊ������
			for(int i=2;i<Math.sqrt(n);i++)
				if(primeFlag[i]) {
					for(int j=2;j*i<n;j++)
					{
						if(primeFlag[j*i]){ //ȥ���ظ������
							primeFlag[i*j]=false;
						    count--;
						}
					}
					
				}
			System.out.println("ʹ�ã�"+String.valueOf(System.currentTimeMillis()-start));
			return count;
		}
		
        /**
         * �ж��Ƿ�������
         * @param i
         * @return
         */
		private boolean isPrime(int n) {
			// TODO Auto-generated method stub
			for(int i=2;i<=Math.sqrt(n);i++)
				if(n%i==0) return false;
			return true;
		}
	}
}
