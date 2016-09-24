package mycodelearn.undergrowth.basiclearn.lang.test;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* * enum枚举和注解的学习笔记  
	 * 枚举测试用例-->testEnumClass
	 * 枚举类型-->有一组固定的常量组成合法值的类型
	 * 枚举类型提供了编译时的类型安全
	 * 枚举天生就是不可变的，因此所有域都应该是final
	 * 枚举常量实际上是static final的常量
	 * 特定于常量的类主体--枚举的抽象方法
	 * 虽然无法编写可扩展的枚举类型，但是可以通过接口的方式为枚举模拟扩展
	 * 测试位域  用例-->testEnumSet
	 * 测试Map  用例-->testEnumMap
	 * 
	 * 注解
	 * 元注解---->注解类型声明中的注解  private @interface TestSome
	 * 标记注解---->标注被注解的元素    @TestSome
	 * 解析注解---对标记注解按照元注解的实现进行解析  测试用例-->testSome
	 * 坚持使用Override注解，表明此方法是覆盖超类型中的一个声明，也可防止重载Overload
	 * 标记接口---->没有包含方法声明的接口，只是指明类实现了具有某种属性的接口(特定属性)
	 *  Cloneable、Serializable等
	 *  如果想要使用类型，一定要定义接口
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月15日
* @version  1.0.0
 */
public class EnumAnnotationTest {

	@Test
	public void testEnumClass() {
		// 枚举类型自动产生的valueOf(String) ,将常量的名字转为常量本身
		System.out.println(EnumClass.ADD);
		// values()按照声明顺序返回它的值数组
		System.out.println("values()-->按照声明顺序返回它的值数组");
		System.out.println("操作数10.0和5.0");
		for (EnumClass ec : EnumClass.values()) {
			System.out.print(ec + "\t序列号:" + ec.ordinal() + "\t" + "代表符号:" + ec.getName() + "\t");
			System.out.println(String.valueOf(ec.apply(10.0, 5.0)));

		}

	}

	private enum EnumClass {
		ADD("+") {
			@Override
			Double apply(Double op1, Double op2) {
				// TODO Auto-generated method stub
				return op1 + op2;
			}
		},
		MINUS("-") {
			@Override
			Double apply(Double op1, Double op2) {
				// TODO Auto-generated method stub
				return op1 - op2;
			}
		},
		MULTI("*") {
			@Override
			Double apply(Double op1, Double op2) {
				// TODO Auto-generated method stub
				return op1 * op2;
			}
		},
		DIVIDE("/") {
			@Override
			Double apply(Double op1, Double op2) {
				// TODO Auto-generated method stub
				return op1 / op2;
			}
		};

		private final String name;

		EnumClass(String name) {
			this.name = name;
		}

		// 枚举的抽象方法 就是特定于常量的类主体
		abstract Double apply(Double op1, Double op2);

		public String getName() {
			return name;
		}

	}

	/**
	 * 测试位域 使用EnumSet替代位域
	 */
	@Test
	public void testEnumSet() {
		Set<EnumClass> enumSet = EnumSet.of(EnumClass.ADD, EnumClass.MINUS);
		for (EnumClass enumClass : enumSet) {
			System.out.println(enumClass.getName() + "\t" + enumClass.ordinal());
		}
	}

	/**
	 * 测试枚举类型键 使用EnumMap替代序列
	 */
	@Test
	public void testEnumMap() {
		Map<EnumClass, String> enmuMap = new EnumMap<>(EnumClass.class);
		enmuMap.put(EnumClass.DIVIDE, "除数不能为0");
		for (Map.Entry<EnumClass, String> entry : enmuMap.entrySet()) {
			System.out.println(entry.getKey().getName() + "\t" + entry.getValue());
		}
	}

	/**
	 * 元注解 Retention-->表示注解应该在什么时候保留 Target-->表示注解应该应用的范围
	 * 此注解只能用于无参数的静态方法上，用于添加欢迎信息
	 * 
	 * @author Administrator
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	private @interface TestSome {
		String values() default "";
	}

	@Test
	public void testSome() {
		System.out.println("测试注解\t" + TestSome.class);
		Class<AnnotationTest> anClass = AnnotationTest.class;
		int countAnno = 0, pass = 0;
		for (Method method : anClass.getDeclaredMethods()) {
			if (method.isAnnotationPresent(TestSome.class)) {
				countAnno++;
				try {
					method.invoke(null);
					// 可以添加更多的功能
					System.out.println("你使用了欢迎注解，并且成功调用，谢谢使用");
					pass++;
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		System.out.println("总共有注解方法" + countAnno + "\t通过测试注解方法" + pass);
	}

	public static class AnnotationTest {
		@TestSome()
		public static void hello() {
			System.out.println(AnnotationTest.class.getName() + "\t hello world annotation");
		}

		@TestSome
		public void hello(String name) {
			System.out.println(AnnotationTest.class.getName() + "\t " + name + " annotation");
		}

		public void hello(String name, String sex) {
			System.out.println(AnnotationTest.class.getName() + "\t " + name + sex + " annotation");
		}
	}

}
