package learn.TestGenericity;

/**
 * Created by sure GM on 2016/6/5 16:26.
 * 定义泛型类
 */
public class CC<T> {
	// private Object ob;   // 将不知道的类型以T代替
	private T ob;

	public T getOb() {
		return ob;
	}

	public void setOb(T ob) {
		this.ob = ob;
	}

	public CC(T ob) {
		super();
		this.ob = ob;
	}

	void print() {
		System.out.println("T的实际类型是：" + ob.getClass().getName());
	}
}
