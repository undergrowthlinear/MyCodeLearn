package mycodelearn.undergrowth.basiclearn.util.concurrent.practice.test;

@ThreadSafeTest
public class SafeSequence {
	private int value = 1000;

	//使用synchronized 使用对象锁进行同步
	public synchronized int getNext() {
		return value++;
	}

}