package mylearncode.undergrowth.ms.activemq.test;

import java.util.Enumeration;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import mylearncode.undergrowth.ms.activemq.JmsObjectMessageBean;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月21日
* @version  1.0.0
 */
public class JmsQueueConsumer implements MessageListener {

	@SuppressWarnings("rawtypes")
	@Override
	public void onMessage(Message msg) {
		try {
			if (msg instanceof TextMessage) {
				TextMessage message = (TextMessage) msg;
				System.out.println("------Received TextMessage------");
				System.out.println(message.getText());
			} else if (msg instanceof MapMessage) {
				MapMessage message = (MapMessage) msg;
				System.out.println("------Received MapMessage------");
				System.out.println(message.getLong("long"));
				System.out.println(message.getBoolean("boolean"));
				System.out.println(message.getShort("short"));
				System.out.println(message.getString("MapMessage"));
				System.out.println("------Received MapMessage for while------");
				Enumeration enumer = message.getMapNames();
				while (enumer.hasMoreElements()) {
					Object obj = enumer.nextElement();
					System.out.println(message.getObject(obj.toString()));
				}
			} else if (msg instanceof StreamMessage) {
				StreamMessage message = (StreamMessage) msg;
				System.out.println("------Received StreamMessage------");
				System.out.println(message.readString());
				System.out.println(message.readBoolean());
				System.out.println(message.readLong());
			} else if (msg instanceof ObjectMessage) {
				System.out.println("------Received ObjectMessage------");
				ObjectMessage message = (ObjectMessage) msg;
				JmsObjectMessageBean jmsObject = (JmsObjectMessageBean) message.getObject();
				System.out.println(jmsObject.getUserName() + "__" + jmsObject.getAge() + "__" + jmsObject.isFlag());
			} else if (msg instanceof BytesMessage) {
				System.out.println("------Received BytesMessage------");
				BytesMessage message = (BytesMessage) msg;
				byte[] byteContent = new byte[1024];
				int length = -1;
				StringBuffer content = new StringBuffer();
				while ((length = message.readBytes(byteContent)) != -1) {
					content.append(new String(byteContent, 0, length));
				}
				System.out.println(content.toString());
			} else {
				System.out.println(msg);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
