package learn;

/**
 * Created by sure GM on 2016/5/21 18:51.
 */
class G {
	public int i;
	public int j;

	public G() {

	}

	public G(int i, int j) {
		this.i = i;
		this.j = j;
	}
}

class H extends G {
	public int k;

	public H() {

	}

	public H(int i, int j, int k) {
		// this.i = i; //this 此处i前面可以加this，继承后已经成为H的一个属性
		// this.j = j;	//这种方法可行
		super(i, j);	//第二种方法使用super()
		//G(i, j);	//error
		this.k = k;
	}
}

public class TestSuper {
	public static void main(String[] args) {
		H hh = new H(22, 1, 2);
		System.out.printf("%d %d %d", hh.i, hh.j, hh.k);
	}
}
