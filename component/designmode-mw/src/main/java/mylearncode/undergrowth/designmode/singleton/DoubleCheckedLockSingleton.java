package mylearncode.undergrowth.designmode.singleton;
/**
 * 采用双重检查加锁的单例模式 既能够保证延迟实例化对象 又能不影响性能 此种方式最好
 * @author Administrator
 *
 */
public class DoubleCheckedLockSingleton implements Singleton {

	private DoubleCheckedLockSingleton(){
		 System.out.println("构建单例模式对象"+DoubleCheckedLockSingleton.class.getName());
	}
	/**
	 * volatile关键字保证在多个线程中 获取到正确的doubleCheckedLockSingleton值
	 */
	private static volatile DoubleCheckedLockSingleton doubleCheckedLockSingleton=null;
	public static DoubleCheckedLockSingleton getInstance(){
		if(doubleCheckedLockSingleton==null){
			synchronized (DoubleCheckedLockSingleton.class) {
				if(doubleCheckedLockSingleton==null) doubleCheckedLockSingleton=new DoubleCheckedLockSingleton();
			}
			
		}
		return doubleCheckedLockSingleton;
	}
	@Override
	public void saySomeThing() {
		System.out.println("我叫"+doubleCheckedLockSingleton.getClass().getName()+",我是单例模式");
	}
	
}
