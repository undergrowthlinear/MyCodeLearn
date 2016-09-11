package mylearncode.undergrowth.spring.mw.springcore.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mylearncode.undergrowth.spring.mw.springinaction.springcore.Knight;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* ApplicationContext----负责对象的创建与组装,不同之处在于加载的方式不同
* @author <a href="undergrowth@126.com">undergrowth</a>
* @date 2016年9月11日
* @version 1.0.0
 */
public class KnightMainTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:knights.xml");
		Knight knight = (Knight) context.getBean("knight");
		knight.embarkOnQuest();
	}

}
