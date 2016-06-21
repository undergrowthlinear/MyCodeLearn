package mylearncode.undergrowth.ms.activemq.test;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class JmsTemplateTest {

	private JmsTemplate jmsTemplate = null;
	private Destination destination = null;
	ApplicationContext context = null;

	@Before
	public void before() throws JMSException, Exception {
		context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		jmsTemplate = (JmsTemplate) context.getBean("myJmsTemplate");
		destination = (Destination) context.getBean("queueDestination");
	}

	@Test
	public void testSend() throws JMSException, Exception {
		jmsTemplate.send(destination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				return session.createTextMessage("Jms模板");
			}
		});
	}

	@Test
	public void testReceive() throws JMSException, Exception {
		//通过监听器异步接收消息
	}

	@After
	public void after() throws JMSException {

	}
}
