package learn.TestException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by sure GM on 2016/5/21 16:50.
 */
public class TestException {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readFile();
		try {
			abc();		//要在调用处显示捕获异常
		}	catch (MyException e) {
			e.printStackTrace();
		}
	}

	public static void readFile() {
		File file = new File("D:\\txt.txt");
		try {
			FileInputStream input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			System.out.println("执行了finally语句块");
		}
	}

	//如果异常我不想在本方法中捕获
	//throw和throws的区别
	//throw用于方法体中，而throws是对外抛出异常，使用在方法的定义处
	//如果方法使用了throws抛出异常，那么调用该方法的地方就需要显示捕获的该异常
	public static void abc() throws MyException {
		//自定义的异常类，需要使用throw关键字来抛出
		if(1 != 0) {

		}	else {
			throw new MyException("发生异常", 1);
		}
	}

}

//自定义的异常类
class MyException extends Exception {

	private int id;

	public MyException(String msg, int id) {
		System.out.println(msg + " :id");
	}

}
