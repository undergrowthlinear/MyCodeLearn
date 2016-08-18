package mycodelearn.undergrowth.netty.stable.mw.learn;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public class DataServerInitializer extends ChannelInitializer<Channel>{

	@Override
	protected void initChannel(Channel ch) throws Exception {
		// TODO Auto-generated method stub
		ch.pipeline().addLast(new DataServerHandler());
	}

}
