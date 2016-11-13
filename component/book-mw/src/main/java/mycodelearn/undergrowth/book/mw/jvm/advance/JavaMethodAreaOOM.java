package mycodelearn.undergrowth.book.mw.jvm.advance;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhangwu
 * @version 1.0.0
 * @description java方法区溢出
 *
 * -verbose:gc -Xms10M -Xmx10M -Xmn8M -Xss1024K -XX:PermSize=10M -XX:MaxPermSize=10M -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * @date 2016-11-13-16:34
 */
public class JavaMethodAreaOOM {

	public JavaMethodAreaOOM() {
	}

	@Test
	public void testMAOOM() {
		while (true) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObkect.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				@Override
				public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
					return methodProxy.invokeSuper(o, objects);
				}
			});
			enhancer.create();
		}
	}

	class OOMObkect{
		public OOMObkect() {
		}
	}

}
