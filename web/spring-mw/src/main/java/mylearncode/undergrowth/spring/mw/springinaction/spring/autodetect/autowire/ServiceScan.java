package mylearncode.undergrowth.spring.mw.springinaction.spring.autodetect.autowire;

import org.springframework.stereotype.Service;

@Service
public class ServiceScan {
	private String name=getClass().getName();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
