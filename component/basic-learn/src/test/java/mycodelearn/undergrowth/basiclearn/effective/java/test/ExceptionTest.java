package mycodelearn.undergrowth.basiclearn.effective.java.test;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月21日
* @version 1.0.0
 */
public class ExceptionTest {
	
	/**
	 * 57.异常是为了在异常情况下使用而设计的
	 * 在现代JVM中,基于异常的模式比标准模式要慢得多
	 */
	
	/**
	 * 58.throwable(抛出结构)
	 * 受检异常(Exception及其子类)----编译---如果期望调用者能够适当恢复,则使用受检异常
	 * 非受检异常(RuntimeException)----运行----表明编程错误
	 * 错误(Error)----表明资源不足/约束失败/其他无法执行的条件
	 */
	
	/**
	 * 59.避免不必要的使用受检的异常----过分的受检异常会使API使用起来非常不方便
	 * 可考虑将受检异常转换为状态测试+非受检异常
	 */
	
	/**
	 * 60.优先使用标准的异常
	 * IllegalArgumentException
	 * IllegalStateException
	 * NullPointerException
	 * IndexOutOfBoundsException
	 * ConcurrentModificationException
	 * UnsupportedOperationException
	 * NumberFormatException
	 * ArithmeticException
	 */
	
	/**
	 * 61.抛出与抽象相对应的异常----
	 * 异常转译(exception translation)----将底层异常抛出为高层异常
	 * 异常链(exception chaining)----将底层异常转换为高层异常
	 */
	
	/**
	 * 62.每个方法抛出的异常都要有文档----描述方法所抛出的异常,是正确使用这个方法所需文档的重要组成部分
	 */
	
	/**
	 * 63.在细节消息中包含能捕获失败的信息----异常的细节信息应该是包含"对该异常有贡献"的参数/域值
	 */
	
	/**
	 * 64.努力使失败保持原子性----失败的方法应该使对象保持在被调用之前
	 * 获得失败原子性:
	 * 参数的检查/计算顺序的调整/写恢复代码/临时拷贝后替换
	 */
	
	/**
	 * 65.不要忽略异常----将异常传播出去,有助于调试该异常
	 */
	
}
