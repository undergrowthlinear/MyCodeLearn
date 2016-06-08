package mycodelearn.undergrowth.basiclearn.test;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 *               Class类---Class类是反射的基石，Class类的实例表示正在运行的java应用程序中的接口或类。换句话说，
 *               Class类是用来描述java类的。
 *               Class类没有公共构造函数，所以有三种方式获取Class对象(Class对象代表内存中的一个字节码)： 1.类名.class
 *               2.对象名.getClass() 3.Class.forName(类的完全限定名()即包括包名) 而对于第三种方式
 *               Class.forName(类的完全限定名()即包括包名)
 * 
 *               不能获取基本数据类型的Class对象的原因是：
 * 
 *               If name denotes a primitive type or void, an attempt will be
 *               made to locate a user-defined class in the unnamed package
 *               whose name is name. Therefore, this method cannot be used to
 *               obtain any of the Class objects representing primitive types or
 *               void.
 * 
 *               上面这段英文是 forName(String name, boolean initialize,ClassLoader
 *               loader)
 *               方法中的解释：意思就是说，如果使用Class.forName()方法，当传进去的name为基本数据类型或void的话，
 *               类加载器就会在未定义的包名中查找用户定义的类。所以，这个方法是不能获取到基本数据类型或者void的Class对象的。
 * 
 *               因为在未定义的包中是无法查找到基本数据类型的或void的，基本数据类型通常是被bootstrap(引导类加载器)
 *               类加载器加载的
 * 
 *               总之：只要是在源程序中出现的类型，都有各自的Class类的实例对象。
 * @author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
 * Date 2016年6月7日
 * @version 1.0.0
 */
public class ReflectTest {

	@Test
	public void testFindClass() throws ClassNotFoundException {
		String str1 = "123";
		// 三种方法 获取String类的字节码
		Class cls1 = str1.getClass();
		Class cls2 = String.class;
		Class cls3 = Class.forName("java.lang.String");
		// 在虚拟机中每一个类只会有一份字节码
		System.out.println(cls1 == cls2);
		System.out.println(cls2 == cls3);
	}

	/**
	 * 对于java基本类型(boolean/char/byte/short/int/long/float/double/void)，
	 * 他们是默认的Class对象。 对于基本数据类型，只有一种方式获取到它的Class对象，即
	 */
	@Test
	public void testFindBasicClass() {
		System.out.println(int.class.toString());
	}

	/**
	 * 不能获取基本数据类型的Class对象的原因是：
	 * getClass()方法为Object对象的方法，而在java中，除了9中基本数据类型、Object类、接口外，其他任何类型的父类都是Object
	 * ,也就是说java中的基本数据类型是没有父类的。看下面这段代码，即可证明
	 */
	@Test
	public void testFindParent() {
		System.out.println("int 的父类为：" + int.class.getSuperclass()); // 基本数据类型没有父类
		System.out.println("short 的父类为：" + short.class.getSuperclass());
		System.out.println("String 的父类：" + String.class.getSuperclass());
	}

	/**
	 * 反射-----反射即是让java类中的各种成分映射成相应的java类。
	 * 
	 * Reflection allows programmatic access to information about the fields,
	 * methods and constructors of loaded classes, and the use of reflected
	 * fields, methods, and constructors to operate on their underlying
	 * counterparts, within security restrictions.
	 * 
	 * 反射允许我们访问类的字段、方法、构造器，同时可以利用反射字段、方法和构造器来进行相应的底层操作，在安全的约束内。
	 * 
	 * 3.构造器(Constructor)--Constructor provides information about, and access
	 * to, a single constructor for a class.
	 * 
	 * 构造器提供访问类的单个构造器的信息与方法
	 */

	@Test
	public void testCreateConstructor() {

		try {
			// 使用new的方式创建String对象
			String string1 = new String(new String("abc"));
			System.out.println(string1);

			// 使用反射构建String对象
			// 使用String类的字节码创建带一个String.class参数的构造器
			Constructor constructor = String.class.getConstructor(String.class);
			// 打印出构造器的描述信息 包括类型参数(如果有)
			System.out.println(constructor.toGenericString());
			// 创建类的新实例 并初始化
			String string = (String) constructor.newInstance("abc");
			// 打印生成的实例
			System.out.println(string);

			// 使用字节码直接构建String对象 但是无法提供参数
			String string2 = String.class.newInstance();
			System.out.println(string2.length());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 方法(Method)--A Method provides information about, and access to, a single
	 * method on a class or interface.
	 * 
	 * 提供访问类或接口的单个方法的信息
	 * 注意：对于Method类的invoke的第二个参数为可变参数，即在调用的时候从第二个参数开始，都是以一个数组的形式进行传递调用的。
	 * 
	 * 
	 */
	@Test
	public void testReflectMethod() {
		try {
			String string = "123456789";
			// 获取到String类中的charAt方法 并带有一个参数为int
			Method method = String.class.getMethod("charAt", int.class);
			// 调用string对象的charAt方法
			System.out.println(method.invoke(string, 2));
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 字段(Field)----A Field provides information about, and dynamic access to, a
	 * single field of a class or an interface.
	 * 
	 * 提供访问类或者接口的一个字段的动态信息
	 */
	@Test
	public void testReflectField() {
		try {
			ReflectFieldDefine reflectFieldDefine = new ReflectFieldDefine(3, 5);
			// getField只能获取Class对象的公共成员
			Field fieldY = reflectFieldDefine.getClass().getField("y");
			System.out.println(fieldY.get(reflectFieldDefine));
			// 获取Class对象的私有成员
			// getDeclaredField可以访问只要是在reflectFieldDefine对象上声明过的字段(包括私有的)
			Field fieldX = reflectFieldDefine.getClass().getDeclaredField("x");
			fieldX.setAccessible(true); // 反射的对象在使用时取消java语言访问检查 不然的话 私有的字段无法访问
			System.out.println(fieldX.get(reflectFieldDefine));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public class ReflectFieldDefine {
		private int x;
		public int y;

		public ReflectFieldDefine(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	/**
	 * 数组(Array)--The Array class provides static methods to dynamically create
	 * and access Java arrays. 提供静态的方法动态的创建和访问java数组。 相同的数据类型、相同维度的数组的字节码是一致的
	 * 会发现，数组的Class对象的名称由两部分组成：一个是数组的维度、一个是数组元素的数据类型
	 * 
	 * 对于数组元素的数据类型有一个相应的映射表如下
	 */
	@Test
	public void testReflectArray() {

		// TODO Auto-generated method stub
		// 数组的反射
		// 一个一维数组就是一个Object对象
		// 相同数据类型和维度的数组具有相同的字节码
		int[] a1 = new int[] { 1, 2, 3 };
		Integer[] a6 = new Integer[] { 4, 5, 6 };
		int[] a2 = new int[5];
		int[][] a3 = new int[2][2];
		String[] a4 = new String[] { "a", "b", "c" };
		// 判断是否是同一份字节码
		System.out.println(a1.getClass() == a2.getClass());
		System.out.println(a1.getClass().getName() == a6.getClass().getName());
		System.out.println(a1.getClass().getName() == a3.getClass().getName());
		System.out.println(a1.getClass().getName() == a4.getClass().getName());
		System.out.println(a1.getClass().getName()); // 数组类的名称 由维度与数据类型组成
		System.out.println(a3.getClass().getName());
		System.out.println(a6.getClass().getName());
		System.out.println(a4.getClass().getName());
		System.out.println("int 的父类为：" + int.class.getSuperclass()); // 基本数据类型没有父类
		System.out.println("short 的父类为：" + short.class.getSuperclass());
		System.out.println("String 的父类：" + String.class.getSuperclass());
		System.out.println(a1.getClass().getSuperclass().getName()); // 数组的父类为Object
		System.out.println(a4.getClass().getSuperclass().getName());

		// 数组的父类为Object 因此有下面的这些转换
		Object obj1 = a1;
		Object obj2 = a2;
		// 因为int没有父类 所以无法进行int向Object的转换
		// Object[] obj3=a1;
		Object[] obj4 = a3;

		// 输出数组中的元素内容
		System.out.println(Arrays.asList(a1));
		System.out.println(Arrays.asList(a6));
		System.out.println(Arrays.asList(a4));

		printObj(a1); // 提供一个静态的方法 打印对象 无论数组还是单个对象
		printObj("xyz");
	}

	private static void printObj(Object obj) { // 打印对象
		// TODO Auto-generated method stub
		Class cls = obj.getClass(); // 获取对象的字节码
		if (cls.isArray()) { // 判断对象是否是数组 是数组
			int len = Array.getLength(obj); // 获取数组长度
			for (int i = 0; i < len; i++) {
				System.out.println(Array.get(obj, i)); // 打印每一个数组元素
			}
		} else {
			System.out.println(obj); // 不是数组
		}
	}
}
