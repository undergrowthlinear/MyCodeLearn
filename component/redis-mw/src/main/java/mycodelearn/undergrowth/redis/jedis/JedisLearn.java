package mycodelearn.undergrowth.redis.jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用) * redis-cli 6379/绑定特定的ip Redis client的辅助工具类
 * 用于连接Redis服务器 创建不同的Redis Server对应的客户端对象
 * redis是一个开源的，C语言编写、bsd协议的，键值对的缓存和存储的、通常被作为NoSql的内存数据库
 * 键值包含--字符串、列表、集合、有序集合、散列表、位图、hyperloglogs 一个redis实例 支持16个字典 使用 select 数字 进行切换
 * 0--15 keys * 查看key的类型--->type testBucket 查看key的值
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年8月4日
 * @version 1.0.0
 */
public class JedisLearn {

	private Logger logger = LoggerFactory.getLogger(JedisLearnTest.class);

	Jedis jedis = null;

	public String connect(String serverIp, int serverPort) {
		jedis = new Jedis(serverIp, serverPort);
		logger.info("connect to " + serverIp + ":" + serverPort + " successfuly," + jedis.ping());
		return jedis.ping();
	}

	public String setString(String key, String value) {
		return jedis.set(key, value);
	}

	public void addList(String listName, List<String> listValue) {
		for (Iterator<String> iterator = listValue.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			jedis.lpush(listName, string);
		}
	}

	public void getList(String listName, int start, int end) {
		List<String> listValue = jedis.lrange(listName, start, end);
		iteratorList(listValue);
	}

	private void iteratorList(List<String> listValue) {
		for (Iterator<String> iterator = listValue.iterator(); iterator.hasNext();) {
			String value = (String) iterator.next();
			logger.info(value);
		}
	}

	public long addHash(String key, String field, String value) {
		return jedis.hset(key, field, value);
	}

	public String getHash(String key, String field) {
		return jedis.hget(key, field);
	}

	public long addSet(String key, String... members) {
		return jedis.sadd(key, members);
	}

	public Set<String> getSet(String key) {
		return jedis.smembers(key);
	}

	public long addZSet(String key, Map<String, Double> scoreMembers) {
		return jedis.zadd(key, scoreMembers);
	}

	public Set<String> getZSet(String key, int start, int end) {
		return jedis.zrange(key, start, end);
	}

}
