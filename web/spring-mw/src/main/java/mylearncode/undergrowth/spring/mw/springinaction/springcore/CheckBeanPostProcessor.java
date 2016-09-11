package mylearncode.undergrowth.spring.mw.springinaction.springcore;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CheckBeanPostProcessor implements BeanPostProcessor,InitializingBean {

	public String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		System.err.println(CheckBeanPostProcessor.class.getName() + " what my postProcessBeforeInitialization :"
				+ beanName + ",time is:" + System.currentTimeMillis());
		if (bean instanceof CheckBeanPostProcessor) {
			CheckBeanPostProcessor checkBeanPostProcessorData = (CheckBeanPostProcessor) bean;
			checkBeanPostProcessorData.setName("beforeInit_" + checkBeanPostProcessorData.getName());
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		System.err.println(CheckBeanPostProcessor.class.getName() + " what my postProcessAfterInitialization :"
				+ beanName + ",time is:" + System.currentTimeMillis());
		if (bean instanceof CheckBeanPostProcessor) {
			CheckBeanPostProcessor checkBeanPostProcessorData = (CheckBeanPostProcessor) bean;
			checkBeanPostProcessorData.setName("afterInit_" + checkBeanPostProcessorData.getName());
		}
		return bean;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.err.println(CheckBeanPostProcessor.class.getName() + " what my afterPropertiesSet :"
				+  ",time is:" + System.currentTimeMillis());
	}

}
