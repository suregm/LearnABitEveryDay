package bitBeat.ProducerAndConsumer;

/**
 * Created by sure GM on 2016/5/21 18:29.
 */
public class Main {
	public static void main(String[] args) {
		Share share = new Share();
		Producer p = new Producer(share);
		Consumer c = new Consumer(share);
		p.start();
		c.start();
	}
}
