package mylearncode.undergrowth.spring.mw.bootstrap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BootstrapController {

	// bootstrap
	public static final String JBOOTSTRAP_INDEX = "/bootstrap";
	public static final String JBOOTSTRAP_INDEX_VIEW = "/bootstrap/index";

	@RequestMapping(value = JBOOTSTRAP_INDEX)
	public String jqueryIndex(Model model) {
		return JBOOTSTRAP_INDEX_VIEW;
	}

}
