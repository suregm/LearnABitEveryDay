package bitBeat;

import java.util.Scanner;

/**
 * Created by sure GM on 2015/10/11.
 */
public class Joseph_circle2
{
    public static void main(String[] args)
    {
        int i;
        int n;
        int leftCount;
        int numCount = 0;
        int index = 0;
        Scanner s = new Scanner(System.in);
        System.out.printf("pls input the number of people: ");
        n = s.nextInt();
        System.out.println();
        boolean[] arr = new boolean[n];
        for (i=0; i<arr.length; i++)
        {
            arr[i] = true;
        }
        leftCount = n;
        while(leftCount > 1)
        {
            if (arr[index]) //true == arr[index]
            {
                numCount ++;
                if (3 == numCount)
                {
                    numCount = 0;
                    arr[index] = false;
                    leftCount --;
                }
            }
            index ++;
            if (n == index) //��0ѭ��
            {
                index = 0;
            }
        }
        for (i=0; i<n; i++) {
            if (true == arr[i])
            {
                System.out.println("the left one is: " + (i + 1));
            }
        }
    }
}
