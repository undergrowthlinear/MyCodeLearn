package mycodelearn.undergrowth.netty.stable.mw.learn;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

public class MyHandler extends ChannelDuplexHandler {

	private ChannelHandlerContext ctx;

	public void beforeAdd(ChannelHandlerContext ctx)

	{
		this.ctx = ctx;
	}

	public void login(String username, String password) {
		ctx.write(new LoginMessage(username, password));
	}
}
