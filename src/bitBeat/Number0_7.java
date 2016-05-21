package bitBeat;

/**
 * Created by sure GM on 2015/10/11.
 */
public class Number0_7
{
    public static void main(String[] args)
    {
        int sum = 4;
        int i;
        System.out.println("1 digit: " + sum);
        sum *= 6;
        System.out.println("2 digits: " + sum);
        for (i=3; i<9; i++)
        {
            sum *= (9-i);
            System.out.println(i + " digits: " + sum);
        }
    }
}
