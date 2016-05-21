package learn.TestPolymorphism;

/**
 * Created by sure GM on 2016/5/21 19:51.
 */

//重载也体现了Java中的多态性
public class TestOverload {
	public static void main(String [] args) {
		TestOverload demo = new TestOverload();
		System.out.println(demo.getIntMax(1,2));
		System.out.println(demo.getFloatMax(2.9f,4.5f));
		System.out.println(demo.getDoubleMax(3.52,1.4));
	}

	public int getIntMax(int a, int b) {
		return a>b ? a : b;
	}

	public float getFloatMax(float a, float b) {
		return a > b ? a : b;
	}

	public double getDoubleMax(double a, double b) {
		return a > b ? a : b;
	}

	public int getMax(int a, int b) {
		return a>b ? a : b;
	}

	public float getMax(float a, float b) {
		return a > b ? a : b;
	}

	public double getMax(double a, double b) {
		return a > b ? a : b;
	}

	public void aa(int a, float b) {

	}

	public void aa(float a, int b) {

	}

	public void bb() {

	}

	public void bb(int a) {

	}
}
