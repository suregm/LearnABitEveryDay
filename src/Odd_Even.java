import java.util.Scanner;

/**
 * Created by sure GM on 2016/5/1.
 */

/**
 奇偶个数
 题目内容：
 你的程序要读入一系列正整数数据，输入-1表示输入结束，-1本身不是输入的数据。程序输出读到的数据中的奇数和偶数的个数。

 输入格式:
 一系列正整数，整数的范围是（0,100000）。如果输入-1则表示输入结束。

 输出格式：
 两个整数，第一个整数表示读入数据中的奇数的个数，第二个整数表示读入数据中的偶数的个数。两个整数之间以空格分隔。
 */

public class Odd_Even {
    public static void main(String[] args) {
        int number;
        int count_odd = 0;
        int count_even = 0;
        System.out.println("pls input numbers, \"-1\" to stop.");
        Scanner s = new Scanner(System.in);
        do {
            number = Integer.parseInt(s.next());
            if (number != -1 && number < 0 || number > 100000) {
                System.out.println("pls input again");
            } else {
                if (number % 2 == 0) {
                    count_even++;
                }
                else if (number % 2 == 1){
                    count_odd++;
                }
            }
        } while (number != -1);

        System.out.println("the count of odd numbers is: " + count_odd + ", the count of even numbers is: " + count_even + ".");
    }

}
