/**
 * Created by sure GM on 2016/5/10 23:29.
 */

import java.util.Scanner;

/**
 单词长度
 题目内容：
 你的程序要读入一行文本，其中以空格分隔为若干个单词，以‘.’结束。你要输出这行文本中每个单词的长度。这里的单词与语言无关，可以包括各种符号，比如“it's”算一个单词，长度为4。注意，行中可能出现连续的空格。

 输入格式:
 输入在一行中给出一行文本，以‘.’结束，结尾的句号不能计算在最后一个单词的长度内。

 输出格式：
 在一行中输出这行文本对应的单词的长度，每个长度之间以空格隔开，行末没有最后的空格。

 输入样例：
 It's great to see you here.

 输出样例：
 4 5 2 3 3 4
 时间限制：500ms内存限制：32000kb
 */

public class WordLength {
    public static void main(String[] args)
    {

    }

    public static String input() {
        int n;
        System.out.println("pls input a number:");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        while (!input.endsWith(".")) {
            System.out.println("pls input again.");
            s = new Scanner(System.in);
            input = s.nextLine();
        }
        return input;
    }
}
