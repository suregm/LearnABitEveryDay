package learn.TestString;

/**
 * Created by sure GM on 2016/5/21 18:58.
 */
public class TestString2 {
	public static void main(String[] args)
	{
		String str1 = new String("china");
		String str2 = new String("china");
		System.out.println(str1.equals(str2));	// str1.equals(str2) 对于字符串，则用来判断指向的对象内容是否相同

		if(str1 == str2)	//判断str1和str2自身的内存地址是否相等，还是用来判断str1与str2指向对象的内容相等？？？
			// ==指向前者，内存地址不相同
			System.out.println("str1 == str2");
		else
			System.out.println("str1 != str2");

		String str3 = "china";
		String str4 = "china";
		System.out.println(str3.equals(str4));	//用来判断指向的对象内容是否相同

		if(str3 == str4)	//静态变量，字符串常量在公用的data segment区域存放，故相同
			System.out.println("str3 == str4");
		else
			System.out.println("str3 != str4");
	}
}
