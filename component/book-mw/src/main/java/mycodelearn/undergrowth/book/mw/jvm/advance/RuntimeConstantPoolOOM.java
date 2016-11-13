package mycodelearn.undergrowth.book.mw.jvm.advance;

import org.junit.Test;

/**
 * @author zhangwu
 * @version 1.0.0
 * @description 运行时常量区内存溢出
 * @date 2016-11-13-16:27
 */
public class RuntimeConstantPoolOOM {

	@Test
	public void testCPOOM(){
		String str1=new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern()==str1);

		String str2=new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern()==str2);
	}

}
