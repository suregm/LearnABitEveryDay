package learn.TestIO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by sure GM on 2016/6/14 5:30.
 */
public class TestWriter {

	public static void main(String[] args) throws IOException {
		File file = new File("G://Code//test.txt");
		Writer out = new FileWriter(file);  // 覆盖
		Writer out2 = new FileWriter(file, true);  // 追加
		String str = "现在是二〇一六年六月十四日 05:32:13";
		out.write(str); // 将字符串写入输出流
		out.close();
		out2.write(str);
		out2.close();
	}
}
