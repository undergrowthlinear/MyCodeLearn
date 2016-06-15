package mycodelearn.undergrowth.service.redis;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
* Date 2016年6月15日
* @version 1.0.0
 */
public interface RedisService {

	/**
	 * 判断用户名是否在Redis集合中
	* @param userName
	* @return
	 */
	public boolean nameInRedisSet(String userName);
	
}
