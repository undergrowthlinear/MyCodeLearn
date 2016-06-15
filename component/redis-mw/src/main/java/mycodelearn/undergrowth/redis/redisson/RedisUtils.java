package mycodelearn.undergrowth.redis.redisson;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
*  Redis client的辅助工具类
* 用于连接Redis服务器 创建不同的Redis Server对应的客户端对象
* redis是一个开源的，C语言编写、bsd协议的，键值对的缓存和存储的、通常被作为NoSql的内存数据库
   键值包含--字符串、列表、集合、有序集合、散列表、位图、hyperloglogs
  set key value  -->设置键值对
  get key           --获取键对应的值
 getset key field--设置新的键值 并返回key的旧值
Hget key field

 del   key       --删除key和关联值
Hdel key value  --删除删列表的字段的值

 incr key  --以key所对应的值的数字值加一
decr key
incrby key num
decrby key num   --如果key不存在，或者key所对应的值的类型不正确  则将key所对应的值置为0 再进行操作

Exists  key  --判断key是否存在
Hexists key field  --判断key或者字段是否存在

help
help del --查看命令帮助
expire key seconds   --设置key的过期时间
quit   --退出redis的客户端
Hget key field   --获取hash字段的值
主要命令分为5类
string类型   --例如 get set
hash类型    --hget hgetall
list类型      -- lrange
set类型     --smembers
sortedset类型  ---zremrangebyscore

命令的返回值
      --状态返回--status reply--OK
     --错误返回--error reply--(error)some 
    --整数返回--integer reply--(integer)1
    --字符串返回--bulk reply--"1"--(nil)
   --多行字符串回复--multi bulk reply--1) "bar"

通配符
 --* ? [a-z] \.

一个redis实例 支持16个字典 使用 select 数字 进行切换 0--15


Map
SortedSet
Set
Bucket
List

* @author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
* Date 2016年6月7日
* @version 1.0.0
 */

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.core.RAtomicLong;
import org.redisson.core.RBucket;
import org.redisson.core.RCountDownLatch;
import org.redisson.core.RDeque;
import org.redisson.core.RList;
import org.redisson.core.RLock;
import org.redisson.core.RMap;
import org.redisson.core.RQueue;
import org.redisson.core.RSet;
import org.redisson.core.RSortedSet;
import org.redisson.core.RTopic;

public class RedisUtils {

	private volatile static  RedisUtils redisUtils;

	private RedisUtils() {
	}

	/**
	 * 提供单例模式
	 * 
	 * @return RedisUtils
	 */
	public static RedisUtils getInstance() {
		if (redisUtils == null)
			synchronized (RedisUtils.class) {
				if (redisUtils == null)
					redisUtils = new RedisUtils();
			}
		return redisUtils;
	}
	
	
	/**
	 * 使用config创建Redisson Redisson是用于连接Redis Server的基础类
	* @param  config 使用配置器创建
	* @return  Redisson
	 */
	public Redisson getRedisson(Config config) {
		Redisson redisson = Redisson.create(config);
		System.out.println("成功连接Redis Server");
		return redisson;
	}

	/**
	 * 使用ip地址和端口创建Redisson
	 * 
	 * @param  ip	主机ip
	 * @param  port 主机端口
	 * @return Redisson
	 */
	public Redisson getRedisson(String ip, String port) {
		Config config = new Config();
		config.useSingleServer().setAddress(ip + ":" + port);
		Redisson redisson = Redisson.create(config);
		//System.out.println("成功连接Redis Server" + "\t" + "连接" + ip + ":" + port + "服务器");
		return redisson;
	}

	/**
	 * 关闭Redisson客户端连接
	 * 
	 * @param  redisson redisson
	 */
	public void closeRedisson(Redisson redisson) {
		redisson.shutdown();
		//System.out.println("成功关闭Redis Client连接");
	}

	/**
	 * 获取字符串对象
	 * 
	 * @param  redisson redisson
	 * @param  objectName objectName
	 * @return T
	 */
	public <T> RBucket<T> getRBucket(Redisson redisson, String objectName) {
		RBucket<T> bucket = redisson.getBucket(objectName);
		return bucket;
	}

	/**
	 * 获取Map对象
	 * 
	 * @param  redisson redisson
	 * @param  objectName objectName
	 * @return RMap
	 */
	public <K, V> RMap<K, V> getRMap(Redisson redisson, String objectName) {
		RMap<K, V> map = redisson.getMap(objectName);
		return map;
	}

	/**
	 * 获取有序集合
	 * 
	 * @param  redisson redisson
	 * @param  objectName objectName
	 * @return RSortedSet
	 */
	public <V> RSortedSet<V> getRSortedSet(Redisson redisson, String objectName) {
		RSortedSet<V> sortedSet = redisson.getSortedSet(objectName);
		return sortedSet;
	}

	/**
	 * 获取集合
	 * 
	 * @param  redisson redisson
	 * @param  objectName objectName
	 * @return RSet
	 */
	public <V> RSet<V> getRSet(Redisson redisson, String objectName) {
		RSet<V> rSet = redisson.getSet(objectName);
		return rSet;
	}

	/**
	 * 获取列表
	 * 
	 * @param  redisson redisson
	 * @param  objectName objectName
	 * @return RList
	 */
	public <V> RList<V> getRList(Redisson redisson, String objectName) {
		RList<V> rList = redisson.getList(objectName);
		return rList;
	}

	/**
	 * 获取队列
	 * 
	 * @param  redisson redisson
	 * @param  objectName objectName
	 * @return RQueue
	 */
	public <V> RQueue<V> getRQueue(Redisson redisson, String objectName) {
		RQueue<V> rQueue = redisson.getQueue(objectName);
		return rQueue;
	}

	/**
	 * 获取双端队列
	 * 
	 * @param  redisson redisson
	 * @param  objectName objectName
	 * @return RDeque
	 */
	public <V> RDeque<V> getRDeque(Redisson redisson, String objectName) {
		RDeque<V> rDeque = redisson.getDeque(objectName);
		return rDeque;
	}

	/**
	 * 此方法不可用在Redisson 1.2 中 在1.2.2版本中 可用
	 * 
	 * @param  redisson redisson
	 * @param  objectName objectName
	 * @return RBlockingQueue
	 */
	/**
	 * public <V> RBlockingQueue<V> getRBlockingQueue(Redisson redisson,String
	 * objectName){ RBlockingQueue rb=redisson.getBlockingQueue(objectName);
	 * return rb; }
	 */

	/**
	 * 获取锁
	 * 
	 * @param  redisson redisson
	 * @param  objectName objectName
	 * @return RLock
	 */
	public RLock getRLock(Redisson redisson, String objectName) {
		RLock rLock = redisson.getLock(objectName);
		return rLock;
	}

	/**
	 * 获取原子数
	 * 
	 * @param  redisson redisson
	 * @param  objectName objectName
	 * @return RAtomicLong
	 */
	public RAtomicLong getRAtomicLong(Redisson redisson, String objectName) {
		RAtomicLong rAtomicLong = redisson.getAtomicLong(objectName);
		return rAtomicLong;
	}

	/**
	 * 获取记数锁
	 * 
	 * @param  redisson redisson
	 * @param  objectName objectName
	 * @return RCountDownLatch
	 */
	public RCountDownLatch getRCountDownLatch(Redisson redisson, String objectName) {
		RCountDownLatch rCountDownLatch = redisson.getCountDownLatch(objectName);
		return rCountDownLatch;
	}

	/**
	 * 获取消息的Topic
	 * 
	 * @param  redisson redisson
	 * @param  objectName objectName
	 * @return RTopic
	 */
	public <M> RTopic<M> getRTopic(Redisson redisson, String objectName) {
		RTopic<M> rTopic = redisson.getTopic(objectName);
		return rTopic;
	}

}
