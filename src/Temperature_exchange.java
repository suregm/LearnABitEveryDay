/**
 * Created by sure GM on 2016/5/1.
 */

import java.util.Scanner;

public class Temperature_exchange {
    public static void main(String[] args)
    {
        int huashi;
        int sheshi;
        System.out.println("请输入要转换的华氏温度");
        Scanner s = new Scanner(System.in);
        huashi = s.nextInt();
        sheshi = (int)((huashi - 32) * (5.0/9));
        System.out.println("华氏" + huashi + "°F对应的摄氏温度为:" + sheshi + "°C");
    }
}
