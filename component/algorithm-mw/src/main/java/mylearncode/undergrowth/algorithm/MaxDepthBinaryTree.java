package mylearncode.undergrowth.algorithm;

import mylearncode.undergrowth.algorithm.MinDepthBinaryTree.Solution;
import mylearncode.undergrowth.algorithm.MinDepthBinaryTree.TreeNode;

public class MaxDepthBinaryTree {

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
		/*root.right=right1;
		left1.left=left11;
		left1.right=right11;
		left11.left=left111;*/
		Solution solution=new Solution();
		System.out.println(solution.maxDepth(root));
	}

	
	/**
	 * 
	 * Given a binary tree, find its maximum depth.

	The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

	 * 
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */
	public static class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
		  }
	
	public static class Solution {
	    public int maxDepth(TreeNode root) {
	        if(root==null) return 0;
	        if(root.left==null&&root.right==null) return 1;
	        return Math.max(1+maxDepth(root.left), 1+maxDepth(root.right));
	    }
	}
}
