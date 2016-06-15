package mycodelearn.undergrowth.basiclearn.io.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Locale;

import org.junit.Test;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* 主要的就是字节流与字符流
	字节流以OutputStream、InputStream
	字符流以Writer、Reader
	字节流与字符流的桥梁为OutputStreamWriter、InputStreamReader
	缓冲字符流的为BufferedWriter、BufferedReader
* 输出流学习笔记
	 * OutputStream(抽象类) 
	 *    -->FileOutputStream
	 *    -->ObjectOutputStream(在SerializableLearn中进行测试)
	 *    -->ByteArrayOutputStream
	 *  
	 * 输入流学习笔记
	 *  InputStream
	 *  	-->FileInputStream
	 *  	-->ObjectInputStream
	 *  	-->ByteArrayInputStream 
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月11日
* @version 1.0.0
 */
public class ByteStream {

	// 输出路径
	String pathname = "d:\\log\\";
	File filePath = new File(pathname);
	OutputStream osStream = null;
	ObjectOutputStream ooStream = null;
	InputStream isStream = null;
	ObjectInputStream oisStream = null;
	// 测试输出的字符串
	String testOutString = "测试输出的FileOutputStream字符串";
	byte[] b = new byte[testOutString.getBytes().length];

	/**
	 * 测试输出流数据 文件输出流-->用于将文件写入File或FileDescriptor的输出流
	 */
	@Test
	public void testFileOutputStream() {
		isFileExist(filePath);
		filePath = new File(filePath, getFileName());
		try {
			osStream = new FileOutputStream(filePath);
			osStream.write(testOutString.getBytes());
			osStream.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeOutStream(osStream);
		}

	}

	/**
	 * 测试文件输入流
	 */
	@Test
	public void testFileInputStream() {
		isFileExist(filePath);
		filePath = new File(filePath, getFileName());
		try {
			isStream = new FileInputStream(filePath);
			isStream.read(b);
			System.out.println(new String(b));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeInStream(isStream);
		}
	}

	/**
	 * 测试对象输出流
	 */
	@Test
	public void testObjectOutputStream() {
		isFileExist(filePath);
		filePath = new File(filePath, getFileName());
		try {
			osStream = new FileOutputStream(filePath);
			ooStream = new ObjectOutputStream(osStream);
			ooStream.writeObject(testOutString);
			ooStream.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeOutStream(ooStream);
			closeOutStream(osStream);
		}
	}

	/**
	 * 测试对象输入流
	 */
	@Test
	public void testObjectInputStream() {
		isFileExist(filePath);
		filePath = new File(filePath, getFileName());
		try {
			isStream = new FileInputStream(filePath);
			oisStream = new ObjectInputStream(isStream);
			String resultString = (String) oisStream.readObject();
			System.out.println(resultString);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeInStream(oisStream);
			closeInStream(isStream);
		}
	}

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
	 * 关闭输入流
	 * 
	 * @param osStream2
	 */
	private void closeInStream(InputStream isStream) {
		// TODO Auto-generated method stub
		if (isStream != null) {
			try {
				isStream.close();
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
				+ calendar.get(Calendar.DAY_OF_MONTH) + "_" + ByteStream.class.getSimpleName() + ".txt";
		return fileName;
	}

	/**
	 * 测试字节数组输出流 ByteArrayOutputStream-->将构建一个缓冲区，将数据写入字节数组
	 */
	@Test
	public void testByteArrayOutputStream() {
		int size = 1000;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(size);
		try {
			byteArrayOutputStream.write(testOutString.getBytes());
			System.out.println(new String(byteArrayOutputStream.toByteArray()));
			// 使用平台默认的字符集将字节码转换为字符串
			System.out.println(byteArrayOutputStream.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeOutStream(byteArrayOutputStream);
		}
	}

	/**
	 * 打印各种数据值的表现形式
	 */
	@Test
	public void testPrintStream() {
		PrintStream psStream = new PrintStream(System.out);
		psStream.append('Q');
		psStream.println();
		psStream.print(true);
		psStream.println();
		try {
			psStream.write(testOutString.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
