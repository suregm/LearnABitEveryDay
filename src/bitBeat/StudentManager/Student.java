package bitBeat.StudentManager;

/**
 * Created by sure GM on 2016/5/21 18:33.
 */
public class Student implements Comparable {
	private int id;
	private String name;
	private int score;

	//source >> Generate Constructor using Fields
	public Student(int id, String name, int score) {
		this.id = id;
		this.name = name;
		this.score = score;
	}

	//source >> Generate Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	//source >> override/implement methods >> toString勾上
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "学号：" + id +"，姓名：" + name + "，分数：" + score;
	}

	//该方法就是定义我们的排序规则的
	//函数的返回值可以是正数、负数和0
	@Override
	public int compareTo(Object o) {
		//对Object o进行强制转型
		Student stu = (Student)o;
		//按升序排列
		if(this.score > stu.getScore()) {
			return 1;
		} else if(this.score < stu.getScore()) {
			return -1;
		}
		return 0;
	}
}
