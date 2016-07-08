package mycodelearn.undergrowth.basiclearn.util.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;



/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* * List测试
 * LinkedList--允许为空，具有双端队列和列表功能
 * ArrayList--数组大小可变的列表
 * Vector--数组大小可随着添加或者删除元素变化
 * Stack--堆栈--先入后出
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月14日
* @version  1.0.0
 */
public class ListTest {

	List<Integer> arrayList,vectorList,stackList,linkedList;
	
	/**
	 * 构建List的四种子类
	 */
	@Before
	public void before(){
		arrayList=new ArrayList<Integer>();
		vectorList=new Vector<Integer>();
		stackList=new Stack<Integer>();
		linkedList=new LinkedList<Integer>();
		
		initList(arrayList);
		initList(vectorList);
		initList(stackList);
		initList(linkedList);
	}
	
	
	/**
	 * 向列表中填充数据
	 * @param list
	 */
	private void initList(List<Integer> list) {
		// TODO Auto-generated method stub
		List<Integer> c=Arrays.asList(13,2,56,100,87,87);
		list.addAll(c);
	}
	
	/**
	 * 测试迭代元素
	 */
	@Test
	public void testIterator(){
		iteratorList(arrayList);
		iteratorList(vectorList);
		iteratorList(stackList);
		iteratorList(linkedList);
	}

	/**
	 * 迭代元素
	 * @param list
	 */
	private void iteratorList(List<Integer> list) {
		// TODO Auto-generated method stub
		System.out.print(list.getClass().getName()+"\t");
		for (Integer integer : list) {
			System.out.print(integer+"\t");
		}
		System.out.println();
	}
	
	/**
	 * 测试set与get方法
	 */
	@Test
	public void testGetSet(){
		System.out.println("调用set方法之前");
		iteratorList(arrayList);
		//设置索引值为0的元素的值为10000
		arrayList.set(0, 10000);
		System.out.println("调用set方法之后");
		iteratorList(arrayList);
		System.out.println("最后一个元素为:"+arrayList.get(arrayList.size()-1));
	}
	
	/**
	 * 测试subList方法
	 */
	@Test
	public void testSubList(){
		iteratorList(vectorList.subList(0, vectorList.size()-2));
	}
	
	/**
	 * 测试indexOf
	 */
	@Test
	public void testIndexOf(){
		System.out.println("100这个元素的索引为:"+stackList.indexOf(100));;
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
		iteratorList(linkedListCopy);
		System.out.println("查看队列头元素，删除元素:"+linkedListCopy.poll());
		System.out.println("查看队列头元素，删除元素:"+linkedListCopy.pollFirst());
		System.out.println("查看队列尾元素，删除元素:"+linkedListCopy.pollLast());
		iteratorList(linkedListCopy);
		System.out.println("添加对头元素"+linkedListCopy.offerFirst(300));
		System.out.println("添加对尾元素"+linkedListCopy.offer(400));
		System.out.println("添加对尾元素"+linkedListCopy.offerLast(500));
		iteratorList(linkedListCopy);
	}
	
	/**
	 * 测试Stack的堆栈特性
	 */
	@Test
	public void testStack(){
		Stack<Integer> stack=(Stack<Integer>) stackList;
		iteratorList(stack);
		System.out.println("查看堆栈出栈，不删除元素:"+stack.peek());
		System.out.println("查看堆栈出栈，删除元素:"+stack.pop());
		iteratorList(stack);
	}
}

