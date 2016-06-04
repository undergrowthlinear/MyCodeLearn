package mycodelearn.undergrowth.work;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.esms.MOMsg;
import com.esms.MessageData;
import com.esms.common.entity.Account;
import com.esms.common.entity.BusinessType;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;
import com.esms.common.entity.MTPack.MsgType;
import com.esms.common.entity.MTPack.SendType;
import com.esms.common.entity.MTReport;
import com.esms.common.entity.MTResponse;
import com.esms.common.entity.Ticket;
import com.esms.common.entity.TicketInfo;

import mycodelearn.undergrowth.work.entity.PostServer;


/**
 * 支持SOCKET、HTTP、SOCKET4、SOCKET5
 * 
 * @Description: TODO(测试SDK)
 * @Author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
 * @Date 2015年11月12日
 * @Version 1.0.0
 */
public class SdkTest {

	private static PostServer postServer = null;
	private static  Account account = null;
	private static  int pressure=1;

	public static void main(String[] args) throws Exception{
		before();
		MtSendSms();
	}
	
	
	public static void before() {
		// 获取bean
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		postServer = (PostServer) context.getBean("postServer", PostServer.class);
		// 组装发送
		account = new Account(postServer.getUsername(),
				postServer.getPassword());
	}

	
	public static void MtSendSms() throws Exception {
		// TODO Auto-generated method stub
	    ExecutorService executor=Executors.newFixedThreadPool(pressure);
	    for (int i = 0; i < pressure; i++) {
			executor.execute(new SendMsgThread());
		}
	    if(!executor.isShutdown()){
	    	executor.shutdown();
	    }
	}

	
	static class SendMsgThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				MTPack mtPack = createMTPack(MsgType.SMS,SendType.MASS,"rt22");
				GsmsResponse mtResponse = postServer.getPostMsg().post(account,
						mtPack);
				System.out.println(mtResponse);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * 
	 * @return
	 * @throws Exception 
	 */
	private  static MTPack createMTPack(MsgType msgType,SendType sendType,String content) throws Exception {
		// TODO Auto-generated method stub
		MTPack mtPack = new MTPack();
		mtPack.setBatchID(UUID.randomUUID());
		mtPack.setBatchName("msgType:"+msgType+",测试SDK发送" + System.currentTimeMillis());
		mtPack.setMsgType(msgType);
		// 语音业务
		mtPack.setBizType(22);
		mtPack.setDistinctFlag(true);
		mtPack.setSendType(sendType);
		List<MessageData> msgs=null;
		msgs = createMessageDatas(10000,content);
		if(msgs==null) throw new Exception("消息内容不能为空");
		mtPack.setMsgs(msgs);
		return mtPack;
	}

	private static List<MessageData> createMessageDatas(int num,String content) {
		// TODO Auto-generated method stub
		List<MessageData> msgs = new ArrayList<MessageData>();
		for (int i = 0; i < num; i++) {
			msgs.add(new MessageData("18287" + String.format("%06d", i),
					content));
			/*msgs.add(new MessageData("18087131" + String.format("%03d", i),
					content));
			msgs.add(new MessageData("18587131" + String.format("%03d", i),
					content));*/
		}
		/*msgs.add(new MessageData("18287131061","a$1%!~@#5582a$1%!~@#5582a$1%!~@#5582a$1%!~@#5582a$1%!~@#5582a$1%!~@#5582a$1%!~@#5582"));
		msgs.add(new MessageData("18520045892","a$1%!~@#5582a$1%!~@#5582a$1%!~@#5582a$1%!~@#5582a$1%!~@#5582a$1%!~@#5582a$1%!~@#5582"));
		msgs.add(new MessageData("18047582654","a$1%!~@#5582a$1%!~@#5582a$1%!~@#5582a$1%!~@#5582a$1%!~@#5582a$1%!~@#5582a$1%!~@#5582"));*/
		/*msgs.add(new MessageData("18287131061",content));
		msgs.add(new MessageData("18520045892",content));*/
		//msgs.add(new MessageData("18287131061",content));
		return msgs;
	}

	@Test
	public void getAccountInfo() {
		try {
			System.out.println(postServer.getPostMsg().getAccountInfo(account));
			;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getBusinessType() {
		try {
			BusinessType[] businessTypes = postServer.getPostMsg().getBizTypes(
					account);
			for (BusinessType businessType : businessTypes) {
				System.out.println(businessType);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getMOMessage() {
		try {
			MOMsg[] moMsgs = postServer.getPostMsg().getMOMsgs(account, 10);
			for (MOMsg moMsg : moMsgs) {
				System.out.println(moMsg);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getResponse() {
		try {
			MTResponse[] mtResponses = postServer.getPostMsg().getResps(
					account, 10);
			for (MTResponse mtResponse : mtResponses) {
				System.out.println(mtResponse);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void findReport() {
		try {
			MTReport[] mtReports = postServer.getPostMsg().getReports(account,
					10);
			for (MTReport mtReport : mtReports) {
				System.out.println(mtReport);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void findTickets() {
		try {
			TicketInfo ticketInfo = postServer.getPostMsg().findTickets(
					account, null, null, null, "user01@ent01", 10);
			for (Ticket ticket : ticketInfo.getTickets()) {
				System.out.println(ticket);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}