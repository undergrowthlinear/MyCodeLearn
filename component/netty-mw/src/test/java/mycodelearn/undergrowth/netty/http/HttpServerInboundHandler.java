package mycodelearn.undergrowth.netty.http;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpRequest;

public class HttpServerInboundHandler extends ChannelInboundHandlerAdapter {

	private static Log log = LogFactory.getLog(HttpServerInboundHandler.class);

	private HttpRequest request;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof HttpRequest) {
			request = (HttpRequest) msg;

			String uri = request.uri();
			System.out.println(request);
			/*
			 * System.out.println(request.method()); System.out.println("Uri:" +
			 * uri); System.out.println(request.headers());
			 */
		}
		if (msg instanceof HttpContent) {
			HttpContent content = (HttpContent) msg;
			ByteBuf buf = content.content();
			System.out.println(buf.toString(io.netty.util.CharsetUtil.UTF_8));
			buf.release();

			String res = "I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,I am OK,";
			StringBuilder builder=new StringBuilder();
			builder.append("<html>");
			builder.append("<head>");
			builder.append("<title>");
			builder.append("测试标题");
			builder.append("</title>");
			builder.append("</head>");
			builder.append("<body>");
			builder.append(res);
			builder.append("</body>");
			builder.append("</html>");
			 res=builder.toString();
			System.out.println(res.getBytes().length);
			FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK,
					Unpooled.wrappedBuffer(res.getBytes("UTF-8")));
			response.headers().set(CONTENT_TYPE, "text/plain");
			response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
			// if (HttpHeaders.isKeepAlive(request.headers().))
			{
				response.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
			}
			ctx.write(response);
			ctx.flush();
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		log.error(cause.getMessage());
		ctx.close();
	}

}
