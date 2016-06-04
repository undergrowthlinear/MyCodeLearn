package mycodelearn.undergrowth.spring.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mycodelearn.undergrowth.common.key.KeysMapping;


/**
 * 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
 * @Date 2016年5月27日
 * @Version 1.0.0
 */
@Controller
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value = KeysMapping.REST_GREETING)
	public String greeting(Model model, @RequestParam(value = "name", defaultValue = "World") String name) {
		Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));
		model.addAttribute(greeting);
		return KeysMapping.REST_GREETING_VIEW;
	}

}
