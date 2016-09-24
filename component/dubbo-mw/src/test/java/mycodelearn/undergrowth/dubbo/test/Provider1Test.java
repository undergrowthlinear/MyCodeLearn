package mycodelearn.undergrowth.dubbo.test;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.registry.zookeeper.ZookeeperRegistry;
import com.alibaba.dubbo.registry.zookeeper.ZookeeperRegistryFactory;
import com.thoughtworks.xstream.alias.ClassMapper.Null;

public class Provider1Test {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "provider1.xml" });
		context.start();
		RegistryConfig registryConfig = (RegistryConfig) context.getBean("zKRegistry");
		ZookeeperRegistry zoo = null;
		//获取地址列表
		String[] addressList = registryConfig.getAddress().split(",");
		for (String address : addressList) { //获取容器中的注册器
			zoo = (ZookeeperRegistry) new ZookeeperRegistryFactory()
					.getRegistry(URL.valueOf(registryConfig.getProtocol() + "://" + address));
			if (zoo != null) {
				break;
			}
		}
		Set<URL> regsNum = zoo.getRegistered();
		Set<URL> regsNums = new HashSet<>();
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
