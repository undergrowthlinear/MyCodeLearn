package mycodelearn.undergrowth.jquery;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mycodelearn.undergrowth.common.key.KeysMapping;


/**
 * 
* @Description: TODO(用于测试jquery 控制器)
* @Author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* @Date 2016年6月4日
* @Version 1.0.0
 */
@Controller
public class JqueryController {

	@RequestMapping(value=KeysMapping.JQUERY_INDEX)
	public String jqueryIndex(Model model){
		return KeysMapping.JQUERY_INDEX_VIEW;
	}
	
}
