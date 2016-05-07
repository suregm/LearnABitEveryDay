/**
 * Created by sure GM on 2015/10/11.
 */
public class Prime
{
    public static void main(String[] args)
    {
        boolean b = false;
        System.out.printf("Prime above 100 are:\n");
        System.out.printf(2 + " ");
        System.out.printf(3 + " ");
        for (int i=3; i<100; i+=2)
        {
            for (int j=2; j<=Math.sqrt(i); j++)
            {
                if (i % j == 0)
                {
                    b = false;
                    break;
                }
                else
                {
                    b = true;
                }
            }
            if (b)
            {
                System.out.printf(i + " ");
            }
        }
    }
}
