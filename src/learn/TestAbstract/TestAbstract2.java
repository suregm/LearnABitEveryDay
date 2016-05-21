package learn.TestAbstract;

/**
 * Created by sure GM on 2016/5/21 19:41.
 */
public class TestAbstract2 {
	public static void main(String [] args) {
		Dog dog = new Dog();
		dog.name = "小白";
		dog.col = "白色";
		System.out.println(dog.name);
		System.out.println(dog.col);
		dog.say();
		dog.aa();

		//Animal an = new Animal();		//false
	}
}

//如果一个类中包含抽象方法，那么这个类就必须声明为抽象类
//抽象类中不一定要有抽象方法
//抽象类中可以有属性，并且可以被子类继承
//在抽象类中可以包含已经实现了的方法，并且也可以被子类继承
//抽象类不能被实例化
abstract class Animal {
	//名字
	String name;
	//颜色
	String col;
	//抽象方法
	abstract public void say();

	public void aa() {
		System.out.println("该方法是在抽象中实现的");
	}
}

//继承了抽象类，那么久必须实现它的抽象方法
class Dog extends Animal {
	//实现父类的方法
	public void say() {
		System.out.println("汪汪汪汪。。。");
	}
}
