package mylearncode.undergrowth.designmode.strategy.test;

import static org.junit.Assert.*;

import java.awt.dnd.DragGestureEvent;

import org.junit.Test;

import mylearncode.undergrowth.designmode.strategy.DisableFly;
import mylearncode.undergrowth.designmode.strategy.DisableQuack;
import mylearncode.undergrowth.designmode.strategy.DivingDuck;
import mylearncode.undergrowth.designmode.strategy.Duck;
import mylearncode.undergrowth.designmode.strategy.EnableFly;
import mylearncode.undergrowth.designmode.strategy.EnableQuack;

public class DivingDuckTest {

	@Test
	public void test() {
		Duck dark=new DivingDuck();
		System.out.println("Ѽ�ӵĹ�����Ϊ��");
		dark.swim();
		dark.display();
		System.out.println("Ѽ�ӵ�������Ϊ����̬���иı䣺");
		//��̬���иı�Ѽ�ӵ���Ϊ
		//����ǱˮѼ ���
		dark.setFlyBehavior(new EnableFly());
		dark.getFlyBehavior().fly();
		//����ǱˮѼ���
		dark.setQuackBehavior(new EnableQuack());
		dark.getQuackBehavior().quack();
		//��̬���иı�Ѽ�ӵ���Ϊ
		//����ǱˮѼ�����
		dark.setFlyBehavior(new DisableFly());
		dark.getFlyBehavior().fly();
		//����ǱˮѼ�����
		dark.setQuackBehavior(new DisableQuack());
		dark.getQuackBehavior().quack();
	}

}
