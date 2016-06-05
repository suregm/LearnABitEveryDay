package learn.TestGenericity;

/**
 * Created by sure GM on 2016/6/5 16:04.
 */
public class TestC12 {

	public static void main(String[] args) {
		// begin test c1
	    C1 c1 = new C1(1);
		System.out.println(c1);
		c1.print();
		int i = c1.getA();
		System.out.println("i = " + i);
		// end test c1

		// begin test c2
		C2 c2 = new C2("hello");
		c2.print();
		String s = c2.getA();
		System.out.println("s = " + s);
		// end test c2



		// 使用一个类object时，使用方法比较简洁（直接调用print()），但是获取属性的时候麻烦（需要转型），因此引入泛型
		// begin test c12
		C12 c12 = new C12(1);   // 向上转型
		c12.print();
		int i12 = (Integer) c12.getObject();  // 向下转型
		System.out.println("i12 = " + i12);
		// end test c12

		// begin test c12
		C12 c12_2 = new C12("你好");   // 向上转型
		c12_2.print();
		String s12 = (String) c12_2.getObject();  // 向下转型
		System.out.println("s12 = " + s12);
		// end test c12



		// 验证泛型
		// begin test CC
		CC<Integer> cc = new CC<Integer>(1);
		cc.print();
		int icc = cc.getOb();
		System.out.println("icc = " + icc);
		// end test CC

		// 验证泛型
		// begin test CC
		CC<String> cc2 = new CC<String>("泛型好简单");
		cc2.print();
		String scc = cc2.getOb();
		System.out.println("scc = " + scc);
		// end test CC

	}
}
