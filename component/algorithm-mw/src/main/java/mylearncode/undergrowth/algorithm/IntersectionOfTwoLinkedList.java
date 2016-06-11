package mylearncode.undergrowth.algorithm;

public class IntersectionOfTwoLinkedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Write a program to find the node at which the intersection of two singly
	 * linked lists begins.
	 * 
	 * 
	 * For example, the following two linked lists: A: a1 → a2 ↘ c1 → c2 → c3 ↗
	 * B: b1 → b2 → b3
	 * 
	 * 
	 * begin to intersect at node c1.
	 * 
	 * 
	 * Notes: •If the two linked lists have no intersection at all, return null.
	 * •The linked lists must retain their original structure after the function
	 * returns. •You may assume there are no cycles anywhere in the entire
	 * linked structure. •Your code should preferably run in O(n) time and use
	 * only O(1) memory.
	 * 
	 * Definition for singly-linked list. public class ListNode { int val;
	 * ListNode next; ListNode(int x) { val = x; next = null; } }
	 * 
	 * 
	 * 查找两个链表相交的结点
	 * 先算出两个链表的长度 如果长度不相等 则让长的先走两者链表的差值步 然后长的链表和短的链表一起走 找到相等的结点 返回即可
	 */
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public static class Solution {
		public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
			ListNode listNodeA=headA;
			ListNode listNodeB=headB;
			//记录链表的长度
			int lengthA=0;
			int lengthB=0;
			//找出链表A的长度
			while(listNodeA!=null){
				lengthA++;
				listNodeA=listNodeA.next;
			}
			//找出链表B的长度
			while(listNodeB!=null){
				lengthB++;
				listNodeB=listNodeB.next;
			}
			listNodeA=headA;
			listNodeB=headB;
			//让长的链表先走 使长的链表与短的链表保持一致的起点
			if(lengthA>lengthB){//让链表A先走
				for(int i=0;i<lengthA-lengthB;i++){
					listNodeA=listNodeA.next;
				}
			}else if(lengthA<lengthB){//让链表B先走
				for(int i=0;i<lengthB-lengthA;i++){
					listNodeB=listNodeB.next;
				}
			}
			//比较两个链表的相同结点
			while(listNodeA!=null){
				if(listNodeA.val==listNodeB.val&&listNodeA.next==listNodeB.next) return listNodeA;
				listNodeA=listNodeA.next;
				listNodeB=listNodeB.next;
			}
			return null;
			
		}
	}
}
