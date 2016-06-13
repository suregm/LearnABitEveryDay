package learn.TestIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by sure GM on 2016/6/14 5:39.
 */
public class TestInputStream {

	public static void main(String[] args) throws Exception {
		File file = new File("G://Code//test.txt");
		// InputStream/OutputStream 是抽象类，必须使用FileInputStream/FileOutputStream
		InputStream in = new FileInputStream(file);
		int len = 0;
		byte[] b = null;
		try {
			// 方法一
			b = new byte[1024];
			len = in.read(b);
			System.out.println("读取的内容是：" + new String(b, 0, len));
		} catch (Exception e) {
			// 方法二
			// 定义指定大小的空间字节数组，节省空间
			int fileLength = (int)file.length();
			b = new byte[fileLength];
//			len = in.read(b);
			System.out.println("读取的内容是：" + new String(b));
		} finally {
			// 方法三
			int temp = 0;
			len = 0;
			while ((temp = in.read()) != -1) {
				// 一个字节一个字节读取，放到b字节数组里
				b[len++] = (byte)temp;
			}
			System.out.println("读取的内容是：" + new String(b));
		}
		in.close();
	}
}
