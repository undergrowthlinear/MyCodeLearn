package mylearncode.undergrowth.designmode.template.test;

import static org.junit.Assert.*;

import org.junit.Test;

import mylearncode.undergrowth.designmode.template.CaffeineBeverage;
import mylearncode.undergrowth.designmode.template.Coffee;
import mylearncode.undergrowth.designmode.template.Tea;

public class CaffeineBeverageTest {

	@Test
	public void test() {
		CaffeineBeverage baBeverage=new Tea();
		System.out.println("��ʼ�ݲ�");
		baBeverage.prepareRecipe();
		baBeverage=new Coffee();
		System.out.println("==================��ʼ���ݿ���==================");
		baBeverage.prepareRecipe();
	}

}
