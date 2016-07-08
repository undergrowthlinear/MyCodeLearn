package mycodelearn.undergrowth.service.redis.impl;


import org.redisson.Redisson;
import org.redisson.core.RSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mycodelearn.undergrowth.redis.redisson.RedisUtils;
import mycodelearn.undergrowth.service.redis.RedisService;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
* Date 2016年6月15日
* @version  1.0.0
 */
@Service
public class DefaultRedisService implements RedisService{

	private final Logger logger = LoggerFactory.getLogger(DefaultRedisService.class);

	@Value("${redisServerIp}")
	private String redisServerIp;
	@Value("${redisServerPort}")
	private String redisServerPort;

	public void setRedisServerIp(String redisServerIp) {
		this.redisServerIp = redisServerIp;
	}

	public void setRedisServerPort(String redisServerPort) {
		this.redisServerPort = redisServerPort;
	}

	@Override
	public boolean nameInRedisSet(String userName) {
		// TODO Auto-generated method stub
		boolean flag = true;
		Redisson redisson = null;
		try {
			redisson = RedisUtils.getInstance().getRedisson(redisServerIp, redisServerPort);
			logger.info("connect to " + redisServerIp + ":" + redisServerPort + ",userName is " + userName);
			RSet<String> rSet = RedisUtils.getInstance().getRSet(redisson, "testUserSet");
			if (!rSet.contains(userName)) {
				rSet.add(userName);
				flag = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			if (redisson != null)
				RedisUtils.getInstance().closeRedisson(redisson);
		}
		return flag;
	}
	
	
}
