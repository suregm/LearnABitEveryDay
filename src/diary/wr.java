package diary;

import java.util.Scanner;

/**
 * Created by sure GM on 2016/5/21 23:57.
 */
public class wr {
	public static void main(String[] args)
	{
		System.out.println("pls input the date formed as '19891202'('-1' to end): \n");
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
				case 20160606:
					System.out.println("\t\tAs many of you prepare to take the National Higher Education Entrance Examination, I want to wish you, the next generation of scientific minds, success in your academic endeavors. This culmination of your hard work marks just the beginning of your very bright futures.\n" +
							"\n" +
							"\t\tGrowing up, my parents placed a high value on education and I am grateful for the limitless opportunities provided by my studies. Whether you aim to be a doctor, teacher, scientist, musician, engineer, or a writer — be fearless in the pursuit of your aspirations. You are the next generation of big thinkers and thought leaders that will shape the future for generations to come. - SH\n" +
							"\n" +
							"\t\t你们中的许多人即将参加#2016高考#，我在这里祝愿你们，新一代的科学人才，金榜题名。这是你们勤学不辍的顶峰，也标志着你们美好未来的开始。\n" +
							"\n" +
							"\t\t在我成长的过程中，我的父母一直很重视教育。我很庆幸这一路来学习给我带来得无数的机会。无论你励志成为一名医生、老师、科学家、音乐人、工程师或是作家 —— 请勇往直前地追逐你的梦想。你们是下一代的大思想家和意见领袖；未来将因你们而生。\n" +
							"\t\t——史蒂芬霍金_StephenHawking  \n" +
							"\t\t——6月6日 08:00 來自微博 weibo.com");
					break;
				case 20160607:
					System.out.println("\t\t高考加油！");
					break;
				case 20160608:
					System.out.println("\t\t晚上回家啦！小雨，端午节见，有粽子、饼干、棒棒糖还有德芙巧克力，o(∩_∩)o 哈哈");
					break;
				default:
					System.out.println("\t\tthank you!");
					break;
			}
			date = s.nextInt();
		}
	}
}
