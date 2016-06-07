package mycodelearn.undergrowth.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * ���������ʱ ���д���ͻ�������
 * @author Administrator
 *
 */
public class AcceptCompletionHandler
		implements
		CompletionHandler<AsynchronousSocketChannel,AIOAsyncTimeServerHandler> {

	public void completed(AsynchronousSocketChannel result,
			AIOAsyncTimeServerHandler attachment) {
		// TODO Auto-generated method stub
		//������������Ŀͻ�������
		attachment.getAsynchronousServerSocketChannel().accept(attachment, this);
		ByteBuffer dst=ByteBuffer.allocate(1024);
		//���������
		result.read(dst, dst, new ServerReadCompletionHandler(result));
	}

	public void failed(Throwable exc, AIOAsyncTimeServerHandler attachment) {
		// TODO Auto-generated method stub
		exc.printStackTrace();
		//ʹ�������߳̽���
		attachment.getLatch().countDown();
	}

}
