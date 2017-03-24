package bitBeat.QCC;

/**
 * Created by sure GM on 2016/8/4 23:42.
 */
public class Main {

	public static void main(String[] args) {

		String ss = "123 234  3434     4343";
		System.out.println(ss.replaceAll("\\t+", " ").replaceAll("\\s+", ","));

		System.out.println(Function.contains_GoMX("Sure GM", Constant.NAME));
		System.out.println(Function.contains_GoMX("ure G", Constant.NAME));
		System.out.println(Function.contains_GoMX("学习", Constant.HOBBIES));
		System.out.println(Function.contains_GoMX("PROGRAMMING", Constant.HOBBIES));
		System.out.println(Function.contains_GoMX("PROGRAMMING(Java, Android, Python)", Constant.HOBBIES));
		System.out.println(Function.contains_GoMX("PROGRAMMING(Java, Android, Python)", Constant.HOBBIES2));

		System.out.println();

		System.out.println(Function.contains_Sure("Sure GM", Constant.NAME));
		System.out.println(Function.contains_Sure("ure G", Constant.NAME));
		System.out.println(Function.contains_Sure("学习", Constant.HOBBIES));
		System.out.println(Function.contains_Sure("PROGRAMMING", Constant.HOBBIES));
		System.out.println(Function.contains_Sure("PROGRAMMING(Java, Android, Python)", Constant.HOBBIES));
		System.out.println(Function.contains_Sure("PROGRAMMING(Java, Android, Python)", Constant.HOBBIES2));

		Supermarket su = new Supermarket();
		System.out.println(new Supermarket().supermarket);
	}
}
