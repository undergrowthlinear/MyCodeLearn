package mylearncode.undergrowth.designmode.singleton;
/**
 * 急切加载--在构建静态的实例变量时即初始化(不好之处在于，如果此类不使用的话，就浪费资源了)
 * @author Administrator
 *
 */
public class EagerSingleton implements Singleton {
   private EagerSingleton(){
	   System.out.println("构建单例模式对象"+EagerSingleton.class.getName());
   }
   /**
    * 构建静态变量时 即初始化对象
    */
   private static EagerSingleton eagerSingleton=new EagerSingleton();
   public static EagerSingleton getInstance(){
	   return eagerSingleton;
   }
   public void saySomeThing(){
		System.out.println("我叫"+eagerSingleton.getClass().getName()+",我是单例模式");
	}
}
