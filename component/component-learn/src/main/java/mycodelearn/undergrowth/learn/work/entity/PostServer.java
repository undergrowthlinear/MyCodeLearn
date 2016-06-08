package mycodelearn.undergrowth.learn.work.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.esms.PostMsg;

@Component
public class PostServer{

	@Value("${mtHost}")
	private String mtHost;
	@Value("${mtPort}")
	private int mtPort;
	@Value("${moHost}")
	private String moHost;
	@Value("${moPort}")
	private int moPort;
	@Value("${username}")
	private String username;
	@Value("${password}")
	private String password;
	private PostMsg postMsg;

	public PostServer() {
		postMsg = new PostMsg();
	}
	
	public void setMtHost(String mtHost) {
		this.mtHost = mtHost;
	}

	
	public void setMtPort(int mtPort) {
		this.mtPort = mtPort;
	}



	public void setMoHost(String moHost) {
		this.moHost = moHost;
	}

	

	public void setMoPort(int moPort) {
		this.moPort = moPort;
	}

	

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getUsername() {
		return username;
	}
	

	public String getPassword() {
		return password;
	}
	

	public void setPassword(String password) {
		this.password = password;
	}

	public PostMsg getPostMsg() {
		return postMsg;
	}

	public void setPostMsg(PostMsg postMsg) {
		this.postMsg = postMsg;
	}

	
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		isExist();
		postMsg.getCmHost().setHost(mtHost, mtPort);
		postMsg.getWsHost().setHost(moHost, moPort);
	}

	private void isExist() {
		// TODO Auto-generated method stub
		mtHost=mtHost==null?"127.0.0.1":mtHost;
		moHost=moHost==null?"127.0.0.1":moHost;
		mtPort=mtPort==0?8090:mtPort;
		moPort=moPort==0?8088:mtPort;
		username=username==null?"admin@ent03":username;
		password=password==null?"123456":password;
	}

}
