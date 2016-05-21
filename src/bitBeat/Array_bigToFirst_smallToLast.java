package bitBeat;

import java.util.Scanner;

/**
 * Created by sure GM on 2015/10/11.
 */
public class Array_bigToFirst_smallToLast
{
    public static void main(String[] args)
    {
        int N = 8;
        int i;
        int temp;
        int[] a = new int[N];
        int idMax = 0;
        int idMin = 0;
        Scanner s = new Scanner(System.in);
        System.out.println("pls input 8 numbers:");
        for (i=0; i<N; i++)
        {
            a[i] = s.nextInt();
        }
        System.out.println("your inputted array is:");
        for (i=0; i<N; i++)
        {
            System.out.printf(a[i] + " ");
        }
        int max = a[0];
        int min = a[0];
        for (i=0; i<N; i++)
        {
            if (a[i] > max)
            {
                max = a[i];
                idMax = i;
            }
            if (a[i] < min)
            {
                min = a[i];
                idMin = i;
            }
        }
        if (0 != idMax)
        {
            temp = a[0];
            a[0] = a[idMax];
            a[idMax] = temp;
        }
        if ((N-1) != idMin)
        {
            temp = a[N-1];
            a[N-1] = a[idMin];
            a[idMin] = temp;
        }
        System.out.println("\nThe array after exchange is:");
        for (i=0; i<N; i++)
        {
            System.out.printf(a[i] + " ");
        }
    }
}
