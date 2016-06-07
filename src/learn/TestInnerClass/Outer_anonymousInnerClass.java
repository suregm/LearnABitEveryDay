package learn.TestInnerClass;

/**
 * Created by sure GM on 2016/6/5 18:43.
 * 匿名内部类
 * 匿名内部类也就是没有名字的内部类，正因为没有名字，所以匿名内部类只能使用一次，它通常用来简化代码编写
 * 使用匿名内部类还有个前提条件：必须继承一个父类或实现一个接口
 * http://www.cnblogs.com/nerxious/archive/2013/01/25/2876489.html
 */
public class Outer_anonymousInnerClass {

}

//实例1:不使用匿名内部类来实现抽象方法
/*abstract class Person {
	public abstract void eat();
}

class Child extends Person {
	public void eat() {
		System.out.println("eat something");
	}
}

public class Demo {
	public static void main(String[] args) {
		Person p = new Child();
		p.eat();
	}
}*/

//实例2：匿名内部类的基本实现
/*
abstract class Person {
	public abstract void eat();
}

public class Demo {
	public static void main(String[] args) {
		Person p = new Person() {
			public void eat() {
				System.out.println("eat something");
			}
		};
		p.eat();
	}
}*/

//实例3：在接口上使用匿名内部类
/*
interface Person {
	public void eat();
}

public class Demo {
	public static void main(String[] args) {
		Person p = new Person() {
			public void eat() {
				System.out.println("eat something");
			}
		};
		p.eat();
	}
}*/
//由上面的例子可以看出，只要一个类是抽象的或是一个接口，那么其子类中的方法都可以使用匿名内部类来实现
//最常用的情况就是在多线程的实现上，因为要实现多线程必须继承Thread类或是继承Runnable接口

//实例4：Thread类的匿名内部类实现
/*
public class Demo {
	public static void main(String[] args) {
		Thread t = new Thread() {
			public void run() {
				for (int i = 1; i <= 5; i++) {
					System.out.print(i + " ");
				}
			}
		};
		t.start();
	}
}*/

//实例5：Runnable接口的匿名内部类实现
/*
public class Demo {
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			public void run() {
				for (int i = 1; i <= 5; i++) {
					System.out.print(i + " ");
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
	}
}*/

class Demo {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				for (int i = 1; i <= 5; i++) {
					System.out.print(i + " ");
				}
			}
		}).start();
	}
}

class TestThread {

	public static void main(String[] args) {
		// 此处的new Runnable匿名类可以使用lambda表达式代替
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("我是一个实现了Runnable接口的匿名内部类 并将我作为一个参数传给了Thread的构造函数");
			}
		})
		{
			@Override
			public void run() {
				System.out.println("我是一个继承了Thread接口的匿名内部类");
			}
		}.start(); //我是thread 的匿名对象
	}
}