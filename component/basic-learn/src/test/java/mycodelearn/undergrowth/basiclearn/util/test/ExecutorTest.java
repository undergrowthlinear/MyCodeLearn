package mycodelearn.undergrowth.basiclearn.util.test;

import java.util.concurrent.Executor;

import org.junit.Test;

/**
 * Description: TODO(Executor-->可执行者)
 * 
 * @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
 *  Date 2016年6月14日
 * @version 1.0.0
 */
public class ExecutorTest {

	private Executor executor;
	private Runnable runnable = new Runnable() {

		public void run() {
			// TODO Auto-generated method stub
			System.out.println(Thread.currentThread().getName());
		}
	};

	/**
	 * 测试异步执行
	 */
	@Test
	public void asynExecuteTest() {
		System.out.println(Thread.currentThread().getName());
		executor = new AsynExecute();
		executor.execute(runnable);
	}

	/**
	 * 测试同步执行
	 */
	@Test
	public void syncExecuteTest() {
		System.out.println(Thread.currentThread().getName());
		executor = new SyncExecute();
		executor.execute(runnable);
	}
}

/**
 * 构建单独线程执行 异步执行的任务
 * 
 * @author undergrowth
 *
 */
class AsynExecute implements Executor {

	public void execute(Runnable command) {
		// TODO Auto-generated method stub
		new Thread(command, "单独的线程").start();
	}

}

/**
 * 使用调用线程线程执行任务 同步执行的任务
 * 
 * @author undergrowth
 *
 */
class SyncExecute implements Executor {

	public void execute(Runnable command) {
		// TODO Auto-generated method stub
		command.run();
	}

}
