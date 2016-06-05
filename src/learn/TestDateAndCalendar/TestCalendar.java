package learn.TestDateAndCalendar;

import java.util.Calendar;

/**
 * Created by sure GM on 2016/6/5 14:17.
 */
public class TestCalendar {

	public static void main(String[] args) {

		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.get(Calendar.YEAR));
		System.out.println(calendar.get(Calendar.MONTH) + 1);
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK) - 1);

		System.out.println("现在是："
				+ calendar.get(Calendar.YEAR) + "年"
				+ (calendar.get(Calendar.MONTH) + 1) + "月"
				+ calendar.get(Calendar.DAY_OF_MONTH) + "日"
				+ calendar.get(Calendar.HOUR_OF_DAY) + "时"
				+ calendar.get(Calendar.MINUTE) + "分"
				+ calendar.get(Calendar.SECOND) + "秒");

		switch(calendar.get(Calendar.DAY_OF_WEEK) - 1) {
			case 0:
				System.out.println("今天是：" + "周日");
				break;
			case 1:
				System.out.println("今天是：" + "周一");
				break;
			case 2:
				System.out.println("今天是：" + "周二");
				break;
			case 3:
				System.out.println("今天是：" + "周三");
				break;
			case 4:
				System.out.println("今天是：" + "周四");
				break;
			case 5:
				System.out.println("今天是：" + "周五");
				break;
			case 6:
				System.out.println("今天是：" + "周六");
				break;
			default:
				break;
		}


		Calendar calendar2 = new Calendar() {
			@Override
			protected void computeTime() {

			}

			@Override
			protected void computeFields() {

			}

			@Override
			public void add(int field, int amount) {

			}

			@Override
			public void roll(int field, boolean up) {

			}

			@Override
			public int getMinimum(int field) {
				return 0;
			}

			@Override
			public int getMaximum(int field) {
				return 0;
			}

			@Override
			public int getGreatestMinimum(int field) {
				return 0;
			}

			@Override
			public int getLeastMaximum(int field) {
				return 0;
			}
		};
	}
}
