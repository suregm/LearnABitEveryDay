package bitBeat.ProducerAndConsumer;

/**
 * Created by sure GM on 2016/5/21 18:28.
 */
public class Consumer extends Thread {
	private Share share;

	public Consumer(Share share) {
		this.share = share;
	}

	//在run方法中消费生产者产生的数据
	public void run() {
		for(int i=0; i<10; i++) {
			share.get();
			try {
				Thread.sleep((int)Math.random()*100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
