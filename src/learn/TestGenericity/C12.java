package learn.TestGenericity;

/**
 * Created by sure GM on 2016/6/5 16:14.
 */
public class C12 {

	// Object是所有类的父类
	private Object object;

	public C12(Object object) {
		super();
		this.object = object;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	void print() {
		System.out.println("object类型是：" + object.getClass().getName());
	}
}
