package learn.TestThread;

/**
 * Created by sure GM on 2015/8/18.
 */
public class TestTickets {
	public static void main(String[] args) {
		A aa1 = new A();
		aa1.start();

		A aa2 = new A();
		aa2.start();
	}
}

class A extends Thread {
	public static int tickets = 100;  //static不能省
	//不加static，则将导致每张票被卖两次
	public static String str = new String("哈哈");  //static不能省

	public void run() {
		while (true) {
			synchronized (str) {
				if (tickets > 0) {
					System.out.printf("%s线程正在卖出第%d张票\n",
							Thread.currentThread().getName(), tickets);
					--tickets;
				} else {
					break;
				}
			}
		}
	}
}