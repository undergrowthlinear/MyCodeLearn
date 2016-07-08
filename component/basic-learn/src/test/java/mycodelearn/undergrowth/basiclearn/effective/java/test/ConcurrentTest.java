package mycodelearn.undergrowth.basiclearn.effective.java.test;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* * ThreadDump分析:
* http://blog.csdn.net/rachel_luo/article/details/8920596
* 线程的状态
* new(创建)--start-->Runnable(就绪)--调度器调度-->Running(运行)--run-->Dead(死亡)
* Running(运行)--synchronized--Block(锁池/entry set)--获得锁-->Runnable(就绪)
* Running(运行)--wait--Block(等待池/wait set)--notify/notifyAll-->Block(锁池/entry set)--获得锁-->Runnable(就绪)
* Running(运行)--sleep/join--Block(其他等待)--条件达到-->Runnable(就绪)
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月22日
* @version  1.0.0
 */
public class ConcurrentTest {
	
	/**
	 * 66.同步访问共享可变的数据----当多个线程共享可变数据时,每个读或者写的线程都必须同步
	 * 未能同步共享的数据可能造成
	 * 活性失败(liveness failure)----jvm的性能优化导致----保证正确的事情能尽快执行
	 * 安全性失败(safety failure)----由于内存不可见导致----永远不让糟糕的事情发生
	 * 
	 * 解决方式----
	 * synchronized----保证内存可见性与同步块
	 * volatile----保持内存可见性
	 */
	
}
