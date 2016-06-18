package mylearncode.undergrowth.ms.activemq;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

public class JmsProducer {

	JmsUtils jmsUtils = null;
	private Session session = null;
	private MessageProducer producer = null;

	public JmsProducer(String destType) throws JMSException, Exception {
		jmsUtils = new JmsUtils();
		jmsUtils.initializeProducer(destType);
		session = jmsUtils.getSession();
		producer = jmsUtils.getProducer();
	}

	public void sendMessage(String msgType) throws JMSException, Exception {
		// 发送文本消息
		if ("text".equals(msgType)) {
			String textMsg = "ActiveMQ Text Message!";
			TextMessage msg = session.createTextMessage();
			// TextMessage msg = session.createTextMessage(textMsg);
			msg.setText(textMsg);
			producer.send(msg);
		}
		// 发送Map消息
		if ("map".equals(msgType)) {
			MapMessage msg = session.createMapMessage();
			msg.setBoolean("boolean", true);
			msg.setShort("short", (short) 0);
			msg.setLong("long", 123456);
			msg.setString("MapMessage", "ActiveMQ Map Message!");
			producer.send(msg);
		}
		// 发送流消息
		if ("stream".equals(msgType)) {
			String streamValue = "ActiveMQ stream Message!";
			StreamMessage msg = session.createStreamMessage();
			msg.writeString(streamValue);
			msg.writeBoolean(false);
			msg.writeLong(1234567890);
			producer.send(msg);
		}
		// 发送对象消息
		if ("object".equals(msgType)) {
			JmsObjectMessageBean jmsObject = new JmsObjectMessageBean(
					"ActiveMQ Object Message", 18, false);
			ObjectMessage msg = session.createObjectMessage();
			msg.setObject(jmsObject);
			producer.send(msg);
		}
		// 发送字节消息
		if ("bytes".equals(msgType)) {
			String byteValue = "字节消息";
			BytesMessage msg = session.createBytesMessage();
			msg.writeBytes(byteValue.getBytes());
			producer.send(msg);
		}
	}

	// 关闭连接
	public void close() throws JMSException {
		jmsUtils.closeProducer();
	}
}
