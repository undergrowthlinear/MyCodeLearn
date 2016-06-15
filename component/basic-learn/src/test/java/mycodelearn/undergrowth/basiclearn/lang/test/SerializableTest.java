package mycodelearn.undergrowth.basiclearn.lang.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Locale;

import org.junit.Test;
/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* * 序列化与反序列化的学习笔记 测试 
	 * ObjectOutputStream--序列化(将对象编码为字节流) 
	 * ObjectInputStream--反序列化(从字节流中重构对象/用字节流转为唯一参数的构造器)
	 * Serializable-->为标记接口，实现之，则具有可序列功能
	 * 序列化形式
	 * 		-->会成为导出API的一部分
	 * 		-->默认的序列化形式(描述对象内部所包含的数据，以及每一个可以从这个对象到达的其他对象的内部数据)
	 * 		-->理想的序列化形式(只表示对象的逻辑结构)
	 * 序列版本UID-->如果未显示指定，则在运行此类时，根据此类的名称、实现接口名称、所有公有的和受保护的成员的名称所生成
	 * 
	 * 默认将当前类的非静态和非瞬态类写入
	 * s.defaultWriteObject()
	 * 从流中读取当前类的非静态和非瞬态的状态
	 * s.defaultReadObject()
	 * 
	 * 控制序列化写
	 * private void writeObject(ObjectOutputStream s)
	 * 控制序列化写替换
	 * private Object writeReplace()
	 * 控制序列化读
	 * private void readObject(ObjectInputStream s)
	 * 控制序列化读替换
	 * private  Object readResolve()
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月15日
* @version 1.0.0
 */
public class SerializableTest {

	/**
	 * 输出类库中自带的类-->已经序列化的 输出到控制台
	 */
	@Test
	public void testObjectOutStreamSystem() {
		// 输出到控制台
		try {
			oStream = new ObjectOutputStream(System.out);
			oStream.writeObject(Calendar.getInstance());
			oStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeOutStream(oStream);
		}
	}

	/**
	 * 输出类库中自带的类-->已经序列化的 输出到文件中
	 */
	@Test
	public void testObjectOutStreamFile() {
		// 输出到文件中
		outPutFileStream(Calendar.getInstance());
	}

	/**
	 * 将对象写入到输出流中
	 * 
	 * @param object
	 */
	private void outPutFileStream(Object object) {
		try {
			isFileExist(filePath);
			filePath = new File(filePath, getFileName());
			oStream = new ObjectOutputStream(new FileOutputStream(filePath));
			oStream.writeObject(object);
			oStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeOutStream(oStream);
		}
	}

	/**
	 * 读取系统类库序列化的类，进行反序列化
	 */
	@Test
	public void testObjectInputStreamFile() {
		// 从文件中读入
		Calendar calendar = (Calendar) inPutFileStream();
		System.out.println(calendar.getTime().toString());
	}

	/**
	 * 读入文件流
	 */
	private Object inPutFileStream() {
		Object object = null;
		try {
			isFileExist(filePath);
			filePath = new File(filePath, getFileName());
			oisStream = new ObjectInputStream(new FileInputStream(filePath));
			object = oisStream.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeInputStream(oisStream);
		}
		return object;
	}

	/**
	 * 测试自定义的序列化类-->对序列化进行更多的控制
	 */
	@Test
	public void testObjectOutputStreamCustom() {
		CustomSerializableClass customSerializableClass = new CustomSerializableClass("自定义序列化", Calendar.getInstance());
		outPutFileStream(customSerializableClass);
	}

	/**
	 * 测试自定义的反序列化类--->对反序列化进行更多的控制
	 */
	@Test
	public void testObjectInputStreamCustom() {
		CustomSerializableClass customSerializableClass = (CustomSerializableClass) inPutFileStream();
		System.out.println(customSerializableClass);
	}

	/**
	 * 测试自定义的序列化类-->使用序列代理进行控制序列化
	 */
	@Test
	public void testObjectOutputStreamCustomProxy() {
		CustomSerializableProxyClass customSerializableClass = new CustomSerializableProxyClass("自定义序列化",
				Calendar.getInstance());
		outPutFileStream(customSerializableClass);
	}

	/**
	 * 测试自定义的反序列化类--->使用序列代理进行控制反序列化
	 */
	@Test
	public void testObjectInputStreamCustomProxy() {
		CustomSerializableProxyClass customSerializableClass = (CustomSerializableProxyClass) inPutFileStream();
		System.out.println(customSerializableClass);
	}

	/**
	 * 未实现Serializable接口 尝试输出 则会报错 java.io.NotSerializableException:
	 * com.undergrowth.io.CustomNoSerializableClass
	 */
	@Test
	public void testObjectNoSerializable() {
		CustomNoSerializableClass cuNo = new CustomNoSerializableClass("自定义序列化");
		outPutFileStream(cuNo);
	}

	/**
	 * 关闭输入流
	 * 
	 * @param oisStream2
	 */
	private void closeInputStream(ObjectInputStream oisStream2) {
		// TODO Auto-generated method stub
		if (oisStream2 != null) {
			try {
				oisStream2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 输出路径
	String pathname = "d:\\log\\";
	String fileNameSuffix = ".txt";
	File filePath = new File(pathname);
	ObjectOutputStream oStream = null;
	ObjectInputStream oisStream = null;

	/**
	 * 关闭输出流
	 * 
	 * @param osStream2
	 */
	private void closeOutStream(OutputStream osStream2) {
		// TODO Auto-generated method stub
		if (osStream2 != null) {
			try {
				osStream2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param filePath2
	 */
	private void isFileExist(File filePath2) {
		// TODO Auto-generated method stub
		if (!filePath2.exists())
			filePath2.mkdir();
	}

	/**
	 * 获取文件名
	 * 
	 * @return
	 */
	private String getFileName() {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		String fileName = calendar.get(Calendar.YEAR) + "_" + (calendar.get(Calendar.MONTH) + 1) + "_"
				+ calendar.get(Calendar.DAY_OF_MONTH) + "_" + SerializableTest.class.getSimpleName() + fileNameSuffix;
		return fileName;
	}
}
