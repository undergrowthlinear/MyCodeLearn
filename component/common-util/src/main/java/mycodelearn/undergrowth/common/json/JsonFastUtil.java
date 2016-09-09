package mycodelearn.undergrowth.common.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json工具类
 * 
 * @author zhangwu
 * @version 1.0
 * @created 08-8月-2016 10:08:58
 */
public class JsonFastUtil {

	private JsonFastUtil() {

	}


	public static String serialize(Object obj) throws Exception {
		return JSON.toJSONString(obj);
	}

	public static <T> T deserialize(String src, Class<T> t) throws Exception {
		if (src == null) {
			throw new IllegalArgumentException("src should not be null");
		}
		return JSON.parseObject(src, t);
	}

}