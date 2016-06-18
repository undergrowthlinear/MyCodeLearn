package mylearncode.undergrowth.ms.activemq;

import javax.jms.JMSException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JmsProducerTopicTest {

	private JmsProducer producer = null;

	@Before
	public void before() throws JMSException, Exception {
		producer = new JmsProducer("topic");
	}

	@Test
	public void testProducer() throws JMSException, Exception {
		producer.sendMessage("text");
	}

	@After
	public void after() throws JMSException {
		producer.close();
	}
}
