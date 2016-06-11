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
     Solution solution=new Solution();
     System.out.println(solution.isHappy(19));
     System.out.println(solution.isHappy(12));
	}

	/**
	�������ж�-->
	����һ�����������������λ���ϵ�����ƽ������ӣ��ٴεݹ���ƽ���ͣ�ֱ��ƽ����Ϊ1�����������Ϊ�Ҹ�����
	  ��ƽ���ͳ����ظ� ��Ϊ�Ҹ���
	**/

	public static class Solution {
	    public boolean isHappy(int n) {
	        //�ж��Ƿ�Ϊ����
	        if(n<1) return false;
	        //�ж��Ƿ����1
	        if(n==1) return true;
	        //���ڴ��ƽ���͵Ľ���� �������ظ��Ľ�� ��ʾ�������������Ҹ���
	        Set<Integer> resultSet=new HashSet<Integer>();
	        //�������ԵĽ�� ����������Χʱ  �����в���
	        while(n<=Integer.MAX_VALUE){
	            n=getBitSum(n);
	            //�ж��Ƿ�Ϊ1
	            if(n==1) return true;
	            //�ж��Ƿ��������ֹ�
	            else if(resultSet.contains(n)) return false;
	            //û�г��ֹ� ����ӵ��������
	            else resultSet.add(n);
	        }
	        return false;
	    }
	    
	    
	    //�������λ���ϵ�ƽ���� 
	    public int getBitSum(int n){
	        int sum=0;
	        while(n!=0){
	            sum+=Math.pow(n%10,2);
	            n=n/10;
	        }
	        return sum;
	    }
	}
	
}
