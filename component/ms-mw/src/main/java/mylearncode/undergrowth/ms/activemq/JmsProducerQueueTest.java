package mylearncode.undergrowth.ms.activemq;

import javax.jms.JMSException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JmsProducerQueueTest {

	private JmsProducer producer = null;

	@Before
	public void before() throws JMSException, Exception {
		producer = new JmsProducer("queue");
	}

	@Test
	public void testProducer() throws JMSException, Exception {
		producer.sendMessage("bytes");
	}

	@After
	public void after() throws JMSException {
		producer.close();
	}
}
