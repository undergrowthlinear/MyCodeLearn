package mycodelearn.undergrowth.basiclearn.util.test;


import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import mycodelearn.undergrowth.basiclearn.lang.test.Operation;
import mycodelearn.undergrowth.basiclearn.lang.test.StringIntegerComparator;


/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* * Set集合测试
 * 集合中的每一个元素都只能存在一份
 * HashSet--迭代时，不能保证元素的顺序
 * LinkedHashSet--迭代时，保障元素的顺序按照它们插入的顺序
 * TreeSet--迭代时，保障元素的顺序按照它们插入的顺序，同时在集合内部对元素进行升序排序
 * EnumSet--枚举类型的集合s
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月14日
* @version 1.0.0
 */
public class SetTest {
	
	Set<Operation> enumSet;
	Set<Integer> hashSet,linkedHashSet,treeSet;
	Set<String> treeSetComparator;
	
	@Before
	public void before(){
		//创建四种Set的子类型
		enumSet=EnumSet.of(Operation.ADD, Operation.MULTI,Operation.ADD);
		hashSet=new HashSet<Integer>();
		linkedHashSet=new LinkedHashSet<Integer>();
		treeSet=new TreeSet<Integer>();
		//使用比较器构建TreeSet 按照比较器的方式进行排列Set中的元素
		StringIntegerComparator comparator=new StringIntegerComparator();
		treeSetComparator=new TreeSet<String>(comparator);
		treeSetComparator.addAll(Arrays.asList("100","45","78","120","87","89","87"));
		//初始化Set值
		initSet(hashSet);
		initSet(linkedHashSet);
		initSet(treeSet);
		
	}

	/**
	 * 向Set种填充数据
	 * @param hashSet2
	 */
	private void initSet(Set<Integer> set) {
		// TODO Auto-generated method stub
		Collection<Integer> c=Arrays.asList(100,45,78,120,87,89,87);
		set.addAll(c);
	}
	
	
	/**
	 * 迭代元素
	 */
	@Test
	public void testIterator(){
		iteratorSet(hashSet);
		iteratorSet(linkedHashSet);
		iteratorSet(treeSet);
		iteratorEnumSet(enumSet);
		iteratorTreeSet(treeSetComparator);
	}

	/**
	 * 迭代TreeSet
	 * @param treeSetComparator2
	 */
	private void iteratorTreeSet(Set<String> treeSetComparator2) {
		// TODO Auto-generated method stub
		System.out.print(treeSetComparator2.getClass().getName()+"\t");
		for (String string : treeSetComparator) {
			System.out.print(string+"\t");
		}
		System.out.println();
	}

	/**
	 * 迭代集合
	 * @param set
	 */
	private void iteratorSet(Set<Integer> set) {
		// TODO Auto-generated method stub
		System.out.print(set.getClass().getName()+"\t");
		for (Integer integer : set) {
			System.out.print(integer+"\t");
		}
		System.out.println();
	}
	
	/**
	 * 迭代EnumSet
	 * @param enumSet
	 */
	private void iteratorEnumSet(Set<Operation> enumSet){
		System.out.print(enumSet.getClass().getName()+"\t");
		for (Operation operation : enumSet) {
			System.out.print(operation.ordinal()+":"+operation.getName()+"\t");
		}
		System.out.println();
	}
	
	/**
	 * 测试NavigableSet的11个特有方法
	 */
	@Test
	public void testNavigableSet(){
		TreeSet<Integer> treeSetCopy=(TreeSet<Integer>) treeSet;
		//集合中维持的元素 2个方法
		System.out.print("集合中维持的元素\t");
		iteratorSet(treeSetCopy);
		System.out.print("集合中倒序元素\t");
		iteratorSet(treeSetCopy.descendingSet());
		Iterator<Integer> iterator=treeSetCopy.descendingIterator();
		//截取部分集合 3个方法
		System.out.println("截取小于100的集合\t");
		SortedSet<Integer> sortedSet=treeSetCopy.headSet(100);
		iteratorSet(sortedSet);
		System.out.println("截取大于100的集合，包括100这个元素\t");
		sortedSet=treeSetCopy.tailSet(100,true);
		iteratorSet(sortedSet);
		System.out.println("截取制定范围内的集合\t");
		sortedSet=treeSetCopy.subSet(87, 120);
		iteratorSet(sortedSet);
		//截取单个元素  4个方法
		System.out.println("截取大于等于100的元素\t");
		System.out.println(treeSetCopy.ceiling(100));
		System.out.println("截取小于等于100的元素\t");
		System.out.println(treeSetCopy.floor(100));
		System.out.println("截取大于100的元素\t");
		System.out.println(treeSetCopy.higher(100));
		System.out.println("截取小于100的元素\t");
		System.out.println(treeSetCopy.lower(100));
		//查看并删除元素
		iteratorSet(treeSetCopy);
		System.out.println("移除集合第一个元素:"+treeSetCopy.pollFirst());
		System.out.println("移除集合最后一个元素:"+treeSetCopy.pollLast());
		iteratorSet(treeSetCopy);
	}
	
}

