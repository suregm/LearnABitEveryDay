package learn.TestException;

/**
 * Created by sure GM on 2016/6/5 13:56.
 * 语法错误，运行时错误（异常），语义错误
 */
public class TestRuntimeException {

	/**
	 * 运行时异常，编译时不检查，可以不使用try...catch捕获
	 * @throws RuntimeException
	 */
	public static void testRuntimeException() throws RuntimeException {
		throw new RuntimeException("运行时异常");
	}

	/**
	 * Exception异常，编译时会检查，必须使用try...catch捕获
	 * @throws Exception
	 */
	public static void testException() throws Exception {
		throw new Exception("编译时异常");
	}

	public static void main(String[] args) {
		// 必须使用try...catch捕获
		try {
			testException();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 无需try...catch捕获
		testRuntimeException();
	}
}
