package mylearncode.undergrowth.spring.mw.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mylearncode.undergrowth.spring.mw.schema.entity.People;

public class PeopleTest {

	@Test
	public void peopleTest(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");  
		People p = (People)ctx.getBean("cutesource");  
		System.out.println(p.getId());  
		System.out.println(p.getName());  
		System.out.println(p.getAge());
	}
	
}
