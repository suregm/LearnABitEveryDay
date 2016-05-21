package learn;

/**
 * Created by sure GM on 2016/5/21 19:14.
 */
abstract class P {
	abstract public void f();
}

//abstract class Q extends P {	//抽象类继承class P正确
class Q extends P {
	public void f() {	//class P中已经实现，故无需加abstract
		System.out.printf("QQQQ\n");
	}
}

public class TestAbsPoly {
	public static void main(String[] args) {
		//P pp = new P();		//error 抽象的对象
		Q qq = new Q();
		qq.f();

		P pp;	//ok 可以定义一个抽象类的引用，但是不能定义一个抽象类的对象，故16行错误
		pp = qq;	//抽象的父类引用指向子类的对象
		pp.f();	//多态，调用子类的方法
	}
}
