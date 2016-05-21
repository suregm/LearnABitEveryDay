package learn;

/**
 * Created by sure GM on 2016/5/21 19:05.
 */
class A {
	int i;
	int j;
}

public class TestMemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A aa = new A();
		//堆内存、栈内存，都是同一个物理硬件上的内存模拟出来的
		//new A(); 在堆中动态分配一块区域，被当做了A对象
		//aa本身的内存是在栈中分配的
		//堆中内存的地址传递给了aa
		//aa指向堆中的内存，aa代表了堆中的内存
		//aa.i 代表aa这个静态指针变量所指向的动态内存中的A对象中的i成员
		aa.i = 10;
		aa.j = 20;
		System.out.printf("%d %d\n", aa.i, aa.j);
	}

}
