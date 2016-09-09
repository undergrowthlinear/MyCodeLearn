package mycodelearn.undergrowth.dubbo.test;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.registry.zookeeper.ZookeeperRegistry;
import com.alibaba.dubbo.registry.zookeeper.ZookeeperRegistryFactory;

public class Provider1Test {

	private static String provideString = "dubbo://172.16.28.84:20880/mycodelearn.undergrowth.dubbo.test.DemoService?anyhost=true&application=hello-world-app&dubbo=2.5.3&dynamic=true&group=test&interface=mycodelearn.undergrowth.dubbo.test.DemoService&methods=sayHello&pid=26148&retries=5&revision=1.0&side=provider&timestamp=1473057679833&version=1.0";

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "provider1.xml" });
		context.start();
		RegistryConfig registryConfig = (RegistryConfig) context.getBean("zKRegistry");
		ZookeeperRegistry zoo = (ZookeeperRegistry) new ZookeeperRegistryFactory()
				.getRegistry(URL.valueOf(registryConfig.getAddress()));
		Set<URL> regsNum = zoo.getRegistered();
		Set<URL> regsNums=new HashSet<>();
		for (URL url : regsNum) {
			regsNums.add(url);
		}
		for (URL url : regsNums) {
			zoo.unregister(url);
		}
		for (URL url : regsNums) {
			zoo.register(url);
		}
		System.in.read(); // 按任意键退出
	}

}
