package mycodelearn.undergrowth.netty.stable.mw.learn;

import org.junit.Test;

import io.netty.handler.codec.http.QueryStringDecoder;

public class QueryStringDecoderLern {

	@Test
	public void testQueryString() {
		QueryStringDecoder decoder = new QueryStringDecoder("/hello?recipient=world&x=1;y=2");
		assert decoder.path().equals("/hello");
		assert decoder.parameters().get("recipient").get(0).equals("world");
		assert decoder.parameters().get("x").get(0).equals("1");
		assert decoder.parameters().get("y").get(0).equals("2");
	}

}
