package mycodelearn.undergrowth.basiclearn.io.test;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Calendar;
import java.util.Locale;

import org.junit.Test;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* * 字符流学习笔记
	 * Writer-->字符流写
	 * 		 -->BufferedWriter
	 * 		 -->OutputStreamWriter
	 * 		 	   -->FileWriter
	 * 		 -->PrintWriter
	 * 
	 * Reader-->字符流读
	 * 		 -->BufferedReader
	 * 			 -->LineNumberReader
	 * 		 -->InputStreamReader
	 * 		    -->FileReader
	 * 
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月11日
* @version 1.0.0
 */
public class CharStream {

	/**
	 * 测试字符流写 使用BufferedWriter进行缓冲字符流
	 */
	@Test
	public void testWriter() {
		PrintWriter pwWriter = null;
		isFileExist(filePath);
		filePath = new File(filePath, getFileName());
		try {
			pwWriter = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
			pwWriter.write(testOutString);
			pwWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeWriter(pwWriter);
		}
	}

	/**
	 * 测试字符流读 使用BufferedReader进行缓冲字符流
	 */
	@Test
	public void testReader() {
		BufferedReader brReader = null;
		isFileExist(filePath);
		filePath = new File(filePath, getFileName());
		try {
			StringBuilder builder = new StringBuilder();
			String tmpString = null;
			brReader = new BufferedReader(new FileReader(filePath));
			while ((tmpString = brReader.readLine()) != null) {
				builder.append(tmpString);
			}
			System.out.println(builder.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeReader(brReader);
		}
	}

	// 输出路径
	String pathname = "d:\\log\\";
	File filePath = new File(pathname);
	// 测试输出的字符串
	String testOutString = "测试输出的WriterReaderLearn字符串";

	/**
	 * 关闭读字符流
	 * 
	 * @param reader
	 */
	private void closeReader(Reader reader) {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭写字符流
	 * 
	 * @param writer
	 */
	private void closeWriter(Writer writer) {
		if (writer != null) {
			try {
				writer.close();
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
				+ calendar.get(Calendar.DAY_OF_MONTH) + "_" + CharStream.class.getSimpleName() + ".txt";
		return fileName;
	}
}
