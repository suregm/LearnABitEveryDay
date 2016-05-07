import java.util.Scanner;

/**
 * Created by sure GM on 2016/5/1.
 */
public class Odd_Even {
    public static void main(String[] args) {
        int number;
        int count_odd = 0;
        int count_even = 0;
        System.out.println("pls input numbers, \"-1\" to stop.");
        Scanner s = new Scanner(System.in);
        do {
            number = Integer.parseInt(s.next());
            if (number != -1 && number < 0 || number > 100000) {
                System.out.println("pls input again");
            } else {
                if (number % 2 == 0) {
                    count_even++;
                }
                else if (number % 2 == 1){
                    count_odd++;
                }
            }
        } while (number != -1);

        System.out.println("the count of odd numbers is: " + count_odd + ", the count of even numbers is: " + count_even + ".");
    }

}
