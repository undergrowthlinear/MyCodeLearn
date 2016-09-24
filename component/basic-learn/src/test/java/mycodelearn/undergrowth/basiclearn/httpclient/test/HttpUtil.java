package mycodelearn.undergrowth.basiclearn.httpclient.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;

public class HttpUtil {

	public HttpUtil() {
	}

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public HttpResponse requestByGet(String s, List list) {
		if (list != null) {
			String s1 = URLEncodedUtils.format(list, "UTF-8");
			s = (new StringBuilder(String.valueOf(s))).append("?").append(s1).toString();
		}
		HttpResponse httpresponse = null;
		HttpGet httpget = new HttpGet(s);
		try {
			httpresponse = httpClient.execute(httpget);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("HttpUtil "
				+ (new StringBuilder("resCode=")).append(httpresponse.getStatusLine().getStatusCode()).toString());
		return httpresponse;
	}

	public HttpResponse sendByPost(String s, List list) {
		HttpResponse httpresponse = null;
		try {
			HttpPost httppost = new HttpPost(s);
			httppost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			httppost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
			httpresponse = httpClient.execute(httppost);
			System.out.println(httpresponse);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return httpresponse;
	}

	public void setHttpClient(HttpClient httpclient) {
		httpClient = httpclient;
	}

	private static final String TAG = "HttpUtil";
	private HttpClient httpClient;

}
