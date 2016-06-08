/**
 * 
 */
package mycodelearn.undergrowth.netty.protobuf;

import mycodelearn.undergrowth.netty.protobuf.message.MessageReqProto;
import mycodelearn.undergrowth.netty.protobuf.message.MessageReqProto.MessageReq;
import mycodelearn.undergrowth.netty.protobuf.message.MessageRespProto;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author u1
 * date  2015-7-1
 */
public class ProtoTimeServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		// TODO Auto-generated method stub
		//
		MessageReqProto.MessageReq messageReq=(MessageReq) msg;
		System.out.println("����˽��յ�����ϢΪ:"+messageReq);
		//��д�ͻ�����Ϣ
		ctx.writeAndFlush(resp(messageReq.getReqId()));
	}

	/**
	 * 
	 * @param  reqId
	 * @return 
	 */
	private MessageRespProto.MessageResp resp(int reqId) {
		// TODO Auto-generated method stub
		MessageRespProto.MessageResp.Builder builder=MessageRespProto.MessageResp.newBuilder();
		builder.setReqId(reqId);
		builder.setRespCode(0);
		builder.setDesc("���������յ��ͻ��˵���Ϣ");
		return builder.build();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		cause.printStackTrace();
		ctx.close();
	}
		
	
	
}
