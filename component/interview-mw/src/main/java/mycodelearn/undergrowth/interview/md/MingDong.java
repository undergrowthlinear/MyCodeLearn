package mycodelearn.undergrowth.interview.md;

import org.junit.Test;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月12日
* @version 1.0.0
 */
public class MingDong {
	/**
	 * Q1:Oracle中如何进行分页
	 * A1:使用伪列(rownum),rownum的值从1开始赋值,不满足条件,则舍去,继续从1开始赋值,因此当使用rownum>2,这种情况时,
	 * 需要借助别名列进行条件的限定
	 */

	@Test
	public void testString() {
		// 2个 一个执行xyz 一个指向new String
		String string = new String("xyz");
		System.out.println(string);
	}
	
	/**
	 * Q2:PreparedStatement比Statement效率高
	 * A2:PreparedStatement对SQL进行了预编译,只用第一次进行编译,Statement每次执行都需要编译,因此前者比后者效率高
	 * PreparedStatement支持命名和位置参数,可防止SQL注入,安全性也比Statement高
	 */

	/**
	 * Q3:Spring的事务管理方式及各自运用场所
	 * A3:编码式----支持细粒度控制事务的边界
	 * 声明式----基于AOP,有助于用户将操作与事务进行解耦
	 */
	
}