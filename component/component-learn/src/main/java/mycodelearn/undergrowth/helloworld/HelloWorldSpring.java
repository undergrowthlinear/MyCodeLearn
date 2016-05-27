package mycodelearn.undergrowth.helloworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mycodelearn.undergrowth.spring.annotation.NotifyPrinter;

/**
 * 
* @Description: TODO(这里用一句话描述这个类的作用)
* @Author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
* @Date 2016年5月27日
* @Version 1.0.0
 */
public class HelloWorldSpring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		NotifyPrinter notifyPrinter = context.getBean(NotifyPrinter.class);
		notifyPrinter.printNotify();
	}

}
