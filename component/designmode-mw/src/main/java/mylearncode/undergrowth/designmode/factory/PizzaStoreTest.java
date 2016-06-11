package mylearncode.undergrowth.designmode.factory;

import static org.junit.Assert.*;

import org.junit.Test;

public class PizzaStoreTest {

	@Test
	public void test() {
		PizzaStore ps=null;
		//�򵥹�������
		/*ps=new PizzaStore();
		ps.orderPizza("NYCheese");*/
		
		//��������ģʽ
		/*ps=new NYPizzaStore();
		ps.orderPizza("Clam");*/
		
		//���󹤳�ģʽ
	    //����������ԭ�ϴӳ��󹤳��л�ȡ
		ps=new NYPizzaStore();
		ps.orderPizza("Cheese");
	}

}
