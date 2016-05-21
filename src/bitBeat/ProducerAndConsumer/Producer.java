package bitBeat.ProducerAndConsumer;

/**
 * Created by sure GM on 2016/5/21 18:26.
 */
public class Producer extends Thread{
	private Share share;

	public Producer(Share share) {
		this.share = share;
	}

	//在run方法中生产数据
	public void run() {
		for(int i=0; i<10; i++) {
			share.set(i);
			try {
				Thread.sleep((int)Math.random()*100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
