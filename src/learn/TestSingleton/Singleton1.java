package learn.TestSingleton;

/**
 * Created by sure GM on 2016/6/5 12:41.
 */
public class Singleton1 {

	/**
	 * 构造私有方法
	 */
	private Singleton1() {

	}

	/**
	 * 饿汉式单例实现
	 */
	private static final Singleton1 singleton1 = new Singleton1();

	/**
	 * 静态工厂方式
	 */
	public static Singleton1 getInstance() {
		System.out.println("饿汉式每次调用都实例化");
		return singleton1;
	}
}
