package mycodelearn.undergrowth.interview.bt;

import org.junit.Test;

/**
 * 
 * Description: TODO(铂涛旅行面试题)
 * 
 * @author <a href="zhangwu@wxchina.com">Wu.Zhang</a> Date 2016年6月8日
 * @version 1.0.0
 */
public class BoTaolvXing {

	/**
	 * Q1:final、finally、finalize A1:final类表示该类不可被继承，修饰方法表示方法不能被修改，修饰字段表示值不可变
	 * finally用于try/catch/finally中,用于清除操作
	 * finalize为Object对象方法，用于垃圾回收器回收对象时，所做的清理操作
	 */

	/**
	 * Q2:Set与List有什么区别，为什么Set插入比List要高效 A2:两者都继承Collection接口,
	 * List为顺序存储，元素可重复，Set为随机存储,使用元素的HashCode进行存储，元素不可重复
	 * 因为Set为随机存储，所以插入效率高于List的顺序存储
	 */

	/**
	 * Q3:写出熟悉的Linux命令 A3:cd--切换路径 chmod--改变文件权限 cp--复制文档/目录 cat---查看文件内容
	 * date--查看系统日期 grep--过滤信息 ls--查看目录列表详情 man--查看帮助手册 mkdir--新建目录 rmdir--删除目录
	 * mv--移动文件 more--分页显示 ps--查看进程 pwd--打印当前工作目录 history find gzip tar
	 */

	/**
	 * Q4:Spring容器的单例与非单例在容器的生命周期有什么不同
	 * A4:Spring容器中默认Bean都是单例的，在进行单例访问时，存在多线程资源共享问题，以synchronized同步为代价的是以时间换空间，
	 * 而Spring大部分组件是以空间换时间的做法，采用ThreadLocal进行存储有状态的属性
	 * 单例的对象在容器创建时由容器创建，在容器关闭时才会销毁单例对象，而非单例对象不由Spring容器进行维护，当没有引用时，jvm即会销毁
	 */

	/**
	 * ThreadLocal通过 t.threadLocals = new ThreadLocalMap(this, firstValue)
	 * this指向ThreadLocal
	 * @throws InterruptedException 
	 */
	@Test
	public void testThreadLocal() throws InterruptedException {
		TestThreadLocal threadLocal = new TestThreadLocal();
		threadLocal.testMain();
	}

	public class TestThreadLocal {
		ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
		ThreadLocal<String> stringLocal = new ThreadLocal<String>();

		public void set() {
			longLocal.set(Thread.currentThread().getId());
			stringLocal.set(Thread.currentThread().getName());
		}

		public long getLong() {
			return longLocal.get();
		}

		public String getString() {
			return stringLocal.get();
		}

		public void testMain() throws InterruptedException {
			final TestThreadLocal test = new TestThreadLocal();

			test.set();
			System.out.println(test.getLong());
			System.out.println(test.getString());

			Thread thread1 = new Thread() {
				public void run() {
					test.set();
					System.out.println(test.getLong());
					System.out.println(test.getString());
				};
			};
			thread1.start();
			thread1.join();

			System.out.println(test.getLong());
			System.out.println(test.getString());
		}
	}

}
