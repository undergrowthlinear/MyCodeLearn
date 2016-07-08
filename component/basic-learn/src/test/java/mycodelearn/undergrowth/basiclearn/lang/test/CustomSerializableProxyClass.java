package mycodelearn.undergrowth.basiclearn.lang.test;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Calendar;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* 自定义的序列化类
* 自定义的序列化类
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月15日
* @version  1.0.0
 */
public class CustomSerializableProxyClass implements Serializable {

	private static final int NUM = 10;
	private String name;
	private volatile Calendar calendar;
	// transient关键字进行修饰 表示此字段会从默认的序列化形式中省略
	private transient Double numDouble = 100.0;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public CustomSerializableProxyClass() {
		// TODO Auto-generated constructor stub
	}

	public CustomSerializableProxyClass(String name, Calendar calendar) {
		super();
		this.name = name;
		this.calendar = calendar;
	}

	@Override
	public String toString() {
		return "CustomSerializableClass [name=" + name + ", calendar=" + calendar.getTime().toString() + ",NUM=" + NUM
				+ ",numDouble=" + numDouble + "]";
	}

	/**
	 * 使用序列化代理序列化此类
	 * 
	 * @return
	 */
	private Object writeReplace() {
		return new SerialzlizableProxy(this);
	}

	/**
	 * 使用序列化代理 序列化谁，反序列化时就调用谁的方法 对外围类进行序列化时，使用序列化代理进行序列化 对外围类进行反序列化时，使用代理类返回外围类
	 * 
	 * @author Administrator
	 *
	 */
	private static class SerialzlizableProxy implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String name;

		public SerialzlizableProxy(CustomSerializableProxyClass customSerializableClass) {
			// TODO Auto-generated constructor stub
			this.name = customSerializableClass.name;
		}

		private Object readResolve() {
			System.out.println("代理类进行反序列化-->" + name);
			return new CustomSerializableProxyClass("使用序列化代理，替换过后的反序列化值", Calendar.getInstance());
		}
	}

	/**
	 * 防止从外围类进行调用
	 * 
	 * @param s
	 * @throws InvalidObjectException
	 */
	private void readObject(ObjectInputStream s) throws InvalidObjectException {
		throw new InvalidObjectException("使用序列化代理返回");
	}

}
