package mycodelearn.undergrowth.spring.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
* @Description: TODO(这里用一句话描述这个类的作用)
* #--表达式
* $--属性
* @Author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
* @Date 2016年5月27日
* @Version 1.0.0
 */
@Component
public class NotifyServiceImpl implements NotifyService {
	
	
	private String testInfo;
	
	@Override
	public String getNotify() {
		// TODO Auto-generated method stub
		return NotifyServiceImpl.class.getSimpleName()+"testInfo:"+testInfo;
	}

	@Value("${testInfo}")
	public void setTestInfo(String testInfo) {
		this.testInfo = testInfo;
	}
	

	
}
