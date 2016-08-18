package mycodelearn.undergrowth.netty.stable.mw.learn;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

public class DataServerHandler extends SimpleChannelInboundHandler<Message>{

	private final AttributeKey<Boolean> auth=AttributeKey.valueOf("auth");
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
		// TODO Auto-generated method stub
		Attribute<Boolean> attr=ctx.attr(auth);
		Channel ch = ctx.channel();
	}

}
