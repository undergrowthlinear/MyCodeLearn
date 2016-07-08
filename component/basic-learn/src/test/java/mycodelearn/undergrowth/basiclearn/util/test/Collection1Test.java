package mycodelearn.undergrowth.basiclearn.util.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;


/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
*  * Collection测试
 * 
 * 
 * PriorityQueue--迭代时，不保障元素的迭代顺序
 * 
 * equals 与 hashCode 的区别
 *  1、If equal, then same hash codes too.
	2、Same hash codes no guarantee of being equal.
	不同数据类型生成的hashcode值不一样
	如何重写equals与hashCode方法 依据具体的需求而定
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月14日
* @version  1.0.0
 */
public class Collection1Test {

	/**
	 * 集合的三种子类型
	 */
	Collection<Integer> collectionList,collectionSet,collectionQueue;
	
	/**
	 * 创建集合的三种子类型
	 */
	@Before
	public void before(){
		//创建List Set Queue 三种子Collection类型 的实现类
		collectionList=new ArrayList<Integer>();
		collectionSet=new TreeSet<Integer>();
		collectionQueue=new PriorityQueue<Integer>();
		
		//向三种子类型填充数据
		initCollection(collectionList);
		initCollection(collectionSet);
		initCollection(collectionQueue);
	}

	/**
	 * 向集合中填充数据
	 * @param collectionList2
	 */
	private void initCollection(Collection<Integer> collection) {
		// TODO Auto-generated method stub
		Collection<Integer> c=Arrays.asList(13,2,56,100,87,87);
		collection.addAll(c);
	}
	
	/**
	 * 迭代三种子类型
	 */
	@Test
	public void testIterator(){
		iteratorCollection(collectionList);
		iteratorCollection(collectionSet);
		iteratorCollection(collectionQueue);
	}

	/**
	 * 迭代集合
	 * @param collectionList2
	 */
	private void iteratorCollection(Collection<Integer> collection) {
		// TODO Auto-generated method stub
		System.out.print(collection.getClass().getSimpleName()+"\t");
		for (Integer Integer : collection) {
			System.out.print(Integer+"\t");
		}
		System.out.println();
	}
	/**
	 * 移除集合中的元素
	 */
	@Test
	public void testRemove(){
		//移除前
		System.out.println("移除前集合");
		testIterator();
		removeCollection(collectionList);
		removeCollection(collectionSet);
		removeCollection(collectionQueue);
		//再进行迭代
		System.out.println("移除后集合");
		testIterator();
	}

	/**
	 * 移除集合中的元素
	 * @param collection
	 */
	private void removeCollection(Collection<Integer> collection) {
		// TODO Auto-generated method stub
		Collection<Integer> c=Arrays.asList(2,56,200);
		collection.removeAll(c);
	}
	
	/**
	 * 测试集合是否为空
	 */
	@Test
	public void testIsEmpty(){
		System.out.println(collectionList.isEmpty());
		System.out.println(collectionSet.isEmpty());
		System.out.println(collectionQueue.isEmpty());
	}
	
	/**
	 * 测试集合是否包含元素
	 */
	@Test
	public void testContains(){
		Collection<Integer> c=Arrays.asList(100);
		System.out.println(collectionList.containsAll(c));
		System.out.println(collectionSet.containsAll(c));
		System.out.println(collectionQueue.containsAll(c));
	}
	
	/**
	 * 测试集合转换为数组
	 */
	@Test
	public void testToArray(){
		Integer[] a=new Integer[collectionList.size()];
		System.out.println(Arrays.toString(collectionList.toArray(a)));
		a=new Integer[collectionSet.size()];
		System.out.println(Arrays.toString(collectionSet.toArray(a)));
		a=new Integer[collectionQueue.size()];
		System.out.println(Arrays.toString(collectionQueue.toArray(a)));
	}
	
	/**
	 * 测试清除集合
	 */
	@Test
	public void testClear(){
		//清除前
		System.out.println("清除前");
		testIterator();
		collectionList.clear();
		collectionSet.clear();
		collectionQueue.clear();
		//清除后
		System.out.println("清除后");
		testIterator();
	}
	
	/**
	 * 测试保留集合
	 */
	@Test
	public void testRetain(){
		//保留前
		System.out.println("保留前");
		testIterator();
		Collection<Integer> c=Arrays.asList(100);
		collectionList.retainAll(c);
		collectionSet.retainAll(c);
		collectionQueue.retainAll(c);
		//保留后
		System.out.println("保留后");
		testIterator();
	}
}
