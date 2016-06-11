package mylearncode.undergrowth.algorithm;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution=new Solution();
		System.out.println(solution.isIsomorphic("egg", "add"));
		System.out.println(solution.isIsomorphic("foo", "bar"));
		System.out.println(solution.isIsomorphic("paper", "title"));
		System.out.println(solution.isIsomorphic("ab", "aa"));
	}

	/**
	 * ͬ���ַ��� -->��ͬ���ȵ��ַ��� ͬһ�ַ�����ӳ��ʱ ���ܳ��ֲ�ͬ�����
	 * Given two strings s and t, determine if they are isomorphic.
	 * 
	 * Two strings are isomorphic if the characters in s can be replaced to get
	 * t.
	 * 
	 * All occurrences of a character must be replaced with another character
	 * while preserving the order of characters. No two characters may map to
	 * the same character but a character may map to itself.
	 * 
	 * For example, Given "egg", "add", return true.
	 * 
	 * Given "foo", "bar", return false.
	 * 
	 * Given "paper", "title", return true.
	 * 
	 * @author u1
	 * 
	 */
	public static class Solution {
		public boolean isIsomorphic(String s, String t) {
			if(s==null || t==null) return false;
			if(s.length()!=t.length()) return false;
			Map<Character, Character> sMapt=new HashMap<Character, Character>();
			Map<Character, Character> tMaps=new HashMap<Character, Character>();
			for (int i = 0; i < s.length(); i++) {
				char sBit=s.charAt(i),tBit=t.charAt(i);
				//���s�е��ַ�ӳ�䵽t��
				if(!sMapt.containsKey(sBit)) sMapt.put(sBit, tBit);
				//������ӳ��� ���ж�ӳ����ַ��Ƿ���ͬ
				else if(sMapt.get(sBit)!=tBit) return false;
				
				//ͬ�����ж�t��s��ӳ��
				if(!tMaps.containsKey(tBit)) tMaps.put(tBit, sBit);
				else if(tMaps.get(tBit)!=sBit) return false;
			}
			return true;
		}
	}
}
