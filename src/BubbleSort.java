import java.util.Scanner;

/**
 * Created by sure GM on 2015/9/18.
 */
class BubbleSort {
    public static void main(String[] args) {
        int[] a= new int[10];
        int i,j;
        int temp;

        System.out.printf("pls input 10 numbers: \n");
        try {
            Scanner s = new Scanner(System.in);
            for (i=0; i<10; i++) {
                a[i] = s.nextInt();
            }
            for (i=0; i<9; i++) {
                for (j=i+1; j<10; j++) {
                    if (a[i] > a[j]) {
                        temp = a[i];
                        a[i] = a[j];
                        a[j] = temp;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Numbers after sorting: ");
        for (i=0; i<10; i++) {
            System.out.printf("%d ", a[i]);
        }
    }
}
