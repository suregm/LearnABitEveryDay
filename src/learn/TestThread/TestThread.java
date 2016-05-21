package learn.TestThread;

/**
 * Created by sure GM on 2016/5/21 18:17.
 */
public class TestThread {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread thread = new MyThread();
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
		//实现接口线程的启动方法
		MyThread2 thread2 = new MyThread2();
		new Thread(thread2).start();
		//主方法也是一个线程
		for(int i=0; i<100; i++) {
			System.out.println(i);
		}

	}

}

class MyThread extends Thread {

	//run方法中的代码就是我们的线程体
	//就是线程启动之后执行的代码
	//如果run方法执行完成，那么整个线程就执行完成了
	public void run() {
		for(int i=0; i<100; i++) {
			System.out.println("实现的Thread类");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class MyThread2 implements Runnable {
	public void run() {
		for(int i=0; i<100; i++) {
			System.out.println("实现的Runnable接口");
		}
	}
}
