package learn;

/**
 * Created by sure GM on 2016/5/21 19:58.
 */
public class Object_Oriented {
	public static void main(String [] args) {
		//封装性
		Person p = new Person();		//构造方法 malloc(sizeof(int))
		p.setName("张三");
		System.out.println(p.getName());
		p.hello();
		p.say();
		//静态属性访问的两种方式
		p.address = "湖南长沙";		//通过对象名访问
		System.out.println(p.address);
		Person.address = "湖南长沙雨花区";		//通过类名访问
		System.out.println(Person.address);

		Person p2 = new Person("sure",25,0,"游泳");
		p2.hello();
		p2.say();
		p2.hello("你好，见到你很高兴！");
	}
}

//定义了一个人类
class Person {
	public Person() {
		System.out.println("一个对象已经创建");
	}
	//人类中包含了一些属性和方法
	//属性		静态属性		成员变量
	//姓名
	private String name;
	//年龄
	private int age;
	//性别
	private int gender;		//0表示男，1表示女
	//爱好
	private String hobbies;

	//地址
	static String address;

	//对外开放一些接口
	public String getName() {
		return name;
	}

	//this关键字：该类本身的一个对象
	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	//方法的[重载]
	//无参构造方法
	public Person(String name) {
		this.name = name;
	}

	//构造方法
	public Person(String name, int _age, int gender, String hobbies) {
		this.name = name;
		age = _age;
		this.gender = gender;
		this.hobbies = hobbies;
	}

	//方法		动态属性		成员方法
	public void hello() {
		System.out.println("hello");
	}

	public void hello(String str) {
		System.out.println(str);
	}

	//自我介绍
	public void say() {
		System.out.println("你好，我是"+name+"，今年"+age+"岁，我的爱好是"+hobbies);
	}
}
