package mycodelearn.undergrowth.basiclearn.util.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

/**
 * 
 * Description: TODO(测试集合操作)
 * 
 * @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a> Date 2016年6月14日
 * @version 1.0.0
 */
public class CollectionTest {

	/**
	 * 泛型方法 对实现Iterable<E>接口的对象进行迭代
	 * 
	 * @param iterable
	 */
	public <E> void iterator(Iterable<E> iterable) {
		for (E e : iterable) {
			System.out.println(e);
		}
	}

	// 测试数组
	private final String[] ITEMS = { "中国", "美国", "德国", "法国" };

	/**
	 * 创建ArrayList集合
	 * 
	 * @return
	 */
	private Collection<String> createArrayList() {
		Collection<String> collection = new ArrayList<String>();
		addData(collection);
		return collection;
	}

	/**
	 * 向数组中添加元素
	 * 
	 * @param collection
	 */
	private void addData(Collection<String> collection) {
		// TODO Auto-generated method stub
		collection.addAll(Arrays.asList(ITEMS));
	}

	/**
	 * 测试ArrayList可变数组
	 */
	@Test
	public void testArrayList() {
		Collection<String> collection = createArrayList();
		iterator(collection);
		System.out.println("删除后，在进行迭代");
		collection.remove("中国");
		iterator(collection);
		System.out.println("测试是否包含中国:" + collection.contains("中国"));
		System.out.println("测试清除集合:");
		collection.clear();
		iterator(collection);
		System.out.println("测试清除集合后，是否为空:" + collection.isEmpty());
		addData(collection);
		iterator(collection);
		System.out.println("填充数据后，转换为数组");
		System.out.println("数组元素个数:" + collection.toArray().length);
		System.out.println("截取子列表,[0,2)");
		iterator(((List<String>) collection).subList(0, 2));
	}

	/**
	 * 创建堆栈元素
	 * 
	 * @return
	 */
	private Stack<String> createStack() {
		// TODO Auto-generated method stub
		Stack<String> stack = new Stack<>();
		stack.push(ITEMS[0]);
		stack.push(ITEMS[1]);
		stack.push(ITEMS[2]);
		return stack;
	}

	/**
	 * 测试堆栈 先进后出
	 */
	@Test
	public void testStack() {
		Stack<String> stack = createStack();
		iterator(stack);
		System.out.println("判断堆栈是否为空:" + stack.empty());
		System.out.println("查找堆栈元素的位置:" + stack.search("中国"));
		System.out.println("查看栈顶元素:" + stack.peek());
		System.out.println("删除栈顶元素:" + stack.pop());
	}

	/**
	 * 创建Set集合
	 * 
	 * @return
	 */
	private Set<String> createSet() {
		// TODO Auto-generated method stub
		Set<String> set = new HashSet<>();
		set.add(ITEMS[0]);
		set.add(ITEMS[0]);
		set.add(ITEMS[0]);
		set.add(ITEMS[1]);
		set.add(ITEMS[2]);
		set.add(ITEMS[3]);
		return set;
	}

	/**
	 * 测试HashSet
	 */
	@Test
	public void testHashSet() {
		Set<String> set = createSet();
		iterator(set);
	}

	/**
	 * 创建Map 键值对
	 * 
	 * @return
	 */
	private Map<String, String> createMap() {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put(ITEMS[0], ITEMS[0]);
		map.put(ITEMS[0], ITEMS[0]);
		map.put(ITEMS[1], ITEMS[1]);
		map.put(ITEMS[2], ITEMS[2]);
		map.put(ITEMS[3], ITEMS[3]);
		return map;
	}

	/**
	 * 测试HashMap
	 */
	@Test
	public void testHashMap() {
		Map<String, String> map = createMap();
		iterator(map.keySet());
		iterator(map.values());
	}

	@Test
	public void testCompar() {
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < 10; i++)
			persons.add(new Person("under" + i, i));
		iterator(persons);
		Collections.sort(persons);
		iterator(persons);
	}

	class Person implements Comparable<Person> {
		private String name;
		private int age;

		public Person(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public int compareTo(Person o) {
			// TODO Auto-generated method stub
			if (o.age > this.age)
				return 1;
			if (o.age < this.age)
				return -1;
			return 0;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + "]";
		}

		
	}

}
