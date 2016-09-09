package mycodelearn.undergrowth.common.pattern.test;

import java.util.regex.Pattern;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年9月6日
 * @version 1.0.0
 */
public class PatternTest {

	public static final String SEARCH_PARAM_SEPARATOR = "※";
	// +不能放置在第一个
	public static final String SEARCH_PARAM_PATTERN = "[-|+].*" + SEARCH_PARAM_SEPARATOR + "[01]"
			+ SEARCH_PARAM_SEPARATOR + "[-|+]";
	public static final String searchNameValue = "+aass,-under※1※+";
	public static final String searchDescValue = "+need※0※+";
	public static final String searchPhoneValue = "|5666※1※|";
	public static final String searchAreaCodeValue = "+gz※1※+";
	public static final String searchNameValueError = "eaass,-under※1※+";
	public static final String searchDescValueError = "+need※3※+";
	public static final String searchPhoneValueError = "|56661※|";
	public static final String searchAreaCodeValueError = "+gz※1※6";

	@Test
	public void testSearchPattern() {
		Pattern pattern = Pattern.compile(SEARCH_PARAM_PATTERN);
		assertTrue(pattern.matcher(searchNameValue).matches());
		assertTrue(pattern.matcher(searchDescValue).matches());
		assertTrue(pattern.matcher(searchPhoneValue).matches());
		assertTrue(pattern.matcher(searchAreaCodeValue).matches());
		assertFalse(pattern.matcher(searchNameValueError).matches());
		assertFalse(pattern.matcher(searchDescValueError).matches());
		assertFalse(pattern.matcher(searchPhoneValueError).matches());
		assertFalse(pattern.matcher(searchAreaCodeValueError).matches());
	}

	@Test
	public void testBreak() {
		for (int i = 100; i < 200; i++) {
			for (int j = 100; j < 200; j++) {
				System.out.println("i:" + i + ",j:" + j);
				if (i == j) {
					break;
				}
			}
		}
	}
}
