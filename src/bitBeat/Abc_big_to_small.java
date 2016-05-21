package bitBeat;

import java.util.Scanner;

/**
 * Created by sure GM on 2015/10/11.
 */
public class Abc_big_to_small
{
    public static void main(String[] args)
    {
        int a;
        int b;
        int c;
        int temp;
        Scanner s = new Scanner(System.in);
        System.out.println("pls input 3 numbers:");
        a = s.nextInt();
        b = s.nextInt();
        c = s.nextInt();
        if (a < b)
        {
            temp = a;
            a = b;
            b = temp;
        }
        if (a < c)
        {
            temp = a;
            a = c;
            c = temp;
        }
        if (b < c)
        {
            temp = b;
            b = c;
            c = temp;
        }
        System.out.println("three numbers sorted from big to small is:");
        System.out.println(a + " " + b + " " + c);
    }
}
