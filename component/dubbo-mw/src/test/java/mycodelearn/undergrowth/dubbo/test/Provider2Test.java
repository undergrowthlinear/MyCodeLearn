package mycodelearn.undergrowth.dubbo.test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.registry.zookeeper.ZookeeperRegistry;
import com.alibaba.dubbo.registry.zookeeper.ZookeeperRegistryFactory;

public class Provider2Test {


	public static void main(String[] args) throws Exception {
		final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "provider2.xml" });
		context.start();
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					unregisterProvider(context);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 60, 60, TimeUnit.SECONDS);

		System.in.read(); // 按任意键退出
	}

	private static void unregisterProvider(ClassPathXmlApplicationContext context) throws InterruptedException {
		RegistryConfig registryConfig = (RegistryConfig) context.getBean("zKRegistry");
		ZookeeperRegistry zoo = (ZookeeperRegistry) new ZookeeperRegistryFactory()
				.getRegistry(URL.valueOf(registryConfig.getAddress()));
		Set<URL> regsNum = zoo.getRegistered();
		Set<URL> regsNums = new HashSet<>();
		for (URL url : regsNum) {
			regsNums.add(url);
		}
		for (URL url : regsNums) {
			zoo.unregister(url);
		}
		Thread.sleep(6000);
		for (URL url : regsNums) {
			zoo.register(url);
		}
	}

}
