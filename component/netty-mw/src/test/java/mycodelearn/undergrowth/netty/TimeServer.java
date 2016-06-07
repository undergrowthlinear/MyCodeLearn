package mycodelearn.undergrowth.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * ʱ�䴦��ķ����
 * ���ӷ�����--telnet localhost 7777
 * 
 * ʹ��netty��ܽ��б��
 * ����
 * 1�������¼������
 * 2��ʹ��������������¼�����ء�ͨ�����¼�������
 * 3���󶨶˿ڷ���
 * 4���ȴ��������
 * 5���ر��¼������
 * @author Administrator
 * 
 */
public class TimeServer {

	private int port;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TimeServer(7777).run();
	}

	public TimeServer(int port) {
		this.port = port;
	}

	public void run() {
		// 1�������¼������
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		try {
			
			// 2��ʹ��������������¼�����ء�ͨ�����¼�������
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap
					.group(boss, worker)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch)
								throws Exception {
							// TODO Auto-generated method stub
							ch.pipeline().addLast(new TimeEncoder(),new TimeServerHandler());
						}

					}).option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE, true);
			// 3���󶨶˿ڷ���
			ChannelFuture future = bootstrap.bind(port).sync();
			//4���ȴ��������
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}finally{
			//5���ر��¼������
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}

	}
}
