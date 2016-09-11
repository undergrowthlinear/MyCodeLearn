package mylearncode.undergrowth.spring.mw.springcore.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mylearncode.undergrowth.spring.mw.springinaction.springcore.InjectObject;

public class InjectObjectTest {

	@Test
	public void injectTest() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:injectobject.xml");
		InjectObject injectObject = context.getBean(InjectObject.class);
		System.out.println(injectObject);
	}

}
