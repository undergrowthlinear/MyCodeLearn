package mycodelearn.undergrowth.redis.jedis;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用) * redis-cli 6379/绑定特定的ip Redis client的辅助工具类
 * 用于连接Redis服务器 创建不同的Redis Server对应的客户端对象
 * redis是一个开源的，C语言编写、bsd协议的，键值对的缓存和存储的、通常被作为NoSql的内存数据库
 * 键值包含--字符串、列表、集合、有序集合、散列表、位图、hyperloglogs 一个redis实例 支持16个字典 使用 select 数字 进行切换
 * 0--15 keys * 查看key的类型--->type testBucket 查看key的值 命令的返回值 --状态返回--status
 * reply--OK --错误返回--error reply--(error)some --整数返回--integer reply--(integer)1
 * --字符串返回--bulk reply--"1"--(nil) --多行字符串回复--multi bulk reply--1) "bar"
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年8月4日
 * @version 1.0.0
 */
public class JedisLearnTest {

	JedisLearn jedis = null;
	String serverIp = "192.168.126.129";
	int serverPort = 6379;
	String listName = "underList";
	String hashName = "underHash";
	String setName = "underSets";
	String zsetName = "underZSets";
	String hashField = "u1";
	String hashValue = "u1";
	Map<String, Double> scoreMembers=new HashMap<>();

	@Before
	public void before() {
		jedis = new JedisLearn();
		assertEquals("connect not successfuly", "PONG", jedis.connect(serverIp, serverPort));
		scoreMembers.put("k1", 100.0);
		scoreMembers.put("k2", 67.0);
		scoreMembers.put("k3", 89.0);
	}

	@Test
	public void connectTest() {
		// jedis.connect(serverIp, serverPort);
	}

	@Test
	public void setStringTest() {
		assertEquals("add String fail", "OK", jedis.setString("under", "growth"));
	}

	@Test
	public void addListTest() {
		List<String> listValue = new ArrayList<>();
		listValue.addAll(Arrays.asList("growth1", "growth2", "growth3", "growth4", "growth5"));
		jedis.addList(listName, listValue);
	}
	
	@Test
	public void getListTest() {
		jedis.getList(listName, 0, 100);
	}
	
	@Test
	public void addHashTest() {
		jedis.addHash(hashName, hashField, hashValue);
	}
	
	@Test
	public void getHashTest() {
		assertEquals("not equals",hashValue,jedis.getHash(hashName, hashField));
	}
	
	@Test
	public void addSetTest() {
		jedis.addSet(setName, "under1","under2");
	}
	
	@Test
	public void getSetTest() {
		assertEquals("not equals",2,jedis.getSet(setName).size());
	}
	
	@Test
	public void addZSetTest() {
		jedis.addZSet(zsetName, scoreMembers);
	}
	
	@Test
	public void getZSetTest() {
		assertEquals("not equals",3,jedis.getZSet(zsetName, 0, 100).size());
	}

}
