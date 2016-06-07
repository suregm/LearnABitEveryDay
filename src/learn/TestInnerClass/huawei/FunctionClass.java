package learn.TestInnerClass.huawei;

/**
 * Created by sure GM on 2016/6/7 23:24.
 * 2016/6/7，华为上班时间恰遇一个类似加固代码的问题（控件多次点击保证操作成功），
 * 上午和朱燕讨论，利用了内部类的抽象类方法得以实现，精简了代码，明晰了逻辑，否则action()语句块重复出现，工作内容重复。
 * 【特别说明】当时还有一种想法，是将语句块作为String参数传进actionStrength()中，但是需要一个将String转化为Java语句执行的过程，难点在此。
 */
public abstract class FunctionClass {

	// public void actionStrength(verify) { //不能将verify传作参数，否则while和if处的Boolean值是相同的，没有变化
	public void actionStrength() {
		action();
		int timer = 3;
		while (timer > 0 && !verify()) {
			action();
			timer--;
			if (verify()) {
				break;
			}
		}
	}

	public abstract void action();  // 抽象方法，包含语句快
	public abstract boolean verify();   // 验证语句也作为抽象方法调用，调用时会时时更新
}
