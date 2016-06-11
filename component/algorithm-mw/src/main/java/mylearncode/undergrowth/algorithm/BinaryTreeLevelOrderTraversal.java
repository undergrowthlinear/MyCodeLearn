package mylearncode.undergrowth.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import mylearncode.undergrowth.algorithm.MinDepthBinaryTree.Solution;
import mylearncode.undergrowth.algorithm.MinDepthBinaryTree.TreeNode;

public class BinaryTreeLevelOrderTraversal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root=new TreeNode(0);
		TreeNode left1=new TreeNode(1);
		TreeNode right1=new TreeNode(2);
		TreeNode left11=new TreeNode(11);
		TreeNode right11=new TreeNode(22);
		TreeNode left111=new TreeNode(111);
		root.left=left1;
		root.right=right1;
		left1.left=left11;
		left1.right=right11;
		left11.left=left111;
		Solution solution=new Solution();
		List<List<Integer>> result=solution.levelOrder(root);
		for (List<Integer> list : result) {
			for (Integer integer : list) {
				System.out.print(integer+" ");
			}
			System.out.println();
		}
	}

	/**
	 * Definition for a binary tree node. public class TreeNode { int val;
	 * TreeNode left; TreeNode right; TreeNode(int x) { val = x; } }
	 * 
	 * Given a binary tree, return the level order traversal of its nodes'
	 * values. (ie, from left to right, level by level).
	 * 
	 * For example: Given binary tree {3,9,20,#,#,15,7},
	 * 
	 * 3 / \ 9 20 / \ 15 7
	 * 
	 * 
	 * 
	 * return its level order traversal as:
	 * 
	 * [ [3], [9,20], [15,7] ]
	 * 
	 * ʹ�ù�����ȱ���������
	 * 
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
			
			//��ת�����
			List<List<Integer>> resultReverse = new ArrayList<List<Integer>>();
			for (int i = result.size()-1; i >=0; i--) {
				resultReverse.add(result.get(i));
			}
			return resultReverse;
		}
	}
}
