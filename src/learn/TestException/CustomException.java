package learn.TestException;

/**
 * Created by sure GM on 2016/6/5 14:06.
 * 自定义异常，继承自Exception
 */
public class CustomException extends Exception {

	public CustomException(String message) {
		super(message);
	}
}
