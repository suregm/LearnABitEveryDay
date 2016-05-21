package bitBeat;

/**
 * Created by sure GM on 2016/5/21 18:53.
 * 统计一个String对象中大写字母、小写字母、非字母，数字各自出现的个数
 */
public class CharCount {
	public static void main(String[] args) {
		String str = "abAM1,!23；  ";
		int countU = 0;
		int countL = 0;
		int countOther = 0;
		int countNum = 0;
		int i;

		//		//方法一
		//		for(i=0; i<str.length(); ++i) {
		//			char ch = str.charAt(i);
		//			if(ch>='a' && ch<='z') {
		//				countL++;
		//			} else if(ch>='A' && ch<='Z') {
		//				countU++;
		//			} else {
		//				countOther++;
		//				if(ch>='0' && ch<='9') {
		//					countNum++;
		//				}
		//			}
		//		}

		//		//方法二
		//		for(i=0; i<str.length(); ++i) {
		//			char ch = str.charAt(i);
		//			if(Character.isUpperCase(ch))
		//				countU++;
		//			else if(Character.isLowerCase(ch))
		//				countL++;
		//			else {
		//				countOther++;
		//			}
		//		}

		//方法三
		String s1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String s2 = "abcdefghijklmnopqrstuvwxyz";
		String s3 = "0123456789";
		for (i=0; i<str.length(); ++i)
		{
			char ch = str.charAt(i);

			if (-1 != (s1.indexOf(ch)))
			{
				countU++;
			}
			else if (-1 != s2.indexOf(ch))
			{
				countL++;
			}
			else
			{
				countOther++;
				if(-1 != s3.indexOf(ch))
					countNum++;
			}
		}

		System.out.printf("大写字母的个数为：%d\n", countU);
		System.out.printf("小写字母的个数为：%d\n", countL);
		System.out.printf("非字母的个数为：%d\n", countOther);
		System.out.printf("数字的个数为：%d\n", countNum);
	}
}
