package mycodelearn.undergrowth.learn.spring.annotation;

import org.springframework.stereotype.Component;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
* Date 2016年5月27日
* @version  1.0.0
 */
@Component
public class MessageServiceImpl implements MessageService {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return MessageServiceImpl.class.getName();
	}

}
