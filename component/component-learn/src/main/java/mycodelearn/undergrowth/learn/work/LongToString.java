package mycodelearn.undergrowth.learn.work;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LongToString {

	public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		/*
		 * StringBuilder builder=new StringBuilder("");
		 * //System.out.println(Long.valueOf(builder.toString()));
		 * SimpleDateFormat dateFormat=new SimpleDateFormat("yyMMddHHmmss");
		 * System.out.println(dateFormat.parse(builder.toString()));
		 */
		byte[] by = { 0, 0, 0, 5, 20, -9, -76, 44, 95, 41, 115, -128, -111, 82, 0, -128, 49, 48, 54, 57, 48, 52, 48, 48,
				57, 54, 50, 56, 56, 0, 0, 0, 0, 0, 0, 0, 0, 49, 54, 48, 48, 49, 48, 48, 49, 48, 48, 0, 0, 0, 49, 56, 51,
				51, 57, 55, 56, 56, 56, 54, 48, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 60, 95, 41, 114, -128, 4, 6, 21, 36,
				68, 69, 76, 73, 86, 82, 68, 49, 54, 48, 53, 51, 48, 49, 48, 50, 51, 49, 54, 48, 53, 51, 48, 49, 48, 50,
				51, 49, 56, 51, 51, 57, 55, 56, 56, 56, 54, 48, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		System.out.println(new String(by));
		System.out.println(new String(by,"ascii"));
		System.out.println(new String(by,"utf-8"));
		System.out.println(new String(by,"gb2312"));
	}

}
