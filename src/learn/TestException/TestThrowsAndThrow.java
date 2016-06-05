package learn.TestException;

/**
 * Created by sure GM on 2016/6/5 13:46.
 */
public class TestThrowsAndThrow {

	public static void testThrow(int a) throws Exception {
		if (a == 1) {
			// 直接抛出一个异常类
			throw new Exception("1 存在异常");
		}
		System.out.println(a + " 正常输出");
	}

	public static void main(String[] args) {
		try {
			testThrow(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			testThrow(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
