package learn.TestGenericity.GenericityMethod;

/**
 * Created by sure GM on 2016/6/5 17:22.
 * http://www.cnblogs.com/iyangyuan/archive/2013/04/09/3011274.html
 */
public class Generic {

	/**
	 * 定义泛型方法
	 * Class<T>作用是指明泛型T的具体类型
	 * @param c 用来创建泛型T代表的类的对象
	 * @param <T> 声明词方法持有一个类型T，也可以理解为声明此方法为泛型方法
	 * @return T 指明该方法的返回值类型为T
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public <T> T getObject(Class<T> c) throws InstantiationException, IllegalAccessException {
		// 创建泛型对象
		T t = c.newInstance();  // 创建具体对象实例，利用反射创建对象
		return t;
	}

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

	    Generic generic = new Generic();
		// 调用泛型方法
		// 此时obj就是Animal类的具体实例
		// 利用Class.forName()指定泛型的具体类型
		Object obj = generic.getObject(Class.forName("learn.TestGenericity.GenericityRestrict.Animal"));
		System.out.println(obj.getClass().getName());
	}
}
