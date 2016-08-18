package mycodelearn.undergrowth.netty.stable.mw.learn;

import static io.netty.buffer.Unpooled.buffer;
import static io.netty.buffer.Unpooled.directBuffer;
import static io.netty.buffer.Unpooled.wrappedBuffer;

import java.nio.ByteBuffer;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import io.netty.buffer.ByteBuf;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用) *
 * 
 * <pre>
 *      +-------------------+------------------+------------------+
 *      | discardable bytes |  readable bytes  |  writable bytes  |
 *      |                   |     (CONTENT)    |                  |
 *      +-------------------+------------------+------------------+
 *      |                   |                  |                  |
 *      0      <=      readerIndex   <=   writerIndex    <=    capacity
 * </pre>
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年8月16日
 * @version 1.0.0
 */
public class UnpooledAlloc {

	ByteBuf heapBuffer = buffer(12, 12);
	Random random = new Random();

	@Before
	public void before() {
		while (heapBuffer.maxWritableBytes() >= 4) {
			System.out.println(heapBuffer.discardReadBytes() + "-" + heapBuffer.readerIndex() + "-"
					+ heapBuffer.writerIndex() + "-" + heapBuffer.capacity());
			heapBuffer.writeInt(random.nextInt());
		}
	}

	@Test
	public void testbufferBufWhile() {
		System.out.println("====================================================================");
		while (heapBuffer.isReadable()) {
			System.out.println(heapBuffer.discardReadBytes() + "-" + heapBuffer.readerIndex() + "-"
					+ heapBuffer.writerIndex() + "-" + heapBuffer.capacity());
			System.out.println(heapBuffer.readByte());
		}
		System.out.println(heapBuffer.discardReadBytes() + "-" + heapBuffer.readerIndex() + "-"
				+ heapBuffer.writerIndex() + "-" + heapBuffer.capacity());
		System.out.println("====================================================================");

	}

	@Test
	public void testbufferBuf() {
		ByteBuf buffer = heapBuffer;
		for (int i = 0; i < buffer.capacity(); i++) {
			byte b = buffer.getByte(i);
			System.out.println((char) b);
		}

	}

	@Test
	public void testUnpooledAlloc() {
		ByteBuf directBuffer = directBuffer(256);
		ByteBuf wrappedBuffer = wrappedBuffer(new byte[128], new byte[256]);
		ByteBuf copiedBuffe = copiedBuffer(ByteBuffer.allocate(128));
	}

	private ByteBuf copiedBuffer(ByteBuffer allocate) {
		// TODO Auto-generated method stub
		return null;
	}

}
