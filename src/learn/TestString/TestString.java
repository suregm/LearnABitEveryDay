package learn.TestString;

/**
 * Created by sure GM on 2016/5/21 18:18.
 */
public class TestString {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = new String("suregm");
		String str2 = new String("suregm");
		//str1 == str2其实比较的是两个引用的地址，而不是比较的两个引用中的内容
		System.out.println(str1 == str2);		//错误
		System.out.println(str1.equals(str2));
		/*
		StringBuffer buffer = new StringBuffer();
		buffer.append(1);
		buffer.append(true);
		buffer.append("suregm");
		System.out.println(buffer);
		*/

		Person p1 = new Person("suregm",1);
		Person p2 = new Person("suregm",1);
		System.out.println(p1.equals(p2));
		System.out.println(p1.toString());

	}

}

//我要比较两个自定义类对象是否相同（即他们的内容是否相等）
class Person extends Object {
	String name;
	int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public boolean equals(Object o) {
		Person p = (Person) o;
		if(this.name.equals(p.name) && this.age == age)
			return true;
		return false;
	}

	public String toString() {
		return "我的名字是" + name + "，今年" + age + "岁了。";
	}
}
