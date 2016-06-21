package mylearncode.undergrowth.ms.activemq;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 
 * @Description: TODO(Jms工具类)
 * @Author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
 * @Date 2015年12月25日
 * @Version 1.0.0
 */
public class JmsUtils {
	// 连接参数
	private String USER = ActiveMQConnection.DEFAULT_USER;
	private String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private String URL = ActiveMQConnection.DEFAULT_BROKER_URL;
	// 队列名称
	private String SUBJECTQUEUE = "testQueue";
	private String SUBJECTTOPIC = "testTopic";

	// 常用连接参数
	private Destination dest = null;
	private Connection conn = null;
	private Session session = null;
	// 消费者
	private MessageConsumer consumer = null;
	// 生产者
	private MessageProducer producer = null;

	public Destination getDest() {
		return dest;
	}

	public Connection getConn() {
		return conn;
	}

	public Session getSession() {
		return session;
	}

	public MessageConsumer getConsumer() {
		return consumer;
	}

	public MessageProducer getProducer() {
		return producer;
	}

	private void initialize(String destType) throws JMSException, Exception {
		// 连接工厂
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				USER, PASSWORD, URL);
		connectionFactory.setObjectMessageSerializationDefered(true);
		conn = connectionFactory.createConnection();
		// 事务性会话，自动确认消息
		session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 消息的目的地（Queue/Topic）
		if ("queue".equals(destType))
			dest = session.createQueue(SUBJECTQUEUE);
		else {// 发布/订阅
			dest = session.createTopic(SUBJECTTOPIC);
		}
	}

	public void initializeProducer(String destType) throws JMSException,
			Exception {
		initialize(destType);
		// 消息的提供者（生产者）
		producer = session.createProducer(dest);
		// 持久化消息
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		// 连接到JMS提供者（服务器）
		conn.start();
	}

	// 初始化
	public void initializeConsumer(String destType,
			MessageListener consumerHandle) throws JMSException, Exception {
		initialize(destType);
		// 会话创建消息的生产者将消息发送到目的地
		consumer = session.createConsumer(dest);
		conn.start();
		consumer.setMessageListener(consumerHandle);
	}

	// 关闭连接
	public void closeProducer() throws JMSException {
		if (producer != null)
			producer.close();
		if (session != null)
			session.close();
		if (conn != null)
			conn.close();
	}

	// 关闭连接
	public void closeConsumer() throws JMSException {
		System.out.println("Consumer:->Closing connection");
		if (consumer != null)
			consumer.close();
		if (session != null)
			session.close();
		if (conn != null)
			conn.close();
	}

}
