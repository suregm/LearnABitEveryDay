package learn.TestAbstract;

/**
 * Created by sure GM on 2016/5/21 19:12.
 */

//有抽象方法的类一定是抽象类
abstract class K {
	abstract public void f();	//没有方法体的方法叫抽象方法，抽象方法要求末尾必须加分号，前面必须有abstract
}

//抽象类不一定有抽象方法
abstract class O {
	public void g() {

	}
}

public class TestAbstract {
	public static void main(String[] args) {

	}
}
