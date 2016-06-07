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
 * @Date  2015-7-6
 */
public class TcpLineBasedFrameDecoderTimeClientHandler extends ChannelHandlerAdapter {

	private String content="���ڵ�ʱ��Ϊ:"+System.getProperty("line.separator");
	private int count=0;
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++){
			ByteBuf reqBuf=Unpooled.copiedBuffer(content.getBytes());
			ctx.writeAndFlush(reqBuf);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		// TODO Auto-generated method stub
		String conString=(String) msg;
		System.out.println("�ͻ��˶���������Ϊ:"+conString+"\t ���ǵ�"+(++count));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		ctx.close();
		cause.printStackTrace();
	}

	
	
}
