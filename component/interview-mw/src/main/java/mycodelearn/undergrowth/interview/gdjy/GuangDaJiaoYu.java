package mycodelearn.undergrowth.interview.gdjy;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月13日
* @version 1.0.0
 */
public class GuangDaJiaoYu {
	/**
	 * Q1：MVC与MVP的特点及用途
	 * A1：MVC----model-view-controller----是一种框架模式view与model之间可以直接交互,
	 * 也可通过controller进行交互,该框架模式易于理解,耦合性较低
	 * MVP----model-view-presenter----完全将模型与视图分离开,模型与视图的交互都需要通过presenter,
	 * view与presenter耦合的太紧密
	 */
	
	/**
	 * Q2:继承与重用有什么区别
	 * A2:继承为子类继承父类方法与属性,该方法进行重用耦合性很高
	 * 而将特定工具方法,通用方法抽取出来,做成工具类,可降低耦合性
	 */
	
	/**
	 * Q3：数据库如何优化
	 * A3:1.SQL语句的优化
	 * 2.表空间的优化
	 * 3.索引的优化
	 * 4.合理的创建临时表与视图
	 * 5.使用ssd加快redo log的写入
	 */
	
	/**
	 * Q4:如何构建高性能web
	 * A4:负载均衡(ip/array/nginx)/组件分离/分布式缓存(redis)/数据库扩展(主从复制/读写分离/垂直拆分/水平拆分--同一表存在不同数据库上)
	 */
	
	/**
	 * Q5：什么事Application Server,常见的有哪些
	 * A5:应用服务器是以各种协议将服务端程序暴露给客户端的程序
	 * tomcat weblogic jetty
	 */
}
