package bitBeat;

/**
 * Created by sure GM on 2015/10/11.
 */
public class Peach
{
    public static void main(String[] args)
    {
        int i;
        int j = 0;
        int temp;
        int k;
        int count;
        for (i=4; i<10000; i+=4)    //�������ʣ��4��
        {
            count = 0;
            temp = i;
            for (k=0; k<5; k++)
            {
                j = i/4*5 + 1;
                i = j;
                if (0 == (j % 4))
                {
                    count++;
                }
                else
                {
                    break;
                }
            }
            i = temp;
            if (4 == count)
            {
                System.out.println("the peaches in total is: " + j);
                break;
            }
        }
    }
}
