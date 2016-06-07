package learn.TestInnerClass;

/**
 * Created by sure GM on 2016/6/5 18:28.
 * 方法内部类
 * 方法内部类只能在定义该内部类的方法内实例化，不可以在此方法外对其实例化。
 * 方法内部类对象不能使用该内部类所在方法的非final局部变量。
 * 因为方法的局部变量位于栈上，只存在于该方法的生命期内。当一个方法结束，其栈结构被删除，局部变量成为历史。但是该方法结束之后，在方法内创建的内部类对象可能仍然存在于堆中！例如，如果对它的引用被传递到其他某些代码，并存储在一个成员变量内。正因为不能保证局部变量的存活期和方法内部类对象的一样长，所以内部类对象不能使用它们。
 */
public class Outer_methodInnerClass {

	public void doSomething(){
		final int a =10;
		class Inner{
			public void seeOuter(){
				System.out.println(a);
			}
		}
		Inner in = new Inner();
		in.seeOuter();
	}
	public static void main(String[] args) {
		Outer_methodInnerClass out = new Outer_methodInnerClass();
		out.doSomething();
	}
}
