package mylearncode.undergrowth.designmode.singleton.test;
import org.junit.Test;

import mylearncode.undergrowth.designmode.singleton.ClassicSingleton;
import mylearncode.undergrowth.designmode.singleton.DoubleCheckedLockSingleton;
import mylearncode.undergrowth.designmode.singleton.EagerSingleton;
import mylearncode.undergrowth.designmode.singleton.Singleton;

public class SingletonTest {

	@Test
	public void test() {
		final Singleton singleton;
		//经典单例模式
		for(int i=0;i<100;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					ClassicSingleton.getInstance().saySomeThing();
				}
			}).run();
			
		}
		
		//急切模式
		for(int i=0;i<10;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					EagerSingleton.getInstance().saySomeThing();
				}
			}).run();
			
		}
		
		//双重检查加锁模式
				for(int i=0;i<10;i++){
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							DoubleCheckedLockSingleton.getInstance().saySomeThing();
						}
					}).run();
					
				}
		
		
	}

}
