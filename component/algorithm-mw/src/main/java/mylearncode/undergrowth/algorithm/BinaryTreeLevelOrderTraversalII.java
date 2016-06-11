package mylearncode.undergrowth.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import mylearncode.undergrowth.algorithm.BinaryTreeLevelOrderTraversal.TreeNode;

public class BinaryTreeLevelOrderTraversalII {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * Given a binary tree, return the bottom-up level order traversal of its
	 * nodes' values. (ie, from left to right, level by level from leaf to
	 * root).
	 * 
	 * For example: Given binary tree {3,9,20,#,#,15,7},
	 * 
	 * 3 / \ 9 20 / \ 15 7
	 * 
	 * 
	 * 
	 * return its bottom-up level order traversal as:
	 * 
	 * [ [15,7], [9,20], [3] ]
	 * 
	 * 
	 * 
	 * confused what "{1,#,2,3}" means? > read more on how binary tree is
	 * serialized on OJ.
	 * 
	 * Definition for a binary tree node. public class TreeNode { int val;
	 * TreeNode left; TreeNode right; TreeNode(int x) { val = x; } }
	 */

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static class Solution {
		public List<List<Integer>> levelOrder(TreeNode root) {
			List<List<Integer>> result = new ArrayList<List<Integer>>(); //��¼ÿһ��Ľ����
			if (root == null)
				return result;
			int curNum=0; //��¼��ǰ��Ľ�����
			int lastNum=1;//��¼��һ��Ľ��ĸ���
			//��¼��ǰ��Ľ���ֵ
			List<Integer> curVal=new ArrayList<Integer>();
			//��¼��ǰ����
			LinkedList<TreeNode> curNodeList=new LinkedList<TreeNode>();
			curNodeList.add(root);
			while(!curNodeList.isEmpty()){//�������в�Ľ��
				//��ȡ�����еĽ��
				TreeNode curNode=curNodeList.poll();
				curVal.add(curNode.val);
				lastNum--;
				//�жϽ��������Ƿ�Ϊ��
				if(curNode.left!=null){
					curNodeList.add(curNode.left);
					curNum++;
				}
				//�жϽ����ҽ���Ƿ�Ϊ��
				if(curNode.right!=null){
					curNodeList.add(curNode.right);
					curNum++;
				}
				//�жϵ�ǰ��Ľ���Ƿ�������
				if(lastNum==0){
					lastNum=curNum;
					curNum=0;
					result.add(curVal);//��ӵ������
					curVal=new ArrayList<Integer>();
					
				}
				
			}
			
			return result;
		}
	}
}
