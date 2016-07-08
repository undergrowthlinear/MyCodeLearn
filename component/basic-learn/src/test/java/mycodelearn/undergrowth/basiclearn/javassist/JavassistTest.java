package mycodelearn.undergrowth.basiclearn.javassist;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtField.Initializer;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月6日
 * @version 1.0.0
 */
public class JavassistTest {

	@Test
	public void testJavassist()
			throws CannotCompileException, NotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		ClassPool cp = ClassPool.getDefault();
		CtClass ctClass = cp.makeClass("mycodelearn.undergrowth.basiclearn.javassist.JavassistClass1");

		StringBuffer body = null;
		// 参数 1：属性类型 2：属性名称 3：所属类CtClass
		CtField ctField = new CtField(cp.get("java.lang.String"), "name", ctClass);
		ctField.setModifiers(Modifier.PRIVATE);
		// 设置name属性的get set方法
		ctClass.addMethod(CtNewMethod.setter("setName", ctField));
		ctClass.addMethod(CtNewMethod.getter("getName", ctField));
		ctClass.addField(ctField, Initializer.constant("default"));

		// 参数 1：参数类型 2：所属类CtClass
		CtConstructor ctConstructor = new CtConstructor(new CtClass[] {}, ctClass);
		body = new StringBuffer();
		body.append("{\n name=\"me\";\n}");
		ctConstructor.setBody(body.toString());
		ctClass.addConstructor(ctConstructor);

		// 参数： 1：返回类型 2：方法名称 3：传入参数类型 4：所属类CtClass
		CtMethod ctMethod = new CtMethod(CtClass.voidType, "execute", new CtClass[] {}, ctClass);
		ctMethod.setModifiers(Modifier.PUBLIC);
		body = new StringBuffer();
		body.append("{\n System.out.println(name);");
		body.append("\n System.out.println(\"execute ok\");");
		body.append("\n return ;");
		body.append("\n}");
		ctMethod.setBody(body.toString());
		ctClass.addMethod(ctMethod);
		Class<?> c = ctClass.toClass();
		Object o = c.newInstance();
		Method method = o.getClass().getMethod("execute", new Class[] {});
		// 调用字节码生成类的execute方法
		method.invoke(o, new Object[] {});

	}

	@Test
	public void testArray() {
		String[] teStrings = new String[] {};
		System.out.println(teStrings.length);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testProxy() throws InstantiationException, IllegalAccessException {
		ProxyFactory factory = new ProxyFactory();
		// 设置父类，ProxyFactory将会动态生成一个类，继承该父类
		factory.setSuperclass(JavassistClass.class);
		// 设置过滤器，判断哪些方法调用需要被拦截
		factory.setFilter(new MethodFilter() {
			@Override
			public boolean isHandled(Method m) {
				if (m.getName().equals("getName")) {
					return true;
				}
				return false;
			}
		});
		// 设置拦截处理
		factory.setHandler(new MethodHandler() {
			@Override
			public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
				// 拦截后前置处理，改写name属性的内容
				// 实际情况可根据需求修改
				JavassistClass o = (JavassistClass) self;
				o.setName("haha");
				return proceed.invoke(self, args);
			}
		});

		Class<?> c = factory.createClass();
		JavassistClass object = (JavassistClass) c.newInstance();
		System.out.println(object.getName());

		System.out.println(new JavassistClass().getName());

	}
}
