package bitBeat.QCC;

import java.text.SimpleDateFormat;

/**
 * Created by sure GM on 2017/3/22 23:54.
 */
public class Date {

	public static String time2Millis(String time) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");

		long millis = sdf.parse(str).getTime();
		System.out.println(millis);

		return millis + "";
	}

}
