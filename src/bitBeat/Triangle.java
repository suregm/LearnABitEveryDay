package bitBeat;

/**
 * Created by sure GM on 2016/5/21 18:46.
 */
public class Triangle {
	int a, b, c;

	int zhouchang() {
		return a + b + c;
	}

	void set(int i, int j, int k) {
		a = i;
		b = j;
		c = k;
	}

	double area() {
		double p = 1.0*(a+b+c)/2;
		return Math.sqrt(p*(p-a)*(p-b)*(p-c));
	}

	public static void main(String[] args) {
		Triangle t = new Triangle();
//		t.a = 3;
//		t.b = 4;
//		t.c = 5;
		t.set(3, 4, 5);
		System.out.printf("%d %f\n", t.zhouchang(), t.area());
	}
}
