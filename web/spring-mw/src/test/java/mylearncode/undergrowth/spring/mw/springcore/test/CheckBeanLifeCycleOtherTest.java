package mylearncode.undergrowth.spring.mw.springcore.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mylearncode.undergrowth.spring.mw.springinaction.springcore.CheckBeanPostProcessor;
import mylearncode.undergrowth.spring.mw.springinaction.springcore.other.PostProcessorBean;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * ApplicationContext----负责对象的创建与组装,不同之处在于加载的方式不同
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年9月11日
 * @version 1.0.0
 */
public class CheckBeanLifeCycleOtherTest {

	@Test
	public void mainTest() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:checkbeanlifecycle-other.xml");
		PostProcessorBean postProcessorBean = (PostProcessorBean) context.getBean("postProcessorBean");
		System.out.println(postProcessorBean.getPassword());
		System.out.println(postProcessorBean.getUsername());
	}

}
