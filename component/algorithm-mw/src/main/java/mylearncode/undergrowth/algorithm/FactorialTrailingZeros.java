package mylearncode.undergrowth.algorithm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactorialTrailingZeros {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution = new Solution();/*
											 * System.out.println(5+"--"+"0�ĸ���"+
											 * solution.trailingZeroes(5));
											 * System
											 * .out.println(7+"--"+"0�ĸ���"+solution
											 * .trailingZeroes(7));
											 * System.out.println
											 * (125+"--"+"0�ĸ���"
											 * +solution.trailingZeroes(125));
											 * System
											 * .out.println(Integer.MAX_VALUE
											 * +"--"
											 * +"0�ĸ���"+solution.trailingZeroes
											 * (Integer.MAX_VALUE));
											 * System.out.println
											 * (Long.MAX_VALUE);
											 * System.out.println
											 * (Double.MAX_VALUE);
											 * System.out.println("ʹ�ó��෨");
											 * System
											 * .out.println(5+"--"+"0�ĸ���"+solution
											 * .trailingZeroesDivide(5));
											 * System.
											 * out.println(7+"--"+"0�ĸ���"+solution
											 * .trailingZeroesDivide(7));
											 * System.
											 * out.println(125+"--"+"0�ĸ���"
											 * +solution
											 * .trailingZeroesDivide(125));
											 * System
											 * .out.println(Integer.MAX_VALUE
											 * +"--"
											 * +"0�ĸ���"+solution.trailingZeroesDivide
											 * (Integer.MAX_VALUE));
											 * System.out.println
											 * ("ʹ��BigInteger����׳�--"
											 * +solution.fact
											 * (BigInteger.valueOf(100)));
											 */
		// System.out.println("ʹ��BigInteger����׳�--"+solution.fact(BigInteger.valueOf(200)));
		//System.out.println(Integer.MAX_VALUE);
		System.out.println(solution.factFor(19));;
		List<Integer> resuList=solution.fact(1000);
		for (int i = resuList.size()-1; i >=0 ; i--) {
			System.out.print(resuList.get(i));;
		}
		System.out.println();
		System.out.println("ʹ��BigInteger����׳�--"+solution.fact(BigInteger.valueOf(1000)));
	}

	/**
	 * Given an integer n, return the number of trailing zeroes in n!.
	 * 
	 * Note: Your solution should be in logarithmic time complexity.
	 * 
	 * ����n�Ľ׳���β��0�ĸ��� 1�������n�Ľ׳� Ȼ��ʹ�ó��෨ ���β��0�ĸ���--��n�ܴ�ʱ ����Integer�����
	 * 2��ʹ��������2��5����Ϊn!��β��Ϊ0�Ķ�����������2*5��ɵ� ֻ�ü���������2��5�ĸ��� ��֪��β���ж��ٸ�0��
	 * 5!--2*3*2*2*5--120--һ��0--һ��5 7��--2*3*2*2*5*2*3*7--һ��0--һ��5 �ڽ��н׳˼���ʱ
	 * 2�ĸ������Զ���5�ĸ��� ����ֻ�ü���n!��5�ĸ������� n!��5�ĸ���--floor(n/5)--���� ���Ƕ���25��125��625֮��Ķ���
	 * --�ֶ���ܶ��5 ���Զ��ڴ������ ��ʹ�� 25--floor(n/5)+floor(n/25)
	 * 125--floor(n/5)+floor(n/25)+floor(n/125)
	 * �������ռ���n!��β��0�ĸ���=floor(n/5)+floor(n/25)+floor(n/125)....+floor(n/k)
	 * kΪС�ڵ���n��5��ָ��
	 * 
	 * @author u1
	 * 
	 */
	public static class Solution {
		public int trailingZeroes(int n) {
			long start = System.currentTimeMillis();
			int base = 0;
			int num = 0; // ���ʹ��int ����� ��ʹ��long
			while (n != 0) {
				base = n / 5;
				num += base;
				n = base;
			}
			System.out.println("����ʱ��"
					+ String.valueOf(System.currentTimeMillis() - start));
			return num;
		}

		/**
		 * ʹ�ó��෨ ���ַ�ʽ���� ��̫�� �ͻ����
		 * 
		 * @param n
		 * @return
		 */
		public int trailingZeroesDivide(int n) {
			long start = System.currentTimeMillis();
			int num = 0;
			double sum = 1;
			for (int i = 1; i <= n; i++) {
				sum *= i;
			}
			System.out.println("sumֵΪ--" + sum);
			while (sum > 0) {
				if (sum % 10 == 0) {
					num++;
					sum = sum / 10;
				} else {
					break;
				}
			}
			System.out.println("����ʱ��"
					+ String.valueOf(System.currentTimeMillis() - start));
			return num;
		}

		/**
		 * ʹ��BigInteger
		 */
		public BigInteger fact(BigInteger val) {
			long start = System.currentTimeMillis();
			BigInteger result = BigInteger.ONE;
			for (BigInteger index = BigInteger.ONE; index.compareTo(val) < 1; index = index
					.add(BigInteger.ONE)) {
				result = result.multiply(index);
			}
			System.out.println("BigInteger����ʱ��-->"
					+ String.valueOf(System.currentTimeMillis() - start));
			return result;
		}

		public int factFor(int n) {
			int sum = 1;
			for (int i = 1; i <= n; i++) {
				sum *= i;
			}
			// System.out.println(sum);
			return sum;
		}

		/**
		 * ����׳�
		 */
		public List<Integer> fact(int n) {
			long start = System.currentTimeMillis();
			List<Integer> resuList = new ArrayList<Integer>();
			List<Integer> tmpList = new ArrayList<Integer>();
			Map<Integer, Integer> bitResultMap=new HashMap<Integer, Integer>();
			//��Ϊ19 ��ֱ�Ӽ��㷵��
			if (n <= 12) {
				resuList.add(factFor(n));
				return resuList;
			}
			int fact19=factFor(12);
			//������19 ��19��ÿһλ����������ֱ���� ����list�� Ȼ���ڽ��н�λ����
			//���19�׳˵�ÿһλ��
			tmpList.clear();
			fact19 = splitNum(tmpList, fact19);
			//�ֱ����
			for(int count=13;count<=n;count++){
				for(int i=0;i<tmpList.size();i++){
					bitResultMap.put(i, tmpList.get(i)*count);
				}
				tmpList.clear();
				//�����λ
				for (int i = 0; i < bitResultMap.size(); i++) {
					int value=bitResultMap.get(i);
					//��ȡ��λ
					if(value>10) { 
						if(bitResultMap.get(i+1)==null) bitResultMap.put(i+1,0);
						bitResultMap.put(i+1, bitResultMap.get(i+1)+value/10);
						bitResultMap.put(i, value%10);
					}
				}
				//�ָ�tmpֵ
				for (int i = 0; i < bitResultMap.size(); i++){
					tmpList.add(bitResultMap.get(i));
				}
				
				
			}
			resuList=tmpList;
			System.out.println("����ʱ��"
					+ String.valueOf(System.currentTimeMillis() - start));
			return resuList;
		}

		private int splitNum(List<Integer> tmpList, int fact19) {
			for(;fact19!=0;fact19=fact19/10){
				tmpList.add(fact19%10);
			}
			return fact19;
		}
	}
}
