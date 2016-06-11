package mylearncode.undergrowth.algorithm;

import mylearncode.undergrowth.algorithm.BinaryTreeLevelOrderTraversal.TreeNode;

public class SymmetricTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(0);
		TreeNode left1 = new TreeNode(1);
		TreeNode right1 = new TreeNode(1);
		TreeNode left11 = new TreeNode(11);
		TreeNode right11 = new TreeNode(22);
		TreeNode left111 = new TreeNode(22);
		root.left = left1;
		root.right = right1;
		left1.left = left11;
		left1.right = right11;
		// left11.left=left111;
		Solution solution = new Solution();
		System.out.println(solution.isSymmetric(root));
	}

	/**
	 * Given a binary tree, check whether it is a mirror of itself (ie,
	 * symmetric around its center).
	 * 
	 * For example, this binary tree is symmetric: 1 / \ 2 2 / \ / \ 3 4 4 3
	 * 
	 * 
	 * 
	 * But the following is not:
	 * 
	 * 1 / \ 2 2 \ \ 3 3
	 * 
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
		public boolean isSymmetric(TreeNode root) {  
		    if(root == null)  
		        return true;  
		    return helper(root.left, root.right);  
		}  
		public boolean helper(TreeNode root1, TreeNode root2)  
		{  
		    if(root1 == null && root2 == null)  
		        return true;  
		    if(root1 == null || root2 == null)  
		        return false;  
		    if(root1.val != root2.val)  
		        return false;  
		   return helper(root1.left,root2.right) && helper(root1.right,root2.left);  
		}  
		
		public boolean isSymmetric2(TreeNode root) {
			if (root == null)
				return true;
			//Ҷ�ӽڵ�
			if (root.left == null && root.right == null)
				return true;
			//��һ�����
			if(root.left==null || root.right==null) return false;
			if(root.left.val!=root.right.val) return false;
			return isSymmetric(root.left) && isSymmetric(root.right);
		}
	}

}
