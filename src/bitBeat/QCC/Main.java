package bitBeat.QCC;

/**
 * Created by sure GM on 2016/8/4 23:42.
 */
public class Main {

	public static void main(String[] args) {
		System.out.println(Function.contains("学习", Constant.HOBBIES));
		System.out.println(Function.contains("PROGRAMMING", Constant.HOBBIES));

		System.out.println();

		System.out.println(Function.contains_Sure("学习", Constant.HOBBIES));
		System.out.println(Function.contains_Sure("PROGRAMMING", Constant.HOBBIES));
	}
}
