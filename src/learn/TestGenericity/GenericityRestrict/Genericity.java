package learn.TestGenericity.GenericityRestrict;

/**
 * Created by sure GM on 2016/6/5 16:49.
 * T extends Animal 限定了T的类型必须是Animal类或者其子类，此即为泛型限制
 */
public class Genericity<T extends Animal> {

	private T ob;

	public T getOb() {
		return ob;
	}

	public void setOb(T ob) {
		this.ob = ob;
	}

	public Genericity(T ob) {
		super();
		this.ob = ob;
	}

	void print() {
		System.out.println("T的类型是：" + ob.getClass().getName());
	}
}
