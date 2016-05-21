package bitBeat; /**
 * Created by sure GM on 2016/5/12 22:54.
 */

import java.util.Scanner;

/**
 字数统计
 题目内容：
 你的程序要读入一篇英文文章，然后统计其中的单词数来输出。需要统计的数据为：

 总的单词数量；
 含有1个字母到10个字母的单词的数量。

 单词和单词的间隔是由各种空格（包括空格、tab和回车换行）形成的，也就是说，这里的单词与语言无关，可以包括各种符号，比如“it's”算一个单词，长度为4。注意，行中可能出现连续的空格。长度超过10个字母的单词需要计入总的单词数量，但是不做分类统计。

 因此，对于下面的句子：
 "What you see is a very hefty response," said CNN Aviation Correspondent Rene Marsh.
 其中的单词共14个。注意这里的response,"后面跟了空格。

 输入格式:
 一篇英文文章。

 输出格式：
 11个数字，依次是总的单词数量、1个字母的单词数量、2个字母的单词数量。。。10个字母的单词的数量。每个数字后有一个空格。行末有一个回车换行。

 输入样例：
 "What you see is a very hefty response," said CNN Aviation Correspondent Rene Marsh.

 输出样例：
 14 1 1 3 3 2 1 0 1 0 1

 时间限制：500ms内存限制：32000kb
 */

public class WordCount {
    public static void main(String[] args)
    {

    }

    public static void input(int leftPoint, int rightPoint) {
        int n;
        System.out.println("pls input an article:");
        Scanner s = new Scanner(System.in);
        String input = s.next();


    }
}
