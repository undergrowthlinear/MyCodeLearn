package mylearncode.undergrowth.algorithm;

public class PathSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution=new Solution();
		TreeNode root=new TreeNode(1);
		TreeNode left1=new TreeNode(1);
		TreeNode right1=new TreeNode(1);
		TreeNode left11=new TreeNode(16);
		TreeNode left111=new TreeNode(1);
		root.left=left1;
		root.right=right1;
		left1.left=left11;
		left11.left=left111;
		System.out.println(solution.hasPathSum(root, 19));
		System.out.println(solution.hasPathSum(root, 18));
	}

	
	/**
	 * 
	 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum. 
		For example:
		 Given the below binary tree and sum = 22,               5
		             / \
		            4   8
		           /   / \
		          11  13  4
		         /  \      \
		        7    2      1
		
		
		return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
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
	    public boolean hasPathSum(TreeNode root, int sum) {
	        if(root==null) return false;  //�п��ܵ����ڵ����
	        if(root.left==null&&root.right==null){ //��ʾ����Ҷ�ӽڵ�
	        	return root.val==sum;
	        }
	        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
	    }
	}
}
