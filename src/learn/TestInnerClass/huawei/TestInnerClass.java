package learn.TestInnerClass.huawei;

import java.util.Calendar;

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

		System.out.println();

		// 第二次调用
		new FunctionClass(){
			@Override
			public void action() {
				System.out.println("I am actionStep1");
				System.out.println("I am actionStep2");

				try{
					Thread.currentThread().sleep(3000); // 等待三秒，使得verify()发生变化
				}catch(InterruptedException ie){
					ie.printStackTrace();
				}

			}

			@Override
			public boolean verify() {
//				boolean verify = !(5.27 > 12.02);
				Calendar calendar = Calendar.getInstance();
				int minute = calendar.get(Calendar.SECOND);
				return (minute % 10) > 5;
			}
		}.actionStrength();

	}
}
