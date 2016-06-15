package mycodelearn.undergrowth.basiclearn.util.test;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import mycodelearn.undergrowth.basiclearn.lang.test.IntegerComparator;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* * Queue--队列
 * PriorityQueue--迭代时，不保障元素的迭代顺序
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月14日
* @version 1.0.0
 */
public class QueueTest {

	Queue<Integer> linkedList,arrayDeque,priorityQueue;
	
	@Before
	public void before(){
		linkedList=new LinkedList<Integer>();
		arrayDeque=new ArrayDeque<Integer>();
		priorityQueue=new PriorityQueue<Integer>(20,new IntegerComparator());
		//初始化
		initQueue(linkedList);
		initQueue(arrayDeque);
		initQueue(priorityQueue);
	}

	/**
	 * 初始化队列
	 * @param queue
	 */
	private void initQueue(Queue<Integer> queue) {
		// TODO Auto-generated method stub
		Collection<Integer> c=Arrays.asList(12,34,1,11,789,65);
		queue.addAll(c);
	}
	
	/**
	 * 迭代Queue元素
	 */
	@Test
	public void testQueue(){
		iteratorQueue(linkedList);
		iteratorQueue(arrayDeque);
		iteratorQueue(priorityQueue);
	}

	/**
	 * 迭代队列元素
	 * @param queue
	 */
	private void iteratorQueue(Queue<Integer> queue) {
		// TODO Auto-generated method stub
		System.out.print(queue.getClass().getName()+"\t");
		for (Integer integer : queue) {
			System.out.print(integer+"\t");
		}
		System.out.println();
	}
	
	/**
	 * 测试LinkedList的Deque特性
	 */
	@Test
	public void testLinkedList(){
		LinkedList<Integer> linkedListCopy=(LinkedList<Integer>) linkedList;
		System.out.println("查看队列头元素，不删除元素:"+linkedListCopy.peek());
		System.out.println("查看队列头元素，不删除元素:"+linkedListCopy.peekFirst());
		System.out.println("查看队列尾元素，不删除元素:"+linkedListCopy.peekLast());
		iteratorQueue(linkedListCopy);
		System.out.println("查看队列头元素，删除元素:"+linkedListCopy.poll());
		System.out.println("查看队列头元素，删除元素:"+linkedListCopy.pollFirst());
		System.out.println("查看队列尾元素，删除元素:"+linkedListCopy.pollLast());
		iteratorQueue(linkedListCopy);
		System.out.println("添加对头元素"+linkedListCopy.offerFirst(300));
		System.out.println("添加对尾元素"+linkedListCopy.offer(400));
		System.out.println("添加对尾元素"+linkedListCopy.offerLast(500));
		iteratorQueue(linkedListCopy);
	}
	
	
}

