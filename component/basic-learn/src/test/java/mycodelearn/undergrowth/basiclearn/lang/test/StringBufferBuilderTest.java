package mycodelearn.undergrowth.basiclearn.lang.test;

import org.junit.Test;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * @author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
 * Date 2016年6月7日
 * @version  1.0.0
 */
public class StringBufferBuilderTest {

	/**
	 * StringBuffer-->可变的字符序列 线程安全的字符缓冲区 在字符串缓冲区进行同步 主要操作是-->append(将字符添加到缓冲区末尾)
	 * insert(将字符添加到指定位置)
	 * 
	 * StringBuilder-->可变字符序列 但不保证同步，需外部同步 属于StringBuffer的简易版本
	 * 主要操作与StringBuffer一致
	 * 
	 * @author Administrator
	 * 
	 */

	String str = "测试StringBuffer";
	StringBuffer sbBuffer = new StringBuffer();
	StringBuffer sbBuffer2 = new StringBuffer(30);
	StringBuffer sbBuffer3 = new StringBuffer(str);

	/**
	 * 测试长度与容量
	 */
	@Test
	public void testLength() {
		System.out.println(sbBuffer.capacity());
		System.out.println(sbBuffer.length());
		System.out.println(sbBuffer2.capacity());
		System.out.println(sbBuffer2.length());
		System.out.println(sbBuffer3.capacity());
		System.out.println(sbBuffer3.length());
		//
		sbBuffer2.ensureCapacity(50);
		System.out.println(sbBuffer2.capacity());
		System.out.println(sbBuffer2.length());
	}

	/**
	 * 测试Append-->附加到缓冲区末尾
	 */
	@Test
	public void testAppend() {
		System.out.println(sbBuffer.append(Boolean.TRUE).append('a').append(sbBuffer3));
	}

	/**
	 * 测试insert-->将字符添加到指定位置
	 */
	@Test
	public void testInsert() {
		System.out.println(sbBuffer3);
		System.out.println(sbBuffer3.insert(3, "插入字符"));
	}

	/**
	 * 查找方法与String的也很类似
	 */
	@Test
	public void testLook() {
		System.out.println(sbBuffer3);
		System.out.println(sbBuffer3.charAt(5));
		System.out.println(sbBuffer3.codePointAt(5));
		System.out.println(sbBuffer3.codePointBefore(5));
		// 获取字符数组
		System.out.println(sbBuffer);
		char[] dst = new char[1024];
		sbBuffer3.getChars(0, sbBuffer3.length(), dst, 0);
		System.out.println(sbBuffer.append(dst));
		// 索引
		System.out.println(sbBuffer3.indexOf("字符串"));
		//
		System.out.println(sbBuffer3);
		System.out.println(sbBuffer3.lastIndexOf("你好"));
	}

	/**
	 * 测试删除字符操作
	 */
	@Test
	public void testDelete() {
		System.out.println(sbBuffer3);
		System.out.println(sbBuffer3.deleteCharAt(5));
		System.out.println(sbBuffer3.delete(1, 5));
	}

	/**
	 * 测试其他操作
	 */
	@Test
	public void testOther() {
		System.out.println(sbBuffer3.toString());
		System.out.println(sbBuffer3.reverse());
		System.out.println(sbBuffer3);
		System.out.println(sbBuffer3.replace(0, 3, "替换字符串后的字符序列"));
		System.out.println(sbBuffer3.substring(0, 10));
		System.out.println(sbBuffer3);
		System.out.println(sbBuffer3.capacity());
		System.out.println(sbBuffer3.length());
		sbBuffer3.trimToSize();
		System.out.println(sbBuffer3.capacity());
		System.out.println(sbBuffer3.length());
	}
}
