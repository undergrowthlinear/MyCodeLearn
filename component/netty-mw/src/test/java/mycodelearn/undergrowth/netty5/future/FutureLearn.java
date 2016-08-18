package mycodelearn.undergrowth.netty5.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.junit.Test;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年8月17日
 * @version 1.0.0
 */
public class FutureLearn {
	interface ArchiveSearcher {
		String search(String target);
	}

	class App {
		ExecutorService executor = Executors.newCachedThreadPool();
		ArchiveSearcher searcher = new ArchiveSearcher() {

			@Override
			public String search(String target) {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread().getName() + ",current time is " + System.currentTimeMillis());
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return target + " sleep 60S,current time is " + System.currentTimeMillis();
			}
		};

		void showSearch(final String target) throws InterruptedException {
			System.out.println(Thread.currentThread().getName() + ",current time is " + System.currentTimeMillis());
			Future<String> future = executor.submit(new Callable<String>() {
				public String call() {
					return searcher.search(target);
				}
			});
			System.out.println(Thread.currentThread().getName() + ",current time is " + System.currentTimeMillis());
			displayOtherThings(); // do other things while searching
			try {
				displayText(future.get()); // use future
			} catch (ExecutionException ex) {
				cleanup();
				return;
			}
		}

		void showSearchFutureTask(final String target) throws InterruptedException {
			System.out.println(Thread.currentThread().getName() + ",current time is " + System.currentTimeMillis());
			FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
				public String call() {
					return searcher.search(target);
				}
			});
			executor.execute(future);
			System.out.println(Thread.currentThread().getName() + ",current time is " + System.currentTimeMillis());
			displayOtherThings(); // do other things while searching
			try {
				displayText(future.get()); // use future
			} catch (ExecutionException ex) {
				cleanup();
				return;
			}
		}

		private void cleanup() {
			// TODO Auto-generated method stub

		}

		private void displayText(String string) {
			// TODO Auto-generated method stub
			System.out.println(Thread.currentThread().getName() + ",current time is " + System.currentTimeMillis());
			System.out.println(string + ",sleep return current time is " + System.currentTimeMillis());
		}

		private void displayOtherThings() {
			// TODO Auto-generated method stub
			System.out.println(Thread.currentThread().getName() + ",current time is " + System.currentTimeMillis());
			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Test
	public void testAppShowSearch() {
		App app = new App();
		try {
			app.showSearch("undergrowth");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testShowSearchFutureTask() {
		App app = new App();
		try {
			app.showSearchFutureTask("undergrowth");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSome(){
		System.out.println((1&2)-1);
	}

}
