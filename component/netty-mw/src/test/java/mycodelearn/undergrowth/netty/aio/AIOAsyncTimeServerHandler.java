package mycodelearn.undergrowth.netty.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * ����AIO �������첽������IO��ʽ
 * ��NIO��д����Ҫ��
 * @author Administrator
 *
 */
public class AIOAsyncTimeServerHandler implements Runnable {

	//�������˵��첽����ͨ��
	private AsynchronousServerSocketChannel  asynchronousServerSocketChannel;
	
	public AsynchronousServerSocketChannel getAsynchronousServerSocketChannel() {
		return asynchronousServerSocketChannel;
	}
	//����ʹ�������̵߳ȴ�
	private CountDownLatch latch;

	
	
	public CountDownLatch getLatch() {
		return latch;
	}

	public AIOAsyncTimeServerHandler(int port) throws IOException {
		// TODO Auto-generated constructor stub
		asynchronousServerSocketChannel=AsynchronousServerSocketChannel.open();
		//�󶨶˿�
		asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
		System.out.println("�첽���������������"+port);
	}

	public void run() {
		// TODO Auto-generated method stub
		latch=new CountDownLatch(1);
		//��������
		doAccept();
		try {
			//�ȴ��������
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��������
	 */
	private void doAccept() {
		// TODO Auto-generated method stub
		asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
	}

}
