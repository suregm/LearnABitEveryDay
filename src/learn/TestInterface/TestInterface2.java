package learn.TestInterface;

/**
 * Created by sure GM on 2016/5/21 19:39.
 */
public class TestInterface2 {
	public static void main(String [] args) {
		//Animal animal = new Animal();
		Dog dog = new Dog();
		System.out.println(dog.name);
		System.out.println(Animal.name);		//接口中的属性可以通过接口名直接访问
		//Animal.name = "小黑";		//接口中的属性是不能被改变的，有一final
		dog.say();
	}
}

//声明了一个动物的接口
//接口也不能被实例化，必须有类来实现它
interface Animal {
	//在接口中定义的属性，必须赋初值
	//在接口中定义的属性，默认并且也只能是public final static类型的
	//名字
	//final：表示不可变的
	//基于这样的性质，我们可以使用接口来定义一些不可变的常量
	public final static String name = "小白";
	//颜色
	String col = "白色";

	//声明方法
	//接口中的方法不能有主体，即不能有实现
	public void say();
}

class Dog implements Animal {
	public void say() {
		System.out.println("汪汪汪汪...");
	}
}
