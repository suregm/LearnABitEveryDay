package learn.TestThread;

/**
 * Created by sure GM on 2016/5/21 18:19.
 */
public class TestSyn {
	public static void main(String[] args) {
		Tickets tk1 = new Tickets();
		/*Tickets tk2 = new Tickets();
		Tickets tk3 = new Tickets();*/

		Thread th1 = new Thread(tk1);
		Thread th2 = new Thread(tk1);
		Thread th3 = new Thread(tk1);

		th1.start();
		th2.start();
		th3.start();

		Tickets2 t1 = new Tickets2();
		t1.start();
		Tickets2 t2 = new Tickets2();
		t2.start();
	}

}

class Tickets implements Runnable {
	//这个数据应该是所有的线程都共享的，静态的
	private static int count = 10;
	synchronized public void run() {	//互斥方法2，方法同步
		/*while(true) {
			//卖票的时候需要查询是否还有票可卖
			synchronized(this) {	//同步互斥方法1，代码块同步
				if(count > 0) {
					System.out.println(Thread.currentThread().getName() + "卖出第" + count + "张票");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count --;
				} else {	//没票了，卖票的线程就退出
					break;
				}
			}
		}*/
		for(int i=0; i<10; i++) {
			System.out.println(Thread.currentThread().getName() + "  " + i);
		}
	}
}

class Tickets2 extends Thread {
	public void run() {

	}
}
