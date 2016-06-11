package mylearncode.undergrowth.algorithm;

public class CountAndSay {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution=new Solution();
		for(int i=1;i<=10;i++)
		System.out.println(solution.countAndSay(i));
	}

	/**
	 * The count-and-say sequence is the sequence of integers beginning as
	 * follows: 1, 11, 21, 1211, 111221, ...
	 * 
	 * 1 is read off as "one 1" or 11. 11 is read off as "two 1s" or 21. 21 is
	 * read off as "one 2, then one 1" or 1211. Given an integer n, generate the
	 * nth sequence.
	 * 
	 * ���n������ ������
	 * 1---> 1   1��1---11
	 * 2--->11   2��1---21
	 * 3--->21   --һ��2 һ��1 1211
	 * 4--->1211 ---һ��1 һ��2 2��1 ---> 111221
	 * n--->���ֵĸ��������ֵ����     
	 * 
	 * Note: The sequence of integers will be represented as a string.
	 * 
	 * @author Administrator
	 * 
	 */
	public static class Solution {
		public String countAndSay(int n) {
			String s="1"; //��¼��ǰ���ֵ���һ���������ֵ����� �����ֵĸ������������
			if(n==1) return s;
			int count=0;
			int i;
			StringBuilder builder=new StringBuilder();
			while(++count<n) //��ѡ����n ��Ϊn���� ��n-1�Ѿ������
			{
				builder.setLength(0);
				int numRepeat=1;
				//ͨ��ǰһ�����ֵ�����  ��������ֵ�����
				for(i=1;i<s.length();i++){
					//�����ַ������г����ظ��Ĵ���
					if(s.charAt(i)==s.charAt(i-1)) numRepeat++;
					else { //������ظ�
						builder.append(numRepeat).append(s.charAt(i-1));
						numRepeat=1;
					}
				}
				//�����굱ǰ���ֵ����к� ������һ������
				s=builder.append(numRepeat).append(s.charAt(i-1)).toString()	;
			}
			
			return s;
		}
	}
}
