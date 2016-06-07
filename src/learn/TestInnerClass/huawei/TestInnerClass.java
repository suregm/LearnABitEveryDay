package learn.TestInnerClass.huawei;

/**
 * Created by sure GM on 2016/6/7 23:30.
 */
public class TestInnerClass {

	public static void main(String[] args) {

		// 第一次调用
		FunctionClass fc = new FunctionClass(){
			@Override
			public void action() {
				System.out.println("I am actionStep1");
			}

			@Override
			public boolean verify() {
				boolean verify = String.valueOf("Learn A Bit Everyday".length()).equals("Life Long");
				return verify;
			}

		};
		fc.actionStrength();

		// 第二次调用
		new FunctionClass(){
			@Override
			public void action() {
				System.out.println("I am actionStep1");
				System.out.println("I am actionStep2");
			}

			@Override
			public boolean verify() {
				boolean verify = !(5.27 > 12.02);
				return verify;
			}
		}.actionStrength();

	}
}
