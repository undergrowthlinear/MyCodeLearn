/**
 * 
 */
package mycodelearn.undergrowth.netty.adhere.unpack;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author u1
 * date  2015-7-6
 */
public class TcpLineBasedFrameDecoderTimeServerHandler extends ChannelHandlerAdapter {

	private int count=0;
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		// TODO Auto-generated method stub
		String conString=(String) msg;
		System.out.println("����������������Ϊ:"+conString+"\t ���ǵ�"+(++count));
		String nowTime="����ʱ��Ϊ:"+new Date(System.currentTimeMillis())+System.getProperty("line.separator");
		ByteBuf rep=Unpooled.copiedBuffer(nowTime.getBytes());
		ctx.writeAndFlush(rep);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		ctx.close();
		cause.printStackTrace();
	}
	
	
	
}
