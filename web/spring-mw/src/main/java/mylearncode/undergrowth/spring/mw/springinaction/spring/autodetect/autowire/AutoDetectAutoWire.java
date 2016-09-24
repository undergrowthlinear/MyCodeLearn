package mylearncode.undergrowth.spring.mw.springinaction.spring.autodetect.autowire;

import org.springframework.stereotype.Component;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用) 自动检测与自动装配
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年9月17日
 * @version 1.0.0
 */
@Component("autoDA")
public class AutoDetectAutoWire {

	private String name=getClass().getName();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	

	

	

}
