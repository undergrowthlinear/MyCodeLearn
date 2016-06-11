package mylearncode.undergrowth.designmode.decorate.test;

import static org.junit.Assert.*;

import org.junit.Test;

import mylearncode.undergrowth.designmode.decorate.Baverage;
import mylearncode.undergrowth.designmode.decorate.Coffee;
import mylearncode.undergrowth.designmode.decorate.HoneyIngredient;
import mylearncode.undergrowth.designmode.decorate.MochaIngredient;
import mylearncode.undergrowth.designmode.decorate.Tea;

public class MochaIngredientTest {

	@Test
	public void test() {
		//������Ҫ2��Ħ���Ŀ��� �������Ǯ
		Baverage baverage=new Coffee();
		baverage=new MochaIngredient(baverage);
		baverage=new MochaIngredient(baverage);
		System.out.println(baverage.getDescription()+"\t�۸�:"+baverage.cost());
	    
		//һ��Ħ�� һ�ݷ��۵Ĳ�Ҷ
		baverage=new Tea();
		baverage=new MochaIngredient(baverage);
		baverage=new HoneyIngredient(baverage);
		System.out.println(baverage.getDescription()+"\t�۸�:"+baverage.cost());
	}

}
