package mylearncode.undergrowth.algorithm;

import mylearncode.undergrowth.algorithm.BinaryTreeLevelOrderTraversal.TreeNode;

public class SameTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root_p=new TreeNode(0);
		TreeNode left1_p=new TreeNode(1);
		TreeNode right1_p=new TreeNode(2);
		TreeNode left11_p=new TreeNode(11);
		TreeNode right11_p=new TreeNode(22);
		//TreeNode left111=new TreeNode(111);
		root_p.left=left1_p;
		root_p.right=right1_p;
		left1_p.left=left11_p;
		left1_p.right=right11_p;
		
		TreeNode root_q=new TreeNode(0);
		TreeNode left1_q=new TreeNode(1);
		TreeNode right1_q=new TreeNode(2);
		TreeNode left11_q=new TreeNode(11);
		TreeNode right11_q=new TreeNode(33);
		//TreeNode left111=new TreeNode(111);
		root_q.left=left1_q;
		root_q.right=right1_q;
		left1_q.left=left11_q;
		left1_q.right=right11_q;
		
		Solution solution=new Solution();
		System.out.println(solution.isSameTree(root_p, root_q));
	}

	/**
	 * Given two binary trees, write a function to check if they are equal or
	 * not.
	 * 
	 * Two binary trees are considered equal if they are structurally identical
	 * and the nodes have the same value.
	 * 
	 * 
	 * Definition for a binary tree node. public class TreeNode { int val;
	 * TreeNode left; TreeNode right; TreeNode(int x) { val = x; } }
	 * 
	 * �ж������������Ƿ���ͬ
	 *   �������Ľṹ�ͽڵ��ֵ����Ҫһֱ
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
		public boolean isSameTree(TreeNode p, TreeNode q) {
			if(p==null&&q==null) return true;
			if(p==null&&q!=null) return false;
			if(p!=null&&q==null) return false;
			if(p.val!=q.val) return false;
			else {
				return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
			}
		}
		//ʹ������ķ�ʽ�������Ŷ�����
		public boolean isSameTree2(TreeNode p,TreeNode q){
			//ʹ��LinkedList
			return false;
		}
	}

}
