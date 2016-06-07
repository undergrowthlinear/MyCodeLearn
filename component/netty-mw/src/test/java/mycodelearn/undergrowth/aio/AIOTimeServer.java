package mycodelearn.undergrowth.aio;

import java.io.IOException;

/**
 * ʹ��AIO��ʽ���첽������IO
 * �൱��NIO�������� ����˼·�������NIO���Ը��ӵļ�����
 * @author Administrator
 *
 */
public class AIOTimeServer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new Thread(new AIOAsyncTimeServerHandler(9999)).start();
	}

}
