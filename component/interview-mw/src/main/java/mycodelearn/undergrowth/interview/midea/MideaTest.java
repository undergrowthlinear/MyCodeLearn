package mycodelearn.undergrowth.interview.midea;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.apache.commons.collections.functors.WhileClosure;
import org.junit.Test;


/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月22日
* @version  1.0.0
 */
public class MideaTest {

	class TestA {

		public int testA() {
			int num = 0;
			num++;
			return num;
		}
	}

	@Test
	public void testTest() {
		TestA test = new TestA();
		System.out.println(test.testA());
	}

	class TestABC {
		private BlockingQueue<Integer> queueA = new ArrayBlockingQueue<>(1);
		private BlockingQueue<Integer> queueB = new ArrayBlockingQueue<>(1);
		private BlockingQueue<Integer> queueC = new ArrayBlockingQueue<>(1);

		public TestABC() {
			try {
				queueB.put(1);
				queueC.put(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		public void runA() {
			try {
				queueA.put(1);
				System.out.print("A");
				queueB.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		public void runB() {
			try {
				queueB.put(1);
				System.out.print("B");
				queueC.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void runC() {
			try {
				queueC.put(1);
				System.out.print("C");
				queueA.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private CyclicBarrier barrier = new CyclicBarrier(3);

	@Test
	public void abcTest() {
		final TestABC testABC = new TestABC();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					barrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int i = 0; i < 10; i++) {
					testABC.runA();
				}
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					barrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int i = 0; i < 10; i++) {
					testABC.runB();
				}
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					barrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int i = 0; i < 10; i++) {
					testABC.runC();
				}
			}
		}).start();
		while (true)
			;
	}

}
