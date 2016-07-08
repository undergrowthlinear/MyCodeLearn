package mycodelearn.undergrowth.learn.spring.inaction.rs;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* 远程服务/远程调用----
* RPC----remote procedure call----远程过程调用
* 执行流从一个应用转移到另一个应用
* Spring支持:
* 服务端----远程服务导出器--适配-->服务实现Bean
* 客户端----代理工厂--代理-->客户端依赖Bean
* Rmi----远程方法调用----无网络访问限制/基于java
* Hessian/Burlap----有网络访问限制/通过Http访问/发布Java服务
* Spring Http Invoker----有网络访问限制/通过XML/序列化访问/发布Spring服务
* JAX-RPC/JAX-WS----访问/发布平台中立的基于SOAP的web服务
* 
* * 服务器端 你需要做的仅仅是 写好你需要提供远程服务的实现类 
24. * 然后将其交给RmiServiceExporter类 RmiServiceExporter会将实现类发布为RMI服务 
25. *  
26. * 客户端 也很简单 
27. * 只需要使用RmiProxyFactoryBean从服务器端的URL从获取服务对象  并进行封装给你定义的id 
28. * 然后从spring容器中获取RmiProxyFactoryBean封装的id即可 
29. *  
30. * 此测试代码中 服务器和客户端都在一个项目中 也可换成多个项目 在不同的电脑中 
31. * 只需要在服务器的RmiServiceExporter中加入 p:registryHost="ip地址" 即可 
32. * 客户端将localhost换成ip地址即可 

* 
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月24日
* @version  1.0.0
 */
public class RmiTest {

	@Test
	public void rmiTest(){
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		RSServiceHello hello=(RSServiceHello) context.getBean("rSServiceHelloCli");
		System.out.println(hello.sayHello("undergrowth"));
	}
	
	
}
