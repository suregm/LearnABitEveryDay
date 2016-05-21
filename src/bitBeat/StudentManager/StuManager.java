package bitBeat.StudentManager;

import java.util.*;

/**
 * Created by sure GM on 2016/5/21 18:34.
 */
public class StuManager {
	//java中数组也是一种引用类型，那么他就必须同new关键字来给它分配空间
	//用数组来完成这个学生成绩管理系统，如果人数超过数组大小则存放不下
	//java中提供了一种集合，可以解决存储大小溢出的问题
	private Student [] stus = new Student[100];
	//private Student [] stu1 = {};	//这种方法赋初值也可以


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//语法：父类引用指向子类对象
		//Collection允许元素重复，并且是按照加入的先后顺序排列
		Collection col = new ArrayList();	//快速插入包ctrl+shift+O
		Student stu1 = new Student(1,"张三",90);
		Student stu2 = new Student(2,"李四",80);
		Student stu3 = new Student(3,"王五",70);
		Student stu4 = new Student(4,"赵六",60);
		col.add(stu1);
		col.add(stu2);
		col.add(stu3);
		col.add(stu4);
		col.add(stu3);
		System.out.println(col);

		/*System.out.println(col.contains(stu1));
		System.out.println(col.isEmpty());
		System.out.println(col.remove(stu1));
		System.out.println(col.remove(new Student(3,"www",70)));*/

		/*
		//集合中元素的遍历
		for(Iterator i = col.iterator();i.hasNext();) {
			Student stu = (Student)i.next();
			System.out.println(stu);	//Student类里面source>override/implement methods
		}
		*/

		//不允许元素重复，并且按照自己内部的排序规则安排元素
		Set set = new HashSet();
		set.add(stu1);
		set.add(stu2);
		set.add(stu3);
		set.add(stu4);
		set.add(stu1);
		System.out.println(set);

		List list = new LinkedList();
		list.add(stu1);
		list.add(stu2);
		list.add(stu3);
		list.add(stu4);
		list.add(stu4);
		list.add(0,stu4);
		System.out.println(list);
		System.out.println(list.get(2));

		//Map中的键不能重复，重复则会覆盖前面的元素
		Map map = new HashMap();
		map.put("name", "张三");
		map.put("name", "李四");
		map.put("age", 34);
		//map.remove("age");
		System.out.println(map);
		System.out.println(map.get("name"));
		System.out.println(map.get("age"));
	}

	private ArrayList list = null;

	public StuManager() {
		list = new ArrayList();
	}

	//增加一个学生的信息
	//添加成功则返回true，否则返回false
	public boolean add() {
		Scanner sc = new Scanner(System.in);
		int id;
		String name;
		int score;
		id = sc.nextInt();
		name = sc.next();
		score = sc.nextInt();
		return list.add(new Student(id, name, score));
	}

	//查找指定学号的学生信息
	public boolean find(int id) {
		for(Iterator i=list.iterator(); i.hasNext();) {		//类型不匹配：不能从 Iterator 转换为 HTMLDocument.Iterator。type mismatch: cannot convert from iterator to htmldocument.iterator. 删除导入包import javax.swing.text.html.HTMLDocument.Iterator即可
			Student stu = (Student)i.next();
			if(stu.getId() == id) {
				System.out.println(stu);
				return true;
			}
		}
		System.out.println("没有学号为：" + id + "的学生存在");
		return false;
	}

	/**
	 * 显示所有学生的信息
	 */
	public void display() {
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	public boolean delete(int id) {
		for(int i=0; i<list.size(); i++) {
			Student stu = (Student)list.get(i);
			if(stu.getId() == id) {
				list.remove(i);
				//list.remove(stu);
				System.out.println(stu + "删除成功");
				return true;
			}
		}
		System.out.println("没有找到学号为：" + id + "的学生");
		return false;
	}

	/**
	 * 根据学号修改学生的成绩
	 * @param id
	 * @param score
	 * @return
	 */
	public boolean update(int id, int score) {
		for(int i=0; i<list.size(); i++) {
			Student stu = (Student)list.get(i);
			if(stu.getId() == id) {
				stu.setScore(score);
				System.out.println("修改成功");
				return true;
			}
		}
		System.out.println("没有找到学号为：" + id + "的学生");
		return false;
	}

	public void sort() {
		Collections.sort(list);
	}
}
