package learn.TestIO;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by sure GM on 2016/6/13 23:38.
 */
public class TestReader {

	public static void main(String[] args) throws Exception {
	    File file = new File("G://Code//test.txt");
		Reader reader = new FileReader(file);
		char c[] = new char[1024];  //字符数组
		int len;
		try {
			// 方法一
			len = reader.read(c);   // 限定读取长度，避免出现空内容或乱码
		} catch (IOException e) {
			// 方法二
			int temp = 0;
			len = 0;
			while ((temp = reader.read()) != -1) {
				c[len++] = (char)temp;
			}
		}

		reader.close();
		System.out.println("读取的内容是：" + new String(c, 0, len));
	}
}
