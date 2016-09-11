package mylearncode.undergrowth.spring.mw.springinaction.springcore.other;

import org.springframework.beans.factory.InitializingBean;

public class PostProcessorBean implements InitializingBean{

	private String username;

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(PostProcessorBean.class.getName()+"____afterPropertiesSet");
	}
}
