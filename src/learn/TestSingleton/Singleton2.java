package learn.TestSingleton;

/**
 * Created by sure GM on 2016/6/5 13:08.
 */
public class Singleton2 {

	/**
	 * 构造私有方法
	 */
	private Singleton2() {

	}

	/**
	 * 懒汉式单例实现，在第一次调用的时候实例化
	 */
	private static Singleton2 singleton;

	/**
	 * 工厂
	 * 多线程并发，加synchronized
	 */
	public synchronized static Singleton2 getInstance() {
		if (singleton == null) {
			//第一次调用的时候实例化
			System.out.println("懒汉式只在第一次调用的时候实例化");
			singleton = new Singleton2();
		}
		return singleton;
	}
}
