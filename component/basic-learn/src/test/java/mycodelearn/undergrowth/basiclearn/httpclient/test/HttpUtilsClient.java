package mycodelearn.undergrowth.basiclearn.httpclient.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

/**
 * 
 * LONGITUDE----113.363736 LATITUDE----23.141278 USERNAME----13829762945
 * PASSWD----123456 HOST----www.jujucloud.com Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年9月19日
 * @version 1.0.0
 */
public class HttpUtilsClient {

	private HttpClient httpClient = new DefaultHttpClient();

	@Test
	public void testReq() {
		String paramString = "http://www.jujucloud.com";
		List<BasicNameValuePair> paramList = new ArrayList<>();
		paramList.add(new BasicNameValuePair("LONGITUDE", "113.363736"));
		paramList.add(new BasicNameValuePair("LATITUDE", "23.141278"));
		paramList.add(new BasicNameValuePair("USERNAME", "111111"));
		paramList.add(new BasicNameValuePair("PASSWD", "111111"));
		// paramList.add(new BasicNameValuePair("HOST", "www.jujucloud.com"));
		try {
			String str = URLEncodedUtils.format(paramList, "UTF-8");
			paramString = paramString + "?" + str;
			HttpGet localHttpGet = new HttpGet(paramString);
			HttpResponse localHttpResponse = this.httpClient.execute(localHttpGet);
			System.out.println(localHttpResponse);
			System.out.println(localHttpResponse.getStatusLine().getStatusCode());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testReqPost() {
		String paramString = "http://www.jujucloud.com";
		List<BasicNameValuePair> paramList = new ArrayList<>();
		paramList.add(new BasicNameValuePair("LONGITUDE", "113.363736"));
		paramList.add(new BasicNameValuePair("LATITUDE", "23.141278"));
		paramList.add(new BasicNameValuePair("USERNAME", "111111"));
		paramList.add(new BasicNameValuePair("PASSWD", "111111"));
		paramList.add(new BasicNameValuePair("KEY_FN", "fn"));
		paramList.add(new BasicNameValuePair("KEY_TEL", "tel"));
		// paramList.add(new BasicNameValuePair("HOST", "www.jujucloud.com"));
		try {
			HttpPost localHttpPost = new HttpPost(paramString);
			localHttpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			localHttpPost.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
			HttpResponse localHttpResponse = this.httpClient.execute(localHttpPost);
			System.out.println(localHttpResponse);
			System.out.println(localHttpResponse.getStatusLine().getStatusCode());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
