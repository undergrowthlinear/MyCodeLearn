/**
 * 
 */
package mycodelearn.undergrowth.netty.adhere.unpack;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * //netty���5����
		//1������NIO�̳߳أ��������ӡ���������
		//2��ʹ������������̳߳ء�NIOͨ�����¼�����������������
		//3���󶨶˿ڣ�ͬ���ȴ��������
		//4��ͬ���ȴ��ر�ͨ��,��ֹmain�����˳�
		//5���ر��̳߳���Դ
 * @author u1
 * @Date  2015-7-6
 */
public class TcpAdhereTimeServer {

	private int port;
	
	/**
	 * @param i
	 */
	public TcpAdhereTimeServer(int port) {
		// TODO Auto-generated constructor stub
		this.port=port;
	}


	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		new TcpAdhereTimeServer(3333).run();
	}
    
	
	public void run() throws InterruptedException{
		//netty���5����
				//1������NIO�̳߳أ��������ӡ���������
				NioEventLoopGroup boss=new NioEventLoopGroup();
				NioEventLoopGroup work=new NioEventLoopGroup();
				//2��ʹ������������̳߳ء�NIOͨ�����¼�����������������
				ServerBootstrap bootstrap=new ServerBootstrap();
				bootstrap.group(boss, work).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						// TODO Auto-generated method stub
						/*ch.pipeline().addLast(new LineBasedFrameDecoder(1024));*/
						ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer(System.getProperty("line.separator").getBytes())));
						ch.pipeline().addLast(new StringDecoder());
						ch.pipeline().addLast(new TcpLineBasedFrameDecoderTimeServerHandler());
					}
				}).childOption(ChannelOption.SO_KEEPALIVE, true);
				//3���󶨶˿ڣ�ͬ���ȴ��������
				ChannelFuture future=bootstrap.bind(port).sync();
				//4��ͬ���ȴ��ر�ͨ��,��ֹmain�����˳�
				future.channel().closeFuture().sync();
				//5���ر��̳߳���Դ
				boss.shutdownGracefully();
				work.shutdownGracefully();
	}
}
