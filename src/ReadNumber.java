/**
 * Created by sure GM on 2016/5/7.
 */

import java.util.Scanner;

/**
 念整数
 题目内容：
 你的程序要读入一个整数，范围是[-100000,100000]。然后，用汉语拼音将这个整数的每一位输出出来。
 如输入1234，则输出：
 yi er san si
 注意，每个字的拼音之间有一个空格，但是最后的字后面没有空格。当遇到负数时，在输出的开头加上“fu”，如-2341输出为：
 fu er san si yi

 输入格式:
 一个整数，范围是[-100000,100000]。

 输出格式：
 表示这个整数的每一位数字的汉语拼音，每一位数字的拼音之间以空格分隔，末尾没有空格。
 */

public class ReadNumber {
    public static void main(String[] args)
    {

        int n = input(-100000, 100000);
        readNumber(n);

    }

    public static void readNumber(int number) {
        for (int i = 0; i < Integer.toString(number).length(); i++) {
            char c = Integer.toString(number).charAt(i);
            boolean isLastChar;
            if (i < Integer.toString(number).length()-1) {
                isLastChar = false;
            }
            else {
                isLastChar = true;
            }
            pinyin(c, isLastChar);
        }
    }

    public static void pinyin(char c, boolean isLastChar) {
        switch (c) {
            case '-' :
                System.out.print("fu");
                break;
            case '0' :
                System.out.print("ling");
                break;
            case '1' :
                System.out.print("yi");
                break;
            case '2' :
                System.out.print("er");
                break;
            case '3' :
                System.out.print("san");
                break;
            case '4' :
                System.out.print("si");
                break;
            case '5' :
                System.out.print("wu");
                break;
            case '6' :
                System.out.print("liu");
                break;
            case '7' :
                System.out.print("qi");
                break;
            case '8' :
                System.out.print("ba");
                break;
            case '9' :
                System.out.print("jiu");
                break;
            default:
                break;
        }

        if (!isLastChar) {
            System.out.print(" ");
        }
    }

    public static int input(int leftPoint, int rightPoint) {
        int n;
        System.out.println("pls input a number:");
        Scanner s = new Scanner(System.in);
        int input = s.nextInt();
        while (input < leftPoint || input > rightPoint) {
            System.out.println("pls input again.");
            s = new Scanner(System.in);
            input = s.nextInt();
        }
       return input;
    }
}
