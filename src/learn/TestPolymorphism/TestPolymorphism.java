package learn.TestPolymorphism;

/**
 * Created by sure GM on 2016/5/21 19:32.
 * 理解多态
 */
public class TestPolymorphism {
	public static void main(String [] args) {
		/*	Dog dog = new Dog();
		dog.say();
		Cat cat = new Cat();
		cat.say();
		*/
		Animal animal = new Dog();		//父类引用指向的是子类对象
		animal.say();
		animal = new Cat();		//多态的体现
		animal.say();
		Person p = new Person();
		p.eat(new Dog());		//多态
		p.eat(new Cat());
		p.eat(new Rabbit());
		p.eat(new aaa());
	}
}

//要实现多态，必须要满足下面一些情况
//要有继承
//要有方法的重写
//要有父类引用指向子类对象

//定义一个人类
class Person {
	//这个人养了一条狗和一只猫
	Dog dog;
	Cat cat;
	/*
	public void eat(Dog dog) {
		System.out.println("喂狗");
		dog.eat();
	}

	public void eat(Cat cat) {
		System.out.println("喂猫");
		cat.eat();
	}

	public void eat(Rabbit rabbit) {
		System.out.println("喂兔子");
		rabbit.eat();
	}
	*/
	/*	public void eat(aaa a) {

	}
	*/

	public void eat(Animal animal) {
		System.out.println("喂动物");
		animal.eat();
	}

}

class Animal {
	public void say() {
		System.out.println("动物叫");
	}

	public void eat() {
		System.out.println("动物吃东西");
	}
}

class Dog extends Animal {
	public void say() {
		System.out.println("汪汪汪汪。。。。");
	}

	public void eat() {
		System.out.println("狗吃肉");
	}
}

class Cat extends Animal {
	public void say() {
		System.out.println("喵喵喵喵。。。。");
	}

	public void eat() {
		System.out.println("猫吃鱼");
	}
}

class Rabbit extends Animal {
	public void say() {
		System.out.println("兔兔兔兔");
	}

	public void eat() {
		System.out.println("兔子吃胡萝卜");
	}
}

//太繁琐
class aaa extends Animal {
	public void say() {

	}

	public void eat() {
		System.out.println("爱吃啥吃啥");
	}
}
