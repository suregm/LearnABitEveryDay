package learn.TestException;

/**
 * Created by sure GM on 2016/6/5 14:08.
 */
public class TestCustomException {

	public static void testCustomException() throws Exception {
		// 自定义一套异常体系
		throw new CustomException("自定义异常");
	}

	public static void main(String[] args) {
		try {
			testCustomException();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
