package mycodelearn.undergrowth.basiclearn.util.concurrent.test;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 
* Description: TODO(Executors类--> 提供实用方法-->)
* 工厂类作用-->Executor、ExecutorService、ScheduledExecutorService、ThreadFactory、Callable
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月14日
* @version 1.0.0
 */
public class ExecutorsTest {

	// 可执行的任务 不可返回值
	private Runnable runnable = new RunnableConcrete();
	// 可执行的任务 可返回值
	private Callable<String> callable = new CallableConcrete<String>();
	// 线程工厂
	private ThreadFactory threadFactory;
	// 提供了执行任务、跟踪任务、关闭线程池的操作(线程池、调度器)
	private ExecutorService executorService;
	// 用于表示异步执行的结果
	private Future<String> future;
	// 可延迟或重复调用的线程池
	private ScheduledExecutorService scheduledExecutorService;
	// 一个延时、可接受计算结果
	private ScheduledFuture<String> scheduledFuture;

	/**
	 * 使用Runnable创建Callable
	 */
	@Test
	public void callableCreateTest() {
		// 使用Runnable、给定的返回值创建一个Callable
		callable = Executors.callable(runnable, ExecutorsTest.class
				.getSimpleName());
		try {
			// 显示返回值
			System.out.println(callable.call());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 通过线程工厂创建线程
	 */
	@Test
	public void threadFactoryTest() {
		// 创建线程工厂
		threadFactory = Executors.defaultThreadFactory();
		// 创建一个Runnable
		runnable = new RunnableConcrete();
		// 创建线程
		threadFactory.newThread(runnable).start();
	}

	/**
	 * 产生根据需要而创建线程的线程池 当线程使用完后 可重复使用线程
	 */
	@Test
	public void executorServiceTest() {
		// 创建线程池
		executorService = Executors.newCachedThreadPool();
		// 在线程池中执行任务
		future = executorService.submit(runnable, "返回结果");
		System.out.println(future.isDone());
		// if(future.isDone())
		{
			try {
				// 获取任务的返回结果
				System.out.println(future.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 调用任务集合
	 */
	@Test
	public void executorServiceCollectionsTest() {
		/*
		 * CallableConcrete<String> callable=new CallableConcrete<String>();
		 * Collection<CallableConcrete<String>>
		 * tasks=createCallableTasks(20,callable);
		 */
		callable = new CallableConcrete<String>();
		// 构建集合任务
		Collection<Callable<String>> tasks = createCallableTasks(20, callable);
		// 创建线程池
		executorService = Executors.newCachedThreadPool();
		try {
			// 执行任务集合
			List<Future<String>> resuList = executorService.invokeAll(tasks);
			// 迭代任务集合
			iterator(resuList);
			// 关闭操作
			closeExecutorService(executorService);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 关闭线程池操作
	 * 
	 * @param executorService2
	 */
	private void closeExecutorService(ExecutorService executorService2) {
		// TODO Auto-generated method stub
		if (!executorService2.isShutdown()) {
			executorService2.shutdown();
			try {
				if (!executorService2.awaitTermination(10, TimeUnit.SECONDS)) {
					System.out.println("线程池未关闭");
				} else {
					System.out.println("线程池已成功关闭");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 迭代
	 * 
	 * @param <T>
	 * @param resuList
	 */
	@SuppressWarnings("unchecked")
	private <T> void iterator(Iterable<T> resuList) {
		// TODO Auto-generated method stub
		for (T t : resuList) {
			try {
				System.out.println(((Future<String>) t).get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 创建可调度的任务
	 * 
	 * @return
	 */
	private <E> Collection<E> createCallableTasks(int length, E e) {
		// TODO Auto-generated method stub
		Collection<E> list = new ArrayList<E>();
		for (int i = 0; i < length; i++) {
			list.add(e);
		}
		return list;
	}

	/**
	 * 创建可延时的任务
	 */
	@Test
	public void scheduledExecutorServiceDelayTest() {
		// 获取可延时、重复调度的线程池
		scheduledExecutorService = Executors.newScheduledThreadPool(1);
		// 显示当前时间
		System.out.println(System.currentTimeMillis());
		// 延迟10s后 执行任务
		scheduledFuture = scheduledExecutorService.schedule(callable, 10,
				TimeUnit.SECONDS);
		try {
			// 获取线程池的执行任务的返回结果
			System.out.println(scheduledFuture.get() + "\t"
					+ System.currentTimeMillis());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建重复调度的任务
	 */
	@Test
	public void scheduledExecutorServiceTest() {
		// 获取可延时、重复调度的线程池
		scheduledExecutorService = Executors.newScheduledThreadPool(2);
		// 显示当前时间
		System.out.println(System.currentTimeMillis());
		// 延迟10s后 重复执行任务 
		scheduledFuture = (ScheduledFuture<String>) scheduledExecutorService.scheduleAtFixedRate(runnable, 10, 10,TimeUnit.SECONDS);
		//运行1分钟后 终止重复执行的任务
		scheduledExecutorService.schedule(new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				//终止重复执行的任务
				scheduledFuture.cancel(true);
				System.out.println("终止重复执行的任务");
			}
			
		}, 1*60	, TimeUnit.SECONDS);
		// 获取线程池的执行任务的返回结果 因为任务被取消了 所以执行词句会报错
		try {
			System.out.println(scheduledFuture.get() + "\t"
					+ System.currentTimeMillis());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(CancellationException e){
			e.printStackTrace();
		}
	}

}

