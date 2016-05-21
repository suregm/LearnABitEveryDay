package bitBeat;

import java.util.Scanner;

/**
 * Created by sure GM on 2016/5/7.
 * 特征值
 */

/**
 数字特征值
 题目内容：
 对数字求特征值是常用的编码算法，奇偶特征是一种简单的特征值。对于一个整数，从个位开始对每一位数字编号，个位是1号，十位是2号，以此类推。这个整数在第n位上的数字记作x，如果x和n的奇偶性相同，则记下一个1，否则记下一个0。按照整数的顺序把对应位的表示奇偶性的0和1都记录下来，就形成了一个二进制数字。比如，对于342315，这个二进制数字就是001101。

 这里的计算可以用下面的表格来表示：
 数字
 3 4 2 3 1 5
 数位
 6 5 4 3 2 1
 数字奇偶
 奇 偶 偶 奇 奇 奇
 数位奇偶
 偶 奇 偶 奇 偶 奇
 奇偶一致
 0 0 1 1 0 1
 二进制位值
 32 16 8 4 2 1

 按照二进制位值将1的位的位值加起来就得到了结果13。

 你的程序要读入一个非负整数，整数的范围是[0,100000]，然后按照上述算法计算出表示奇偶性的那个二进制数字，输出它对应的十进制值。

 提示：将整数从右向左分解，数位每次加1，而二进制值每次乘2。
 */

public class EigenValue {
    public static void main(String[] args)
    {
        int number;
        int[] nums = new int[6];
        int[] binary = new int[6];  //存放数位奇偶特征值
        int decimal = 0;    //十进制值

        System.out.println("pls input a number between 0 and 1,000,000.");
        Scanner s = new Scanner(System.in);
        number = Integer.parseInt(s.next());

        while (number < 0 || number >= 1000000) {
            System.out.println("pls input again. a number between 0 and 1,000,000.");
            number = Integer.parseInt(s.next());
        }
        for (int i = 0; i < 6; i++) {
            nums[i] = number % 10;
            number = number / 10;
        }

        for (int i = 0; i < 6; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < 6; i++) {
            if (((i+1) % 2) == (nums[i] % 2)) {
                binary[i] = 1;
            }
            else {
                binary[i] = 0;
            }
        }

        for (int i = 0; i < 6; i++) {
            System.out.print(binary[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < binary.length; i++) {
            decimal += binary[i] * Math.pow(2, i);
        }
        System.out.println(decimal);
    }
}
