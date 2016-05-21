package learn.TestModifiers;

/**
 * Created by sure GM on 2016/5/21 19:00.
 */
class E {
	private int i;
	private static int cnt = 0;	//private保证只有类内部可访问修改cnt

	public E() {	//public 外部访问
		++cnt;
	}

	public E(int i) {
		this.i = i;
		++cnt;
	}

	public static int getCnt() {
		//return返回的是E对象的个数，public保证外部能访问，static共用访问
		return cnt;
	}
}

public class TestStatic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.printf("当前时刻A对象的个数是：%d\n", E.getCnt());
		E ee1 = new E();
		System.out.printf("当前时刻A对象的个数是：%d\n", E.getCnt());
		E ee2 = new E(5);
		System.out.printf("当前时刻A对象的个数是：%d\n", E.getCnt());
	}

}
