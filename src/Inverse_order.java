import java.util.Scanner;

/**
 * Created by sure GM on 2015/10/11.
 */
public class Inverse_order
{
    public static void main(String[] args)
    {
        int i = 0;
        int j;
        Scanner s = new Scanner(System.in);
        int[] a = new int[20];
        System.out.println("pls input some positive numbers(input -1 to end): ");
        do
        {
            a[i] = s.nextInt();
            i++;
        }
        while (-1 != a[i-1]);
        System.out.println("your inputting list is:");
        for (j=0; j<i-1; j++)
        {
            System.out.printf(a[j] + " ");
        }
        System.out.println("\nlist after inverting is:");
        for (j=i-2; j>=0; j=j-1)
        {
            System.out.printf(a[j] + " ");
        }
    }
}
