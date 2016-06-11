package mylearncode.undergrowth.designmode.proxy;

import java.lang.reflect.Proxy;

/**
 * �򵥹���  
 * ����JDK���õĴ��� �����������
 * @author Administrator
 *
 */
public class ProxyFactory {
	
	public static Person getOwnerPersonBean(Person personBean){
		return (Person) Proxy.newProxyInstance(personBean.getClass().getClassLoader(), personBean.getClass().getInterfaces(), new PersonBeanOwnerHandler(personBean));
	}
	
	public static Person getNonOwnerPersonBean(Person personBean){
		return (Person) Proxy.newProxyInstance(personBean.getClass().getClassLoader(), personBean.getClass().getInterfaces(), new PersonBeanNonOwnerHandler(personBean));
	}
	
}
