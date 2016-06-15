package mycodelearn.undergrowth.common.json;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="zhangwu@wxchina.com">Wu.Zhang</a> 
 * Date 2016年6月15日
 * @version 1.0.0
 */
public class PublicJsonUtils {

	public static String jsonData(String ret, String msg) {
		StringBuilder jsonData = new StringBuilder();
		jsonData.append('{');
		jsonData.append("\"ret\":\"");
		jsonData.append(ret + "\"");
		jsonData.append(",\"msg\":\"");
		jsonData.append(msg + "\"");
		jsonData.append('}');
		return jsonData.toString();
	}

}
