package bitBeat.Matrix;

import java.util.Scanner;

/**
 * Created by sure GM on 2015/10/11.
 */
public class MatrixX
{
    private int mRow;
    private int mCol;

    public MatrixX(int rowCount, int colCount) {
        this.mRow = rowCount;
        this.mCol = colCount;
    }

    public void createMatrix(String ele) {
        int[][] matrix = new int[mRow][mCol];
        int count = 0;
        String[] eleArr = ele.replaceAll("\\t+", " ").replaceAll("\\s+", " ").trim().split(" ");
        for (int i = 0; i < mRow; i++) {
            for (int j = 0; j < mCol; j++) {
                if(count < eleArr.length && count % mCol != 0) {
                    matrix[i][j] = Integer.parseInt(eleArr[count]);
                    System.out.print(matrix[i][j]);
                    if((count + 1) % mCol == 0) {
                        System.out.println();
                    } else {
                        System.out.print("\t");
                    }
                }
                count++;
            }
        }
    }

    public static void main(String[] args)
    {
        int i;
        int j;
        int sum = 0;
        Scanner s = new Scanner(System.in);
        int[][] a = new int[3][3];
        System.out.println("pls input 9 numbers:");
        for (i=0; i<3; i++)
        {
            for (j=0; j<3; j++)
            {
                a[i][j] = s.nextInt();
            }
        }
        System.out.println("the 3 * 3 bitBeat.Matrix is:");
        for (i=0; i<3; i++)
        {
            for (j=0; j<3; j++)
            {
                System.out.printf(a[i][j] + " ");
            }
            System.out.println();
        }
        for (i=0; i<3; i++)
        {
            for (j=0; j<3; j++)
            {
                if (i == j)
                {
                    sum += a[i][j];
                }
            }
        }
        System.out.println("sum of the diagonal elements is: " + sum);
    }
}
