package bitBeat.ProducerAndConsumer;

/**
 * Created by sure GM on 2016/5/21 18:26.
 */
public class Share {
	private int content;
	private boolean flag = false;	//表示没有生产数据

	//消费者取得数据
	synchronized public int get() {		//使用wait方法调用时必须synchronized锁定
		//做一个判断，当有数据的时候才消费，没有数据就一直等待
		while(!flag) {
			//等待该怎么实现？？？
			try {
				wait();		//使用wait方法调用时必须synchronized锁定
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		flag = false;
		System.out.println("消费者取得的数据为：" + content);
		notify();	//唤醒其他在等待的线程
		return content;
	}

	//生产者生产数据
	synchronized public void set(int n) {
		//数据没有被消费掉，就需要等待
		while(flag) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		flag = true;
		this.content = n;
		System.out.println("生产者输出的数据为：" + content);
		notify();	//唤醒其他在等待的线程
	}
}
