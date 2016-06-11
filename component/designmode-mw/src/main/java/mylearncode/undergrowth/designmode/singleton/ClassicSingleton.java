package mylearncode.undergrowth.designmode.singleton;

/**
 * 单例模式：
 *   确保类只有一个实例，并提供一个全局的访问点
 * 
 * 经典的单例模式
 * 1、将构造器私有化
 * 2、提供一个私有的类的对象的静态成员变量
 * 3、提供一个静态的公有的获取对象的方法
 * 
 * 经典单例模式中 存在一个问题 当在多线程的环境中 可能会有多个ClassicSingleton的对象
 * 当然 可以在getInstance上加上synchronized 同步锁 但是每一次getInstance()的时候
 * 都需要同步 这样会极大地降低性能
 * 所以 查看另外两种变化体
 * 1、急切加载--在构建静态的实例变量时即初始化(不好之处在于，如果此类不使用的话，就浪费资源了)
 * 2、采用双重检查加锁的单例模式 既能够保证延迟实例化对象 又能不影响性能 此种方式最好
 * @author Administrator
 *
 */
public class ClassicSingleton implements Singleton {

	private ClassicSingleton(){
		System.out.println("构建单例模式对象"+ClassicSingleton.class.getName());
	}
	private static Singleton classicSingleton=null;
	public static  Singleton getInstance(){
		if(classicSingleton==null) {
			classicSingleton=new ClassicSingleton();
			
		}
		return classicSingleton;
	}
	
	/* (non-Javadoc)
	 * @see com.undergrowth.singleton.Singleton#saySomeThing()
	 */
	@Override
	public void saySomeThing(){
		System.out.println("我叫"+classicSingleton.getClass().getName()+",我是单例模式");
	}
}
