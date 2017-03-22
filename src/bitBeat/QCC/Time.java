package bitBeat.QCC;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by sure GM on 2017/3/22 23:54.
 */
public class Time {

	/**
	 * 将时间转换为毫秒
	 * @param time
	 * @return
	 */
	public static long time2Millis(String time) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");

		long millis = 0;
		try {
			millis = sdf.parse(time).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(millis);

		return millis;
	}

	/**
	 * 毫秒转换为时间字符串
	 * @param millis
	 */
	public static String millis2Time(long millis) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(millis);


		long now = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(now);


		Date date = new Date(millis);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateStr = sdf.format(date);
		System.out.println(dateStr);
		return dateStr;
	}
}
