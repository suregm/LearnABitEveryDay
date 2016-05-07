/**
 * Created by sure GM on 2015/10/11.
 */
public class Prime2
{
    public static void main(String[] args)
    {
        int[] a = new int[] {2, 3, 5, 7};
        for (int j=0; j<4; j++)
        {
            System.out.printf(a[j] + " ");
        }
        boolean b = false;
        for (int i=11; i<100; i+=2)
        {
            for (int j=0; j<4; j++)
            {
                if (i % a[j] == 0)
                {
                    b = false;
                    break;
                }
                else
                {
                    b = true;
                }
            }
            if (b = true)
            {
                System.out.printf(i + " ");
            }
        }
    }
}
