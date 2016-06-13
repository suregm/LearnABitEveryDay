package learn.TestIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by sure GM on 2016/6/14 5:39.
 */
public class TestOutputStream {

	public static void main(String[] args) throws Exception {
		File file = new File("G://Code//test.txt");
		// InputStream/OutputStream 是抽象类，必须使用FileInputStream/FileOutputStream
		OutputStream out = new FileOutputStream(file);  // 覆盖
		OutputStream out2 = new FileOutputStream(file);  // 追加内容
		String str = "现在是二〇一六年六月十四日 06:02:51";
		byte[] b = str.getBytes();
		out.write(b);
		out.close();
		out2.write(b);
		out2.close();

	}
}
