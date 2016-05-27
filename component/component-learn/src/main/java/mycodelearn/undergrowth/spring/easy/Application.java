package mycodelearn.undergrowth.spring.easy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @Description: TODO(进行spring注解上下文测试)
 * @Author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
 * @Date 2016年5月27日
 * @Version 1.0.0
 */
@Configuration
@ComponentScan(basePackages = "mycodelearn.undergrowth.spring")
public class Application {

	@Bean
	MessageService mockMessageService() {
		return new MessageService() {
			public String getMessage() {
				return "Hello World!";
			}
		};
	}

	@Bean
	NotifyService mockNotifyService() {
		return new NotifyService() {

			@Override
			public String getNotify() {
				// TODO Auto-generated method stub
				return "Notify Hello World";
			}
		};
	}

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
		MessagePrinter printer = context.getBean(MessagePrinter.class);
		printer.printMessage();

		NotifyPrinter notifyPrinter = context.getBean(NotifyPrinter.class);
		notifyPrinter.printNotify();
	}

}