package mycodelearn.undergrowth.basiclearn.lang.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;

public class CustomSerializableClass implements Serializable {
	

	

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
	public CustomSerializableClass() {
		// TODO Auto-generated constructor stub
	}

	public CustomSerializableClass(String name, Calendar calendar) {
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
	 * 控制序列化过程
	 * 
	 * @param s
	 */
	private void writeObject(ObjectOutputStream s) {
		// 默认将当前类的非静态和非瞬态类写入
		try {
			s.defaultWriteObject();
			// 将瞬态的状态写入 不然默认的序列化形式 是不写入的 进行反序列化的时候 会得到null
			s.writeDouble(numDouble);
			System.out.println("序列化成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 控制反序列化过程
	 * 
	 * @param s
	 */
	private void readObject(ObjectInputStream s) {
		try {
			// 从流中读取当前类的非静态和非瞬态的状态
			s.defaultReadObject();
			numDouble = s.readDouble();
			System.out.println("反序列化成功");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 在反序列化的时候 替换其反序列化的对象
	 * 
	 * @return
	 */
	private Object readResolve() {
		return new CustomSerializableClass("替换过后的反序列化值", Calendar.getInstance());

	}

}
