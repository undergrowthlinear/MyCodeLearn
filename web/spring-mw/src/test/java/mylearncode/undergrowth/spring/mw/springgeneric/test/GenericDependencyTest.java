package mylearncode.undergrowth.spring.mw.springgeneric.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mylearncode.undergrowth.spring.mw.generic.dependency.StudentService;
import mylearncode.undergrowth.spring.mw.generic.dependency.UserService;

public class GenericDependencyTest {

	@Test
	public void genericTest() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:genericdependency.xml");
		//
		UserService userService = context.getBean(UserService.class);
		userService.service();
		StudentService studentService = context.getBean(StudentService.class);
		studentService.service();
	}

}
