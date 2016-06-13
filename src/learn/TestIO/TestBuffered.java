package learn.TestIO;

import java.io.*;

/**
 * Created by sure GM on 2016/6/14 6:07.
 */
public class TestBuffered {

	/**
	 * Buffered 缓冲区中转站，效率高
	 * @throws Exception
	 */
	public static void bufferedStream() throws Exception {
		File file = new File("G://Code//test.doc");
		InputStream in = new FileInputStream(file);
		OutputStream out = new FileOutputStream("G://testCopy2.doc");
		// 定义一个带缓冲的字节输入流
		BufferedInputStream bin = new BufferedInputStream(in);
		// 定义一个带缓冲的字节输出流
		BufferedOutputStream bout = new BufferedOutputStream(out);
		int b = 0;
		long startTime = System.currentTimeMillis();
		while ((b = bin.read()) != -1) {
			bout.write(b);
		}
		bin.close();
		bout.close();
		in.close();
		out.close();
		long endTime = System.currentTimeMillis();
		System.out.println("缓冲写入花费的时间是：" + (endTime - startTime)/1000 + "秒");
	}

	// 非缓冲复制文件
	public static void stream() throws Exception {
		File file = new File("G://Code//test.doc");
		InputStream in = new FileInputStream(file);
		OutputStream out = new FileOutputStream("G://testCopy.doc");
		int b = 0;
		long startTime = System.currentTimeMillis();
		while ((b = in.read()) != -1) {
			out.write(b);
		}
		in.close();
		out.close();
		long endTime = System.currentTimeMillis();
		System.out.println("非缓冲写入花费的时间是：" + (endTime - startTime)/1000 + "秒");
	}

	public static void main(String[] args) throws Exception {
		stream();
		bufferedStream();
	}
}
