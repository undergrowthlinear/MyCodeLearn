/**
 * 
 */
package mycodelearn.undergrowth.netty.protobuf;

import mycodelearn.undergrowth.netty.protobuf.message.MessageReqProto;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * ʹ��google��protobufЭ�����ͨ�� 
 * //netty���5����
//1������NIO�̳߳أ��������ӡ���������
//2��ʹ������������̳߳ء�NIOͨ�����¼�����������������
//3���󶨶˿ڣ�ͬ���ȴ��������
//4��ͬ���ȴ��ر�ͨ��,��ֹmain�����˳�
//5���ر��̳߳���Դ
 * 
 * @author u1
 * @Date 2015-7-1
 */
public class ProtoTimeServer {

	private int port;

	public static void main(String[] args) {
       new ProtoTimeServer(6666).run();
	}

	public ProtoTimeServer(int port) {
		this.port = port;
	}

	public void run() {
		NioEventLoopGroup boss = null, work = null;
		try {
			// netty���5����
			// 1�������̳߳�
			boss = new NioEventLoopGroup();
			work = new NioEventLoopGroup();
			// 2��ʹ�������������̳߳ء�ͨ����ͨ��������������ִ�в���
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(boss, work).channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 100)
					.option(ChannelOption.SO_KEEPALIVE, true)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch)
								throws Exception {
							// TODO Auto-generated method stub
							// ���ý��봦�����Ͱ��������
							/*ch.pipeline().addLast(
									new ProtobufVarint32FrameDecoder());*/
							ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1048576, 0, 4,0,4));
							ch.pipeline().addLast(
									new ProtobufDecoder( //���ý�������Ŀ������
											MessageReqProto.MessageReq
													.getDefaultInstance()));
							// ���ñ������Ͱ��������
							/*ch.pipeline().addLast(
									new ProtobufVarint32LengthFieldPrepender());*/
							ch.pipeline().addLast(new LengthFieldPrepender(4));
							ch.pipeline().addLast(new ProtobufEncoder());
							// ���ô�����
							ch.pipeline().addLast(new ProtoTimeServerHandler());
						}
					});
			// 3���󶨶˿�ͬ������
			ChannelFuture future = bootstrap.bind(port).sync();
			// 4�������˿ڹر�
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 5���ͷ���Դ
			boss.shutdownGracefully();
			work.shutdownGracefully();
		}

	}
}
