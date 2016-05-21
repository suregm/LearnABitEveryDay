package learn.TestException;

/**
 * Created by sure GM on 2016/5/21 18:47.
 */

//先catch子类异常再catch父类异常
//如果先catch父类异常再catch子类异常，则编译时会报错

class A2 extends Exception
{
}

class B2 extends A2
{
}

class C2 extends B2
{
}

class M2
{
	public void compare(int i, int j) throws A2, B2
	{
		if (i > j)
			throw new A2();
		else
			throw new B2();
	}
}

public class TestTryCatch
{
	public static void main(String[] args)
	{
		M2 mm = new M2();
		try
		{
			mm.compare(-4, 1);
		}
		catch (B2 bb)	//所有的catch每次只能有一个被执行，若先执行父类A2则包含B2，导致B2异常不会被执行。B2继承于A2，故先catch子类B2异常
		{
			System.out.println("左边不能小于右边");
		}
		//中间不能添加语句，因为是一个整体
		catch (A2 aa)
		{
			System.out.println("左边不能大于右边");
		}
	}
}
