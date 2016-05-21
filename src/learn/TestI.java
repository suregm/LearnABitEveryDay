package learn;

/**
 * Created by sure GM on 2016/5/21 19:07.
 */
class W {
	int i;

	public void show() {
		System.out.printf("show->  %d\n", i);	//i是属性i，此时的i也是局部变量
	}

	public void f() {
		int i;	//这里的i和属性i没有冲突，i只要不使用则可以只定义不初始化，使用时则必须初始化
		//System.out.printf("f->  %d\n", i);	//因为i是局部变量，Java要求使用之前必须初始化
	}

	public void g(int i) {	//形参i，也是局部变量
		this.i = i;
		System.out.printf("f->  %d\n", i);
	}
}

public class TestI {
	public static void main(String[] args) {
		W ww = new W();
		//ww.g(99);
		ww.show();
	}
}
