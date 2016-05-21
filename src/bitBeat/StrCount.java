package bitBeat;

/**
 * Created by sure GM on 2016/5/21 18:59.
 * 统计一个字符串在另一个字符串中出现的次数
 */
public class StrCount {
	public static void main(String[] args) {
		String str1 = "abcsdfsdfdgde2343abcr3t4tabcbabc2454";
		String str2 = "abc";
		int index = -1;
		int count = 0;

		index = str1.indexOf(str2);		//把str2看作一个整体
		while(-1 != index) {
			++count;
			index = str1.indexOf(str2, index+str2.length());
		}
		System.out.printf("str2在str1中出现的次数为：%d\n", count);
	}
}
