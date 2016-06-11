package mylearncode.undergrowth.algorithm;

public class CompareVersionNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution=new Solution();
		System.out.println(solution.compareVersion("1.2.0", "1.2"));
		System.out.println(solution.compareVersion("13.37", "1.2"));
		System.out.println(solution.compareVersion("1.1", "1.2"));
		System.out.println(solution.compareVersion("01", "1"));
		//System.out.println("1.2.0".split("\\.").length);
		System.out.println(solution.compareVersion2("1.2.0", "1.2"));
		System.out.println(solution.compareVersion2("13.37", "1.2"));
		System.out.println(solution.compareVersion2("1.1", "1.2"));
		System.out.println(solution.compareVersion2("01", "1"));
	}

	/**
	 * Compare two version numbers version1 and version2. If version1 > version2
	 * return 1, if version1 < version2 return -1, otherwise return 0.
	 * 
	 * You may assume that the version strings are non-empty and contain only
	 * digits and the . character. The . character does not represent a decimal
	 * point and is used to separate number sequences. For instance, 2.5 is not
	 * "two and a half" or "half way to version three", it is the fifth
	 * second-level revision of the second first-level revision.
	 * 
	 * Here is an example of version numbers ordering: 0.1 < 1.1 < 1.2 < 13.37
	 * 
	 * �Ƚϰ汾��
	 *  ��.��ǰ����������������ֱַ���бȽ� 
	 *  ��ע��һ��  1.0.0 �� 1.0 ����ȵ�
	 * 
	 * @author u1
	 * 
	 */
	public static class Solution {
		public int compareVersion(String version1, String version2) {
			String[] arrayVersion1=version1.split("\\.");
			String[] arrayVersion2=version2.split("\\.");
			int lenVersion1=arrayVersion1.length;
			int lenVersion2=arrayVersion2.length;
			int numVersion1,numVersion2;//��ʾ����ĵ�ǰԪ�ص�����
			//��ȡ�汾�� �ϳ��Ľ���ѭ���Ƚ�
			int lenCompare=Math.max(lenVersion1, lenVersion2);
			for (int i = 0; i < lenCompare; i++) {
				numVersion1=0;
				numVersion2=0;
				if(i<lenVersion1){ //��ʾ�ַ���1 �����ַ�
					numVersion1=Integer.valueOf(arrayVersion1[i]);
				}
				if(i<lenVersion2){ //��ʾ�ַ���2 �����ַ�
					numVersion2=Integer.valueOf(arrayVersion2[i]);
				}
				if(numVersion1>numVersion2) return 1;
				else if(numVersion1<numVersion2) return -1;
			}
			return 0;
		}
		
		public int compareVersion2(String version1, String version2) {
			String[] arrayVersion1=version1.split("\\.");
			String[] arrayVersion2=version2.split("\\.");
			int lenVersion1=arrayVersion1.length;
			int lenVersion2=arrayVersion2.length;
			int numVersion1,numVersion2;//��ʾ����ĵ�ǰԪ�ص�����
			//��ȡ�汾�� ��С�Ľ���ѭ���Ƚ�
			int lenCompare=Math.min(lenVersion1, lenVersion2);
			for (int i = 0; i < lenCompare; i++) {
				numVersion1=0;
				numVersion2=0;
				if(i<lenVersion1){ //��ʾ�ַ���1 �����ַ�
					numVersion1=Integer.valueOf(arrayVersion1[i]);
				}
				if(i<lenVersion2){ //��ʾ�ַ���2 �����ַ�
					numVersion2=Integer.valueOf(arrayVersion2[i]);
				}
				if(numVersion1>numVersion2) return 1;
				else if(numVersion1<numVersion2) return -1;
			}
			//�жϽϴ��ߵ�lenCompareλ �Ƿ�Ϊ0
			if(lenCompare==lenVersion1&&lenCompare!=lenVersion2&&!"0".equals(arrayVersion2[lenCompare])) return -1;
			if(lenCompare==lenVersion2&&lenCompare!=lenVersion1&&!"0".equals(arrayVersion1[lenCompare])) return 1;
			return 0;
		}
	}
}
