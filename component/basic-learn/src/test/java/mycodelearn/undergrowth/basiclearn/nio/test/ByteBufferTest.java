package mycodelearn.undergrowth.basiclearn.nio.test;

import java.nio.ByteBuffer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月28日
 * @version 1.0.0
 */
public class ByteBufferTest {

	private Logger logger=LoggerFactory.getLogger(ByteBufferTest.class);
	
	protected int sequence=1;
	protected int sessionId=2;
	private int sourceIp=3;
	protected String node="45";

	public ByteBuffer encode(ByteBuffer buffer) {
		buffer.clear();
		buffer.position(4);
		buffer.putInt(getCommand()).putInt(sequence);
		buffer.putInt(sessionId);
		buffer.putInt(sourceIp);
		EncodeUtilTest.encodeShortLen_Str(buffer, node);
		buffer.flip();
		buffer.putInt(buffer.remaining());
		buffer.position(0);
		return buffer;
	}

	private int getCommand() {
		// TODO Auto-generated method stub
		return 12345678;
	}

	@Test
	public void testBuffer() {
		ByteBufferTest byteBufferTest = new ByteBufferTest();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		ByteBuffer bbByteBuffer=byteBufferTest.encode(buffer);
		logger.debug(bbByteBuffer.toString());
		logger.debug(bbByteBuffer.array().toString());
		logger.debug(new String(bbByteBuffer.array()));
	}

}
