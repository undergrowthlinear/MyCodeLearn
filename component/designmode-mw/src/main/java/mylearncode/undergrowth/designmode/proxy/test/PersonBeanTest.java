package mylearncode.undergrowth.designmode.proxy.test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationHandler;

import org.junit.Test;

import mylearncode.undergrowth.designmode.proxy.Person;
import mylearncode.undergrowth.designmode.proxy.PersonBeanImpl;
import mylearncode.undergrowth.designmode.proxy.PersonBeanOwnerHandler;
import mylearncode.undergrowth.designmode.proxy.ProxyFactory;

public class PersonBeanTest {

	@Test
	public void test() {
		PersonBeanImpl personBean = new PersonBeanImpl("�Ʊ�", "����", "ְҵ������õ��˶�Ա");
		System.out.println(personBean);
		//����ӵ����
		Person personBeanOwner = ProxyFactory.getOwnerPersonBean(personBean);
		System.out.println(personBeanOwner.getName());
		personBeanOwner.setDescription(personBeanOwner.getDescription()
				+ ",�Ͻ��е�ս����");
		System.out.println(personBeanOwner.getDescription());
		System.out.println(personBean);
		try {
			// �Լ����Լ����� �ᷢ���쳣
			personBeanOwner.setScore(100);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(personBean);
		}
		//������ӵ����
		Person personNonOwner=ProxyFactory.getNonOwnerPersonBean(personBean);
		System.out.println(personNonOwner.getDescription());
		personNonOwner.setScore(100);
		System.out.println(personBean);
		try {
			//���ñ��˵���Ϣ �ᷢ���쳣
			personNonOwner.setDescription("�����ߵ÷ǳ���");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(personBean);
		}
	}

}
