package learn.TestException;

/**
 * Created by sure GM on 2016/5/21 18:50.
 */
class DivisorIsZeroException extends Exception {
	public DivisorIsZeroException(String errorMessage) {
		super(errorMessage);
	}
}

class Z {
	int divide(int a, int b) throws DivisorIsZeroException {
		try {
			if(0 == b) {
				throw new DivisorIsZeroException("除数不能为零！");
			}
		} catch (DivisorIsZeroException e) {
			e.printStackTrace();
		}

		if(0==b) {
			throw new DivisorIsZeroException("除数不能为零！");
		}
		int m = a / b;
		return m;
	}}

public class TestThrowable {

	public static void main(String[] args) throws DivisorIsZeroException {
		// TODO Auto-generated method stub
		Z zz = new Z();
		System.out.printf("%d\n", zz.divide(6, 2));
	}

}
