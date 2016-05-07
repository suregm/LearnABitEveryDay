/**
 * Created by sure GM on 2015/10/11.
 */
public class Yanghui
{
    public static void main(String[] args)
    {
        int i;
        int j;
        int k;
        int N = 10;
        int[][] a = new int[10][10];
        for (i=0; i<10; i++)
        {
            a[i][i] = 1;
            a[i][0] = 1;
        }
        for (i=2; i<10; i++)
        {
            for (j=1; j<i; j++)
            {
                a[i][j] = a[i-1][j-1] + a[i-1][j];
            }
        }
        for (i=0; i<10; i++)
        {
            for (k=0; k<3*(N-i)-1; k++) //
            {
                System.out.printf(" ");
            }
            for (j=0; j<=i; j++)
            {
                System.out.printf(a[i][j] + "    ");
            }
            System.out.println();
        }
    }
}
