package mylearncode.undergrowth.spring.mw.springinaction.spring.autodetect.autowire;

import org.springframework.stereotype.Controller;

@Controller
public class ControllerScan {
	private String name=getClass().getName();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
