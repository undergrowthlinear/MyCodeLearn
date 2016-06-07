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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

public class HttpOriginalTest {

	private static final String HOST = "172.16.22.243";
	private static final int PORT = 8086;
	String testText = "username=admin@ent02&password=123456&to=18287131062&msgtype=1&text=@<>qw";
	private static final String ENCODE = "UTF-8";

	@Test
	public void sendSmsGet() {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(createUrl());
		StringBuffer sb = new StringBuffer();
		try {
			HttpResponse response = httpClient.execute(httpGet);
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

	@Test
	public void sendSmsPost() {
		// TODO Auto-generated method stub
		try {
			@SuppressWarnings("deprecation")
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(createUrl());
			BasicHttpEntity requestBody = new BasicHttpEntity();
			requestBody.setContent(new ByteArrayInputStream(testText.getBytes(ENCODE)));
			requestBody.setContentLength(testText.getBytes(ENCODE).length);
			httpPost.setEntity(requestBody);
			StringBuffer sb = new StringBuffer();
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
			System.out.println(sb.toString());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static URI createPostUrl() {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		String protocal = "http://";
		String path = "cgi-bin/sendsms";
		String userName = "admin@ent03";
		String password = "123456";
		String to = "18287131062";
		String msgtype = "9";
		int bizType = 22;
		URI uri = null;
		try {
			String text = URLEncoder.encode("qwer3", "GBK");
			builder.append(protocal);
			builder.append(HOST);
			builder.append(":" + PORT + "/");
			builder.append(path);
			/*
			 * builder.append("username=" + userName + "&");
			 * builder.append("password=" + password + "&");
			 * builder.append("to=" + to + "&"); builder.append("msgtype=" +
			 * msgtype + "&"); builder.append("text=" + text+ "&");
			 * builder.append("biztype=" + bizType);
			 */
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

	private static URI createUrl() {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		String protocal = "http://";
		String path = "cgi-bin/sendsms?";
		String userName = "admin@ent02";
		String password = "123456";
		String to = "18287131062";
		String msgtype = "1";
		int bizType = 0;
		URI uri = null;
		try {
			String text = URLEncoder.encode("qwer3", "GBK");
			builder.append(protocal);
			builder.append(HOST);
			builder.append(":" + PORT + "/");
			builder.append(path);
			builder.append("username=" + userName + "&");
			builder.append("password=" + password + "&");
			builder.append("to=" + to + "&");
			builder.append("msgtype=" + msgtype + "&");
			builder.append("text=" + text + "&");
			builder.append("biztype=" + bizType);
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
