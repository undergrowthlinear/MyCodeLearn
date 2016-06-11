package mylearncode.undergrowth.designmode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * �Լ�������(ӵ����) �Լ�����ά���Լ�����Ϣ ������������
 * @author Administrator
 *
 */
public class PersonBeanOwnerHandler implements InvocationHandler {

	Person personBean;
	
	
	public PersonBeanOwnerHandler(Person personBean) {
		super();
		this.personBean = personBean;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		if(method.getName().startsWith("get")){//��ȡ�Լ���������Ϣ
			return method.invoke(personBean, args);
		}else if(method.getName().equals("setScore")){
			throw new IllegalAccessException("�㲻���Լ����Լ�����");
		}else if(method.getName().startsWith("set")){ //�����Լ���������Ϣ
			return method.invoke(personBean, args);
		}
		return null;
	}

}
