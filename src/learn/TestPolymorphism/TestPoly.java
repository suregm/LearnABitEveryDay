package learn.TestPolymorphism;

/**
 * Created by sure GM on 2016/5/21 19:03.
 */
class M1 {
	public void f() {
		System.out.printf("MMMM\n");
	}

}

class N1 extends M1 {
	public void f() {
		System.out.printf("NNNN\n");
	}

}

public class TestPoly {
	public static void main(String[] args) {
		M1 mm = new M1();
		N1 nn = new N1();

		mm.f();
		nn.f();

		// nn = mm; //把父类M1赋给子类N1，把mm（动物）当作nn（狗）来看待，显然不对
		mm = nn; // 把nn（狗）当作mm（动物）来看待，正确，子类可以当作父类看待
		mm.f(); // 多态

		mm = nn;	//加上此行，30行的强制转换成立，则可以将子类引用指向父类对象
		nn = (N1)mm;	//这种可以强制转换，因为加了29行，mm已经是指向子类nn对象了
		nn.f();		//输出NNNN
		mm.f();		//输出NNNN，是因为29行的mm = nn，30行的强制转换不改变mm的值
	}
}
//接口发送给形参，则需要父类强制转换为子类，就运用到32-34行的方法。
