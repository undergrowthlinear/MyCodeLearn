package mylearncode.undergrowth.algorithm;

public class ValidPalindrome {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution=new Solution();
		System.out.println(solution.isPalindrome("A man, a plan, a canal: Panama"));
		System.out.println(solution.isPalindrome("race a car"));
		System.out.println(solution.isPalindrome("1a2"));
	}
	
	/**
	�����ַ���-->
	    ������뷴�����һ��
	         ֻ�����ַ�(��ĸ������)��˳�� ���������ַ��ʹ�Сд
	    �ڽ�����֤ʱ ������������ ����������� �ıȽ�����λ���ϵ��ַ��Ƿ�һ�� ��һ�� ���˳�
	    һ�� �ͼ����Ƚ� ֱ����ߵ�λ�ó����ұ�
	**/
	public static class Solution {
	    public boolean isPalindrome(String s) {
	        if(s==null) return false;
	        if("".equals(s)) return true;
	        //ת��ΪСд
	        s=s.toLowerCase();
	        //���ҵ�λ��
	        int left=0,right=s.length()-1;
	        while(left<right){
	        	//���˵���Ч�ַ�
	            while(!isValid(s.charAt(left))){
	                left++;
	                if(left>=right) return true;
	            }
	            //���˵���Ч�ַ�
	            while(!isValid(s.charAt(right))){
	                right--;
	                if(left>=right) return true;
	            }
	            //����� ������
	            if(s.charAt(left)!=s.charAt(right)) return false;
	            //�����ж�
	            left++;
	            right--;
	        }
	        
	        return true;
	    }
	    //�ж��Ƿ����ַ�
	    public boolean isValid(char c){
	        if(c<='z' && c>='a') return true;
	        if(c<='9' && c>='0') return true;
	        return false;
	    }
	}

}
