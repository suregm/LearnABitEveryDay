package learn.TestInterface;

/**
 * Created by sure GM on 2016/5/21 19:06.
 */
interface It {
	public static final int i = 20;		//接口是一种特殊的抽象类，是抽象方法和常量值的结合。
	public abstract void f();	//接口中不能带有主体
}

interface It2 {
	int i = 30;		//省略public static final
	void f();		//省略public abstract
}

interface It3 extends It, It2	//接口允许多继承
{

}

abstract class T implements It2 {	//第10行有抽象方法，所以必须加abstract
	//implements不能改为extends，因为类可以继承类，但不能继承接口，逻辑意义不通，类可以实现接口
	public void f() {
		//i = 99;	//error 无法为final i 赋新值
		System.out.printf("%d\n", i);
	}
}

class U extends T implements It2, It3 {		//一个类可以在继承一个父类的同时，实现一个或多个接口，但extends关键字必须在implements前面
	public void f() {	//接口弥补类的单继承缺陷
		System.out.printf("UUUU\n");
	}

	public void g() {

	}
}

class TestInterface {
	public static void main(String[] args) {
		System.out.printf("嘿嘿\n");
		//It it = new It();		//error 接口，是特殊的抽象类，不能实例化此类型
		int i;
		It2 it2;
		it2 = new U();
		it2.f();
		//it2.g();		//error	通过接口引用只能调用子类从父类继承过来的成员，不能调用子类所特有的
	}
}
