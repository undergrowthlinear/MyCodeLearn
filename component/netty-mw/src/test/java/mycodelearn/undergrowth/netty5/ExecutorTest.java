package mycodelearn.undergrowth.netty5;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
* Date 2016年6月16日
* @version 1.0.0
 */
public class ExecutorTest {

	/**
	 * 
	* Description: TODO(the caller's thread)
	* @author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
	* Date 2016年6月16日
	* @version 1.0.0
	 */
	class DirectExecutor implements Executor {
		      public void execute(Runnable r) {
		          r.run();
		      }
		  }
	
	@Test
	public void directTest(){
		DirectExecutor directExecutor=new DirectExecutor();
		final Runnable runnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread().getId()+":"+Thread.currentThread().getName());
			}
		};
		directExecutor.execute(runnable);
	}
	
	/**
	 * 
	* Description: TODO(新线程)
	* @author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
	* Date 2016年6月16日
	* @version 1.0.0
	 */
	class ThreadPerTaskExecutor implements Executor {
		public void execute(Runnable r) {
			new Thread(r).start();
		}
	}
	@Test
	public void newThreadTest(){
		ThreadPerTaskExecutor threadPerTaskExecutor=new ThreadPerTaskExecutor();
		final Runnable runnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread().getId()+":"+Thread.currentThread().getName());
			}
		};
		threadPerTaskExecutor.execute(runnable);
	}
	
	
	/**
	 * 
	* Description: TODO(使用线程池,顺序执行提交的任务)
	* @author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
	* Date 2016年6月16日
	* @version 1.0.0
	 */
	class SerialExecutor implements Executor {
		final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
		final Executor executor;
		Runnable active;

		SerialExecutor(Executor executor) {
			this.executor = executor;
		}

		public synchronized void execute(final Runnable r) {
			tasks.offer(new Runnable() {
				public void run() {
					try {
						r.run();
					} finally {
						scheduleNext();
					}
				}
			});
			if (active == null) {
				scheduleNext();
			}
		}

		protected synchronized void scheduleNext() {
			if ((active = tasks.poll()) != null) {
				executor.execute(active);
			}
		}
	}
	
	@Test
	public void serialTest(){
		Executor executor=Executors.newSingleThreadExecutor();
		SerialExecutor serialExecutor=new SerialExecutor(executor);
		final Runnable runnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread().getId()+":"+Thread.currentThread().getName());
			}
		};
		for(int i=0;i<10;i++){
			serialExecutor.execute(runnable);
		}
		while(true);
	}
}
