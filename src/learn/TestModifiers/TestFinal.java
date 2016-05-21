package learn.TestModifiers;

/**
 * Created by sure GM on 2016/5/21 19:08.
 */
public class TestFinal {
	public static void main(String [] args) {
		Dog dog = new Dog();
		dog.say();
		}
		}

class Animal {
	//final的方法不能被覆盖，但是能被继承
	//如果方法不想被子类覆盖（重写），就可以定义为final的
	final public void say() {
		System.out.println("动物叫");
	}
}

class Dog extends Animal {
	/*public void say() {
		System.out.println("汪汪汪汪。。。。");
	}*/
}

//final的类不能被继承
//如果类不想被继承，就可以定义为final的
final class Animal2 {

}

//final类不能被继承
/*class Dog2 extends Animal2 {

}*/
