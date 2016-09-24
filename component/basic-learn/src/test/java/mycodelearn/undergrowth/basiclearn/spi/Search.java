package mycodelearn.undergrowth.basiclearn.spi;

/**
 * 
 * JAVA中SPI的约定
 * 
 * 当服务的提供者，提供了服务接口的一种实现之后，在jar包的META-INF/services/目录里同时创建一个以服务接口命名的文件。
 * 该文件里就是实现该服务接口的具体实现类。而当外部程序装配这个模块的时候，就能通过该jar包META-INF/services/
 * 里的配置文件找到具体的实现类名，并装载实例化，完成模块的注入 Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年9月12日
 * @version 1.0.0
 */
public interface Search {
	public void search();
}
