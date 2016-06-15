package mycodelearn.undergrowth.basiclearn.lang.test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 
 * Description: TODO(用于测试String StringBuffer StringBuilder)
 * @author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
 * Date 2016年6月7日
 * @version 1.0.0
 */
public class StringTest {

	/**
	 * 不可变的String类 final String +-->字符串连接符以及将其他对象转为字符串的特殊支持 比较、查找、复制、提取、转换、检查 操作
	 * 
	 * @author Administrator
	 * 
	 */

	/**
	 * 获取系统所有字符集
	 */
	@Test
	public void testAvailCharset() {
		Map<String, Charset> charsetMap = Charset.availableCharsets();
		System.out.println("总共有字符集:" + charsetMap.size());
		System.out.println("默认字符集为:" + Charset.defaultCharset().displayName());
		System.out.println("遍历支持字符集如下:");
		for (Map.Entry<String, Charset> charsetEntry : charsetMap.entrySet()) {
			System.out.println(charsetEntry.getKey() + ":" + charsetEntry.getValue().displayName() + "\t"
					+ charsetEntry.getValue().name());
		}

	}

	/**
	 * 显示系统支持的语言相关的政治、地理、文化区域 系统支持的语言环境
	 */
	@Test
	public void testAvailLocale() {
		Locale[] locales = Locale.getAvailableLocales();
		for (Locale locale : locales) {
			System.out.println(locale.getCountry() + "\t" + locale.getDisplayCountry() + "\t" + locale.getDisplayName()
					+ "\t" + locale.getLanguage() + "\t" + locale.getDisplayLanguage());
		}
	}

	// 测试字符串
	String testString = "123@987@,qwert:@op";
	// 测试比较字符串
	String otherTestString = "123@987@,QWERT:@op";
	// 测试字符数组
	char[] testCharArray = testString.toCharArray();

	/**
	 * 测试字符串的查找功能
	 */
	@Test
	public void testLook() {
		System.out.println("待测试字符串为:" + testString);
		// 获取最后一个字符
		System.out.println("testString.charAt(testString.length()-1)-->" + testString.charAt(testString.length() - 1));
		System.out.println(
				"testString.codePointAt(testString.length()-1)-->" + testString.codePointAt(testString.length() - 1));
		System.out.println("testString.codePointBefore(testString.length()-1)-->"
				+ testString.codePointBefore(testString.length() - 1));
		System.out.println("testString.codePointCount(testString.length()-2, testString.length())-->"
				+ testString.codePointCount(testString.length() - 2, testString.length()));
		// 查找索引
		System.out.println(testString.indexOf("qw"));
		System.out.println(testString.indexOf("qw", 3));
		System.out.println(testString.lastIndexOf("@"));
		System.out.println(testString.lastIndexOf("@", 3));
		// 长度
		System.out.println(testString.length());
		System.out.println(testString.offsetByCodePoints(2, 5));
	}

	/**
	 * 测试字符串比较
	 */
	@Test
	public void testComparable() {
		System.out.println(otherTestString.compareTo(testString));
		System.out.println(otherTestString.compareToIgnoreCase(testString));
		System.out.println(otherTestString.equals(testString));
		System.out.println(otherTestString.equalsIgnoreCase(testString));
		// 正则表达式进行匹配
		testString = "12345@qwert";
		String regex = "[0-9]{1,}@[a-z]{1,}";
		System.out.println(testString.matches(regex));
		// 正则表达式匹配
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(testString);
		System.out.println(matcher.matches());
		testString = "123@987@,qwert:@op";
		// 区域匹配
		System.out.println(testString.regionMatches(0, otherTestString, 0, testString.length()));
		System.out.println(testString.regionMatches(0, otherTestString, 0, 7));
	}

	/**
	 * 测试字符串复制
	 */
	@Test
	public void testCopy() {
		System.out.println(otherTestString.concat(testString));
		System.out.println(String.copyValueOf(testCharArray));
		System.out.println(String.copyValueOf(testCharArray, 2, 5));
		// 复制到字符数组
		char[] dst = new char[1024];
		testString.getChars(0, testString.length(), dst, 5);
		System.out.println(dst);
		// 字符串的内部规范化表示
		System.out.println(testString.intern());
	}

	/**
	 * 字符串检查
	 */
	@Test
	public void testCheck() {
		System.out.println(otherTestString.contains(testString));
		System.out.println(otherTestString.contentEquals(testString));
		// 结束
		System.out.println(testString.endsWith("op"));
		System.out.println(testString.endsWith("oP"));
		// 是否为空
		System.out.println(testString.isEmpty());
		// 开头
		System.out.println(testString.startsWith("23"));
		System.out.println(testString.startsWith("123"));
		System.out.println(testString.startsWith("@", 3));
	}

	/**
	 * 测试字符串转换
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void testTransform() throws UnsupportedEncodingException {
		// String的静态方法进行转换
		System.out.println(String.format(testString));
		System.out.println(String.format(testString + "\t %d %o %x", 100, 100, 100));
		System.out.println(String.format(Locale.CANADA, "你好," + testString + "\t %d %o %x", 100, 100, 100));
		// 默认采用GBK字符集获取字节码
		System.out.println(testString.getBytes());
		System.out.println(testString.getBytes(Charset.forName("UTF-8")));
		System.out.println(testString.getBytes("ISO-8859-1"));
		// 转换为小写、大写
		System.out.println(otherTestString.toLowerCase());
		System.out.println(testString.toUpperCase());
		// 去除空白
		System.out.println(String.valueOf("  " + otherTestString + "  ").trim());
		System.out.println(String.valueOf(12.367));
	}

	/**
	 * 字符串提取操作
	 */
	@Test
	public void testExtract() {
		// 替换
		System.out.println(testString.replace("@", "连接符"));
		System.out.println(testString.replaceFirst("@", "连接符"));
		// 分割
		String[] testArrayStrings = testString.split("@");
		for (String string : testArrayStrings) {
			System.out.println(string);
		}
		// 截取
		System.out.println(testString.substring(3));
		System.out.println(testString.substring(3, 6));
	}

}
