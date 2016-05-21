package learn;

/**
 * Created by sure GM on 2016/5/21 19:16.
 */
public class HelloSure {

	public static synchronized void main(String[] a){
		System.out.print("Hello, ");
		Thread t=new Thread(){
			public void run(){Sure();}
		};
		t.run();
	}

	static synchronized void Sure(){
		System.out.print("Sure!");
	}
}
