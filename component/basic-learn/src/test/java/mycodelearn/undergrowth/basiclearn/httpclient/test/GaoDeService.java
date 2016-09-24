package mycodelearn.undergrowth.basiclearn.httpclient.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class GaoDeService {

	private static final String HOST="restapi.amap.com";
	private static final String PATH="/v3/geocode/geo";
	private static final String KEY_VALUE = "6099680eea65c2777c064290d654a363";
	private static final String ADDRESS_VALUE = "广东省广州市天河区华景路1号南方通信大厦";
	private static final String CITY_VALUE = "广州市";
	private static final String OUTPUT_VALUE = "JSON";
	
	@Test
	public void testReq() {
		try {
			HttpUtil httputil = new HttpUtil();
			httputil.setHttpClient(new DefaultHttpClient());
			HttpResponse httpresponse;
			LinkedList linkedlist = new LinkedList();
			linkedlist.add(new BasicNameValuePair("key", KEY_VALUE));
			linkedlist.add(new BasicNameValuePair("address", ADDRESS_VALUE));
			linkedlist.add(new BasicNameValuePair("city", CITY_VALUE));
			linkedlist.add(new BasicNameValuePair("output", OUTPUT_VALUE));
			httpresponse = httputil.requestByGet((new StringBuilder("http://")).append(GaoDeService.HOST)
					.append(PATH).toString(), linkedlist);
			InputStream inputstream = httpresponse.getEntity().getContent();
			String s1 = inputStreamToString(inputstream).toString();
			System.out.println(s1);
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private StringBuilder inputStreamToString(InputStream inputstream) {
		BufferedReader bufferedreader;
		StringBuilder stringbuilder = new StringBuilder();
		bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
		String s;
		try {
			s = bufferedreader.readLine();
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
			return stringbuilder;
		}
		if (s == null)
			return stringbuilder;
		stringbuilder.append(s);
		return stringbuilder;
	}

}
