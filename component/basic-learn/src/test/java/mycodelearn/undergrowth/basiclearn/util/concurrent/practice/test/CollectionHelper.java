package mycodelearn.undergrowth.basiclearn.util.concurrent.practice.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月18日
* @version 1.0.0
 */
public class CollectionHelper<T> {

	public static <T> void iteratorResult(List<Future<T>> results) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		for (Future<T> future : results) {
			System.out.println(future.isDone()+","+future.get());
		}
	}

	public static <T> List<Future<T>> addCallableCollection(ExecutorService service, int threadNum,
			final Callable<T> callable) {
		// TODO Auto-generated method stub
		List<Future<T>> results = new ArrayList<>();
		for (int i = 0; i < threadNum; i++) {
			results.add(service.submit(callable));
		}
		return results;
	}

	public static <T> Collection<Callable<T>> addCallableCollection(int threadNum, final Callable<T> callable) {
		// TODO Auto-generated method stub
		Collection<Callable<T>> tasks = new ArrayList<>();
		for (int i = 0; i < threadNum; i++) {
			tasks.add(callable);
		}
		return tasks;
	}
}
