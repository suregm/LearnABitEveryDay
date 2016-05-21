package bitBeat;

import java.util.Scanner;

/**
 * Created by sure GM on 2015/10/11.
 */
public class Move_backward_m
{
    public static void main(String[] args)
    {
        int i;
        int m;
        int N = 10;
        int[] a = new int[N];
        Scanner s = new Scanner(System.in);
        System.out.println("pls input 10 numbers:");
        for (i=0; i<N; i++)
        {
            a[i] = s.nextInt();
        }
        System.out.println("your inputted array is:");
        for (i=0; i<N; i++)
        {
            System.out.printf(a[i] + " ");
        }
        System.out.println("\npls input the digit for moving backward:");
        m = s.nextInt();
        int[] b = new int[m];   //��ʱ����
        for (i=0; i<m; i++)
        {
            b[i] = a[N-m+i];
        }
        for (i=N-1; i>=m; i--)
        {
            a[i] = a[i-m];
        }
        for (i=0; i<m; i++)
        {
            a[i] = b[i];
        }
        System.out.println("the array after moving is:");
        for (i=0; i<N; i++)
        {
            System.out.printf(a[i] + " ");
        }
    }
}
