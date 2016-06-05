package learn.TestDateAndCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sure GM on 2016/6/5 14:52.
 */
public class TestSimpleDateFormat {

	/**
	 * 封装一个方法，将日期对象格式定义为指定格式
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {
//		return null;
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (date != null) {
			result = sdf.format(date);
		}
		return result;
	}

	/**
	 * 重载，将日期字符串转化为指定格式的日期对象
	 * @param dateStr 日期字符串
	 * @param format 格式
	 * @return
	 */
	public static Date formatDate(String dateStr, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(dateStr);
	}

	public static void main(String[] args) throws ParseException {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat();
		System.out.println(sdf.format(date));
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf2.format(date));

		System.out.println(formatDate(date, "yyyy-MM-dd HH:mm:ss"));
		System.out.println(formatDate(date, "yyyy年MM月dd日 HH:mm:ss"));
		System.out.println(formatDate(date, "yyyy年MM月dd日"));

		String dateStr = "2016年06月05日 15:11:30";
		System.out.println(formatDate(dateStr, "yyyy年MM月dd日 HH:mm:ss"));
		String dateStr2 = "2016-06-05 15:11:30";
		System.out.println(formatDate(dateStr2, "yyyy-MM-dd HH:mm:ss"));
	}
}
