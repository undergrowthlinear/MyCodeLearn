package mycodelearn.undergrowth.basiclearn.util.concurrent.practice.test;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* Q1:隐士锁与显示锁的区别
* A1:synchronized----隐士锁
* Lock----显示锁----支持无条件/可中断/可定时/可轮询
* Q2:乐观锁与悲观锁的区别
* Q2:悲观锁在每次访问数据时,都进行加锁操作,适用于多写的场景
* 乐观锁----每次访问数据不进行加锁,当更新操作时,会去回补哪些操作过数据,适用于多读的场景
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月18日
* @version 1.0.0
 */
public class LockTest {

}
