package mylearncode.undergrowth.spring.mw.springcore.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mylearncode.undergrowth.spring.mw.springinaction.springcore.SpelInject;

public class SpelTest {

	@Test
	public void spelTest() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spel.xml");
		SpelInject spelInject = context.getBean(SpelInject.class);
		System.out.println(spelInject);
	}

}
