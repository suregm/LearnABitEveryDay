package learn.TestExtends;

/**
 * Created by sure GM on 2016/5/21 19:47.
 */
public class TestExtends {
	public static void main(String [] args) {
		Dog dog = new Dog();
		dog.name = "小白";
		System.out.println(dog.name);
		dog.col = "白色";
		System.out.println(dog.col);
		dog.say();
		//dog.age = 2;		//私有的不能被继承
		//dog.a();				//私有的属性和方法都不能被子类继承
		Cat cat = new Cat();
		cat.name = "小黄";
		System.out.println(cat.name);
		cat.col = "黄色";
		System.out.println(cat.col);
		cat.say();
	}
}

//在继承中还有一个非常重要的概念————覆盖（重写）；体现在两个类中
//前面讲过一个重载（overload）；在一个类中，方法名相同，但是参数不同
//在Java中只支持单继承，一个类只能有一个父类，如果有需求需要继承多个类，那么Java中提供了另外一种机制接口。

//在继承中，还有一个有意思的东西，多态性
//重写也是多态性的一种体现，重载也可以体现多态

//定义一个动物类
class Animal {
	//名字
	String name = "小黑";
	//颜色
	String col;

	//有些属性和方法，我不想被子类继承，那么我就可以声明为是私有的
	//年龄
	private int age;

	private void a() {

	}

	public void say() {
		System.out.println("动物叫");
	}
}

//定义一个狗类
class Dog extends Animal {
		/*//狗的名字
		String name;
		//狗毛发的颜色
		String col;
		*/

	//name覆盖了父类中的name
	String name;

	//重写（覆盖），就是覆盖了父类中的say方法
	//如果我想调用父类中的say方法，该如何调用
	//如果父类中的方法不能满足我们的要求，我们就可以重写它
	//但是重写的要求是，名字、返回值和参数列表必须是一样的
	public int say(int a) {
		System.out.println(super.name);
		super.say();
		System.out.println("汪汪汪汪...");
		return 0;
	}
}

//定义一个猫类
class Cat extends Animal {
		//名字
		String name;
		//猫毛发的颜色
		String col;

	public void say() {
		System.out.println("猫叫");
	}
}

//定义一个鱼类
class Fish extends Animal {
 	//名字
	String name;
	//鱼身的颜色
	String col;
}
