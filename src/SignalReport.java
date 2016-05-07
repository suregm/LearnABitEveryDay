import java.util.Scanner;

/**
 * Created by sure GM on 2016/5/1.
 */
public class SignalReport {
    public static void main(String[] args) throws NullPointerException
    {
        String signal;
        int num_readability;
        int num_strength;
        String readability = null;
        String strength = null;
        System.out.println("pls input a number:");
        Scanner s = new Scanner(System.in);
        signal = s.next();
        num_readability = Integer.parseInt(signal.substring(0, 1));
        num_strength = Integer.parseInt(signal.substring(1));
        switch (num_readability) {
            case 1 :
                readability = "Unreadable";
                break;
            case 2 :
                readability = "Barely readable, occasional words distinguishable";
                break;
            case 3 :
                readability = "Readable with considerable difficulty";
                break;
            case 4 :
                readability = "Readable with practically no difficulty";
                break;
            case 5 :
                readability = "Perfectly readable";
                break;

        }
        switch (num_strength) {
            case 1 :
                strength = "Faint signals, barely perceptible";
                break;
            case 2 :
                strength = "Very weak signals";
                break;
            case 3 :
                strength = "Weak signals";
                break;
            case 4 :
                strength = "Fair signals";
                break;
            case 5 :
                strength = "Fairly good signals";
                break;
            case 6 :
                strength = "Good signals";
                break;
            case 7 :
                strength = "Moderately strong signals";
                break;
            case 8 :
                strength = "Strong signals";
                break;
            case 9 :
                strength = "Extremely strong signals";
                break;
        }
        System.out.println(strength + ", " + readability.toLowerCase() + ".");
    }
}
