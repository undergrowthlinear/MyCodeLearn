package mycodelearn.undergrowth.basiclearn.test;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
* Description: TODO(Callable-->返回结果并且可能抛出异常的任务)
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月14日
* @version 1.0.0
 */
public class CallableTest {

	private Callable<String> callable;

	/**
	 * 使用调用者调用任务,同步执行
	 */
	@Test
	public void synCallable() {
		callable = new CallableConcrete<String>();
		try {
			System.out.println(callable.call());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 使用线程池进行任务调度
	 */
	@Test
	public void asynCallable(){
		callable = new CallableConcrete<String>();
		try {
			System.out.println(Executors.newCachedThreadPool().submit(callable).get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
}

/**
 * Callable的实现类
 * 
 * @author undergrowth
 * 
 */
class CallableConcrete<V> implements Callable<V> {

	@SuppressWarnings("unchecked")
	public V call() throws Exception {
		// TODO Auto-generated method stub
		return (V)(Thread.currentThread().getName()+"\t"+CallableConcrete.class.getSimpleName());
	}

}
