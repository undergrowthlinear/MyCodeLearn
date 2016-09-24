package mylearncode.undergrowth.spring.mw.springautodetectwire.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mylearncode.undergrowth.spring.mw.springinaction.spring.autodetect.autowire.AutoDetectAutoWire;
import mylearncode.undergrowth.spring.mw.springinaction.spring.autodetect.autowire.ComponentScan;
import mylearncode.undergrowth.spring.mw.springinaction.spring.autodetect.autowire.ConfigurableStudent;
import mylearncode.undergrowth.spring.mw.springinaction.spring.autodetect.autowire.ConfigurationBean;
import mylearncode.undergrowth.spring.mw.springinaction.spring.autodetect.autowire.ControllerScan;
import mylearncode.undergrowth.spring.mw.springinaction.spring.autodetect.autowire.QualifierStudent;
import mylearncode.undergrowth.spring.mw.springinaction.spring.autodetect.autowire.RepositoryByScan;
import mylearncode.undergrowth.spring.mw.springinaction.spring.autodetect.autowire.RepositoryScan;
import mylearncode.undergrowth.spring.mw.springinaction.spring.autodetect.autowire.ServiceScan;

public class AutoDetectWireTest {

	@Test
	public void autoWireDetectTest() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:autodetectautowire.xml");
		//
		AutoDetectAutoWire autoDetectAutoWire = context.getBean(AutoDetectAutoWire.class);
		Assert.assertEquals(AutoDetectAutoWire.class.getName(), autoDetectAutoWire.getName());
		//
		ComponentScan componentScan = context.getBean(ComponentScan.class);
		Assert.assertEquals(ComponentScan.class.getName(), componentScan.getName());
		//
		ControllerScan controllerScan = context.getBean(ControllerScan.class);
		Assert.assertEquals(ControllerScan.class.getName(), controllerScan.getName());
		//
		ServiceScan serviceScan = context.getBean(ServiceScan.class);
		Assert.assertEquals(ServiceScan.class.getName(), serviceScan.getName());
		//
		RepositoryScan repositoryScan = context.getBean(RepositoryScan.class);
		Assert.assertEquals(RepositoryScan.class.getName(), repositoryScan.getName());
		// RepositoryByScan
		RepositoryByScan repositoryByScan = context.getBean(RepositoryByScan.class);
		Assert.assertEquals(QualifierStudent.class.getName(), repositoryByScan.getStudent().getName());
		// ConfigurableStudent
		ConfigurableStudent configurableStudent = context.getBean(ConfigurableStudent.class);
		Assert.assertEquals(ConfigurableStudent.class.getName() + "_" + ConfigurationBean.class.getName() + "_"
				+ QualifierStudent.class.getName(), configurableStudent.getName());
	}

}
