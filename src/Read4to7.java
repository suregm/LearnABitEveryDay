import java.util.Scanner;

/**
 * Created by sure GM on 2015/10/11.
 */
public class Read4to7
{
    public static void main(String[] args)
    {
        int i;
        int j;
        long a;
        String sa;
        Scanner s = new Scanner(System.in);
        System.out.println("pls input a number above 999999:");
        a = s.nextLong();
        sa = Long.toString(a);
        char[] ch = sa.toCharArray();   //convert to charArray
        i = ch.length;
        if (i < 7)
        {
            System.out.println("input error!");
        }
        else
        {
            System.out.println("Read4to7 is: ");
            for (j=7; j>=4; j--)
            {
                System.out.printf("%s",ch[i-j]);
            }
        }
    }
}
