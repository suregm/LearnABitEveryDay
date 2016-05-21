package learn.TestThread;

/**
 * Created by sure GM on 2016/5/21 18:19.
 */
public class TestLock {
	public static void main(String[] args) {

		final Object res1 = "res1";
		final Object res2 = "res2";

		Thread th1 = new Thread() {
			public void run() {
				synchronized(res1) {
					System.out.println("线程1锁定了res1");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					synchronized(res2) {
						System.out.println("线程1锁定了线程2");
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		};

		Thread th2 = new Thread() {
			public void run() {
				synchronized(res2) {
					System.out.println("线程2锁定了res2");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					synchronized(res1) {
						System.out.println("线程2锁定了res1");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
		};

		th1.start();
		th2.start();
	}
}
