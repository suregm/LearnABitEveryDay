package learn.TestInnerClass.huawei;

/**
 * Created by sure GM on 2016/6/7 23:24.
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

	public abstract void action();  //抽象方法，包含语句快
	public abstract boolean verify();
}
