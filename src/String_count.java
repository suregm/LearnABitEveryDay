import java.util.Scanner;

/**
 * Created by sure GM on 2015/10/18.
 */
public class String_count
{
    public static void main(String[] args)
    {
        String str1;
        String str2;
        Scanner s = new Scanner(System.in);
        System.out.println("pls input the first string:");
        str1 = s.nextLine();
        System.out.println("pls input the second string:");
        str2 = s.nextLine();
        int index = -1;
        int count = 0;
        index = str1.indexOf(str2);
        while (-1 != index)
        {
            ++count;
            index = str1.indexOf(str2, index + str2.length());
        }
        System.out.println(str2 + " appears " + count + " times in " + str1);
    }
}
