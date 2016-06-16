package mycodelearn.undergrowth.netty5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
* Date 2016年6月16日
* @version 1.0.0
 */
public class NetServiceTest implements Runnable {

	private Logger logger=LoggerFactory.getLogger(NetServiceTest.class);
	
	private final ServerSocket serverSocket;
	private final ExecutorService pool;
	private final int port;

	public NetServiceTest(int port, int poolSize) throws IOException {
		this.port=port;
		serverSocket = new ServerSocket(this.port);
		pool = Executors.newFixedThreadPool(poolSize);
	}
	

	public void run() { // run the service
		try {
			logger.info("启动服务,port:"+this.port);
			for (;;) {
				pool.execute(new Handler(serverSocket.accept()));
			}
		} catch (IOException ex) {
			shutdownAndAwaitTermination(pool);
		}
	}

	void shutdownAndAwaitTermination(ExecutorService pool) {
		pool.shutdown(); // Disable new tasks from being submitted
		try {
			// Wait a while for existing tasks to terminate
			if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
				pool.shutdownNow(); // Cancel currently executing tasks
				// Wait a while for tasks to respond to being cancelled
				if (!pool.awaitTermination(60, TimeUnit.SECONDS))
					System.err.println("Pool did not terminate");
			}
		} catch (InterruptedException ie) {
			// (Re-)Cancel if current thread also interrupted
			pool.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}
	}

	
	public static void main(String[] args) {
		NetServiceTest netServiceTest=null;
		try {
			netServiceTest = new NetServiceTest(7777, 10);
			netServiceTest.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class Handler implements Runnable {
	private final Socket socket;

	Handler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// read and service request on socket
		BufferedReader bufferedReader = null;
		PrintWriter printWriter = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			printWriter = new PrintWriter(this.socket.getOutputStream(), true);
			// 读取客户端传递的数据
			String string = bufferedReader.readLine();
			System.out.println("客户端传递的数据为:" + string);
			printWriter.println("服务器现在时间为:" + new Date(System.currentTimeMillis()));
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
				if (printWriter != null)
					printWriter.close();
				if (socket != null)

					socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}
	}
}


