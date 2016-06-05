package learn.TestGenericity;

/**
 * Created by sure GM on 2016/6/5 16:03.
 */
public class C2 {
	private String a;

	public String getA() {
		return a;
	}

	public C2(String a) {
		this.a = a;
	}

	public void setA(String a) {
		this.a = a;
	}

	void print() {
		System.out.println("a类型是：" + a.getClass().getName());
	}
}
