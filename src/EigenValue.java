import java.util.Scanner;

/**
 * Created by sure GM on 2016/5/7.
 * 特征值
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

        if (number < 0 || number >= 1000000) {
            System.out.println("pls input again. a number between 0 and 1,000,000.");
            number = Integer.parseInt(s.next());
        }
        else {
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
}
