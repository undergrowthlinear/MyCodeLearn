package mycodelearn.undergrowth.basiclearn.text.test;


import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* * Format-->用于处理语言环境敏感的信息
	 * 		format-->对象转为字符串
	 * 		parseObject-->字符串转为对象
	 * 	DateFormat-->处理日期、时间格式化
	 * 		SimpleDateFormat-->日期格式化的实现类
	 * 
	--------------------------------------
	G  Era 标志符  Text  AD  
	y  年  Year  1996; 96  
	M  年中的月份  Month  July; Jul; 07  
	w  年中的周数  Number  27  
	W  月份中的周数  Number  2  
	D  年中的天数  Number  189  
	d  月份中的天数  Number  10  
	F  月份中的星期  Number  2  
	E  星期中的天数  Text  Tuesday; Tue  
	a  Am/pm 标记  Text  PM  
	H  一天中的小时数（0-23）  Number  0  
	k  一天中的小时数（1-24）  Number  24  
	K  am/pm 中的小时数（0-11）  Number  0  
	h  am/pm 中的小时数（1-12）  Number  12  
	m  小时中的分钟数  Number  30  
	s  分钟中的秒数  Number  55  
	S  毫秒数  Number  978  
	z  时区  General time zone  Pacific Standard Time; PST; GMT-08:00  
	Z  时区  RFC 822 time zone  -0800  
	--------------------------------------
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月15日
* @version  1.0.0
 */
public class DateFormatTest {

	// 日期格式
	DateFormat dateFormat;
	// 日历
	Calendar calendar;
	// 日期
	Date nowDate = new Date(System.currentTimeMillis());
	// 日期格式化具体类
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	// 时间字符串
	String nowTimeString, pattern;
	// 解析位置
	ParsePosition parsePosition = new ParsePosition(0);
	// 字段位置 跟踪字符串
	FieldPosition fieldPosition = new FieldPosition(DateFormat.MONTH_FIELD);

	/**
	 * 测试DateFormat的静态方法 getAvailableLocales
	 */
	@Test
	public void testDateFormatAvailableLocales() {
		Locale[] locales = DateFormat.getAvailableLocales();
		availLocale(locales);
	}

	/**
	 * 遍历可得到的语言环境
	 * 
	 * @param locales
	 */
	private void availLocale(Locale[] locales) {
		for (Locale locale : locales) {
			System.out.println(locale.getCountry() + "\t" + locale.getDisplayCountry() + "\t" + locale.getDisplayName()
					+ "\t" + locale.getLanguage() + "\t" + locale.getDisplayLanguage());
		}
	}

	/**
	 * 测试获取DateFormat的获取
	 */
	@Test
	public void testDateFormat() {
		// 获取日期格式化
		dateFormat = DateFormat.getDateInstance();
		// 获取到与日期格式化相关的日期
		calendar = dateFormat.getCalendar();
		// 按照默认格式格式化日期
		defaultFormatDate();
	}

	/**
	 * 按照默认格式格式化日期
	 */
	private void defaultFormatDate() {
		// TODO Auto-generated method stub
		nowTimeString = dateFormat.format(nowDate);
		System.out.println(nowTimeString);
		System.out.println(System.currentTimeMillis());
		System.out.println(calendar.getTimeInMillis());
	}

	/**
	 * SimpleDateFormat-->解析日期格式的实现类
	 */
	@Test
	public void testSimpleDateFormat() {
		// 默认格式
		formatSimpleDateFormat();
		// 修改日期格式化模式
		List<String> patternsList = createListPattern();
		// 迭代日期格式
		for (String pattern : patternsList) {
			simpleDateFormat.applyPattern(pattern);
			formatSimpleDateFormat();
		}
	}

	/**
	 * 创建一系列的模式
	 * 
	 * @return
	 */
	private List<String> createListPattern() {
		// TODO Auto-generated method stub
		List<String> patternsList = new ArrayList<String>();
		patternsList.add("		yyyy-MM-dd	HH:mm:ss.S	z");
		patternsList.add("G		yyyy-MM-dd	a HH:mm:ss.S	z");
		patternsList.add("G		yyyy-MM-dd	kk:mm:ss.S	z");
		patternsList.add("		yyyy-MM-dd	a hh:mm:ss.S	z");
		patternsList.add("G		yyyy-MM-dd	KK:mm:ss.S	z");
		patternsList.add("		 一年中的第DD天	HH:mm:ss.S	Z");
		patternsList.add("G 	一年中的第DD天	HH:mm:ss.S	Z");
		patternsList.add("G 	月份中的星期数:FF	HH:mm:ss.S	Z");
		patternsList.add("G 	星期中的天数:EE	HH:mm:ss.S	Z");
		return patternsList;
	}

	/**
	 * 测试不同的模式
	 */
	private void formatSimpleDateFormat() {
		nowTimeString = simpleDateFormat.format(nowDate);
		System.out.println(simpleDateFormat.toPattern());
		System.out.println(nowTimeString);
	}

	/**
	 * 解析字符串为对象
	 */
	@Test
	public void testPrase() {
		try {
			Map<String, String> datePattern = createPatternsMap();
			// 迭代不同的模式 将其转换为日期
			for (Map.Entry<String, String> dateEntry : datePattern.entrySet()) {
				// 获取字符串和日期模式
				System.out.println(dateEntry.getKey() + "---对应模式---" + dateEntry.getValue());
				simpleDateFormat.applyPattern(dateEntry.getValue());
				System.out.println(simpleDateFormat.parse(dateEntry.getKey()));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 创建不同的日期和时间模式
	 * 
	 * @return
	 */
	private Map<String, String> createPatternsMap() {
		// TODO Auto-generated method stub
		Map<String, String> datePattern = new HashMap<String, String>();
		List<String> patternsList = createListPattern();
		// 迭代日期格式
		for (String pattern : patternsList) {
			simpleDateFormat.applyPattern(pattern);
			nowTimeString = simpleDateFormat.format(nowDate);
			// 将不同的日期和模式存储在Map中
			datePattern.put(nowTimeString, pattern);
		}
		return datePattern;
	}

	/**
	 * 测试解析位置
	 */
	@Test
	public void testPrasePosition() {
		nowTimeString = simpleDateFormat.format(nowDate);
		System.out.println("带解析的字符串-->" + nowTimeString);
		System.out.println("日期格式化模式-->" + simpleDateFormat.toPattern());
		System.out.println("开始的位置-->" + parsePosition.getIndex());
		System.out.println("解析的日期-->" + simpleDateFormat.parse(nowTimeString, parsePosition));
		System.out.println("解析完的位置-->" + parsePosition.getIndex());
		System.out.println("解析的日期-->" + simpleDateFormat.parse(nowTimeString, parsePosition));
	}

	/**
	 * 测试日期常量
	 */
	@Test
	public void testConstants() {
		// 用于表示月份常量
		System.out.println(DateFormat.MONTH_FIELD);
		simpleDateFormat.applyPattern("yyyy-MM-dd	HH:mm:ss.S	z");
		StringBuffer toAppendTo = new StringBuffer();
		// 转换日期 跟踪日期字符串
		nowTimeString = simpleDateFormat.format(nowDate, toAppendTo, fieldPosition).toString();
		System.out.println(nowTimeString);
		System.out.println(toAppendTo.toString());
		// 获取字符串的起始位置
		System.out.println(fieldPosition.getBeginIndex() + "\t" + fieldPosition.getEndIndex());
	}

}
