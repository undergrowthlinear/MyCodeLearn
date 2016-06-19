
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DeadLockTest {

	public static void main(String[] args) {
		final int threadNum = 10000;
		final ExecutorService service = Executors.newFixedThreadPool(threadNum);
		final CyclicBarrier barrier = new CyclicBarrier(threadNum);
		DeadLockTest deadLockTest = new DeadLockTest();
		final LeftRightDeadlock leftRightDeadlock = deadLockTest.new LeftRightDeadlock();
		final CollectionHelper<String> helper = deadLockTest.new CollectionHelper<String>();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Callable<String> leftRightCallable = new Callable<String>() {

					@Override
					public String call() throws Exception {
						// TODO Auto-generated method stub
						barrier.await();
						return leftRightDeadlock.leftRight();
					}
				};
				List<Future<String>> results = helper.addCallableCollection(service, threadNum / 2, leftRightCallable);
				helper.iteratorResult(results);
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Callable<String> rightLeftCallable = new Callable<String>() {

					@Override
					public String call() throws Exception {
						// TODO Auto-generated method stub
						barrier.await();
						return leftRightDeadlock.rightLeft();
					}
				};
				List<Future<String>> results = helper.addCallableCollection(service, threadNum / 2, rightLeftCallable);
				helper.iteratorResult(results);
			}
		}).start();

		while (true)
			;
	}

	class LeftRightDeadlock {
		private final Object left = new Object();
		private final Object right = new Object();

		public String leftRight() {
			synchronized (left) {
				synchronized (right) {
					return "left to right";
				}
			}
		}

		public String rightLeft() {
			synchronized (right) {
				synchronized (left) {
					return "right to left";
				}
			}
		}

	}

	class CollectionHelper<T> {

		public <T> void iteratorResult(List<Future<T>> results) {
			// TODO Auto-generated method stub
			for (Future<T> future : results) {
				try {
					System.out.println(future.isDone() + "," + future.get());
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public <T> List<Future<T>> addCallableCollection(ExecutorService service, int threadNum,
				final Callable<T> callable) {
			// TODO Auto-generated method stub
			List<Future<T>> results = new ArrayList<>();
			for (int i = 0; i < threadNum; i++) {
				results.add(service.submit(callable));
			}
			return results;
		}

		public <T> Collection<Callable<T>> addCallableCollection(int threadNum, final Callable<T> callable) {
			// TODO Auto-generated method stub
			Collection<Callable<T>> tasks = new ArrayList<>();
			for (int i = 0; i < threadNum; i++) {
				tasks.add(callable);
			}
			return tasks;
		}
	}

}
