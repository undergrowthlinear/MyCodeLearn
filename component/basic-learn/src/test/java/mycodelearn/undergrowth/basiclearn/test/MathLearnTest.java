package mycodelearn.undergrowth.basiclearn.test;


import org.junit.Test;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * @author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
 * Date 2016年6月7日
 * @version 1.0.0
 */
public class MathLearnTest {

	/**
	 * 测试Math的常量
	 */
	@Test
	public void testMathConstant() {
		System.out.println(Math.E);
		System.out.println(Math.PI);
	}

	@Test
	public void testMathMethod() {
		// 绝对值 Math.abs(-19)
		System.out.println("绝对值 Math.abs(-19)");
		System.out.println(Math.abs(-19));
		System.out.println("绝对值 Math.abs(19)");
		System.out.println(Math.abs(19));
		// 向上取整 Math.ceil(-19.1)
		System.out.println("向上取整 Math.ceil(-19.1)");
		System.out.println(Math.ceil(-19.1));
		System.out.println("向上取整 Math.ceil(-19.8)");
		System.out.println(Math.ceil(-19.8));
		// 立方根
		System.out.println("立方根  Math.cbrt(9) x*x*x=9");
		System.out.println(Math.cbrt(9));
		// e的多少次幂
		System.out.println("e的多少次幂 Math.exp(2)");
		System.out.println(Math.exp(2));
		// 向下取整
		System.out.println("向下取整 Math.floor(-19.2)");
		System.out.println(Math.floor(-19.2));
		System.out.println("向下取整 Math.floor(-19.7)");
		System.out.println(Math.floor(-19.7));
		// 自然对数 e
		System.out.println("自然对数 e Math.log(Math.E)");
		System.out.println(Math.log(Math.E));
		// 10为底的指数
		System.out.println("10为底的指数 Math.log10(10)");
		System.out.println(Math.log10(10));
		// 最大数
		System.out.println("最大数 Math.max(10, 20)");
		System.out.println(Math.max(10, 20));
		// 最小数
		System.out.println("最大数 Math.min(10, 20)");
		System.out.println(Math.min(10, 20));
		// 第一个参数和第二参数之间的数 相邻
		System.out.println(Math.nextAfter(20, 30));
		System.out.println(Math.nextUp(20));
		// 幂
		System.out.println("幂 Math.pow(10, 10)");
		System.out.println(Math.pow(10, 10));
		// 随机数
		System.out.println("随机数[0,1) Math.random()");
		System.out.println(Math.random());
		// 四舍五入
		System.out.println(Math.round(10.2));
		System.out.println(Math.round(10.8));
		System.out.println(Math.round(-10.2));
		System.out.println(Math.round(-10.8));
		// rint 最接近的整数
		System.out.println(Math.rint(10.2));
		System.out.println(Math.rint(10.6));
		System.out.println(Math.rint(-10.2));
		System.out.println(Math.rint(-10.6));
		// f × 2scaleFactor
		System.out.println(" f × 2scaleFactor Math.scalb(5, 2)");
		System.out.println(Math.scalb(5, 2));
		// 参数的符号
		System.out.println(Math.signum(98));
		System.out.println(Math.signum(-98));
		// sqrt
		System.out.println(Math.sqrt(4));
	}
}
