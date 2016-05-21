package learn.TestModifiers;

/**
 * Created by sure GM on 2016/5/21 19:01.
 */
class F {
	public int i = 20;
	private static F ff = new F();	//属性aa可以是一个对象，12行static return调用，静态的不能访问非静态的，故此处也必须是静态的
	//类内部可以new对象
	//链表，一个指针指向它本身

	private F() {	//private则不能new F()

	}

	public static F getF() {	//static一定不能省略，公开的接口或方法，调用100此getF()只得到一个ff
		return ff;	//如果非静态，则A需要对象去调用，而外部无法建对象，不能调用getF()方法，故事static。
	}
}

public class TestStatic2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//F ff1 = new F();	//private故调用出错
		//F ff2 = new F();
		F ff1 = F.getF();
		F ff2 = F.getF();

		ff1.i = 99;
		System.out.printf("%d\n", ff2.i);	//i是public，但限制后只能创建一个对象

		if(ff1 == ff2) {
			System.out.printf("ff1 = ff2\n");
		} else {
			System.out.printf("ff1,ff2不相等\n");
		}
	}

}
