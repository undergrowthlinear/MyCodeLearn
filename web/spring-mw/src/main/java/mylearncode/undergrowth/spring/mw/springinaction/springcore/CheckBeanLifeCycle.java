package mylearncode.undergrowth.spring.mw.springinaction.springcore;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CheckBeanLifeCycle implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor,
		InitializingBean, DisposableBean {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.err.println(
				CheckBeanLifeCycle.class.getName() + " what my destroy :" + ",time is:" + System.currentTimeMillis());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.err.println(CheckBeanLifeCycle.class.getName() + " what my afterPropertiesSet :" + ",time is:"
				+ System.currentTimeMillis());
		name="init_"+this.name;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		System.err.println(CheckBeanLifeCycle.class.getName() + " what my postProcessBeforeInitialization :" + beanName
				+ ",time is:" + System.currentTimeMillis());
		if (bean instanceof CheckBeanLifeCycle) {
			((CheckBeanLifeCycle) bean).setName("beforeInit_"+getName());
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		System.err.println(CheckBeanLifeCycle.class.getName() + " what my postProcessAfterInitialization :" + beanName
				+ ",time is:" + System.currentTimeMillis());
		if (bean instanceof CheckBeanLifeCycle) {
			((CheckBeanLifeCycle) bean).setName("afterInit_"+getName());
		}
		return bean;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		System.err.println(CheckBeanLifeCycle.class.getName() + " what my applicationContext :"
				+ applicationContext.getApplicationName() + ",time is:" + System.currentTimeMillis());
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		System.err.println(CheckBeanLifeCycle.class.getName() + " what my BeanFactory :" + beanFactory.toString()
				+ ",time is:" + System.currentTimeMillis());
	}

	@Override
	public void setBeanName(String name) {
		// TODO Auto-generated method stub
		System.err.println(CheckBeanLifeCycle.class.getName() + " what my name :" + name + ",time is:"
				+ System.currentTimeMillis());
	}

}
