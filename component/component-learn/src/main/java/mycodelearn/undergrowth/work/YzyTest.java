package mycodelearn.undergrowth.work;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.junit.Test;

public class YzyTest {

	private static final String HOST = "172.16.22.243";
	private static final int PORT = 10888;
	String testText = "<?xml version='1.0'?><request>" + "<event>voiceCode</event>"
			+ "<callid>7023351b-039d-447e-8351-87efa373e05b</callid>"
			+ "<accountid>aae25ec101fc12087516bc6564d0aa73</accountid>"
			+ "<appid>0e0ad5c8ba5c4225b9eff2f4c0259196</appid>" + "<called>18520045892</called>"
			+ "<state>0</state>" + "<duration>38</duration>" + "</request>";
	private static final String ENCODE="UTF-8";
	
	
	@Test
	public void sendSms() throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("deprecation")
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(createUrl());
		//httpPost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
		BasicHttpEntity requestBody = new BasicHttpEntity();
		requestBody.setContent(new ByteArrayInputStream(testText
				.getBytes(ENCODE)));
		requestBody
				.setContentLength(testText.getBytes(ENCODE).length);
		httpPost.setEntity(requestBody);
		StringBuffer sb = new StringBuffer();
		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entry = response.getEntity();
			if (entry != null) {
				InputStreamReader is = new InputStreamReader(entry.getContent());
				BufferedReader br = new BufferedReader(is);
				String str = null;
				while ((str = br.readLine()) != null) {
					sb.append(str.trim());
				}
				br.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString());

	}

	private static URI createUrl() {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		String protocal = "http://";
		// String path = "cgi-bin/sendsms?";
		String userName = "admin@ent01";
		String password = "123456";
		String to = "18287131062";
		String msgtype = "1";
		URI uri = null;
		String testText = "<?xml version='1.0'?><request>" + "<event>voiceCode</event>"
				+ "<callid>10cd9004-e611-435e-a9cb-69ccf6b1033a</callid>"
				+ "<accountid>aae25ec101fc12087516bc6564d0aa73</accountid>"
				+ "<appid>0e0ad5c8ba5c4225b9eff2f4c0259196</appid>" + "<called>18287131061</called>"
				+ "<state>0</state>" + "<duration>38</duration>" + "</request>";
		try {
			String text = URLEncoder.encode(testText, "UTF-8");
			builder.append(protocal);
			builder.append(HOST);
			builder.append(":" + PORT + "/");
			// builder.append(path);
			// builder.append("username=" + userName + "&");
			// builder.append("password=" + password + "&");
			// builder.append("to=" + to + "&");
			// builder.append("msgtype=" + msgtype + "&");
			//builder.append("text=" + text);
			uri = new URI(builder.toString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uri;
	}

}
