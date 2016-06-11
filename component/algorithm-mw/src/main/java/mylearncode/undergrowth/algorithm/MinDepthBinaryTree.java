package mylearncode.undergrowth.algorithm;

public class MinDepthBinaryTree {

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
		System.out.println(solution.minDepth(root));
	}

	
	/**
	 * 
	 * 
		Given a binary tree, find its minimum depth.
		
		The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

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
	    public int minDepth(TreeNode root) {
	        if(root==null) return 0;
	        if(root.left==null&&root.right==null) return 1;
	        int left=1+minDepth(root.left);
	        int right=1+minDepth(root.right);
	        //�ж��Ƿ�������Ҷ�ӽڵ�
	        if(left==1 || right==1) return Math.max(left, right);
	        return Math.min(left, right);
	    }
	}
}
