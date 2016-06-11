package mylearncode.undergrowth.designmode.adapter.test;
import org.junit.Test;

import mylearncode.undergrowth.designmode.adapter.Duck;
import mylearncode.undergrowth.designmode.adapter.Turkey;
import mylearncode.undergrowth.designmode.adapter.TurkeyAdapter;
import mylearncode.undergrowth.designmode.adapter.WildTurkey;

public class TurkeyAdapterTest {

	@Test
	public void test() {
		Turkey turkey=new WildTurkey();
		//将火鸡转为鸭子
		Duck duck=new TurkeyAdapter(turkey);
		duck.quack();
		duck.fly();
	}

}
