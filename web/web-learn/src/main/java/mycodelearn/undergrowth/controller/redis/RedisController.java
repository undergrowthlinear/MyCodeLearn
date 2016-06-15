package mycodelearn.undergrowth.controller.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mycodelearn.undergrowth.common.json.PublicJsonUtils;
import mycodelearn.undergrowth.common.key.KeysMapping;
import mycodelearn.undergrowth.service.redis.impl.DefaultRedisService;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="zhangwu@wxchina.com">Wu.Zhang</a> Date 2016年6月15日
 * @version 1.0.0
 */
@Controller
public class RedisController {

	@RequestMapping(value = KeysMapping.REDIS_NAME)
	public String redisName(Model model, @RequestParam(name = "username") String userName) {
		String ret = "0";
		String msg = userName + "_您的姓名在Redis缓存中";
		if (!redisService.nameInRedisSet(userName)) {
			ret = "-1";
			msg = userName + "_您的姓名不在Redis缓存中";
		}
		model.addAttribute("jsonData", PublicJsonUtils.jsonData(ret, msg));
		return KeysMapping.PUBLIC_JSON_VIEW;
	}

	@RequestMapping(value = KeysMapping.REDIS_INDEX)
	public String redisIndex(Model model) {
		return KeysMapping.REDIS_INDEX_VIEW;
	}

	@Autowired
	private DefaultRedisService redisService;

	public void setRedisService(DefaultRedisService redisService) {
		this.redisService = redisService;
	}

}
