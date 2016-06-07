package mycodelearn.undergrowth.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * ���������������Э��
 * 
 * @author Administrator
 * 
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)

		// Discard the received data silently.
		//1���������յ�����Ϣ
		//((ByteBuf) msg).release(); // (3)
		//2�������յ�����Ϣ���������̨
		/*ByteBuf buf=(ByteBuf) msg;
		try {
			while(buf.isReadable()){
				System.out.print((char)buf.readByte());
				System.out.flush();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			ReferenceCountUtil.release(msg);
		}*/
		//3�������յ�����Ϣд�ظ��ͻ���
		ctx.writeAndFlush(msg);

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)

		// Close the connection when an exception is raised.

		cause.printStackTrace();

		ctx.close();

	}

}
