package bitBeat;

import java.util.Scanner;

/**
 * Created by sure GM on 2016/5/7.
 */

/*
 素数和

 题目内容：
 我们认为2是第一个素数，3是第二个素数，5是第三个素数，依次类推。
 现在，给定两个整数n和m，0<n<=m<=200，你的程序要计算第n个素数到第m个素数之间所有的素数的和，包括第n个素数和第m个素数。
 注意，是第n个素数到第m个素数之间的所有的素数，并不是n和m之间的所有的素数。

 输入格式:
 两个整数，第一个表示n，第二个表示m。

 输出格式：
 一个整数，表示第n个素数到第m个素数之间所有的素数的和，包括第n个素数和第m个素数。
 */

public class SumOfPrimes {
    public static void main(String[] args)
    {
        int n;
        int m;
        int sum = 0;

        int[] mn = getMN();
        n = mn[0];
        m = mn[1];

        int[] prime = getPrimeList(200);
        System.out.print("prime[200]: ");
        for (int i = 0; i < prime.length; i++) {
            System.out.print(prime[i] + " ");
        }
        System.out.println();

        for (int i = n-1; i <= m-1; i++) {
            sum += prime[i];
        }
        System.out.println("第" + n + "个素数到第" + m + "个素数之间所有的素数的和是：" + sum);
    }

    public static int[] getMN() {
        int n;
        int m;
        int[] mnArray = new int[2];
        System.out.println("pls input 2 numbers(0<n<=m<=200) separated by space: ");
        Scanner s = new Scanner(System.in);
        String mn_input = s.nextLine();
        while (!mn_input.contains(" ")) {
            System.out.println("pls input again, contain a space");
            s = new Scanner(System.in);
        }
        String[] mn = mn_input.split(" ");
        n = Integer.parseInt(mn[0]);
        System.out.print("n=" + n + " ");
        m = Integer.parseInt(mn[1]);
        System.out.println("m=" + m);
        while (n <= 0 || n > 200 || m <= 0 || m > 200 || n > m) {
            System.out.println("pls input again.");
            s = new Scanner(System.in);
            mn = s.nextLine().split(" ");
            n = Integer.parseInt(mn[0]);
            m = Integer.parseInt(mn[1]);
        }
        mnArray[0] = n;
        mnArray[1] = m;
        return mnArray;
    }

    public static int[] getPrimeList(int n) {
        boolean b = false;
        int count = 2;
        int[] prime = new int[n];
        prime[0] = 2;
        prime[1] = 3;

        for (int i = 3;; i += 2) {
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    b = false;
                    break;
                } else {
                    b = true;
                }
            }
            if (b) {
                prime[count] = i;
                count++;
                if (count == n) {
                    break;
                }
            }

        }
        return prime;
    }
}
