package mycodelearn.undergrowth.basiclearn.test;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.channels.Channel;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

/**
 * 
 * @Description: TODO(这里用一句话描述这个类的作用) 标准输入、输出、错误流 环境信息访问、设置、系统属性 加载文件和类库
 *               复制数组方法、调用垃圾回收器、java虚拟机、信道、安全管理器
 * @Author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
 * @Date 2016年6月7日
 * @Version 1.0.0
 */
public class SystemTest {

	/**
	 * 测试标准输入流
	 * 
	 * @throws IOException
	 */
	//@Test
	public void testIn() throws IOException {
		// 将字节流传唤为字符流 进行操作
		BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
		String n = null;
		while ((n = is.readLine()) != null && !"".equals(n)) {
			System.out.format("%s %d", n, n.length());
		}
	}

	/**
	 * 测试标准输出流
	 */
	@Test
	public void testOut() {
		PrintStream ps = System.out;
		ps.append("你好啊，System.out\n");
		ps.println("换行符");
		ps.format("%s %n", "你好啊", "你好啊".length());
		ps.write(20);
		ps.flush();
		ps.close();
	}

	/**
	 * 测试标准错误流
	 */
	@Test
	public void testErr() {
		PrintStream ps = System.err;
		ps.append("你好啊，System.out\n");
		ps.println("换行符");
		ps.format("%s %n", "你好啊", "你好啊".length());
		ps.write(20);
		ps.flush();
		ps.close();
	}

	/**
	 * 测试获取环境信息、系统属性、系统时间
	 */
	@Test
	public void testEnvironmentGet() {
		// 获取当前时间 毫秒数
		System.out.println(
				System.currentTimeMillis() + "\t" + new Date(System.currentTimeMillis()) + "\t" + System.nanoTime());
		// 获取当前环境信息的Map映射视图
		for (Map.Entry<String, String> env : System.getenv().entrySet()) {
			System.out.println(env.getKey() + ":" + env.getValue());
		}
		System.out.println("====================================");
		// 获取系统属性
		Properties properties = System.getProperties();
		properties.list(System.out);
		// Set遍历
		System.out.println("====================================");
		for (String propertyName : properties.stringPropertyNames()) {
			// 移除系统指定属性
			if ("sun.desktop".equals(propertyName))
				System.out.println(System.clearProperty(propertyName));
			System.out.println(propertyName + "-->" + properties.getProperty(propertyName));
		}
	}

	/**
	 * 关于垃圾回收器、java虚拟机、hashcode
	 */
	//@Test
	public void testOther() {

		// 返回指定对象的哈希值
		System.out.println(System.identityHashCode("123454"));

		// 运行处于挂起状态的终止方法
		System.runFinalization();

		// 垃圾回收
		System.out.println("调用垃圾回收机制进行清理所丢弃的对象,但是垃圾回收机制的线程级别很低,不能保证马上执行");
		System.gc();
		Runtime.getRuntime().gc();

		// 终止虚拟机退出 非0为异常终止
		System.out.println("java虚拟机推出之前，显示");
		System.exit(0);
		Runtime.getRuntime().exit(0);
		System.exit(1);
		Runtime.getRuntime().exit(1);
		System.out.println("java虚拟机已终止，不会显示了");
	}

	/**
	 * 测试控制台
	 */
	@Test
	public void testConsole() {
		// 如果没有重新定向输入、输出 则有控制台
		// 但是eclipse已经重定向了 所以下面代码不会执行
		// 获取控制台
		Console console = System.console();
		char[] pass = null;
		if (console != null) {
			pass = console.readPassword("[%s]", "请输入密码");
			System.out.println(pass);
			Arrays.fill(pass, ' ');
			System.out.println(pass);
		}
	}

	/**
	 * 获取安全属性
	 */
	@Test
	public void getSecurityManager() {
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager != null) {
			System.out.println(securityManager);
		}
	}

	/**
	 * 获取信道
	 * 
	 * @throws IOException
	 */
	@Test
	public void getChannel() throws IOException {
		Channel channel = System.inheritedChannel();
		if (channel != null)
			System.out.println(channel.isOpen());
	}

	/**
	 * 测试复制数组
	 */
	@Test
	public void testArray() {
		char[] src = new char[1024];
		Arrays.fill(src, (char) 49);
		System.out.println(Arrays.toString(src));
		char[] dest = new char[512];
		System.arraycopy(src, 0, dest, 0, dest.length);
		System.out.println(Arrays.toString(dest));
	}

	/**
	 * 加载静态库 对于window而言 是加载dll 动态链接库 对于unix/linux而言 是加载静态共享库文件 .so 加载静态共享库后
	 * 可通过jni技术 调用本地方法(c/c++) 在嵌入式领域中 驱动开发中 当需要底层驱动与上层应用交互时 即会经常使用
	 */
	//@Test
	public void testLoad() {
		System.loadLibrary("");
		Runtime.getRuntime().loadLibrary("");
		System.load("");
		Runtime.getRuntime().load("");
	}
}
