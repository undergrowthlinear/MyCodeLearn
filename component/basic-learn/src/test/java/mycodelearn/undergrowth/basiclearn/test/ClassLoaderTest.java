package mycodelearn.undergrowth.basiclearn.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用) 类加载器负载加载类对象、资源
 *               一般策略是将给定名称转为文件名，然后从文件系统中进行加载
 *               类加载器采用委托机制进行加载类和资源-->在加载类或者资源之前将操作委托给父加载器进行加载
 * @author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
 * Date 2016年6月7日
 * @version 1.0.0
 */
public class ClassLoaderTest {

	/**
	 * 获取系统的类加载器及其父类
	 */
	@Test
	public void testClassLoaderName() {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		iteratorClassLoader(classLoader);
	}

	/**
	 * 迭代类加载器
	 * 
	 * @param  classLoader
	 */
	private void iteratorClassLoader(ClassLoader classLoader) {
		while (classLoader != null) {
			System.out.println(classLoader);
			classLoader = classLoader.getParent();
		}
	}

	/**
	 * 显示加载资源的URL
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testLoadResource() throws IOException, URISyntaxException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		URL url = ClassLoader.getSystemResource("config.xml");
		System.out.println("==================显示文件资源==================");
		disUrl(url);
		System.out.println("==================显示网络资源==================");
		url = new URL("http://dict.youdao.com/search?le=eng&q=%E9%80%92%E5%BD%92&keyfrom=dict.top");
		disUrl(url);

	}

	/**
	 * 利用反射获取方法名进行 显示 显示资源定位符的信息
	 * 
	 * @param  url
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private void disUrl(URL url) throws IOException, URISyntaxException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		Method[] methods = url.getClass().getDeclaredMethods();
		for (Method method : methods) {
			// 只获取不带参数的方法
			if (method.getParameterTypes().length == 0)
				System.out.println(method.getName() + "-->" + method.invoke(url, null));
		}
		/*
		 * System.out.println("url.getFile():"+url.getFile());
		 * System.out.println("url.getAuthority():"+url.getAuthority());
		 * System.out.println("url.getContent():"+url.getContent());
		 * System.out.println(":"+url.getDefaultPort());
		 * System.out.println(":"+url.getHost());
		 * System.out.println(":"+url.getPath());
		 * System.out.println(":"+url.getPort());
		 * System.out.println(":"+url.getProtocol());
		 * System.out.println(":"+url.getQuery());
		 * System.out.println(":"+url.getRef());
		 * System.out.println(":"+url.getUserInfo());
		 * System.out.println(":"+url.hashCode());
		 * System.out.println(":"+url.openConnection());
		 * System.out.println(":"+url.openStream());
		 * System.out.println(":"+url.toExternalForm());
		 * System.out.println("url.toString():"+url.toString());
		 * System.out.println("url.toURI():"+url.toURI());
		 */
	}

	/**
	 * 显示加载输入流资源 进行读取
	 * 
	 * @throws IOException
	 */
	@Test
	public void testLoadInputStream() throws IOException {
		System.out.println("==================加载输入流资源==================");
		InputStream is = ClassLoader.getSystemResourceAsStream("config.xml");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		// byte[] num=new byte[1024];
		int n = 0;
		System.out.println(is.markSupported());
		// 进行流标记
		is.mark(0);
		while ((n = is.read()) != -1) {
			System.out.print((char) n);
		}
		System.out.println();
		// 重置流 重新读取
		is.reset();
		String content = null;
		while ((content = reader.readLine()) != null) {
			System.out.println(content);
		}
	}

	/**
	 * 自定义类加载器
	 * 
	 * @author Administrator
	 *
	 */
	class CustomerClassLoader extends ClassLoader {

		/**
		 * 父类的实现默认为抛出异常 将父类的protected修饰符扩大 改为public 供外部使用
		 */
		@Override
		public Class<?> findClass(String name) throws ClassNotFoundException {
			// TODO Auto-generated method stub
			Class<?> class1 = null;
			InputStream is = null;
			try {
				// 获取类的字节码文件
				is = ClassLoader
						.getSystemResourceAsStream(name.replace(".", System.getProperty("file.separator")) + ".class");
				byte[] b = new byte[is.available()];
				// 读取到字节数组中
				is.read(b);
				// 转换为相应的字节码
				class1 = defineClass(name, b, 0, b.length);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return class1;
		}

		/**
		 * 扩大修饰符 供外部使用
		 */
		@Override
		public Package getPackage(String name) {
			// TODO Auto-generated method stub
			return super.getPackage(name);
		}

		/**
		 * 扩大修饰符 供外部使用
		 */
		@Override
		public Package[] getPackages() {
			// TODO Auto-generated method stub
			return super.getPackages();
		}

	}

	/**
	 * 测试自定义类加载器 类加载器采用委托机制 进行加载类
	 * 
	 * @throws ClassNotFoundException
	 */
	@Test
	public void testCustomClassLoader() throws ClassNotFoundException {
		CustomerClassLoader classLoader = new CustomerClassLoader();
		// 迭代类加载器
		iteratorClassLoader(classLoader);
		// 加载类 MathLearn
		@SuppressWarnings("unchecked")
		Class<MathLearnTest> mathLearn = (Class<MathLearnTest>) classLoader
				.loadClass("mycodelearn.undergrowth.basiclearn.test.MathLearnTest");
		System.out.println("mathLearn的类加载器为:" + mathLearn.getClassLoader());
		iteratorClassMethod(mathLearn);
	}

	/**
	 * 迭代类字节码方法
	 * 
	 * @param  mathLearn
	 */
	private void iteratorClassMethod(Class<MathLearnTest> mathLearn) {
		for (Method method : mathLearn.getDeclaredMethods()) {
			System.out.println(method.getName());
		}
	}

	/**
	 * 查找类加载器
	 * 
	 * @throws ClassNotFoundException
	 */
	@Test
	public void testFindClass() throws ClassNotFoundException {
		CustomerClassLoader classLoader = new CustomerClassLoader();
		// 查找类 MathLearn
		@SuppressWarnings("unchecked")
		Class<MathLearnTest> mathLearn = (Class<MathLearnTest>) classLoader
				.findClass("mycodelearn.undergrowth.basiclearn.test.MathLearnTest");
		iteratorClassMethod(mathLearn);
	}

	/*
	 * 查找包相关信息
	 */
	@Test
	public void testPackage() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		CustomerClassLoader classLoader = new CustomerClassLoader();
		Package package1 = classLoader.getPackage("mycodelearn.undergrowth.basiclearn.test");
		disPackageMethod(package1);
	}

	/**
	 * 显示包相关信息
	 * 
	 * @param  package1
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private void disPackageMethod(Package package1) {
		// TODO Auto-generated method stub
		Method[] methods = package1.getClass().getDeclaredMethods();
		for (Method method : methods) {
			// 只获取不带参数的方法
			if (method.getParameterTypes().length == 0)
				try {
					// 调用实例方法
					System.out.println(method.getName() + "-->" + method.invoke(package1, null));
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
		}
	}

}
