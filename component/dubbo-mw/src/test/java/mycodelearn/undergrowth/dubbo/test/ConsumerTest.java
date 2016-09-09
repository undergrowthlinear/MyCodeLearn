package mycodelearn.undergrowth.dubbo.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerTest {

	private static int num = 100000;
	private static int sleepTime = 1000;

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "consumer.xml" });
		context.start();

		DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
		for (int i = 0; i < num; i++) {
			Person hello = demoService.sayHello("world"); // 执行远程方法

			System.err.println(hello); // 显示调用结果
			Thread.sleep(sleepTime);
		}
		
	}

}
