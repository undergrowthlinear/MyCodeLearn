package mycodelearn.undergrowth.basiclearn.util.test;


import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.WeakHashMap;

import org.junit.Before;
import org.junit.Test;

import mycodelearn.undergrowth.basiclearn.lang.test.Operation;



/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* * Map测试
 * HashMap--key-value,迭代时，不保障元素的顺序
 * LinkedHashMap--使用双端链表连接元素，迭代式，保障元素的顺序
 * IdentityHashMap--比较key的时候，使用引用相等判断替代对象相等
 * WeakHashMap--当key不在使用时，移除其关联的对象
 * Hashtable--任何非空的对象都能够作为key或者value
 * Properties--能够从流中加载或者保存到流中
 * TreeMap--迭代时，保证元素的顺序,同时元素也是按照顺序排放的
 * EnumMap--使用枚举类型作为key
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月14日
* @version 1.0.0
 */
public class MapTest {
	
	Map<String,String> hashMap,linkedHashMap,identityHashMap,weakHashMap,hashtable,treeMap;
	EnumMap<Operation, String> enumMap;
	Properties properties;
	
	@Before
	public void before() throws IOException{
		//创建对象
		hashMap=new HashMap<String, String>();
		linkedHashMap=new LinkedHashMap<String, String>();
		identityHashMap=new IdentityHashMap<String, String>();
		weakHashMap=new WeakHashMap<String, String>();
		hashtable=new Hashtable<String, String>();
		properties=new Properties();
		treeMap=new TreeMap<String, String>();
		enumMap=new EnumMap<Operation, String>(Operation.class);
		//进行初始化
		initiMap(hashMap);
		initiMap(linkedHashMap);
		initiMap(identityHashMap);
		initiMap(weakHashMap);
		initiMap(hashtable);
		initiMap(treeMap);
		//初始化Properties
		InputStream isInputStream=ClassLoader.getSystemResourceAsStream("test.properties");
		properties.load(isInputStream);
		//初始化EnumMap
		initEnumMap(enumMap);
	}

	/**
	 * 初始化EnumMap
	 * @param enumMap
	 */
	private void initEnumMap(EnumMap<Operation, String> enumMap) {
		// TODO Auto-generated method stub
		enumMap.put(Operation.ADD, "+");
		enumMap.put(Operation.MINUS, "-");
		enumMap.put(Operation.MULTI, "*");
		enumMap.put(Operation.DIVIDE, "/");
	}

	/**
	 * 初始化Map
	 * @param map
	 */
	private void initiMap(Map<String, String> map) {
		// TODO Auto-generated method stub
		map.put("q", "qq");
		map.put("w", "ww");
		map.put("e", "ee");
		map.put("r", "rr");
		map.put("t", "tt");
		map.put("y", "yy");
	}
	
	/**
	 * 迭代Map
	 */
	@Test
	public void testIteratorMap(){
			iteratorMap(hashMap);
			iteratorMap(linkedHashMap);
			iteratorMap(identityHashMap);
			iteratorMap(weakHashMap);
			iteratorMap(hashtable);
			iteratorMap(treeMap);
			iteratorProperties(properties);
	}

	/**
	 * 迭代Properties
	 * @param properties2
	 */
	private void iteratorProperties(Properties properties2) {
		// TODO Auto-generated method stub
		Set<Entry<Object, Object>> sets=properties2.entrySet();
		System.out.print(sets.getClass().getName()+"\t");
		for (Entry<Object, Object> entry : sets) {
			System.out.print(entry.getKey()+":"+entry.getValue()+"\t");
		}
		System.out.println();
	}

	/**
	 * 迭代Map
	 * @param map
	 */
	private void iteratorMap(Map<String, String> map) {
		// TODO Auto-generated method stub
		System.out.print(map.getClass().getName()+"\t");
		for (Map.Entry<String, String> entry:map.entrySet()) {
			System.out.print(entry.getKey()+":"+entry.getValue()+"\t");
		}
		System.out.println();
	}
	
	/**
	 * 迭代Set
	 * @param keySet
	 */
	private void iteratorSet(NavigableSet<String> keySet) {
		// TODO Auto-generated method stub
		System.out.print(keySet.getClass().getName()+"\t");
		for (String string : keySet) {
			System.out.print(string+"\t");
		}
		System.out.println();
	}
	
	/**
	 * 测试可导航的Map
	 */
	@Test
	public void testNavigableMap(){
		iteratorMap(treeMap);
		TreeMap<String, String> treeMapCopy=(TreeMap<String, String>) treeMap;
		//遍历降序的Map
		iteratorMap(treeMapCopy.descendingMap());
		NavigableSet<String> keySet=treeMapCopy.descendingKeySet();
		//遍历降序的key
		iteratorSet(keySet);
		//截取子Map
		System.out.print("截取小于t的值得Map子集合"+"\t");
		iteratorMap(treeMapCopy.headMap("t"));
		System.out.print("截取大于t的值得Map子集合"+"\t");
		iteratorMap(treeMapCopy.tailMap("t"));
		System.out.print("截取键的范围[t,y)之间的Map子集合"+"\t");
		iteratorMap(treeMapCopy.subMap("t","y"));
		//截取key
		System.out.print("截取大于等于t键值的key"+"\t");
		System.out.println(treeMapCopy.ceilingKey("t"));
		System.out.print("截取小于等于t键值的key"+"\t");
		System.out.println(treeMapCopy.floorKey("t"));
		System.out.print("截取大于t键值的key"+"\t");
		System.out.println(treeMapCopy.higherKey("t"));
		System.out.print("截取小于t键值的key"+"\t");
		System.out.println(treeMapCopy.lowerKey("t"));
		//截取Entry
		System.out.print("截取大于等于t键值的Entry"+"\t");
		System.out.println(treeMapCopy.ceilingEntry("t"));
		System.out.print("截取小于等于t键值的Entry"+"\t");
		System.out.println(treeMapCopy.floorEntry("t"));
		System.out.print("截取大于t键值的Entry"+"\t");
		System.out.println(treeMapCopy.higherEntry("t"));
		System.out.print("截取小于t键值的Entry"+"\t");
		System.out.println(treeMapCopy.lowerEntry("t"));
		//查看并移除Entry
		System.out.println("查看并移除第一个Entry:"+treeMapCopy.pollFirstEntry());
		System.out.println("查看并移除最后一个Entry:"+treeMapCopy.pollLastEntry());
		iteratorMap(treeMapCopy);
	}

	
	
}

