import java.util.Scanner;

/**
 * Created by sure GM on 2016/5/1.
 */
public class TimeZoneExchange {
    public static void main(String[] args)
    {
        String utcTime;
        String bjtTime;
        int hour_utc;
        int minutes_utc;
        int hour_bjt;
        int minutes_bjt;
        System.out.println("pls input a UTC time:");
        Scanner s = new Scanner(System.in);
        utcTime = s.next();
        if (Integer.parseInt(utcTime) < 0 || Integer.parseInt(utcTime) > 2359) {
            System.out.println("pls input again");
        }
        else {
            switch (utcTime.length()) {
                case 1:
                    hour_utc = 0;
                    minutes_utc = Integer.parseInt(utcTime);
                    hour_bjt = 24 - 8;
                    minutes_bjt = minutes_utc;
                    System.out.println("" + hour_bjt + "0" + minutes_bjt);
                    break;
                case 2:
                    hour_bjt = 24 - 8;
                    System.out.println("" + hour_bjt + utcTime);
                    break;
                case 3:
                    hour_utc = Integer.parseInt(utcTime.substring(0, 1));
                    minutes_utc = Integer.parseInt(utcTime.substring(1));
                    if (hour_utc < 8) {
                        hour_bjt = hour_utc + 24 - 8;
                        System.out.println("" + hour_bjt + utcTime.substring(1));
                    } else if (hour_utc == 8) {
                        minutes_bjt = minutes_utc;
                        System.out.println(minutes_bjt);
                    } else {
                        hour_bjt = hour_utc - 8;
                        System.out.println("" + hour_bjt + utcTime.substring(1));
                    }
                    break;
                case 4:
                    hour_utc = Integer.parseInt(utcTime.substring(0, 2));
                    minutes_utc = Integer.parseInt(utcTime.substring(2));
                    if (hour_utc < 8) {
                        hour_bjt = hour_utc + 24 - 8;
                        System.out.println("" + hour_bjt + utcTime.substring(2));
                    } else if (hour_utc == 8) {
                        minutes_bjt = minutes_utc;
                        System.out.println(minutes_bjt);
                    } else {
                        hour_bjt = hour_utc - 8;
                        System.out.println("" + hour_bjt + utcTime.substring(2));
                    }

            }
        }
    }
}
