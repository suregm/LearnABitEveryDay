package learn;

/**
 * Created by sure GM on 2016/5/21 18:55.
 */
class Human1 {
	private String name;	//private实际编程中定义为protected最好
	private int age;

	public Human1() {

	}

	public Human1(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getInfor() {
		String strInfor = name + ": " + age;
		return strInfor;
	}
}

class Student1 extends Human1 {
	public double score;
	public String school;

	public Student1() {

	}

	public Student1(String name, int age, String school) {
		super(name, age);
		this.school = school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getInfor() {
		//String strInfor = name + ": " + age + ": " + school;	//name, age 是私有的
		String strInfor = super.getInfor() + ": " + school;
		return strInfor;
	}
}

public class TestSetGet {
	public static void main(String[] args) {
		Student1 stu1 = new Student1("sure GM", 18, "郧阳中学");
		System.out.println(stu1.getInfor());
	}
}
