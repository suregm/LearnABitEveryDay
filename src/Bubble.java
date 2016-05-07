import java.util.Scanner;

/**
 * Created by sure GM on 2015/10/11.
 */
public class Bubble
{
    public static void main(String[] args)
    {
        int i;
        int j;
        int temp;
        Scanner s = new Scanner(System.in);
        int[] a = new int[10];
        System.out.println("pls input 10 numbers: ");
        for (i=0; i<10; i++)
        {
            a[i] = s.nextInt();
        }
        for (i=0; i<9; i++)
        {
            for (j=i+1; j<10; j++)
            {
                if (a[i] > a[j])
                {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        System.out.println("after sorted: ");
        for (i=0; i<10; i++)
        {
            System.out.printf(a[i] + " ");
        }
    }
}
