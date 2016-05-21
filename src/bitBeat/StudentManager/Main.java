package bitBeat.StudentManager;

/**
 * Created by sure GM on 2016/5/21 18:35.
 */
public class Main {
	public static void main(String[] args) {
		/*
		//LinkedList集合，在底层是使用链表实现的
		//Vector
		LinkedList list = new LinkedList();
		list.addFirst(null);	//在头部增加一个元素
		list.addLast(null);	//在尾部增加一个元素
		list.getFirst();	//取头部的元素
		list.getLast();		//取尾部的元素

		Vector v = new Vector();

		ArrayList list1 = new ArrayList();
		list1.add(1);
		list1.add(new Student(1, "222", 56));
		//编译器编译的时候没有显示错误，因为编译器不检查
		//Student stu = (Student)list1.get(0);	//运行报错
		//Student stu = (Student)list1.get(1);	//运行正确

		//泛型的好处，可以避免我们的数据类型转换错误的安全隐患
		ArrayList<Student> list2 =new ArrayList<Student>();
		//在这里编译器就检查加入的对象类型是否正确
		//list2.add(1);		//coding时就提示错误
		list2.add(new Student(1, "aaa", 78));
		Student stud1 = list2.get(0);	//泛型，无需强制转换Student stud1 = (Student)list2.get(0)

		StuManager stu = new StuManager();
		Scanner sc = new Scanner(System.in);
		int type, id;
		while(true) {
			System.out.println("1、增加学生的信息");
			System.out.println("2、查询学生的信息");
			System.out.println("3、显示所有的信息");
			System.out.println("4、修改学生的信息");
			System.out.println("5、删除学生的信息");
			System.out.println("6、成绩排名");
			System.out.println("7、退出");
			//输入一个数字选择一个相应的操作
			type = sc.nextInt();
			switch(type) {
			case 1 :
				stu.add();
				break;
			case 2 :
				id = sc.nextInt();
				stu.find(id);
				break;
			case 3 :
				stu.display();
				break;
			case 4 :
				id = sc.nextInt();
				int score = sc.nextInt();
				stu.update(id, score);
				break;
			case 5 :
				id = sc.nextInt();
				stu.delete(id);
				break;
			case 6 :	//怎么对这个集合进行排序？
				stu.sort();
				break;
			case 7 :
				System.exit(0);
				break;
			}
		}
	}*/
		AAA<Integer> a = new AAA<Integer>(new Integer(2));
		a.type();
		AAA<Student> b = new AAA<Student>(new Student(2, "555", 56));
		b.type();
	}
}

//泛型类，这个类型是不确定的，在我们运行期间根据实际情况来确定
class AAA <T> {
	T t;
	public AAA(T t) {
		this.t = t;
	}

	public void type() {
		System.out.println(t.getClass().getName());
		t.getClass().getClassLoader();	//反射机制
	}
}
