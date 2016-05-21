package bitBeat;

import java.util.Scanner;

/**
 * Created by sure GM on 2015/10/11.
 */
public class Matrix
{
    public static void main(String[] args)
    {
        int i;
        int j;
        int sum = 0;
        Scanner s = new Scanner(System.in);
        int[][] a = new int[3][3];
        System.out.println("pls input 9 numbers:");
        for (i=0; i<3; i++)
        {
            for (j=0; j<3; j++)
            {
                a[i][j] = s.nextInt();
            }
        }
        System.out.println("the 3 * 3 bitBeat.Matrix is:");
        for (i=0; i<3; i++)
        {
            for (j=0; j<3; j++)
            {
                System.out.printf(a[i][j] + " ");
            }
            System.out.println();
        }
        for (i=0; i<3; i++)
        {
            for (j=0; j<3; j++)
            {
                if (i == j)
                {
                    sum += a[i][j];
                }
            }
        }
        System.out.println("sum of the diagonal elements is: " + sum);
    }
}
