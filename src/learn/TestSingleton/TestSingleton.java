package learn.TestSingleton;

/**
 * Created by sure GM on 2016/6/5 12:42.
 * 设计模式 Head First.
 */
public class TestSingleton {

	public static void main(String[] args) {
		Singleton1 singleton1 = Singleton1.getInstance();
		Singleton1 singleton2 = Singleton1.getInstance();
		Singleton1 singleton3 = Singleton1.getInstance();
		System.out.println("饿汉式单例实现：" + (singleton1 == singleton2));

		TestSingleton t = new TestSingleton();
		TestSingleton t2 = new TestSingleton();
		System.out.println();
		System.out.println(t == t2);
		System.out.println();

		Singleton2 singleton4 = Singleton2.getInstance();
		Singleton2 singleton5 = Singleton2.getInstance();
		Singleton2 singleton6 = Singleton2.getInstance();
		System.out.println("懒汉式单例实现：" + (singleton4 == singleton5));

	}
}
