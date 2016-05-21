package learn.TestThread;

/**
 * Created by sure GM on 2015/8/18.
 */
public class TestTickets4
{
	public static void main(String[] args)
	{
		D dd = new D();
		Thread t1 = new Thread(dd);
		t1.start();

		Thread t2 = new Thread(dd);
		t2.start();
	}
}

class D implements Runnable
{
	public int tickets = 100;
	String str = new String("哈哈");

	public void run()
	{
		while (true)
		{
			synchronized (str)
			{
				if (tickets > 0)
				{
					System.out.printf("%s线程正在卖出第%d张票\n",
							Thread.currentThread().getName(), tickets);
					--tickets;
				}
				else
				{
					break;
				}
			}
		}
	}
}
