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

public class TimeService {

	private static String HOST = "www.jujucloud.com";
	private static String KEY_FN = "fn";
	private static String KEY_TEL = "tel";
	private static String aPasswd = "123456";
	//private static String aUsername = "13829762945";
	private static String aUsername = "18620069240";
	private static String aLongitude = "113.363736";
	private static String aLatitude = "23.141278";
 /**
  * 13993	144.484233000	192.168.23.2	114.67.23.134	HTTP	348	GET /company/mobile/login.json?username=861852030479697&token=e10adc3949ba59abbe56e057f20f883e&tel=13829762945&fn=loginByTel HTTP/1.1 
  */
	@Test
	public void testReq() {
		try {
			HttpUtil httputil = new HttpUtil();
			httputil.setHttpClient(new DefaultHttpClient());
			HttpResponse httpresponse;
			MD5 md5 = new MD5();
			LinkedList linkedlist = new LinkedList();
			linkedlist.add(new BasicNameValuePair(TimeService.KEY_FN, "loginByTel"));
			/*linkedlist.add(new BasicNameValuePair("token", md5.getMD5ofStr(aPasswd)));
			String s = md5.getMD5ofStr(aPasswd);
			System.out.println(s);*/
			String s = md5.getMD5ofStr(aPasswd);
			System.out.println(s);
			linkedlist.add(new BasicNameValuePair("token", "e10adc3949ba59abbe56e057f20f883e"));
			linkedlist.add(new BasicNameValuePair(TimeService.KEY_TEL, aUsername));
			httpresponse = httputil.requestByGet((new StringBuilder("http://")).append(TimeService.HOST)
					.append("/company/mobile/login.json").toString(), linkedlist);
			InputStream inputstream = httpresponse.getEntity().getContent();
			String s1 = inputStreamToString(inputstream).toString();
			System.out.println(s1);
			//
			LinkedList linkedlist1 = new LinkedList();
			;
			linkedlist1.add(new BasicNameValuePair("type", "0"));
			linkedlist1.add(new BasicNameValuePair("posX", aLongitude));
			linkedlist1.add(new BasicNameValuePair("posY", aLatitude));
			linkedlist1.add(new BasicNameValuePair("remark", ""));
			linkedlist1.add(new BasicNameValuePair("address", "\u516C\u53F8"));
			HttpResponse httpresponse1 = httputil.sendByPost((new StringBuilder("http://")).append(TimeService.HOST)
					.append("/company/mobile/attence/operate.do?op=save").toString(), linkedlist1);
			if (httpresponse1.getStatusLine().getStatusCode() == 200) {
				String s2 = EntityUtils.toString(httpresponse1.getEntity(), "UTF-8");
				System.out.println(s2);
			}
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
