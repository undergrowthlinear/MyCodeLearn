package mylearncode.undergrowth.algorithm;

public class ReverseLinkedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode listNode1=new ListNode(1);
		ListNode listNode2=new ListNode(2);
		ListNode listNode3=new ListNode(3);
		ListNode listNode4=new ListNode(4);
		ListNode listNode=listNode1;
		listNode1.next=listNode2;
		listNode2.next=listNode3;
		listNode3.next=listNode4;
		listNode4.next=null;
		//�������
		while(listNode!=null){
			System.out.println(listNode.val);
			listNode=listNode.next;
		}
		//�������
		Solution solution=new Solution();
		listNode=solution.reverseList(listNode1);
		while(listNode!=null){
			System.out.println(listNode.val);
			listNode=listNode.next;
		}
	}

	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 * ��ת������-->
	 *   �ȴ���ͷ��㣬���Ŵ���ǰ���͵�ǰ���ĺ��㣬������ָ������ĺ��㣬��ǰ���ָ��ǰ��㣬���շ��ص�ǰ��㣬����
	 */
	
	 public static class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) { val = x; }
		  }
	
	public static class Solution {
	    public ListNode reverseList(ListNode head) {
	        //�ж�ͷ����Ƿ�Ϊ��
	        if(head==null) return null;
	        //��ȡ��ǰ���
	        ListNode current=head;
	        //��ȡ��һ�����
	        ListNode post=head.next;
	        //ͷ�����Ϊnull
	        head.next=null;
	        //��תʣ�µĽ��
	        while(post!=null){
	            ListNode tmp=post;
	            post=post.next;
	            tmp.next=current;
	            current=tmp;
	        }
	        //���ط�ת������
	        return current;
	    }
	}
}
