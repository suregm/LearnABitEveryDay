package learn.TestGenericity;

/**
 * Created by sure GM on 2016/6/5 15:59.
 */
public class C1 {
	private Integer a;

	public Integer getA() {
		return a;
	}

	public void setA(Integer a) {
		this.a = a;
	}

	public C1(Integer a) {
		super();
		this.a = a;
	}

	void print() {
		System.out.println("a类型是：" + a.getClass().getName());
	}
}
