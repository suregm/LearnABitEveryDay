package learn.TestModifiers;

/**
 * Created by sure GM on 2016/5/21 19:55.
 */

//测试static关键字
public class TestStatic3 {
	static int i = 0;
	static {
		i++;
	}

	public TestStatic3() {
		i++;
	}

	public static void main(String [] args) {
		System.out.println(i);  // 1
		TestStatic3 d1 = new TestStatic3();
		System.out.println(i);  // 2
		TestStatic3 d2 = new TestStatic3();
		System.out.println(i);  // 3
	}
}
