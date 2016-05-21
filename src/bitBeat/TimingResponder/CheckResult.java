package bitBeat.TimingResponder;

/**
 * Created by sure GM on 2016/5/21 18:22.
 */
public class CheckResult {
	public static boolean check(String str, String result) {
		//字符串的形式是 5 - 6 = ，我们就要取出字符串中的数字
		String data1 = str.substring(0,1);
		System.out.println(data1);
		String operation = str.substring(2,3);
		System.out.println(operation);
		String data2 = str.substring(4,5);
		System.out.println(data2);

		/*int r;
		if(operation == "+") {
			r = Integer.parseInt(data1) + Integer.parseInt(data2);
		} else if(operation == "-") {
			r = Integer.parseInt(data1) - Integer.parseInt(data2);
		} else {
			r = Integer.parseInt(data1) * Integer.parseInt(data2);
		}*/

		int r =Integer.MAX_VALUE;
		if("+".equals(operation)) {
			r = Integer.parseInt(data1) + Integer.parseInt(data2);
		} else if( "-".equals(operation)) {
			r = Integer.parseInt(data1) - Integer.parseInt(data2);
		} else if( "*".equals(operation)) {
			r = Integer.parseInt(data1) * Integer.parseInt(data2);
		}
		System.out.println(r);
		if(Integer.parseInt(result) == r)
			return true;

		return false;
	}
}
