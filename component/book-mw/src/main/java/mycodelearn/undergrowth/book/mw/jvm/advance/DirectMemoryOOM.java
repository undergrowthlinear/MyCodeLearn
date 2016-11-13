package mycodelearn.undergrowth.book.mw.jvm.advance;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author zhangwu
 * @version 1.0.0
 * @description 直接内存区域
 * -verbose:gc -Xms10M -Xmx10M -Xmn8M -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxDirectMemorySize=10M
 * @date 2016-11-13-16:45
 */
public class DirectMemoryOOM {

	private static final  int _1MB=1024*1024;

	@Test
	public  void testDMOOM() throws IllegalAccessException {
		Field unsafeField= Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe= (Unsafe) unsafeField.get(null);
		while (true){
			unsafe.allocateMemory(_1MB);
		}

	}

}
