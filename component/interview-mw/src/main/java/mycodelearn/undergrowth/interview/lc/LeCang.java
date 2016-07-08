package mycodelearn.undergrowth.interview.lc;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月11日
* @version  1.0.0
 */
public class LeCang {

	/**
	 * Vector----线程安全,容量翻倍,随机访问,顺序存储
	 * ArrayList----线程不安全,容量50%,随机访问,顺序存储
	 * LinkedList----线程不安全,顺序访问,链式存储
	 * Hashtable----线程安全
	 * Hashmap----线程不安全
	 */
	
	/**
	 * GC的算法----分代收集
	 * YoungArea----新生的对象----频繁回收----年轻代被分为3个部分——Enden区和两个Survivor区（From和to）
	 * OldArea----应用程序中生命周期较长的对象----较少回收
	 * PermanentArea----Class和Meta信息----基本不回收
	 * JVM Heap包括YoungArea、OldArea
	 */
	
	/**
	 * Java OutofMemory----hprof
	 * Permanent OutOfMemory----Class和Meta信息----XX:PermSize
	 * Heap OutOfMemory----new 的对象实例----set JAVA_OPTS= -Xms256m -Xmx1024m
	 * Stack OutOfMemory----基本类型、方法输入输出----Xss 
	 * -XX:+HeapDumpOnOutOfMemoryError----生成hprof文件
	 */
	
	
	
}
