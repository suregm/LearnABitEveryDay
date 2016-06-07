package learn.TestGenericity.GenericityMethod;

/**
 * Created by sure GM on 2016/6/5 17:18.
 */
public class Genericity {

	/**
	 * 泛型方法
	 * @param t
	 * @param <T>
	 */
	public static <T> void f(T t) {
		System.out.println("T的类型是：" + t.getClass().getName());
	}

	public static void main(String[] args) {
	    f("");
		f(1);
		f(1.0);
		f(1.0f);
		f(new Object());
	}
}
