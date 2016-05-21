package learn.TestModifiers;

import java.io.File;

/**
 * Created by sure GM on 2016/5/21 19:44.
 */

//测试Java中的访问修饰符
//跨包访问类，这个类必须是公共类
//类的修饰符，只能是public或者是默认的
//在同一个Java文件中，只能有一个公共类
//内部类可以使用private关键字来修饰

public class Modifier {
	public static void main(String [] args) {
		//AAA a = new AAA();		//错误
		//sure.gm.AAA a = new sure.gm.AAA();		//错误
		//sure.gm.demo5 d = new sure.gm.demo5();
		Modifier d = new Modifier();
		File f;
	}
}