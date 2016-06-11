package mylearncode.undergrowth.algorithm;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangelII {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution=new Solution();
		List<Integer> result=solution.getRow(3);
		for (Integer integer : result) {
			System.out.print(integer+" ");
		}
	}

	/**
	 * Given an index k, return the kth row of the Pascal's triangle.

		For example, given k = 3,
		 Return [1,3,3,1]. 
		
		Note:
		 Could you optimize your algorithm to use only O(k) extra space? 

	 * @author u1
	 *
	 */
	public static  class Solution {
	    public List<Integer> getRow(int rowIndex) {
	        List<Integer> result=new ArrayList<Integer>();
	        if(rowIndex<0) return result;
	        result.add(1);
	        for(int i=1;i<=rowIndex;i++){
	        	for(int j=result.size()-1;j>0;j--){//�Ӻ���ǰɨ��
	        		result.set(j, result.get(j)+result.get(j-1));
	        	}
	        	result.add(1);//����е����һ�� 1
	        }
	        return result;
	    }
	}
}
