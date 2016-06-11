package mylearncode.undergrowth.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PascalTriangle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution=new Solution();
		List<List<Integer>> resList=solution.generate(5);
		for (List<Integer> list : resList) {
			System.out.print("[");
			for (Integer integer : list) {
				System.out.print(integer+" ");
			}
			System.out.println("]");
		}
	}

	/**
	 * Given numRows, generate the first numRows of Pascal's triangle.
		For example, given numRows = 5,
		 Return 
		[
		     [1],
		    [1,1],
		   [1,2,1],
		  [1,3,3,1],
		 [1,4,6,4,1]
		]
		
		������ǡ���˹������
	 * @author u1
	 *
	 */
	public static class Solution {
	    public List<List<Integer>> generate(int numRows) {
	        List<List<Integer>> result=new ArrayList<List<Integer>>();
	        List<Integer> pre=new ArrayList<Integer>();
	        if(numRows<=0) return result;
	        pre.add(1);//��һ��
	        result.add(pre);
	        //Ȼ��ӵڶ��п�ʼ ���ϼ���ÿ�г���һ�����ֺ����һ�����ֵ�ֵ 
	        //���㷽ʽΪ ��ǰ�е�Ԫ��=��һ��ͬ��λ�ú�ͬ��λ��ǰһ������Ԫ��֮��
	        for(int i=2;i<=numRows;i++){
	        	List<Integer> cur=new ArrayList<Integer>();//��¼��ǰ�е�ֵ
	        	cur.add(1);//��ӵ�ǰ�еĵ�һ��Ԫ�ص�ֵ
	        	//���㵱ǰ������Ԫ�صĺ�
	        	for(int j=0;j<pre.size()-1;j++){
	        		cur.add(pre.get(j)+pre.get(j+1)); 
	        	}
	        	cur.add(1);//��ӵ�ǰ�е����һ��ֵ
	        	result.add(cur);//��ӵ�ǰ�е����ؼ�����
	        	pre=cur;
	        }
	        
	        return result;
	    }
	}
}
