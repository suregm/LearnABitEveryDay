package learn.TestThread;

/**
 * Created by sure GM on 2015/8/18.
 */
public class TestTickets2 {
	public static void main(String[] args) {
		/*//错误的写法
		B bb1 = new B();
		Thread t1 = new Thread(bb1);
		t1.start();

		B bb2 = new B();
		Thread t2 = new Thread(bb2);
		t2.start();
		*/

		B bb = new B();
		Thread t1 = new Thread(bb);
		t1.start();
		Thread t2 = new Thread(bb);
		t2.start();
	}
}

class B implements Runnable {
	public static int tickets = 100;  //static不能省
	//不加static，则将导致每张票被卖两次
	public static String str = new String("哈哈");  //不能写在run()方法里面，否则锁定的不是同一个str对象，导致第100张票出现两次

	public void run() { //void 前面不能加synchronized 否则会导致只有一个售票点在卖票，synchronized必须放在while()的下级
		while (true) {
			synchronized (str) {
				if (tickets > 0) {
					System.out.printf("%s线程正在卖出第%d张票\n", Thread.currentThread().getName(), tickets);
					--tickets;
				} else {
					break;
				}
			}
		}
	}
}