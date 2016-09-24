package mylearncode.undergrowth.spring.mw.springinaction.spring.autodetect.autowire;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBean {

	@Bean
	public QualifierConfigurableStudent student(){
		QualifierConfigurableStudent student=new QualifierConfigurableStudent();
		student.setName(ConfigurationBean.class.getName()+"_"+QualifierStudent.class.getName());
		return student;
	}
	
	@Bean
	public ConfigurableStudent conStu(){
		ConfigurableStudent student=new ConfigurableStudent(student());
		return student;
	}
	
}
