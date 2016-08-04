package mycodelearn.undergrowth.basiclearn.nio.test;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class EncodeUtilTest {
	public static void encodeShortLen_Str(ByteBuffer buffer, String str) {
		if (str == null)
			buffer.putShort((short) 0);
		else
			try {
				byte bs[] = str.getBytes(CHARSET);
				int len = bs.length;
				if (len > 32767) {
					buffer.putShort((short) 0);
				} else {
					buffer.putShort((short) len).put(bs);
				}
			} catch (UnsupportedEncodingException unsupportedencodingexception) {
			}
	}

	public static String decodeShortLen_Str(ByteBuffer buffer)

	{
		byte bs[];
		int len = buffer.getShort();
		if (len < 0)
			throw new IllegalArgumentException("illegal argument");
		if (len == 0)
			return "";
		bs = new byte[len];
		buffer.get(bs);
		try {
			return new String(bs, CHARSET);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String CHARSET = "GBK";
}
