package mycodelearn.undergrowth.basiclearn.test;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

/**
 * 
 * Description: TODO(泛型--匹配任何类型)
 *              * 泛型学习测试代码 
 * 泛型-->类或者接口的声明中拥有一个或多个类型参数时，称为泛型类/泛型接口，简称泛型  
 * 泛型在安全性和表述性上都比原生态类型更具有优势 
 * 泛型是不可变的，无法向数组一样形成协变关系  
 * (Sub extends Super,同时可支持Sub[] extends Super[])  
 * (Sub extends Super,不支持List<Sub> extends List<Super>)  
 * 泛型在编译时检查其元素类型信息，而在运行期舍弃其元素类型信息 
 *  每个类都是自身的子类型和超类型 
 * T 形式类型参数  
 * E 集合中的形式类型参数  
 * K Map中的键形式参数  
 * V Map中的值形式参数  
 * ? 任意匹配的配型参数 <? extends E>/<? super E> 
 * 有限的通配符形式参数(PECS) 
 *  
 * Class<T> 泛型  
 * Class<String> 参数化的类型  
 * Class 原生态类型(删除了所有的泛型信息)  
 * Class<?> 无限制的通配符类型 
 * Class<? extends E> 有限制的通配符类型 
 * Class<T extends Comparable<T>> 递归类型限制(用类型参数本身组成表达式限制类型参数) 
 *  <T> 形式类型参数  
 *  <String> 实际类型参数 
 *  
 * static <E> E get(E e) 泛型方法 
 *  (<E> 称为类型参数列表，即在调用方法之前告诉编译器，此方法中的类型都是什么类型) 
 * 编译器使用泛型方法可通过类型推导，推导出类型参数列表的类型 
 *  
 * 此内容主要涉及泛型类和泛型方法，测试用例-->testGenericeClass 
 * 泛型集合-->testGeneCollection 
 * 有限的通配符使用-->testBoundWildType 
 * 泛型编译器维护类型信息，运行时舍弃-->testEraseGenericsInfo 
 * 递归类型参数调用-->testRecursionTypeRestri 
 * 测试多个类型参数-->testDoubleType 
 * @author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
 * Date 2016年6月7日
 * @version 1.0.0
 */
public class GenericsTest {

	/**
	 * 测试泛型类和方法
	 */
	@Test
	public void testGenericeClass() {
		GenericeClass<String> gClass1 = new GenericeClass<String>("测试泛型类参数");
		System.out.println(gClass1);
		System.out.println(gClass1.getAddSome("泛型方法"));
		System.out.println(gClass1.getAddSome(123));
	}

	/**
	 * 泛型测试类
	 * 
	 * @author Administrator
	 * 
	 * @param  <T>
	 */
	private static class GenericeClass<T> {
		private final T t;

		public GenericeClass(T t) {
			super();
			this.t = t;
		}

		/**
		 * 获取对象后 添加附加信息
		 * 
		 * @param  t
		 * @return 
		 */
		public <E> E getAddSome(E t) {
			if (t instanceof String) {
				// 因为在进入此方法时 已做了类型判断 使用注解消除非受检警告
				@SuppressWarnings("unchecked")
				E t1 = (E) (String.valueOf(t) + ",附加泛型防范新消息");
				return t1;
			}
			return t;
		}

		public void iterator(Iterable<? extends T> src) {
			for (T t : src) {
				System.out.println(t);
			}
		}

		@Override
		public String toString() {
			return "GenericeClass [t=" + t + "]";
		}

	}

	/**
	 * 测试泛型集合
	 */
	@Test
	public void testGeneCollection() {
		Collection<String> collection = createCollection("123");
		/*
		 * for (Iterator<String> iterator = collection.iterator();
		 * iterator.hasNext();) { String string = (String) iterator.next();
		 * System.out.println(string); }
		 */
		iterator(collection);
	}

	/**
	 * 创建测试集合
	 * 
	 * @return 
	 */
	private <E> Collection<E> createCollection(E t) {
		// TODO Auto-generated method stub
		Collection<E> collection = Collections.emptyList();
		if (t instanceof String) {
			collection = new CopyOnWriteArrayList<E>();
			// 已经进行类类型检查 所以转换时没有问题的 每个类都是自身的子类型和超类型
			@SuppressWarnings("unchecked")
			Collection<? extends E> initData = (Collection<? extends E>) Arrays
					.asList(new String[] { "测试集合1", "测试集合2", "测试集合3" });
			collection.addAll(initData);
		}
		return collection;
	}

	/**
	 * 创建Number集合 此方法不推荐使用 这里只是测试使用
	 * 
	 * @return 
	 */
	@Deprecated
	private <E> Collection<E> createNumber() {
		Collection<E> collection = new CopyOnWriteArrayList<E>();
		@SuppressWarnings("unchecked")
		Collection<? extends E> initData = (Collection<? extends E>) Arrays
				.asList(new Number[] { 123.2, Integer.MAX_VALUE, 789 });
		// System.out.println(initData.getClass().getName());
		collection.addAll(initData);
		return collection;
	}

	/**
	 * 迭代实现了Iterable接口的可迭代子类
	 * 
	 * @param  src
	 */
	public <E> void iterator(Iterable<E> src) {
		for (E e : src) { // 内部通过迭代器来遍历元素
			System.out.println(e);
		}
	}

	/**
	 * 测试有限的通配符类型 每个类都是自身的子类型和超类型 PECS原则-->producer-extends,consumer-super
	 */
	@Test
	public void testBoundWildType() {
		GenericeClass<Number> gClass1 = new GenericeClass<Number>(123456);
		System.out.println(gClass1);
		System.out.println(gClass1.getAddSome("123456"));
		// 创建Number数组
		Collection<Number> collection = createNumber();
		iterator(collection);
		// 使用GenericeClass的迭代方法进行迭代 每个类都是自身的子类型和超类型
		System.out
				.println("使用有限的通配符进行迭代 形式为：Iterable<? extends T>  实际为：Iterable<Number extends Number> 每个类都是自身的子类型和超类型");
		gClass1.iterator(collection);
		Collection<Integer> collection2 = createNumber();
		// 使用foreach进行迭代
		System.out.println("使用foreach进行迭代");
		iterator(collection2);
		// 使用有限的通配符进行迭代
		System.out.println("使用有限的通配符进行迭代 形式为：Iterable<? extends T>  实际为：Iterable<Integer extends Number>");
		gClass1.iterator(collection2);
		System.out.println("进行字符串集合的foreach迭代");
		Collection<String> collection3 = createNumber();
		iterator(collection3);
		System.out.println("如果使用collection3.add(123)方法，泛型的安全性检查就会得到体现，编译器会报错");
		// collection3.add(123);
		System.out.println(
				"如果调用gClass1.iterator(collection3)方法，就会有问题，因为形式为：Iterable<? extends T> 实际为：Iterable<String extends Number>");
		// gClass1.iterator(collection3);
		System.out.println("获取Serializable集合");
		Collection<Serializable> collection4 = createNumber();
		iterator(collection4);
		System.out.println(
				"之前一直有个疑惑，为什么Collection<Number>、Collection<Integer>、Collection<String>、Collection<Serializable>、Collection<List>调用的时候 能够返回相应的集合，"
						+ "因为很明显 在createNumber方法中创建的是Number的List<Number>集合 我们有知道泛型是不可协变的 为什么会成功呢 ");
		System.out.println(
				"因为泛型在编译器检查其类型信息(所以当定义Collection<Serializable> collection4 = createNumber();的时候 调用createNumber，而此方法只会进行类型检查，createNumber为泛型方法，所以编译时是没有问题的)，而在运行时丢弃其元素类型信息，并且强制类型转换是在运行期进行的，所以在(Collection<? extends E>)强制类型转换实际上是(Collection)List,所以运行期也没有问题，调试一下就知道了，害我想了1个多小时");
	}

	/**
	 * 测试泛型只在编译器维护其类型信息 在运行期不会维护其元素的类型信息 下面代码会报错 java.lang.ClassCastException:
	 * java.lang.Double cannot be cast to java.util.List
	 */
	@Test
	public void testEraseGenericsInfo() {
		try {
			System.out.println("获取List集合");
			Collection<List> collection5 = createNumber();
			for (List list : collection5) {
				System.out.println(list.get(0));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 递归类型限制 使用类型参数本身组成表达式限制类型参数
	 */
	@Test
	public void testRecursionTypeRestri() {
		// Integer和String都实现了Comparable接口
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		System.out.println(recurType(list));
		List<String> listString = Arrays.asList("1", "2", "3", "4");
		System.out.println(recurType(listString));
		// 自定义实现的Comparable接口
		List<CustomComparable> listCustomComparables = Arrays.asList(new CustomComparable(10), new CustomComparable(20),
				new CustomComparable(30));
		System.out.println(recurType(listCustomComparables));
	}

	/**
	 * 找到列表中的最大值
	 * 
	 * @param  list
	 * @return 
	 */
	private <T extends Comparable<T>> T recurType(List<T> list) {
		T to = list.get(0);
		for (Iterator<T> iterator = list.iterator(); iterator.hasNext();) {
			T t = (T) iterator.next();
			if (to.compareTo(t) <= 0)
				to = t;
		}
		return to;
	}

	/**
	 * 多个类型参数调用 多个类型参数 其中一个参数要么与泛型类相关联 iterator(Iterable<? extends T> src)
	 * 要么与静态方法关联 GenericsLearn.<Number>doubleType
	 */
	@Test
	public void testDoubleType() {
		Collection<Integer> collection = Arrays.asList(1, 2, 3);
		Collection<Number> collection2 = GenericsTest.<Number> doubleType(collection);
		System.out.println("使用泛型方法传递多个类型参数");
		for (Number number : collection2) {
			System.out.println(number);
		}
	}

	public static <E> Set<E> doubleType(Collection<? extends E> collection) {
		Set<E> set = new HashSet<>();
		for (Iterator<? extends E> iterator = collection.iterator(); iterator.hasNext();) {
			E e = (E) iterator.next();
			System.out.println(e);
			set.add(e);
		}
		return set;
	}

	@Test
	public void testOther() {
		Integer integer = (int) 13.2;
		String aString = String.valueOf(13.2);
	}

	/**
	 * 自定义比较器
	 * 
	 * @author Administrator
	 *
	 */
	class CustomComparable implements Comparable<CustomComparable> {

		private final int num;

		public CustomComparable(int num) {
			this.num = num;
		}

		@Override
		public int compareTo(CustomComparable o) {
			// TODO Auto-generated method stub
			if (this.num > o.num)
				return 1;
			else if (this.num == o.num)
				return 0;
			else
				return -1;
		}

		@Override
		public String toString() {
			return "CustomComparable [num=" + num + "]";
		}

	}
}
