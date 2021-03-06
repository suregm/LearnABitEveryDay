package bitBeat.StudyJava;

/**
 * Created by sure GM on 2017/1/11 22:19.
 */
public class BaseOfJava {

	/**
	郝斌版《Java教程》学习笔记
	时间：2015.07.22 - 2015.08.20


	第一部分：基础知识（流程图）

	第二部分：面向对象
        1. 封装
	类（对一类事物抽象所得到的一个概念）
	对象（一个具体的事物）
	构造函数（不能有返回值，方法名与类名相同，可以有多个。
	默认生成的无参无方法体无返回值的构造函数，自己一旦定义，编译器将不再生成默认的构造函数。生成一个类对象，能且只能调用其中的一个构造函数。）
	static
	静态成员属于类本身，非静态（一定有对象）的通过对象来调用。
	非静态的可以访问静态的。
	通过类名只能访问一个类中的非私有静态成员
			私有静态成员不可以通过对象名访问
                this
	非静态方法默认都含有一个this指针
			this代表正在调用本方法的对象
	final
	修饰类，该类不能被继承
	修饰方法，该方法不能被继承，但可以被重写
	修饰属性，表示该属性能且只能被赋一次值，赋值方式有两种，赋值时只能选择一种。
	赋值方法：1. 定义的同时显示的初始化
                                                  2. 构造函数中初始化
			函数重载
	同名不同参（参数的个数、类型、顺序不一而同）
	返回值不能作为是否构成函数重载的依据

        2. 继承
	子类继承父类，子类可以使用父类的属性方法
	注意的问题：
	非私有成员才可以被子类继承
	重写：重写方法必须和被重写方法具有相同的方法名称，参数列表、返回值类型。
	重写方法的访问权限不能小于被重写方法。（多态实现）

			3. 多态
	同一段代码可以随上下文的不同而执行不同的操作，俗称多态。
	即，一个父类的引用可以指向父类的对象也可以指向子类的对象，可以根据指向的不同，自动调用相应的对象。
	注意事项：
	通过父类的引用只能访问子类从父类继承过来的成员
	只有在父类的引用本身指向的是一个子类对象时，才可以将父类的引用强制转化为子类的引用
	class B extends A
                        A aa = new B();
	B bb = new B();
	aa = bb;    //此行不可省略，否则bb = (B)aa; 运行出错
	bb = (B)aa;

	相关知识：
	抽象类：一个抽象类通常都含有抽象方法
			只重写了抽象类部分抽象方法的类也必须被标记为abstract
	不可以定义抽象类对象，但是抽象类可以实现多态

	接口：接口中的方法都是public abstract
	不可以定义接口对象，但接口却可以实现多态
			重写接口方法时public不能省
	e.g. 线程的创建，事件的处理，容器的组织方式，serializable接口

	第三部分：高级部分
        1. 异常
	分类：无法处理的错误
			可以处理的异常
	必须处理的异常，是Exception子类但不是RuntimeException子类
	处理可否的异常，是RuntimeException的子类
	注意问题：
			finally{}一定会执行
	先捕获子类异常，再捕获父类异常，顺序不可颠倒
			重写方法抛出异常的范围不能大于被重写方法抛出异常的范围
	假设f方法抛出了A异常，则f方法有两种方法处理A异常：
			1. throws A
                           2. try{} catch{}

        2. 线程
			一个程序运行时的不同执行路径
	创建线程的方式：继承Thread类，实现Runnable接口，.start();启动
	线程的同步：
	多个线程操作同一资源，并且要求这些操作中的若干个操作不能被中断。
	实现方式：synchronized，可以修饰两种：
	代码块
	方法（默认锁定的是this）
	e.g. 卖票系统
	线程的通信：
	多个线程只有彼此协作才可以完成某个功能，需要线程的通信
	实现方式：wait 和 notify()、notifyAll()
	e.g. 生产和消费

        3. 包
	包的生产与运行：
	package语句必须是第一条语句，类名是包名和类名的组合
			只有在整个包的最上层目录才可以运行
	同包：只有私有的不能访问
	不同包的相互访问：只有public的类被另一个包里面的类访问、继承
                    （两种情况：子类的内部访问，子类对象名访问）
	jar包的生成与使用
	普通jar包的生成 jar -cvf 包名.jar *
	可运行jar包的生成：jar cvfm 包名.jar 1.txt *



	GUI
	容器和组件的关系：容器是组件，但组件不一定是容器。

	常见的布局管理器：
	BorderLayout -- Frame
	FlowLayout -- Panel
			GridLayout

	事件模型：
	要明白编译器自动完成的操作，程序员手动完成的操作。
	程序员只需要做两件事：
	告诉事件源可以产生哪些事件
			设计好可以处理这些事件的时间监听器

	内部类：
	内部类的方法可以访问外部类的所有成员
	外部类的方法不可以直接访问内内部类的任何成员。

	产生的原因：如果一个类A要使用B类的所有成员，并且A类不需要被除B以外的其他类访问，则我们可以把A定义成B的内部类。
	但实际上，几乎不存在外部类需要访问内部类成员的问题。

	匿名类：
	匿名类是内部类的一种极端表现形式
	匿名类可以访问外部类的所有成员，和包裹本匿名类方法中的final类型的局部变量。



	IO流
			完成设备和程序之间数据传输的类

	分类：
	输入流，输出流
	字节流，字符流
	原始流，包裹流

			常用流
	四大基本抽象流：
	InputStream    OutputStream
	Reader Writer
	不能定义对象，是抽象的，要定义对象需使用它们的子类。

	字节流和字符流的区别：
	字节流可以处理所有格式的文件
			字符流只能处理文本格式的文件
	文件流
	FileInputStream FileOutputStream
	FileReader FileWriter
	缓冲流
	BufferedInputStream BufferedOutputStream
	BufferedReader BufferedWriter
	缓冲流可以提高数据传输的速度
			转化流
	OutputStreamWriter    InputStreamReader
	e.g. 编程实现用户的键盘输入读取保存到一个String对象中
			数据流
	DataInputStream    DataOutputStream
	数据流可以把基本类型数据的二进制直接读入或者写出
	e.g. 编程实现把long类型的数据写入byte数组，再从byte数组中将数据读出
	Print流（以上的流关闭会报异常，Print流不报异常）
	PrintStream    PrintWriter
	Print流可以把基本类型数据格式化后的字符串输出
	e.g. 编程实现把键盘输入的数据写入A文件，如果输入有误，则把出错信息写入B文件
			Object流
	ObjectInputStream    ObjectOutputStream
	Object流可以把一个对象直接写入或读出




			容器
	专门用来存放其他类对象的类，叫做容器
	Collection 接口
	Set接口，无序，不允许重复
	实现类：TreeSet HashSet
	List接口，有序，允许重复
	实现类：ArrayList LinkedList
	Map接口
	既保存数据本身，也保存数据的主键的一种接口
	实现类：HashMap TreeMap

	hashCode()和equals()方法
	所有以hash开头的都必须重写hashCode和equals()

	Collections类
	该类提供了对Collection接口实现类的排序、倒置和查找等功能。

	Comparable接口
			通过该接口的方法可以制定出对象之间比较的标准
	凡是需要进行对象比较排序的场合均可以考虑实现该接口

	Iterator接口（迭代器）
	利用该接口提供的方法我们可以遍历所有容器中的元素
	**/

}
