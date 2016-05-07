/**
 * Created by sure GM on 2015/10/11.
 */
public class String_sort
{
    public static void main(String[] args)
    {
        int i;
        int j;
        int N = 5;
        String temp = null;
        String[] s = new String[N];
        s[0] = "hello";
        s[1] = "goodbye";
        s[2] = "gongmingxu";
        s[3] = "state";
        s[4] = "gas";
        for (i=0; i<N; i++)
        {
            for (j=i+1; j<N; j++)
            {
                if (!compare(s[i], s[j]))
                {
                    temp = s[i];
                    s[i] = s[j];
                    s[j] = temp;
                }
            }
        }
        for (i=0; i<N; i++)
        {
            System.out.println(s[i]);
        }
    }

    private static boolean compare(String s1, String s2) {
        boolean result = true;
        for (int i=0; i<s1.length() && i<s2.length(); i++)
        {
            if (s1.charAt(i) > s2.charAt(i))
            {
                result = false;
                break;
            }
            else if (s1.charAt(i) < s2.charAt(i))
            {
                result = true;
                break;
            }
            else
            {
                if (s1.length() < s2.length())
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
            }
        }
        return result;
    }
}
