package mylearncode.undergrowth.designmode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * �����Լ�������(��ӵ����) ���Ի�ȡ��Ϣ �������������˵����� ���ǲ������������˵�������Ϣ
 * @author Administrator
 *
 */
public class PersonBeanNonOwnerHandler implements InvocationHandler {

	Person personBean;
	
	
	public PersonBeanNonOwnerHandler(Person personBean) {
		super();
		this.personBean = personBean;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		if(method.getName().startsWith("get")){
			return method.invoke(personBean, args);
		}else if (method.getName().equals("setScore")) {
			return method.invoke(personBean, args);
		}else if (method.getName().startsWith("set")) {
			throw new IllegalAccessException("�㲻�����������˵�������Ϣ");
		}
		return null;
	}

}
