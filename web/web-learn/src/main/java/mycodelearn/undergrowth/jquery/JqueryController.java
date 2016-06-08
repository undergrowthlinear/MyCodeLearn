package mycodelearn.undergrowth.jquery;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mycodelearn.undergrowth.common.key.KeysMapping;

/**
 * 
 * Description: TODO(用于测试jquery 控制器)
 * 
 * @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a> Date 2016年6月4日
 * @version 1.0.0
 */
@Controller
public class JqueryController {

	@RequestMapping(value = KeysMapping.JQUERY_INDEX)
	public String jqueryIndex(Model model) {
		return KeysMapping.JQUERY_INDEX_VIEW;
	}

	@RequestMapping(value = KeysMapping.JQUERY_AJAX)
	public String jqueryAjax(Model model) {
		return KeysMapping.JQUERY_AJAX_VIEW;
	}

	@RequestMapping(value = KeysMapping.JQUERY_AUTHEN)
	public String jqueryAuthen(Model model, @RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password) {
		String ret = "-1";
		if ("1".equals(username) && "2".equals(password))
			ret = "0";
		StringBuilder jsonData = new StringBuilder();
		jsonData.append('{');
		jsonData.append("\"ret\":\"");
		jsonData.append(ret+"\"");
		jsonData.append('}');
		model.addAttribute("jsonData", jsonData.toString());
		return KeysMapping.JQUERY_AUTHEN_VIEW;
	}
	
	@RequestMapping(value = KeysMapping.JQUERY_EFFECT)
	public String jqueryEffect(Model model) {
		return KeysMapping.JQUERY_EFFECT_VIEW;
	}
	
	@RequestMapping(value = KeysMapping.JQUERY_EVENT)
	public String jqueryEvent(Model model) {
		return KeysMapping.JQUERY_EVENT_VIEW;
	}
	
	@RequestMapping(value = KeysMapping.JQUERY_MODULE)
	public String jqueryModule(Model model) {
		return KeysMapping.JQUERY_MODULE_VIEW;
	}
	
	@RequestMapping(value = KeysMapping.JQUERY_SEARCH)
	public String jquerySearch(Model model) {
		return KeysMapping.JQUERY_SEARCH_VIEW;
	}

}
