package mycodelearn.undergrowth.interview.cx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月16日
* @version 1.0.0
 */
public class ChaoXiang {

	/**
	 * Q1：Spring IOC的特点
	 * A1:IOC为inverse of control控制反转为spring容器的核心,意思是对象之间的依赖关系
	 * 由原先对象进行维护转为由容器进行控制对象间的依赖关系,控制权限转移降低了对象之间的依赖程度
	 */
	
	/**
	 * Q2:jvm的内存区域有哪些,哪个区域不会报OutOfMemory
	 * A2：参看http://blog.csdn.net/zmx729618/article/details/51198171
	 * 主要有5个部分
	 * 所有线程共享的区域
	 * 方法区--永久内存区--存放class与meta信息----会报OutOfMemory
	 * 堆区--heap(young/old)--存放对象实例----会报OutOfMemory
	 * 每个线程独有
	 * 虚拟机栈--stack--由栈帧组成,每次方法调用则是一个栈帧----会报OutOfMemory
	 * 本地方法栈--执行本地c/c++代码----会报OutOfMemory
	 * 程序计数器----指向当前线程的下一条指令----不会报OutOfMemory
	 */
	
	@Test
	public void arrayTest(){
		List<String> list=new ArrayList<String>();
		list.addAll(Arrays.asList("A","B","C","D"));
		int size=list.size();
		/*int index=-1;
		for (int i = 0; i < size; i++) {
			//System.out.println(list.get(i));
			if(list.get(i).equals("A")) index=i;
		}
		if(index!=-1) list.remove(index);*/
		/*for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			if(string.equals("A")) iterator.remove();;
		}*/
		//此种方法不行 因为list.remove移动后 list.size为3 不存在list.get(3)的元素了
		//会报IndexOutOfBoundsException
		for (int i = 0; i < size; i++) {
			//System.out.println(list.get(i));
			if(list.get(i).equals("A")) list.remove(i);
		}
		System.out.println(list);
	}
	
	@Test
	public void charTest(){
		char chinese='中';
		System.out.println(chinese);
	}
	
}
