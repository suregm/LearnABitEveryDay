package bitBeat.Matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * Created by sure GM on 2017/4/5.
 */
public class ReadRdp {
    public static void main(String[] args) {

        String filePath = "G:\\Code\\LearnABitEveryDay!\\src\\bitBeat\\Matrix\\Rdp101.txt";
        String content = readFile(filePath);

        System.out.println("The NAME is: " + getName(content));
        System.out.println("The CAPACITY is: " + getCapacity(content));
        System.out.println("The MATRIX is: ");
        getMatrix(content);
    }

    public static String readFile(String filePath) {
        String content = "";

        File file = new File(filePath);
        StringBuffer sb = new StringBuffer();
        int rowCount = -1;
        int colCount = -1;
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str = "";
            while((str = in.readLine()) != null && !str.replaceAll("\\t?+", " ").replaceAll("\\s?+", " ").equals("")) {
                sb.append(str + " ");

//                if(str.contains("XCOORD.") && str.contains("YCOORD.")) {
//                    colCount = str.trim().split("\\t").length;
//                }

                if(sb.toString().contains("XCOORD.") && sb.toString().contains("YCOORD.")) {
                    rowCount++;
                    colCount = str.replaceAll("\\t+", " ").replaceAll("\\s+", " ").trim().split(" ").length;
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        sb.append(" | " + rowCount);
        sb.append(" " + colCount);

        content = sb.toString().replaceAll("\\t+", " ").replaceAll("\\s+", " ").trim();
        System.out.println(content);
        return content;
    }

    public static String getName(String content) {
        return content.substring(0, content.indexOf(" "));
    }

    public static int getCapacity(String content) {
        return Integer.parseInt(content.substring(content.indexOf("CAPACITY")).split(" ")[3]);
    }

    public static void getMatrix(String content) {
        String ele = content.substring(content.indexOf("SERVICE TIME") + "SERVICE TIME".length(), content.indexOf(" | ")).trim();
        int rowCount = Integer.parseInt(content.substring(content.indexOf(" | ") + " | ".length()).split(" ")[0]);
        int colCount = Integer.parseInt(content.substring(content.indexOf(" | ") + " | ".length()).split(" ")[1]);

//        System.out.println(ele);
//        System.out.println("rowCount: " + rowCount);
//        System.out.println("colCount: " + colCount);

        MatrixX matrixX = new MatrixX(rowCount, colCount);
        matrixX.createMatrix(ele);
    }
}
