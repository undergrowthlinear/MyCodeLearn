package mycodelearn.undergrowth.basiclearn.guava.test;

import org.junit.Test;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用) 
 * 变量先定义再赋值后使用 
 * null是一个不确定对象
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年8月5日
 * @version 1.0.0
 */
public class GuavaLearn {

	@Test
	public void testNull() {
		int age = 0;
		System.out.println("user age:" + age);

		long money;
		money = 10L;
		System.out.println("user money:" + money);

		String name = null;
		System.out.println("user name:" + name);
	}

	@Test
	public void testNullObject() {
		if (null instanceof java.lang.Object) {
			System.out.println("null属于java.lang.Object类型");
		} else {
			System.out.println("null不属于java.lang.Object类型");
		}
	}

}
