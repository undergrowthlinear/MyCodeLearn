package mycodelearn.undergrowth.basiclearn.util.concurrent.test;


import org.junit.Test;

/**
* Description: TODO(测试Runnable 可执行的任务--> 本身并不执行，需外部调用执行)
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月14日
* @version  1.0.0
 */
public class RunnableTest {

	private Runnable runnable;
	private Thread thread;

	/**
	 * 使用匿名类的方式构建可执行的任务
	 */
	@Test
	public void testRun() {
		// 匿名类
		runnable = new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				System.out.println("内部线程名:" + Thread.currentThread().getName());
				System.out.println("内部线程输出:" + "发生什么");
			}
		};
		// 执行任务
		runnable.run();
		System.out.println("外部线程名:" + Thread.currentThread().getName());
	}

	/**
	 * 使用单独的线程构建可执行的任务 注意 start()与run()的区别
	 */
	@Test
	public void testThread() {
		// 构建另外线程执行
		thread = new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				System.out.println("内部线程名:" + Thread.currentThread().getName());
				// System.out.println("内部线程输出:" + "发生什么");
			}
		}, "内部线程名称");
		// 使用start启动新线程 并发执行
		thread.start();
		System.out.println("外部线程名:" + Thread.currentThread().getName());
		// 使用run则 使用外部线程执行任务
		thread.run();
	}

}

/**
 * Runnable的实现类
 * @author undergrowth
 *
 */
class RunnableConcrete implements Runnable{

	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName()+"\t"+RunnableConcrete.class.getSimpleName());
	}
	
}

