package bitBeat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by sure GM on 2015/10/18.
 */
public class Student
{
    public static void main(String[] args)
    {
        int i;
        int j;
        Scanner s = new Scanner(System.in);
        String [][] a = new String[5][6];
        for (i=0; i<5; i++)
        {
            System.out.println("pls input the id of the " + (i + 1) + "th student:");
            a[i][0] = s.nextLine();
            System.out.println("pls input the name of the " + (i + 1) + "th student:");
            a[i][1] = s.nextLine();
            for (j=2; j<5; j++)
            {
                System.out.println("pls input the " + (j - 1) + "th score of this student:");
                a[i][j] = s.nextLine();
            }
            System.out.println();
        }

        //calculate average of the scores
        int sum;
        float avg;
        for (i=0; i<5; i++)
        {
            sum = 0;
            for (j=2; j<5; j++)
            {
                sum += Integer.parseInt(a[i][j]);
            }
            avg = (float) sum / 3;
            a[i][5] = String.valueOf(avg);
        }

        //write to the disk
        String s1;
        try
        {
            File f = new File("d:\\bitBeat.Student.txt");
            if (f.exists())
            {
                System.out.println("the file exists");
            }
            else
            {
                System.out.println("the file does not exist, it's being creating the file");
                f.createNewFile();
            }
            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            for (i=0; i<5; i++)
            {
                for (j=0; j<6; j++)
                {
                    s1 = a[i][j] + "\r\n";
                    output.write(s1);
                }
            }
            output.close();
            System.out.println("the data has been written into the txt file, pls check d:\\bitBeat.Student.txt");
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }

    }
}
