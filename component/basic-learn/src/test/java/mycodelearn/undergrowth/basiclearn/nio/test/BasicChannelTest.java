package mycodelearn.undergrowth.basiclearn.nio.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月15日
* @version 1.0.0
 */
public class BasicChannelTest {

	RandomAccessFile randomAccessFile, randomAccessFile2;
	FileChannel fileChannel, fileChannel2;
	ByteBuffer buffer, buffer2;

	/**
	 * 测试之前打开文件 分配buffer
	 * 
	 * @throws FileNotFoundException
	 */
	@Before
	public void before() throws FileNotFoundException {
		randomAccessFile = new RandomAccessFile(ClassLoader.getSystemResource("config.xml").getFile(), "rw");
		randomAccessFile2 = new RandomAccessFile(ClassLoader.getSystemResource("config2.xml").getFile(), "rw");
		fileChannel = randomAccessFile.getChannel();
		fileChannel2 = randomAccessFile2.getChannel();
		buffer = ByteBuffer.allocate(1024);
		buffer2 = ByteBuffer.allocate(512);
	}

	/**
	 * 测试完成后 关闭文件
	 * 
	 * @throws IOException
	 */
	@After
	public void after() throws IOException {
		fileChannel.close();
		fileChannel2.close();
		randomAccessFile.close();
		randomAccessFile2.close();
	}

	/**
	 * 测试读写
	 */
	@Test
	public void testRead() {
		// TODO Auto-generated method stub
		try {
			System.out.println(readBuffer(fileChannel, buffer));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 测试标记与重置 You can mark a given position in a Buffer by calling the
	 * Buffer.mark() method. You can then later reset the position back to the
	 * marked position by calling the Buffer.reset() method
	 * 
	 * @throws IOException
	 */
	@Test
	public void testMarkReset() throws IOException {
		int byteRead = fileChannel.read(buffer);
		if (byteRead != -1) {
			// 反转buffer模式
			buffer.flip();
			while (buffer.hasRemaining()) {
				char tmp = (char) buffer.get();
				// 当出现4字符的时候 进行标注
				if (tmp == '4')
					buffer.mark();
				System.out.print(tmp);
			}
			// 将buffer的position置为9字符的位置
			buffer.reset();
			System.out.println();
			System.out.println("置为4字符的起始位置开始读取数据");
			while (buffer.hasRemaining())
				System.out.print((char) buffer.get());
		}
	}

	/**
	 * 测试equals和compareTo 这两个方法都是比较剩余元素 equals() Two buffers are equal if: 5.
	 * They are of the same type (byte, char, int etc.) 6. They have the same
	 * amount of remaining bytes, chars etc. in the buffer. 7. All remaining
	 * bytes, chars etc. are equal.
	 * 
	 * compareTo() The compareTo() method compares the remaining elements
	 * (bytes, chars etc.) of the two buffers, for use in e.g. sorting routines.
	 * A buffer is considered "smaller" than another buffer if: 8. The first
	 * element which is equal to the corresponding element in the other buffer,
	 * is smaller than that in the other buffer. 9. All elements are equal, but
	 * the first buffer runs out of elements before the second buffer does (it
	 * has fewer elements).
	 * 
	 * @throws IOException
	 */
	@Test
	public void testEqualsCompare() throws IOException {
		int byteRead = fileChannel.read(buffer);
		buffer2.put((byte) 'q');
		int byteRead2 = fileChannel2.read(buffer2);
		if (byteRead != -1 && byteRead2 != -1) {
			buffer.flip();
			buffer2.flip();
			System.out.println("剩下元素");
			System.out.println(buffer.remaining());
			System.out.println(buffer2.remaining());
			// 比较两个buffer是否相等
			System.out.println();
			System.out.println(buffer.equals(buffer2));
			System.out.println(buffer.compareTo(buffer2));
		}
	}

	/**
	 * 测试重置方法 The Buffer.rewind() sets the position back to 0, so you can reread
	 * all the data in the buffer. The limit remains untouched, thus still
	 * marking how many elements (bytes, chars etc.) that can be read from the
	 * Buffer.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testRewind() throws IOException {
		int byteRead = fileChannel.read(buffer);
		if (byteRead != -1) {
			// 反转buffer模式
			buffer.flip();
			while (buffer.hasRemaining())
				System.out.print((char) buffer.get());
			// 重新读取buffer
			System.out.println();
			System.out.println("重新读取buffer");
			// 将buffer的positon置为0
			buffer.rewind();
			while (buffer.hasRemaining())
				System.out.print((char) buffer.get());

		}
	}

	/**
	 * 将文件通道的内容读入到缓存中 从缓存中读出数据 返回 If you call clear() the position is set back
	 * to 0 and the limit to capacity. In other words, the Buffer is cleared.
	 * The data in the Buffer is not cleared. Only the markers telling where you
	 * can write data into the Buffer are.
	 * 
	 * 
	 * compact() copies all unread data to the beginning of the Buffer. Then it
	 * sets position to right after the last unread element. The limit property
	 * is still set to capacity, just like clear() does. Now the Buffer is ready
	 * for writing, but you will not overwrite the unread data.
	 * 
	 * @param fileChannel
	 * @param buffer
	 * @return
	 * @throws IOException
	 */
	public String readBuffer(FileChannel fileChannel, ByteBuffer buffer) throws IOException {
		StringBuilder builder = new StringBuilder();
		// 1、读入数据到缓存中
		int byteRead = fileChannel.read(buffer);
		while (byteRead != -1) {
			// 2、转换缓存的模式
			buffer.flip();
			// 3、从缓存中读出数据
			while (buffer.hasRemaining())
				builder.append((char) buffer.get());

			// 4、清除缓冲区
			buffer.clear();
			byteRead = fileChannel.read(buffer);
		}
		// System.out.println(builder.toString());
		return builder.toString();
	}

	@Test
	public void testWriteBuffer() throws IOException {
		buffer.put((byte) '1');
		buffer.put((byte) '2');
		buffer.put((byte) '3');
		buffer.put((byte) '4');
		System.out.println(fileChannel.write(buffer));
	}

	/**
	 * Scatter和Gather模式 Scatter Read支持从一个通道读取到多个缓存区 Gather
	 * Write支持从多个缓存区将数据写入到一个通道中
	 * 
	 * @throws IOException
	 */
	@Test
	public void testScatterGather() throws IOException {
		// 构建buffer数组
		ByteBuffer[] buffers = { buffer, buffer2 };
		// 读数据到多个缓存中
		System.out.println(fileChannel.read(buffers));
		;
		// 转换缓存模式
		buffer.flip();
		buffer2.flip();
		// 读取缓存数据
		System.out.println("缓存1");
		while (buffer.hasRemaining())
			System.out.print((char) buffer.get());
		buffer.rewind();
		System.out.println();
		System.out.println("缓存1和2");
		while (buffer.hasRemaining())
			System.out.print((char) buffer.get());
		while (buffer2.hasRemaining())
			System.out.print((char) buffer2.get());
		buffer.rewind();
		buffer2.rewind();
		System.out.println();
		System.out.println("字符集输出");
		System.out.println(Charset.defaultCharset().decode(buffer));
		buffer.rewind();
		buffer2.rewind();
		// 写缓存数据
		fileChannel2.write(buffers);

	}

	/**
	 * 从一个通道转换到另一个通道
	 * 
	 * @throws IOException
	 */
	@Test
	public void testChannerTransfer() throws IOException {
		System.out.println(fileChannel2.transferFrom(fileChannel, 0, fileChannel.size()));
		;
	}
}
