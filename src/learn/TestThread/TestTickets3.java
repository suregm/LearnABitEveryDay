package learn.TestThread;

/**
 * Created by sure GM on 2015/8/18.
 */
public class TestTickets3
{
	public static void main(String[] args)
	{
		C cc = new C();
		Thread t1 = new Thread(cc);
		t1.start();

		Thread t2 = new Thread(cc);
		t2.start();

	}
}

class C implements Runnable
{
	public static int tickets = 100;
	public String str = new String("哈哈");

	public void run()
	{
		String str = "哈哈";  //可以定义一个局部变量，该局部变量的名字和属性名相同
		//不推荐这种写法
		while (true)
		{
			synchronized (str)  ////锁定的不是同一个str对象  此时的str是局部变量str，即是11行定义的str
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