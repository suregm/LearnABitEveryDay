package learn.TestInnerClass;

/**
 * Created by sure GM on 2016/6/5 18:39.
 * 静态嵌套类
 * 从技术上讲，静态嵌套类不属于内部类。因为内部类与外部类共享一种特殊关系，更确切地说是对实例的共享关系。而静态嵌套类则没有上述关系。它只是位置在另一个类的内部，因此也被称为顶级嵌套类。
 */
public class Outer_staticNestedInnerClass {
	static class Inner{}
}

class Test {
	public static void main(String[] args){
		Outer_staticNestedInnerClass.Inner n = new Outer_staticNestedInnerClass.Inner();
	}
}
