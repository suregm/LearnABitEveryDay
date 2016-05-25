package diary;

import java.util.Scanner;

/**
 * Created by sure GM on 2016/5/21 23:57.
 */
public class wr {
	public static void main(String[] args)
	{
		System.out.println("pls input the date formed as '19891202'('-1' to end): ");
		Scanner s = new Scanner(System.in);
		int date = s.nextInt();
		while (-1 != date) {
			switch (date) {
				case 20160525:
					System.out.println("\t\tIt\'s raining...");
					System.out.println();
					break;
				case 20160521:
					System.out.println("\t\t遇见的总会遇见\n" +
							"\t\t`\n" +
							"\t\tSEE YOU AGAIN\n" +
							"\t\t`\n" +
							"\n" +
							"\t\t上天是公平的，遇见的总会遇见。不论是你的努力多少，不论是你们失联多久，不论是你有心或无意，你该遇见的总会遇见。");
					System.out.println();
					break;
				case 20080625:
					System.out.println("\t\t趟过高考的我们\n" +
							"\n" +
							"\t\t很晚了\n" +
							"\t\t不知道夜空有没有星星\n" +
							"\t\t我们心中需要明灯\n" +
							"\t\t和同学朋友聊了很多\n" +
							"\t\t真是现实让人受折磨\n" +
							"\t\t成绩似坎坷\n" +
							"\t\t逼着我们趟过\n" +
							"\t\t有的是鲤鱼 跳过了龙门\n" +
							"\t\t有的止步于漩涡\n" +
							"\t\t一切的一切\n" +
							"\t\t过去的我们无从把握\n" +
							"\t\t需要我们做的就是把即将到来的明天捕捉\n" +
							"\t\t眼睛是春天的海\n" +
							"\t\t过去的不能重头再来\n" +
							"\t\t青春是绿色的河\n" +
							"\t\t年轻的我们要走的路还有很多很多\n" +
							"\t\t我们应该哦\n" +
							"\t\t心儿是永远的琴弦\n" +
							"\t\t坚定也执着\n" +
							"\t\t像坚强的草儿\n" +
							"\t\t用点绿大地来证明自我\n" +
							"\t\t海豚sure");
					System.out.println();
					break;
				default:
					System.out.println("thank you!");
					break;
			}
			date = s.nextInt();
		}
	}
}
