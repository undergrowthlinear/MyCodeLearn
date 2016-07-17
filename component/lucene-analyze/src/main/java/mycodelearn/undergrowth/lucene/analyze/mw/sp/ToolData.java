package mycodelearn.undergrowth.lucene.analyze.mw.sp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolData {
	public String[] fieldNames = { "id", "email", "content", "attach", "date" };
	public String[] ids = { "1", "2", "3", "4", "5" };
	public String[] emails = { "undergrowth@126.com", "undergrowth@163.com", "undergrowth@gamil.com",
			"undergrowth@qq.com", "undergrowth@139.com" };
	public String[] contents = { "126 email welcome you", "163 email is wangyi product",
			"gmail email email is google ,it is great", "qq email is tencent,it is free",
			"139 email is mobile,it charge money" };
	public int[] attachs = { 5, 7, 2, 9, 3 };
	public Date[] dates = null;

	public ToolData(String[] fieldNames, String[] ids, String[] emails, String[] contents, int[] attachs,
			Date[] dates) {
		this.fieldNames = fieldNames;
		this.ids = ids;
		this.emails = emails;
		this.contents = contents;
		this.attachs = attachs;
		this.dates = dates;
	}

	public ToolData() {
		initDatesData();
	}

	private void initDatesData() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		dates = new Date[attachs.length];
		for (int i = 0; i < attachs.length; i++) {
			try {
				dates[i] = format.parse("2016-7-" + (15 - i));
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}