package mycodelearn.undergrowth.book.mw.jvm.advance;

import org.junit.Test;

/**
 * @author zhangwu
 * @version 1.0.0
 * @description java虚拟机栈溢出
 * -Xss128K
 * @date 2016-11-13-16:09
 */
public class JavaVMStackSOF {
	public int stackLength = 1;

	public void stackLeak() {
		stackLength++;
		stackLeak();
	}

	@Test
	public void testStackSof() {
		JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
		try {
			javaVMStackSOF.stackLeak();
		} catch (Throwable e) {
			System.err.println("stack length:"+javaVMStackSOF.stackLength);
			e.printStackTrace();
		}
	}

	private void dontStop(){
		while (true);
	}

	public void stackLeakByThread(){
		while (true){
			Thread thread=new Thread(new Runnable() {
				@Override
				public void run() {
					dontStop();
				}
			});
			thread.start();
		}
	}

	/**
	 * 通过多线程并发导致内存溢出
	 */
	@Test
	public  void testLeakByThread(){
		//-verbose:gc -Xms10M -Xmx10M -Xmn8M -Xss1024K -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails -XX:SurvivorRatio=8
		JavaVMStackSOF javaVMStackSOF=new JavaVMStackSOF();
		javaVMStackSOF.testLeakByThread();
	}

}
